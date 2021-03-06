// Brett Barinaga and Andrew Flagstead
// CPSC 450 
// OurSpace
// Motherland.java

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.lang.*;


// NOTE: RUN THIS CLASS FOR TESTING
//https://www.geeksforgeeks.org/java-util-hashmap-in-java/
//https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html

public class Motherland {
    private static ArrayList<Comrade> proletariat;

    Motherland() {
        try {
        File file = new File("Proletariat");
        readAndPopulateProles(file);
        readAndAddConnections(file);
        }

        catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", "Proletariat");
            e.printStackTrace();
        }
    }
    
    // Should not be run this is only for testing purposes. To run the actual program
    // use Kremlin.java as the executable class
    public static void main(String[] args) {
        try {
            File file = new File("Proletariat");
            readAndPopulateProles(file);
            readAndAddConnections(file);
            System.out.println();

            for (int j = 0; j < proletariat.size(); j++) {
                Comrade testComrade = proletariat.get(j);

                System.out.println("Test Comrade " + testComrade.getName());
                for (int i = 0; i < testComrade.getComrades().size(); i++) {
                    System.out.println(testComrade.getName() +"'s Comrade: " + testComrade.getComrades().get(i).getComrade().getName() + " " + testComrade.getComrades().get(i).getCamraderie());
                }
                System.out.println();
            }

            int[][] adjMatrix = builtAdjMatrix();
            printMatrix(adjMatrix);
            comradeDijkstra(adjMatrix, 1, 0);
            System.out.println();
            System.out.println();
            System.out.println("Ivan's Friends");
            for (int i = 0; i < proletariat.get(0).getComrades().size(); i++) {
                System.out.println(proletariat.get(0).getComrades().get(i).getComrade().getName());
            }

            // purgeDisloyalComrade(proletariat.get(2));
            // System.out.println();

            // System.out.println("Ivan's Friends, sasha never existed");
            // for (int i = 0; i < proletariat.get(0).getComrades().size(); i++) {
            //     System.out.println(proletariat.get(0).getComrades().get(i).getComrade().getName());
            // }


            System.out.println();
            System.out.println();
            System.out.println();
            for (int j = 0; j < proletariat.size(); j++) {
                Comrade testComrade = proletariat.get(j);
                System.out.println("Test Comrade " + testComrade.getName());
                for (int i = 0; i < testComrade.getComrades().size(); i++) {
                    System.out.println(testComrade.getName() +"'s Comrade: " + testComrade.getComrades().get(i).getComrade().getName() + " " + testComrade.getComrades().get(i).getCamraderie());
                }
                System.out.println();
            }

            //greatPurge(5);
            System.out.println();
            for (int j = 0; j < proletariat.size(); j++) {
                Comrade testComrade = proletariat.get(j);
                System.out.println("Test Comrade " + testComrade.getName());
                for (int i = 0; i < testComrade.getComrades().size(); i++) {
                    System.out.println(testComrade.getName() +"'s Comrade: " + testComrade.getComrades().get(i).getComrade().getName() + " " + testComrade.getComrades().get(i).getCamraderie());
                }
                System.out.println();
            }


            // System.out.println();
            // Comrade bestComrade = lookForPromotion();
            // System.out.println(bestComrade.getName());

            
        }
        catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", "Proletariat");
            e.printStackTrace();
        }
    }
    
    public boolean doesComradeExist(String comrade) {
        for (int i = 0; i< proletariat.size(); i++) {
            if (proletariat.get(i).getName().equals(comrade))
                return true;
        }
        return false;
    }

    // Menu actions
    public void runDijkstra(String s, String d) {
            int[][] adjMatrix = builtAdjMatrix();
            printMatrix(adjMatrix);
            int source = -1;
            int dest = -1;

            for (int i = 0; i< proletariat.size(); i++) {
                if (proletariat.get(i).getName().equals(s))
                    source = i;
            }

            for (int i = 0; i< proletariat.size(); i++) {
                if (proletariat.get(i).getName().equals(d))
                    dest = i;
            }

            if (source == -1 || dest == -1)
                System.out.println("Nonexistant comrades");

            comradeDijkstra(adjMatrix, source, dest);
    }

    public void printMap() {
        System.out.println();
        for (int j = 0; j < proletariat.size(); j++) {
            Comrade testComrade = proletariat.get(j);

            System.out.println("Test Comrade " + testComrade.getName());
            for (int i = 0; i < testComrade.getComrades().size(); i++) {
                System.out.println(testComrade.getName() +"'s Comrade: " + testComrade.getComrades().get(i).getComrade().getName() + " " + testComrade.getComrades().get(i).getCamraderie());
            }
            System.out.println();
        }
    }

    public void greatPurge(int minLoyalty) {
        for (int i = 0; i < proletariat.size(); i++) {
            if (proletariat.get(i).getPartyLoyalty() < minLoyalty) {
                Comrade traitor = proletariat.get(i);
                eraseTraitorFromFriends(traitor);
                proletariat.remove(traitor);
            }
        }
    }
    
    public Comrade lookForPromotion() {
        int[] scores = new int[proletariat.size()];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = 0;
        }

        for (int i = 0; i < proletariat.size(); i++) {
            Comrade candidate = proletariat.get(i);
 
            for (int j = 0; j < candidate.getComrades().size(); j++) {
                Comrade friend = candidate.getComrades().get(j).getComrade();
                int camradToFriend = candidate.getComrades().get(j).getCamraderie();

                int index = proletariat.indexOf(friend);
                scores[index] += camradToFriend;
            }
        }
        
        int maxScore = 0;
        int index = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] >= maxScore)
            {
                maxScore = scores[i];
                index = i;
            }   
        }
        for (int score : scores) {
            System.out.print(score + ", ");
        }
        return proletariat.get(index);
    }

    public static void readAndPopulateProles(File file) throws Exception {
        // go through the proletariat file and ignore friends, just creating 
        // the comrades with their associated stats, and leaving their connections
        // as blank
        Scanner filein = new Scanner(file);
        proletariat = new ArrayList<Comrade>();
        while (filein.hasNextLine()) {
            String currLine = filein.nextLine();
            Scanner lineScan = new Scanner(currLine);
            lineScan.useDelimiter(", ");
            String theName = "";
            String theAge; // will convert to int
            String theOccupation; 
            String theCity; 
            String theloyalty; // will convert to int           
            if (currLine.charAt(0) == '{') {
                while (currLine.charAt(0) != '}') {
                    currLine = filein.nextLine();
                }
            }
            else {
                System.out.println(currLine);
                theName = lineScan.next();
                theAge = lineScan.next();
                theOccupation = lineScan.next();
                theCity = lineScan.next();
                theloyalty = lineScan.next();
                Comrade comrade = new Comrade(theName, Integer.parseInt(theAge), theOccupation, theCity, Integer.parseInt(theloyalty));
                proletariat.add(comrade);
            }
            lineScan.close();
        }
        filein.close();
    }

    public static void readAndAddConnections(File file) throws Exception {
        // go through proletariat file again,
        // this time care about friends
        // for each friend, look up the associated
        // name of that friend in the proletariat arraylist
        // add THAT comrade object in the proletariat arraylist
        // to the Connection arraylist for the person we are looking at
        // along with the camaraderie value
        Scanner filein = new Scanner(file);
        String currLine = filein.nextLine();
        while (filein.hasNextLine()) {
            Scanner lineScan = new Scanner(currLine);
            lineScan.useDelimiter(", ");
            Comrade theComrade = findComrade(lineScan.next());
            currLine = filein.nextLine();
            lineScan.close();
            if (currLine.charAt(0) == '{')
                currLine = filein.nextLine();
                while (currLine.charAt(0) != '}') {
                    lineScan = new Scanner(currLine);
                    lineScan.useDelimiter(", ");
                    String name = lineScan.next();
                    String camaraderie = lineScan.next();                
                    Comrade newComrade = findComrade(name);
                    Connection con = new Connection(newComrade, Integer.parseInt(camaraderie));         
                    theComrade.getComrades().add(con);
                    currLine = filein.nextLine();
                }
                if (filein.hasNextLine())
                    currLine = filein.nextLine();
        }
        filein.close();
    }

    public static Comrade findComrade(String name) {
        Comrade yourmom = new Comrade();
        for (int i = 0; i < proletariat.size(); i++) {
            if (name.equals(proletariat.get(i).getName())) 
                return proletariat.get(i);
        }
        return yourmom; // this shouldnt happen
    }

    public static int[][] builtAdjMatrix() {
        int size = proletariat.size();
        int[][] adjMatrix = new int[size][size];
        for (int i = 0; i < size; i++) { // for each comrade
            for (int j = 0; j < size; j++) { // for each comrade
                if (i == j)
                    adjMatrix[i][j] = 0;
                else {
                    Comrade currComrade = proletariat.get(i);
                    Comrade comradeBeingChecked = proletariat.get(j);

                    // if the comrade being checked (the jth) is
                    // being followed by the comrade (the ith)
                    if (currComrade.getJustComrades().contains(comradeBeingChecked)) {
                        adjMatrix[i][j] = currComrade.getComrades().get(currComrade.getJustComrades().indexOf(comradeBeingChecked)).getCamraderie(); // im sorry but it works
                    } else {
                        adjMatrix[i][j] = 0;
                    }                                      
                }
            }
        }
        return adjMatrix;
    }

    // unmade
    final static int NO_PARENT = -1;
    private static void comradeDijkstra(int[][] graph, int source, int destination) {
        int nVertices = graph[0].length; 
        int[] shortestDistances = new int[nVertices]; 
        boolean[] added = new boolean[nVertices]; 
  
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) { 
            shortestDistances[vertexIndex] = Integer.MAX_VALUE; 
            added[vertexIndex] = false; 
        } 
          
        shortestDistances[source] = 0; 
  
        int[] parents = new int[nVertices]; 

        parents[source] = NO_PARENT; 
  
        for (int i = 1; i < nVertices; i++) { 
            int nearestVertex = -1; 
            int shortestDistance = Integer.MAX_VALUE; 
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) { 
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) { 
                    nearestVertex = vertexIndex; 
                    shortestDistance = shortestDistances[vertexIndex]; 
                } 
            } 
  
            added[nearestVertex] = true; 

            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) { 
                int edgeDistance = graph[nearestVertex][vertexIndex]; 
                  
                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) { 
                    parents[vertexIndex] = nearestVertex; 
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance; 
                } 
            } 
        } 
        // unmade ^

        System.out.print("Source \t -> \t Destination \t Weight\t Chain"); 
         
        if (source < destination) {
            for (int i = source; i <= destination;  i++) { 
                if (i != source && i == destination) { 
                    System.out.print("\n" + proletariat.get(source).getName() + " -> "); 
                    System.out.print(proletariat.get(i).getName() + " \t "); 
                    System.out.print(shortestDistances[i] + "\t"); 
                    if (i == NO_PARENT) { 
                        break; 
                    } 
                    printPath(parents[i], parents); 
                    System.out.print(proletariat.get(i).getName() + " "); 
                } 
            } 
        }
        else {
            for (int i = source; i >= destination;  i--) { 
                if (i != source && i == destination) { 
                    System.out.print("\n" + proletariat.get(source).getName() + " -> "); 
                    System.out.print(proletariat.get(i).getName() + " \t "); 
                    System.out.print(shortestDistances[i] + "\t"); 
                    if (i == NO_PARENT) { 
                        break; 
                    } 
                    printPath(parents[i], parents); 
                    System.out.print(proletariat.get(i).getName() + " "); 
                } 
            } 
        }
    }
    
    // unmade
    // it'll be a pain to change this, since it's recursive
    private static void printPath(int currentVertex, int[] parents) { 
        if (currentVertex == NO_PARENT) { 
            return; 
        } 
        printPath(parents[currentVertex], parents); 
        System.out.print(proletariat.get(currentVertex).getName() + " --> ");

    } 

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void eraseTraitorFromFriends(Comrade traitor) {
        for (int i = 0; i < proletariat.size(); i++) { // for each prole
            Comrade currentComrade = proletariat.get(i);
            for (int j = 0; j < currentComrade.getComrades().size(); j++) {
                if (currentComrade.getComrades().get(j).getComrade() == traitor) {
                    proletariat.get(i).getComrades().remove(currentComrade.getComrades().get(j));
                }
            }
        }
    }

    public int checkStatus(String first, String second) { 
        Comrade firstC, secondC;
        firstC = new Comrade();
        secondC = new Comrade();

        for (Comrade prole : proletariat) {
            if (prole.getName().equals(first)) {
                firstC = prole;
            }
            if (prole.getName().equals(second)) {
                secondC = prole;
            }    
        }

        for (int i = 0; i < firstC.getComrades().size(); i++) {
            if (firstC.getJustComrades().get(i) == secondC) {
                return firstC.getComrades().get(i).getCamraderie();
            }
        }
        return -1;
    }
}
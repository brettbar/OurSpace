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

    public static void main(String[] args) {
        
        try {
            File file = new File("Proletariat");
            readAndPopulateProles(file);
            readAndAddConnections(file);
            System.out.println();
            // for (int i = 0; i < proletariat.size(); i++) {
            //     System.out.println(proletariat.get(i).getName());
            // }

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
            
        }
        catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", "Proletariat");
            e.printStackTrace();
        }

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


        
}
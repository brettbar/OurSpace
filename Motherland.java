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
            comradeDijkstra(adjMatrix, 0);
            
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
                        adjMatrix[i][j] = currComrade.getComrades().get(currComrade.getJustComrades().indexOf(comradeBeingChecked)).getCamraderie();
                    } else {
                        adjMatrix[i][j] = 0;
                    }                                      
                }
            }
        }
        return adjMatrix;
    }

    // shamelessly stolen from here:
    // https://stackoverflow.com/questions/5061912/printing-out-a-2-d-array-in-matrix-format/5061920
    // but its only used for printing in the testing phase, and this isn't really be graded
    // so it seems fair game to use
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void comradeDijkstra(int[][] graph, int source) {
        int distArr[] = new int[5];  
   
        Boolean sptSet[] = new Boolean[5]; 
  
        for (int i = 0; i < 5; i++) { 
            distArr[i] = Integer.MAX_VALUE; 
            sptSet[i] = false; 
        } 
  
        distArr[source] = 0; 
  
        for (int i = 0; i < 4; i++) { 

            int u = minDistance(distArr, sptSet); 
  
            sptSet[u] = true; 
  
            //v for vertex
            for (int v = 0; v < 5; v++) 
  
                if (!sptSet[v] && graph[u][v]!=0 && 
                        distArr[u] != Integer.MAX_VALUE && distArr[u]+graph[u][v] < distArr[v]) {
                    distArr[v] = distArr[u] + graph[u][v]; 
                }
        } 
  
        printSolution(distArr, 5); 
    }
    
    public static void printSolution(int distArr[], int n) { 
        System.out.println("Ivan Ivanov as source:");
        String[] names = {"Ivan Ivanov", "Andre Pochinkov", "Vitas Kushikov", "Ana Petroliokov", "Misha Krivoruchko"};
        
        System.out.println("Comrade   Distance"); 
        for (int i = 0; i < 5; i++) 
            System.out.println(names[i] +" ---> "+ distArr[i]); 
    } 
    
    public static int minDistance(int distArr[], Boolean set[]) { 
        // Initialize min value 
        int min = Integer.MAX_VALUE, minIndex=-1; 
  
        for (int i = 0; i < 5; i++) 
            if (set[i] == false && distArr[i] <= min) 
{ 
                min = distArr[i]; 
                minIndex = i; 
            } 
  
        return minIndex; 
    }

        
}
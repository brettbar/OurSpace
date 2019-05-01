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
            
        }
        catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", "Proletariat");
            e.printStackTrace();
        }

    }

    public static void readAndPopulateProles(File file) throws Exception {
        Scanner filein = new Scanner(file);
        
        // ArrayList<Comrade> proletariat = new ArrayList<Comrade>();
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
        // return proletariat;
    }

    public static void readAndAddConnections(File file) throws Exception {
        Scanner filein = new Scanner(file);

        // go through proletariat file
        // this time care about friends
        // for each friend, look up the associated
        // name of that friend in the proletariat arraylist
        // add THAT comrade object in the proletariat arraylist
        // to the Connection arraylist for the person we are looking at
        // along with the camaraderie value
        String currLine = filein.nextLine();
        while (filein.hasNextLine()) {
            Scanner lineScan = new Scanner(currLine);
            lineScan.useDelimiter(", ");

            Comrade theComrade = findComrade(lineScan.next());
            currLine = filein.nextLine();
            lineScan.close();
            // System.out.println(theComrade.getAge());
            
            if (currLine.charAt(0) == '{')
                currLine = filein.nextLine();
                while (currLine.charAt(0) != '}') {
                    lineScan = new Scanner(currLine);
                    lineScan.useDelimiter(", ");
                    // System.out.println(currLine);
      
                    String name = lineScan.next();
                    // System.out.println(name);
                    String camaraderie = lineScan.next();
                    // System.out.println(camaraderie);
                    
                    Comrade newComrade = findComrade(name);

                    Connection con = new Connection(newComrade, Integer.parseInt(camaraderie));
                    
                    // System.out.println(con.getComrade().getName() + " " + con.getComrade().getCity());
                    
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
}
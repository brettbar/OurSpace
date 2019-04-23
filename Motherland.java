import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.lang.*;


public class Motherland {
    private ArrayList<Comrade> proletariat;

    public static void main(String[] args)
    {
        proletariat = new ArrayList<Comrade>();
    }

    public static void readAndPopulate(String filename) throws Exception
    {
        // try 
        // {
        //     BufferedReader reader = new BufferedReader(new FileReader(filename));
            
        // }
        // catch (Exception e) 
        // {
        //     System.err.format("Exception occurred trying to read '%s'.", filename);
        //     e.printStackTrace();
        // }
        File file = new File(filename);
        Scanner filein = new Scanner(file);

        String name = "";
        int age = 0;
        String occupation = "";
        String city = "";
        int partyLoyalty = 0;
        ArrayList<Connection> comrades = new ArrayList<Connection>();

        while(filein.hasNext())
        {
            String line = filein.nextLine();

            if (line.charAt(0) == '{')
            {
                // start friend list
            }
            else if (line.charAt(0) == '}')
            {   
                // end friend list
            }
        }
    }
}
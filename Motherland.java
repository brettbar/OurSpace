import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Motherland extends JFrame {
    private ArrayList<Comrade> proletariat;

    private JButton checkUser;
    private JButton checkStatus;
    private JButton dijsktra;
    private JButton login;

    public Motherland() {
        setTitle("OurSpace");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        checkUser = new JButton();
        checkStatus = new JButton();
        dijsktra = new JButton();
        login = new JButton();

        checkUser.addActionListener(new ButtonListener());
        checkStatus.addActionListener(new ButtonListener());
        dijsktra.addActionListener(new ButtonListener());
        login.addActionListener(new ButtonListener());

        JPanel panel = new JPanel();
        panel.add(checkUser);
        panel.add(checkStatus);
        panel.add(dijsktra);
        panel.add(login);
        add(panel);

        JPanel imagePanel = new JPanel();
        JLabel label = new JLabel();
        
        imagePanel.add(label);
        ImageIcon yourBoi = new ImageIcon("YourBoi.jpg");
       // imagePanel.setIcon(yourBoi);
        setVisible(true);




    }

    private class ButtonListener implements ActionListener { 
        public void actionPerformed (ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("checkUser"));
                //check if user exists

            if (command.equals("checkStatus"));
                //check camraderie of two comrades

            if (command.equals("dijkstra"));
                //execute dijkstra algorithm 
            
            if (command.equals("login"));
                //login as commissar

        }
    }

    public static void main(String[] args)
    {
        new Motherland();
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
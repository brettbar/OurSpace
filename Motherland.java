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

    private JPanel buttons;
    private JPanel imagePanel;
    private JLabel label;

    public Motherland() {
        setTitle("OurSpace, the proletariate's social network");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        doButtons();
        doImage();
        //pack();
        
        add(buttons, BorderLayout.EAST);    
        add(imagePanel, BorderLayout.WEST);

        setVisible(true);

    }

    private void doButtons() {
        checkUser = new JButton("Check for Comrade");
        checkStatus = new JButton("Status");
        dijsktra = new JButton("Comrade Dijkstra");
        login = new JButton("Commissar");

        checkUser.addActionListener(new ButtonListener());
        checkStatus.addActionListener(new ButtonListener());
        dijsktra.addActionListener(new ButtonListener());
        login.addActionListener(new ButtonListener());

        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.add(checkUser);
        buttons.add(checkStatus);
        buttons.add(dijsktra);
        buttons.add(login);
    }

    private void doImage() {
        imagePanel = new JPanel();
        label = new JLabel();
        
        ImageIcon yourBoi = new ImageIcon("YourBoi.jpg");
        label.setIcon(yourBoi);
        imagePanel.add(label);
    }

    private void lazer() {
        ImageIcon YourBoiL = new ImageIcon("YourBoiLazer.jpeg");
        label.setIcon(YourBoiL);
        //add(label);
        System.out.println("Photo");
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
            
            if (e.getSource() == login) {
                lazer();
            }
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
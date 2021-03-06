// Brett Barinaga and Andrew Flagstead
// CPSC 450 
// OurSpace
// Motherland.java
// NOTE: USE THIS TO RUN THE PROGRAM, NOT ANY OTHER CLASS

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.lang.*;
import javax.swing.*;

//import jdk.nashorn.internal.scripts.JO;

import java.awt.*;
import java.awt.event.*;


// import javafx.scene.media.Media;
// import javafx.scene.media.MediaPlayer;

// USE THIS FOR ACTUALLY RUNNING THE PROGRAM

public class Kremlin extends JFrame {
    private final String PASSWORD;

    private JButton checkUser;
    private JButton checkStatus;
    private JButton dijkstra;
    private JButton purge;
    private JButton promotion;

    private JPanel logoPanel;
    private JPanel buttons;
    private JPanel stalinPanel;
    private JPanel textPanel;
    private JLabel label;
    private JLabel logoLabel;

    private JTextField out;
    
    private Motherland rodinu = new Motherland();


    public Kremlin() {
        rodinu = new Motherland();
        PASSWORD = "potatoturnip";
        setTitle("OurSpace, the proletariat's social network");
        setSize(1150, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE); // this will be where dialog goes
        setResizable(false);

        setLayout(new BorderLayout());

        doButtons();
        stalinImage();
        logoImage();
        doTextField();
        
        add(buttons, BorderLayout.EAST);    
        add(stalinPanel, BorderLayout.WEST);
        add(logoPanel, BorderLayout.SOUTH);
        add(textPanel, BorderLayout.CENTER);

        setVisible(true);

    }

    private void doTextField() {
        textPanel = new JPanel();
        JLabel newLabel = new JLabel();
        
        ImageIcon creed = new ImageIcon("blyat.png");
        newLabel.setIcon(creed);
        textPanel.add(newLabel);
    }

    private void doButtons() {
        checkUser = new JButton("Check for Comrade");
        checkStatus = new JButton("           Status             ");
        dijkstra = new JButton(" Comrade Dijkstra  ");
        purge = new JButton("      Stalin Purge       ");
        promotion = new JButton("   Find Promotion  ");

        checkUser.addActionListener(new ButtonListener());
        checkStatus.addActionListener(new ButtonListener());
        dijkstra.addActionListener(new ButtonListener());
        purge.addActionListener(new ButtonListener());
        promotion.addActionListener(new ButtonListener());

        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.add(checkUser);
        buttons.add(checkStatus);
        buttons.add(dijkstra);
        buttons.add(purge);
        buttons.add(promotion);
        buttons.setBackground(Color.WHITE);
    }

    private void stalinImage() {
        stalinPanel = new JPanel();
        label = new JLabel();
        
        ImageIcon yourBoi = new ImageIcon("YourBoi.jpg");
        label.setIcon(yourBoi);
        stalinPanel.add(label);
        stalinPanel.setBackground(Color.WHITE);

    }

    private void logoImage() {
        logoPanel = new JPanel();
        logoLabel = new JLabel();

        ImageIcon logo = new ImageIcon("ourspace.png");
        logoLabel.setIcon(logo);
        logoPanel.add(logoLabel);
        logoPanel.setBackground(Color.WHITE);
    }

    private void lazer() {
        ImageIcon YourBoiL = new ImageIcon("YourBoiLazer.jpeg");
        label.setIcon(YourBoiL);
        System.out.println("Photo Change");
    }

    private synchronized void urBoi()  {
        try{
            wait(500000);
        }
        catch (Exception e) {
            System.out.println("You're dumb");
        }
        ImageIcon YourBoi = new ImageIcon("YourBoi.jpg");
        label.setIcon(YourBoi);
        System.out.println("Photo Change");
    }

    private class ButtonListener implements ActionListener { 
        public void actionPerformed (ActionEvent e) {

            if (e.getSource() == checkUser) {
                String comrade = JOptionPane.showInputDialog("Enter the comrade");
                if (rodinu.doesComradeExist(comrade))
                    JOptionPane.showMessageDialog(null, comrade + " exists");
                else
                    JOptionPane.showMessageDialog(null, comrade + " does not exist");
            }
                //check if user exists

            if (e.getSource() == checkStatus){ 
                String firstComrade = JOptionPane.showInputDialog("Enter the first Comrade");
                String secondComrade = JOptionPane.showInputDialog("Enter the second Comrade");

                int stat = rodinu.checkStatus(firstComrade, secondComrade);

                if (stat == -1)
                    JOptionPane.showMessageDialog(null, "There is no camaraderie between " + firstComrade + " and " + secondComrade);
                else 
                    JOptionPane.showMessageDialog(null, "The camaraderie between " + firstComrade + " and " + secondComrade + " is " + stat);
            }   
                //check camraderie of two comrades


            if (e.getSource() == dijkstra) { 
                String source = JOptionPane.showInputDialog("Enter the starting comrade");
                String dest = JOptionPane.showInputDialog("Enter the ending comrade");

                rodinu.printMap();
                rodinu.runDijkstra(source,dest);

                JOptionPane.showMessageDialog(null, "Look in the console for the chain");
            }
                
            if (e.getSource() == purge) {
                String password;
                password = JOptionPane.showInputDialog("Enter the password, comrade commissar");
                if (password.equals(PASSWORD)) {
                    String input = JOptionPane.showInputDialog("Enter a loyalty to purge with");
                    int loy = Integer.parseInt(input);
                    lazer();
                    rodinu.greatPurge(loy);  
                }
                else 
                    JOptionPane.showMessageDialog(null, "Nice try, capitalist swine");
            }

            if (e.getSource() == promotion) {
                Comrade bestcomrade = rodinu.lookForPromotion();
                JOptionPane.showMessageDialog(null, bestcomrade.getName() + " is eligible for a promotion in the party");
            }


        }
    }

    public static void main(String[] args)
    {
        new Kremlin();
    }


}
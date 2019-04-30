import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Motherland extends JFrame {
    private ArrayList<Comrade> proletariat;
    private final String PASSWORD;

    private JButton checkUser;
    private JButton checkStatus;
    private JButton dijkstra;
    private JButton purge;

    private JPanel buttons;
    private JPanel imagePanel;
    private JLabel label;

    public Motherland() {
        PASSWORD = "potatoturnip";
        setTitle("OurSpace, the proletariat's social network");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.RED);

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
        checkStatus = new JButton("           Status             ");
        dijkstra = new JButton(" Comrade Dijkstra  ");
        purge = new JButton("      Stalin Purge       ");

        checkUser.addActionListener(new ButtonListener());
        checkStatus.addActionListener(new ButtonListener());
        dijkstra.addActionListener(new ButtonListener());
        purge.addActionListener(new ButtonListener());

        buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.add(checkUser);
        buttons.add(checkStatus);
        buttons.add(dijkstra);
        buttons.add(purge);
        buttons.setBackground(Color.RED);
    }

    private void doImage() {
        imagePanel = new JPanel();
        label = new JLabel();
        
        ImageIcon yourBoi = new ImageIcon("YourBoi.jpg");
        label.setIcon(yourBoi);
        imagePanel.add(label);
        imagePanel.setBackground(Color.RED);

    }

    private void lazer() {
        ImageIcon YourBoiL = new ImageIcon("YourBoiLazer.jpeg");
        label.setIcon(YourBoiL);
        //add(label);
        System.out.println("Photo Change");
    }
    private class ButtonListener implements ActionListener { 
        public void actionPerformed (ActionEvent e) {
            String command = e.getActionCommand();

            if (e.getSource() == checkUser);
                //check if user exists

            if (e.getSource() == checkStatus);
                //check camraderie of two comrades

            if (e.getSource() == dijkstra);
                //execute dijkstra algorithm 
            
            if (e.getSource() == purge) {
                String password;
                password = JOptionPane.showInputDialog("Enter the password, comrade commissar");
                if (password.equals(PASSWORD)) {
                    lazer();
                }
                else 
                    JOptionPane.showMessageDialog(null, "Nice try, capitalist swine");
            }


        }
    }

    public static void main(String[] args)
    {
        new Motherland();
    }

    public static void readAndPopulate(String filename) throws Exception {

    }
    
    /*public static void playSound() throws Exception {
        String bip = "C:\\Users\\Andrew\\Documents\\CPSC450\\OurSpace\\homeRunSound.mp3";
        Media hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }*/
}
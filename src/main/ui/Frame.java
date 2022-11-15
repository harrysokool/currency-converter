package ui;

import javax.swing.*;
import static java.lang.Thread.sleep;

//frame for the currency converter
public class Frame extends JFrame {

    // EFFECTS: construct the frame set up the frame, a welcome image when open application
    //          then set the panel.
    public Frame() {

        ImageIcon image = new ImageIcon("data/welcome.png");
        JLabel label = new JLabel();
        label.setIcon(image);
        label.setBounds(0,0,300,250);

        // set up frame.
        this.setTitle("Currency Converter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(300,300);
        this.setLayout(null);
        this.setVisible(true);
        this.add(label);

        try {
            sleep(3000);
        } catch (Exception e) {
            System.out.println();
        }

        //Create and set up the content pane.
        JComponent newContentPane = new Panel();
        newContentPane.setOpaque(true);
        this.setContentPane(newContentPane);
    }

    // EFFECTS: start currency converter
    public static void main(String[] args) {
        new Frame();
    }

}

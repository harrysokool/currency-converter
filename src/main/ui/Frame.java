package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import static java.lang.Thread.sleep;

//frame for the currency converter
public class Frame extends JFrame {


    // MODIFIES: this
    // EFFECTS: construct the frame set up the frame
    public Frame() {
        // set up frame.
        this.setUpFrame();

        try {
            sleep(3000);
        } catch (Exception e) {
            System.out.println();
        }

        //Create and set up the content pane.
        JComponent newContentPane = new Panel();
        newContentPane.setOpaque(true);
        this.setContentPane(newContentPane);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                for (Event next : EventLog.getInstance()) {
                    System.out.println(next.toString() + "\n");
                }
                System.exit(0);
            }
        });


    }

    // MODIFIES: this, frame
    // EFFECTS: Sets up the frame, welcome image when open application.
    private void setUpFrame() {
        ImageIcon image = new ImageIcon("data/welcome.png");
        JLabel label = new JLabel();
        label.setIcon(image);
        label.setBounds(25,0,400,250);
        this.setTitle("Currency Converter");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setSize(370,270);
        this.setLayout(null);
        this.setVisible(true);
        this.add(label);
    }

    // EFFECTS: start currency converter
    public static void main(String[] args) {
        new Frame();
    }

}

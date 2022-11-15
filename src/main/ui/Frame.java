package ui;

import javax.swing.*;
import static java.lang.Thread.sleep;

public class Frame extends JFrame {

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
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

    // start currency converter
    public static void main(String[] args) {
        new Frame();
    }

}

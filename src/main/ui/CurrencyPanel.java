package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CurrencyPanel extends JPanel {
    private static final String LBL_TXT = "Currencies Available: ";
    private MainFrame cc;
    private static final int LBL_WIDTH = 150;
    private static final int LBL_HEIGHT = 365;

    public CurrencyPanel() {
        //border
        Border border = BorderFactory.createStrokeBorder(new BasicStroke(3.0f));

        // label
        JLabel label = new JLabel();
        label.setText(LBL_TXT);
        label.setBounds(0,0,150,30);
        label.setBorder(border);

        // panel setting
        this.setBackground(Color.lightGray);
        this.setBounds(0,0,LBL_WIDTH,LBL_HEIGHT);
        this.setBorder(border);
        this.add(label);

    }

}

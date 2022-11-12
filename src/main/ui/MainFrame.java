package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JPanel implements ListSelectionListener {
//    private static final int BUTTON_WIDTH = 150;
//    private static final int BUTTON_HEIGHT = 50;
//    private CurrencyPanel cp;
//    private JButton convertCurrencyButton;
//    private JButton saveButton;
//    private JButton loadButton;

    // list of currencies
    private JList list;
    private DefaultListModel<String> currencyList;
    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private JButton addCurrencyButton;
    private JButton removeCurrencyButton;
    private JTextField currencyName;

    // Constructs main window
    // Effects: sets up window for Currency Converter
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public MainFrame() {
        currencyList = new DefaultListModel<>();
        currencyList.addElement("HKD");
        currencyList.addElement("CAD");

        list = new JList(currencyList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(list);

        addCurrencyButton = new JButton(addString);
        HireListener hireListener = new HireListener(addCurrencyButton);
        addCurrencyButton.setActionCommand(addString);
        addCurrencyButton.addActionListener(hireListener);
        addCurrencyButton.setEnabled(false);

        removeCurrencyButton = new JButton(removeString);
        removeCurrencyButton.setActionCommand(removeString);
        removeCurrencyButton.addActionListener(new FireListener());

        currencyName = new JTextField(10);
        currencyName.addActionListener(hireListener);
        currencyName.getDocument().addDocumentListener(hireListener);
        String name = currencyList.getElementAt(list.getSelectedIndex()).toString();

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(removeCurrencyButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(currencyName);
        buttonPane.add(addCurrencyButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);

        // button to add currency
//        addCurrencyButton = addButton(160, 10, "Add Currency");
//
//        // button to remove currency
//        removeCurrencyButton = addButton(160, 60, "Remove Currency");
//
//        // button to convert currency
//        convertCurrencyButton = addButton(240, 110, "Convert Currency");
//
//        // button to save currency list
//        saveButton = addButton(320, 10,"Save");
//
//        // button to load currency list
//        loadButton = addButton(320, 60,"Load");
//
//        // panel to show the currencies in the currencyList
//        cp = new CurrencyPanel();
//
//        // window settings
//        this.setTitle("Currency Converter");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setResizable(false);
//        this.setSize(500,400);
//        this.setVisible(true);
//
//        // add components
//        this.add(cp);
//        this.add(addCurrencyButton);
//        this.add(removeCurrencyButton);
//        this.add(convertCurrencyButton);
//        this.add(saveButton);
//        this.add(loadButton);

    }

//    public JButton addButton(int x, int y, String text) {
//        JButton button = new JButton();
//        button.setBounds(x,y,BUTTON_WIDTH,BUTTON_HEIGHT);
//        button.setText(text);
//        button.setHorizontalTextPosition(JButton.CENTER);
//        button.setVerticalTextPosition(JButton.CENTER);
//        button.addActionListener(this);
//        button.setFocusable(false);
//        return button;
//    }

    class FireListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever is selected.
            int index = list.getSelectedIndex();
            currencyList.remove(index);

            int size = currencyList.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                removeCurrencyButton.setEnabled(false);

            } else { //Select an index.
                if (index == currencyList.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    //This listener is shared by the text field and the hire button.
    class HireListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public HireListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = currencyName.getText().toUpperCase();

            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                currencyName.requestFocusInWindow();
                currencyName.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            currencyList.insertElementAt(currencyName.getText().toUpperCase(), index);
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(employeeName.getText());

            //Reset the text field.
            currencyName.requestFocusInWindow();
            currencyName.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        //This method tests for string equality. You could certainly
        //get more sophisticated about the algorithm.  For example,
        //you might want to ignore white space and capitalization.
        protected boolean alreadyInList(String name) {
            return currencyList.contains(name);
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeCurrencyButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeCurrencyButton.setEnabled(true);
            }
        }
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new MainFrame();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // start currency converter
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(MainFrame::createAndShowGUI);
    }
}

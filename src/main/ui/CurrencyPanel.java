package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.IOException;
import java.util.Scanner;

public class CurrencyPanel extends JPanel implements ListSelectionListener {
    // list of currencies
    private JList list;
    private DefaultListModel<String> currencyList;
    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private static final String saveString = "Save";
    private static final String loadString = "Load";
    private JButton addCurrencyButton;
    private JButton removeCurrencyButton;
    private JTextField currencyName;

    private JButton saveCurrencyButton;
    private JButton loadCurrencyButton;

    //Json
    static final String JSON_STORE = "./data/CurrencyList.json";
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Constructs main window
    // Effects: sets up window for Currency Converter
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public CurrencyPanel() {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        currencyList = new DefaultListModel<>();
        currencyList.addElement("HKD");
        currencyList.addElement("CAD");

        //list
        list = new JList(currencyList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(list);

        //add button
        addCurrencyButton = new JButton(addString);
        AddListener addListener = new AddListener(addCurrencyButton);
        addCurrencyButton.setActionCommand(addString);
        addCurrencyButton.addActionListener(addListener);
        addCurrencyButton.setEnabled(false);

        //remove button
        removeCurrencyButton = new JButton(removeString);
        removeCurrencyButton.setActionCommand(removeString);
        removeCurrencyButton.addActionListener(new RemoveListener());

        //save button
        saveCurrencyButton = new JButton(saveString);
        saveCurrencyButton.setActionCommand(saveString);
        saveCurrencyButton.addActionListener(new SaveListener());

        //load button
        loadCurrencyButton = new JButton(loadString);
        loadCurrencyButton.setActionCommand(loadString);
        loadCurrencyButton.addActionListener(new LoadListener());

        //text field
        currencyName = new JTextField(3);
        currencyName.addActionListener(addListener);
        currencyName.getDocument().addDocumentListener(addListener);
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
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(saveCurrencyButton);
        buttonPane.add(loadCurrencyButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == saveCurrencyButton) {
//                try {
//                    jsonWriter.open();
//                    jsonWriter.write(currencyList);
//                    jsonWriter.close();
//                    System.out.println("Saved currencies to " + JSON_STORE);
//                } catch (FileNotFoundException exception) {
//                    System.out.println("Unable to write to file: " + JSON_STORE);
//                }
//            }
        }
    }

    class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            try {
//                currencyList = jsonReader.read();
//                System.out.println("Loaded currencies from " + JSON_STORE);
//            } catch (IOException exception) {
//                System.out.println("Unable to read from file: " + JSON_STORE);
//            }
        }
    }

    class RemoveListener implements ActionListener {
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
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
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
}

package ui;

import model.Currency;
import model.CurrencyList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// panel for currency converter
public class Panel extends JPanel {
    JPanel panel;

    // json
    static final String JSON_STORE = "./data/CurrencyListGUI.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    // currency list
    CurrencyList cl;

    // table
    JTable table;
    DefaultTableModel model;
    Object[] column;
    Object[] row;

    // names for the buttons
    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton convertButton;
    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private static final String saveString = "Save";
    private static final String loadString = "Load";

    // text field
    private JTextField addCurrencyName;
    private JTextField currencyRate;
    private JTextField removeCurrencyName;
    private JTextField currency1;
    private JTextField currency2;
    private JTextField amount;

    // label
    private JLabel addName;
    private JLabel addRate;
    private JLabel removeName;
    private JLabel currency1name;
    private JLabel currency2name;
    private JLabel amountToConvert;




    //     // EFFECTS: construct JPanel and setup for the panel, and adding the components
    public Panel() {

        cl = new CurrencyList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        this.makeLabel();
        this.makeTable(); // table
        this.makeTextField(); // text fields
        this.makeAddButtons(); // add button
        this.makeRemoveButton(); // remove button
        this.makeSaveButton(); // save button
        this.makeLoadButton(); // load button
        this.makeConvertButton(); // convert button

        // panel setting
        this.setBounds(0,0,300,300);
        this.setLayout(null);

        // adding component
        addComponent();

    }

    private void addComponent() {
        this.add(addCurrencyName);
        this.add(currencyRate);
        this.add(removeCurrencyName);
        this.add(addButton);
        this.add(removeButton);
        this.add(saveButton);
        this.add(loadButton);
        this.add(table);
        this.add(addName);
        this.add(addRate);
        this.add(removeName);
        this.add(currency1name);
        this.add(currency2name);
        this.add(amountToConvert);
        this.add(currency1);
        this.add(currency2);
        this.add(amount);
        this.add(convertButton);
    }

    private void makeLabel() {
        addName = new JLabel("Currency:");
        addName.setBounds(160, 20, 70, 20);

        addRate = new JLabel("Rate to 1 USD:");
        addRate.setBounds(160, 65, 100, 20);

        removeName = new JLabel("Currency:");
        removeName.setBounds(160, 135, 70, 20);

        currency1name = new JLabel("Currency 1:");
        currency1name.setBounds(275, 20, 80, 20);

        currency2name = new JLabel("Currency 2:");
        currency2name.setBounds(275, 65, 80, 20);

        amountToConvert = new JLabel("Amount:");
        amountToConvert.setBounds(275, 110, 80, 20);
    }

    // EFFECTS: construct table and setup for the table
    private void makeTable() {
        // table
        column = new Object[]{"Currency", "Rate"};
        row = new Object[2];
        table = new JTable();
        table.setBounds(10,20,140,210);
        table.setFillsViewportHeight(true);
        table.setBackground(Color.LIGHT_GRAY);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        model.addRow(column);
    }

    // EFFECTS: construct text field and setup for the text field
    private void makeTextField() {
        //text field to add currency name
        addCurrencyName = new JTextField();
        addCurrencyName.setBounds(157, 45, 70, 20);

        //text field for currency rate
        currencyRate = new JTextField();
        currencyRate.setBounds(157, 85, 70, 20);

        //text field to remove currency
        removeCurrencyName = new JTextField();
        removeCurrencyName.setBounds(157, 155, 70, 20);

        currency1 = new JTextField();
        currency1.setBounds(270, 45, 80, 20);

        currency2 = new JTextField();
        currency2.setBounds(270, 90, 80, 20);

        amount = new JTextField();
        amount.setBounds(270, 135, 80, 20);
    }

    // EFFECTS: construct add button and setup for the button
    private void makeAddButtons() {
        // add button
        addButton = new JButton(addString);
        addButton.setBounds(160, 110, 70, 20);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    row[0] = addCurrencyName.getText().toUpperCase();
                    row[1] = currencyRate.getText();
                    String name = addCurrencyName.getText().toUpperCase();
                    double rate = Double.parseDouble(currencyRate.getText());
                    cl.addCurrency(new Currency(name, rate));
                    model.addRow(row);
                    addCurrencyName.setText("");
                    currencyRate.setText("");
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(panel, "Please try again.");
                }
            }
        });
    }

    // EFFECTS: construct remove button and setup for the button
    private void makeRemoveButton() {
        removeButton = new JButton(removeString);
        removeButton.setBounds(160, 180, 70, 20);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cl.removeCurrency(removeCurrencyName.getText().toUpperCase())) {
                    cl.removeCurrency(removeCurrencyName.getText().toUpperCase());
                    model.setRowCount(0);
                    model.addRow(column);
                    for (Currency currency: cl.getCurrencies()) {
                        row[0] = currency.getCurrencyName();
                        row[1] = currency.getRateToOneUSD();
                        model.addRow(row);
                    }
                    JOptionPane.showMessageDialog(panel, "Removed.");
                } else {
                    JOptionPane.showMessageDialog(panel, "Currency not found in the list.");
                }
            }
        });
    }

    // EFFECTS: construct save button and setup for the button
    private void makeSaveButton() {
        saveButton = new JButton(saveString);
        saveButton.setBounds(160, 205, 70, 20);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(cl);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(panel, "Successfully Stored!!!");
                } catch (FileNotFoundException exception) {
                    JOptionPane.showMessageDialog(panel, "Fail to Store!!!");
                }
            }
        });
    }

    // EFFECTS: construct load button and setup for the button
    private void makeLoadButton() {
        loadButton = new JButton(loadString);
        loadButton.setBounds(275, 205, 70, 20);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cl = jsonReader.read();
                    model.setRowCount(0);
                    model.addRow(column);
                    for (Currency currency: cl.getCurrencies()) {
                        row[0] = currency.getCurrencyName();
                        row[1] = currency.getRateToOneUSD();
                        model.addRow(row);
                    }
                    JOptionPane.showMessageDialog(panel, "Loaded Successfully!!!");
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(panel, "Fail To Load!!!");
                }
            }
        });
    }

    private void makeConvertButton() {
        convertButton = new JButton("Convert");
        convertButton.setBounds(270, 165, 80, 20);
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String c1 = currency1.getText();
                    String c2 = currency2.getText();
                    double a = Double.parseDouble(amount.getText());
                    if (cl.oldCurrency(c1)) {
                        if (cl.newCurrency(c2)) {
                            JOptionPane.showMessageDialog(panel,c2.toUpperCase() + ": "
                                    + cl.convertCurrency(c1, c2, a));
                        } else {
                            JOptionPane.showMessageDialog(panel, "Please Try Again!!!");
                        }
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(panel, "Please Try Again!!!");
                }
            }
        });
    }

}


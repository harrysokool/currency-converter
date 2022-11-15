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

    // names for the buttons
    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private static final String saveString = "Save";
    private static final String loadString = "Load";

    // text field
    private final JTextField addCurrencyName;
    private final JTextField currencyRate;
    private final JTextField removeCurrencyName;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public Panel() {

        cl = new CurrencyList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        // table
        Object[] column = {"Currency", "Rate"};
        Object[] row = new Object[2];
        table = new JTable();
        table.setBounds(10,20,140,200);
        table.setFillsViewportHeight(true);
        table.setBackground(Color.LIGHT_GRAY);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        model.addRow(column);


        //text field to add currency name
        addCurrencyName = new JTextField();
        addCurrencyName.setText("Name");
        addCurrencyName.setBounds(157, 45, 70, 20);

        //text field for currency rate
        currencyRate = new JTextField();
        currencyRate.setText("Rate");
        currencyRate.setBounds(157, 70, 70, 20);

        //text field for currency rate
        removeCurrencyName = new JTextField();
        removeCurrencyName.setText("Name");
        removeCurrencyName.setBounds(157, 130, 70, 20);

        // add button
        JButton addButton = new JButton(addString);
        addButton.setBounds(160, 20, 70, 20);
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

        // remove button
        // buttons
        JButton removeButton = new JButton(removeString);
        removeButton.setBounds(160, 100, 70, 20);
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

        // save button
        JButton saveButton = new JButton(saveString);
        saveButton.setBounds(160, 160, 70, 20);
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

        // load button
        JButton loadButton = new JButton(loadString);
        loadButton.setBounds(160, 190, 70, 20);
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

        // panel setting
        this.setBounds(0,0,300,300);
        this.setLayout(null);

        // adding component
        this.add(addCurrencyName);
        this.add(currencyRate);
        this.add(removeCurrencyName);
        this.add(addButton);
        this.add(removeButton);
        this.add(saveButton);
        this.add(loadButton);
        this.add(table);

    }

}


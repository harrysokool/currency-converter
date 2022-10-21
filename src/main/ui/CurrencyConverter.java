package ui;

import model.Currency;
import model.CurrencyList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class CurrencyConverter {
    private static final String JSON_STORE = "./data/CurrencyList.json";
    private Scanner input;
    private CurrencyList currencyList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs currencyList and runs application
    public CurrencyConverter() throws FileNotFoundException {
        input = new Scanner(System.in);
        currencyList = new CurrencyList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runCurrencyConverter();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    public void runCurrencyConverter() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes currencyList
    private void init() {
        currencyList = new CurrencyList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add currency");
        System.out.println("\tr -> remove currency");
        System.out.println("\tc -> convert currency");
        System.out.println("\tl -> list currency");
        System.out.println("\ts -> save currencies to file");
        System.out.println("\tj -> load currencies from file");
        System.out.println("\tq -> quit");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "a":
                doAddCurrency();
                break;
            case "r":
                doRemoveCurrency();
                break;
            case "c":
                doConvertCurrency();
                break;
            case "l":
                doListCurrency();
                break;
            case "s":
                saveCurrencies();
                break;
            case "j":
                loadCurrencies();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

      /*
        MODIFIES: this
        EFFECTS: add currency to the list.
      */
    private void doAddCurrency() {
        System.out.println("Type in the name of currency (e.g. HKD, CAD, USD): ");
        String name = input.next();
        System.out.println("Set the rate of the currency to 1 USD: ");
        double rate = input.nextDouble();
        Currency currency = new Currency(name, rate);
        currencyList.addCurrency(currency);
    }

        /*
        MODIFIES: this
        EFFECTS: remove currency to the list.
         */
    private void doRemoveCurrency() {
        System.out.println("Type in the name of currency (e.g. HKD, CAD, USD): ");
        String name = input.next();
        if (currencyList.removeCurrency(name)) {
            System.out.println(name.toUpperCase() + " is removed from the list.");
        } else {
            System.out.println(name.toUpperCase() + " not found in the list.");
        }
    }

    //EFFECTS: covert currency
    private void doConvertCurrency() {
        System.out.println("Type in the name of currency to convert from (e.g. HKD, CAD, USD): ");
        String currency1 = input.next();
        System.out.println("Type in the name of currency to convert to (e.g. HKD, CAD, USD): ");
        String currency2 = input.next();
        System.out.println("Type in the amount to convert: ");
        double amount = input.nextDouble();
        if (currencyList.oldCurrency(currency1)) {
            if (currencyList.newCurrency(currency2)) {
                System.out.println(currency2.toUpperCase() + ": "
                        + currencyList.convertCurrency(currency1, currency2, amount));
            } else {
                System.out.println(currency2.toUpperCase() + " is not in the list!!!");
            }
        } else {
            System.out.println(currency1.toUpperCase() + " is not in the list!!!");
        }

    }

    // EFFECTS: list the currency
    private void doListCurrency() {
        System.out.println(currencyList.listCurrencies());
    }

    // EFFECTS: saves the workroom to file
    private void saveCurrencies() {
        try {
            jsonWriter.open();
            jsonWriter.write(currencyList);
            jsonWriter.close();
            System.out.println("Saved currencies to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadCurrencies() {
        try {
            currencyList = jsonReader.read();
            System.out.println("Loaded currencies from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

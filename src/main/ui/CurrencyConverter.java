package ui;

import model.Currency;
import model.CurrencyList;
import java.util.Scanner;


public class CurrencyConverter {
    private Scanner input;
    private CurrencyList currencyList;


    // EFFECTS: runs the currency converter application
    public CurrencyConverter() {
        runCurrencyConverter();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    public void runCurrencyConverter() {
        boolean keepGoing = true;
        String command = null;

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
        System.out.println("\tq -> quit");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddCurrency();
        } else if (command.equals("r")) {
            doRemoveCurrency();
        } else if (command.equals("c")) {
            doConvertCurrency();
        } else if (command.equals("l")) {
            doListCurrency();
        } else {
            System.out.println("Selection not valid...");
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
        System.out.println(currency2.toUpperCase() + ": " + currencyList.convertCurrency(currency1, currency2, amount));
    }

    // EFFECTS: list the currency
    private void doListCurrency() {
        System.out.println(currencyList.listCurrencies());
    }

}

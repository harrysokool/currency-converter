package model;
// A list of currency, and can be added or removed.

import java.util.ArrayList;
import java.util.List;

public class CurrencyList {
    // fields
    private final List<Currency> currencies; // list of currencies

    //methods
    /*EFFECTS: initialize an empty list of currencies
     */
    public CurrencyList() {
        currencies = new ArrayList<>();
    }

    /*
     * REQUIRES: a valid currency
     * MODIFIES: this
     * EFFECTS: add currency into currencies.
     */
    public String addCurrency(Currency currency) {
        currencies.add(currency);
        return currency.getCurrencyName();
    }

    /*
     * REQUIRES: a valid currency
     * MODIFIES: this
     * EFFECTS: remove currency from currencies.
     */
    public String removeCurrency(Currency currency) {
        currencies.remove(currency);
        return currency.getCurrencyName();
    }

    /*
     * EFFECTS: list currencies
     */
    public List<Currency> listCurrencies() {
        return currencies;
    }

    /*
     * REQUIRES: amount >= 0
     * MODIFIES: this
     * EFFECTS: rate to 1 USD of the currency is updated
     */
    public double convertCurrency(Currency prevCurrency, Currency nextCurrency, double amount) {
        double finalAmount;
        prevCurrency.toUSD(amount);
        finalAmount = prevCurrency.getUsd() * nextCurrency.getRateToOneUSD();
        return finalAmount;
    }

}

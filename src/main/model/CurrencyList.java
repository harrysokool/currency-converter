package model;
// A list of currency, and can be added or removed.

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class CurrencyList implements Writable {
    // fields
    private List<Currency> currencies; // list of currencies
    private Currency currency1; // to change this currency
    private Currency currency2; // to change into this currency

    //methods
    /*EFFECTS: initialize an empty list of currencies
     */
    public CurrencyList() {
        currencies = new ArrayList<>();
    }

    public int size() {
        return currencies.size();
    }

    // EFFECTS: returns currency at index i in currencies.
    public Currency get(int i) {
        return currencies.get(i);
    }

    public Currency getCurrency1() {
        return currency1;
    }

    public Currency getCurrency2() {
        return currency2;
    }

    /*
     * REQUIRES: a valid and initialize currency
     * MODIFIES: this
     * EFFECTS: if currencies size is 0, then add currency and return true; if currencies size > 0, and
     *          if currency not in currencies, then add currency into currencies and return true;
     *          if currencies size > 0 and currency is already in currencies, then return false
     */
    public boolean addCurrency(Currency currency) {
        if (!(currency == null)) {
            if (currencies.size() == 0) {
                currencies.add(currency);
                return true;
            } else if (!currencies.contains(currency)) {
                currencies.add(currency);
                return true;
            }
        }
        return false;
    }

    /*
     * REQUIRES: a valid currency name
     * MODIFIES: this
     * EFFECTS: remove currency from currencies if found, else
     *          return false.
     */
    public boolean removeCurrency(String currency) {
        if (currencies.size() == 0) {
            return false;
        } else {
            for (Currency curr : currencies) {
                if (curr.getCurrencyName().equals(currency.toUpperCase())) {
                    currencies.remove(curr);
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * MODIFIES: this
     * EFFECTS: if currencies size = 0, return false, checks if currency is in currencies,
     *          if it is in currencies, assign currency1 to the existing currency.
     */
    public boolean oldCurrency(String currency) {
        for (Currency curr : currencies) {
            if (currency.toUpperCase().equals(curr.getCurrencyName())) {
                this.currency1 = curr;
                return true;
            }
        }
        return false;
    }

    /*
     * MODIFIES: this
     * EFFECTS: if currencies size = 0, return false, checks if currency is in currencies,
     *          if it is in currencies, assign currency2 to the existing currency.
     */
    public boolean newCurrency(String currency) {
        for (Currency curr : currencies) {
            if (currency.toUpperCase().equals(curr.getCurrencyName())) {
                this.currency2 = curr;
                return true;
            }
        }
        return false;
    }

    public boolean containsBothCurrencies(String currency1, String currency2) {
        if (oldCurrency(currency1.toUpperCase()) && newCurrency(currency2.toUpperCase())) {
            return true;
        }
        return false;
    }

    /*
     * REQUIRES: prevCurrency, nextCurrency must be in the currencies, amount >= 0
     * EFFECTS: convert and return amount from prevCurrency into nextCurrency
     */
    public double convertCurrency(String prevCurrency, String nextCurrency, double amount) {
        double finalAmount;
        double amountInUSD;
        containsBothCurrencies(prevCurrency.toUpperCase(), nextCurrency.toUpperCase());
        amountInUSD = amount * currency1.getRateToOneUSD();
        finalAmount = amountInUSD / currency2.getRateToOneUSD();
        return (double) Math.round(finalAmount * 100) / 100;
    }

    /*
     * EFFECTS: list currency in currencies if currencies size > 0,
     *          else shows a message "No currency in the list"
     */
    public String listCurrencies() {
        if (currencies.size() > 0) {
            String curr = "";
            for (Currency currency : currencies) {
                curr += " " + currency.getCurrencyName();
            }
            return "currencies =" + curr;
        } else {
            return "No currency in the list";
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("currencies", currenciesToJson());
        return json;
    }

    // EFFECTS: returns currencies in currencylist as a JSON array
    private JSONArray currenciesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Currency curr : currencies) {
            jsonArray.put(curr.toJson());
        }
        return jsonArray;
    }
}

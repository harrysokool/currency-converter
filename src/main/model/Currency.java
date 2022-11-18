package model;
// Represent the name of a currency and the rate to USD of the currency.

import org.json.JSONObject;
import persistence.Writable;

public class Currency implements Writable {
    //fields
    private String currencyName; // name of the currency
    private double rateToOneUSD; // rate of the currency to 1 USD. e.g.) 1 USD = rate * 8 HKD

    //methods
    /*
     * REQUIRES: currencyName has a non-zero length, rateToOneUSD must be >= 0.
     * EFFECTS: name of currency is set to currencyName; rate to 1 USD to set to
     *          rateToOneUSD; amount of currency set to 0; set usd of this currency
     *          to 0.
     */
    public Currency(String currencyName, double rateToOneUSD) {
        setCurrencyName(currencyName.toUpperCase());
        updateRate(rateToOneUSD);
    }

    public String getCurrencyName() {
        return currencyName.toUpperCase();
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName.toUpperCase();
    }

    public double getRateToOneUSD() {
        return rateToOneUSD;
    }

    /*
     * REQUIRES: rateToOneUSD >= 0
     * MODIFIES: this
     * EFFECTS: rate to 1 USD of the currency is updated
     */
    public void updateRate(double rateToOneUSD) {
        this.rateToOneUSD = rateToOneUSD;
    }

    /*
     * EFFECTS: returns a string representation of currency
     */
    @Override
    public String toString() {
        return "currencyName = " + currencyName
                + ", rateToOneUSD = " + rateToOneUSD + "\n";
    }

    /*
     * REQUIRES: a object of currency
     * EFFECTS: compares both currency objects, if they have the same field then return true,
     *          if the object o is null or not the same class then return false,
     *          compares the field of both objects.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Currency currency = (Currency) o;
        return currencyName.equals(currency.getCurrencyName());
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("currency", currencyName);
        json.put("rate", rateToOneUSD);
        return json;
    }
}

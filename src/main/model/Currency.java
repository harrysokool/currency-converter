package model;
// Represent the name of a currency and the rate to USD of the currency.

public class Currency {
    //fields
    private String currencyName; // name of the currency
    private double rateToOneUSD; // rate of the currency to 1 USD. e.g.) 1 USD = rate * 8 HKD
    private double amount; // amount of the currency
    private double usd; // amount of the currency to usd

    //methods
    /*
     * REQUIRES: currencyName has a non-zero length, rateToOneUSD must be >= 0.
     * EFFECTS: name of currency is set to currencyName; rate to 1 USD to set to
     *          rateToOneUSD; amount of currency set to 0; set usd of this currency
     *          to 0.
     */
    public Currency(String currencyName, double rateToOneUSD) {
        setCurrencyName(currencyName);
        updateRate(rateToOneUSD);
        amount = 0;
        usd = 0;
    }

    public double getUsd() {
        return usd;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public double getRateToOneUSD() {
        return rateToOneUSD;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }

    /*
     * REQUIRES: amount >= 0
     * MODIFIES: this
     * EFFECTS: amount is updated
     */
    public void amountToConvert(double amount) {
        this.amount = amount;
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
     * REQUIRES: amount >= 0
     * MODIFIES: this
     * EFFECTS: usd of the currency is updated
     */
    public void toUSD(double amount) {
        amountToConvert(amount);
        setUsd(getAmount() * getRateToOneUSD());
    }

    /*
     * EFFECTS: returns a string representation of currency
     */
    @Override
    public String toString() {
        return "Currency{"
                + "Currency name='" + currencyName + '\''
                + ", Rate to ene USD=" + rateToOneUSD
                + '}';
    }

}






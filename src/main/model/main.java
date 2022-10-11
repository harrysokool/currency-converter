package model;

public class main {
    public static void main(String[] args) {
        CurrencyList cl = new CurrencyList();
        cl.addCurrency(new Currency("HKD", 0.125));
        cl.addCurrency(new Currency("cad", 0.727));
        System.out.println(cl.convertCurrency("cAd", "hkd", 100));
    }
}

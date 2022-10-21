package persistance;

import model.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCurrency(String cname, double rate, Currency currency) {
        assertEquals(cname, currency.getCurrencyName());
        assertEquals(rate, currency.getRateToOneUSD());
    }
}

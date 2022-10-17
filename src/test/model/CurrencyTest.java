package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CurrencyTest {
    private Currency testCurrency;

    @BeforeEach
    void setUp(){
        testCurrency = new Currency("CAD", 0.727);
    }

    @Test
    void testConstructor(){
        assertEquals("CAD", testCurrency.getCurrencyName());
        assertEquals(0.727, testCurrency.getRateToOneUSD());
    }

    @Test
    void testUpdateRate(){
        testCurrency.updateRate(0);
        assertEquals(0, testCurrency.getRateToOneUSD());
        testCurrency.updateRate(1);
        assertEquals(1, testCurrency.getRateToOneUSD());
        testCurrency.updateRate(100);
        assertEquals(100, testCurrency.getRateToOneUSD());
    }

    @Test
    void testToString(){
        assertTrue(testCurrency.toString().contains("currencyName = CAD, "
                + "rateToOneUSD = 0.727"));
    }

    @Test
    void testEqual(){
        Currency curr1 = new Currency("hkd", 0.125);
        Currency curr2 = new Currency("cad", 0.727);
        assertEquals(false, testCurrency.equals(curr1));
        assertEquals(true, testCurrency.equals(curr2));
        assertEquals(false, testCurrency.equals(null));
        assertEquals(false, testCurrency.equals(new String()));

    }

}

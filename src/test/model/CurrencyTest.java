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
        Currency curr3 = new Currency("hkd", 0.125);
        Currency curr4 = new Currency("hkd", 0.700);
        Currency curr5 = new Currency("cad", 0.125);
        assertTrue(curr1.equals(curr1));
        assertFalse(curr1.equals(curr2));
        assertTrue(curr1.equals(curr3));
        assertTrue(curr1.equals(curr4));
        assertFalse(curr1.equals(curr5));
        assertFalse(curr1.equals(null));
        assertFalse(curr1.equals(123));
        assertFalse(curr1.equals("hello"));
        assertFalse(curr1.equals(new Exception()));

    }

}

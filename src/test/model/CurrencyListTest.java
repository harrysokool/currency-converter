package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CurrencyListTest {
    private CurrencyList testCurrencies;

    @BeforeEach
    void setUp(){
        testCurrencies = new CurrencyList();
    }

    @Test
    void testConstructor(){
        assertEquals(0, testCurrencies.size());
    }

    // currencies size = 0
    @Test
    void testAddCurrency1(){
        assertEquals(0, testCurrencies.size());
        testCurrencies.addCurrency(new Currency("CAD", 0.727));
        assertEquals(1, testCurrencies.size());
        assertEquals("CAD", testCurrencies.get(0).getCurrencyName());
    }

    // currencies size != 0 and added different currency
    @Test
    void testAddCurrency2(){
        assertEquals(0, testCurrencies.size());
        assertTrue(testCurrencies.addCurrency(new Currency("CAD", 0.727)));
        assertEquals(1, testCurrencies.size());
        assertTrue(testCurrencies.addCurrency(new Currency("HKD", 0.125)));
        assertFalse(testCurrencies.addCurrency(new Currency("CAD", 0.727)));
        assertEquals(2, testCurrencies.size());
        assertEquals("CAD", testCurrencies.get(0).getCurrencyName());
        assertEquals("HKD", testCurrencies.get(1).getCurrencyName());
    }

    // currencies size != 0 added same currency
    @Test
    void testAddCurrency3(){
        assertEquals(0, testCurrencies.size());
        assertTrue(testCurrencies.addCurrency(new Currency("CAD", 0.727)));
        assertEquals(1, testCurrencies.size());
        assertFalse(testCurrencies.addCurrency(new Currency("CAD", 0.727)));
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        assertEquals(2, testCurrencies.size());

    }

    // currencies size != 0 added invalid currency
    @Test
    void testAddCurrency4(){
        assertEquals(0, testCurrencies.size());
        assertFalse(testCurrencies.addCurrency(null));
        assertEquals(0, testCurrencies.size());
    }

    // currencies size = 0
    @Test
    void testRemoveCurrency1(){
        assertEquals(0, testCurrencies.size());
        assertFalse(testCurrencies.removeCurrency("cad"));
        assertEquals(0, testCurrencies.size());
    }

    // currencies size > 0, CAD is in currencies and remove CAD
    @Test
    void testRemoveCurrency2(){
        testCurrencies.addCurrency(new Currency("CAD", 0.727));
        assertEquals(1, testCurrencies.size());
        assertEquals("CAD", testCurrencies.get(0).getCurrencyName());
        assertTrue(testCurrencies.removeCurrency("cad"));
        assertEquals(0, testCurrencies.size());
    }

    // currencies > 0, CAD is in currencies and remove other currency
    @Test
    void testRemoveCurrency3(){
        testCurrencies.addCurrency(new Currency("CAD", 0.727));
        assertEquals(1, testCurrencies.size());
        assertEquals("CAD", testCurrencies.get(0).getCurrencyName());
        assertFalse(testCurrencies.removeCurrency("hkd"));
        assertEquals(1, testCurrencies.size());
    }

    // currencies size > 0
    @Test
    void testListCurrencies1(){
        testCurrencies.addCurrency(new Currency("CAD", 0.727));
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        assertEquals(2, testCurrencies.size());
        assertEquals("currencies = CAD HKD", testCurrencies.listCurrencies());
    }

    // currencies size = 0
    @Test
    void testListCurrencies2() {
        assertEquals(0, testCurrencies.size());
        assertEquals("No currency in the list", testCurrencies.listCurrencies());
    }

    // currencies size = 0
    @Test
    void testOldCurrency1(){
        assertEquals(0, testCurrencies.size());
        assertFalse(testCurrencies.oldCurrency("HKD"));
        assertNull(testCurrencies.getCurrency1());
    }

    // currencies size > 0 and found
    @Test
    void testOldCurrency2(){
        testCurrencies.addCurrency(new Currency("CAD", 0.727));
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        testCurrencies.addCurrency(new Currency("CAD", 0.727));
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        assertEquals(2, testCurrencies.size());
        assertTrue(testCurrencies.oldCurrency("HKD"));
        assertEquals("HKD", testCurrencies.getCurrency1().getCurrencyName());
    }

    // currencies size > 0 and not found
    @Test
    void testOldCurrency3(){
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        assertEquals(1, testCurrencies.size());
        assertFalse(testCurrencies.oldCurrency("CAD"));
        assertNull(testCurrencies.getCurrency1());
    }

    // currencies size = 0
    @Test
    void testNewCurrency1(){
        assertEquals(0, testCurrencies.size());
        assertFalse(testCurrencies.newCurrency("CAD"));
        assertNull(testCurrencies.getCurrency2());
    }

    // currencies size > 0 and found
    @Test
    void testNewCurrency2(){
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        testCurrencies.addCurrency(new Currency("CAD", 0.727));
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        assertEquals(2, testCurrencies.size());
        assertTrue(testCurrencies.newCurrency("CAD"));
        assertEquals("CAD", testCurrencies.getCurrency2().getCurrencyName());
    }

    // currencies size > 0 and not found
    @Test
    void testNewCurrency3(){
        testCurrencies.addCurrency(new Currency("CAD", 0.125));
        testCurrencies.addCurrency(new Currency("CAD", 0.125));
        assertEquals(1, testCurrencies.size());
        assertFalse(testCurrencies.newCurrency("hkd"));
        assertNull(testCurrencies.getCurrency1());
    }

    // both are true
    @Test
    void testContainsBothCurrencies1(){
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        testCurrencies.addCurrency(new Currency("CAD", 0.727));
        assertTrue(testCurrencies.oldCurrency("HKD"));
        assertTrue(testCurrencies.newCurrency("CAD"));
        assertTrue(testCurrencies.containsBothCurrencies("hkd", "cad"));
    }

    // only one is true
    @Test
    void testContainsBothCurrencies2(){
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        testCurrencies.addCurrency(new Currency("JPY", 0.727));
        assertTrue(testCurrencies.oldCurrency("HKD"));
        assertFalse(testCurrencies.newCurrency("CAD"));
        assertFalse(testCurrencies.containsBothCurrencies("hkd", "cad"));
    }

    // both are false
    @Test
    void testContainsBothCurrencies3(){
        testCurrencies.addCurrency(new Currency("USD", 0.125));
        testCurrencies.addCurrency(new Currency("JPY", 0.727));
        assertFalse(testCurrencies.oldCurrency("HKD"));
        assertFalse(testCurrencies.newCurrency("CAD"));
        assertFalse(testCurrencies.containsBothCurrencies("hkd", "cad"));
    }

    // when both currencies are in the testcurrencies and amount is 0, 1 and 100
    @Test
    void testConvertCurrency(){
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        testCurrencies.addCurrency(new Currency("CAD", 0.727));
        assertTrue(testCurrencies.oldCurrency("HKD"));
        assertTrue(testCurrencies.newCurrency("CAD"));
        assertEquals(0, testCurrencies.convertCurrency("cad", "hkd", 0));
        assertEquals(5.82, testCurrencies.convertCurrency("cad", "hkd", 1));
        assertEquals(581.6, testCurrencies.convertCurrency("cad", "hkd", 100));
    }


}

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
        assertEquals(true,  testCurrencies.addCurrency(new Currency("CAD", 0.727)));
        assertEquals(1, testCurrencies.size());
        assertEquals(true,  testCurrencies.addCurrency(new Currency("HKD", 0.125)));
        assertEquals(false,  testCurrencies.addCurrency(new Currency("CAD", 0.727)));
        assertEquals(2, testCurrencies.size());
        assertEquals("CAD", testCurrencies.get(0).getCurrencyName());
        assertEquals("HKD", testCurrencies.get(1).getCurrencyName());
    }

    // currencies size != 0 added same currency
    @Test
    void testAddCurrency3(){
        assertEquals(0, testCurrencies.size());
        assertEquals(true,  testCurrencies.addCurrency(new Currency("CAD", 0.727)));
        assertEquals(1, testCurrencies.size());
        assertEquals(false,  testCurrencies.addCurrency(new Currency("CAD", 0.727)));
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        assertEquals(2, testCurrencies.size());

    }

    // currencies size = 0
    @Test
    void testRemoveCurrency1(){
        assertEquals(0, testCurrencies.size());
        assertEquals(false, testCurrencies.removeCurrency("cad"));
        assertEquals(0, testCurrencies.size());
    }

    // currencies size > 0, CAD is in currencies and remove CAD
    @Test
    void testRemoveCurrency2(){
        testCurrencies.addCurrency(new Currency("CAD", 0.727));
        assertEquals(1, testCurrencies.size());
        assertEquals("CAD", testCurrencies.get(0).getCurrencyName());
        assertEquals(true, testCurrencies.removeCurrency("cad"));
        assertEquals(0, testCurrencies.size());
    }

    // currencies > 0, CAD is in currencies and remove other currency
    @Test
    void testRemoveCurrency3(){
        testCurrencies.addCurrency(new Currency("CAD", 0.727));
        assertEquals(1, testCurrencies.size());
        assertEquals("CAD", testCurrencies.get(0).getCurrencyName());
        assertEquals(false, testCurrencies.removeCurrency("hkd"));
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
        assertEquals(false, testCurrencies.oldCurrency("HKD"));
        assertEquals(null, testCurrencies.getCurrency1());
    }

    // currencies size > 0 and found
    @Test
    void testOldCurrency2(){
        testCurrencies.addCurrency(new Currency("CAD", 0.727));
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        testCurrencies.addCurrency(new Currency("CAD", 0.727));
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        assertEquals(2, testCurrencies.size());
        assertEquals(true, testCurrencies.oldCurrency("HKD"));
        assertEquals("HKD", testCurrencies.getCurrency1().getCurrencyName());
    }

    // currencies size > 0 and not found
    @Test
    void testOldCurrency3(){
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        assertEquals(1, testCurrencies.size());
        assertEquals(false, testCurrencies.oldCurrency("CAD"));
        assertEquals(null, testCurrencies.getCurrency1());
    }

    // currencies size = 0
    @Test
    void testNewCurrency1(){
        assertEquals(0, testCurrencies.size());
        assertEquals(false, testCurrencies.newCurrency("CAD"));
        assertEquals(null, testCurrencies.getCurrency2());
    }

    // currencies size > 0 and found
    @Test
    void testNewCurrency2(){
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        testCurrencies.addCurrency(new Currency("CAD", 0.727));
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        assertEquals(2, testCurrencies.size());
        assertEquals(true, testCurrencies.newCurrency("CAD"));
        assertEquals("CAD", testCurrencies.getCurrency2().getCurrencyName());
    }

    // currencies size > 0 and not found
    @Test
    void testNewCurrency3(){
        testCurrencies.addCurrency(new Currency("CAD", 0.125));
        testCurrencies.addCurrency(new Currency("CAD", 0.125));
        assertEquals(1, testCurrencies.size());
        assertEquals(false, testCurrencies.newCurrency("hkd"));
        assertEquals(null, testCurrencies.getCurrency1());
    }

    // both are true
    @Test
    void testContainsBothCurrencies1(){
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        testCurrencies.addCurrency(new Currency("CAD", 0.727));
        assertEquals(true, testCurrencies.oldCurrency("HKD"));
        assertEquals(true, testCurrencies.newCurrency("CAD"));
        assertEquals(true, testCurrencies.containsBothCurrencies("hkd", "cad"));
    }

    // only one is true
    @Test
    void testContainsBothCurrencies2(){
        testCurrencies.addCurrency(new Currency("HKD", 0.125));
        testCurrencies.addCurrency(new Currency("JPY", 0.727));
        assertEquals(true, testCurrencies.oldCurrency("HKD"));
        assertEquals(false, testCurrencies.newCurrency("CAD"));
        assertEquals(false, testCurrencies.containsBothCurrencies("hkd", "cad"));
    }

    // both are false
    @Test
    void testContainsBothCurrencies3(){
        testCurrencies.addCurrency(new Currency("USD", 0.125));
        testCurrencies.addCurrency(new Currency("JPY", 0.727));
        assertEquals(false, testCurrencies.oldCurrency("HKD"));
        assertEquals(false, testCurrencies.newCurrency("CAD"));
        assertEquals(false, testCurrencies.containsBothCurrencies("hkd", "cad"));
    }


    @Test
    void testConvertCurrency1(){

    }

    @Test
    void testConvertCurrency2(){

    }

    @Test
    void testConvertCurrency3(){

    }


}

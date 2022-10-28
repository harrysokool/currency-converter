package persistance;

import model.Currency;
import model.CurrencyList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// citation: JsonSerializationDemo
public class JsonReaderTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            CurrencyList cl = new CurrencyList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            CurrencyList cl = new CurrencyList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCurrencyList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCurrencyList.json");
            cl = reader.read();
            assertEquals(0, cl.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            CurrencyList cl = new CurrencyList();
            cl.addCurrency(new Currency("HKD", 0.125));
            cl.addCurrency(new Currency("CAD", 0.727));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCurrencyList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCurrencyList.json");
            cl = reader.read();
            List<Currency> currencies = cl.getCurrencies();
            assertEquals(2, currencies.size());
            checkCurrency("HKD", 0.125, currencies.get(0));
            checkCurrency("CAD", 0.727, currencies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

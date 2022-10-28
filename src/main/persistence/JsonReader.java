package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Currency;
import model.CurrencyList;
import org.json.*;

// citation: JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CurrencyList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCurrencyList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses currencies from JSON object and returns it
    private CurrencyList parseCurrencyList(JSONObject jsonObject) {
        CurrencyList cl = new CurrencyList();
        addCurrencies(cl, jsonObject);
        return cl;
    }

    // MODIFIES: cl
    // EFFECTS: parses currencies from JSON object and adds them to currencies
    private void addCurrencies(CurrencyList cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("currencies");
        for (Object json : jsonArray) {
            JSONObject nextCurrency = (JSONObject) json;
            addCurrency(cl, nextCurrency);
        }
    }

    // MODIFIES: cl
    // EFFECTS: parses currency from JSON object and adds it to currencies
    private void addCurrency(CurrencyList cl, JSONObject jsonObject) {
        String name = jsonObject.getString("currency");
        double rate =  jsonObject.getDouble("rate");
        Currency currency = new Currency(name, rate);
        cl.addCurrency(currency);
    }
}

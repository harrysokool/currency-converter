package persistence;

import org.json.JSONObject;

// citation: JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
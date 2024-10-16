package edu.uga.devdogs.sampledataparser.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A custom deserializer for converting a JSON string into a {@link LocalTime} object.
 * The deserializer expects the time string to be in the "HH:mm" format (e.g., "14:30").
 */
public class LocalTimeDeserializer implements JsonDeserializer<LocalTime> {

    // Formatter to parse the time in "HH:mm" format
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Deserializes a JSON element into a {@link LocalTime} object.
     * The JSON element is expected to be a string in the "HH:mm" format, representing a time.
     *
     * @param jsonElement the JSON element to deserialize, expected to be a time string.
     * @param type the type of the object to deserialize.
     * @param jsonDeserializationContext the context for deserialization.
     * @return a {@link LocalTime} object representing the time from the JSON string.
     * @throws JsonParseException if the JSON element cannot be parsed into a valid {@link LocalTime}.
     */
    @Override
    public LocalTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        String timeString = jsonElement.getAsString();

        return LocalTime.parse(timeString, formatter);
    }
}

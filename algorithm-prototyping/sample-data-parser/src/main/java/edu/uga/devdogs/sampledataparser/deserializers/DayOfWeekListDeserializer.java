package edu.uga.devdogs.sampledataparser.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 * A custom deserializer for converting a JSON array of strings to a list of {@link DayOfWeek} enums.
 * This deserializer is used when the JSON contains an array of day strings (e.g., ["Monday", "Tuesday"]).
 * Each string is converted into its corresponding {@link DayOfWeek} enum value.
 */
public class DayOfWeekListDeserializer implements JsonDeserializer<List<DayOfWeek>> {

    /**
     * Deserializes a JSON element into a list of {@link DayOfWeek} enums.
     * The JSON element is expected to be a JSON array where each element is a string representing
     * a day of the week (e.g., "Monday", "Tuesday"). The deserializer converts these strings into
     * their corresponding {@link DayOfWeek} enum values.
     *
     * @param jsonElement the JSON element to deserialize, expected to be a JSON array of strings.
     * @param type the type of the object to deserialize.
     * @param jsonDeserializationContext the context for deserialization.
     * @return a list of {@link DayOfWeek} corresponding to the input JSON strings.
     * @throws JsonParseException if the JSON element is not a valid array or if a string
     *         does not match a valid day of the week.
     */
    @Override
    public List<DayOfWeek> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        List<DayOfWeek> days = new ArrayList<>();

        for (JsonElement element : jsonArray) {
            String dayString = element.getAsString();
            days.add(DayOfWeek.valueOf(dayString.toUpperCase()));
        }

        return days;
    }
}

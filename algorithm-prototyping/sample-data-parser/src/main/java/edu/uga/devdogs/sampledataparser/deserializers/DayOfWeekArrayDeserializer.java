package edu.uga.devdogs.sampledataparser.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.DayOfWeek;

/**
 * A custom deserializer for converting a JSON array of strings to an array of {@link DayOfWeek} enums.
 * This deserializer is used when the JSON contains an array of day strings (e.g., ["Monday", "Tuesday"]).
 * Each string is converted into its corresponding {@link DayOfWeek} enum value.
 */
public class DayOfWeekArrayDeserializer implements JsonDeserializer<DayOfWeek[]> {

    /**
     * Deserializes a JSON element into an array of {@link DayOfWeek} enums.
     * The JSON element is expected to be a JSON array where each element is a string representing
     * a day of the week (e.g., "Monday", "Tuesday"). The deserializer converts these strings into
     * their corresponding {@link DayOfWeek} enum values.
     *
     * @param jsonElement the JSON element to deserialize, expected to be a JSON array of strings.
     * @param type the type of the object to deserialize.
     * @param jsonDeserializationContext the context for deserialization.
     * @return an array of {@link DayOfWeek} corresponding to the input JSON strings.
     * @throws JsonParseException if the JSON element is not a valid array or if a string
     *         does not match a valid day of the week.
     */
    @Override
    public DayOfWeek[] deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        DayOfWeek[] days = new DayOfWeek[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            String dayString = jsonArray.get(i).getAsString();
            days[i] = DayOfWeek.valueOf(dayString.toUpperCase());
        }

        return days;
    }
}

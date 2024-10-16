package edu.uga.devdogs.sampledataparser.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.DayOfWeek;

public class DayOfWeekArrayDeserializer implements JsonDeserializer<DayOfWeek[]> {

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

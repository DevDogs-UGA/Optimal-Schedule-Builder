package edu.uga.devdogs.sampledataparser.deserializers;

import com.google.gson.*;
import edu.uga.devdogs.sampledataparser.records.Course;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * A custom deserializer for deserializing a JSON array of courses into a
 * {@link Map} where the keys are course codes and the values are {@link Course} objects.
 * <p>
 * This deserializer reads a JSON array, deserializes each element into a {@link Course},
 * and stores it in a {@link HashMap} with the course code as the key for easy access.
 */
public class CourseMapDeserializer implements JsonDeserializer<Map<String, Course>> {

    /**
     * Deserializes the provided {@link JsonElement} into a {@link Map} where the course code
     * is the key, and the {@link Course} object is the value.
     *
     * @param jsonElement the JSON data being deserialized, expected to be a JSON array of courses
     * @param type the type of the Map to be returned
     * @param jsonDeserializationContext the context for deserialization, used to deserialize individual {@link Course} objects
     * @return a {@link Map} where the key is the course code and the value is the corresponding {@link Course} object
     * @throws JsonParseException if the JSON is not in the expected format or if any deserialization error occurs
     */
    @Override
    public Map<String, Course> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        Map<String, Course> courses = new HashMap<>();

        for (JsonElement element : jsonArray) {
            Course course = jsonDeserializationContext.deserialize(element, Course.class);
            courses.put(course.courseCode(), course);
        }

        return courses;
    }

}

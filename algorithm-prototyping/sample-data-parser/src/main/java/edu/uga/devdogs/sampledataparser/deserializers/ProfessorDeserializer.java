package edu.uga.devdogs.sampledataparser.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import edu.uga.devdogs.sampledataparser.records.Professor;

import java.lang.reflect.Type;

/**
 * A custom deserializer for converting a JSON string into a {@link Professor} object.
 * This deserializer matches a professor's name from the JSON input with an array of pre-defined
 * {@link Professor} objects. If a match is found, the corresponding {@link Professor} object is returned.
 * Otherwise, it throws a {@link JsonParseException}.
 */
public class ProfessorDeserializer implements JsonDeserializer<Professor> {

    private final Professor[] professors;

    /**
     * Constructs a {@link ProfessorDeserializer} with a predefined array of {@link Professor} objects.
     *
     * @param professors an array of {@link Professor} objects that will be used to match the deserialized name.
     */
    public ProfessorDeserializer(Professor[] professors) {
        this.professors = professors;
    }

    /**
     * Deserializes a JSON element into a {@link Professor} object by matching the provided professor name
     * (as a JSON string) to a {@link Professor} object in the predefined array of professors.
     *
     * @param json the JSON element representing a professor's name as a string.
     * @param type the type of the object to deserialize.
     * @param jsonDeserializationContext the context for deserialization.
     * @return the corresponding {@link Professor} object if a match is found.
     * @throws JsonParseException if no matching professor is found.
     */
    @Override
    public Professor deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        String professorName = json.getAsString();

        for (Professor professor : professors) {
            if (professor.name().equals(professorName)) {
                return professor;
            }
        }

        throw new JsonParseException("Professor not found: " + professorName);
    }

}

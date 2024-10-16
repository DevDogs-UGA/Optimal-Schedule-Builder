package edu.uga.devdogs.sampledataparser.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import edu.uga.devdogs.sampledataparser.records.Professor;

import java.lang.reflect.Type;

public class ProfessorDeserializer implements JsonDeserializer<Professor> {

    private final Professor[] professors;

    public ProfessorDeserializer(Professor[] professors) {
        this.professors = professors;
    }

    public Professor deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext jsonDeserializationContext)
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

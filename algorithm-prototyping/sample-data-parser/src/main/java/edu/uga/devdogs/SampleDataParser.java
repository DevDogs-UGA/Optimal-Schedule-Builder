package edu.uga.devdogs;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.uga.devdogs.deserializers.LocalTimeDeserializer;
import edu.uga.devdogs.deserializers.ProfessorDeserializer;
import edu.uga.devdogs.records.Course;
import edu.uga.devdogs.records.Professor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;

public class SampleDataParser {

    public SampleDataParser() {

    }

    public Course[] parse(String professorsFilePath, String coursesFilePath) {
        String professorsJson = readFile(professorsFilePath);
        String coursesJson = readFile(coursesFilePath);

        Gson professorsGson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Professor[] professors = professorsGson.fromJson(professorsJson, Professor[].class);

        Gson coursesGson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Professor.class, new ProfessorDeserializer(professors))
                .registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer())
                .create();

        return coursesGson.fromJson(coursesJson, Course[].class);
    }

    private static String readFile(String filePath) {
        String json;

        try {
            json = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found: " + filePath);
        }
        return json;
    }

}

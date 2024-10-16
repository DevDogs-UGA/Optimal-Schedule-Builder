package edu.uga.devdogs;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.uga.devdogs.deserializers.DayOfWeekArrayDeserializer;
import edu.uga.devdogs.deserializers.LocalTimeDeserializer;
import edu.uga.devdogs.deserializers.ProfessorDeserializer;
import edu.uga.devdogs.records.Course;
import edu.uga.devdogs.records.Distances;
import edu.uga.devdogs.records.Professor;
import edu.uga.devdogs.records.SampleData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class SampleDataParser {

    public SampleDataParser() {

    }

    public SampleData parse(String professorsFilePath, String coursesFilePath, String distancesFilePath) {
        String professorsJson = readFile(professorsFilePath);
        String coursesJson = readFile(coursesFilePath);
        String distancesJson = readFile(distancesFilePath);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Professor[] professors = gson.fromJson(professorsJson, Professor[].class);

        Gson coursesGson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Professor.class, new ProfessorDeserializer(professors))
                .registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer())
                .registerTypeAdapter(DayOfWeek[].class, new DayOfWeekArrayDeserializer())
                .create();

        Course[] courses = coursesGson.fromJson(coursesJson, Course[].class);

        Distances distances = gson.fromJson(distancesJson, Distances.class);

        return new SampleData(courses, distances);
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

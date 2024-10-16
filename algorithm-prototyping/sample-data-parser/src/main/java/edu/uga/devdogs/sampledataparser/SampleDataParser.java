package edu.uga.devdogs.sampledataparser;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.uga.devdogs.sampledataparser.deserializers.DayOfWeekArrayDeserializer;
import edu.uga.devdogs.sampledataparser.deserializers.LocalTimeDeserializer;
import edu.uga.devdogs.sampledataparser.deserializers.ProfessorDeserializer;
import edu.uga.devdogs.sampledataparser.records.Course;
import edu.uga.devdogs.sampledataparser.records.Distances;
import edu.uga.devdogs.sampledataparser.records.Professor;
import edu.uga.devdogs.sampledataparser.records.SampleData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * The SampleDataParser class is responsible for parsing JSON data from multiple files
 * into structured Java objects. It deserializes the data for professors, courses, and building distances,
 * and returns a {@link SampleData} object containing all parsed data.
 */
public class SampleDataParser {

    /**
     * Constructs a SampleDataParser object.
     */
    public SampleDataParser() {

    }

    /**
     * Parses JSON data from the provided file paths, deserializing it into {@link Professor}, {@link Course},
     * and {@link Distances} objects. The method combines the parsed data into a {@link SampleData} object.
     *
     * @param professorsFilePath the file path to the JSON file containing professor data.
     * @param coursesFilePath    the file path to the JSON file containing course data.
     * @param distancesFilePath  the file path to the JSON file containing building distance data.
     * @return a {@link SampleData} object containing the parsed course, professor, and distance data.
     * @throws IllegalArgumentException if any of the files cannot be found or read.
     */
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

    /**
     * Reads a file and returns its contents as a string.
     *
     * @param filePath the file path of the JSON file to read.
     * @return the contents of the file as a string.
     * @throws IllegalArgumentException if the file cannot be found or read.
     */
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

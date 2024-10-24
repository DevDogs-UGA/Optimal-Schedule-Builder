package edu.uga.devdogs.sampledataparser;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.uga.devdogs.sampledataparser.deserializers.CourseMapDeserializer;
import edu.uga.devdogs.sampledataparser.deserializers.DayOfWeekListDeserializer;
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
import java.util.List;
import java.util.Map;

/**
 * The SampleDataParser class is responsible for parsing JSON data from multiple files
 * into structured Java objects. It deserializes the data for professors, courses, and building distances,
 * and returns a {@link SampleData} object containing all parsed data.
 */
public class SampleDataParser {

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
    public static SampleData parse(String professorsFilePath, String coursesFilePath, String distancesFilePath) {
        String professorsJson = readFile(professorsFilePath);
        String coursesJson = readFile(coursesFilePath);
        String distancesJson = readFile(distancesFilePath);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        List<Professor> professors = gson.fromJson(professorsJson, new TypeToken<List<Professor>>() {}.getType());

        Gson coursesGson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Professor.class, new ProfessorDeserializer(professors))
                .registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer())
                .registerTypeAdapter(new TypeToken<List<DayOfWeek>>() {}.getType(), new DayOfWeekListDeserializer())
                .registerTypeAdapter(new TypeToken<Map<String, Course>>() {}.getType(), new CourseMapDeserializer())
                .create();

        Map<String, Course> courses = coursesGson.fromJson(coursesJson, new TypeToken<Map<String, Course>>() {}.getType());

        Map<String, Map<String, Double>> distances = gson.fromJson(distancesJson, new TypeToken<Map<String, Map<String, Double>>>() {}.getType());

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

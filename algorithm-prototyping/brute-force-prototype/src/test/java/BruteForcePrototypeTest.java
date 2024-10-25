import edu.uga.devdogs.bruteforceprototype.BruteForcePrototype;
import edu.uga.devdogs.sampledataparser.SampleDataParser;
import edu.uga.devdogs.sampledataparser.records.Course;
import edu.uga.devdogs.sampledataparser.records.SampleData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BruteForcePrototypeTest {

    static BruteForcePrototype bruteForce;

    static List<Set<Course>> testCourseSets;

    @BeforeAll
    static void setUp() {
        // File paths for the sample data
        String professorsFilePath = "../src/main/resources/professors.json";
        String coursesFilePath = "../src/main/resources/courses.json";
        String distancesFilePath = "../src/main/resources/distances.json";

        // Parse sample data
        SampleData sampleData = SampleDataParser.parse(professorsFilePath, coursesFilePath, distancesFilePath);
        Map<String, Course> courses = sampleData.courses();

        // Initialize the brute force prototype with the sample data
        bruteForce = new BruteForcePrototype(sampleData);

        // Initialize the list of course sets
        testCourseSets = new ArrayList<>();

        // Add first course set for testing
        testCourseSets.add(Set.of(
                courses.get("ENGL 1101"),
                courses.get("CSCI 1302"),
                courses.get("PHYS 1211"),
                courses.get("PHYS 1211L")
        ));

        // Add second course set for testing
        testCourseSets.add(Set.of(
                courses.get("ENGL 1102"),
                courses.get("PHYS 1212"),
                courses.get("PHYS 1212L"),
                courses.get("MATH 2250")
        ));
    }

    @Test
    void testOptimize() {

    }

    @Test
    void testGenerateValidSchedules() {

    }

}

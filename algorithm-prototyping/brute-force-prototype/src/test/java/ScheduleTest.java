import edu.uga.devdogs.sampledataparser.SampleDataParser;
import edu.uga.devdogs.sampledataparser.records.Course;
import edu.uga.devdogs.sampledataparser.records.SampleData;
import edu.uga.devdogs.sampledataparser.records.Section;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScheduleTest {

    static List<Set<Section>> testSectionSets;

    @BeforeAll
    static void setUp() {
        // File paths for the sample data
        String professorsFilePath = "../src/main/resources/professors.json";
        String coursesFilePath = "../src/main/resources/courses.json";
        String distancesFilePath = "../src/main/resources/distances.json";

        // Parse sample data
        SampleData sampleData = SampleDataParser.parse(professorsFilePath, coursesFilePath, distancesFilePath);
        Map<String, Course> courses = sampleData.courses();

        // Initialize the list of test section sets
        testSectionSets = new ArrayList<>();

        // Add first section set for testing
        testSectionSets.add(Set.of(
                courses.get("ENGL 1101").sections().get(0),
                courses.get("CSCI 1302").sections().get(0),
                courses.get("PHYS 1211").sections().get(0),
                courses.get("PHYS 1211L").sections().get(0)
        ));

        // Add second section set for testing
        testSectionSets.add(Set.of(
                courses.get("ENGL 1102").sections().get(0),
                courses.get("PHYS 1212").sections().get(0),
                courses.get("PHYS 1212L").sections().get(0),
                courses.get("MATH 2250").sections().get(0)
        ));
    }

    @Test
    void testSchedule() {

    }

}

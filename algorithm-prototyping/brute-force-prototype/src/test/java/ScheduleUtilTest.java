import edu.uga.devdogs.bruteforceprototype.schedule.Schedule;
import edu.uga.devdogs.sampledataparser.SampleDataParser;
import edu.uga.devdogs.sampledataparser.records.Course;
import edu.uga.devdogs.sampledataparser.records.SampleData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScheduleUtilTest {

    static Map<String, Course> courses;
    static Map<String, Map<String, Double>> distances;

    @BeforeAll
    static void setUp() {
        // File paths for the sample data
        String professorsFilePath = "../src/main/resources/professors.json";
        String coursesFilePath = "../src/main/resources/courses.json";
        String distancesFilePath = "../src/main/resources/distances.json";

        // Parse sample data
        SampleData sampleData = SampleDataParser.parse(professorsFilePath, coursesFilePath, distancesFilePath);
        courses = sampleData.courses();
        distances = sampleData.distances();

        // Example of how to create a test schedule:
        Schedule testSchedule = new Schedule(Set.of(
                courses.get("ENGL 1101").sections().get(1),
                courses.get("CSCI 1301").sections().get(2),
                courses.get("PHYS 1211").sections().get(2),
                courses.get("PHYS 1211L").sections().get(1)
        ));


        // Test Schedule 2
        Schedule testSchedule2 = new Schedule(Set.of(
                courses.get("ENGL 1102").sections().get(0),
                courses.get("CSCI 1302").sections().get(1),
                courses.get("CHEM 1211").sections().get(0),
                courses.get("CHEM 1211L").sections().get(1)
        ));

        // Test Schedule 3
        Schedule testSchedule3 = new Schedule(Set.of(
                courses.get("CHEM 1211").sections().get(1),
                courses.get("CHEM 1211L").sections().get(2),
                courses.get("PHYS 1212").sections().get(0),
                courses.get("PHYS 1212L").sections().get(0)
        ));

        // Test Schedule 4
        Schedule testSchedule3 = new Schedule(Set.of(
                courses.get("ENGL 1102").sections().get(1),
                courses.get("PHYS 1211").sections().get(2),
                courses.get("MATH 2250").sections().get(2),
                courses.get("PHYS 1211L").sections().get(0)
        ));

        // Test Schedule 5
        Schedule testSchedule3 = new Schedule(Set.of(
                courses.get("MATH 2700").sections().get(1),
                courses.get("CHEM 1211").sections().get(0),
                courses.get("CHEM 1211L").sections().get(1),
                courses.get("ENGL 1102").sections().get(0)
        ));
    }

    @Test
    void testValidate() {

    }

    @Test
    void testComputeAverageProfessorQuality() {

    }

    @Test
    void computeMaxDistance() {

    }

    @Test
    void computeAverageIdleTime() {

    }

    @Test
    void computeOverallObjective() {

    }

}

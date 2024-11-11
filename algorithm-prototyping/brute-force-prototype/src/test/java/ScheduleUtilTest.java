import edu.uga.devdogs.bruteforceprototype.schedule.Schedule;
import edu.uga.devdogs.sampledataparser.SampleDataParser;
import edu.uga.devdogs.sampledataparser.records.Course;
import edu.uga.devdogs.sampledataparser.records.SampleData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScheduleUtilTest {

    static Map<String, Course> courses;
    static Map<String, Map<String, Double>> distances;

    static List<Schedule> validTestSchedules;
    static List<Schedule> invalidTestSchedules;

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

        // Create valid test schedules
        validTestSchedules.add(new Schedule(Set.of(
                courses.get("ENGL 1101").sections().get(1),
                courses.get("CSCI 1301").sections().get(2),
                courses.get("PHYS 1211").sections().get(2),
                courses.get("PHYS 1211L").sections().get(1)
        )));

        validTestSchedules.add(new Schedule(Set.of(
                courses.get("ENGL 1102").sections().get(0),
                courses.get("CSCI 1302").sections().get(1),
                courses.get("CHEM 1211").sections().get(0),
                courses.get("CHEM 1211L").sections().get(1)
        )));

        validTestSchedules.add(new Schedule(Set.of(
                courses.get("CHEM 1211").sections().get(1),
                courses.get("CHEM 1211L").sections().get(2),
                courses.get("PHYS 1212").sections().get(0),
                courses.get("PHYS 1212L").sections().get(0)
        )));

        validTestSchedules.add(new Schedule(Set.of(
                courses.get("ENGL 1102").sections().get(1),
                courses.get("PHYS 1211").sections().get(2),
                courses.get("MATH 2250").sections().get(2),
                courses.get("PHYS 1211L").sections().get(0)
        )));

        validTestSchedules.add(new Schedule(Set.of(
                courses.get("MATH 2700").sections().get(1),
                courses.get("CHEM 1211").sections().get(0),
                courses.get("CHEM 1211L").sections().get(1),
                courses.get("ENGL 1102").sections().get(0)
        )));

        // Create invalid test schedules

        invalidTestSchedules.add(new Schedule(Set.of(
                courses.get("ENGL 1101").sections().get(0),
                courses.get("CSCI 1301").sections().get(0),
                courses.get("PHYS 1211").sections().get(2)
        )));

        invalidTestSchedules.add(new Schedule(Set.of(
                courses.get("CHEM 1211").sections().get(0),
                courses.get("CHEM 1211L").sections().get(0),
                courses.get("MATH 2250").sections().get(0),
                courses.get("CSCI 1302").sections().get(0)
        )));

        invalidTestSchedules.add(new Schedule(Set.of(
                courses.get("MATH 2260").sections().get(0),
                courses.get("ENGL 1101").sections().get(1),
                courses.get("CSCI 1301").sections().get(0),
                courses.get("PHYS 1211").sections().get(0)
        )));

        invalidTestSchedules.add(new Schedule(Set.of(
                courses.get("PHYS 1212").sections().get(0),
                courses.get("PHYS 1212L").sections().get(2),
                courses.get("MATH 2700").sections().get(1),
                courses.get("ENGL 1101").sections().get(0)
        )));

        invalidTestSchedules.add(new Schedule(Set.of(
                courses.get("ENGL 1102").sections().get(1),
                courses.get("CSCI 1302").sections().get(2),
                courses.get("MATH 2250").sections().get(1),
                )));
    }

    @Test
    void testValidate() {
        // Assert that ScheduleUtil.validate() returns true for all schedules in validTestSchedules
        // and returns false for all schedules in invalidTestSchedules
    }

    @Test
    void testComputeAverageProfessorQuality() {
        // Compute the average professor quality for each schedule within validTestSchedules by hand
        // Assert that ScheduleUtil.computeAverageProfessorQuality() returns the expected value for each schedule
    }

    @Test
    void testComputeMaxDistance() {
        // Compute the max distance for each schedule within validTestSchedules by hand
        // Assert that ScheduleUtil.computeMaxDistance() returns the expected value for each schedule
    }

    @Test
    void testComputeAverageIdleTime() {
        // Compute the average idle time for each schedule within validTestSchedules by hand
        // Assert that ScheduleUtil.computeAverageIdleTime() returns the expected value for each schedule
    }

    @Test
    void testComputeOverallObjective() {
        // Compute the overall objective for each schedule within validTestSchedules by hand
        // Assert that ScheduleUtil.computeOverallObjective() returns the expected value for each schedule
    }

}

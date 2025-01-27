import edu.uga.devdogs.bruteforceprototype.schedule.Schedule;
import edu.uga.devdogs.bruteforceprototype.schedule.ScheduleUtil;
import edu.uga.devdogs.sampledataparser.SampleDataParser;
import edu.uga.devdogs.sampledataparser.records.Course;
import edu.uga.devdogs.sampledataparser.records.SampleData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        validTestSchedules = new ArrayList<>();
        invalidTestSchedules = new ArrayList<>();

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
                courses.get("MATH 2250").sections().get(1)
        )));
    }

    @Test
    void testValidate() {
        // Assert that ScheduleUtil.validate() returns true for all schedules in validTestSchedules
        // and returns false for all schedules in invalidTestSchedules
        for (Schedule schedule : validTestSchedules) {
            assertTrue(ScheduleUtil.validate(schedule));
        }
        for (Schedule schedule : invalidTestSchedules) {
            assertFalse(ScheduleUtil.validate(schedule));
        }
    }

    @Test
    void testComputeAverageProfessorQuality() {
        // Compute the average professor quality for each schedule within validTestSchedules by hand
        // Assert that ScheduleUtil.computeAverageProfessorQuality() returns the expected value for each schedule
        assertEquals(3.55, ScheduleUtil.computeAverageProfessorQuality(validTestSchedules.get(0)), 0.01,"The professor's average quality should be 3.425.");
        assertEquals(2.075, ScheduleUtil.computeAverageProfessorQuality(validTestSchedules.get(1)), 0.01,"The professor's average quality should be 2.075.");
        assertEquals(3.275, ScheduleUtil.computeAverageProfessorQuality(validTestSchedules.get(2)), 0.01,"The professor's average quality should be 3.275.");
        assertEquals(4.025, ScheduleUtil.computeAverageProfessorQuality(validTestSchedules.get(3)), 0.01,"The professor's average quality should be 4.025.");
        assertEquals(2.450, ScheduleUtil.computeAverageProfessorQuality(validTestSchedules.get(4)), 0.01,"The professor's average quality should be 2.450.");
    }

    @Test
    void testComputeMaxDistance() {
        // Compute the max distance for each schedule within validTestSchedules by hand
        // Assert that ScheduleUtil.computeMaxDistance() returns the expected value for each schedule

        //region Valid Schedules
        assertNotNull(validTestSchedules.get(0));
        assertEquals(14.317821063276353, ScheduleUtil.computeMaxDistance(validTestSchedules.get(0), distances));
        assertNotNull(validTestSchedules.get(1));
        assertEquals(13.92838827718412, ScheduleUtil.computeMaxDistance(validTestSchedules.get(1), distances));
        assertNotNull(validTestSchedules.get(2));
        assertEquals(14.0, ScheduleUtil.computeMaxDistance(validTestSchedules.get(2), distances));
        assertNotNull(validTestSchedules.get(3));
        assertEquals(9.486832980505138, ScheduleUtil.computeMaxDistance(validTestSchedules.get(3), distances));
        assertNotNull(validTestSchedules.get(4));
        assertEquals(15.033296378372908, ScheduleUtil.computeMaxDistance(validTestSchedules.get(4), distances));
        //endregion
    }

    @Test
    void testComputeAverageIdleTime() {
        // Compute the average idle time for each schedule within validTestSchedules by hand
        // Assert that ScheduleUtil.computeAverageIdleTime() returns the expected value for each schedule
        assertEquals(20.0, ScheduleUtil.computeAverageIdleTime(validTestSchedules.get(0)), 0.1);
        assertEquals(104.0, ScheduleUtil.computeAverageIdleTime(validTestSchedules.get(1)), 0.1);
        assertEquals(83.3, ScheduleUtil.computeAverageIdleTime(validTestSchedules.get(2)), 0.1);
        assertEquals(72.0, ScheduleUtil.computeAverageIdleTime(validTestSchedules.get(3)), 0.1);
        assertEquals(60.0, ScheduleUtil.computeAverageIdleTime(validTestSchedules.get(4)), 0.1);
    }

    @Test
    void testComputeOverallObjective() {
        // Compute the overall objective for each schedule within validTestSchedules by hand
        // Assert that ScheduleUtil.computeOverallObjective() returns the expected value for each schedule
        assertEquals(.73781537911, ScheduleUtil.computeOverallObjective(validTestSchedules.get(0), distances, new double[]{.3, .3, .4}));
        assertEquals(.58161019538, ScheduleUtil.computeOverallObjective(validTestSchedules.get(1), distances, new double[]{.2, .5, .3}));
        assertEquals(.63009935897, ScheduleUtil.computeOverallObjective(validTestSchedules.get(2), distances, new double[]{.7, .1, .2}));
        assertEquals(.7799393625, ScheduleUtil.computeOverallObjective(validTestSchedules.get(3), distances, new double[]{.4, .3, .3}));
        assertEquals(.43219670438, ScheduleUtil.computeOverallObjective(validTestSchedules.get(4), distances, new double[]{.8, .1, .1}));
}

}

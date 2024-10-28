import edu.uga.devdogs.sampledataparser.SampleDataParser;
import edu.uga.devdogs.sampledataparser.records.SampleData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;

public class SampleDataParserTest {

    private static SampleData sampleData;

    @BeforeAll
    static void setUp() {
        String professorsFilePath = "src/test/resources/professors.json";
        String coursesFilePath = "src/test/resources/courses.json";
        String distancesFilePath = "src/test/resources/distances.json";

        sampleData = SampleDataParser.parse(professorsFilePath, coursesFilePath, distancesFilePath);
    }

    @Test
    void testParsedDataNotNull() {
        assertNotNull(sampleData, "SampleData should not be null.");
        assertNotNull(sampleData.courses(), "Courses should not be null.");
        assertNotNull(sampleData.distances(), "Distances should not be null.");
    }

    @Test
    void testParsedCourses() {
        assertEquals(1, sampleData.courses().size(), "Courses array should be of length 1.");

        var course = sampleData.courses().get("ENGL 1101");
        assertEquals("ENGL 1101", course.courseCode(), "CourseCode of the first course should be 'ENGL 1101'.");
    }

    @Test
    void testParsedSections() {
        var course = sampleData.courses().get("ENGL 1101");
        assertEquals(1, course.sections().size(), "Sections array of the first course should be of length 1.");

        var section = course.sections().getFirst();
        assertEquals("ENGL 1101", section.courseCode(), "CourseCode of the first section should be 'ENGL 1101'.");
        assertEquals("25013", section.crn(), "CRN of the first section should be '25013'.");
    }

    @Test
    void testParsedProfessor() {
        var section = sampleData.courses().get("ENGL 1101").sections().getFirst();
        var professor = section.professor();

        assertEquals("Daniel Barnum", professor.name(), "The professor should be Daniel Barnum.");
        assertEquals(5, professor.quality(), "The professor should have a quality of 5.");
    }

    @Test
    void testParsedClasses() {
        var section = sampleData.courses().get("ENGL 1101").sections().getFirst();
        var _class = section.classes().getFirst();

        assertEquals("25013", _class.crn(), "CRN of the first class should be '25013'.");
        assertEquals(DayOfWeek.MONDAY, _class.days().getFirst(), "The first day should be DayOfWeek.MONDAY.");
    }

    @Test
    void testParsedDistances() {
        var distances = sampleData.distances();

        assertEquals(0.0, distances.get("Park Hall").get("Park Hall"), "The distance between 'Park Hall' and 'Park Hall' should be 0.0");
        assertEquals(1.5, distances.get("Boyd").get("Miller Plant Sciences"), "The distance between 'Boyd' and 'Miller Plant Sciences' should be 1.5");
    }
}

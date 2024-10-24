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
        String distancesFilePath = "src/test/resources/distance_matrix.json";

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
        var classData = section.classes().getFirst();

        assertEquals("25013", classData.crn(), "CRN of the first class should be '25013'.");
        assertEquals(DayOfWeek.MONDAY, classData.days().getFirst(), "The first day should be DayOfWeek.MONDAY.");
    }

    @Test
    void testParsedDistances() {
        var distances = sampleData.distances();
        assertEquals(4, distances.buildings().size(), "Buildings array should be of length 4.");
        assertEquals("Park Hall", distances.buildings().getFirst(), "The first building should be Park Hall.");

        assertEquals(4, distances.matrix().size(), "Matrix should have 4 rows.");
        assertEquals(4, distances.matrix().getFirst().size(), "Matrix should have 4 columns.");
        assertEquals(2.3, distances.matrix().get(1).get(1), 0.00001, "Matrix[1][1] should be 2.3.");
    }
}

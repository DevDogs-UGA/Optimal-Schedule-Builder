import edu.uga.devdogs.sampledataparser.SampleDataParser;
import edu.uga.devdogs.sampledataparser.records.SampleData;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SampleDataParserTest {

    @Test
    void testValidFiles() {
        String professorsFilePath = "src/test/resources/professors.json";
        String coursesFilePath = "src/test/resources/courses.json";
        String distancesFilePath = "src/test/resources/distance_matrix.json";

        SampleDataParser parser = new SampleDataParser();

        // Act : Parse the test data
        SampleData sampleData = parser.parse(professorsFilePath, coursesFilePath, distancesFilePath);

        // Assert: Check if the parsed data is as expected
        assertNotNull(sampleData,
                "SampleData should not be null.");
        assertNotNull(sampleData.courses(),
                "Courses should not be null.");
        assertEquals(1, sampleData.courses().size(),
                "Courses array should not be of length 1.");
        assertEquals("ENGL 1101", sampleData.courses().getFirst().courseCode(),
                "CourseCode of the first course should be the string \"ENGL 1101\".");
        assertEquals(1, sampleData.courses().getFirst().sections().size(),
                "Sections array of the first course should be of length 1.");
        assertEquals("ENGL 1101", sampleData.courses().getFirst().sections().getFirst().courseCode(),
                "CourseCode of the first section of the first course should be the string \"ENGL 1101\".");
        assertEquals("25013", sampleData.courses().getFirst().sections().getFirst().crn(),
                "CRN of the first section of the first course should be the string \"25013\".");
        assertEquals("Daniel Barnum", sampleData.courses().getFirst().sections().getFirst().professor().name(),
                "The professor of the first section of the first course should be Daniel Barnum");
        assertEquals(5, sampleData.courses().getFirst().sections().getFirst().professor().quality(),
                "The professor of the first section of the first course should have a quality of 5.");
        assertEquals("25013", sampleData.courses().getFirst().sections().getFirst().classes().getFirst().crn(),
                "CRN of the first class of the first section of the first course should be the string \"25013\".");
        assertEquals(DayOfWeek.MONDAY, sampleData.courses().getFirst().sections().getFirst().classes().getFirst().days().getFirst(),
                "The first day of the first class of the first section of the first course should be DayOfWeek.MONDAY.");
        assertNotNull(sampleData.distances(),
                "Distances should not be null.");
        assertNotNull(sampleData.distances().buildings(),
                "Buildings should not be null.");
        assertEquals(4, sampleData.distances().buildings().size(),
                "Buildings array should be of length 4.");
        assertEquals("Park Hall", sampleData.distances().buildings().getFirst(),
                "The first building should be Park Hall.");
        assertNotNull(sampleData.distances().matrix(),
                "Matrix should not be null.");
        assertEquals(4, sampleData.distances().matrix().size(),
                "Matrix should have 4 rows.");
        assertEquals(4, sampleData.distances().matrix().getFirst().size(),
                "Matrix should have 4 columns.");
        assertEquals(2.3, sampleData.distances().matrix().get(1).get(1), 0.00001,
                "Matrix[1][1] should be 2.3.");
    }

}

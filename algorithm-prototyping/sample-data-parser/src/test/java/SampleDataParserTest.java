import edu.uga.devdogs.SampleDataParser;
import edu.uga.devdogs.records.SampleData;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

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
        assertEquals(1, sampleData.courses().length,
                "Courses array should not be of length 1.");
        assertEquals(1, sampleData.courses()[0].sections().length,
                "Sections array of the first course should be of length 1.");
        assertEquals("Daniel Barnum", sampleData.courses()[0].sections()[0].professor().name(),
                "The professor of the first section of the first course should be Daniel Barnum");
        assertEquals(5, sampleData.courses()[0].sections()[0].professor().quality(),
                "The professor of the first section of the first course should have a quality of 5.");
        assertEquals("25013", sampleData.courses()[0].sections()[0].crn(),
                "The CRN of the first section of the first course should be the string \"25013\".");
        assertArrayEquals(new DayOfWeek[]{DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY}, sampleData.courses()[0].sections()[0].classes()[0].days(),
                "The days of the first class of the first section of the first course should be {DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY}.");
        assertNotNull(sampleData.distances(),
                "Distances should not be null.");
        assertNotNull(sampleData.distances().buildings(),
                "Buildings should not be null.");
        assertEquals(4, sampleData.distances().buildings().length,
                "Buildings array should be of length 4.");
        assertEquals("Park Hall", sampleData.distances().buildings()[0],
                "The first building should be Park Hall.");
        assertNotNull(sampleData.distances().matrix(),
                "Matrix should not be null.");
        assertEquals(4, sampleData.distances().matrix().length,
                "Matrix should have 4 rows.");
        assertEquals(4, sampleData.distances().matrix()[0].length,
                "Matrix should have 4 columns.");
        assertEquals(2.3, sampleData.distances().matrix()[1][1], 0.00001,
                "Matrix[1][1] should be 2.3.");
    }

}

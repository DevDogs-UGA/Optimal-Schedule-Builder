import edu.uga.devdogs.Course;
import edu.uga.devdogs.SampleDataParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SampleDataParserTest {

    @Test
    void testValidFiles() throws Exception {
        String professorsFilePath = "src/test/resources/professors.json";
        String coursesFilePath = "src/test/resources/courses.json";

        SampleDataParser parser = new SampleDataParser();

        // Act : Parse the test data
        Course[] courses = parser.parse(professorsFilePath, coursesFilePath);

        // Assert: Check if the parsed data is as expected
        assertNotNull(courses,
                "Courses should not be null.");
        assertTrue(courses.length > 0,
                "Courses array should not be empty.");
        assertEquals("Daniel Barnum", courses[0].sections()[0].professor().name(),
                "The professor of the first section of the first course should be Daniel Barnum");
        assertEquals(5, courses[0].sections()[0].professor().quality(),
                "The professor of the first section of the first course should have a quality of 5.");
    }

}

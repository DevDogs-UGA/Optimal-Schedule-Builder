package edu.uga.devdogs.course_information.CourseSection;

import edu.uga.devdogs.course_information.Course.Course;
import edu.uga.devdogs.course_information.Class.ClassEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseSectionRepositoryTest {

    @Autowired
    private CourseSectionRepository courseSectionRepository;

    private CourseSection testCourseSection;
    private Course testCourse;

    @BeforeEach
    void setUp() {
        // Creating a mock course
        testCourse = new Course();
        testCourse.setSubject("CSCI");

        // Creating a mock class entity
        ClassEntity classEntity = new ClassEntity();
        classEntity.setStartTime("10:00 AM");
        classEntity.setEndTime("11:00 AM");

        // Creating a mock CourseSection
        testCourseSection = new CourseSection(
                12345, 1, 'O', 3.0, 3.0, "Dr. Smith",
                202401, 30, 10, 2024, testCourse, Arrays.asList(classEntity)
        );

        courseSectionRepository.save(testCourseSection);
    }

    @AfterEach
    void tearDown() {
        courseSectionRepository.deleteAll();
    }

    // ✅ 1. Test Finding Course Section by CRN
    @Test
    @Order(1)
    @Rollback
    void testFindByCrn() {
        CourseSection found = courseSectionRepository.findByCrn(12345);
        assertNotNull(found);
        assertEquals("Dr. Smith", found.getInstructor());
        assertEquals(30, found.getClassSize());
    }

    // ✅ 2. Test Finding Non-Existent CRN (Should Return Null)
    @Test
    @Order(2)
    @Rollback
    void testFindByCrnNotFound() {
        CourseSection found = courseSectionRepository.findByCrn(99999);
        assertNull(found);
    }

    // ✅ 3. Test Finding Course Sections by Instructor
    @Test
    @Order(3)
    @Rollback
    void testFindAllByInstructor() {
        List<CourseSection> sections = courseSectionRepository.findAllByInstructor("Dr. Smith");
        assertFalse(sections.isEmpty());
        assertEquals(1, sections.size());
        assertEquals("Dr. Smith", sections.get(0).getInstructor());
    }

    // ✅ 4. Test Finding Course Sections for Non-Existent Instructor
    @Test
    @Order(4)
    @Rollback
    void testFindAllByInstructorNotFound() {
        List<CourseSection> sections = courseSectionRepository.findAllByInstructor("Dr. Doe");
        assertTrue(sections.isEmpty());
    }

    // ✅ 5. Test Getting Courses by Subject
    @Test
    @Order(5)
    @Rollback
    void testGetCoursesBySubject() {
        List<CourseSection> sections = courseSectionRepository.getCoursesBySubject("CSCI");
        assertFalse(sections.isEmpty());
        assertEquals("CSCI", sections.get(0).getCourse().getSubject());
    }

    // ✅ 6. Test Finding Course Sections by Time
    @Test
    @Order(6)
    @Rollback
    void testFindAllCourseSectionsByTime() {
        List<CourseSection> sections = courseSectionRepository.findAllCourseSectionsByTime("10:30 AM");
        assertFalse(sections.isEmpty());
        assertEquals(1, sections.size());
    }

    // ✅ 7. Test Finding Course Sections for Invalid Time
    @Test
    @Order(7)
    @Rollback
    void testFindAllCourseSectionsByInvalidTime() {
        List<CourseSection> sections = courseSectionRepository.findAllCourseSectionsByTime("5:00 PM");
        assertTrue(sections.isEmpty());
    }

    // ✅ 8. Test Creating a Course Section with Null Instructor (Constraint Violation)
    @Test
    @Order(8)
    @Rollback
    void testSaveCourseSectionWithNullInstructor() {
        CourseSection invalidSection = new CourseSection(
                54321, 2, 'O', 3.0, 3.0, null,
                202401, 30, 10, 2024, testCourse, null
        );

        assertThrows(DataIntegrityViolationException.class, () -> {
            courseSectionRepository.saveAndFlush(invalidSection);
        });
    }

    // ✅ 9. Test Saving a Duplicate CRN (If CRN is Unique in DB Schema)
    @Test
    @Order(9)
    @Rollback
    void testSaveDuplicateCrn() {
        CourseSection duplicateSection = new CourseSection(
                12345, 2, 'O', 3.0, 3.0, "Dr. Jones",
                202401, 25, 5, 2024, testCourse, null
        );

        courseSectionRepository.save(duplicateSection);
        CourseSection found = courseSectionRepository.findByCrn(12345);
        
        assertNotNull(found);
        assertEquals("Dr. Smith", found.getInstructor()); // Ensuring original is retained
    }

    // ✅ 10. Test Retrieving an Empty Database
    @Test
    @Order(10)
    @Rollback
    void testEmptyDatabase() {
        courseSectionRepository.deleteAll();
        List<CourseSection> sections = courseSectionRepository.findAll();
        assertTrue(sections.isEmpty());
    }
}

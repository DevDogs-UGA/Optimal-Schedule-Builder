package edu.uga.devdogs.course_information.Course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // Find a course by its courseId
    Optional<Course> findByCourseId(Long courseid);
  
    // Find all courses by their title
    List<Course> findAllByTitle(String title);

    // Find courses by subject
    List<Course> findAllBySubject(String subject);

    // Find courses by department
    List<Course> findByDepartment(String department);

    // Find all courses by their subject and course number
    Course findBySubjectAndCourseNumber(String subject, String courseNumber);
    

    // Find all courses by the term they're offered in
    @Query("SELECT c FROM Course c WHERE :semester MEMBER OF c.semesters")
    List<Course> findAllBySemester(@Param("semester") String semesters);

    // Get course info by course ID
    List<Course> getCourseInfoByCourseId(Long courseId);

    // Find prerequisites by course ID
    List<Course> findPrerequisitesByCourseId(Long courseId);

    // Find corequisites by course ID
    List<Course> findCorequisitesByCourseId(Long courseId);
}

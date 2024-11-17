package edu.uga.devdogs.course_information.CourseSection;

import edu.uga.devdogs.course_information.Course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseSectionRepository extends JpaRepository<CourseSection, Long>{

    // This will get a list of courses by their major
    @Query("SELECT cs FROM courseSection cs WHERE cs.course.subject = ?1")
    List<Course> getCoursesBySubject(String subject);

    // This will get a list of courseSections by the instructor
    // @Query("SELECT cs FROM courseSection cs WHERE cs.instructor = ?1")
    List<CourseSection> findAllByInstructor(String instructor);
    

    // This will find a course by its CRN
    @Query("SELECT crn from courseSection cs WHERE cd.course.suject = ?1")
    List<Course> getCourseByCRN(int crn);

}


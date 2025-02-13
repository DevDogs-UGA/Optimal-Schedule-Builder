package edu.uga.devdogs.course_information.CourseSection;

import edu.uga.devdogs.course_information.Course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseSectionRepository extends JpaRepository<CourseSection, Long>{

    // This will get a list of courses by their major
    @Query("SELECT cs FROM courseSection cs WHERE cs.course.subject = ?1")
    List<Course> getCoursesBySubject(String subject);
    
    // This will get a list of course sections that match the time range
    @Query("SELECT cs FROM CourseSection cs JOIN cs.Classes c " + 
            "WHERE c.startTime <= :time AND c.endTime >= :time")
    List<CourseSection> findCourseSectionsByTime(java.sql.Time time);

    // This will find a course by its CRN
    //@Query("SELECT crn from courseSection cs WHERE cs.crn = ?1")
    CourseSection findBycrn(int crn);
}


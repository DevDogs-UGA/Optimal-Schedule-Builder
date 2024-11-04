package edu.uga.devdogs.course_information.CourseSection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.uga.devdogs.course_information.webscraping.Course;

import java.util.List;

public interface CourseSectionRepository extends JpaRepository<CourseSection, Long>{

    //This will get a list of courses by their major
    @Query("SELECT cs FROM courseSection cs WHERE cs.course.subject = ?1")
    List<Course> getCoursesBySubject(String subject);    
    
}


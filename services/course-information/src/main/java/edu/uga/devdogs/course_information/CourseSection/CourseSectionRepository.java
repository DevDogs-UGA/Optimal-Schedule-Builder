package edu.uga.devdogs.course_information.CourseSection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.uga.devdogs.course_information.webscraping.Course;

import java.util.List;

public interface CourseSectionRepository extends JpaRepository<CourseSection, Long>{

    //This will get a list of courses by their major
    @Query("SELECT cs FROM courseSection cs WHERE cs.course.subject = ?1")
    List<CourseSection> getCoursesBySubject(String subject);    

    @Query("SELECT cs FROM CourseSection cs WHERE cs.term = ?1 AND cs.year = ?2")
    List<CourseSection> getCoursesByTermAndYear(int term, int year);

    @Query("Select c FROM CourseSection cs WHERE cs.professor = ?1")
    List<Course> fetchCoursesByProfessor(String professor);
}

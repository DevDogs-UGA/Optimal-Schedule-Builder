package edu.uga.devdogs.course_information.Course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long>{

    //This will get a course by its unique ID
    Course getById(Long id);

    //@Query("select u from Course where u.title = ?1")
    //Course getByTitle(String title);

    //This will get a list of Courses by their subject.
    List<Course> findBySubject(String subject);
    
    // Find courses by Athena Name
    List<Course> findByTitle(String Title);

    List<Course> findAllBySubject(String subject);   
    
    //this will get a list of courses by the term they're offered in 
    List<Course> findAllBySemesterCourseOffered(String semesterCourseOffered);
}

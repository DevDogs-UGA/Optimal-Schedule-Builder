package edu.uga.devdogs.course_information.Course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long>{

    //This will get a course by its unique ID
    Course getById(Long id);

    // This will get a course by its title
    Course getByTitle(String title);

    //This will get a list of Courses by their subject.
    List<Course> findBySubject(String subject);
    
    // Find courses by Athena Name
    List<Course> findByTitle(String Title);

    //This will get a list of courses by their major
    //@Query("SELECT cs FROM courseSection cs WHERE cs.course.subject = ?1")
    List<Course> findAllBySubject(String subject);

    // Gets all courses by a certain credit hour
    // The creditHours placeholder in the query is linked to the method parameter using @Param
    @Query("SELECT c FROM Course c JOIN c.courseSections cs WHERE cs.creditHoursLow <= :creditHours AND cs.creditHoursHigh >= :creditHours")
    List<Course> getCoursesByCreditHours(@Param("creditHours") double creditHours);
}

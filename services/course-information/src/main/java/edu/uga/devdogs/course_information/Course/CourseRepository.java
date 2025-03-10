package edu.uga.devdogs.course_information.Course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long>{

    //This will get a course by its unique ID
    Optional<Course> findById(Long id);

    //This will get a list of Courses by their subject.
    List<Course> findAllBySubject(String subject); 
    
    //This will get a list of courses by the term they're offered in
    //The default findAll isn't compatible with a list as a parameter.
    @Query("SELECT c FROM Course c WHERE :semester MEMBER OF c.semesters")
    List<Course> findAllBySemester(@Param("semester") String semesters);

    // Gets all courses by a certain credit hour
    @Query("SELECT c FROM Course c JOIN c.courseSections cs WHERE cs.creditHoursLow <= :creditHours AND cs.creditHoursHigh >= :creditHours")
    List<Course> findAllCoursesByCreditHours(double creditHours);

    //finds all courses by a given title. 
    List<Course> findAllByTitle(String title);

}

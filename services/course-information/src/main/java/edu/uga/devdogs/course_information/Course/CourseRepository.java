package edu.uga.devdogs.course_information.Course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long>{

    //This will get a course by its unique ID
    Course findById(Long id);

    // This will get a course by its title
    Course findByTitle(String title);

    //This will get a list of Courses by their subject.
    List<Course> findBySubject(String subject);

    // This will get a list of courses by their course number
    List<Course> findByCourseNumber(String courseNumber);

    // Find course by department
    List<Course> findByDepartment(String department);

    // Gets course info by course ID
    List<Course> getCourseInfoById(Long id);

    // Gets prerequisites by course ID
    List<Course> getPrerequisites(Long id);

    // Gets corequisites by course ID
    List<Course> getCorequisites(Long id);
    
    // Find courses by Athena Name
    List<Course> findAllByTitle(String Title);

    //This will get a list of courses by their major
    //@Query("SELECT cs FROM courseSection cs WHERE cs.course.subject = ?1")
    List<Course> findAllBySubject(String subject);

    // Gets all courses by a certain credit hour
    @Query("SELECT c FROM Course c JOIN c.courseSections cs WHERE cs.creditHoursLow <= :creditHours AND cs.creditHoursHigh >= :creditHours")
    List<Course> getCoursesByCreditHours(double creditHours);

    
    //This will get a list of courses by the term they're offered in
    //The default findAll isn't compatible with a list as a parameter.
    @Query("SELECT c FROM Course c WHERE :semester MEMBER OF c.semesters")
    List<Course> findAllBySemester(@Param("semester") String semesters);
    List<Course> findAllCoursesByCreditHours(double creditHours);
}

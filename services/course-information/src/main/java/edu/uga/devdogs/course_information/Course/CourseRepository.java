package edu.uga.devdogs.course_information.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Long>{

    //This will get a course by its unique ID
    Course getById(Long id);

    //@Query("select u from Course where u.title = ?1")
    //Course getByTitle(String title);
}

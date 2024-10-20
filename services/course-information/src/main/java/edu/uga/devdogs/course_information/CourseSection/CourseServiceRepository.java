package edu.uga.devdogs.course_information.CourseService;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CourseServiceRepository extends JpaRepository<CourseService, Long>{

    //This will get a course by it's subject
    @Query("select u from courseSection where u.subject = ?1")
    List course<> getCoursesByMajor()
    

    //@Query("select u from Course where u.title = ?1")
    //Course getByTitle(String title);
}

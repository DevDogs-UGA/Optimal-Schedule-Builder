package edu.uga.devdogs.course_information.CourseSection;

import edu.uga.devdogs.course_information.Course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseSectionRepository extends JpaRepository<CourseSection, Long>{

    
}


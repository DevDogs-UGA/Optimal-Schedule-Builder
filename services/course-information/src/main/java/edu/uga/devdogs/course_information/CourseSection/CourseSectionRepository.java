package edu.uga.devdogs.course_information.CourseSection;

import edu.uga.devdogs.course_information.Course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseSectionRepository extends JpaRepository<CourseSection, Long>{

    //This will get a list of courses by their major
    @Query("SELECT cs FROM CourseSection cs WHERE cs.course.subject = :subject")
    List<CourseSection> getCoursesBySubject(@Param("subject") String subject);

    // This will find a course by its CRN
    // @Query("SELECT crn from courseSection cs WHERE cd.course.suject = ?1")
    // List<Course> getCourseByCrn(int crn);

    // This will get a list of courseSections by the instructor
    // @Query("SELECT cs FROM courseSection cs WHERE cs.instructor = ?1")
    List<CourseSection> findAllByInstructor(String instructor);
    
    // This will get a list of course sections that match the time range
    @Query("SELECT cs FROM CourseSection cs JOIN cs.classEntity c WHERE c.startTime <= :time AND c.endTime >= :time")
    List<CourseSection> findAllCourseSectionsByTime(@Param("time") String time);

    // This will find a course by its CRN
    //@Query("SELECT crn from courseSection cs WHERE cs.crn = ?1")
    CourseSection findByCrn(int crn);


}


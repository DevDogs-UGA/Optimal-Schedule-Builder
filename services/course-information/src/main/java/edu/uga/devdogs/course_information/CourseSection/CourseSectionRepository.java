package edu.uga.devdogs.course_information.CourseSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseSectionRepository extends JpaRepository<CourseSection, Integer>{

    //This will get a list of courses by their major   
    // THIS NEEDS TO BE FIXED, JPA THORWS AN ERROR WITH THE METHOD AS IS
    // @Query("SELECT cs FROM courseSection cs WHERE cs.course.subject = ?1")
    // List<CourseSection> getCoursesBySubject(String subject);    

    @Query("SELECT cs FROM CourseSection cs WHERE cs.crn = ?1")
    CourseSection getSectionByCrn(int courseCrn);
}
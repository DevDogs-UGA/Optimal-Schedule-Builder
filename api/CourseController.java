import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class CourseController {
    /**
     * Get all courses.
     * 
     * @return ResponseEntity containing the list of courses
     */

    /**
     * Get the exact course that has a specific Athena name
     * 
     * @param athenaName
     * @return ResponseEntity containing the course with the specific athena name
     */

    /**
     * Get course CRNs by time slot
     * 
     * @param time Time slot that a class needs to be in
     * @return ResponseEntity containing classes adhering to the time slot
     */

    /**
     * Get courses according to the given CRN number
     * 
     * @param crn The CRN number of the course needed
     * @return ResponseEntity containing the course that has the exact CRN number.
     */

     /**
      * Get courses according to professor
      * 
      * @param prof Professor that relates to the courses needed
      * @return ResponseEntity containing the lists of courses taught by the professor
      */

    /**
     * Get all courses according to requirements
     * 
     * @param req requirements for the course
     * @return ResponseEntity containing courses according to requirements
     */
}

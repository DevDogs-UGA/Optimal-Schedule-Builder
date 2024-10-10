package edu.uga.devdogs.course_information.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles the Course-Information details.
 * Provides courses based on parameters like major, professor,
 * CRN, time slot, and athena name.
 */
@RestController
@RequestMapping("/api/course_information")
public class CourseInfoController {


    /**
     * GET endpoint to handle GET requests to get a list of courses by major. It takes
     * a String containing the name of a major as a parameter, and it returns a list of
     * {@code Course} objects associated with that major.
     *
     * <p>Since we haven't implemented the {@code Course} class yet, this function
     * will not compile.</p>
     *
     * @param major The major for which the user wants to get a list of courses.
     * 
     * @return A list of {@code Course} objects associated with the given major.
     */
    @GetMapping("/courses-by-major")
    public List<Course> getCoursesByMajor(@RequestParam(name="major", required="true") String major) {
        return getCourseList(major);
    }

}
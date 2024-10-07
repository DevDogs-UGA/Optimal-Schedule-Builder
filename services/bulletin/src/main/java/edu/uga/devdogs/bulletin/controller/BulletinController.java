package edu.uga.devdogs.bulletin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for handling requests related to the UGA Bulletin service.
 * Provides information on course ID, course title, course description,
 * and semesters offered.
 */
@RestController
@RequestMapping("/api/bulletin")
public class BulletinController {

    /**
     * Retrieves course information based on the provided course ID.
     *
     * @param courseId The ID of the course to retrieve. (e.g., "CSCI-1301")
     * @return A "Course" List containing the course information (ID, title, description, semester).
     */
    @GetMapping("/course")
    public List<Course> getCourseInfo(@RequestParam String courseId) {
        // TODO: Replace with actual service layer calls and implement the Course class
        List<Course> courseList = new ArrayList<>();

        return courseList;
    }

    /**
     * Enum for special types of courses.
     * HONORS: Honors course
     * LAB: Course with a lab component
     * ONLINE: Online course
     */
    public enum extras {
        HONORS,
        LAB,
        ONLINE
    }

    /**
     * Retrieves the special type of a course based on its CRN.
     * 
     * @param crn The Course Registration Number of the course.
     * @return The special type of the course, which can be HONORS, LAB, or ONLINE.
     * @see extras
     */
    @GetMapping("/course/crn/extras")
    public extras isHonorsLabOnline(String crn) {
        return getExtrasForCRN(crn);
    }

    // Other endpoints related to Bulletin data could be added here
}

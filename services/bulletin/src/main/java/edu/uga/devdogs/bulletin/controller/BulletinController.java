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

    // Other endpoints related to Bulletin data could be added here

    /**
     * Retrieves a list of courses based on the specified parameters.
     *
     * @author Raghav Vikramprabhu
     *
     * @param creditHours The required number of credit hours for the courses.
     * @param majorCode   The optional major code (e.g., "CSCI").
     * @param classLevel  The optional class level (e.g., 4000).
     * @return A list of courses that match the given criteria.
     */
    @GetMapping("/courses")
    public List<Course> getCourses(
            @RequestParam("creditHours") int creditHours,
            @RequestParam(value = "majorCode", required = false) String majorCode,
            @RequestParam(value = "classLevel", required = false) Integer classLevel
    ) {
        // Placeholder return to avoid compilation error.
        // List<Course> courses = fetchCourses(creditHours, majorCode, classLevel);
        return List.of(new Course());  // Replace with actual data once implemented
    }

    // Uncomment when implementing the function
    // private List<Course> fetchCourses(int creditHours, String majorCode, Integer classLevel) {
    //     // Function implementation logic goes here.
    // }
}

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
     * Retrieves course information based on the given term, like Fall or Summer.
     *
     * @param term The term for which the course has to be in.
     * @return A "Course" List containing the course information from a specific term.
     */
    @GetMapping("/course")
    public List<Course> getCourseByTerm(@RequestParam String term) {
        List<Course> courseList = new ArrayList<>();
        //courseList = courseTerm(term);
        //the above is a placeholder function that returns a list of courses required by term.
    }

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
}

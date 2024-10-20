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
    @Operation(summary = "Get courses by term", description = "Retrieves courses based on the given term")
    @ApiResponse(value = {
        @ApiResponse(responseCode = "200", description = "Course found"),
        @ApiResponse(responseCode = "400", description = "Invalid term"),
        @ApiResponse(responseCode = "404",description = "Course not found")
    })
    @GetMapping("/term")
    public List<Course> getCourseByTerm(@RequestParam(value = "term", required = true) String term) {
        
        if(term.isEmpty()){
            return ResponseEntity.badRequest().body(null); //Returns 400 if parameter isn't given
        }

        try {
            List<Course> courseList = fetchCourseByTerm(term); //fetches the courses based on term

            if(courseList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(courseList); //Checks and returns 404 error if courseList information is empty
            }

            return ResponseEntity.ok(courseList); //Returns course information if everything is correct
        } catch (Exception e) {
            //Catches any server problems or exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
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

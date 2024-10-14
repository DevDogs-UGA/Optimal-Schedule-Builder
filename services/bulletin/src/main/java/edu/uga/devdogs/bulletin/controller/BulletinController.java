package edu.uga.devdogs.bulletin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
     * Retrieves a list of sections that matches the requirements given.
     * 
     * @param requirement The string name for a requirement
     * @return A list of courses that fufill the requirement
     */
    @Operation(summary = "get courses by requirement", description = "Retrieves a list of course objects with the given requirement fufilled")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Course found"),
        @ApiResponse(responseCode = "400", description = "Invalid requirement"),
        @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @GetMapping("/requirement")
    @Tag(name="bulletin")
    public ResponseEntity<List<Course>> getRequirementCourses(@RequestParam("requirement") String requirement) {
        //return 400 for empty requirement
        if requirement.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            List<Course> courses = getCoursesByRequirement(requirement);
            if (courseList == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                // Return 404 if no courses are found
            }
            // Return courses if found
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            // Return 500 if a server error occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

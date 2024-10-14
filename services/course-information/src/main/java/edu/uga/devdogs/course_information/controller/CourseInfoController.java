package edu.uga.devdogs.course_information.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

/**
 * Controller that handles the Course-Information details.
 * Provides courses based on parameters like major, professor,
 * CRN, time slot, and athena name.
 */
@RestController
@RequestMapping("/api/courseInformation")
public class CourseInfoController {

    /**
     * Retrieves a list of courses for a given major
     * 
     * @param major The major identifier for which to select courses (e.g. CSCI)
     * @return a list of Course objects matching the given major
     */
    @Operation(summary = "get courses by major", description = "Retrieves a list of course objects with the given major identifier.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Course found"),
        @ApiResponse(responseCode = "400", description = "Invalid major"),
        @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @GetMapping("/coursesByMajor")
    @Tag(name="course-information")
    public ResponseEntity<List<Course>> getCourseList(@RequestParam String major) {

        //return 400 for empty major
        if (major.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            //Call method to get course list by major
            List<Course> courseList = getCoursesByMajor(major);

            //Check if the above method call returned null
            if (courseList == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //Return 404 if no courses are found
            }

            //Return the courses if found
            return ResponseEntity.ok(courseList);

        } catch (Exception e) {

            //Return 500 if a server error occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    
}
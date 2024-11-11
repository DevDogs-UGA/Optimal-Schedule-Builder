package edu.uga.devdogs.bulletin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.uga.devdogs.bulletin.database.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Collections;
import java.util.List;

/**
 * Controller for handling requests related to the UGA Bulletin service.
 * Provides information on course ID, course title, course description,
 * and semesters offered.
 */
@RestController
@RequestMapping("/api/bulletin")
@Tag(name = "Bulletin API", description = "API for accessing UGA Bulletin course information")
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
    @Operation(summary = "Get course information by course ID", description = "Retrieves course information based on the provided course ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Course found"),
        @ApiResponse(responseCode = "400", description = "Invalid course ID"),
        @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @GetMapping("/getCourseById")
    public ResponseEntity<Course> getCourseInfo(@RequestParam(value = "courseId") String courseId) {

        // Return 400 for empty courseId
        if (courseId.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            // Call method to get course by ID
            Course course = getCourseByID(courseId);

            // Check if the course is not found (null)
            if (course == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if no course is found
            }

            // Return the course if found
            return ResponseEntity.ok(course);

        } catch (Exception e) {

            // Return 500 if a server error occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    /**
     * Retrieves a list of courses based on the specified parameters. For example, you can edit this function to query
     * with your required parameters such as credit Hours and add on additional optional ones like,
     * "Give me all the 4 credit hours CSCI classes"
     *
     * @author Raghav Vikramprabhu
     *
     * @param creditHours The required number of credit hours for the courses. REQUIRED PARAMETER
     * @param majorCode   The optional major code (e.g., "CSCI"). OPTIONAL PARAMETER
     * @param classLevel  The optional class level (e.g., 4000). OPTIONAL PARAMETER
     * @return A list of courses that match the given criteria.
     */
    @Operation(summary = "Retrieves a list of courses based on major and class level", description = "Retrieves a list of course objects using major code 'CSCI' and/or class level")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Courses found"),
        @ApiResponse(responseCode = "400", description = "Invalid major code"),
        @ApiResponse(responseCode = "404", description = "Courses not found")
    })
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses(
            @RequestParam(value = "creditHours") int creditHours,
            @RequestParam(value = "majorCode", required = false) String majorCode,
            @RequestParam(value = "classLevel", required = false) Integer classLevel
    ) {
        // Validation for creditHours (if negative or invalid)
        if (creditHours <= 0 || creditHours > 4) {
            return ResponseEntity.badRequest().body(null); // Return 400 for bad request
        }
        
        try {
            // Fetch the courses based on provided filters
            List<Course> courses = fetchCourses(creditHours, majorCode, classLevel);
            
            // Check if no courses are found
            if (courses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(courses); // Return 404 if no courses found
            }

            // Return the list of courses with a 200 OK response
            return ResponseEntity.ok(courses);

        } catch (Exception e) {
            // Handle any exceptions and return an internal server error response
            // You might want to log the error for debugging purposes
            // logger.error("Error fetching courses", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 if server error occurs
        }
    }


    /**
     * Retrieves co-requisites for a given course ID or CRN.
     *
     * @param courseId The ID of the course to retrieve co-requisites for. (optional)
     * @param crn The CRN of the course to retrieve co-requisites for. (optional)
     * @return A list of course objects that are co-requisites for the given course.
     */
    @Operation(summary = "Get coreqs by course ID", description = "Retrieves co-requisites based on the provided course ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Course found"),
        @ApiResponse(responseCode = "400", description = "Invalid course ID"),
        @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @GetMapping("/course/coreqs")
    public ResponseEntity<List<Course>> getCoReqs(
            @RequestParam(value = "courseId", required = false) String courseId,
            @RequestParam(value = "crn", required = false) String crn
    ) {
        // Check if both courseId and crn are null or empty
        if ((courseId == null || courseId.isEmpty()) && (crn == null || crn.isEmpty())) {
            return ResponseEntity.badRequest().body(null); // Return 400 if neither parameter is provided
        }

        try {
            // Fetch co-requisite courses using the courseId or crn
            List<Course> coReqs = fetchCoReqs(courseId, crn); // Replace with actual method to fetch co-requisites

            // If no co-requisites found, return 404
            if (coReqs.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(coReqs);
            }

            // Return the list of co-requisite courses
            return ResponseEntity.ok(coReqs);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    /**
     * Retrieves pre-requisites for a given course ID or CRN.
     *
     * @param courseId The ID of the course to retrieve pre-requisites for. (optional)
     * @param crn The CRN of the course to retrieve pre-requisites for. (optional)
     * @return A list of course objects that are pre-requisites for the given course.
     */
    @Operation(summary = "Get prereqs by course ID", description = "Retrieves pre-requisites based on the provided course ID or CRN.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Course found"),
        @ApiResponse(responseCode = "400", description = "Invalid course ID"),
        @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @GetMapping("/course/prereqs")
    public ResponseEntity<List<Course>> getPreReqs(
            @RequestParam(value = "courseId", required = false) String courseId,
            @RequestParam(value = "crn", required = false) String crn
    ) {
        // Check if both courseId and crn are null or empty
        if ((courseId == null || courseId.isEmpty()) && (crn == null || crn.isEmpty())) {
            return ResponseEntity.badRequest().body(null); // Return 400 if neither parameter is provided
        }

        try {
            // Fetch pre-requisite courses using the courseId or crn
            List<Course> preReqs = fetchPreReqs(courseId, crn); // Replace with actual method to fetch pre-requisites

            // If no pre-requisites found, return 404
            if (preReqs.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(preReqs);
            }

            // Return the list of pre-requisite courses
            return ResponseEntity.ok(preReqs);

        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    /**
     * Gets the special types of a course (honors/lab/online) from a CRN.
     * @param crn The CRN of the course to find the special types.
     * @return A list of Strings that correspond the the special types of that course.
     */
    @Operation(summary = "Get special course type by crn", description = "Retrieves if the section is honors, online, or lab based on given crn")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Course found"),
        @ApiResponse(responseCode = "400", description = "Invalid course CRN"),
        @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @GetMapping("/course/specialCourseTypes")
    public ResponseEntity<List<String>> getSpecialCourseTypesFromCRN(
        @RequestParam(value = "crn", required = true) String crn
    ) {
        if (crn == null || crn.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        try {

            List<String> specialCourseTypes = fetchSpecialCourseTypes(crn); // Changed method name to fetchSpecialCourseTypes

            if (specialCourseTypes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(specialCourseTypes);
            } 

            return ResponseEntity.ok(specialCourseTypes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList("An error occurred while fetching course types."));
        }
    }

    /**
     * Gets course sections from class time and optionally class name
     * @param timeSlot The name of the course to retrieve course sections (required)
     * @param crn The crn course to retrieve course sections (optional)
     * @return A list of Strings that correspond the the special types of that course.
     */
    @GetMapping("/course/sections")
    public ResponseEntity<List<String>> getCourseSections(
        @RequestParam(value = "timeSlot", required = true) String timeSlot,
        @RequestParam(value = "crn", required = false) String crn
    ) {
        if ((timeSlot == null || timeSlot.isEmpty()) && (crn == null || crn.isEmpty())) {
            return ResponseEntity.badRequest().body(null); // Return 400 if neither parameter is provided
        }

        try {
            List<String> courseSections = fetchCourseSection(timeSlot, crn);

            if (courseSections.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(courseSections);
            }

            return ResponseEntity.ok(courseSections);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList("An error occurred while fetching course sections."));

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
        if (requirement.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            List<Course> courses = getCoursesByRequirement(requirement);
            if (courses.isEmpty()) {
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


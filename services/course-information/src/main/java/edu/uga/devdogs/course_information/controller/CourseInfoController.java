package edu.uga.devdogs.course_information.controller;

import edu.uga.devdogs.course_information.Building.Building;
import edu.uga.devdogs.course_information.Course.Course;
import edu.uga.devdogs.course_information.CourseSection.CourseSection;
import edu.uga.devdogs.course_information.service.CourseInformationService;
import edu.uga.devdogs.course_information.Professor.Professor;
import edu.uga.devdogs.course_information.Professor.ProfessorRepository;
import edu.uga.devdogs.course_information.exceptions.ProfessorNotFoundException;


import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Controller that handles the Course-Information details.
 * Provides courses based on parameters like major, professor,
 * CRN, time slot, and athena name.
 */
@Tag(name="Course Information API", description="Uses the Course PDF to provide detailed course data")
@RestController
@RequestMapping("/api/courseInformation")
public class CourseInfoController {

    //inject our Course Information PDFs microservice into our REST controller
    private final CourseInformationService courseInformationService;

    @Autowired
    public CourseInfoController(CourseInformationService courseInformationService) {
        this.courseInformationService = courseInformationService;
    }

    /**
     * Returns the details of a specified CRN.
     *
     * @param crn The CRN of the section
     * @return returns a section object for the CRN
     */
    @Operation(summary = "get section by crn", description = "Retrieves a section from the given CRN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Section found"),
            @ApiResponse(responseCode = "400", description = "Invalid CRN"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/section-by-crn")
    @Tag(name="course-information")
    public ResponseEntity<CourseSection> getSectionDetailsByCrn(@RequestParam String crn) {

        //return 400 for empty CRN
        if (crn.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            //Call method to get section details
            CourseSection sectionDetails = courseInformationService.getSectionDetailsByCrn(crn);

            //Return the section if found
            return ResponseEntity.ok(sectionDetails);

        } catch (Exception e) {

            //Return 500 if a server error occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

   
    /**
     * Asks for list of course information that relates to the 
     * @param professor given.
     * 
     * @param professor name of the professor teaching the course
     * @return course information list that's related to the given professor.
     */
    @Operation(summary = "get list of courses by professor", description = "Retrieces course information relating to the professor.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Section found"),
        @ApiResponse(responseCode = "400", description = "Invalid CRN"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/professor")
    @Tag(name="course-information")
   public ResponseEntity<List<CourseSection>> getCourseByProfessor(@RequestParam(value = "professor",required = true) String professor){
        
     if(professor == null || professor.isEmpty()){
          return ResponseEntity.badRequest().body(null); //Returns 400 if the parameter is not provided
     }

     try {
          List<CourseSection> courseInfo = courseInformationService.getCourseSectionsByProfessor(professor); //Fetches course information based on professor
          return ResponseEntity.ok(courseInfo);
     } catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
     }
   } 
    


    /**
     * Retrieves a list of courses for a given major
     * 
     * @param major The major identifier for which to select courses (e.g. CSCI)
     * @return a list of Course objects matching the given major
     */
    @Operation(summary = "Get courses by major", description = "Retrieves a list of course objects with the given major identifier.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Section found"),
        @ApiResponse(responseCode = "400", description = "Invalid CRN"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/coursesByMajor")
    @Tag(name="course-information")
    public ResponseEntity<List<Course>> getCoursesByMajor(@RequestParam String major) {

        //return 400 for empty major
        if (major.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            //Call method to get course list by major
            List<Course> courseList = courseInformationService.getCoursesByMajor(major);

            //Return the courses if found
            return ResponseEntity.ok(courseList);

        } catch (Exception e) {

            //Return 500 if a server error occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Retrieves a list of all academic subjects at UGA .
     *
     * @return List of all available subjects as strings
     */
    @Operation(summary = "Get all subjects", description = "Retrieves a list of all academic subjects at UGA.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subjects found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/subjects")
    @Tag(name="course-information")
    public ResponseEntity<List<String>> getAllSubjects() {

        try {

            List<String> subjects = courseInformationService.getAllSubjects();

            // Return the list of subject strings if found
            return ResponseEntity.ok(subjects);

        } catch (Exception e) {

            // Return 500 if a server error occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Retrieves a list of all CRNs for classes at UGA.
     *
     * @return List of all available crns as integers
     */
    @Operation(summary = "Get all CRNs", description = "Retrieves a list of all CRNs for classes at UGA.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CRNs found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/getAllCRNs")
    @Tag(name="course-information")
    public ResponseEntity<List<Integer>> getAllCRNs() {

        try {

            List<Integer> CRNs = courseInformationService.getAllCRNs();

            // Return the list of subject strings if found
            return ResponseEntity.ok(CRNs);

        } catch (Exception e) {

            // Return 500 if a server error occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Retrieves coordinates associated with a particular building code of a building at UGA.
     *
     * @param buildingNumber Building number stored as a string
     * @return List of all available subjects as strings
     */
    @Operation(summary = "Get coordinates based on building numbers", description = "Retrieves coordinates associated with a particular building code.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coordinates found"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/coordinates")
    @Tag(name="course-information")
    public ResponseEntity<List<String>> getCoordinatesByBuildingNumber(@RequestParam(value="buildingNumber",
            required = true) String buildingNumber) {
        if (buildingNumber == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            // Coordinates of a building number as a string
            List<String> coordinates = new ArrayList<String>();
            coordinates.add(courseInformationService.getCoordinatesByBuildingNumber(buildingNumber));

            // Return the list of subject strings if found
            return ResponseEntity.ok(coordinates);

        } catch (Exception e) {

            // Return 500 if a server error occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Retrieves a list of all instructors at UGA
     * 
     * @return a list of all instructor names as a String
     */ 
    @Operation(summary = "Gets a list of all the instructors", description = "Retrieves a list of all instructors at UGA")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Instructors Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/instructors")
    @Tag(name = "course-information")
    public ResponseEntity<List<String>> getAllInstructors() {
        try {
            // Fetch all the instructors at UGA
            List<String> instructors = courseInformationService.getAllInstructors();
            // Returns the list of instructors if found
            return ResponseEntity.ok(instructors);
        }catch (Exception e) {
            // return 500 if a server error occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Retrieves a Professor's Average Rating on RateMyProfessor.
     * 
     * @param lname A String of the professor's last name
     * @param fname A String of the professor's first name
     * @return A {@code ResponseEntity<float>} with the average rating.
     */
    @Operation(summary = "Gets the average of a Professor's RateMyProfessor Ratings", description = "Retrieves a float containing the mean of a Professor's ratings")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ratings Found/Professor Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
        
    })
    @GetMapping("/avgRating")
    @Tag(name = "course-information")
    public ResponseEntity<Float> getAvgProfessorRating(String lname, String fname) {
        try {
            // Retrieving value from service method
            float avgRating = courseInformationService.getProfessorAverageRating(lname, fname);

            // Returns average rating if found
            return ResponseEntity.ok(avgRating);
        } catch (Exception e) {
            if (e instanceof ProfessorNotFoundException) {  // Thrown by getProfessorAverageRating()

                // return 200 if Professor not found)
                return ResponseEntity.status(HttpStatus.OK).body(null);        
            } else {

                // return 500 for server error
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
    }

    /**
     * Retrieves the number of RateMyProfessor reviews of a professor
     * 
     * @param lname A String of the professor's last name
     * @param fname A String of the professor's first name
     * @return {@code ResponseEntity<Integer>} with the number of reviews.
     */
    @Operation(summary = "Gets the number of ratings for a professor", description = "Retrieves an int containing the total number of professor ratings")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ratings Found/Professor Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
        
    })
    @GetMapping("/numRatings")
    @Tag(name = "course-information")
    public ResponseEntity<Integer> getNumProfessorRatings(String lname, String fname) {
        try {
            // Retrieving value from service method
            int totalRatings = courseInformationService.getProfessorTotalReviews(lname, fname);

            // Returns num of ratings if found
            return ResponseEntity.ok(totalRatings);
        } catch (Exception e) {
            if (e instanceof ProfessorNotFoundException) {  // Thrown by getProfessorTotalRatings()

                // return 200 if Professor not found)
                return ResponseEntity.status(HttpStatus.OK).body(null);        
            } else {

                // return 500 for server error
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
    }

} // CourseInfoController

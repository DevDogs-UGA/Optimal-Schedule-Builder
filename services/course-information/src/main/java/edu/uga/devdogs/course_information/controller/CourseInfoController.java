package edu.uga.devdogs.course_information.controller;

import edu.uga.devdogs.course_information.Course.Course;
import edu.uga.devdogs.course_information.CourseSection.CourseSection;
import edu.uga.devdogs.course_information.service.CourseInformationService;
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
        @ApiResponse(responseCode = "404", description = "Section not found"),
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
          
          if(courseInfo.isEmpty()){
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(courseInfo); //Returns 404 error code is course info isn't found
          }

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
        @ApiResponse(responseCode = "404", description = "Section not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
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
            List<Course> courseList = courseInformationService.getCoursesByMajor(major);

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

    /**
     * Returns the details of a specified CRN
     * 
     * @param crn The CRN of the section
     * @return returns a section object for the CRN
     */
    @Operation(summary = "get section by crn", description = "Retrieves a section from the given CRN.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Section found"),
        @ApiResponse(responseCode = "400", description = "Invalid CRN"),
        @ApiResponse(responseCode = "404", description = "Section not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/section-by-crn")
    @Tag(name="course-information")
    public ResponseEntity<CourseSection> getCourseEntity(@RequestParam String crn) {

        //return 400 for empty CRN
        if (crn.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            //Call method to get section details
            CourseSection sectionDetails = courseInformationService.getSectionDetailsByCrn(crn);

            //Check if the above method call returned null
            if (sectionDetails == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //Return 404 if no courses are found
            }

            //Return the section if found
            return ResponseEntity.ok(sectionDetails);

        } catch (Exception e) {

            //Return 500 if a server error occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    /**
     * Retrieves course information based on Athena name.
     * 
     * @param athenaName The Athena name of the course
     * @return Course details for the given Athena name
     */
    @Operation(summary = "Get course by Athena name", description = "Retrieves course List based on the provided Athena name.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Section found"),
        @ApiResponse(responseCode = "400", description = "Invalid CRN"),
        @ApiResponse(responseCode = "404", description = "Section not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/course-by-athena-name")
    @Tag(name="course-information")
    public ResponseEntity<List<Course>> getCourseByAthenaName(@RequestParam String athenaName) {

        // Return 400 for empty athenaName
        if (athenaName.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            // Call a service method to fetch the course by Athena name
            List<Course> courseDetails = courseInformationService.getCourseByAthenaName(athenaName);  // not yet implemented

            // Check if the above method call returned null
            if (courseDetails == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404 if no course is found
            }

            // Return the course details if found
            return ResponseEntity.ok(courseDetails);

        } catch (Exception e) {
            // Return 500 if a server error occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    

}

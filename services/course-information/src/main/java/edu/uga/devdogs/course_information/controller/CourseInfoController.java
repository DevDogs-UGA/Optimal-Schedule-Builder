package edu.uga.devdogs.course_information.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller that handles the Course-Information details.
 * Provides courses based on parameters like major, professor,
 * CRN, time slot, and athena name.
 */
@RestController
@RequestMapping("/api/course_information")
public class CourseInfoController {
   
    /**
     * Asks for list of course information that relates to the 
     * @param professor given.
     * 
     * @param professor name of the professor teaching the course
     * @return course information list that's related to the given professor.
     */
    @Operation(summary = "get list of courses by professor", description = "Retrieces course information relating to the professor.")
    @ApiResponse(value = {
          @ApiResponse(responseCode = "200",description = "Course found"),
          @ApiResponse(responseCode = "400",description = "Invalid Course ID"),
          @ApiResponse(responseCode = "404",description = "Course not found")
    })
    @GetMapping("/professor")
   public List<Section> getCourseByProfessor(@RequestParam(value = "professor",required = true) String professor){
        
     if(professor == null || professor.isEmpty()){
          return ResponseEntity.badRequest().body(null); //Returns 400 if the parameter is not provided
     }

     try {
          List<Section> courseInfo = fetchCoursesByProf(professor); //Fetches course information based on professor
          
          if(courseInfo.isEmpty()){
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(courseInfo); //Returns 404 error code is course info isn't found
          }

          return ResponseEntity.ok(courseInfo);
     } catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
     }
   } 
    
}
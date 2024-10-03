package main.java.edu.uga.devdogs.course_information.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles the Course-Information details.
 * Provides courses based on parameters like major, professor,
 * CRN, time slot, and athena name.
 */
@RestController
@RequestMapping("/api/course_information")
public class CourseInfoController {
    
    /**
     * Provides courses relating to a specific athena name.
     * 
     * @param athenaName The athena name of the course that a user wants.
     * @return message indicating the placement of implementation.
     */
    @GetMapping("/athena-name")
    public String getCourseByAthenaName(@RequestParam String athenaName){
        return "Implementation for course of a specific athena name";
    }

    /**
     * Provides courses relating to a specific major.
     * 
     * @param major The name of the major that a user wants courses in.
     * @return message indicating the placement of implementation.
     */
    @GetMapping("/major")
    public String getCourseByMajor(@RequestParam String major){
        return "Implementation for courses of a specific major";
    }
    
    /**
     * Provides course information pertaining to a specific professor.
     * 
     * @param professor The name of the professor that the user wants courses with.
     * @return message indicating the placement of the classes by a specific professor.
     */
    @GetMapping("/professors")
    public String getCourseByProf(@RequestParam String professor) {
        return "Implementation for getting list of courses by a specific professor";
    }

    /**
     * Provides course information relating to a given CRN number.
     * 
     * @param crn the CRN number of the course a user wishes to search up.
     * @return message indicating the placement of classes by a specific CRN number.
     */
    @GetMapping("/crn-course")
    public String getCourseByCRN(@RequestParam String crn) {
        return "Implementation for getting list of courses by a specific crn number";
    }

    /**
     * Provides course information relating to a given time slot.
     * 
     * @param time the time slot for courses that a user wishes to find.
     * @return message indicating the placement of classes by a specific time slot.
     */
    @GetMapping("/time-slot")
    public String getCourseByTime(@RequestParam String time) {
        return "Implementation for getting list of courses by a specific time slot";
    }

    
}
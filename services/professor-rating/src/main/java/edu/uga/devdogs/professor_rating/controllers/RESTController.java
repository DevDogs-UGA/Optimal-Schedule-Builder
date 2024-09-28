package edu.uga.devdogs.professor_rating.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Rate My Professor related services.
 * <p>
 * This controller handles HTTP requests related to professor data
 * within the Rate My Professor data. It currently provides a
 * simple placeholder endpoint to demonstrate functionality.
 * </p>
 *
 * @author Raghav Vikramprabhu
 */
@RestController
@RequestMapping("/api/professor-service")
public class RESTController {
    /**
     * Handles GET requests for retrieving a list of professors.
     * <p>
     * This method currently returns a static message indicating where
     * a list of professors would be displayed. The actual functionality
     * to retrieve professors will be implemented in the future.
     * </p>
     *
     * @return A static message indicating that a list of professors will go here.
     */
    @GetMapping("/professors")
    public String getProfessors() {
        //For now just return a static message
        return "List of professors will go here!";
    }

    /**
     * Handles GET requests for returning whether attendance is mandatory
     * for a given professor and class.
     * <p> 
     * This endpoint currently returns an arbitrary double value. The method
     * attatched to this endpoint will be implemented in the future, and the
     * call to it is currently commented.
     * </p>
     *
     * @param professorName The name of the professor who teaches the given class.
     * @param className The name of the given class.
     *
     * @return A double value representing the proportion of classes
     * from RMP that have mandatory attendance.
     * 
     */
    @GetMapping("/attendance")
    public double isAttendanceMandatory(@RequestParam(name="professorName", required=true) String professorName, 
@RequestParam(name="className", required=true) String className) {
        //This is a commented call to a fictitious method, to be implemented later.
        //return getAttendance(professorName, className);
        return 0.0;
    }
}

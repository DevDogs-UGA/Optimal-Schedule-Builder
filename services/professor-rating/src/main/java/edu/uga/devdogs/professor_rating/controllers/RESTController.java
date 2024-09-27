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
}

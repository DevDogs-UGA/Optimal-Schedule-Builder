package com.example.bulletin.service.impl;

import com.example.bulletin.model.Course;
import com.example.bulletin.repository.CourseRepository;
import com.example.bulletin.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that handles business logic for the Bulletin microservice.
 *
 * <p>
 * This Service layer acts as an intermediary between the Controller layer
 * and the database. It is responsible for fetching, processing, and transforming data.
 * </p>
 *
 * <p>
 * Future methods will include data management operations, transforming raw
 * data from the database into meaningful responses for the API.
 * </p>
 *
 * @author Raghav Vikramprabhu
 */
@Service
public class BulletinCourseService {

    /**
     * Placeholder business logic method for future implementation.
     * <p>
     * This method will handle tasks related to data fetching, transformation,
     * and interaction between the database and the API.
     * </p>
     */
    public void someBusinessLogic() {
        // Service logic goes here
    }

    /**
     * Service method for receiving courses of a specified amount of credit hours
     * 
     * @param creditHour the number of credit hours that a class needs to have
     * @return list of "Courses" that have a specific number of credit hours.
     */
    @Operation(summary = "Get courses by a specific credit hour",description = "Retrieves courses that have a specified number of credit hours")
    @ApiResponse(value = {
        @ApiResponse(responseCode = "200", description = "Course found"),
        @ApiResponse(responseCode = "400", description = "Invalid credit hours"),
        @ApiResponse(responseCode = "404",description = "Course not found")
    })
    @GetMapping("/term")
    public List<Course> getCoursesByCreditHours(@RequestParam(value = "creditHours", required = true) int creditHours) {
        if(creditHours == 0 && creditHours >= 5) {
            return ResponseEntity.badRequest().body(null); //Returns 400 if creditHours is invalid
        } else if(creditHours > 0){
            try {
                //Make sure Credit hours is greater than 0
                /*Return list of courses that has the specific amount of credit hours */

                List<Course> data = JPA.getCredits(creditHours);

                if(data.isEmpty()){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(courseList); //Checks and returns 404 error if data information is empty
                }

                return ResponseEntity.ok(data); //Returns course information if everything is correct
            } catch (Exception e) {
                //Catches any server problems or exceptions
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
    }

}


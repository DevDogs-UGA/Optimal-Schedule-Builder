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
     * Retrieves the type of class (e.g., Honors, Lab, Online) for a given course ID.
     *
     * @param courseId the ID of the course
     * @return the type of the class as a String
     * @throws IllegalArgumentException if the course ID is invalid or not found
     */
    public String getClassTypeByCourseId(Long courseId) {
        // Validate the courseId
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException("Invalid course ID");
        }

        // Example JPA call to fetch course type
        // Assuming Course entity has a method getType() that returns the type of the course
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found for ID: " + courseId));

        return course.getType();
    }    

    /**
     * Retrieves a list of courses available in a specified term.
     *
     * <p>
     * This method fetches courses from the database that are available in the given term.
     * If the term is null or empty, it returns all available courses.
     * </p>
     *
     * @param term the academic term for which courses are to be retrieved
     * @return a list of courses available in the specified term
     */
    public List<Course> getCoursesByTerm(String term) {
        // Check if the term is provided and not empty
        if (term != null && !term.isEmpty()) {
            // Fetch courses for the specified term
            return courseRepository.findByTerm(term);
        }

        // Return all courses if no term is specified
        return courseRepository.findAll();
    }

}


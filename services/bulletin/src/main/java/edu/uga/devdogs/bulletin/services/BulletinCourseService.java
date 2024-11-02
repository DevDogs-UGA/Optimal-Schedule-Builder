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
     * Method to get a list of courses matching a given special type (honors, lab, or online).
     * The methods it calls in the JPA layer will be implemented later by the database team.
     * In-line comments in the body of the function indicate what data we need from the JPA layer.
     * 
     * @param type the type of course of which to retrieve a list.
     * @return A list of course objects matching the given type.
     */
    public List<Course> getCoursesByType(String type) {
        List<Course> returnList;
        if (type.equals("honors")) {
            //we want to get a list of all honors courses from the database
            returnList = bulletinJPAFile.getHonorsCourses();
        } else if (type.equals("online")) {
            //we want to get a list of all online courses from the database
            returnList = bulletinJPAFile.getOnlineCourses();
        } else if (type.equals("lab")) {
            //we want to get a list of all lab courses from the database
            returnList = bulletinJPAFile.getLabCourses();
        }

        if (returnList != null) {
            return returnList;
        } else {
            throw new CourseNotFoundException("Courses with the type " + type + " not found!");
        }
    }

    /**
     * Method retrieves the requirements for a given course.
     *
     * @param courseName The identifier for the course (e.g., CSCI1302).
     * @return a list of required classes for the given course.
     */
    public List<String> getCourseRequirements(String courseName) {
        // Find the course by courseCode (assuming courseCode is a field in the Course entity)
        Course course = courseRepository.findByCourseCode(courseName);

        // Return the course requirements (assuming requirements are stored as a List in Course entity)
        if (course != null) {
            return course.getRequirements();
        } else {
            throw new CourseNotFoundException("Course not found for code: " + courseName);
        }
    }

}


package edu.uga.devdogs.course_information.service;

import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class that handles course information business logic.
 * This class provides methods for the management of 
 * course information
 */
@Service
public class CourseInformationService {
    // Methods go here

    /**
     * Method to get a list of class objects matching the given time slot and CRN. The method 
     * it calls in the JPA layer will be implemented later.
     * 
     * @param timeSlot
     * @param courseName
     * @return A list of Class objects matching the given time slot and CRN
     */
    public List<Class> getClassesByCrnAndTime(String timeSlot, String crn) {
        //Right now, the file that will contain the following method has not been created yet,
        //so we use a fictitious file name.
        return courseInfoJPAFile.getClassesByCrnAndTime(timeSlot, crn);        
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

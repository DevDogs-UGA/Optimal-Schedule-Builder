package edu.uga.devdogs.course_information.service;

import edu.uga.devdogs.course_information.exception; 
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


}

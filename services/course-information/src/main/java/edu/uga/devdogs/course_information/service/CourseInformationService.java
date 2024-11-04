package edu.uga.devdogs.course_information.service;

import edu.uga.devdogs.course_information.exception; 
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class that handles business logic for managing course information.
 *
 * <p>
 * This service layer is responsible for providing methods related to course
 * information management, including retrieving class schedules and course details
 * based on specific criteria. It acts as an intermediary between the controller
 * layer and the data repository, ensuring that all course information operations
 * are correctly managed and returned to the client.
 * </p>
 *
 * <p>
 * This class may be extended in the future to include more complex data
 * processing and transformation related to course information.
 * </p>
 *
 * @see CourseInformationService
 * @since 1.0
 */
@Service
public class CourseInformationService {
    // Methods go here

    /**
     * Retrieves a list of Course section (class) objects that match the specified time slot and CRN (Course Reference Number).
     *
     * <p>
     * This method queries the data repository for classes scheduled in a given time slot
     * and having the specified CRN. The underlying repository method will be implemented later.
     * </p>
     *
     * @param timeSlot the time slot for which to retrieve class information (e.g., "10:00 AM - 11:15 AM")
     * @param crn the course reference number to identify a specific class
     * @return a list of {@link Class} objects that match the given time slot and CRN
     * @throws CourseNotFoundException if no classes are found for the specified time slot and CRN
     */
    public List<Class> getClassesByCrnAndTime(String timeSlot, String crn) {
        //Right now, the file that will contain the following method has not been created yet,
        //so we use a fictitious file name.

        //Make object to store returnList to make sure courese actually exist
        List<Class> returnList;

        returnList = courseInfoJPAFile.getClassesByCrnAndTime(timeSlot, crn);

        if (returnList != null)
            return returnList;
        else
            throw new CourseNotFoundException("Class not found for timeSlot: " + timeSlot + " and CRN: " + crn);
    }


}

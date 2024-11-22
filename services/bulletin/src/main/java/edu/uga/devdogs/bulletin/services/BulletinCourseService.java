package edu.uga.devdogs.bulletin.services;

import edu.uga.devdogs.bulletin.database.Course;
import edu.uga.devdogs.bulletin.database.Section;
import edu.uga.devdogs.bulletin.exceptions.CourseNotFoundException;
import edu.uga.devdogs.bulletin.exceptions.IncorrectArguementsException;
import edu.uga.devdogs.bulletin.exceptions.SectionNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
     * Service method for getting pre-requisites for a given course ID or CRN.
     *
     * @param courseId The ID of the course to retrieve pre-requisites for. (optional)
     * @param crn The CRN of the course to retrieve pre-requisites for. (optional)
     * @return A list of course objects that are pre-requisites for the given course.
     */
    public List<Course> getPreReqCourses(String courseID, String crn) {
        String type = (courseID != null) ? courseID : crn;
        // Returns all the pre-requisites for the given CRN or CourseID
        return JPA.getPreReqs(type);
    }

    /**
     * Service method for getting co-requisites for a given course ID or CRN.
     *
     * @param courseId The ID of the course to retrieve co-requisites for. (optional)
     * @param crn The CRN of the course to retrieve co-requisites for. (optional)
     * @return A list of course objects that are co-requisites for the given course.
     */
    public List<Course> getCoReqCourses(String courseID, String CRN) {
        String type;
        if (courseID != null) {
            type = courseID;
        } else {
            type = CRN;
        }
        /* Returns all the coreqs for the given CRN or CourseID */
        return JPA.getCoReqs(type);
    }

    /**
     * Service method for receiving courses of a specified amount of credit hours
     *
     * @param creditHour the number of credit hours that a class needs to have
     * @return list of "Courses" that have a specific number of credit hours.
     */
    public List<Course> getCoursesByCreditHours(int creditHours) {
        List<Course> data = null;

        if(creditHours == 0 || creditHours >= 5) {
            throw new IncorrectArguementsException("Credit hours must be between 1 and 5 inclusive!");
        } else if(creditHours > 0){
            //Make sure Credit hours is greater than 0
            /*Return list of courses that has the specific amount of credit hours */

            data = JPA.getCredits(creditHours);

            if(data.isEmpty()){
                throw new CourseNotFoundException(String.format("Courses with %d Credit hours not found!", creditHours)); //Checks and returns 404 error if data information is empty
            }

            return data; //Returns course information if everything is correct
        }
        return data;
    }

    /**
     * Retrieves a list of courses that match the specified type (e.g., honors, lab, or online).
     *
     * <p>
     * The type parameter determines the category of courses to retrieve, such as "honors" for honors courses,
     * "lab" for lab courses, or "online" for online courses. If no courses are found for the specified type,
     * a {@link CourseNotFoundException} is thrown.
     * </p>
     *
     * @param type the type of course to retrieve
     * @return a list of {@link Course} objects that match the specified type
     * @throws CourseNotFoundException if no courses of the specified type are found
     */
    public List<Course> getCoursesByType(String type) {
        List<Course> returnList = switch (type) {
            case "honors" ->
                //we want to get a list of all honors courses from the database
                    bulletinJPAFile.getHonorsCourses();
            case "online" ->
                //we want to get a list of all online courses from the database
                    bulletinJPAFile.getOnlineCourses();
            case "lab" ->
                //we want to get a list of all lab courses from the database
                    bulletinJPAFile.getLabCourses();
            default -> null;
        };

        if (returnList != null) {
            return returnList;
        } else {
            throw new CourseNotFoundException("Courses with the type " + type + " not found!");
        }
    }

    /**
     * Retrieves the prerequisites or requirements for a specified course.
     *
     * <p>
     * Given a course identifier, this method queries the repository for a {@link Course} object
     * with matching course code and returns its requirements. If the course does not exist, it throws
     * a {@link CourseNotFoundException}.
     * </p>
     *
     * @param courseName the unique identifier for the course (e.g., "CSCI1302")
     * @return a list of requirement strings representing the prerequisites for the course
     * @throws CourseNotFoundException if no course with the specified identifier is found
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

    /**
     * Retrieves a list of courses offered in a specified term (e.g., Fall, Spring, Summer).
     *
     * <p>
     * The term parameter determines the academic term for which to retrieve courses.
     * If no courses are found for the specified term, a {@link CourseNotFoundException} is thrown.
     * </p>
     *
     * @param term the academic term to retrieve courses for
     * @return a list of {@link Course} objects offered in the specified term
     * @throws CourseNotFoundException if no courses are found for the specified term
     */
    public List<Course> getCoursesByTerm(String term) {
        List<Course> courses = courseRepository.findByTerm(term);

        if (courses != null && !courses.isEmpty()) {
            return courses;
        } else {
            throw new CourseNotFoundException("No courses found for term: " + term);
        }
    }

    /**
     * Retrieves the type of a class (e.g., Honors, Lab, Online) based on the given course ID.
     *
     * <p>
     * This method queries the repository for a {@link Course} object with the specified ID
     * and returns its type. If the course does not exist, it throws a {@link CourseNotFoundException}.
     * </p>
     *
     * @param courseId the unique identifier for the course
     * @return the type of the course as a string
     * @throws CourseNotFoundException if no course with the specified ID is found
     */
    public String getCourseTypeById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found for ID: " + courseId));

        // Assuming the Course entity has a method getType() that returns the type of the course
        return course.getType();
    }

    /**
     * Retrieves the type of a class (e.g. Honors, Lab, Online) 
     * based on the given Course Reference Number (CRN).
     * 
     * <p>
     * This method queries the repository for a {@link Course} object with the specified CRN
     * and returns its type. If the course does not exist, it throws a {@link CourseNotFoundException}.
     * </p>
     * 
     * @param CRN the Course Reference Number for a section of the course
     * @return the type of the course as a String
     * @throws CourseNotFoundException if no course with the specified CRN is found
     */
    public String getCourseTypeByCRN(int crn) {
        Course course = courseRepository.findByCRN(crn)
                .orElseThrow(() -> new CourseNotFoundException("Course not found for CRN: " + crn));
        //Assuming the Course object has a method getType() that returns the type of the course
        return course.getType();
    }

    /**
    * Service Method to get the List of Course Sections by timeslot, optionally CRN
     * This method returns a list of courses by the timeslot, optionally crn
     *
     * @param timeSlot The timeslot to use e.g. ("10:00 AM - 11:15 AM")
     * @param crn The crn to optionally use as well
     * @return The list of applying Course Sections
     * @throws SectionNotFoundException if no sections matching the parameters were found.
    * */
    public List<Section> getSectionsByTimeAndOrCrn(String timeSlot, String crn) {
        //Use our helper method to parse a start and end time for JPA calls
        try {
            Time startTime = parseTime(timeSlot.split(" - ")[0]);
            Time endTime = parseTime(timeSlot.split(" - ")[1]);

            //Make object to store returnList to make sure courses actually exist
            List<Section> timeReturnList = new ArrayList<>();
            Set<String> seenTimes = new HashSet<>();


            List<Section> timeStartReturnList = sectionRepository.findByStartTime(startTime);
            List<Section> timeEndReturnList = sectionRepository.findByEndTimeBetween(endTime, endTime);

            // Create a map to store elements from timeEndReturnList by their time properties
            Map<String, Section> endTimeMap = new HashMap<>();
            for (Section endSection : timeEndReturnList) {
                String key = endSection.getStartTime().toString() + "-" + endSection.getEndTime().toString();
                endTimeMap.put(key, endSection);
            }

            // Iterate through timeStartReturnList and check for matches in endTimeMap
            for (Section startSection : timeStartReturnList) {
                String key = startSection.getStartTime().toString() + "-" + startSection.getEndTime().toString();

                if (endTimeMap.containsKey(key) && !seenTimes.contains(key)) {
                    timeReturnList.add(startSection); // Add to result list
                    seenTimes.add(key); // Mark as seen
                }
            }

            if (timeReturnList != null) {
                if (crn != null) {
                    List<Section> finalReturnList = null;
                    for (Section section : timeReturnList) {
                        if (section.getCRN().equals(crn)) {
                            finalReturnList.add(section);
                            break;
                        }
                    }
                    return finalReturnList;
                }
                return timeReturnList;
            }
            else {
                throw new SectionNotFoundException("Class not found for timeSlot: " + timeSlot + " and CRN: " + crn);
            }
        } catch (ParseException e) {
            System.err.println("Failed to parse time: " + e.getMessage());
        }
    }

    /*
     * This method is a helper method to parse our timeslots (10:00 AM - 11:15 AM)
     */
    private static Time parseTime(String timeString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        Date parsedDate = sdf.parse(timeString);
        return new Time(parsedDate.getTime());
    }
}

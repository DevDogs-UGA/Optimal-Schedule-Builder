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

}


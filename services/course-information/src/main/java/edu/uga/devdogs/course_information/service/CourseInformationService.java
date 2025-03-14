package edu.uga.devdogs.course_information.service;

import edu.uga.devdogs.course_information.Class.ClassRepository;
import edu.uga.devdogs.course_information.Course.Course;
import edu.uga.devdogs.course_information.Course.CourseRepository;
import edu.uga.devdogs.course_information.CourseSection.CourseSection;
import edu.uga.devdogs.course_information.CourseSection.CourseSectionRepository;
import edu.uga.devdogs.course_information.exceptions.ProfessorNotFoundException;
import edu.uga.devdogs.course_information.Building.Building;
import edu.uga.devdogs.course_information.Building.BuildingRepository;
import edu.uga.devdogs.course_information.exceptions.SectionDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.uga.devdogs.course_information.exceptions.BuildingNotFoundException;
import edu.uga.devdogs.course_information.exceptions.CourseNotFoundException;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    // Inject our JPA repository interfaces
    private final CourseSectionRepository courseSectionRepository;
    private final ClassRepository classRepository;
    private final CourseRepository courseRepository;
    private final BuildingRepository buildingRepository;

    @Autowired
    public CourseInformationService(CourseSectionRepository courseSectionRepository,
                                    ClassRepository classRepository,
                                    CourseRepository courseRepository,
                                    BuildingRepository buildingRepository) {
        this.courseSectionRepository = courseSectionRepository;
        this.classRepository = classRepository;
        this.courseRepository = courseRepository;
        this.buildingRepository = buildingRepository;
    }

    // The rest of your methods follow...


    /**
     * Method to get a list of section details (tiemslot) matching the given CRN. The method
     * it calls in the JPA layer will be implemented later.
     *
     * @param crn the given course reference number for which to retrieve section details
     * @return A list of Section Details objects matching the given time slot and CRN
     */
    public CourseSection getSectionDetailsByCrn(String crn){
        CourseSection returnList;
        int intcrn = Integer.parseInt(crn);
        returnList = courseSectionRepository.findByCrn(intcrn);

        if (returnList != null)
            return returnList;
        else
            throw new ProfessorNotFoundException("Section details not found for CRN: " + crn);
    }

    /**
     * Method to get a list of Course objects matching the given major. The method
     * it calls in the JPA layer will be implemented later.
     * 
     * @param major the given major for which to retrieve courses
     * @return a list of Course objects matching the given major
     */
    public List<Course> getCoursesByMajor(String major) {
        List<Course> returnList;

        returnList = courseRepository.findAllBySubject(major);

        if (returnList != null) {
            return returnList;
        } else {
            throw new CourseNotFoundException("Courses not found for major: " + major);
        }
    }

    /**
     * Retrieves all course sections taught by a specific professor.
     *
     * @param professorName The professor's name
     * @return List of course sections taught by the specified professor
     * @throws ProfessorNotFoundException if the professor is not found
     */
    public List<CourseSection> getCourseSectionsByProfessor(String professorName) {
        
        // Fetch course sections by professor name
        List<CourseSection> returnList = courseSectionRepository.findAllByInstructor(professorName);

        if (returnList != null) {
            return returnList;
        } else {
            throw new ProfessorNotFoundException(String.format("Professor %s not found!", professorName));
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

     /**
     * Retreives a list of all buildings
     *
     *  @return List of all building bojects
     * @throws BuildingNotFoundException if no buildigns are found
     */
    public List<Building> getAllBuildings() {
        List<Building> buildings = buildingRepository.findAll();
        if (buildings != null) {
            return buildings;
        } else {
            throw new BuildingNotFoundException("No Buildings Found");
        }
    }

    /**
     *  Retreives a list of all academic subjects at UGA.
     * 
     *  @return List of all available subjects
     *  @throws CourseNotFoundException if no courses are found
     */ 
    public List<String> getAllSubjects() {
        List<Course> courses = courseRepository.findAll();
        List<String> subjects = new ArrayList<>();
        if (courses != null) {
            for (Course course : courses) {
                //so we avoid duplicate subject names

                /* getSubject() should work - JPA issue. Commenting for now
                if (!subjects.contains(course.getSubject())) {
                    subjects.add(course.getSubject());
                }
                */
            }
            return subjects;
        } else {
            throw new CourseNotFoundException("No Courses Found");
        }
    
    }

    /**
     * Retrives a list of all Instructors
     * 
     * @return a String list of all instructors
     * @throws ProfessorNotFoundException if there are no professors found
     */
    public List<String> getAllInstructors() {
        List<CourseSection> sections = courseSectionRepository.findAll();
        List<String> instructors = new ArrayList<>();
        // checks if there are sections before continuing
        if (sections == null) {
            throw new ProfessorNotFoundException("No Professors Found");
        }
        // iterates through all the sections
        for(int i = 0; i < sections.size(); i++) {
            // avoids duplicate instructor names
            if(!instructors.contains(sections.get(i).getInstructor())) {
                instructors.add(sections.get(i).getInstructor());
            }    
        }
        return instructors;
    }

    /**
     * Retrieves a course's type (e.g., Honors, Lab, Online) based on the given course ID.
     * Not in use as of 3/10/25.
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
    public String getCourseTypeById(String courseId) {
        Long courseIdLong = Long.parseLong(courseId);
        List<Course> courseList = courseRepository.getCourseInfoByCourseId(courseIdLong);
        if (courseList == null) {
            return null;
        }

        String courseNumber = courseList.get(0).getCourseNumber();
        if (courseNumber.length() == 9) {
            char type = courseNumber.charAt(8);
            switch (type) {
                case 'E':
                    return "Online Learning";
                case 'S':
                    return "Service-Learning";
                case 'H':
                    return "Honors";
                case 'I':
                    return "Integrated Language";
                case 'W':
                    return "Writing Intensive";
                case 'D':
                    return "Non-Credit Discussion Group";
                case 'L':
                    return "Lab (Non-Credit and Credit)";
            }
        }
        return "No Special Designation";
    }

    /**
     * Service method for getting pre-requisites for a given course ID or CRN.
     * Not in use as of 3/10/25.
     *
     * @param courseID The ID of the course to retrieve pre-requisites for.
     * @return A list of course objects that are pre-requisites for the given course.
     */
    public List<Course> getPreReqCourses(String courseID, String crn) {
        Long courseIdLong = Long.parseLong(courseID);
        return courseRepository.findPrerequisitesByCourseId(courseIdLong);
    }

    /**
     * Service method for getting co-requisites for a given course ID or CRN.
     *
     * @param courseID The ID of the course to retrieve co-requisites for.
     * @return A list of course objects that are co-requisites for the given course.
     */
    public List<Course> getCoReqCourses(String courseID) {
        Long courseIdLong = Long.parseLong(courseID);
        return courseRepository.findCorequisitesByCourseId(courseIdLong);
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
        List<Course> courses = courseRepository.findAllBySemester(term);

        if (courses != null && !courses.isEmpty()) {
            return courses;
        } else {
            throw new CourseNotFoundException("No courses found for term: " + term);
        }
    }

    /**
     * Method to get the coordinates of a building based on the building number
     *
     * @param number The number of specified building
     * @return A string coordinate of the building
     */
    public String getCoordinatesByBuildingNumber(String number){
        //String coordinate = buildingRepository.getCoordinatesByBuildingNumber(number); //not implemented in database, commenting for now
        String coordinate = null; //returning null until JPA implementation is added
        if (coordinate != null) {
            return coordinate;
        } else {
            throw new BuildingNotFoundException("Building Not Found");
        }
    }

    /**
    * Retrieves a list of all CRNs (Course Registration Numbers) from the database.
    *
    * @return a list of CRNs as integers
    */
    public List<Integer> getAllCRNs() {
        // Fetch all courses from the database
        List<CourseSection> courseSections = courseSectionRepository.findAll();
        List<Integer> crns = new ArrayList<>();
    
        // Extract CRNs from the courses
        for (CourseSection section : courseSections) {
            crns.add(section.getCrn());
        }
        return crns;
    }







}


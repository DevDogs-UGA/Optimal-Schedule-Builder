package edu.uga.devdogs.course_information.service;

import edu.uga.devdogs.course_information.Class.ClassRepository;
import edu.uga.devdogs.course_information.Class.Class;
import edu.uga.devdogs.course_information.Course.Course;
import edu.uga.devdogs.course_information.Course.CourseRepository;
import edu.uga.devdogs.course_information.CourseSection.CourseSection;
import edu.uga.devdogs.course_information.CourseSection.CourseSectionRepository;
import edu.uga.devdogs.course_information.exceptions.ProfessorNotFoundException;
import edu.uga.devdogs.course_information.Building.Building;
import edu.uga.devdogs.course_information.Building.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    // Methods go here

    //Inject our JPA repository interfaces
    private final CourseSectionRepository courseSectionRepository;
    private final ClassRepository classRepository;
    private final CourseRepository courseRepository;
    private final BuildingRepository buildingRepository;

    //Use constructor to inject
    @Autowired
    public CourseInformationService(CourseSectionRepository courseSectionRepository, ClassRepository classRepository, CourseRepository courseRepository, BuildingRepository buildingRepository) {
        this.courseSectionRepository = courseSectionRepository;
        this.classRepository = classRepository;
        this.courseRepository = courseRepository;
        this.buildingRepository = buildingRepository;
    }

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

        //Use our helper method to parse a start and end time for JPA calls
        try {
            Time startTime = parseTime(timeSlot.split(" - ")[0]);
            Time endTime = parseTime(timeSlot.split(" - ")[1]);

            //Make object to store returnList to make sure courses actually exist
            List<Class> timeReturnList = new ArrayList<>();
            Set<String> seenTimes = new HashSet<>();


            List<Class> timeStartReturnList = classRepository.findAllByStartTime(startTime);
            List<Class> timeEndReturnList = classRepository.findAllByEndTimeBetween(endTime, endTime);

            // Create a map to store elements from timeEndReturnList by their time properties
            Map<String, Class> endTimeMap = new HashMap<>();
            for (Class endClass : timeEndReturnList) {
                String key = endClass.getStartTime().toString() + "-" + endClass.getEndTime().toString();
                endTimeMap.put(key, endClass);
            }

            // Iterate through timeStartReturnList and check for matches in endTimeMap
            for (Class startClass : timeStartReturnList) {
                String key = startClass.getStartTime().toString() + "-" + startClass.getEndTime().toString();

                if (endTimeMap.containsKey(key) && !seenTimes.contains(key)) {
                    timeReturnList.add(startClass); // Add to result list
                    seenTimes.add(key); // Mark as seen
                }
            }
            if (timeReturnList != null)
                return timeReturnList;
            else
                throw new CourseNotFoundException("Class not found for timeSlot: " + timeSlot + " and CRN: " + crn);
        } catch (ParseException e) {
            System.err.println("Failed to parse time: " + e.getMessage());
        }
    }

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
     * Retreives course based on Athena name
     *
     *  @param athenaName The Athena name of the course
     *  @return The {@link Course} object matching the given Athena name
     * @throws CourseNotFoundException if no course is found for the specified Athena name
     */
    public List<Course> getCourseByAthenaName(String athenaName) {
        List<Course> course = courseRepository.findAllByTitle(athenaName);

        if (course != null) {
            return course;
        } else {
            throw new CourseNotFoundException("Course not found for Athena name: " + athenaName);
        }
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
                if (!subjects.contains(course.getSubject())) {
                    subjects.add(course.getSubject());
                }
            }
            return subjects;
        } else {
            throw new CourseNotFoundException("No Courses Found");
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
        String id = course.getCourseNumber();
        if (id.length() == 9) {
            char type = id.charAt(8);
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
     *
     * @param courseID The ID of the course to retrieve pre-requisites for. (optional)
     * @param crn The CRN of the course to retrieve pre-requisites for. (optional)
     * @return A list of course objects that are pre-requisites for the given course.
     */
    public List<Course> getPreReqCourses(String courseID, String crn) {
        String type = (courseID != null) ? courseID : crn;
        // Returns all the pre-requisites for the given CRN or CourseID
        return CourseRepository.getPreReqs(type);  // getPreReqs is not currently implemented
    }

    /**
     * Service method for getting co-requisites for a given course ID or CRN.
     *
     * @param courseID The ID of the course to retrieve co-requisites for. (optional)
     * @param crn The CRN of the course to retrieve co-requisites for. (optional)
     * @return A list of course objects that are co-requisites for the given course.
     */
    public List<Course> getCoReqCourses(String courseID, String crn) {
        String type;
        if (courseID != null) {
            type = courseID;
        } else {
            type = crn;
        }
        /* Returns all the coreqs for the given CRN or CourseID */
        return CourseRepository.getCoReqs(type);  // getCoReqs is not currently implemented
    }

    /**
     * Service method for receiving courses of a specified amount of credit hours
     *
     * @param creditHours the number of credit hours that a class needs to have
     * @return list of "Courses" that have a specific number of credit hours.
     */
    public List<Course> getCoursesByCreditHours(int creditHours) {
        List<Course> data = null;

        if(creditHours < 1 || creditHours > 4) {
            throw new IncorrectArguementsException("Credit hours must be between 1 and 5 inclusive!");
        } else {
            //Make sure Credit hours is greater than 0
            /*Return list of courses that has the specific amount of credit hours */

            data = CourseRepository.getCredits(creditHours);  // getCredits is not currently implemented

            if(data.isEmpty()){
                throw new CourseNotFoundException(String.format("Courses with %d Credit hours not found!", creditHours)); //Checks and returns 404 error if data information is empty
            }

            return data; //Returns course information if everything is correct
        }
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
                    CourseRepository.getHonorsCourses();   // Not currently implemented
            case "online" ->
                //we want to get a list of all online courses from the database
                    CourseRepository.getOnlineCourses();   // Not currently implemented
            case "lab" ->
                //we want to get a list of all lab courses from the database
                    CourseRepository.getLabCourses();     // Not currently implemented
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
        Course course = CourseRepository.findByCourseCode(courseName);   // Not implemented

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
        List<Course> courses = CourseRepository.findByTerm(term);   // findByTerm is not implemented

        if (courses != null && !courses.isEmpty()) {
            return courses;
        } else {
            throw new CourseNotFoundException("No courses found for term: " + term);
        }
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
    public List<CourseSection> getSectionsByTimeAndOrCrn(String timeSlot, int crn) {
        //Use our helper method to parse a start and end time for JPA calls
        try {
            Time startTime = parseTime(timeSlot.split(" - ")[0]);
            Time endTime = parseTime(timeSlot.split(" - ")[1]);

            //Make object to store returnList to make sure courses actually exist
            List<CourseSection> timeReturnList = new ArrayList<>();
            Set<String> seenTimes = new HashSet<>();


            List<CourseSection> timeStartReturnList = CourseRepository.findByStartTime(startTime);  // Not implemented
            List<CourseSection> timeEndReturnList = CourseRepository.findByEndTimeBetween(endTime, endTime);  // Not implemented

            // Create a map to store elements from timeEndReturnList by their time properties
            Map<String, CourseSection> endTimeMap = new HashMap<>();
            for (CourseSection endSection : timeEndReturnList) {
                String key = endSection.getStartTime().toString() + "-" + endSection.getEndTime().toString();
                endTimeMap.put(key, endSection);
            }

            // Iterate through timeStartReturnList and check for matches in endTimeMap
            for (CourseSection startSection : timeStartReturnList) {
                String key = startSection.getStartTime().toString() + "-" + startSection.getEndTime().toString();

                if (endTimeMap.containsKey(key) && !seenTimes.contains(key)) {
                    timeReturnList.add(startSection); // Add to result list
                    seenTimes.add(key); // Mark as seen
                }
            }

            if (timeReturnList != null) {
                if (crn != -1) { // if crn is provided
                    List<CourseSection> finalReturnList = null;
                    for (CourseSection section : timeReturnList) {
                        if (section.getCrn() == crn) {
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

    /**
     * Method to get the coordinates of a building based on the building number
     *
     * @param number The number of specified building
     * @return A string coordinate of the building
     */
    public String getCoordinatesByBuildingNumber(String number){
        String coordinate = buildingRepository.getCoordinatesByBuildingNumber(number);
        if (coordinate != null) {
            return coordinate;
        } else {
            throw new BuildingNotFoundException("Building Not Found");
        }
    }

}


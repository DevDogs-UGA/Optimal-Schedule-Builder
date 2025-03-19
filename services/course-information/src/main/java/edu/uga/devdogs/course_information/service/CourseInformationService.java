package edu.uga.devdogs.course_information.service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uga.devdogs.course_information.Building.Building;
import edu.uga.devdogs.course_information.Building.BuildingRepository;
import edu.uga.devdogs.course_information.Class.ClassRepository;
import edu.uga.devdogs.course_information.Course.Course;
import edu.uga.devdogs.course_information.Course.CourseRepository;
import edu.uga.devdogs.course_information.CourseSection.CourseSection;
import edu.uga.devdogs.course_information.CourseSection.CourseSectionRepository;
import edu.uga.devdogs.course_information.Professor.Professor;
import edu.uga.devdogs.course_information.Professor.ProfessorRepository;
import edu.uga.devdogs.course_information.exceptions.BuildingNotFoundException;
import edu.uga.devdogs.course_information.exceptions.CourseNotFoundException;
import edu.uga.devdogs.course_information.exceptions.ProfessorNotFoundException;

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
    private final ProfessorRepository professorRepository;

    @Autowired
    public CourseInformationService(CourseSectionRepository courseSectionRepository,
                                    ClassRepository classRepository,
                                    CourseRepository courseRepository,
                                    BuildingRepository buildingRepository, ProfessorRepository professorRepository) {
        this.courseSectionRepository = courseSectionRepository;
        this.classRepository = classRepository;
        this.courseRepository = courseRepository;
        this.buildingRepository = buildingRepository;
        this.professorRepository = professorRepository;
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

    /**
     * Retrieves the average RateMyProfessors rating for a given professor.
     * 
     * @param lastName  the professor's last name
     * @param firstName the professor's first name
     * @return the average rating as a float
     * @throws ProfessorNotFoundException if the professor is not found
     */
    public float getProfessorAverageRating(String lastName, String firstName) {
        Professor professor = professorRepository.findByLastNameAndFirstNameIgnoreCase(lastName, firstName);

        if (professor == null) {
            throw new ProfessorNotFoundException("Professor " + firstName + " " + lastName + " not found");
        }

        return professor.getAverageRating();
    }


    /**
     * Retrieves the total number of RateMyProfessors reviews for a given professor.
     * 
     * @param lastName  the professor's last name
     * @param firstName the professor's first name
     * @return the total number of reviews as an int
     * @throws ProfessorNotFoundException if the professor is not found
     */
    public int getProfessorTotalReviews(String lastName, String firstName) {
        Professor professor = professorRepository.findByLastNameAndFirstNameIgnoreCase(lastName, firstName);

        if (professor == null) {
            throw new ProfessorNotFoundException("Professor " + firstName + " " + lastName + " not found");
        }

        return professor.getTotalReviews(); // Assuming totalReviews is an int field in Professor entity
    }




}


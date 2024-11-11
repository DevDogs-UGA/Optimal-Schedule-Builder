// package edu.uga.devdogs.course_information.service;

// import edu.uga.devdogs.course_information.Class.ClassRepository;
// import edu.uga.devdogs.course_information.Class.Class;
// import edu.uga.devdogs.course_information.Course.Course;
// import edu.uga.devdogs.course_information.Course.CourseRepository;
// import edu.uga.devdogs.course_information.CourseSection.CourseSectionRepository;
// import edu.uga.devdogs.course_information.exceptions.SectionDetailsNotFoundException;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import edu.uga.devdogs.course_information.exceptions.CourseNotFoundException;

// import java.sql.Time;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.util.*;

// /**
//  * Service class that handles business logic for managing course information.
//  *
//  * <p>
//  * This service layer is responsible for providing methods related to course
//  * information management, including retrieving class schedules and course details
//  * based on specific criteria. It acts as an intermediary between the controller
//  * layer and the data repository, ensuring that all course information operations
//  * are correctly managed and returned to the client.
//  * </p>
//  *
//  * <p>
//  * This class may be extended in the future to include more complex data
//  * processing and transformation related to course information.
//  * </p>
//  *
//  * @see CourseInformationService
//  * @since 1.0
//  */
// @Service
// public class CourseInformationService {
//     // Methods go here

//     //Inject our JPA repository interfaces
//     private final CourseSectionRepository courseSectionRepository;
//     private final ClassRepository classRepository;
//     private final CourseRepository courseRepository;

//     //Use constructor to inject
//     @Autowired
//     public CourseInformationService(CourseSectionRepository courseSectionRepository, ClassRepository classRepository, CourseRepository courseRepository) {
//         this.courseSectionRepository = courseSectionRepository;
//         this.classRepository = classRepository;
//         this.courseRepository = courseRepository;
//     }

//     /**
//      * Retrieves a list of Course section (class) objects that match the specified time slot and CRN (Course Reference Number).
//      *
//      * <p>
//      * This method queries the data repository for classes scheduled in a given time slot
//      * and having the specified CRN. The underlying repository method will be implemented later.
//      * </p>
//      *
//      * @param timeSlot the time slot for which to retrieve class information (e.g., "10:00 AM - 11:15 AM")
//      * @param crn the course reference number to identify a specific class
//      * @return a list of {@link Class} objects that match the given time slot and CRN
//      * @throws CourseNotFoundException if no classes are found for the specified time slot and CRN
//      */
//     public List<Class> getClassesByCrnAndTime(String timeSlot, String crn) {

//         //Use our helper method to parse a start and end time for JPA calls
//         try {
//             Time startTime = parseTime(timeSlot.split(" - ")[0]);
//             Time endTime = parseTime(timeSlot.split(" - ")[1]);

//             //Make object to store returnList to make sure courses actually exist
//             List<Class> timeReturnList = new ArrayList<>();
//             Set<String> seenTimes = new HashSet<>();


//             List<Class> timeStartReturnList = classRepository.findByStartTime(startTime);
//             List<Class> timeEndReturnList = classRepository.findByEndTimeBetween(endTime, endTime);

//             // Create a map to store elements from timeEndReturnList by their time properties
//             Map<String, Class> endTimeMap = new HashMap<>();
//             for (Class endClass : timeEndReturnList) {
//                 String key = endClass.getStartTime().toString() + "-" + endClass.getEndTime().toString();
//                 endTimeMap.put(key, endClass);
//             }

//             // Iterate through timeStartReturnList and check for matches in endTimeMap
//             for (Class startClass : timeStartReturnList) {
//                 String key = startClass.getStartTime().toString() + "-" + startClass.getEndTime().toString();

//                 if (endTimeMap.containsKey(key) && !seenTimes.contains(key)) {
//                     timeReturnList.add(startClass); // Add to result list
//                     seenTimes.add(key); // Mark as seen
//                 }
//             }

//             if (timeReturnList != null)
//                 return timeReturnList;
//             else
//                 throw new CourseNotFoundException("Class not found for timeSlot: " + timeSlot + " and CRN: " + crn);
//         } catch (ParseException e) {
//             System.err.println("Failed to parse time: " + e.getMessage());
//         }
//     }

//     /**
//      * Method to get a list of section details (tiemslot) matching the given CRN. The method
//      * it calls in the JPA layer will be implemented later.
//      *
//      * @param crn the given course reference number for which to retrieve section details
//      * @return A list of Section Details objects matching the given time slot and CRN
//      */
//     public List<Class> getSectionDetailsByCrn(String crn){
//         List<Class> returnList;

//         returnList = SectionDetailsJPAFile.getSectionDetailsByCrn(crn);

//         if (returnList != null)
//             return returnList;
//         else
//             throw new SectionDetailsNotFoundException("Section details not found for CRN: " + crn);
//     }

//     /**
//      * Method to get a list of Course objects matching the given major. The method
//      * it calls in the JPA layer will be implemented later.
//      * 
//      * @param major the given major for which to retrieve courses
//      * @return a list of Course objects matching the given major
//      */
//     public List<Course> getCoursesByMajor(String major) {
//         List<Course> returnList;

//         returnList = courseSectionRepository.getCoursesBySubject(major);

//         if (returnList != null) {
//             return returnList;
//         } else {
//             throw new CourseNotFoundException("Courses not found for major: " + major);
//         }
//     }

//     /**
//      * Retrieves all course sections taught by a specific professor.
//      *
//      * @param professorId The unique identifier of the professor
//      * @return List of course sections taught by the specified professor
//      * @throws ProfessorNotFoundException if the professor is not found
//      */
//     public List<CourseSection> getCourseSectionsByProfessor(Long professorId) {   
        
//         // Fetch course sections by professor ID
//         List<CourseSection> returnList = courseInfoJPAFile.getCourseSectionsByProfessor(professorId);

//         if (returnList != null) {
//             return returnList;
//         } else {
//             throw new ProfessorNotFoundException("Professor not found with ID: " + professorId);
//         }
//     }
   
//     /* 
//      * This method is a helper method to parse our timeslots (10:00 AM - 11:15 AM)
//      */
//     private static Time parseTime(String timeString) throws ParseException {
//         SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
//         Date parsedDate = sdf.parse(timeString);
//         return new Time(parsedDate.getTime());
//     }

//     /**
//      * Retreives course based on Athena name
//      *
//      *  @param athenaName The Athena name of the course
//      *  @return The {@link Course} object matching the given Athena name
//      * @throws CourseNotFoundException if no course is found for the specified Athena name
//      */
//     public Course getCourseByAthenaName(String athenaName) {
//         Course course = courseRepository.findByAthenaName(athenaName);

//         if (course != null) {
//             return course;
//         } else {
//             throw new CourseNotFoundException("Course not found for Athena name: " + athenaName);
//         }

// }

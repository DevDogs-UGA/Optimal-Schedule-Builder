package edu.uga.devdogs.course_information;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.uga.devdogs.course_information.webscraping.DescriptionScraper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.devdogs.course_information.Professor.Professor;
import edu.uga.devdogs.course_information.Professor.ProfessorRepository;
import edu.uga.devdogs.course_information.Building.Building;
import aj.org.objectweb.asm.TypeReference;
import edu.uga.devdogs.course_information.Building.BuildingRepository;
import edu.uga.devdogs.course_information.Class.ClassRepository;
import edu.uga.devdogs.course_information.Course.Course;
import edu.uga.devdogs.course_information.Course.CourseRepository;
import edu.uga.devdogs.course_information.CourseSection.CourseSection;
import edu.uga.devdogs.course_information.CourseSection.CourseSectionRepository;
import edu.uga.devdogs.course_information.service.ProfessorService;
import edu.uga.devdogs.course_information.webscraping.Course2;
import edu.uga.devdogs.course_information.webscraping.Pdf;
// Ensure the correct package path for RateMyProfessorScraper
//import edu.uga.devdogs.professor_rating.webscraping.RateMyProfessorScraper;

import java.nio.file.Files;
import java.nio.file.Paths;
import edu.uga.devdogs.course_information.webscraping.Pdf;

@SpringBootApplication
@EnableTransactionManagement 
public class CourseInformationApplication {

    private final CourseRepository courseRepository;


    private final BuildingRepository buildingRepository;

    private final ProfessorRepository professorRepository;

    @Value("${update.buildings}")
    private boolean updateBuildings;

    CourseInformationApplication(BuildingRepository buildingRepository, CourseRepository courseRepository, ProfessorRepository professorRepository) {
        this.courseRepository = courseRepository;
        this.buildingRepository = buildingRepository;
        this.professorRepository = professorRepository;
        // Removed incorrect initialization of professorService
    }

    public static void main(String[] args) {
        SpringApplication.run(CourseInformationApplication.class, args);
    }

    @Bean
    @Order(1)
    CommandLineRunner courseSecCommandLineRunner(
        CourseSectionRepository courseSectionRepository,
        CourseRepository courseRepository,
        ClassRepository classRepository,
        BuildingRepository buildingRepository) {
        
            return args -> {
                
                List<Course2> courses = Pdf.parsePdf("spring", "C:\\kadestyron\\d\\Desktop"); 
                List<Course> courseEntities = new ArrayList<>();
                List<CourseSection> courseSections = new ArrayList<>();
                Set<String> processedCourses = new HashSet<>(); // Track processed courses to avoid duplicates
                
                for (Course2 course : courses) {
                    String courseKey = course.getSubject() + "-" + course.getCourseNumber(); // Unique key for course
    
                    // Skip if course has already been processed
                    if (processedCourses.contains(courseKey)) {
                        continue;
                    }
    
                    String creditHoursStr = course.getCreditHours().trim();
                    creditHoursStr = creditHoursStr.replaceAll("[^0-9.-]", "").trim();
                    int creditHours = 3; 
    
                    if (creditHoursStr.contains("-")) {
                        String[] parts = creditHoursStr.split("-");
                        try {
                            double firstValue = Double.parseDouble(parts[0].trim());
                            creditHours = (int) firstValue; 
                        } catch (NumberFormatException e) {
                            creditHours = 3; 
                            System.err.println("Invalid credit hours range: " + course.getCreditHours() + ", defaulting to 3.");
                        }
                    }
    
                    // Scrape the professor information using RateMyProfessorScraper
                    // String professorName = course.getProfessor();
                    // if (professorName != null && !professorName.isEmpty()) {
                    //     int professorId = RateMyProfessorScraper.getRateMyProfessorId(professorName);




                    //     if (professorId != -1) { // Assuming -1 means no valid professor found
                    //         Professor existingProfessor = professorRepository.findById(professorId).orElse(null);

                    //         if (existingProfessor == null) {
                    //             // Fetch and save the professor's details
                    //             Professor professor = new Professor(professorId, professorName);
                    //             professorRepository.save(professor);
                    //         }
                    //     }
                    // }

                    // Check if the course already exists
                    Course existingCourse = courseRepository.findBySubjectAndCourseNumber(course.getSubject(), course.getCourseNumber());
                    Course courseEntity;
                    
                    if (existingCourse == null) {
                        // Scrape the course description if it doesn't exist in the database
                        String courseDescription = DescriptionScraper.getCourseDescription(course.getSubject(), course.getCourseNumber());
    
                        // Save new course with the scraped description
                        courseEntity = new Course(
                            course.getSubject(), 
                            course.getCourseNumber(), 
                            course.getTitle(), 
                            course.getDepartment(), 
                            courseSections, 
                            courseDescription // Store description in Course
                        );
    
                        courseEntities.add(courseEntity);
                    } else {
                        courseEntity = existingCourse;
                    }
    
                    // Add course key to the processed set to avoid further scraping
                    processedCourses.add(courseKey);
    
                    // Check if a section with the same CRN already exists
                    CourseSection existingSection = courseSectionRepository.findByCrn(course.getCrn());
    
                    if (existingSection == null || existingSection.getSeatsAvailable() != course.getAvailableSeats()) {
                        CourseSection courseSection = new CourseSection(
                            course.getCrn(),
                            course.getSec(), 
                            (course.getStat()).charAt(0), 
                            creditHours, 
                            course.getProfessor(), 
                            course.getPartOfTerm(), 
                            course.getClassSize(), 
                            course.getAvailableSeats(), 
                            0, 
                            courseEntity,
                            null,
                            course.getMeetingDays(),
                            course.getMeetingTimes(), 
                            creditHoursStr
                        );
    
                        courseSections.add(courseSection);
                    }
                }
                
                System.out.println("Saving to database...");
                courseRepository.saveAll(courseEntities);
                courseSectionRepository.saveAll(courseSections);
                System.out.println("Saved to database");
            };
    }

    
    @Bean
    @Order(2)
    CommandLineRunner buildingCommandLineRunner (BuildingRepository buildingRepository, CourseSectionRepository courseSectionRepository) {
        return args -> {
            
            List<Course2> courses = Pdf.parsePdf("spring", "C:\\kadestyron\\d\\Desktop"); 
            List<Course> courseEntities = new ArrayList<>();
            List<CourseSection> courseSections = new ArrayList<>();
            Set<String> processedCourses = new HashSet<>(); // Track processed courses to avoid duplicates
            
            
            // Removed redundant declaration of courseSectionRepository
            for (Course2 course : courses) {
                String courseKey = course.getSubject() + "-" + course.getCourseNumber(); // Unique key for course

                // Skip if course has already been processed
                if (processedCourses.contains(courseKey)) {
                    continue;
                }

                String creditHoursStr = course.getCreditHours().trim();
                creditHoursStr = creditHoursStr.replaceAll("[^0-9.-]", "").trim();
                int creditHours = 3; 

                if (creditHoursStr.contains("-")) {
                    String[] parts = creditHoursStr.split("-");
                    try {
                        double firstValue = Double.parseDouble(parts[0].trim());
                        creditHours = (int) firstValue; 
                    } catch (NumberFormatException e) {
                        creditHours = 3; 
                        System.err.println("Invalid credit hours range: " + course.getCreditHours() + ", defaulting to 3.");
                    }
                }

                // Check if the course already exists
                Course existingCourse = courseRepository.findBySubjectAndCourseNumber(course.getSubject(), course.getCourseNumber());
                Course courseEntity;
                
                if (existingCourse == null) {
                    // Scrape the course description if it doesn't exist in the database
                    String courseDescription = DescriptionScraper.getCourseDescription(course.getSubject(), course.getCourseNumber());

                    // Save new course with the scraped description
                    courseEntity = new Course(
                        course.getSubject(), 
                        course.getCourseNumber(), 
                        course.getTitle(), 
                        course.getDepartment(), 
                        courseSections, 
                        courseDescription // Store description in Course
                    );

                    courseEntities.add(courseEntity);
                } else {
                    courseEntity = existingCourse;
                }

                // Add course key to the processed set to avoid further scraping
                processedCourses.add(courseKey);


                Professor existingProfessor = professorRepository.findByName(course.getProfessor());

                String professorName = course.getProfessor();
                if (professorName != null && !professorName.isEmpty() && !professorName.equalsIgnoreCase("TBA")) {

                    // if (existingProfessor == null) {
                    //     // Get additional professor data
                    //     int professorId = RateMyProfessorScraper.getRateMyProfessorId(professorName);
                    //     if (professorId != -1) {
                    //         double rating = RateMyProfessorScraper.getRating(professorId);
                    //         int difficulty = RateMyProfessorScraper.getDifficultyLevel(professorId);
                    //         int wouldTakeAgain = RateMyProfessorScraper.getWouldTakeAgainPercentage(professorId);

                    //         // Create and save the professor with detailed information
                    //         Professor professor = new Professor(professorId, professorName);
                    //         professor.setRating(rating);
                    //         professor.setDifficulty(difficulty);
                    //         professor.setWouldTakeAgainPercentage(wouldTakeAgain);
                    //         professorRepository.save(professor);

                    //         System.out.println("Added professor: " + professorName + " with ID: " + professorId);
                    //     } else {
                    //         System.out.println("Could not find RateMyProfessor ID for: " + professorName);
                    //     }
                    //}
                } 
        

                // Check if a section with the same CRN already exists
                CourseSection existingSection = courseSectionRepository.findByCrn(course.getCrn());

                if (existingSection == null || existingSection.getSeatsAvailable() != course.getAvailableSeats()) {
                    CourseSection courseSection = new CourseSection(
                        course.getCrn(),
                        course.getSec(), 
                        (course.getStat()).charAt(0), 
                        creditHours, 
                        course.getProfessor(), //will be replaced with the professor object
                        course.getPartOfTerm(), 
                        course.getClassSize(), 
                        course.getAvailableSeats(), 
                        0, 
                        courseEntity,
                        null,
                        course.getMeetingDays(),
                        course.getMeetingTimes(), 
                        creditHoursStr
                    );

                    courseSections.add(courseSection);
                }
            } 
            
            System.out.println("Saving to database...");
            courseRepository.saveAll(courseEntities);
            courseSectionRepository.saveAll(courseSections);
            System.out.println("Saved to database");
        };
    }
}
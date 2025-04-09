package edu.uga.devdogs.course_information;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.uga.devdogs.course_information.webscraping.DescriptionScraper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
import edu.uga.devdogs.course_information.Class.ClassEntity;
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
import edu.uga.devdogs.course_information.webscraping.RateMyProfessorScraper;

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
    @Order(2)
    CommandLineRunner courseSecCommandLineRunner(
        CourseSectionRepository courseSectionRepository,
        CourseRepository courseRepository,
        ClassRepository classRepository,
        BuildingRepository buildingRepository) {
        
            return args -> {


                // Set up WebDriver for Firefox
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                WebDriver driver = new FirefoxDriver(options);

                DescriptionScraper scraper = new DescriptionScraper(driver);


                // pdf info
                List<Course2> courses = Pdf.parsePdf("spring", "C:\\kadestyron\\d\\Desktop"); 

                List<Course> courseEntities = new ArrayList<>();
                List<ClassEntity> classEntities = new ArrayList<>();
                List<CourseSection> courseSections = new ArrayList<>();
                Set<String> processedCourses = new HashSet<>(); // Track processed courses to avoid duplicates
                Map<String, Integer> professorIdCache = new HashMap<>();

                


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
    
                    //Scrape the professor information using RateMyProfessorScraper

                    
                    String professorLastName = course.getProfessor();

                    Professor existingProfessor = null;
                    if (professorIdCache.containsKey(professorLastName)) {
                        Integer professorId = professorIdCache.get(professorLastName);
                        existingProfessor = professorRepository.findById(professorId).orElse(null);
                    }

                    if (professorLastName != null && !professorLastName.trim().isEmpty()) {

                        professorLastName = professorLastName.trim();

                        // Only call RMP scraper if this professor doesn't already exist in DB
                        existingProfessor = professorRepository.findByLastName(professorLastName);
                        
                        if (existingProfessor == null) {

                            int professorId = RateMyProfessorScraper.getRateMyProfessorId(professorLastName);

                            Professor professor;
                            if (professorId != -1) {
                                professor = new Professor(
                                    professorLastName,
                                    "", // First name unknown
                                    RateMyProfessorScraper.getRating(professorId),
                                    RateMyProfessorScraper.getDifficultyLevel(professorId),
                                    RateMyProfessorScraper.getWouldTakeAgainPercentage(professorId)
                                );
                            } else {
                                System.out.println("RMP ID not found for: " + professorLastName);
                                professor = new Professor(
                                    professorLastName,
                                    "", // First name unknown
                                    0,
                                    0.0f,
                                    0
                                );
                            }

                            professorRepository.save(professor);
                        }

                    // Check if the course already exists
                    Course existingCourse = courseRepository.findBySubjectAndCourseNumber(course.getSubject(), course.getCourseNumber());
                    Course courseEntity;
                    
                    if (existingCourse == null) {
                        // Scrape the course description if it doesn't exist in the database
                        String courseDescription = "Description not found";
                        try {
                            courseDescription = scraper.getCourseDescription(course.getSubject(), course.getCourseNumber());
                        } catch (Exception e) {
                            System.err.println("Error fetching course description (" + course.getSubject() + " " + course.getCourseNumber() + "): ");
                            driver.quit();
                            return;
                        }
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
    
                    if (existingSection == null || existingSection.getSeatsAvailable() != course.getAvailableSeats() || existingSection.getInstructor() != course.getProfessor()) {

                        if (existingSection != null) {
                            courseSectionRepository.delete(existingSection);
                        }

                        
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
                            course.getMeetingTimes()
                        );
    
                        existingSection = courseSection;

                        Building building = buildingRepository.findByBuildingCode(course.getBuilding());
                        if (building == null) {
                            System.err.println("Building not found: " + course.getBuilding());
                            continue; // Skip this course if the building is not found
                        }
                        ClassEntity classEntity = new ClassEntity(
                            course.getMeetingDays(),
                            course.getMeetingTimes(),
                            building,
                            course.getRoomNumber(),
                            course.getCampus(), 
                            existingSection
                        );
                        

                        courseSections.add(courseSection);
                        classEntities.add(classEntity);
                    }
                }
            }
            System.out.println("Saving to database...");
            courseRepository.saveAll(courseEntities);

            System.out.println("Saved courses to database");
            courseSectionRepository.saveAll(courseSections);

            System.out.println("Saved course sections to database");
            
            classRepository.saveAll(classEntities);
            System.out.println("Saved classes to database");

            
            System.out.println("Saved to database");
        };
    }

    
    @Bean
    @Order(1)
    CommandLineRunner buildingCommandLineRunner (BuildingRepository buildingRepository) {
        return args -> {
            String buildingsJsonPath = "buildingData/AthensBuildingData.json";
            ClassPathResource resource = new ClassPathResource(buildingsJsonPath);

            if (!resource.exists()) {
                System.err.println("File not found at path: " + buildingsJsonPath);
                return;
            }

            try (InputStream inputStream = resource.getInputStream()) {
                ObjectMapper objectMapper = new ObjectMapper();
                CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, Building.class);
                List<Building> buildings = objectMapper.readValue(inputStream, listType);

                System.out.println("Parsed JSON successfully.");

                List<Building> buildingsToSave = new ArrayList<>();
                for (Building building : buildings) {
                    if (!buildingRepository.existsById(building.getBuildingCode())) {
                        buildingsToSave.add(building);
                    }
                }

                if (!buildingsToSave.isEmpty()) {
                    buildingRepository.saveAll(buildingsToSave);
                    System.out.println("Buildings saved successfully.");
                } else {
                    System.out.println("No new buildings to save.");
                }

            } catch (IOException e) {
                System.err.println("Failed to read or parse buildings.json: " + e.getMessage());
            }
       };
    }
}
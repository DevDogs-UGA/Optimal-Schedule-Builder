package edu.uga.devdogs.course_information;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.devdogs.course_information.Building.Building;
import aj.org.objectweb.asm.TypeReference;
import edu.uga.devdogs.course_information.Building.BuildingRepository;
import edu.uga.devdogs.course_information.Class.ClassRepository;
import edu.uga.devdogs.course_information.Course.Course;
import edu.uga.devdogs.course_information.Course.CourseRepository;
import edu.uga.devdogs.course_information.CourseSection.CourseSection;
import edu.uga.devdogs.course_information.CourseSection.CourseSectionRepository;
import edu.uga.devdogs.course_information.webscraping.Course2;
import edu.uga.devdogs.course_information.webscraping.Pdf;
import java.nio.file.Files;
import java.nio.file.Paths;
import edu.uga.devdogs.course_information.webscraping.Pdf;

@SpringBootApplication
public class CourseInformationApplication {


    private final BuildingRepository buildingRepository;

    CourseInformationApplication(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
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
            List<Course2> courses = Pdf.parsePdf("spring", "C:\\kadestyron\\d\\Desktop"); // change this to your computer specifc path
            List<Course> courseEntities = new ArrayList<>();
            List<CourseSection> courseSections = new ArrayList<>();
            
            for(Course2 course : courses) {
                // if(course.getSec().equals("C")) {
                //     continue;
                // }

                //System.out.print(course.getCrn() + " " + course.getSec() + " " +course.getCreditHours() + "  | ");

                Course courseEntity = new Course(
                    course.getSubject(), course.getCourseNumber(), course.getTitle(), course.getDepartment(), null );
                CourseSection courseSection = new CourseSection(
                course.getCrn(), course.getSec(), (course.getStat()).charAt(0),  course.getCreditHours(), course.getCreditHours(), course.getProfessor(), course.getPartOfTerm(), course.getClassSize(), course.getAvailableSeats(), courseEntity, null);   
                //System.out.println(courseSection.getTerm()); 
                
                courseEntities.add(courseEntity);
                
                if (courseSection.getSeatsAvailable() > 0) {
                    courseSections.add(courseSection);
                }

                // courseRepository.save(courseEntity);
                // //only save if it has a seat available
                // if(courseSection.getSeatsAvailable() > 0) {
                //     courseSectionRepository.save(courseSection);
                // }

            }
            System.out.println("Saving to database...");
            courseRepository.saveAll(courseEntities);
            courseSectionRepository.saveAll(courseSections);
            System.out.println("Saved to database");

            // // Create Courses
            // Course course1 = new Course("physiology", "420", "pain", "Mary Francis early education", null);
            // Course course2 = new Course("math", "1101", "intro", "Department of Mathematics", null);

            // course1.setCourseDescription("pain");
            // course1.setSemesters(Stream.of("Spring", "Summer").toList());

            // course2.setCourseDescription("intro");
            // course2.setSemesters(Stream.of("Fall", "Spring").toList());

            // courseRepository.saveAll(List.of(course1, course2));

            // // Create Course Sections
            // CourseSection section1 = new CourseSection(
            //     123456, 4, 'A', 1.0, 4.0, "Barnes", 2, 40, 40, 2024, course1, null
            // );

            // CourseSection section2 = new CourseSection(
            //     654321, 3, 'B', 2.0, 3.5, "Smith", 1, 30, 30, 2024, course2, null
            // );

            // courseSectionRepository.saveAll(List.of(section1, section2));

            // // Create ClassEntities
            // ClassEntity class1 = new ClassEntity(
            //     "MWF", "08:00:00", "09:15:00", building10, "101", "Main Campus", section1
            // );

            // ClassEntity class2 = new ClassEntity(
            //     "TR", "13:00:00", "14:15:00", building6, "205", "North Campus", section1
            // );

            // ClassEntity class3 = new ClassEntity(
            //     "MWF", "10:00:00", "11:15:00", building2, "301", "Main Campus", section2
            // );

            // ClassEntity class4 = new ClassEntity(
            //     "TR", "15:00:00", "16:15:00", building5, "102", "North Campus", section2
            // );

            // classRepository.saveAll(List.of(class1, class2, class3, class4));

            // // Link classes to sections
            // section1.setClasses(List.of(class1, class2));
            // section2.setClasses(List.of(class3, class4));
            // courseSectionRepository.saveAll(List.of(section1, section2));

            // // Print Test Data
            // System.out.println("\n\n\n\n\n\nAll CourseSections for Instructor Barnes:");
            // System.out.println(courseSectionRepository.findAllByInstructor("Barnes"));

            // System.out.println("\nAll Courses with Subject 'physiology':");
            // System.out.println(courseRepository.findAllBySubject("physiology"));

            // System.out.println("\nBuilding with ID '178':");
            // System.out.println(buildingRepository.findById("178"));

            // System.out.println("\nAll Courses with Spring Semester:");
            // System.out.println(courseRepository.findAllBySemester("Spring"));
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
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
                    course.getCrn(),
                    course.getSec(), 
                    (course.getStat()).charAt(0), 
                    (int) Math.round(Double.parseDouble(course.getCreditHours().split(" ")[0])),
                    course.getProfessor(), 
                    course.getPartOfTerm(), 
                    course.getClassSize(), 
                    course.getAvailableSeats(), 
                    0, 
                    courseEntity,
                    null,
                    course.getMeetingDays(),
                    course.getMeetingTimes());   

                //System.out.println(courseSection.getTerm()); 
                
                courseEntities.add(courseEntity);
                
                if (courseSection.getClassSize() > 0) {
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
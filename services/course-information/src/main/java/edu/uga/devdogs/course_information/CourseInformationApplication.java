package edu.uga.devdogs.course_information;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.uga.devdogs.course_information.Building.BuildingRepository;
import edu.uga.devdogs.course_information.service.BuildingService;
import edu.uga.devdogs.course_information.Class.ClassEntity;
import edu.uga.devdogs.course_information.Class.ClassRepository;
import edu.uga.devdogs.course_information.Course.Course;
import edu.uga.devdogs.course_information.Course.CourseRepository;
import edu.uga.devdogs.course_information.CourseSection.CourseSection;
import edu.uga.devdogs.course_information.CourseSection.CourseSectionRepository;
import edu.uga.devdogs.course_information.webscraping.Course2;
import edu.uga.devdogs.course_information.webscraping.Pdf;

@SpringBootApplication
public class CourseInformationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseInformationApplication.class, args);
    }

    // First CommandLineRunner for Buildings
    @Bean
    CommandLineRunner initDatabase(BuildingService buildingService) {
        return args -> {
            buildingService.saveBuildingsFromJson();
        };
    }

    // Second CommandLineRunner for Course Data
    @Bean
    CommandLineRunner courseSecCommandLineRunner(
        CourseSectionRepository courseSectionRepository,
        CourseRepository courseRepository,
        ClassRepository classRepository,
        BuildingRepository buildingRepository) {

        return args -> {

            // Example hardcoded courses
            Course course1 = new Course("physiology", "420", "pain", "Mary Francis early education", null);
            Course course2 = new Course("math", "1101", "intro", "Department of Mathematics", null);

            courseRepository.saveAll(List.of(course1, course2));

            // Process Course2 entities from the PDF
            List<Course2> courses = Pdf.parsePdf("spring", "C:\\Users\\bryan\\OneDrive\\Desktop"); // Update to your specific path

            for (Course2 course : courses) {
                System.out.println(course.toString());

                Course courseEntity = new Course(
                        course.getSubject(), 
                        course.getCourseNumber(), 
                        course.getTitle(), 
                        course.getDepartment(), 
                        null
                );

                courseRepository.save(courseEntity);

                CourseSection courseSection = new CourseSection(
                        course.getCrn(), 
                        1, 
                        'Z',  
                        99, 
                        99, 
                        course.getProfessor(), 
                        99, 
                        course.getClassSize(), 
                        course.getAvailableSeats(), 
                        99, 
                        courseEntity, 
                        null
                );

                courseSectionRepository.save(courseSection);
            }

            // Print Test Data
            System.out.println("\n\n\nAll CourseSections for Instructor Barnes:");
            System.out.println(courseSectionRepository.findAllByInstructor("Barnes"));

            System.out.println("\nAll Courses with Subject 'physiology':");
            System.out.println(courseRepository.findAllBySubject("physiology"));

            System.out.println("\nAll Courses with Spring Semester:");
            System.out.println(courseRepository.findAllBySemester("Spring"));
        };
    }
}
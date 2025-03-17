package edu.uga.devdogs.course_information;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.uga.devdogs.course_information.Building.BuildingRepository;
import edu.uga.devdogs.course_information.service.BuildingService; // ✅ Moved import to the top
import edu.uga.devdogs.course_information.Class.ClassEntity;
import edu.uga.devdogs.course_information.Class.ClassRepository;
import edu.uga.devdogs.course_information.Course.Course;
import edu.uga.devdogs.course_information.Course.CourseRepository;
import edu.uga.devdogs.course_information.CourseSection.CourseSection;
import edu.uga.devdogs.course_information.CourseSection.CourseSectionRepository;

@SpringBootApplication
public class CourseInformationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseInformationApplication.class, args);
    }

    // ✅ First CommandLineRunner for Buildings
    @Bean
    CommandLineRunner initDatabase(BuildingService buildingService) {
        return args -> {
            buildingService.saveBuildingsFromJson();
        };
    }

    // ✅ Second CommandLineRunner for Course Data
    @Bean
    CommandLineRunner courseSecCommandLineRunner(
        CourseSectionRepository courseSectionRepository,
        CourseRepository courseRepository,
        ClassRepository classRepository,
        BuildingRepository buildingRepository) {

        return args -> {
            // Create Courses
            Course course1 = new Course("physiology", "420", "pain", "Mary Francis early education", null);
            Course course2 = new Course("math", "1101", "intro", "Department of Mathematics", null);

            course1.setCourseDescription("pain");
            course1.setSemesters(Stream.of("Spring", "Summer").toList());

            course2.setCourseDescription("intro");
            course2.setSemesters(Stream.of("Fall", "Spring").toList());

            courseRepository.saveAll(List.of(course1, course2));

            // Create Course Sections
            CourseSection section1 = new CourseSection(
                123456, 4, 'A', 1.0, 4.0, "Barnes", 2, 40, 40, 2024, course1, null
            );

            CourseSection section2 = new CourseSection(
                654321, 3, 'B', 2.0, 3.5, "Smith", 1, 30, 30, 2024, course2, null
            );

            courseSectionRepository.saveAll(List.of(section1, section2));

            // Create ClassEntities (Buildings are set to `null` for now)
            ClassEntity class1 = new ClassEntity(
                "MWF", "08:00:00", "09:15:00", null, "101", "Main Campus", section1
            );

            ClassEntity class2 = new ClassEntity(
                "TR", "13:00:00", "14:15:00", null, "205", "North Campus", section1
            );

            ClassEntity class3 = new ClassEntity(
                "MWF", "10:00:00", "11:15:00", null, "301", "Main Campus", section2
            );

            ClassEntity class4 = new ClassEntity(
                "TR", "15:00:00", "16:15:00", null, "102", "North Campus", section2
            );

            classRepository.saveAll(List.of(class1, class2, class3, class4));

            // Link classes to sections
            section1.setClasses(List.of(class1, class2));
            section2.setClasses(List.of(class3, class4));
            courseSectionRepository.saveAll(List.of(section1, section2));

            // ✅ Print Test Data
            System.out.println("\n\n\nAll CourseSections for Instructor Barnes:");
            System.out.println(courseSectionRepository.findAllByInstructor("Barnes"));

            System.out.println("\nAll Courses with Subject 'physiology':");
            System.out.println(courseRepository.findAllBySubject("physiology"));

            System.out.println("\nAll Courses with Spring Semester:");
            System.out.println(courseRepository.findAllBySemester("Spring"));
        };
    }
}

package edu.uga.devdogs.course_information;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import edu.uga.devdogs.course_information.Class.ClassEntity;
import edu.uga.devdogs.course_information.Class.ClassRepository;
import edu.uga.devdogs.course_information.Course.Course;
import edu.uga.devdogs.course_information.Course.CourseRepository;
import edu.uga.devdogs.course_information.CourseSection.CourseSection;
import edu.uga.devdogs.course_information.Building.Building;
import edu.uga.devdogs.course_information.CourseSection.CourseSectionRepository;
import edu.uga.devdogs.course_information.Building.BuildingRepository;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class CourseInformationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseInformationApplication.class, args);
    }

    @Bean
    CommandLineRunner courseSecCommandLineRunner(
        CourseSectionRepository courseSectionRepository,
        CourseRepository courseRepository,
        ClassRepository classRepository,
        BuildingRepository buildingRepository) {
        return args -> {
            // Create Buildings
            Building building1 = new Building("2438", "CAGTECH", "F - 6");
            Building building2 = new Building("46", "Caldwell Hall", "C - 1");
            Building building3 = new Building("2118", "Campus Mail/Environmental Safety", "E - 6");
            Building building4 = new Building("1637", "Campus Transit Facility", "D - 8");
            Building building5 = new Building("31", "Candler Hall", "C - 1");
            Building building6 = new Building("1110", "Carlton Street Deck", "B - 4");
            Building building7 = new Building("2419", "CCRC", "E - 7");
            Building building8 = new Building("2127", "Center for Applied Isotope Study", "E - 6");
            Building building9 = new Building("2395", "Center for Molecular Medicine", "E - 7");
            Building building10 = new Building("178", "Central Campus Mech. Building", "C - 2");

            buildingRepository.saveAll(List.of(building1, building2, building3, building4, building5, building6, building7, building8, building9, building10));

			Course course1 = new Course("physiology", "420", "pain", "Mary Francis early education", null);
			course1.setCourseDescription("pain"); // Setting description separately
			course1.setSemesters(Stream.of("Spring", "Summer").toList());
			
			Course course2 = new Course("math", "1101", "intro", "Department of Mathematics", null);
			course2.setCourseDescription("intro"); // Setting description separately
			course2.setSemesters(Stream.of("Fall", "Spring").toList());

            courseRepository.saveAll(List.of(course1, course2));

            // Create CourseSections and link them to Courses
			CourseSection section1 = new CourseSection(
				123456, 4, 'A', 1.0, 4.0, "Barnes", 2, 40, 40, 2024, course1, null
			);

            CourseSection section2 = new CourseSection(
                654321, 3, 'B', 2.0, 3.5, "Smith", 1, 30, 30, 2024, course2, null
            );

            courseSectionRepository.saveAll(List.of(section1, section2));

            // Create ClassEntities and link them to CourseSections and Buildings
            ClassEntity class1 = new ClassEntity(
                "MWF", "08:00:00", "09:15:00", building10, "101", "Main Campus", section1
            );

            ClassEntity class2 = new ClassEntity(
                "TR", "13:00:00", "14:15:00", building6, "205", "North Campus", section1
            );

            ClassEntity class3 = new ClassEntity(
                "MWF", "10:00:00", "11:15:00", building2, "301", "Main Campus", section2
            );

            ClassEntity class4 = new ClassEntity(
                "TR", "15:00:00", "16:15:00", building5, "102", "North Campus", section2
            );

            classRepository.saveAll(List.of(class1, class2, class3, class4));

            // Update CourseSections with their ClassEntities
            section1.setClasses(List.of(class1, class2));
            section2.setClasses(List.of(class3, class4));

            courseSectionRepository.saveAll(List.of(section1, section2));

            // Print test data
            System.out.println("\n\n\n\n\n\nAll CourseSections for Instructor Barnes:");
            System.out.println(courseSectionRepository.findAllByInstructor("Barnes"));

            System.out.println("\nAll Courses with Subject 'physiology':");
            System.out.println(courseRepository.findAllBySubject("physiology"));

            System.out.println("\nBuilding with ID '178':");
            System.out.println(buildingRepository.findById("178"));

            System.out.println("\nAll Courses with Spring Semester:");
            System.out.println(courseRepository.findAllBySemester("Spring"));
        };
    }
}
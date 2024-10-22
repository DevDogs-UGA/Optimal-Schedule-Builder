package edu.uga.devdogs.course_information;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import edu.uga.devdogs.course_information.Class.Class;
import edu.uga.devdogs.course_information.Class.ClassRepository;
//import edu.uga.devdogs.course_information.config_files.CourseConfig;
import edu.uga.devdogs.course_information.course_section.CourseSectionEntity;
import edu.uga.devdogs.course_information.Course.Course;
import edu.uga.devdogs.course_information.Course.CourseRepository;
import java.sql.Time;


@SpringBootApplication
public class CourseInformationApplication {

	public static void main(String[] args) {

		SpringApplication.run(CourseInformationApplication.class, args);
		@Bean
    	CommandLineRunner courseSecCommandLineRunner(CourseSectionRepository courseSectionRepository) {
			return args -> {
				CourseSection section1 = new CourseSection (
					123456,
					4,
					"something",
					1,
					4,
					"Barnes",
					3,
					40,
					12,
					3,
					1293);

					courseSectionRepository.save(section1);
			};
		}
		@Bean
		CommandLineRunner courseCommandLineRunner (CourseRepository courseRepository) {
			return args -> {
				Course course1 = new Course (
					"physiology", 
					"420", 
					"pain", 
					"Mary Francis early education");

				courseRepository.save(course1);
			};
		}

		@Bean
		CommandLineRunner buildingCommandLineRunner (Building building) {
			return args -> {
				Building building1 = new Building (2438, "CAGTECH", "F - 6");
				buildingRepository.save(building1);
				
				Building building2 = new Building(46, "Caldwell Hall", "C - 1");
				buildingRepository.save(building2);
				
				Building building3 = new Building(2118, "Campus Mail/Environmental Safety", "E - 6");
				buildingRepository.save(building3);
				
				Building building4 = new Building(1637, "Campus Transit Facility", "D - 8");
				buildingRepository.save(building4);
				
				Building building5 = new Building(31, "Candler Hall", "C - 1");
				buildingRepository.save(building5);
				
				Building building6 = new Building(1110, "Carlton Street Deck", "B - 4");
				buildingRepository.save(building6);
				
				Building building7 = new Building(2419, "CCRC", "E - 7");
				buildingRepository.save(building7);
				
				Building building8 = new Building(2127, "Center for Applied Isotope Study", "E - 6");
				buildingRepository.save(building8);
				
				Building building9 = new Building(2395, "Center for Molecular Medicine", "E - 7");
				buildingRepository.save(building9);
				
				Building building10 = new Building(178, "Central Campus Mech. Building", "C - 2");
				buildingRepository.save(building10);
			};
		}
		@Bean
		CommandLineRunner commandLineRunner(ClassRepository classRepository, CourseRepository courseRepository) {
			return args -> {
				Class class1 = new Class(
						"MWF", 
						Time.valueOf("08:00:00"), 
						Time.valueOf("09:15:00"), 
						"Science Building", 
						"101", 
						"Main Campus" 
				);

				Class class2 = new Class(
						"TR", 
						Time.valueOf("13:00:00"), 
						Time.valueOf("14:15:00"), 
						"Engineering Hall", 
						"205", 
						"North Campus"
				);

            classRepository.save(class1);
            classRepository.save(class2);
        };
	}


}


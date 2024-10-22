package edu.uga.devdogs.course_information.Class;

import edu.uga.devdogs.course_information.Course.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Time;

@Configuration
public class ClassConfig {
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
package edu.uga.devdogs.course_information.Class;

import edu.uga.devdogs.course_information.Course.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Time;

@Configuration
public class ClassEntityConfig {
    @Bean
    CommandLineRunner commandLineRunner(ClassEntityRepository classRepository, CourseRepository courseRepository) {
        return args -> {
            ClassEntity class1 = new ClassEntity(
                    "MWF", 
                    Time.valueOf("08:00:00"), 
                    Time.valueOf("09:15:00"), 
                    "Science Building", 
                    "101", 
                    "Main Campus" 
            );

            ClassEntity class2 = new ClassEntity(
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
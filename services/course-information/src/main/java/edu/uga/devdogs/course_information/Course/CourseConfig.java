package edu.uga.devdogs.course_information.Course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseConfig {
    @Bean
    CommandLineRunner commandLineRunner (CourseRepository courseRepository){
        return args -> {
            Course course1 = new Course (
            "physiology", 
            "420", 
            "pain", 
            "Mary Francis early education");

            courseRepository.save(course1);
        };
    }
    
}

package edu.uga.devdogs.course_information.CourseSection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseSectionConfig {
    @Bean
    CommandLineRunner commandLineRunner(CourseSectionRepository courseSectionRepository) {
        return args -> {
            CourseSection section1 = new CourseSection(
                    "physiology",
                    "420",
                    "pain",
                    "Mary Francis early education");

            courseSectionRepository.save(section1);
        };
    }

}

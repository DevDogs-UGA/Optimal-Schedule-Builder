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

}

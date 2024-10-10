package edu.uga.devdogs.course_information.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller that handles the Course-Information details.
 * Provides courses based on parameters like major, professor,
 * CRN, time slot, and athena name.
 */
@RestController
@RequestMapping("/api/course_information")
public class CourseInfoController {
   
    /**
     * Asks for list of course information that relates to the 
     * @param professor given.
     * 
     * @param professor name of the professor teaching the course
     * @return course information list that's related to the given professor.
     */
    @GetMapping("/professor")
   public List<Section> getCourseByProfessor(@RequestParam String professor){
        List <Section> courseInfo = new ArrayList<>();

        //courseInfo = professorCourse();
        //professorCourse is a dummy function that provides a list of course information pertaining to the professor.

        return courseInfo;
   } 
    
}
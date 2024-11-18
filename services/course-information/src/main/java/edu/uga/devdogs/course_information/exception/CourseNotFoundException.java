package edu.uga.devdogs.course_information.exception;


public class CourseNotFoundException extends RuntimeException {

    // Constructor for course not found
    public CourseNotFoundException(String message){

        super(message); 
    }

}

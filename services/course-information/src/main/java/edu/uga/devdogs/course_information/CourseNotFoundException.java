package edu.uga.devdogs.course_information.exception;


public class CourseNotFoundException extends RuntimeException {


    public CourseNotFoundException(String message){

        super(message); 
    }

}

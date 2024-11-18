package edu.uga.devdogs.course_information.exceptions;

public class CourseNotFoundException extends RuntimeException {

    //constructor for course not found
    public CourseNotFoundException() {
        super("Course not found.");
    }

    //constructor for course not found with message
    public CourseNotFoundException(String message) {
        super(message);
    }

    //constructor for course not found with message and cause
    public CourseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    //constructor for course not found with cause
    public CourseNotFoundException(Throwable cause) {
        super(cause);
    }
}
package edu.uga.devdogs.course_information.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException() {
        super("Course not found.");
    }

    public CourseNotFoundException(String message) {
        super(message);
    }

    public CourseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourseNotFoundException(Throwable cause) {
        super(cause);
    }
}
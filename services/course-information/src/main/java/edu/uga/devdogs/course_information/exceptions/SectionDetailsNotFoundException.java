package edu.uga.devdogs.course_information.exceptions;

public class SectionDetailsNotFoundException extends RuntimeException {

    // Constructor for section details not found
    public SectionDetailsNotFoundException() {
        super("Section details not found.");
    }


    // Constructor for section details not found with message
    public SectionDetailsNotFoundException(String message) {
        super(message);
    }

    // Constructor for section details not found with message and cause
    public SectionDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor for section details not found with cause
    public SectionDetailsNotFoundException(Throwable cause) {
        super(cause);
    }
}


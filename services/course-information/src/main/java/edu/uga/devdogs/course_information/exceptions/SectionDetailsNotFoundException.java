package edu.uga.devdogs.course_information.exceptions;

public class SectionDetailsNotFoundException extends RuntimeException {
    public SectionDetailsNotFoundException() {
        super("Section details not found.");
    }

    public SectionDetailsNotFoundException(String message) {
        super(message);
    }

    public SectionDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SectionDetailsNotFoundException(Throwable cause) {
        super(cause);
    }
}


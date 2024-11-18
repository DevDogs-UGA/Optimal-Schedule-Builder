package edu.uga.devdogs.bulletin.exceptions;

public class SectionNotFoundException extends RuntimeException {
    public SectionNotFoundException(String message) {
        super(message);
    }
}

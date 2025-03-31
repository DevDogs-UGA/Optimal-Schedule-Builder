package edu.uga.devdogs.course_information.exceptions;

public class BuildingNotFoundException extends RuntimeException {
    
    //constructor for building not found.
    public BuildingNotFoundException() {
        super("Building Not Found");
    }

    //constructor for building not found with a message.
    public BuildingNotFoundException(String message) {
        super(message);
    }

    //constructor for building not found with a message and cause.
    public BuildingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    //constructor for building not found with a cause.
    public BuildingNotFoundException(Throwable cause) {
        super(cause);
    }
}

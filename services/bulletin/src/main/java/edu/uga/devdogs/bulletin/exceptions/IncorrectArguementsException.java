package edu.uga.devdogs.bulletin.exceptions;

public class IncorrectArguementsException extends RuntimeException {
    public IncorrectArguementsException() {
        super("Section details not found.");
    }

    public IncorrectArguementsException(String message) {
        super(message);
    }

    public IncorrectArguementsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectArguementsException(Throwable cause) {
        super(cause);
    }
}

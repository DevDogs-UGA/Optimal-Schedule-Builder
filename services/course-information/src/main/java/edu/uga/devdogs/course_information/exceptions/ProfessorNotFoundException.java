package edu.uga.devdogs.course_information.exceptions;

public class ProfessorNotFoundException extends RuntimeException {

  //constructor for professor not found
  public ProfessorNotFoundException() {
    super("Professor details not found.");
  }

  //constructor for professor not found with message
  public ProfessorNotFoundException(String message) {
    super(message);
  }

  // constructor for professor not found with message and cause
  public ProfessorNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  // constructor for professor not found with cause
  public ProfessorNotFoundException(Throwable cause) {
    super(cause);
  }
}

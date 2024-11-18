package edu.uga.devdogs.course_information.exceptions;

public class ProfessorNotFoundException extends RuntimeException {
  public ProfessorNotFoundException() {
    super("Professor details not found.");
  }

  public ProfessorNotFoundException(String message) {
    super(message);
  }

  public ProfessorNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ProfessorNotFoundException(Throwable cause) {
    super(cause);
  }
}

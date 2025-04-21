package edu.uga.devdogs.course_information.Algorithm.records;

import java.util.List;

/**
 * Represents a section of a course, including the course code, CRN, professor, and associated classes.
 *
 * @param courseCode The course code for the course associated with this section.
 * @param crn        The course reference number (CRN) for this section.
 * @param professor  The professor teaching this section.
 * @param classes    The array of classes associated with this section.
 */
public record Section(String courseCode,
                      int crn,
                      Professor professor,
                      List<Class> classes) {
}

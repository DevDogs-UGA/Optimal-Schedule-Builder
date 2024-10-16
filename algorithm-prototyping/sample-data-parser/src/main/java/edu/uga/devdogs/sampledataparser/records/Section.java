package edu.uga.devdogs.sampledataparser.records;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a section of a course, including the course code, CRN, professor, and associated classes.
 *
 * @param courseCode The course code for this section.
 * @param crn        The course reference number (CRN) for this section.
 * @param professor  The professor teaching this section.
 * @param classes    The array of classes associated with this section.
 */
public record Section(String courseCode,
                      String crn,
                      @SerializedName("professor_name") Professor professor,
                      Class[] classes) {
}

package edu.uga.devdogs.sampledataparser.records;

/**
 * Represents a course with a course code and its associated sections.
 *
 * @param courseCode The unique code identifying the course.
 * @param sections   The sections available for this course.
 */
public record Course(String courseCode,
                     Section[] sections) {
}

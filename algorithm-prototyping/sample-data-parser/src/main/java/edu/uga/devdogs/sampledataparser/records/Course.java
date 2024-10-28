package edu.uga.devdogs.sampledataparser.records;

import java.util.List;

/**
 * Represents a course with a course code and its associated sections.
 *
 * @param courseCode The unique code identifying the course.
 * @param sections   The sections available for this course.
 */
public record Course(String courseCode,
                     List<Section> sections) {
}

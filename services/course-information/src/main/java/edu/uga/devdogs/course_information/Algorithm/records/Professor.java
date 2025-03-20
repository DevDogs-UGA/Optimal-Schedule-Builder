package edu.uga.devdogs.course_information.Algorithm.records;

/**
 * Represents a professor with a name and a quality rating.
 *
 * @param name    The name of the professor.
 * @param quality The quality rating of the professor (e.g., on a scale from 1 to 5).
 */
public record Professor(String name,
                        double quality) {
}

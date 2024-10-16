package edu.uga.devdogs.sampledataparser.records;

/**
 * Represents a professor with a name and a quality rating.
 *
 * @param name    The name of the professor.
 * @param quality The quality rating of the professor (e.g., on a scale from 1 to 5).
 */
public record Professor(String name,
                        int quality) {
}

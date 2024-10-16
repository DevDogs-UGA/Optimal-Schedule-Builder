package edu.uga.devdogs.sampledataparser.records;

/**
 * Represents the sample data used in the scheduling system, containing the available courses and distances between buildings.
 *
 * @param courses   The array of courses in the sample data.
 * @param distances The distances between buildings on campus.
 */
public record SampleData(Course[] courses,
                         Distances distances) {
}

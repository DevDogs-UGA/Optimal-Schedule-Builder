package edu.uga.devdogs.course_information.Algorithm.records;

import java.util.Map;

/**
 * Represents the sample data used in the scheduling system, containing the available courses and distances between buildings.
 *
 * @param courses   The array of courses in the sample data.
 * @param distances The distances between buildings on campus.
 */
public record SampleData(Map<String, Course> courses,
                         Map<String, Map<String, Double>> distances) {
}

package edu.uga.devdogs.course_information.Algorithm.records;

import java.util.List;

/**
 * Representation of the hard constraints considered by the user
 *
 * @param excludedCourses The courses the user does not want to take.
 * @param excludedSections The sections the user does not want to take.
 * @param campus          The campus the user attends.
 * @param minCreditHours The minimum number of credit hours the user wants to take.
 * @param maxCreditHours The maximum number of credit hours the user wants to take.
 * @param walking         The user uses walking as a means of transportation.
 *
 */
public record HConstraints(List<Course> excludedCourses,
                           List<Section> excludedSections,
                           String campus,
                           int minCreditHours,
                           int maxCreditHours,
                           boolean walking) {
}
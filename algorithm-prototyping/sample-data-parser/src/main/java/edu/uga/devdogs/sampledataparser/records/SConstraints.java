package edu.uga.devdogs.sampledataparser.records;

import java.time.DayOfWeek;
import java.util.List;

/**
 * Representation of the soft constraints considered by the user
 *
 * @param excludedCourses The courses the user does not want to take.
 * @param excludedClasses The classes the user does not want to take.
 * @param campus          The campus the user attends.
 * @param gapDay          The preferred day for a gap.
 * @param prefStartTime   The preferred time to start their first course of the day.
 * @param prefEndTime     The preferred time to start their last course of the day.
 * @param walking         The user uses walking as a means of transportation.
 *
 */
public record SConstraints(List<Course> excludedCourses,
                           List<Class> excludedClasses,
                           String campus,
                           DayOfWeek gapDay,
                           int prefStartTime,
                           int prefEndTime,
                           boolean walking) {
}

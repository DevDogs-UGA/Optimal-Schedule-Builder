package edu.uga.devdogs.sampledataparser.records;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Representation of the soft constraints considered by the user
 *
 * @param gapDay          The preferred day for a gap.
 * @param prefStartTime   The preferred time to start their first course of the day.
 * @param prefEndTime     The preferred time to start their last course of the day.
 * @param showFilledClasses Whether a user wants to exclude full sections from their schedule
 *
 */
public record SConstraints(
                           DayOfWeek gapDay,
                           LocalTime prefStartTime,
                           LocalTime prefEndTime,
                           boolean showFilledClasses) {
}

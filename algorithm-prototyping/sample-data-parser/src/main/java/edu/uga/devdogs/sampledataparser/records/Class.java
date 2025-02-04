package edu.uga.devdogs.sampledataparser.records;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

/**
 * Represents a class in a course section, including its CRN, meeting days, start time, end time, and building.
 *
 * @param crn          The course reference number (CRN) for the section associated with this class.
 * @param days         The days of the week when the class meets.
 * @param startTime    The start time of the class.
 * @param endTime      The end time of the class.
 * @param buildingName The name of the building where the class is held.
 * @param campus       The campus the course takes place at.
 */
public record Class(int crn,
                    List<DayOfWeek> days,
                    LocalTime startTime,
                    LocalTime endTime,
                    String buildingName,
                    String campus,
                    String buildingNumber) {
}

package edu.uga.devdogs.bruteforceprototype.schedule;

import edu.uga.devdogs.sampledataparser.records.*;
import edu.uga.devdogs.bruteforceprototype.*;

import java.time.DayOfWeek;
import java.util.*;

/**
 * Utility class for performing operations and calculations related to a Schedule.
 * This class includes methods to validate schedules, compute various objectives,
 * and evaluate overall performance. These computations are defined in the
 * Algorithm Prototyping document.
 *
 * @see <a href="https://drive.google.com/file/d/1J2_vlChwx_oWGYKRrmDkDBzWY6dORn6v/view?usp=sharing">Algorithm Prototyping</a>
 */
public class ScheduleUtil {

    /**
     * Validates the given schedule by checking for any time conflicts between classes.
     * A time conflict occurs when two classes overlap in their scheduled times.
     *
     * @param schedule the schedule to be validated
     * @return true if the schedule is valid and contains no time conflicts, false otherwise
     */
    public static boolean validate(Schedule schedule) {
        return false;
    }

    /**
     * Computes the average quality rating of professors in the schedule.
     * This is based on each professor's RateMyProfessor quality rating.
     *
     * @param schedule the schedule for which to compute the average professor quality
     * @return the average professor quality rating for the schedule
     */
    public static double computeAverageProfessorQuality(Schedule schedule) {
        return 0.0;
    }

    /**
     * Computes the maximum distance between buildings for the classes in the schedule.
     * This is the maximum distance that a student would have to travel
     * between consecutive classes.
     *
     * @param schedule the schedule for which to compute the maximum distance
     * @param distances object that represents distances between buildings on campus.
     * @return the maximum distance between buildings for consecutive classes
     */
    public static double computeMaxDistance(Schedule schedule, Map<String, Map<String, Double>> distances) {
        EnumMap<DayOfWeek, TreeSet<Class>> classMap = schedule.days(); // Reference to schedule object.
        double maxDistance = 0; // Double to be returned.

        // Iterator that goes over each day of the week.
        for (DayOfWeek day : classMap.keySet()) {
            TreeSet<Class> classes = classMap.get(day); // Get all classes for the day

            if (classes.size() > 1) { // Check for more than 1 class, otherwise do nothing (no distance covered).
                Class previousClass = null;
                // Iterator that goes over each class for the day.
                for (Class currentClass : classes) {
                    if (previousClass != null) {
                        // Gets the distance between buildings of previous class and current class.
                        double distance = distances.
                                get(previousClass.buildingName()).
                                get(currentClass.buildingName());

                        // Update max distance if distance is greater.
                        if (distance > maxDistance) {
                            maxDistance = distance;
                        }
                    }

                    previousClass = currentClass;
                }
            }
        }

        return maxDistance;
    }

    /**
     * Computes the average idle time between consecutive classes in the schedule.
     * Idle time refers to the time gaps between two consecutive classes.
     *
     * @param schedule the schedule for which to compute the average idle time
     * @return the average idle time between classes in the schedule
     */
    public static double computeAverageIdleTime(Schedule schedule) {
        return 0.0;
    }

    /**
     * Computes the overall objective score for the given schedule based on weighted objectives.
     * This method computes each objective, normalizes their values, and computes a weighted sum.
     *
     * @param schedule the schedule to evaluate
     * @param distances object that represents distances between buildings on campus.
     * @param weights an array of floats representing the weights for each objective
     * @return the overall objective score for the schedule
     */
    public static double computeOverallObjective(Schedule schedule, Distances distances, Float[] weights) {
        return 0.0;
    }

}

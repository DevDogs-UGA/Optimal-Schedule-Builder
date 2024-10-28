package edu.uga.devdogs.bruteforceprototype.schedule;

import java.util.Map;

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
     * @param distances a nested string map that represents distances between buildings on campus
     * @return the maximum distance between buildings for consecutive classes
     */
    public static double computeMaxDistance(Schedule schedule, Map<String, Map<String, Double>> distances) {
        return 0.0;
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
     * @param distances a nested string map that represents distances between buildings on campus
     * @param weights an array of floats representing the weights for each objective
     * @return the overall objective score for the schedule
     */
    public static double computeOverallObjective(Schedule schedule, Map<String, Map<String, Double>> distances, double[] weights) {
        // The minimum rating on rate my professor is 1.0(if they have ratings).
        final double profesorQualityMinimum = 1.0;
        // The maximum rating on rate my professor is 5.0
        final double profesorQualityMaximum = 5.0;
        // The minimum distance between classes is 0.0 if they are in the same building.
        final double maxDistanceMinimum = 0.0;
        // The highest distance shown in the document is 22.1, so 30 should be a good maximum for calculations.
        final double maxDistanceMaximum = 30.0;
        // The minimum average idle time is 0 which should only happen if the person has one class a day.
        final double averageIdleTimeMinimum = 0;
        // The earliest a class can start is 8:00 a.m. and latest a class can go is 9:00 p.m. which is 780 minutes between those times.

        final double averageIdleTimeMaximum = 780;

        // finds the values and then plugs them into the normalize function with the minimum and maximum.
        double normalizedProfessorQuality = normalizeValue(computeAverageProfessorQuality(schedule), profesorQualityMinimum, profesorQualityMaximum);
        double normalizedMaxDistance = normalizeValue(computeMaxDistance(schedule, distances), maxDistanceMinimum, maxDistanceMaximum);
        double normalizedAverageIdleTime = normalizeValue(computeAverageIdleTime(schedule), averageIdleTimeMinimum, averageIdleTimeMaximum);

        // computes the weighted sum. normalizedMaxDistance and normalizedAverageIdleTime are being subtracted from 1 since
        // the higher the value, the worse the schedule.
        return normalizedProfessorQuality * weights[0] + (1 - normalizedMaxDistance) * weights[1] + (1 - normalizedAverageIdleTime) * weights[2];
    }

    private static double normalizeValue(double value, double min, double max) {
        // clips the value if below the minimum or above the maximum
        if (value < min) {
            return 0.0;
        } else if (value > max) {
            return 1.0;
        } else {
            // normalizes the values using min-max normalization
            return (value - min) / (max - min);
        }
    }

}

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
     * Iterates through days of the week, checks if the day has 0/1 classes, in which case
     * it continues to the next day of the week. If there are more than one class in the day,
     * iterate through the classes in the day and check for time conflicts.
     *
     * @param schedule the schedule to be validated
     * @return false if a time conflict is found, true if there are no conflicts found.
     */
    public static boolean validate(Schedule schedule) {
        // iterates through the days of the week
        for (DayOfWeek day : schedule.days().keySet()) {
            if (schedule.days().get(day).size() == 1 || schedule.days().get(day).size() == 0) {
                continue;
            } else {
                TreeSet<Class> classes = schedule.days().get(day);
                Iterator<Class> iterator = classes.iterator();
                if (iterator.hasNext()) {
                    Class currentClass = iterator.next();
                    while (iterator.hasNext()) {
                        Class nextClass = iterator.next();
                        if (currentClass.endTime().compareTo(nextClass.startTime) >= 0) {
                            return false; // conflict found
                        } // if
                        currentClass = nextClass; // move to next class
                    } // while
                } // if
            } // if-else
        } // for
        return true;
    }// validate

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
        return 0.0;
    }

}

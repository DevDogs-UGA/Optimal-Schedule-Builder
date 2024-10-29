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
     * @throws IllegalArgumentException if schedule is not valid
     * @return the average idle time between classes in the schedule in minutes
     */
    public static double computeAverageIdleTime(Schedule schedule) {
        int countOfClassGaps = 0;
        int sumOfTimeGaps = 0;

        // Loop through the days
        for (TreeSet<Class> day: schedule.days().values()){
            int prevEndTime = -1;

            // Loop through all of the classes in a single day
            for (Class _class: day){
                int currStartTime = _class.startTime().getHour()*60 + _class.startTime().getMinute();
                int currEndTime = _class.endTime().getHour()*60 + _class.endTime().getMinute();

                // Checks for invalid schedules
                if (currStartTime < prevEndTime){
                    throw new IllegalArgumentException("Schedule contains time conflicts.");
                }
                
                // Only runs if there is a previous class in the current day
                if (prevEndTime != -1){
                    sumOfTimeGaps += currStartTime - prevEndTime;
                    countOfClassGaps++;
                }
                prevEndTime = currEndTime;
            }
        }
        if (countOfClassGaps == 0){
            return 0;
        }

        return sumOfTimeGaps / countOfClassGaps;
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

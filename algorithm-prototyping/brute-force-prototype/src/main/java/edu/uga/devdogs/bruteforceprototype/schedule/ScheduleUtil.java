package edu.uga.devdogs.bruteforceprototype.schedule;

import edu.uga.devdogs.sampledataparser.records.Section;
import edu.uga.devdogs.sampledataparser.records.Class;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;


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
        if (schedule == null) {
            throw new IllegalArgumentException("Schedule cannot be null");
        }

        // Iterates over each day in the schedule
        for (TreeSet<Class> day : schedule.days().values()) {
            // Converts the TreeSet to List for direct indexing
            // TODO: Replace TreeSet with a sorted List for days in Schedule
            List<Class> dayList = new ArrayList<>(day);

            // Iterates over consecutive class pairs
            for (int i = 0; i < dayList.size() - 1; i++) {
                Class currClass = dayList.get(i);
                Class nextClass = dayList.get(i + 1);

                // Convert time to minutes
                int currEndTime = currClass.endTime().getHour() * 60 + currClass.endTime().getMinute();
                int nextStartTime = nextClass.startTime().getHour() * 60 + nextClass.startTime().getMinute();

                // Check if the current class end time overlaps with the next class start time
                if (currEndTime > nextStartTime) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Computes the average quality rating of professors in the schedule.
     * This is based on each professor's RateMyProfessor quality rating.
     *
     * @param schedule the schedule for which to compute the average professor quality
     * @return the average professor quality rating for the schedule
     */
    public static double computeAverageProfessorQuality(Schedule schedule) {
        double professorQualitySum = 0.0;
        int includedProfessorCount = schedule.sections().size();
        // Used to create the summation of the quality of professors within the schedule.
        for (Section section : schedule.sections()) {
            // Ensures that only professors with data are included in calculation.
            if (section.professor().quality() == 0.0) {
                // Decreases our professor count to prevent statistical misguidance.
                includedProfessorCount--;
            } else {
                // Adds valid professors to our summation
                professorQualitySum += section.professor().quality();
            }
        }

        return professorQualitySum / includedProfessorCount;
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
        if (schedule == null || distances == null) {
            throw new IllegalArgumentException("Schedule or Distances cannot be null");
        }

        double maxDistance = 0.0;

        // Iterates over each day in the schedule
        for (TreeSet<Class> day : schedule.days().values()) {
            // Converts the TreeSet to List for direct indexing
            // TODO: Replace TreeSet with a sorted List for days in Schedule
            List<Class> dayList = new ArrayList<>(day);

            // Iterates over consecutive class pairs
            for (int i = 0; i < dayList.size() - 1; i++) {
                Class currClass = dayList.get(i);
                Class nextClass = dayList.get(i + 1);
                double distance = distances.get(currClass.buildingName()).get(nextClass.buildingName());

                if (distance > maxDistance) {
                    maxDistance = distance;
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
     * @return the average idle time between classes in the schedule in minutes
     * @throws IllegalArgumentException if schedule is not valid
     */
    public static double computeAverageIdleTime(Schedule schedule) {
        int countOfClassGaps = 0;
        int sumOfTimeGaps = 0;

        // Loop through the days
        for (TreeSet<Class> day: schedule.days().values()){
            int prevEndTime = -1;

            // Loop through all the classes in a single day
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

        return (double) sumOfTimeGaps / countOfClassGaps;
    }

    /**
     * Computes the overall objective score for the given schedule based on weighted objectives.
     * This method computes each objective, normalizes their values using min-max normalization, and computes a weighted sum.
     * The weighted sum increases as averageProfessorScore increases and decreases as maxDistance and averageIdleTime increase.
     *
     * @param schedule the schedule to evaluate
     * @param distances a nested string map that represents distances between buildings on campus
     * @param weights an array of floats representing the weights for each objective;
     *                must be of length 3 and add to 1.0
     *                weights[0] corresponds to averageProfessorQuality
     *                weights[1] corresponds to maxDistance
     *                weights[2] corresponds to averageIdleTime
     *
     * @return the overall objective score for the schedule
     */
    public static double computeOverallObjective(Schedule schedule, Map<String, Map<String, Double>> distances, double[] weights) {
       // Checks if the parameters are valid
       if (schedule == null || distances == null || weights == null) {
           throw new IllegalArgumentException("Parameters cannot be null");
       } else if (weights.length != 3) {
           throw new IllegalArgumentException("Number of weights must be 3");
       } else if (Math.abs(weights[0] + weights[1] + weights[2] - 1) > 1e-6) {
           throw new IllegalArgumentException("The sum of the weights must be equal to 1");
       }

        // The minimum rating on rate my professor is 1.0(if they have ratings).
        final double professorQualityMinimum = 1.0;
        // The maximum rating on rate my professor is 5.0
        final double professorQualityMaximum = 5.0;
        // The minimum distance between classes is 0.0 if they are in the same building.
        final double maxDistanceMinimum = 0.0;
        // The highest distance shown in the document is 22.1, so 30 should be a good maximum for calculations.
        final double maxDistanceMaximum = 30.0;
        // The minimum average idle time is 0 which should only happen if the person has one class a day.
        final double averageIdleTimeMinimum = 0;
        // The earliest a class can start is 8:00 a.m. and latest a class can go is 9:00 p.m. which is 780 minutes between those times.
        final double averageIdleTimeMaximum = 780;

        // finds the values and then plugs them into the normalize function with the minimum and maximum.
        double normalizedProfessorQuality = normalizeValue(computeAverageProfessorQuality(schedule), professorQualityMinimum, professorQualityMaximum);
        double normalizedMaxDistance = normalizeValue(computeMaxDistance(schedule, distances), maxDistanceMinimum, maxDistanceMaximum);
        double normalizedAverageIdleTime = normalizeValue(computeAverageIdleTime(schedule), averageIdleTimeMinimum, averageIdleTimeMaximum);

        // computes the weighted sum. normalizedMaxDistance and normalizedAverageIdleTime are being subtracted from 1 since
        // the higher the value, the worse the schedule.
        return normalizedProfessorQuality * weights[0] + (1 - normalizedMaxDistance) * weights[1] + (1 - normalizedAverageIdleTime) * weights[2];
    }

    /**
     * Normalizes the values using min-max normalization and clips outliers
     * to the min or the max.
     *
     * @param value the value to normalize
     * @param max the maximum value that should normally be received
     * @param min the minimum value that should normally be received
     *
     * @return the normalized value, between 0 and 1.
     */
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

    /**
     * Converts various data structures containing Sections into arrays of ints for use of other teams.
     *
     * @param sections the iterable object containing the sections to convert.
     * @return the array of CRNs that represent the sections.
     * */
    private static int[] sectionToInt(Set<Section> sections) {
        int[] output = new int[sections.size()];
        int i = 0;
        for (Section section : sections) {
            output[i] = section.crn();
            i++;
        }
        return output;
    }

}

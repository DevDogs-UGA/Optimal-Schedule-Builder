package edu.uga.devdogs.course_information.Algorithm.schedule;

import edu.uga.devdogs.course_information.Algorithm.records.SConstraints;
import edu.uga.devdogs.course_information.Algorithm.records.Section;
import edu.uga.devdogs.course_information.Algorithm.records.Class;
import edu.uga.devdogs.course_information.Algorithm.records.HConstraints;

import edu.uga.devdogs.course_information.service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.EnumMap;
import java.util.Set;

import java.time.DayOfWeek;
import java.time.LocalTime;



/**
 * Utility class for performing operations and calculations related to a Schedule.
 * This class includes methods to validate schedules, compute various objectives,
 * and evaluate overall performance. These computations are defined in the
 * Algorithm Prototyping document.
 *
 * @see <a href="https://drive.google.com/file/d/1J2_vlChwx_oWGYKRrmDkDBzWY6dORn6v/view?usp=sharing">Algorithm Prototyping</a>
 */
public class ScheduleUtil {

    // Used for bootstrap getting coords
    @Autowired
    private CourseInformationService courseInformationService;

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
            if (section.professor().quality() == (Double)null || section.professor().quality() == 0.0) {
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
     * Checks if schedule or constraints is null and throws an exception if it is.
     *
     * @param schedule the schedule to validate
     * @param constraints the constraints to validate
     */
    public static void validateHard(Schedule schedule, HConstraints constraints) {
        if(schedule == null || constraints == null) {
            throw new IllegalArgumentException("Schedule and/or constraints cannot be null");
        }
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
            List<Class> dayList = new ArrayList<>(day);

            // Iterates over consecutive class pairs
            for (int i = 0; i < dayList.size() - 1; i++) {
                Class currClass = dayList.get(i);
                Class nextClass = dayList.get(i + 1);

                double lat1 = courseInformationService.getLatitude(currClass.buildingNumber());
                double lon1 = courseInformationService.getLongitude(currClass.buildingNumber());
                double lon2 = courseInformationService.getLongitude(nextClass.buildingNumber());
                double lat2 = courseInformationService.getLatitude(nextClass.buildingNumber());

                // distance between latitudes and longitudes
                double dLat = Math.toRadians(lat2 - lat1);
                double dLon = Math.toRadians(lon2 - lon1);

                // convert to radians
                lat1 = Math.toRadians(lat1);
                lat2 = Math.toRadians(lat2);

                // apply Haversine formula
                double a = Math.pow(Math.sin(dLat / 2), 2) +
                        Math.pow(Math.sin(dLon / 2), 2) *
                                Math.cos(lat1) *
                                Math.cos(lat2);

                // Earth's radius in miles
                double rad = 3960;
                double c = 2 * Math.asin(Math.sqrt(a));
                double distance = rad * c;

                if (distance > maxDistance) {
                    maxDistance = distance;
                }
            }
        }

        // dimensional analysis. Average human walking speed is 3 miles per hour.
        // miles * (hours/miles) * (minutes/hours)
        return maxDistance * (1/3) * (60);
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
     * Computes the earliest start time of all classes in a schedule
     *
     * @param schedule the schedule for which to compute the earliest start time
     * @return the earliest start time (in hours) as a double. For example, 4:30 pm would be represented as 16.5
     */
    public static double computeStartTime(Schedule schedule) {
        LocalTime earliestStartTime = LocalTime.of(23,59,59);
        for (Section eachSection : schedule.sections()) {
            for (Class eachClass : eachSection.classes()) {
                if (eachClass.startTime().isBefore(earliestStartTime)) {
                    earliestStartTime = eachClass.startTime();
                }
            }
        }
        return earliestStartTime.getHour() + (double) earliestStartTime.getMinute() / 60;
    }



    /**
     * Computes the latest start time of all classes in a schedule
     *
     * @param schedule the schedule for which to compute the latest start time
     * @return the latest start time (in hours) as a double. For example, 4:30 pm would be represented as 16.5
     */
    private static double computeEndTime(Schedule schedule) {
        LocalTime latestStartTime = LocalTime.of(0,0,0);
        for (Section eachSection : schedule.sections()) {
            for (Class eachClass : eachSection.classes()) {
                if (eachClass.startTime().isAfter(latestStartTime)) {
                    latestStartTime = eachClass.startTime();
                }
            }
        }
        return latestStartTime.getHour() + (double) latestStartTime.getMinute() / 60;
    }



    /**
     * Determines whether a given schedule has zero classes on a given day
     *
     * @param schedule the schedule for which to compute whether it has a gap day or not
     * @param gapDay the day that is being checked as a gap day or not
     * @return {@code true} if the given schedule has no classes on the given day, {@code false} if it has at least one class
     */
    private static boolean computeGapDay(Schedule schedule, DayOfWeek gapDay) {
        EnumMap<DayOfWeek, TreeSet<Class>> days = schedule.days();
        TreeSet<Class> classes = days.get(gapDay);
        return classes.isEmpty();
    }



    /**
     * Computes the overall objective score for the given schedule based on weighted objectives.
     * This method computes each objective, normalizes their values using min-max normalization, and computes a weighted sum.
     * The weighted sum increases as averageProfessorScore increases and decreases as maxDistance and averageIdleTime increase.
     *
     * @param schedule the schedule to evaluate
     * @param distances a nested string map that represents distances between buildings on campus
     *
     * @return the overall objective score for the schedule
     */
    public static double computeOverallObjective(Schedule schedule, Map<String, Map<String, Double>> distances) {
        // Checks if the parameters are valid
        if (schedule == null || distances == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
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
        return normalizedProfessorQuality / 3 + (1 - normalizedMaxDistance) / 3 + (1 - normalizedAverageIdleTime) / 3;
    }

    public static double computeOverallObjectiveExtended(Schedule schedule, Map<String, Map<String, Double>> distances, SConstraints softConstraints) {
        // Checks if the parameters are valid
        if (schedule == null || distances == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
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

        // The Earliest a class can start is 8 am and the latest a class can start is 9 pm.
        final double prefTimeMinimum = 8.0;
        final double prefTimeMaximum = 21.0;


        // finds the values and then plugs them into the normalize function with the minimum and maximum.
        double normalizedProfessorQuality = normalizeValue(computeAverageProfessorQuality(schedule), professorQualityMinimum, professorQualityMaximum);
        double normalizedMaxDistance = normalizeValue(computeMaxDistance(schedule, distances), maxDistanceMinimum, maxDistanceMaximum);
        double normalizedAverageIdleTime = normalizeValue(computeAverageIdleTime(schedule), averageIdleTimeMinimum, averageIdleTimeMaximum);

        // Taking an average of all the optional softConstraints and then multiply the average by 0.25.
        double possSConstraintScore = 0;
        int possSConstraintsCount = 0;

        if (softConstraints.gapDay() != null) {
            possSConstraintScore += computeGapDay(schedule, softConstraints.gapDay()) ? 1 : 0;
            possSConstraintsCount++;
        }
        if (softConstraints.prefStartTime() != null) {
            possSConstraintScore += normalizeValue(computeStartTime(schedule), prefTimeMinimum, prefTimeMaximum);
            possSConstraintsCount++;
        }
        if (softConstraints.prefEndTime() != null) {
            possSConstraintScore += 1 - normalizeValue(computeEndTime(schedule), prefTimeMinimum, prefTimeMaximum);
            possSConstraintsCount++;
        }

        // Takes the sum and divides it by number of summed values, checks for when count = 0 so we don't divide by 0
        possSConstraintScore = possSConstraintsCount == 0 ? 0 : possSConstraintScore / possSConstraintsCount;

        // computes the weighted sum. normalizedMaxDistance and normalizedAverageIdleTime are being subtracted from 1 since
        // the higher the value, the worse the schedule.
        double output = normalizedProfessorQuality / 4 + (1 - normalizedMaxDistance) / 4;
        output += (1 - normalizedAverageIdleTime) / 4;
        output += (possSConstraintScore / 4);

        return output;
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
     * Converts Sets containing Sections into arrays of ints for use of other teams.
     *
     * @param sections the set containing the sections to convert.
     * @return the array of CRNs that represent the sections.
     * */
    public static int[] sectionsToInts(Set<Section> sections) {
        int[] output = new int[sections.size()];
        int i = 0;
        for (Section section : sections) {
            output[i] = section.crn();
            i++;
        }
        return output;
    }

}
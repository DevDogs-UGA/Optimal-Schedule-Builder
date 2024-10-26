package edu.uga.devdogs.bruteforceprototype;

import edu.uga.devdogs.bruteforceprototype.schedule.Schedule;
import edu.uga.devdogs.bruteforceprototype.schedule.ScheduleUtil;
import edu.uga.devdogs.sampledataparser.records.Course;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BruteForcePrototype {

    /**
     * Generates the optimal schedule based on the input courses, distances, and weights.
     * Iterates through each valid schedule from {@code generateValidSchedules(inputCourses)},
     * computes the overall objective score for each using
     * {@code ScheduleUtil.computeOverallObjective(schedule, distances, weights)},
     * and returns the schedule with the highest score.
     *
     * @param inputCourses a set of courses to generate an optimal schedule from
     * @param distances a nested string map that represents distances between buildings on campus
     * @param weights an array of floats representing the weights for each objective
     * @return the schedule with the highest overall objective score based on the input courses and weights
     */
    public static Schedule optimize(Set<Course> inputCourses, Map<String, Map<String, Double>> distances,  double[] weights) {
        Set<Schedule> validSchedules = generateValidSchedules(inputCourses);

        Schedule optimalSchedule = null;
        double optimalOverallObjective = 0;

        for (Schedule schedule : validSchedules) {
            double overallObjective = ScheduleUtil.computeOverallObjective(schedule, distances, weights);
            if(overallObjective > optimalOverallObjective) {
                optimalSchedule = schedule;
                optimalOverallObjective = overallObjective;
            }
        }

        return optimalSchedule;
    }


    /**
     * Generates the set of all valid schedules from the provided set of courses.
     * Each schedule is validated using {@code ScheduleUtil.validate(schedule)} to remove
     * schedules with time conflicts.
     *
     * @param inputCourses the set of courses to generate schedules from
     * @return the set of unique, valid schedules for the given set of courses
     */
    public static Set<Schedule> generateValidSchedules(Set<Course> inputCourses) {
        return new HashSet<>();
    }
}
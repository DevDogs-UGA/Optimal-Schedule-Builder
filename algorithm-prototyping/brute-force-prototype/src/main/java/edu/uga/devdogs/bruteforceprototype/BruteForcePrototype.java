package edu.uga.devdogs.bruteforceprototype;

import edu.uga.devdogs.bruteforceprototype.schedule.Schedule;
import edu.uga.devdogs.sampledataparser.records.Course;
import edu.uga.devdogs.sampledataparser.records.Distances;
import edu.uga.devdogs.sampledataparser.records.SampleData;

import java.util.HashSet;
import java.util.Set;

public class BruteForcePrototype {

    private final Course[] courses;
    private final Distances distances;

    public BruteForcePrototype(SampleData sampleData) {
        this.courses = sampleData.courses();
        this.distances = sampleData.distances();
    }

    /**
     * Generates the optimal schedule based on the input course codes and weights.
     * Iterates through each valid schedule from {@code generateValidSchedules(inputCourses)},
     * computes the overall objective score for each using
     * {@code ScheduleUtil.computeOverallObjective(schedule, distances, weights)},
     * and returns the schedule with the highest score.
     *
     * @param inputCourseCodes a set of course codes to generate an optimal schedule from
     * @param weights an array of floats representing the weights for each objective
     * @return the schedule with the highest overall objective score based on the input courses and weights
     */
    public Schedule optimize(Set<String> inputCourseCodes, double[] weights) {
        return new Schedule(new HashSet<>());
    }


    /**
     * Generates the set of all valid schedules from the provided set of courses.
     * Each schedule is validated using {@code ScheduleUtil.validate(schedule)} to remove
     * schedules with time conflicts.
     *
     * @param inputCourses the set of courses to generate schedules from
     * @return the set of unique, valid schedules for the given set of courses
     */
    private Set<Schedule> generateValidSchedules(Set<Course> inputCourses) {
        return new HashSet<>();
    }
}
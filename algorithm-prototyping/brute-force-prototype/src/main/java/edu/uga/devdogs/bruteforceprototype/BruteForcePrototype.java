package edu.uga.devdogs.bruteforceprototype;

import edu.uga.devdogs.bruteforceprototype.schedule.Schedule;
import edu.uga.devdogs.bruteforceprototype.schedule.ScheduleUtil;
import edu.uga.devdogs.sampledataparser.records.Course;
import edu.uga.devdogs.sampledataparser.records.Section;

import java.util.*;

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
     * Uses helper function {@code recurGenerateValidSchedules()} to generate schedules and record them
     *
     * @param inputCourses the set of courses to generate schedules from
     * @return the set of unique, valid schedules for the given set of courses
     */
    public static Set<Schedule> generateValidSchedules(Set<Course> inputCourses) {
        List<Course> courseList = new ArrayList<>(inputCourses);
        Set<Section> sectionList = new HashSet<>();
        HashSet<Schedule> validSchedules = new HashSet<>();

        generateValidSchedulesRecursive(sectionList, courseList, validSchedules);

        return validSchedules;
    }

    /**
     *  Adds a section to the schedule and checks if the schedule is still valid, if so, then check if there are anymore
     *  courses to add, if not it gets added to {@code Set<Schedule> validSchedules} otherwise iterate over all sections
     *  of the next course and recurse.
     *  A schedule's validity is determined by {@code ScheduleUtil.validate(schedule)}.
     * @param sections is the set of all sections in the current schedule
     * @param currCoursesToAdd is the list of all the courses which do not currently have a section in {@code sections}
     * @param validSchedules is the set of all discovered full valid schedules, every iteration uses the same
     *                       validSchedule object.
     * */
    private static void generateValidSchedulesRecursive(Set<Section> sections, List<Course> currCoursesToAdd, Set<Schedule> validSchedules){
        Schedule currSchedule = new Schedule(sections);

        // Gets rid of invalid schedules as soon as they become invalid instead of continuing to recurse
        if (!ScheduleUtil.validate(currSchedule)){
            return;
        }

        // If there are no more courses to add, then it is a full valid schedule
        if (currCoursesToAdd.isEmpty()){
            validSchedules.add(currSchedule);
            return;
        }

        // Instantiates the list of courses for the next iteration
        List<Course> nextCoursesToAdd = new ArrayList<>(currCoursesToAdd);
        nextCoursesToAdd.remove(0);

        // Iterates over all sections for the next course and recurses
        for (Section sectionToAdd: currCoursesToAdd.get(0).sections()){
            // Instantiates the set of sections for the next iteration
            HashSet<Section> nextSections = new HashSet<>(sections);
            nextSections.add(sectionToAdd);
            
            generateValidSchedulesRecursive(nextSections, nextCoursesToAdd, validSchedules);
        }
    }
}

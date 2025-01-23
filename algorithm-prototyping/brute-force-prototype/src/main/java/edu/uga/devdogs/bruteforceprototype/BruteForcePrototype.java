package edu.uga.devdogs.bruteforceprototype;

import edu.uga.devdogs.bruteforceprototype.schedule.Schedule;
import edu.uga.devdogs.bruteforceprototype.schedule.ScheduleUtil;
import edu.uga.devdogs.sampledataparser.records.Course;
import edu.uga.devdogs.sampledataparser.records.Section;

import java.sql.Array;
import java.util.*;

public class BruteForcePrototype {

    /**
     * Cleans the data being sent into the algorithm to account for certain hard constraints.
     *
     * @param inputCourses a set of courses given to be used in the user's schedule.
     * @throws Exception if {@code inputCourses} is not compliant with the filter.
     * @return a set of courses cleaned per the user's specification
     */
    public static Set<Course> dataPreFilter(Set<Course> inputCourses) throws Exception {
        if (inputCourses.size() > 10){
            throw new Exception("Input course list contains more than ten courses.");
        }

        return inputCourses;
    }

    /**
     * Generates the optimal schedule based on the input courses, distances, and weights.
     * Iterates through each valid schedule from {@code generateValidSchedules(inputCourses)},
     * Uses a makeshift priority queue to sort all the schedules that obey the hard constraints
     * using the overall objective score for each schedule using
     * {@code ScheduleUtil.computeOverallObjective(schedule, distances, weights)},
     * and returns the first five items of the list.
     *
     * If the courses inputted do not pass the dataPreFilter, this function will return null.
     *
     * @param inputCourses a set of courses to generate an optimal schedule from
     * @param distances a nested string map that represents distances between buildings on campus
     * @param weights an array of floats representing the weights for each objective
     * @return the five schedules with the highest overall objective score based on the input courses and weights
     */
    public static List<Schedule> optimize(Set<Course> inputCourses, Map<String, Map<String, Double>> distances,  double[] weights) {
        Set<Schedule> validSchedules = generateValidSchedules(inputCourses);

        if (validSchedules == null) {
            // If validSchedules is null, it means that there was an exception from dataPreFilter;
            // The error message would be already printed in generateValidSchedules,
            // so we just need to return null here to indicate that the input data was invalid
            return null;
        }else if (validSchedules.isEmpty()){
            // This block can be expanded in the future to include other methods of handling a lack of
            // Hard-constraint compliant schedules.
            System.out.println("No valid schedules found.");
            return new ArrayList<>();
        }

        List<Schedule> sortedSchedules = new ArrayList<>();
        List<Double> sortedOverallObjectives = new ArrayList<>();


        for (Schedule schedule : validSchedules) {
            double overallObjective = ScheduleUtil.computeOverallObjective(schedule, distances, weights);

            int i = sortedSchedules.size();
            while (i != 0 && overallObjective > sortedOverallObjectives.get(i-1)) {
                i--;
            }
            sortedSchedules.add(i, schedule);
            sortedOverallObjectives.add(i, overallObjective);
        }

        if (sortedSchedules.size() < 5){
            return sortedSchedules;
        }
        return sortedSchedules.subList(0,5);
    }


    /**
     * Generates the set of all valid schedules from the provided set of courses.
     * Each schedule is validated using {@code ScheduleUtil.validate(schedule)} to remove
     * schedules with time conflicts.
     * Uses helper function {@code recurGenerateValidSchedules()} to generate schedules and record them
     *
     * If the courses inputted do not pass the dataPreFilter, this function will return null.
     *
     * @param inputCourses the set of courses to generate schedules from
     * @return the set of unique, valid schedules for the given set of courses
     */
    public static Set<Schedule> generateValidSchedules(Set<Course> inputCourses) {
        List<Course> courseList;

        try{
            courseList = new ArrayList<>(dataPreFilter(inputCourses));
        } catch (Exception e){
            // Boilerplate error handling; We can probably decide on a better method for handling errors later.
            System.out.println(e.getMessage());
            return null;
        }

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

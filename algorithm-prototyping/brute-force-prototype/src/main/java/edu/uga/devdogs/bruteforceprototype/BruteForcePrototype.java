package edu.uga.devdogs.bruteforceprototype;

import edu.uga.devdogs.bruteforceprototype.schedule.Schedule;
import edu.uga.devdogs.bruteforceprototype.schedule.ScheduleUtil;
import edu.uga.devdogs.sampledataparser.records.Course;
import edu.uga.devdogs.sampledataparser.records.SConstraints;
import edu.uga.devdogs.sampledataparser.records.Section;

import java.sql.Array;
import java.util.*;

public class BruteForcePrototype {

    /**
     * Cleans the data being sent into the algorithm to account for certain hard constraints.
     *
     * @param inputCourses a set of courses given to be used in the user's schedule.
     * @param currentConstraints the constraints currently constraining schedule creation.
     * @throws Exception if {@code inputCourses} is not compliant with the filter.
     * @return a set of courses cleaned per the user's specification
     */
    public static Set<Course> dataPreFilter(Set<Course> inputCourses, SConstraints currentConstraints) throws Exception {
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
     * @param constraints the soft constraints on the schedule
     * @return the five schedules with the highest overall objective score based on the input courses and weights
     */
    public static int[][] optimize(Set<Course> inputCourses, Map<String, Map<String, Double>> distances,  double[] weights, SConstraints constraints) {
        Set<Schedule> validSchedules = generateValidSchedules(inputCourses, constraints);

        if (validSchedules == null) {
            // If validSchedules is null, it means that there was an exception from dataPreFilter;
            // The error message would be already printed in generateValidSchedules,
            // so we just need to return null here to indicate that the input data was invalid
            return null;
        }else if (validSchedules.isEmpty()){
            // This block can be expanded in the future to include other methods of handling a lack of
            // Hard-constraint compliant schedules.
            System.out.println("No valid schedules found.");
            return new int[0][];
        }

        List<Schedule> sortedSchedules = new ArrayList<>();
        List<Double> sortedOverallObjectives = new ArrayList<>();

        
        // Makeshift Priority Queue; An array sorted by a variable (in this case, overallObjective).
        // Before an item is added, you find where it should be placed so that the List is still sorted correctly
        // Without having to call a special sorting function.
        // Insert time is O(n), so *technically* merge sort is faster, but this function is not called frequently so 
        // Readibility matters much more than performance here.
        for (Schedule schedule : validSchedules) {
            double overallObjective = ScheduleUtil.computeOverallObjective(schedule, distances, weights);

            // Starting from the back of the array, find where the new schedule belongs based on overallObjective.
            int i = sortedSchedules.size();
            while (i != 0 && overallObjective > sortedOverallObjectives.get(i-1)) {
                i--;
            }
            sortedSchedules.add(i, schedule);
            sortedOverallObjectives.add(i, overallObjective);
        }

        // Ideally, we would like to output 5 schedules, but if there is less than 5 valid schedules, it will just return all of them.
        // Accomplishing this is trivial if you use an if-else statement and write the whole code block twice, once for 5 and once for less than 5
        // But that is both unnecessary and hard to read.
        // Instead, if you use a size variable set to whichever is smaller between the number of valid schedules and 5 schedules
        // You only have to write the code block once.
        int size = Math.min(sortedSchedules.size(), 5);
        int[][] output = new int[size][];

        for (int i = 0; i < size; i++) {
            // This line looks very convoluted, but really all it does is convert the (i)th best schedule into the list of CRNs
            // Of its sections.
            output[i] = ScheduleUtil.sectionsToInts(sortedSchedules.get(i).sections());
        }

        return output;
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
     * @param constraints the soft constraints on the schedule
     * @return the set of unique, valid schedules for the given set of courses
     */
    public static Set<Schedule> generateValidSchedules(Set<Course> inputCourses, SConstraints constraints) {
        List<Course> courseList;

        try{
            courseList = new ArrayList<>(dataPreFilter(inputCourses, constraints));
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

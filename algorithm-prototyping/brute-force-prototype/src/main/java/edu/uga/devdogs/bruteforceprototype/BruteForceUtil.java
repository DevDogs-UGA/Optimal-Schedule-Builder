package edu.uga.devdogs.bruteforceprototype;

import edu.uga.devdogs.sampledataparser.records.Class;
import edu.uga.devdogs.sampledataparser.records.Course;
import edu.uga.devdogs.sampledataparser.records.SConstraints;
import edu.uga.devdogs.sampledataparser.records.Section;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BruteForceUtil {

    /**
     * Cleans the data being sent into the algorithm to account for certain hard constraints.
     *
     * @param inputCourses a set of courses given to be used in the user's schedule.
     * @param currentConstraints the constraints currently constraining schedule creation.
     * @throws Exception if {@code inputCourses} is not compliant with the filter.
     * @return a set of courses cleaned per the user's specification
     */
    public static Set<Course> dataPreFilter(Set<Course> inputCourses, SConstraints currentConstraints) throws Exception {
        Set<Course> output = new HashSet<>();

        if (inputCourses == null) {
            throw new Exception("Input course list is null");
        }

        if (inputCourses.size() > 10){
            throw new Exception("Input course list contains more than ten courses.");
        }

        if (currentConstraints.gapDay() == null){
            throw new Exception("Preferred gap day is null.");
        }

        if (currentConstraints.prefStartTime() == null){
            throw new Exception("Preferred start time is null.");
        }

        if (currentConstraints.prefEndTime() == null){
            throw new Exception("Preferred end time is null.");
        }

        // Loops through all inputted courses and 'removes' all the sections which violate the constraints.
        for (Course course : inputCourses){
            if (course == null){
                throw new Exception("A requested course is null.");
            }

            List<Section> validSections = getValidSections(currentConstraints, course);

            // If there are no valid sections of a requested course, we can just remove the course
            // from the list of courses to output.
            // We can change this behavior to throwing an exception if needed.
            if (!validSections.isEmpty()){

                // We create a copy of the inputted course and change the section list in the copy
                // so that we don't accidentally overwrite or delete the data of the original.
                Course copy = new Course(course.courseCode(), validSections);

                output.add(copy);
            }
        }

        return output;
    }

    /**
     * Cleans the section list of a course to fit the dataPreFilter
     *
     * @param currentConstraints the constraints currently constraining schedule creation.
     * @param course the course to trim sections of.
     * @return a list of all sections from {@code course} which pass the filter
     * */
    private static List<Section> getValidSections(SConstraints currentConstraints, Course course) {
        List<Section> validSections = new ArrayList<>();
        for (Section section: course.sections()){
            boolean valid = true;

            // If any class in a section breaks one of the constraints,
            // that section is not valid, according to the filter.
            for (Class c: section.classes()){
                boolean isTooEarly = c.startTime().isBefore(currentConstraints.prefStartTime());
                boolean isTooLate = c.startTime().isAfter(currentConstraints.prefEndTime());
                boolean isOnGapDay = c.days().contains(currentConstraints.gapDay());

                if (isTooEarly || isTooLate || isOnGapDay){
                    valid = false;
                    break;
                }
            }

            if (valid){
                validSections.add(section);
            }
        }
        return validSections;
    }

}

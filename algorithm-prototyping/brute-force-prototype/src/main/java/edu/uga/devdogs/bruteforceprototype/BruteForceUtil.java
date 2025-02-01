package edu.uga.devdogs.bruteforceprototype;

import edu.uga.devdogs.sampledataparser.records.Course;
import edu.uga.devdogs.sampledataparser.records.SConstraints;

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
        if (inputCourses.size() > 10){
            throw new Exception("Input course list contains more than ten courses.");
        }

        return inputCourses;
    }

}

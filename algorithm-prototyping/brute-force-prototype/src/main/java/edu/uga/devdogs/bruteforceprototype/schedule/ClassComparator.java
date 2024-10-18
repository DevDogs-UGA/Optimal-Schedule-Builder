package edu.uga.devdogs.bruteforceprototype.schedule;

import edu.uga.devdogs.sampledataparser.records.Class;

import java.util.Comparator;

/**
 * A comparator for comparing two Class objects.
 * The comparison is primarily based on the start time of the class.
 * If the start times are the same, the comparison falls back to using the CRN.
 */
public class ClassComparator implements Comparator<Class> {

    /**
     * Compares two Class objects based on their start time.
     * If the start times are the same, it compares them based on their CRN.
     *
     * @param class1 the first Class to compare
     * @param class2 the second Class to compare
     * @return a negative integer, zero, or a positive integer as the first Class
     *         is less than, equal to, or greater than the second Class.
     */
    @Override
    public int compare(Class class1, Class class2) {
        // Compare based on start time
        int timeComparison = class1.startTime().compareTo(class2.startTime());
        if (timeComparison != 0) {
            return timeComparison;
        }

        // If start times are the same, compare by CRN as a fallback
        return class1.crn().compareTo(class2.crn());
    }
}

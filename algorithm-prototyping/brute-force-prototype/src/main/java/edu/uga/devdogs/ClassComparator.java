package edu.uga.devdogs;

import edu.uga.devdogs.records.Class;

import java.util.Comparator;

public class ClassComparator implements Comparator<Class> {

    @Override
    public int compare(Class class1, Class class2) {
        int timeComparison = class1.startTime().compareTo(class2.startTime());
        if (timeComparison != 0) {
            return timeComparison;
        }

        // If start times are the same, compare by CRN as a fallback
        return class1.crn().compareTo(class2.crn());
    }
}

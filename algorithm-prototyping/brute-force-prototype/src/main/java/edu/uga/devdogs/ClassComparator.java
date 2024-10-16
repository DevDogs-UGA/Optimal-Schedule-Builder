package edu.uga.devdogs;

import edu.uga.devdogs.records.Class;

import java.util.Comparator;

public class ClassComparator implements Comparator<Class> {

    @Override
    public int compare(Class class1, Class class2) {
        return class1.startTime().compareTo(class2.startTime());
    }
}

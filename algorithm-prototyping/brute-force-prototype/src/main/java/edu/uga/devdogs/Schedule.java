package edu.uga.devdogs;

import edu.uga.devdogs.records.Course;
import edu.uga.devdogs.records.Course.Section;
import edu.uga.devdogs.records.Course.Section.Class;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

    // Contains a list of classes for each day of the week
    private List<List<Class>> days;

    public Schedule() {
        this.days = new ArrayList<>(5); // 5 days of classes (Mon-Fri)
    }

    public List<List<Class>> getDays() {
        return days;
    }

    public void addSection(Section section) {

    }

    public boolean validate() {

    }
}


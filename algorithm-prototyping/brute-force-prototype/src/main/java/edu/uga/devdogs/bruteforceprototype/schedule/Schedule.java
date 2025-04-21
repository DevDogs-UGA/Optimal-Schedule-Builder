package edu.uga.devdogs.bruteforceprototype.schedule;

import edu.uga.devdogs.sampledataparser.records.Section;
import edu.uga.devdogs.sampledataparser.records.Class;

import java.time.DayOfWeek;
import java.util.*;

/**
 * The Schedule class represents a collection of Sections and their corresponding
 * Classes organized by days of the week. It organizes the data in a way that
 * allows for Schedule-level and Class-level objectives to be calculated easily.
 */
public class Schedule {

    /**
     * A set of sections included in the schedule. Each section contains multiple classes.
     */
    private final Set<Section> sections;

    /**
     * An EnumMap that organizes classes by day of the week. Each day is associated
     * with a TreeSet of classes, which are sorted by start time.
     */
    private final EnumMap<DayOfWeek, TreeSet<Class>> days;

    /**
     * Constructs a Schedule object that organizes sections and their corresponding classes by days of the week.
     * The classes are sorted by their start time within each day.
     *
     * @param sections a set of sections to be included in the schedule.
     */
    public Schedule(Set<Section> sections) {
        this.sections = new HashSet<>(sections);

        this.days = new EnumMap<>(DayOfWeek.class);
        for (DayOfWeek day : DayOfWeek.values()) {
            days.put(day, new TreeSet<>(new ClassComparator())); // Creates a new TreeSet for each DayOfWeek that sorts Classes based on startTime as defined in ClassComparator
        }

        for (Section section : sections) { // Iterates through all sections
            for (Class _class : section.classes()) { // Iterates through all classes in the current section
                for (DayOfWeek day : _class.days()) { // Iterates through all days in the current class
                    days.get(day).add(_class); // Adds the current class to the TreeSet associated with the current DayOfWeek
                }
            }
        }
    }

    /**
     * Returns the set of sections included in the schedule.
     *
     * @return a Set of Section objects.
     */
    public Set<Section> sections() {
        return sections;
    }

    /**
     * Returns an EnumMap that organizes classes by day of the week.
     * Each day is mapped to a TreeSet of classes, which are sorted by start time.
     *
     * @return an EnumMap mapping DayOfWeek to a Set of classes.
     */
    public EnumMap<DayOfWeek, TreeSet<Class>> days() {
        return days;
    }
}
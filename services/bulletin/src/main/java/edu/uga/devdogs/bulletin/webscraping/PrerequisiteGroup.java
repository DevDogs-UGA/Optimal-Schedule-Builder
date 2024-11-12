package edu.uga.devdogs.bulletin.webscraping;

import java.util.ArrayList;

/**
 * @author Whitman Stewart
 * A prerequisite group represents any of the conditions that would meet  one part of a course's requirements.
 * For example, CSCI 4730's requirements -- (CSCI 4720 or CSCI 4720E or CSEE 4280) and CSCI 2720 -- would have two
 * prerequisite groups: "CSCI 4720 or CSCI 4720E or CSEE 4280", and "CSCI 2720."
 */
public class PrerequisiteGroup {
    private boolean requiresHonors;

    public String getCourseName() {
        return courseName;
    }

    private String courseName;

    public boolean requiresHonors() {
        return requiresHonors;
    } // True if this prerequisite can only be fulfilled by being in the honor's program, i.e. honors only class.

    public void setRequiresHonors(boolean requiresHonors) {
        this.requiresHonors = requiresHonors;
    }

    public boolean canSubstituteDepartmentPermission() {
        return canSubstituteDepartmentPermission;
    } //If this condition is true, this prerequisite can be fulfilled by taking one of the classes OR having dept. permission.

    public boolean requiresDepartmentPermission() {
        return canSubstituteDepartmentPermission && courses.isEmpty();
    } //True if the ONLY way to fulfill this prerequisite is by having department permission.

    public void setCanSubstituteDepartmentPermission(boolean canSubstituteDepartmentPermission) {
        this.canSubstituteDepartmentPermission = canSubstituteDepartmentPermission;
    }

    private boolean canSubstituteDepartmentPermission;


    public ArrayList<String> getCourses() {
        return courses;
    }

    private ArrayList<String> courses = new ArrayList<>();

    // Constructor to create a PrerequisiteGroup from scratch.
    public PrerequisiteGroup(String courseName) {
        this.courseName = courseName;
    }

    // Used to clone a prerequisite group with just a different name in the case that the Bulletin lists multiple courses on one line.
    public PrerequisiteGroup(String courseName, PrerequisiteGroup existingPrerequisiteGroup) {
        this.courseName = courseName;
        this.requiresHonors = existingPrerequisiteGroup.requiresHonors();
        this.canSubstituteDepartmentPermission = existingPrerequisiteGroup.canSubstituteDepartmentPermission();
        this.courses = existingPrerequisiteGroup.getCourses();
    }

    public void addCourse(String prerequisite) {
        courses.add(prerequisite);
    }

    @Override
    public String toString() {
        String toString = "PrerequisiteGroup [ ";
        toString += "\n\tCourse Name: " + courseName;
        toString += "\n\tRequires honors: " + requiresHonors;
        toString += "\n\tCan substitute Dept. Permission: " + canSubstituteDepartmentPermission;
        toString += "\n\tRequires Dept. Permission: " + requiresDepartmentPermission();
        toString += "\n\tClasses: ";
        for (String prerequisite : courses) {
            toString += "\n\t" + prerequisite;
        }
        toString += "\n]";

        return toString;
    }
}

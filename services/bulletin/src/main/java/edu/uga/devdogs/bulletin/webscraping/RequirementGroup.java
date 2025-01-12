package edu.uga.devdogs.bulletin.webscraping;

import org.openqa.selenium.internal.Require;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Whitman Stewart
 * A RequirementGroup represents any of the conditions that would meet one part of a course's requirements.
 *
 * For example, CSCI 4730's requirements -- (CSCI 4720 or CSCI 4720E or CSEE 4280) and CSCI 2720 -- would have two
 * requirement groups: "CSCI 4720 or CSCI 4720E or CSEE 4280", and "CSCI 2720."
 *
 * Note that ALL RequirementGroups that a class may have must be met for a student to be eligible to take a class.
 */
public class RequirementGroup {
    private boolean requiresHonors;
    private boolean canSubstituteDepartmentPermission;
    private String courseName;
    private ArrayList<String> courses = new ArrayList<>();

    /**
     * Creates a new RequirementGroup.
     * @param courseName The name of the course that these requirements are for.
     */
    public RequirementGroup(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Gets the name of the course that these requirements are for.
     * @return the name of the course as a string.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Checks if a student must be in the honor's program to satisfy this requirement. (i.e. an honors-only course)
     * @return true if honors is a requirement; false otherwise.
     */
    public boolean requiresHonors() {
        return requiresHonors;
    }

    /**
     * Sets whether a student must be in the honor's program to satisfy the requirement.
     * @param requiresHonors true to set honors as a requirement; false otherwise.
     */
    public void setRequiresHonors(boolean requiresHonors) {
        this.requiresHonors = requiresHonors;
    }

    /**
     * Checks whether a student can bypass taking a class from this group if they have permission of the department.
     * @return true if the course(s) in this requirement can be bypassed by having department permission; false otherwise.
     */
    public boolean canSubstituteDepartmentPermission() {
        return canSubstituteDepartmentPermission;
    }

    /**
     * Checks whether a student MUST have department permission to fulfill this requirement (i.e. a restricted class.)
     * @return true if the class is restricted and department permission is required to fulfill this requirement; false otherwise.
     */
    public boolean requiresDepartmentPermission() {
        return canSubstituteDepartmentPermission && courses.isEmpty();
    }

    /**
     * Sets whether a student can bypass taking a class from this group if they have permission of the department.
     * @param canSubstituteDepartmentPermission true if this requirement can be fulfilled by having department permission; false otherwise.
     */
    public void setCanSubstituteDepartmentPermission(boolean canSubstituteDepartmentPermission) {
        this.canSubstituteDepartmentPermission = canSubstituteDepartmentPermission;
    }

    /**
     * Adds a course to this RequirementGroup that can fill this requirement.
     * @param prerequisite The name of the course that can fill this requirement represented as a string; i.e. "FANR 1100"
     */
    public void addCourse(String prerequisite) {
        courses.add(prerequisite);
    }

    /**
     * Gets the list of course names that can fulfill this requirement.
     * @return An ArrayList of course names as strings; i.e. "FANR 1100"
     */
    public ArrayList<String> getCourses() {
        return courses;
    }


    /**
     * Returns a string representation of this object.
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        String toString = "RequirementGroup [ ";
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

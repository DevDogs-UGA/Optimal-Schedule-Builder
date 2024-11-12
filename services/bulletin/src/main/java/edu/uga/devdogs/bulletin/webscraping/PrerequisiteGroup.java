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

    public String getClassName() {
        return className;
    }

    private String className;

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
        return canSubstituteDepartmentPermission && classes.isEmpty();
    } //True if the ONLY way to fulfill this prerequisite is by having department permission.

    public void setCanSubstituteDepartmentPermission(boolean canSubstituteDepartmentPermission) {
        this.canSubstituteDepartmentPermission = canSubstituteDepartmentPermission;
    }

    private boolean canSubstituteDepartmentPermission;


    private ArrayList<String> classes = new ArrayList<>();
    public PrerequisiteGroup(String className) {
        this.className = className;
    }

    public void addClass(String prerequisite) {
        classes.add(prerequisite);
    }

    @Override
    public String toString() {
        String toString = "PrerequisiteGroup [ ";
        toString += "\n\tClass Name: " + className;
        toString += "\n\tRequires honors: " + requiresHonors;
        toString += "\n\tCan substitute Dept. Permission: " + canSubstituteDepartmentPermission;
        toString += "\n\tRequires Dept. Permission: " + requiresDepartmentPermission();
        toString += "\n\tClasses: ";
        for (String prerequisite : classes) {
            toString += "\n\t" + prerequisite;
        }
        toString += "\n]";

        return toString;
    }
}

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

    public void setClassName(String className) {
        this.className = className;
    }

    private String className;

    public boolean requiresHonors() {
        return requiresHonors;
    }

    public void setRequiresHonors(boolean requiresHonors) {
        this.requiresHonors = requiresHonors;
    }

    public boolean canSubstituteDepartmentPermission() {
        return canSubstituteDepartmentPermission;
    } /*If this condition is true and there are no classes inside of this group, the prerequisite can only be
    met by having department permission (that is, it is restricted). If this condition is true and there are classes inside
    of the group, the prerequisite can be met by taking one of the classes OR by having department permission.
     */

    public void setCanSubstituteDepartmentPermission(boolean canSubstituteDepartmentPermission) {
        this.canSubstituteDepartmentPermission = canSubstituteDepartmentPermission;
    }

    private boolean canSubstituteDepartmentPermission;

    public ArrayList<PrerequisiteClass> getClasses() {
        return classes;
    }

    private ArrayList<PrerequisiteClass> classes = new ArrayList<>(); //Taking one of these classes means the prerequisite has been met.

    public PrerequisiteGroup(String className) {
        this.className = className;
    }

    public void addClass(PrerequisiteClass prerequisite) {
        classes.add(prerequisite);
    }

    @Override
    public String toString() {
        String toString = "PrerequisiteGroup [ ";
        toString += "\n\tClass Name: " + className;
        toString += "\n\tHonors: " + requiresHonors;
        toString += "\n\tCan substitute Dept. Permission: " + canSubstituteDepartmentPermission;
        toString += "\n\tClasses: ";
        for (PrerequisiteClass prerequisite : classes) {
            toString += "\n\t" + prerequisite;
        }
        toString += "]";

        return toString;
    }
}

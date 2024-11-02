package edu.uga.devdogs.bulletin.webscraping;

import java.util.ArrayList;

public class PrerequisiteGroup {
    private boolean requiresHonors;

    public boolean requiresHonors() {
        return requiresHonors;
    }

    public void setRequiresHonors(boolean requiresHonors) {
        this.requiresHonors = requiresHonors;
    }

    public boolean canSubstituteDepartmentPermission() {
        return canSubstituteDepartmentPermission;
    }

    public void setCanSubstituteDepartmentPermission(boolean canSubstituteDepartmentPermission) {
        this.canSubstituteDepartmentPermission = canSubstituteDepartmentPermission;
    }

    private boolean canSubstituteDepartmentPermission;

    public ArrayList<PrerequisiteClass> getClasses() {
        return classes;
    }

    private ArrayList<PrerequisiteClass> classes = new ArrayList<>();

    public PrerequisiteGroup() {
    }

    public void addClass(PrerequisiteClass prerequisite) {
        classes.add(prerequisite);
    }

    @Override
    public String toString() {
        String toString = "PrerequisiteGroup [ ";
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

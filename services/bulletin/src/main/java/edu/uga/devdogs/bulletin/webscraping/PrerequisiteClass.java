package edu.uga.devdogs.bulletin.webscraping;

import java.util.ArrayList;

/**
 * @author Whitman Stewart
 * Represents a course from the UGA Bulletin, including any alternative names it may have.
 */
public class PrerequisiteClass {
    public ArrayList<String> getPrefixes() {
        return prefixes;
    }

    public ArrayList<String> getSuffixes() {
        return suffixes;
    }

    // A course can have one or more prefixes or suffixes that represent the same class (i.e. CSCI 4730/6730 are the same class)
    private ArrayList<String> prefixes;
    private ArrayList<String> suffixes;




    public PrerequisiteClass(ArrayList<String> prefixes, ArrayList<String> suffixes) {
        this.prefixes = prefixes;
        this.suffixes = suffixes;
    }

    @Override
    public String toString() {
        String toString = "Prerequisite [ \n\tPrefix:";
        for (String prefix : prefixes) {
            toString += prefix + ",";
        }
        toString += "\n\t";
        toString += "Suffix:";
        for (String suffix : suffixes) {
            toString += suffix + ",";
        }
        toString += "\n";
        toString += "]";
        return toString;
    }





}

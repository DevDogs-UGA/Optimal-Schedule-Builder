package edu.uga.devdogs.course_information.webscraping;

public class ProfessorRating {
    private final String name;
    private final String department;
    private final double avgRating;
    private final int numRatings;

    public ProfessorRating(String name, String department, double avgRating, int numRatings) {
        this.name = name;
        this.department = department;
        this.avgRating = avgRating;
        this.numRatings = numRatings;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getAvgRating() { return avgRating; }
    public int getNumRatings() { return numRatings; }

    @Override
    public String toString() {
        return name + " (" + department + ") — Rating: " + avgRating + " [" + numRatings + " ratings]";
    }
    
}

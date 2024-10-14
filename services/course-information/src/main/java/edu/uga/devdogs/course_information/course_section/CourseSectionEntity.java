package edu.uga.devdogs.course_information.course_section;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CourseSectionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long courseSectionId;

    private int crn;

    private int sec;

    private char stat;

    private double creditHoursLow;

    private double creditHoursHigh;

    private String instructor;

    private int term;

    private int classSize;

    private int seatsAvailable;

    private int year;

    public CourseSectionEntity() {}

    public CourseSectionEntity(long courseSectionId, int crn, int sec, char stat, double creditHoursLow,
            double creditHoursHigh, String instructor, int term, int classSize, int seatsAvailable, int year) {
        this.courseSectionId = courseSectionId;
        this.crn = crn;
        this.sec = sec;
        this.stat = stat;
        this.creditHoursLow = creditHoursLow;
        this.creditHoursHigh = creditHoursHigh;
        this.instructor = instructor;
        this.term = term;
        this.classSize = classSize;
        this.seatsAvailable = seatsAvailable;
        this.year = year;
    }

    public CourseSectionEntity(int crn, int sec, char stat, double creditHoursLow, double creditHoursHigh, String instructor,
            int term, int classSize, int seatsAvailable, int year) {
        this.crn = crn;
        this.sec = sec;
        this.stat = stat;
        this.creditHoursLow = creditHoursLow;
        this.creditHoursHigh = creditHoursHigh;
        this.instructor = instructor;
        this.term = term;
        this.classSize = classSize;
        this.seatsAvailable = seatsAvailable;
        this.year = year;
    }

    public long getId() {
        return courseSectionId;
    }

    public void setId(long id) {
        this.courseSectionId = id;
    }

    public int getCrn() {
        return crn;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public char getStat() {
        return stat;
    }

    public void setStat(char stat) {
        this.stat = stat;
    }

    public double getCreditHoursLow() {
        return creditHoursLow;
    }

    public void setCreditHoursLow(double creditHoursLow) {
        this.creditHoursLow = creditHoursLow;
    }

    public double getCreditHoursHigh() {
        return creditHoursHigh;
    }

    public void setCreditHoursHigh(double creditHoursHigh) {
        this.creditHoursHigh = creditHoursHigh;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getClassSize() {
        return classSize;
    }

    public void setClassSize(int classSize) {
        this.classSize = classSize;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "CourseSectionEntity [courseSectionId=" + courseSectionId + ", crn=" + crn + ", sec=" + sec + ", stat="
                + stat + ", creditHoursLow=" + creditHoursLow + ", creditHoursHigh=" + creditHoursHigh + ", instructor="
                + instructor + ", term=" + term + ", classSize=" + classSize + ", seatsAvailable=" + seatsAvailable
                + ", year=" + year + "]";
    }

}
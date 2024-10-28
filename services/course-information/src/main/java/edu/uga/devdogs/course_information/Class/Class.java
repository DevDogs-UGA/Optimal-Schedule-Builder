package edu.uga.devdogs.course_information.Class;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

import edu.uga.devdogs.course_information.CourseSection.CourseSection;

/*
 * Java JPA entity represention for Class
 */
@Entity
public class Class implements Serializable{
    
    /*
     * Variables
     */

    @Id
    @GeneratedValue
    private int classId;

    private String days;

    private  java.sql.Time startTime;

    private  java.sql.Time endTime;

    private String building;

    private String room;

    private String campus;

    /*
     * Relationships
     * To-Do: add relationships (one-to-one, one-to-many, many-to-one, many-to-many) here
     */

    @ManyToOne
    @JoinColumn(name = "courseSectionId")
    private CourseSection courseSection;

     /*
      * Constructors
      */
    
     // Default constructor
     public Class() {
     }

     // Constructor w/o classID
     public Class(String days, java.sql.Time startTime, java.sql.Time endTime, String building, String room, String campus, CourseSection courseSection) {
         this.days = days;
         this.startTime = startTime;
         this.endTime = endTime;
         this.building = building;
         this.room = room;
         this.campus = campus;
         this.courseSection = courseSection;
     }

     // Constructor w/ classID
     public Class(int classId, String days, java.sql.Time startTime, java.sql.Time endTime, String building, String room, String campus, CourseSection courseSection) {
         this.classId = classId;
         this.days = days;
         this.startTime = startTime;
         this.endTime = endTime;
         this.building = building;
         this.room = room;
         this.campus = campus;
         this.courseSection = courseSection;
     }

    /*
    * Getters and Setters
    */

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public java.sql.Time getStartTime() {
        return startTime;
    }

    public void setStartTime(java.sql.Time startTime) {
        this.startTime = startTime;
    }

    public java.sql.Time getEndTime() {
        return endTime;
    }

    public void setEndTime(java.sql.Time endTime) {
        this.endTime = endTime;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public CourseSection getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(CourseSection courseSection){
        this.courseSection = courseSection;
    }

    /*
     * toString
     */
    @Override
    public String toString() {
        return "Class [classId=" + classId
            + ", days=" + days 
            + ", startTime=" + startTime 
            + ", endTime=" + endTime
            + ", building=" + building
            + ", room=" + room
            + ", campus=" + campus + "]";
    }
}

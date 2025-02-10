package edu.uga.devdogs.course_information.Class;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

import edu.uga.devdogs.course_information.Building.Building;
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

    private String startTime;

    private String endTime;

    private String room;

    private String campus;

    /*
     * Relationships
     * To-Do: add relationships (one-to-one, one-to-many, many-to-one, many-to-many) here
     */

    @ManyToOne
    @JoinColumn(name = "courseSectionId")
    private CourseSection courseSection;

    @ManyToOne
    @JoinColumn(name = "buildingNumber")
    private Building building;
     /*
      * Constructors
      */
    
     // Default constructor
     public Class() {
     }

     // Constructor w/o classID
     public Class(String days, String startTime, String endTime, Building building, String room, String campus, CourseSection courseSection) {
         this.days = days;
         this.startTime = startTime;
         this.endTime = endTime;
         this.building = building;
         this.room = room;
         this.campus = campus;
         this.courseSection = courseSection;
     }

     // Constructor w/ classID
     public Class(int classId, String days, String startTime, String endTime, Building building, String room, String campus, CourseSection courseSection) {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
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
            + ", room=" + room
            + ", campus=" + campus + "]";
    }
}

package edu.uga.devdogs.course_information.Class;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

import edu.uga.devdogs.course_information.Building.Building;
import edu.uga.devdogs.course_information.CourseSection.CourseSection;
import edu.uga.devdogs.course_information.Professor.Professor;

/*
 * Java JPA entity represention for Class
 */
@Entity
public class ClassEntity implements Serializable{
    
    /*
     * Variables
     */

    @Id
    @GeneratedValue
    private int classId;

    private String days;

    private  String startTime;

    private  String endTime;

    private String room;

    private String campus;

    /*
     * Relationships
     */

    @ManyToOne
    @JoinColumn(name = "course_section_id")
    private CourseSection courseSection;

    @ManyToOne
    @JoinColumn(name = "buildingNumber")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;  

     /*
      * Constructors
      */
    
     // Default constructor
     public ClassEntity() {
     }

     // Constructor w/o classID
     public ClassEntity(String days, String startTime, String endTime, Building building, String room, String campus, CourseSection courseSection) {
         this.days = days;
         this.startTime = startTime;
         this.endTime = endTime;
         this.building = building;
         this.room = room;
         this.campus = campus;
         this.courseSection = courseSection;
     }

     // Constructor w/ classID
     public ClassEntity(int classId, String days, String startTime, String endTime, Building building, String room, String campus, CourseSection courseSection) {
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

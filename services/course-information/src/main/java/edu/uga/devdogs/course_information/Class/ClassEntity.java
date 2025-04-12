package edu.uga.devdogs.course_information.Class;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.time.LocalTime;

import edu.uga.devdogs.course_information.Building.Building;
import edu.uga.devdogs.course_information.CourseSection.CourseSection;
import edu.uga.devdogs.course_information.Professor.Professor;
import edu.uga.devdogs.course_information.webscraping.Course2;

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

    private  LocalTime endTime;

    private  LocalTime startTime;

    

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

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
     public ClassEntity(String days, LocalTime startTime, LocalTime endTime, Building building, String room, String campus, CourseSection courseSection) {
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

    public void updateFrom (Course2 course, LocalTime startTime, LocalTime endTime, Building building) {
        this.days = course.getMeetingDays();
        this.startTime = startTime;
        this.endTime = endTime;
        this.building = building;
        this.campus = course.getCampus();
    }
}

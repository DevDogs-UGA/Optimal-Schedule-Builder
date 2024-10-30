package edu.uga.devdogs.course_information.Course;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;

import edu.uga.devdogs.course_information.CourseSection.CourseSection;


@Entity
public class Course implements Serializable {
    //Variables
    @Id
    @GeneratedValue
    private long courseId;

    private String subject;

    private String courseNumber;

    private String title;

    private String department;

    //Relationships
    @OneToMany(mappedBy = "course")
    private List<CourseSection> courseSections;

    //Constructors, getters, setters...
    public Course() {
    }

    public Course(String subject, String courseNumber, String title, String department, List<CourseSection> courseSections) {
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.title = title;
        this.department = department;
        this.courseSections = courseSections;
    }

    public Course(long courseId, String subject, String courseNumber, String title, String department, List<CourseSection> courseSections) {
        this.courseId = courseId;
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.title = title;
        this.department = department;
        this.courseSections = courseSections;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<CourseSection> getCourseSections() {
        return courseSections;
    }

    public void setCourseSections(List<CourseSection> courseSections) {
        this.courseSections = courseSections;
    }

    @Override
    public String toString() {
        return "Course [courseId=" + courseId + ", subject=" + subject + ", courseNumber=" + courseNumber + ", title="
                + title + ", department=" + department + "]";
    }

    
}

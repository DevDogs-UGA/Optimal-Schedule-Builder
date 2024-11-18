package edu.uga.devdogs.course_information.Course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import edu.uga.devdogs.course_information.CourseSection.CourseSection;


@Entity
public class Course implements Serializable {
    //Variables
    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private long courseId;

    private String subject;

    private String courseNumber;

    private String title;
     
    private String department;

    //Relationships
    @OneToMany(mappedBy = "course")
    private List<CourseSection> courseSections;
    
    //variables for bulletin

    private String courseDescription;

    private String athenaTitle;

    @ManyToMany
    @JoinTable(
        name = "equiv_courses_junction", 
        joinColumns = @JoinColumn(name = "course_id"), 
        inverseJoinColumns = @JoinColumn(name = "equiv_course_id")
    )
    List<Course> equivelantCourses;

    @ManyToMany
    @JoinTable(
        name = "pre_req_junction", 
        joinColumns = @JoinColumn(name = "course_id"), 
        inverseJoinColumns = @JoinColumn(name = "prereq_course_id")
    )
    List<Course> prerequisiteCourses;

    private String semesterCourseOffered;

    private String gradingSystem;


    //getters and setters for bulletin
    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getAthenaTitle() {
        return athenaTitle;
    }

    public void setAthenaTitle(String athenaTitle) {
        this.athenaTitle = athenaTitle;
    }

    public List<Course> getEquivelantCourses() {
        return equivelantCourses;
    }

    public void setEquivelantCourses(List<Course> equivelantCourses) {
        this.equivelantCourses = equivelantCourses;
    }

    public List<Course> getPrerequisiteCourses() {
        return prerequisiteCourses;
    }

    public void setPrerequisiteCourses(List<Course> prerequisiteCourses) {
        this.prerequisiteCourses = prerequisiteCourses;
    }

    public String getSemesterCourseOffered() {
        return semesterCourseOffered;
    }

    public void setSemesterCourseOffered(String semesterCourseOffered) {
        this.semesterCourseOffered = semesterCourseOffered;
    }

    public String getGradingSystem() {
        return gradingSystem;
    }

    public void setGradingSystem(String gradingSystem) {
        this.gradingSystem = gradingSystem;
    }

    //Constructors, getters, setters...
    public Course() {

    }

    //constructor for course
    public Course(String subject, String courseNumber, String title, String department, List<CourseSection> courseSections) {
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.title = title;
        this.department = department;
        this.courseSections = courseSections;
    }

    //constructor for course with id
    public Course(long courseId, String subject, String courseNumber, String title, String department, List<CourseSection> courseSections) {
        this.courseId = courseId;
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.title = title;
        this.department = department;
        this.courseSections = courseSections;
    }

    //getters and setters for course
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

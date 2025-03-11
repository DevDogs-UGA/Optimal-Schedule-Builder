package edu.uga.devdogs.course_information.Course;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import edu.uga.devdogs.course_information.CourseSection.CourseSection;


// Java JPA entity represention for Class
@Entity
public class Course implements Serializable {
    // Variables
    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private long courseId;

    private String title;

    private String subject;

    private String courseNumber;

    private String department;

    private String courseInfoById;

    private List<CourseSection> courseSectionsById;

    private List<CourseSection> preRequisites;

    private List<CourseSection> coRequisites;


    // Relationships
    @OneToMany(mappedBy = "course")
    private List<CourseSection> courseSections;
    
    //variables for bulletin

    private String courseDescription;

    private String athenaTitle;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "equiv_courses_junction", 
        joinColumns = @JoinColumn(name = "course_id"), 
        inverseJoinColumns = @JoinColumn(name = "equiv_course_id")
    )
    List<Course> equivalentCourses;

    @ManyToMany
    @JoinTable(
        name = "pre_req_junction", 
        joinColumns = @JoinColumn(name = "course_id"), 
        inverseJoinColumns = @JoinColumn(name = "prereq_course_id")
    )
    List<Course> prerequisiteCourses;

    @ElementCollection
    private List<String> semesters;

    private String gradingSystem;

    public Course(long courseId, String subject, String courseNumber, String title, String department,
            List<CourseSection> courseSections, String courseDescription, String athenaTitle,
            List<Course> equivalentCourses, List<Course> prerequisiteCourses, List<String> semesters,
            String gradingSystem) {
        this.courseId = courseId;
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.title = title;
        this.department = department;
        this.courseSections = courseSections;
        this.courseDescription = courseDescription;
        this.athenaTitle = athenaTitle;
        this.equivalentCourses = equivalentCourses;
        this.prerequisiteCourses = prerequisiteCourses;
        this.semesters = semesters;
        this.gradingSystem = gradingSystem;
    }
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

    public List<Course> getEquivalentCourses() {
        return equivalentCourses;
    }

    public void setEquivalentCourses(List<Course> equivelantCourses) {
        this.equivalentCourses = equivelantCourses;
    }

    public List<Course> getPrerequisiteCourses() {
        return prerequisiteCourses;
    }

    public void setPrerequisiteCourses(List<Course> prerequisiteCourses) {
        this.prerequisiteCourses = prerequisiteCourses;
    }

    public List<String> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<String> semesters) {
        this.semesters = semesters;
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

    public Course(String subject, String courseNumber, String title, String department, List<CourseSection> courseSections, 
    String courseInfoById, List<CourseSection> preRequisites, List<CourseSection> coRequisites) {
        this.title = title;
    //constructor for course
    public Course(String subject, String courseNumber, String title, String department, List<CourseSection> courseSections) {
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.department = department;
        this.courseInfoById = courseInfoById;
        this.preRequisites = preRequisites;
        this.coRequisites = coRequisites;
        this.courseSections = courseSections;
    }

    public Course(long courseId, String subject, String courseNumber, String title, String department, List<CourseSection> courseSections, 
    String courseInfoById, List<CourseSection> preRequisites, List<CourseSection> coRequisites) {    
        this.courseId = courseId;
        this.title = title;
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.department = department;
        this.courseInfoById = courseInfoById;
        this.preRequisites = preRequisites;
        this.coRequisites = coRequisites;
        this.courseSections = courseSections;
        this.equivalentCourses = new ArrayList<>();
        this.prerequisiteCourses = new ArrayList<>();
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

    public String getCourseInfoById() {
        return courseInfoById;
    }

    public void setCourseInfoById(String courseInfoById) {
        this.courseInfoById = courseInfoById;
    }

                public List<CourseSection> getCourseSections() {
                    return courseSections;
                }

    public void setCourseSections(List<CourseSection> courseSections) {
        this.courseSections = courseSections;
    }

    public List<CourseSection> getCourseSectionsById(long id) {
        return courseSectionsById;
    }

    public List<CourseSection> getPrerequisets(long id) {
        return preRequisites;
    }

    public void setPrerequisets(List<CourseSection> preRequisites) {
        this.preRequisites = preRequisites;
    }

    public List<CourseSection> getCorequisets(long id) {
        return coRequisites;
    }

    public void setCorequisets(List<CourseSection> coRequisites) {
        this.coRequisites = coRequisites;
    }
 
    @Override
    public String toString() {
        return "Course {" +
            "courseId=" + courseId + ", " +
            "title='" + title + '\'' + ", " +
            "subject='" + subject + '\'' + ", " +
            "courseNumber='" + courseNumber + '\'' + ", " +
            "department='" + department + '\'' + ", " +
            "courseInfoById='" + courseInfoById + '\'' + ", " +
            "courseSectionsById=" + formatList(courseSectionsById) + ", " +
            "preRequisites=" + formatList(preRequisites) + ", " +
            "coRequisites=" + formatList(coRequisites) +
            '}';
}

    private String formatList(List<CourseSection> list) {
        if (list == null || list.isEmpty()) {
            return "[]";
        }
        return list.stream()
               .map(Object::toString)
               .reduce((s1, s2) -> s1 + ", " + s2)
               .map(result -> "[" + result + "]")
               .orElse("[]");
    }

    
}

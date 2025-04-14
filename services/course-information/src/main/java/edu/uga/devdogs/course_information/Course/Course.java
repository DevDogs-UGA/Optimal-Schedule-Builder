package edu.uga.devdogs.course_information.Course;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import edu.uga.devdogs.course_information.CourseSection.CourseSection;
import edu.uga.devdogs.course_information.webscraping.Course2;


@Entity
@Table(name = "course", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"subject", "courseNumber"})
})
public class Course implements Serializable {
    // Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private long courseId;

    private String title;
    private String subject;
    private String courseNumber;
    private String department;

    @Column(name = "course_description", columnDefinition = "TEXT")
    private String courseDescription;
    
    private String athenaTitle;
    private String gradingSystem;

    // Relationships
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference("course-sections")
    private List<CourseSection> courseSections = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "pre_req_junction", 
        joinColumns = @JoinColumn(name = "course_id"), 
        inverseJoinColumns = @JoinColumn(name = "prereq_course_id")
    )
    private List<Course> preRequisites = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "co_req_junction", 
        joinColumns = @JoinColumn(name = "course_id"), 
        inverseJoinColumns = @JoinColumn(name = "coreq_course_id")
    )
    private List<Course> coRequisites = new ArrayList<>();

    @ElementCollection
    private List<String> semesters = new ArrayList<>();

    // Default constructor
    public Course() {
        this.courseSections = new ArrayList<>();
        this.preRequisites = new ArrayList<>();
        this.coRequisites = new ArrayList<>();
        this.semesters = new ArrayList<>();
    }

    public Course(String subject, String courseNumber, String title, String department, List<CourseSection> courseSections, String courseDescription) {
        this();
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.title = title;
        this.department = department;
        if (courseSections != null) {
            this.courseSections = courseSections;
        }
        this.courseDescription = courseDescription;
    }

    public Course(String subject, String courseNumber, String title, String department, 
                 String courseDescription, String athenaTitle, String gradingSystem) {
        this();
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.title = title;
        this.department = department;
        this.courseDescription = courseDescription;
        this.athenaTitle = athenaTitle;
        this.gradingSystem = gradingSystem;
    }

    public Course(String subject, String courseNumber, String title, String department, 
                 List<CourseSection> courseSections, List<Course> preRequisites, 
                 List<Course> coRequisites) {    
        this();
        this.title = title;
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.department = department;
        if (preRequisites != null) {
            this.preRequisites = preRequisites;
        }
        if (coRequisites != null) {
            this.coRequisites = coRequisites;
        }
        if (courseSections != null) {
            this.courseSections = courseSections;
        }
    }

    public Course(long courseId, String subject, String courseNumber, String title, String department,
            List<CourseSection> courseSections, String courseDescription, String athenaTitle,
            List<String> semesters, String gradingSystem) {
        this();
        this.courseId = courseId;
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.title = title;
        this.department = department;
        if (courseSections != null) {
            this.courseSections = courseSections;
        }
        this.courseDescription = courseDescription;
        this.athenaTitle = athenaTitle;
        if (semesters != null) {
            this.semesters = semesters;
        }
        this.gradingSystem = gradingSystem;
    }

    // Getters and setters
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

    public List<Course> getPreRequisites() {
        return preRequisites;
    }

    public void setPreRequisites(List<Course> preRequisites) {
        this.preRequisites = preRequisites;
    }

    public List<Course> getCoRequisites() {
        return coRequisites;
    }

    public void setCoRequisites(List<Course> coRequisites) {
        this.coRequisites = coRequisites;
    }

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

    @Override
    public String toString() {
        return "Course {" +
            "courseId=" + courseId + ", " +
            "title='" + title + '\'' + ", " +
            "subject='" + subject + '\'' + ", " +
            "courseNumber='" + courseNumber + '\'' + ", " +
            "department='" + department + '\'' + ", " +
            "preRequisites=" + formatCourseList(preRequisites) + ", " +
            "coRequisites=" + formatCourseList(coRequisites) +
            '}';
    }

    private String formatCourseList(List<Course> list) {
        if (list == null || list.isEmpty()) {
            return "[]";
        }
        return list.stream()
               .map(c -> c.getSubject() + " " + c.getCourseNumber())
               .reduce((s1, s2) -> s1 + ", " + s2)
               .map(result -> "[" + result + "]")
               .orElse("[]");
    }

    public void updateFrom(Course2 course) {
        this.subject = course.getSubject();
        this.title = course.getTitle();
        this.department = course.getDepartment();
    }
}

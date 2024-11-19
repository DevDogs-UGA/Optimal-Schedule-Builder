package edu.uga.devdogs.course_information.Course;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import edu.uga.devdogs.course_information.CourseSection.CourseSection;
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
    //this 
    private String department;

        //Relationships
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

    public Course(String subject, String courseNumber, String title, String department, List<CourseSection> courseSections) {
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.title = title;
        this.department = department;
        this.courseSections = courseSections;
        this.equivalentCourses = new ArrayList<>();
        this.prerequisiteCourses = new ArrayList<>();
    }

    public Course(long courseId, String subject, String courseNumber, String title, String department, List<CourseSection> courseSections) {
        this.courseId = courseId;
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.title = title;
        this.department = department;
        this.courseSections = courseSections;
    }

                    // getters and setters for course
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

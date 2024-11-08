package edu.uga.devdogs.course_information.Course;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    private long courseId;

    private String title;

    private String subject;

    private String courseNumber;

    private String department;

    private String courseInfoById;

    private List<CourseSection> courseSectionsById;

    private List<CourseSection> preRequisites;

    private List<CourseSection> coRequisites;



    //Relationships
    @OneToMany(mappedBy = "course")
    private List<CourseSection> courseSections;
    
    //variables for bulletin


    @ManyToOne()
    private int departmentid;

    private String description;

    private int credits;

    private String prerequisites;

    private String level;

    private String schedule;

    private String location;

    private String semester;

    private int year;

    private int maxEnrollment;

    private int currentEnrollment;

    private int waitlistAvailable;

    private int waitlistCount;

    //marked as int in DB Design
    private int isOnline;

    private String gradeScale;

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    public void setCurrentEnrollment(int currentEnrollment) {
        this.currentEnrollment = currentEnrollment;
    }

    public int getWaitlistAvailable() {
        return waitlistAvailable;
    }

    public void setWaitlistAvailable(int waitlistAvailable) {
        this.waitlistAvailable = waitlistAvailable;
    }

    public int getWaitlistCount() {
        return waitlistCount;
    }

    public void setWaitlistCount(int waitlistCount) {
        this.waitlistCount = waitlistCount;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public String getGradeScale() {
        return gradeScale;
    }

    public void setGradeScale(String gradeScale) {
        this.gradeScale = gradeScale;
    }

    //Constructors, getters, setters...
    public Course() {
    }

    public Course(String subject, String courseNumber, String title, String department, List<CourseSection> courseSections, 
    String courseInfoById, List<CourseSection> preRequisites, List<CourseSection> coRequisites) {
        this.title = title;
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

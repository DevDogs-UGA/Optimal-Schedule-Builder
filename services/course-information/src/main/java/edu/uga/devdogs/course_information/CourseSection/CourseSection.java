package edu.uga.devdogs.course_information.CourseSection;

import java.io.Serializable;
import java.util.List;

import edu.uga.devdogs.course_information.Class.ClassEntity;
import edu.uga.devdogs.course_information.Course.Course;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class CourseSection implements Serializable {
    // Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_section_id")
    private long courseSectionId;

    private int crn;

    private String sec;

    private char stat;

    private String creditHours;

    private String instructor;

    private String term;

    private int classSize;

    private int seatsAvailable;

    private int year;

    private String daysOfTheWeek;

    private String meetingTime;
    


    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

    public void setDaysOfTheWeek(String daysOfTheWeek) {
        this.daysOfTheWeek = daysOfTheWeek;
    }

    // Relationships
    @ManyToOne
    @JoinColumn(name = "course_Id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "courseSection", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClassEntity> classes;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;



    // Constructors, getters, setters, and toString 
    public CourseSection() {}

    public CourseSection(int crn, String sec, char stat, String creditHoursLow, String creditHoursHigh, String instructor,
        String term, int classSize, int seatsAvailable, int year, Course course, List<ClassEntity> classes, String daysOfTheWeek, String meetingTime) {
    this.crn = crn;
    this.sec = sec;
    this.stat = stat;
    this.creditHours = creditHours;
    this.instructor = instructor;
    this.term = term;
    this.classSize = classSize;
    this.seatsAvailable = seatsAvailable;
    this.year = year;
    this.course = course;
    this.classes = classes;
    this.daysOfTheWeek = daysOfTheWeek;
    this.meetingTime = meetingTime;
}

public CourseSection(int crn, String sec, char stat, String creditHoursLow, String creditHoursHigh, String instructor,
String term, int classSize, int seatsAvailable, Course course, List<ClassEntity> classes) {
this.crn = crn;
this.sec = sec;
this.stat = stat;
this.creditHours = creditHours;
this.instructor = instructor;
this.term = term;
this.classSize = classSize;
this.seatsAvailable = seatsAvailable;
this.course = course;
this.classes = classes;
}

    public long getId() {
        return courseSectionId;
    }

    public void setId(long id) {
        this.courseSectionId = id;
    }

    public int getCrn() {
        return crn;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public char getStat() {
        return stat;
    }

    public void setStat(char stat) {
        this.stat = stat;
    }

    public String getCreditHours() {
        return creditHours;
    }

    public void setCreditHoursLow(String creditHours) {
        this.creditHours = creditHours;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getClassSize() {
        return classSize;
    }

    public void setClassSize(int classSize) {
        this.classSize = classSize;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<ClassEntity> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassEntity> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "CourseSection [courseSectionId=" + courseSectionId + ", crn=" + crn + ", sec=" + sec + ", stat="
                + stat + ", creditHoursLow=" + creditHoursLow + ", creditHoursHigh=" + creditHoursHigh + ", instructor="
                + instructor + ", term=" + term + ", classSize=" + classSize + ", seatsAvailable=" + seatsAvailable
                + ", year=" + year + "]";
    }


}


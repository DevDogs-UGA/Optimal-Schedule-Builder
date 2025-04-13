package edu.uga.devdogs.course_information.CourseSection;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import edu.uga.devdogs.course_information.Class.ClassEntity;
import edu.uga.devdogs.course_information.Course.Course;
import edu.uga.devdogs.course_information.webscraping.Course2;
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
import jakarta.persistence.OneToOne;





@Entity
public class CourseSection implements Serializable {
    // Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_section_id")
    private Long courseSectionId;

    @Column(nullable = false, unique = true)
    private int crn;

    private String sec;

    private char stat;

    @Column(nullable = false) 
    private int creditHours = 3;


    private String term;

    private int classSize;

    private int seatsAvailable;

    private int year;

    private String daysOfTheWeek;

    private LocalTime startTime;

    
    private LocalTime endTime;

    // instructor is the name
    private String instructor;

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getInstructor() {
        return instructor;
    }


    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    

    public String getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

    public void setDaysOfTheWeek(String daysOfTheWeek) {
        this.daysOfTheWeek = daysOfTheWeek;
    }

    // Relationships
    @JsonBackReference("course-sections")
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    @OneToMany(mappedBy = "courseSection", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("coursesection-classes")
    private List<ClassEntity> classes;



    
    public List<ClassEntity> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassEntity> classes) {
        this.classes = classes;
    }




    // Constructors, getters, setters, and toString 
    public CourseSection() {}

    public CourseSection(int crn, String sec, char stat, int creditHours, String instructor,
        String term, int classSize, int seatsAvailable, int year, Course course, List<ClassEntity> classes, String daysOfTheWeek, LocalTime startTime, LocalTime endTime) {
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
    this.daysOfTheWeek = daysOfTheWeek;
    this.startTime = startTime;
    this.endTime = endTime;
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

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
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

    public Long getCourseSectionId() {
        return courseSectionId;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    @Override
    public String toString() {
        return "CourseSection [courseSectionId=" + courseSectionId + ", crn=" + crn + ", sec=" + sec + ", stat="
                + stat + ", creditHoursLow=" + creditHours + ", instructor="
                + instructor + ", term=" + term + ", classSize=" + classSize + ", seatsAvailable=" + seatsAvailable
                + ", year=" + year + "]";
    }

    public void updateFrom(Course2 course, LocalTime start, LocalTime end) {
        this.instructor = course.getProfessor();
        this.sec = course.getSec();
        this.startTime = start;
        this.endTime = end;
        this.daysOfTheWeek = course.getMeetingDays();
    }

    

}


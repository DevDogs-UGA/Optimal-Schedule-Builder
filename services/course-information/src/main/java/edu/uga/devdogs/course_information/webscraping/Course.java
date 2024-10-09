package edu.uga.devdogs.course_information.webscraping;
import java.util.Date;

/**
 * @author Yuval Deshe
 * Represents an entry for a Course at UGA.
 */
public class Course {
    private String subject; // The four-letter String representing the course subject (e.g. CSCI).
    private String courseNumber; // The course number (e.g. 1302).
    private String professor; // The professor who teaches the course.
    private String meetingDays; // The days of the week that the course meets.
    private String meetingTimes; // The times that the course meets.
    private String building; // The building that the class takes place in.
    private String roomNumber; // The room number of the class.
    private String campus; // The campus the class is offered at.
    private String description; // A description of the class.
    private String partOfTerm; // The part of the semester that the course is offered in.
    private Date startDate; // The date that the course starts.
    private Date endDate; // The date that the course ends.
    private Course[] prereqs; // Prerequisite courses for the class.
    private Course[] coreqs; // Corequisite courses for the class.
    private int creditHours; // The amount of credit hours the course fulfills.
    private int classSize; // The total number of seats in the class.
    private int availableSeats; // The number of available seats in the class.
    private int crn; // The Course Reference Number (CRN) of the course.

    /**
     * Constructs a {@code Course} object.
     */
    public Course() {
        subject = "";
        courseNumber = "";
        professor = "";
        meetingDays = "";
        meetingTimes = "";
        building = "";
        roomNumber = "";
        campus = "";
        description = "";
        partOfTerm = "";
        startDate = new Date();
        endDate = new Date();
        prereqs = new Course[0];
        coreqs = new Course[0];
        creditHours = 0;
        classSize = 0;
        availableSeats = 0;
        crn = 0;
    } // Course

    /**
     * Constructs a {@code Course} object with all variables specified as parameters.
     * @param subject the four-letter String representing the course subjectr (e.g. CSCI).
     * @param courseNumber the course number (e.g. 1302)
     * @param professor the professor who teaches the course
     * @param meetingDays the days of the week that the class meets. Days should be formatted as 
     * one letter separated by spaces (e.g. M W F or T R).
     * @param meetingTimes the times that the course meets. Times should be formatted as 
     * XX:XX am/pm-XX:XX am/pm (e.g. '11:10 am-12:25 pm' or '03:00 pm-03:50 pm').
     * @param building the building that the class takes place in.
     * @param roomNumber the room number of the class.
     * @param campus the campus the class is offered at.
     * @param description a description of the class.
     * @param partOfTerm the part of the semester that the class is offered.
     * @param startDate the date the class starts.
     * @param endDate the date the class ends.
     * @param prereqs the pre-requisite classes of the course.
     * @param coreqs the co-requisite classes of the course.
     * @param creditHours the number of credit hours the course fulfills.
     * @param classSize the total number of seats in the course.
     * @param availableSeats the amount of available seats in the course.
     * @param crn the Course Reference Number (CRN) of the course.
     */
    public Course(String subject, String courseNumber, String professor, String meetingDays, 
                  String meetingTimes, String building, String roomNumber, String campus, 
                  String description, String partOfTerm, Date startDate, Date endDate, 
                  Course[] prereqs, Course[] coreqs, int creditHours, int classSize, 
                  int availableSeats, int crn) {
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.professor = professor;
        this.meetingDays = meetingDays;
        this.meetingTimes = meetingTimes;
        this.building = building;
        this.roomNumber = roomNumber;
        this.campus = campus;
        this.description = description;
        this.partOfTerm = partOfTerm;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prereqs = prereqs;
        this.coreqs = coreqs;
        this.creditHours = creditHours;
        this.classSize = classSize;
        this.availableSeats = availableSeats;
        this.crn = crn;
    } // Course

    /**
     * Returns the course's subject.
     * @return the value of {@code subject}.
     */
    public String getSubject() {
        return this.subject;
    } // getSubject

    /**
     * Sets the course's subject.
     * @param subject the new value of {@code subject}.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    } // setSubject

    /**
     * Returns the course number.
     * @return the value of {@code courseNumber}.
     */
    public String getCourseNumber() {
        return this.courseNumber;
    } // getCourseNumber

    /**
     * Sets the course number.
     * @param courseNumber the new value of {@code courseNumber}.
     */
    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    } // setCourseNumber

    /**
     * Returns the professor that teaches the course.
     * @return the value of {@code professor}.
     */
    public String getProfessor() {
        return this.professor;
    } // getProfessor

    /**
     * Sets the professor that teaches the course.
     * @param professor the new value of {@code professor}.
     */
    public void setProfessor(String professor) {
        this.professor = professor;
    } // setProfessor

    /**
     * Returns the days that the class meets. Days should be one letter, separated by
     * spaces (e.g. M W F or T R).
     * @return the value of {@code meetingDays}.
     */
    public String getMeetingDays() {
        return this.meetingDays;
    } // getMeetingDays

    /**
     * Sets the days that the class meets. Days should be one letter, separated by spaces
     * (e.g. M W F or T R).
     * @param meetingDays the new value of {@code meetingDays}.
     */
    public void setMeetingDays(String meetingDays) {
        this.meetingDays = meetingDays;
    } // setMeetingDays

    /**
     * Returns the times that the class meets. Times should be formatted as XX:XX am/pm-XX:XX am/pm
     * (e.g. '11:10 am-12:25 pm' or '08:00 am-09:15 am' or '03:00 pm-03:50 pm').
     * @return the value of {@code meetingTimes}.
     */
    public String getMeetingTimes() {
        return this.meetingTimes;
    } // getMeetingTimes

    /**
     * Sets the times that the class meets. Times should be formatted as XX:XX am/pm-XX:XX am/pm
     * (e.g. '11:10 am-12:25 pm' or '08:00 am-09:15 am' or '03:00 pm-03:50 pm').
     * @param meetingTimes the new value of {@code meetingTimes}.
     */
    public void setMeetingTimes(String meetingTimes) {
        this.meetingTimes = meetingTimes;
    } // setMeetingTimes

    /**
     * Returns the building that the course meets in.
     * @return the value of {@code building}.
     */
    public String getBuilding() {
        return this.building;
    } // getBuilding

    /**
     * Sets the building that the course meets in.
     * @param building the new value of {@code building}.
     */
    public void setBuilding(String building) {
        this.building = building;
    } // setBuilding

    /**
     * Returns the room number that the course meets in.
     * @return the value of {@code roomNumber}.
     */
    public String getRoomNumber() {
        return this.roomNumber;
    } // getRoomNumber

    /**
     * Sets the room number that the course meets in.
     * @param roomNumber the new value of {@code roomNumber}.
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    } // setRoomNumber

    /**
     * Returns the campus that the course is offered at.
     * @return the value of {@code campus}.
     */
    public String getCampus() {
        return this.campus;
    } // getCampus

    /**
     * Sets the campus that the course is offered at.
     * @param campus the new value of {@code campus}.
     */
    public void setCampus(String campus) {
        this.campus = campus;
    } // setCampus

    /**
     * Returns the description of the course.
     * @return the value of {@code description}.
     */
    public String getDescription() {
        return this.description;
    } // getDescription

    /**
     * Sets the description of the course.
     * @param description the new value of {@code description}.
     */
    public void setDescription(String description) {
        this.description = description;
    } // setDescription

    /**
     * Returns the part of the semester that the course is held in.
     * @return the value of {@code partOfTerm}.
     */
    public String getPartOfTerm() {
        return this.partOfTerm;
    } // getPartOfTerm

    /**
     * Sets the part of the semester that the course is held in.
     * @param partOfTerm the new value of {@code partOfTerm}.
     */
    public void setPartOfTerm(String partOfTerm) {
        this.partOfTerm = partOfTerm;
    } // setPartOfTerm

    /**
     * Returns the date that the class starts on.
     * @return the value of {@code startDate}.
     */
    public Date getStartDate() {
        return this.startDate;
    } // getStartDate

    /**
     * Sets the date that the class starts on.
     * @param startDate the new value of {@code startDate}.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    } // setStartDate

    /**
     * Returns the date that the class ends on.
     * @return the value of {@code endDate}.
     */
    public Date getEndDate() {
        return this.endDate;
    } // getEndDate

    /**
     * Sets the date that the class ends on.
     * @param endDate the new value of {@code endDate}.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    } // setEndDate

    /**
     * Returns the pre-requisite classes for this course.
     * @return the value of {@code prereqs}.
     */
    public Course[] getPrereqs() {
        return this.prereqs;
    } // getPrereqs

    /**
     * Sets the pre-requisite classes for this course.
     * @param prereqs the new value of {@code prereqs}.
     */
    public void setPrereqs(Course[] prereqs) {
        this.prereqs = prereqs;
    } // setPrereqs

    /**
     * Returns the co-requisite classes for this course.
     * @return the value of {@code coreqs}.
     */
    public Course[] getCoreqs() {
        return this.coreqs;
    } // getCoreqs

    /**
     * Sets the co-requisite classes for this course.
     * @param coreqs the new value of {@code coreqs}.
     */
    public void setCoreqs(Course[] coreqs) {
        this.coreqs = coreqs;
    } // setCoreqs

    /**
     * Returns the number of credit hours that this course fulfills.
     * @return the value of {@code creditHours}.
     */
    public int getCreditHours() {
        return this.creditHours;
    } // getCreditHours

    /**
     * Sets the number of credit hours that this course fulfills.
     * @param creditHours the new value of {@code creditHours}.
     */
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    } // setCreditHours

    /**
     * Returns the total number of seats in the class.
     * @return the value of {@code classSize}.
     */
    public int getClassSize() {
        return this.classSize;
    } // getClassSize

    /**
     * Sets the total number of seats in the class.
     * @param classSize the new value of {@code classSize}.
     */
    public void setClassSize(int classSize) {
        this.classSize = classSize;
    } // setClassSize

    /**
     * Returns the amount of available seats in the class.
     * @return the value of {@code availableSeats}.
     */
    public int getAvailableSeats() {
        return this.availableSeats;
    } // getAvailableSeats

    /**
     * Sets the amount of available seats in the class.
     * @param availableSeats the new value of {@code availableSeats}.
     */
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    } // setAvailableSeats

    /**
     * Returns the Course Reference Number (CRN) of the course.
     * @return the value of {@code crn}.
     */
    public int getCrn() {
        return this.crn;
    } // getCrn

    /**
     * Sets the Course Reference Number (CRN) of the course.
     * @param crn the new value of {@code crn}.
     */
    public void setCrn(int crn) {
        this.crn = crn;
    } // setCrn
} // Course
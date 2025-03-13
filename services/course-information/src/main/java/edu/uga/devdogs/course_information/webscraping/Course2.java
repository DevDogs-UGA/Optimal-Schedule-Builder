package edu.uga.devdogs.course_information.webscraping;

/**
 * @author Yuval Deshe
 * Represents an entry for a Course at UGA.
 */
public class Course2 {
    private String subject; // The four-letter String representing the course subject (e.g. CSCI).
    private String courseNumber; // The course number (e.g. 1302).
    private String title; //ie intro to software development
    private String department; //ie School of Computing

    private int crn; // The Course Reference Number (CRN) of the course.
    private String sec; //section : denotes different sections of a class like B1 and B2. is usually null if the class is not available for the semester
    private String stat; //status of the course like avalable or not 
    private String creditHours; // The amount of credit hours the course fulfills.
    private String meetingDays; // The days of the week that the course meets.
    private String meetingTimes; // The times that the course meets.
    private String building; // The building that the class takes place in.
    private String roomNumber; // The room number of the class.
    private String campus; // The campus the class is offered at.
    private String professor; // The professor who teaches the course.
    private String partOfTerm; // The part of the semester that the course is offered in.
    private int classSize; // The total number of seats in the class.
    private int availableSeats; // The number of available seats in the class.

    /**
     * Constructs a {@code Course} object.
     */
    public Course2() {
        this("","","","",-1,"","","","","","","","","","",-1, -1);
    } // Course

    /**
     * Constructs a {@code Course} object with all variables specified as parameters.
     * @param subject the four-letter String representing the course subjectr (e.g. CSCI).
     * @param courseNumber the course number (e.g. 1302)
     * @param title title of the course eg intro to software development
     * @param department school of computing
     * @param crn the Course Reference Number (CRN) of the course.
     * @param sec specfific section of the class
     * @param stat status of the course
     * @param creditHours the number of credit hours the course fulfills.
     * @param meetingDays the days of the week that the class meets.(e.g. M W F or T R).
     * @param meetingTimes the times that the course meets. Times should be formatted as 
     * XX:XX am/pm-XX:XX am/pm (e.g. '11:10 am-12:25 pm' or '03:00 pm-03:50 pm').
     * @param building the building that the class takes place in.
     * @param roomNumber the room number of the class.
     * @param campus the campus the class is offered at.
     * @param professor the professor who teaches the course
     * @param description a description of the class.
     * @param partOfTerm the part of the semester that the class is offered.
     * @param classSize the total number of seats in the course.
     * @param availableSeats the amount of available seats in the course.
     */
    public Course2(String subject,
                  String courseNumber,
                  String title,
                  String department,
                  int crn,
                  String sec,
                  String stat,
                  String creditHours,
                  String meetingDays,
                  String meetingTimes,
                  String building,
                  String roomNumber,
                  String campus,
                  String professor,
                  String partOfTerm,
                  int classSize,
                  int availableSeats
                  ) {
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.title = title;
        this.department = department;
        this.crn = crn;
        this.sec = sec;
        this.stat = stat;
        this.creditHours = creditHours;
        this.meetingDays = meetingDays;
        this.meetingTimes = meetingTimes;
        this.building = building;
        this.roomNumber = roomNumber;
        this.campus = campus;
        this.professor = professor;
        this.partOfTerm = partOfTerm;
        this.classSize = classSize;
        this.availableSeats = availableSeats;
    }

    //getters and setters for course
    public String getTitle()  {
        return this.title;
       }
    public void setTitle(String s) {
        this.title = s;
    }
    
    public String getDepartment()  {
        return this.department;
    }
    public void setDepartment(String s) {
        this.department = s;
    }
    public String getSec() {
        return this.sec;
    }
    public void setSec(String s) {
        this.sec = s;
    }
    public String getStat() {
        return this.stat;
    }
    public void setStat(String s) {
        this.stat = s;
    }


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
     * Returns the number of credit hours that this course fulfills.
     * @return the value of {@code creditHours}.
     */
    public String getCreditHours() {
        return this.creditHours;
    } // getCreditHours

    /**
     * Sets the number of credit hours that this course fulfills.
     * @param creditHours the new value of {@code creditHours}.
     */
    public void setCreditHours(String creditHours) {
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
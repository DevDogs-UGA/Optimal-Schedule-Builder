/**
 * Class stores information about the course class, which includes professor name, the title, and semester.
 */
public class ClassInfo {
    private String instructorName;
    private String courseTitle;
    private String semesterAndYear;

    /**
     * The data needed to hold class information
     * @param instructorName the professor name
     * @param courseTitle the title of the course
     * @param semesterAndYear the year and semester of when the course was held
     */
    public ClassInfo(String instructorName, String courseTitle, String semesterAndYear) {
        this.instructorName = instructorName;
        this.courseTitle = courseTitle;
        this.semesterAndYear = semesterAndYear;
    }

    /**
     * @return professor name
     */
    public String getInstructorName() {
        return instructorName;
    }

    /**
     * Sets the professor name
     * @param instructorName professor name
     */
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    /**
     * @return course title
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     * Sets the course title
     * @param courseTitle the course title
     */
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    /**
     * @return the semester and year
     */
    public String getSemesterAndYear() {
        return semesterAndYear;
    }

    /**
     * Sets the semester and year of the class held
     * @param semesterAndYear the semester and year
     */
    public void setSemesterAndYear(String semesterAndYear) {
        this.semesterAndYear = semesterAndYear;
    }
}

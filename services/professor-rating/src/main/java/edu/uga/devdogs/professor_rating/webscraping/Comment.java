package edu.uga.devdogs.professor_rating.webscraping;

import java.util.Date;

/**
 * This class represents each of the comments or reviews left on a professor page
 * on "ratemyprofessors.com".
 * It contains information such as when the comment was created, the rating of the professor, and
 * the actual review itself.
 *
 * @author Casey Lee
 */
public class Comment {
    private double helpfulRating;
    private double difficultyRating;
    private String gradeReceivedForClass;

    private String commentBody;
    private String className;
    private String professorName;

    private boolean attendanceMandatory;
    private boolean textbookRequired;

    private Date dateCreated;

    /**
     *
     * @param helpfulRating how helpful the teacher was out of 5.0
     * @param difficultyRating how hard or difficult the teacher was out of 5.0
     * @param gradeReceivedForClass the letter grade received for the class
     * @param commentBody the review the student left
     * @param className the class name the student took
     * @param professorName which professor the student took
     * @param attendanceMandatory determines if attendance is required for the class
     * @param textbookRequired determines if a textbook is required for the class
     * @param dateCreated when the comment was created
     */
    public Comment(double helpfulRating, double difficultyRating, String gradeReceivedForClass, String commentBody, String className, String professorName, boolean attendanceMandatory, boolean textbookRequired, Date dateCreated) {
        this.helpfulRating = helpfulRating;
        this.difficultyRating = difficultyRating;
        this.gradeReceivedForClass = gradeReceivedForClass;
        this.commentBody = commentBody;
        this.className = className;
        this.professorName = professorName;
        this.attendanceMandatory = attendanceMandatory;
        this.textbookRequired = textbookRequired;
        this.dateCreated = dateCreated;
    }

    /**
     * @return a double for the rating of the teacher
     */
    public double getHelpfulRating() {
        return helpfulRating;
    }

    /**
     * Sets the rating on how helpful the teacher was (out of 5.0)
     * @param helpfulRating the helpful rating of the teacher
     */
    public void setHelpfulRating(double helpfulRating) {
        this.helpfulRating = helpfulRating;
    }

    /**
     * @return the difficulty of the teacher
     */
    public double getDifficultyRating() {
        return difficultyRating;
    }

    /**
     * Sets the rating on how difficult the teacher was (out of 5.0)
     * @param difficultyRating the difficulty rating of the teacher
     */
    public void setDifficultyRating(double difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    /**
     * @return the letter grade received for the class
     */
    public String getGradeReceivedForClass() {
        return gradeReceivedForClass;
    }

    /**
     * Sets the letter grade received for the class
     * @param gradeReceivedForClass the letter grade received for the class
     */
    public void setGradeReceivedForClass(String gradeReceivedForClass) {
        this.gradeReceivedForClass = gradeReceivedForClass;
    }

    /**
     * @return the review of the teacher
     */
    public String getCommentBody() {
        return commentBody;
    }

    /**
     * Sets the review of the teacher
     * @param commentBody the review of the teacher
     */
    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    /**
     * @return the class name for the comment
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the class name for the comment
     * @param className the class name for the comment
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the professor's name for the comment
     */
    public String getProfessorName() {
        return professorName;
    }

    /**
     * Sets the professor's name for the comment
     * @param professorName the professor's name for the comment
     */
    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    /**
     * @return a bool whether attendance is required for the class
     */
    public boolean isAttendanceMandatory() {
        return attendanceMandatory;
    }

    /**
     * Sets whether attendance is required for the class
     * @param attendanceMandatory bool whether attendance is required for the class
     */
    public void setAttendanceMandatory(boolean attendanceMandatory) {
        this.attendanceMandatory = attendanceMandatory;
    }

    /**
     * @return a bool whether a textbook is required for the class
     */
    public boolean isTextbookRequired() {
        return textbookRequired;
    }

    /**
     * Sets whether a textbook is required for the class
     * @param textbookRequired bool whether a textbook is required for the class
     */
    public void setTextbookRequired(boolean textbookRequired) {
        this.textbookRequired = textbookRequired;
    }

    /**
     * @return the date when the comment was created
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the date of when the comment was created
     * @param dateCreated the date when the comment was created
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}

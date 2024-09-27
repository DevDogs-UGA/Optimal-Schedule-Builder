import java.util.Date;

public class Comment {
    private double helpfulRating;
    private double difficultyRating;
    private double gradeReceivedForClass;

    private String commentBody;
    private String className;
    private String professorName;

    private boolean attendanceMandatory;
    private boolean textbookRequired;

    private Date dateCreated;

    public Comment(double helpfulRating, double difficultyRating, double gradeReceivedForClass, String commentBody, String className, String professorName, boolean attendanceMandatory, boolean textbookRequired, Date dateCreated) {
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

    public double getHelpfulRating() {
        return helpfulRating;
    }

    public void setHelpfulRating(double helpfulRating) {
        this.helpfulRating = helpfulRating;
    }

    public double getDifficultyRating() {
        return difficultyRating;
    }

    public void setDifficultyRating(double difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    public double getGradeReceivedForClass() {
        return gradeReceivedForClass;
    }

    public void setGradeReceivedForClass(double gradeReceivedForClass) {
        this.gradeReceivedForClass = gradeReceivedForClass;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public boolean isAttendanceMandatory() {
        return attendanceMandatory;
    }

    public void setAttendanceMandatory(boolean attendanceMandatory) {
        this.attendanceMandatory = attendanceMandatory;
    }

    public boolean isTextbookRequired() {
        return textbookRequired;
    }

    public void setTextbookRequired(boolean textbookRequired) {
        this.textbookRequired = textbookRequired;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}

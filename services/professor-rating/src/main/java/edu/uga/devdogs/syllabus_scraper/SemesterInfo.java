public class SemesterInfo {
    private String semesterCode;
    private String semesterName;

    public SemesterInfo(String semesterCode, String semesterName) {
        this.semesterCode = semesterCode;
        this.semesterName = semesterName;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }
}


/**
 * Class holds information about each of the school's department and its following code.
 * The code is to identify which option to select, and the name is the name of the department.
 * Right now, everything requires a department name, but maybe we can add a static map that holds
 * the corresponding class course code (ex. CSCI) with its prefix (CS)
 */
public class DepartmentInfo {
    private String departmentCode;
    private String departmentName;

    /**
     * The data needed to hold the department information
     * @param departmentCode code to identify which option to select on the UGA syllabus site
     * @param departmentName name of the department
     */
    public DepartmentInfo(String departmentCode, String departmentName) {
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
    }

    /**
     * @return the department code
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * Sets the department code
     * @param departmentCode the department code
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    /**
     * @return the department name
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Sets the department name
     * @param departmentName the department name
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}

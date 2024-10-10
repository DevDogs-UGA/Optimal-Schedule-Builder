import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that allows users to download syllabus depending on multiple parameters.
 */
public class CourseScraper {
    /**
     * Scrapes the html of a aspx request in order retrieve hidden values required for post requests
     * @param html html of aspx request
     * @return an AspNetData object that holds hidden aspx values
     */
    private static AspNetData getAspHiddenData(String html) {
        Matcher matcher = Pattern
                .compile("id=\"__VIEWSTATE\" value=\"(.*?)\".*?id=\"__VIEWSTATEGENERATOR\" value=\"(.*?)\".*?id=\"__EVENTVALIDATION\" value=\"(.*?)\"", Pattern.DOTALL)
                .matcher(html);
        if (!matcher.find()) return null;

        String viewState = matcher.group(1);
        String viewStateGenerator = matcher.group(2);
        String eventValidation = matcher.group(3);

        return new AspNetData(viewState, viewStateGenerator, eventValidation);
    }

    /**
     * Downloads every single syllabus from a professor (includes any year as well)
     * @param department the department of the major (Chemistry, school of computing, etc)
     * @param instructorName the professor name
     * @param path the path to where the file should be downloaded (local disk)
     */
    public static void downloadAllSyllabusByInstructor( String department,
                                                        String instructorName,
                                                        String path) {
        try {
            String url = "https://syllabus.uga.edu/Browse.aspx";
            Connection.Response response = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .execute();

            AspNetData data = getAspHiddenData(response.body());
            if (data == null) return;

            response = Jsoup.connect(url)
                    .data("__VIEWSTATE", data.getViewState())
                    .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                    .data("__VIEWSTATEENCRYPTED", "")
                    .data("__EVENTVALIDATION", data.getEventValidation())
                    .data("RadioButtonList1", "DEP")
                    .data("Button1", "Submit")
                    .method(Connection.Method.POST)
                    .cookies(response.cookies())
                    .followRedirects(true)
                    .execute();

            data = getAspHiddenData(response.body());
            if (data == null) return;


            DepartmentInfo selectedDep = null;
            Document doc = response.parse();
            Elements elements = doc.select("#ddlDept > option");
            for (int i = 1; i < elements.size(); i++) {
                Element element = elements.get(i);
                String departmentCode = element.attr("value");
                String departmentName = element.text();

                if (departmentName.equalsIgnoreCase(department)) {
                    selectedDep = new DepartmentInfo(departmentCode, departmentName);
                    break;
                }
            }

            if (selectedDep == null) return;

            response = Jsoup.connect(url)
                    .data("__EVENTTARGET", "ddlDept")
                    .data("__EVENTARGUMENT", "")
                    .data("__LASTFOCUS", "")
                    .data("__VIEWSTATE", data.getViewState())
                    .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                    .data("__VIEWSTATEENCRYPTED", "")
                    .data("__EVENTVALIDATION", data.getEventValidation())
                    .data("RadioButtonList1", "DEP")
                    .data("ddlDept", selectedDep.getDepartmentCode())
                    .method(Connection.Method.POST)
                    .cookies(response.cookies())
                    .followRedirects(true)
                    .execute();

            data = getAspHiddenData(response.body());
            if (data == null) return;

            //Gets the latest semester
            doc = response.parse();
            elements = doc.select("#ddlSemesters > option");
            Element element = elements.getLast();

            String semesterCode = element.attr("value");
            String semesterName = element.text();
            SemesterInfo selectedSemester = new SemesterInfo(semesterCode, semesterName);
            String defaultSemesterCode = selectedSemester.getSemesterCode();

            response = Jsoup.connect(url)
                    .data("__EVENTTARGET", "ddlSemesters")
                    .data("__EVENTARGUMENT", "")
                    .data("__LASTFOCUS", "")
                    .data("__VIEWSTATE", data.getViewState())
                    .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                    .data("__VIEWSTATEENCRYPTED", "")
                    .data("__EVENTVALIDATION", data.getEventValidation())
                    .data("RadioButtonList1", "DEP")
                    .data("ddlDept", selectedDep.getDepartmentCode())
                    .data("ddlSemesters", defaultSemesterCode)
                    .method(Connection.Method.POST)
                    .cookies(response.cookies())
                    .followRedirects(true)
                    .execute();

            data = getAspHiddenData(response.body());
            if (data == null) return;

            response = Jsoup.connect(url)
                    .data("__EVENTTARGET", "")
                    .data("__EVENTARGUMENT", "")
                    .data("__LASTFOCUS", "")
                    .data("__VIEWSTATE", data.getViewState())
                    .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                    .data("__VIEWSTATEENCRYPTED", "")
                    .data("__EVENTVALIDATION", data.getEventValidation())
                    .data("RadioButtonList1", "DEP")
                    .data("ddlDept", selectedDep.getDepartmentCode())
                    .data("ddlSemesters", defaultSemesterCode)
                    .data("Button_ViewAll", "View+information+for+all+courses+with+Syllabus+files")
                    .data("ddlCourse", "-1")
                    .method(Connection.Method.POST)
                    .cookies(response.cookies())
                    .followRedirects(true)
                    .execute();

            data = getAspHiddenData(response.body());
            if (data == null) return;

            String html = response.body();
            List<Integer> downloadInds = new ArrayList<>();
            doc = response.parse();
            Elements trTags = doc.select("#gridFileList > tbody > tr");
            for (int i = 1; i < trTags.size(); i++) {
                Element trTag = trTags.get(i);
                Elements tdTags = trTag.select("td");

                ClassInfo info = new ClassInfo(
                        tdTags.get(0).text(),
                        tdTags.get(1).text(),
                        tdTags.get(2).text());
                if (info.getInstructorName().equalsIgnoreCase(instructorName)) {
                    downloadInds.add(i - 1);
                }
            }

            for (Integer ind : downloadInds)
            {
                response = Jsoup.connect(url)
                        .data("__EVENTTARGET", "gridFileList")
                        .data("__EVENTARGUMENT", String.format("Select$%d", ind))
                        .data("__LASTFOCUS", "")
                        .data("__VIEWSTATE", data.getViewState())
                        .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                        .data("__VIEWSTATEENCRYPTED", "")
                        .data("__EVENTVALIDATION", data.getEventValidation())
                        .data("RadioButtonList1", "DEP")
                        .data("ddlDept", selectedDep.getDepartmentCode())
                        .data("ddlSemesters", defaultSemesterCode)
                        .data("ddlCourse", "-1")
                        .method(Connection.Method.POST)
                        .cookies(response.cookies())
                        .followRedirects(true)
                        .ignoreContentType(true)
                        .execute();

                String contentDisposition = response.header("Content-disposition");
                if (contentDisposition == null || contentDisposition.isEmpty()) continue;

                Matcher matcher = Pattern
                        .compile("filename=\"(.*?)\"")
                        .matcher(contentDisposition);
                if (!matcher.find()) continue;

                String fileName = matcher.group(1);

                // output here
                FileOutputStream  out = new FileOutputStream(new java.io.File(String.format("%s/%s", path, fileName)));
                out.write(response.bodyAsBytes());         
                out.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Downloads every single syllabus from a professor and specific course title (includes any year as well)
     * @param department the department of the major (Chemistry, school of computing, etc)
     * @param courseTitle the title of the course
     * @param instructorName the professor name
     * @param path the path to where the file should be downloaded (local disk)
     */
    public static void downloadAllSyllabusByInstructorAndCourse( String department,
                                                                 String courseTitle,
                                                                 String instructorName,
                                                                 String path) {
        try {
            String url = "https://syllabus.uga.edu/Browse.aspx";
            Connection.Response response = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .execute();

            AspNetData data = getAspHiddenData(response.body());
            if (data == null) return;

            response = Jsoup.connect(url)
                    .data("__VIEWSTATE", data.getViewState())
                    .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                    .data("__VIEWSTATEENCRYPTED", "")
                    .data("__EVENTVALIDATION", data.getEventValidation())
                    .data("RadioButtonList1", "DEP")
                    .data("Button1", "Submit")
                    .method(Connection.Method.POST)
                    .cookies(response.cookies())
                    .followRedirects(true)
                    .execute();

            data = getAspHiddenData(response.body());
            if (data == null) return;


            DepartmentInfo selectedDep = null;
            Document doc = response.parse();
            Elements elements = doc.select("#ddlDept > option");
            for (int i = 1; i < elements.size(); i++) {
                Element element = elements.get(i);
                String departmentCode = element.attr("value");
                String departmentName = element.text();

                if (departmentName.equalsIgnoreCase(department)) {
                    selectedDep = new DepartmentInfo(departmentCode, departmentName);
                    break;
                }
            }

            if (selectedDep == null) return;

            response = Jsoup.connect(url)
                    .data("__EVENTTARGET", "ddlDept")
                    .data("__EVENTARGUMENT", "")
                    .data("__LASTFOCUS", "")
                    .data("__VIEWSTATE", data.getViewState())
                    .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                    .data("__VIEWSTATEENCRYPTED", "")
                    .data("__EVENTVALIDATION", data.getEventValidation())
                    .data("RadioButtonList1", "DEP")
                    .data("ddlDept", selectedDep.getDepartmentCode())
                    .method(Connection.Method.POST)
                    .cookies(response.cookies())
                    .followRedirects(true)
                    .execute();

            data = getAspHiddenData(response.body());
            if (data == null) return;

            //Gets the latest semester
            doc = response.parse();
            elements = doc.select("#ddlSemesters > option");
            Element element = elements.getLast();

            String semesterCode = element.attr("value");
            String semesterName = element.text();
            SemesterInfo selectedSemester = new SemesterInfo(semesterCode, semesterName);
            String defaultSemesterCode = selectedSemester.getSemesterCode();

            response = Jsoup.connect(url)
                    .data("__EVENTTARGET", "ddlSemesters")
                    .data("__EVENTARGUMENT", "")
                    .data("__LASTFOCUS", "")
                    .data("__VIEWSTATE", data.getViewState())
                    .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                    .data("__VIEWSTATEENCRYPTED", "")
                    .data("__EVENTVALIDATION", data.getEventValidation())
                    .data("RadioButtonList1", "DEP")
                    .data("ddlDept", selectedDep.getDepartmentCode())
                    .data("ddlSemesters", defaultSemesterCode)
                    .method(Connection.Method.POST)
                    .cookies(response.cookies())
                    .followRedirects(true)
                    .execute();

            data = getAspHiddenData(response.body());
            if (data == null) return;

            response = Jsoup.connect(url)
                    .data("__EVENTTARGET", "")
                    .data("__EVENTARGUMENT", "")
                    .data("__LASTFOCUS", "")
                    .data("__VIEWSTATE", data.getViewState())
                    .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                    .data("__VIEWSTATEENCRYPTED", "")
                    .data("__EVENTVALIDATION", data.getEventValidation())
                    .data("RadioButtonList1", "DEP")
                    .data("ddlDept", selectedDep.getDepartmentCode())
                    .data("ddlSemesters", defaultSemesterCode)
                    .data("Button_ViewAll", "View+information+for+all+courses+with+Syllabus+files")
                    .data("ddlCourse", "-1")
                    .method(Connection.Method.POST)
                    .cookies(response.cookies())
                    .followRedirects(true)
                    .execute();

            data = getAspHiddenData(response.body());
            if (data == null) return;

            String html = response.body();
            List<Integer> downloadInds = new ArrayList<>();
            doc = response.parse();
            Elements trTags = doc.select("#gridFileList > tbody > tr");
            for (int i = 1; i < trTags.size(); i++) {
                Element trTag = trTags.get(i);
                Elements tdTags = trTag.select("td");

                ClassInfo info = new ClassInfo(
                        tdTags.get(0).text(),
                        tdTags.get(1).text(),
                        tdTags.get(2).text());
                if (info.getCourseTitle().equalsIgnoreCase(courseTitle) &&
                        info.getInstructorName().equalsIgnoreCase(instructorName)) {
                    downloadInds.add(i - 1);
                }
            }

            for (Integer ind : downloadInds)
            {
                response = Jsoup.connect(url)
                        .data("__EVENTTARGET", "gridFileList")
                        .data("__EVENTARGUMENT", String.format("Select$%d", ind))
                        .data("__LASTFOCUS", "")
                        .data("__VIEWSTATE", data.getViewState())
                        .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                        .data("__VIEWSTATEENCRYPTED", "")
                        .data("__EVENTVALIDATION", data.getEventValidation())
                        .data("RadioButtonList1", "DEP")
                        .data("ddlDept", selectedDep.getDepartmentCode())
                        .data("ddlSemesters", defaultSemesterCode)
                        .data("ddlCourse", "-1")
                        .method(Connection.Method.POST)
                        .cookies(response.cookies())
                        .followRedirects(true)
                        .ignoreContentType(true)
                        .execute();

                String contentDisposition = response.header("Content-disposition");

                if (contentDisposition == null || contentDisposition.isEmpty()) continue;
                Matcher matcher = Pattern
                        .compile("filename=\"(.*?)\"")
                        .matcher(contentDisposition);

                if (!matcher.find()) continue;


                String fileName = matcher.group(1);

                // output here
                FileOutputStream  out = new FileOutputStream(new java.io.File(String.format("%s/%s", path, fileName)));
                out.write(response.bodyAsBytes());          
                out.close();

                int i = 0;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param department the department of the major (Chemistry, school of computing, etc)
     * @param courseTitle the title of the course
     * @param path the path to where the file should be downloaded (local disk)
     */
    public static void downloadAllSyllabusByCourse(  String department,
                                                     String courseTitle,
                                                     String path) {
        try {
            String url = "https://syllabus.uga.edu/Browse.aspx";
            Connection.Response response = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .execute();

            AspNetData data = getAspHiddenData(response.body());
            if (data == null) return;

            response = Jsoup.connect(url)
                    .data("__VIEWSTATE", data.getViewState())
                    .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                    .data("__VIEWSTATEENCRYPTED", "")
                    .data("__EVENTVALIDATION", data.getEventValidation())
                    .data("RadioButtonList1", "DEP")
                    .data("Button1", "Submit")
                    .method(Connection.Method.POST)
                    .cookies(response.cookies())
                    .followRedirects(true)
                    .execute();

            data = getAspHiddenData(response.body());
            if (data == null) return;


            DepartmentInfo selectedDep = null;
            Document doc = response.parse();
            Elements elements = doc.select("#ddlDept > option");
            for (int i = 1; i < elements.size(); i++) {
                Element element = elements.get(i);
                String departmentCode = element.attr("value");
                String departmentName = element.text();

                if (departmentName.equalsIgnoreCase(department)) {
                    selectedDep = new DepartmentInfo(departmentCode, departmentName);
                    break;
                }
            }

            if (selectedDep == null) return;

            response = Jsoup.connect(url)
                    .data("__EVENTTARGET", "ddlDept")
                    .data("__EVENTARGUMENT", "")
                    .data("__LASTFOCUS", "")
                    .data("__VIEWSTATE", data.getViewState())
                    .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                    .data("__VIEWSTATEENCRYPTED", "")
                    .data("__EVENTVALIDATION", data.getEventValidation())
                    .data("RadioButtonList1", "DEP")
                    .data("ddlDept", selectedDep.getDepartmentCode())
                    .method(Connection.Method.POST)
                    .cookies(response.cookies())
                    .followRedirects(true)
                    .execute();

            data = getAspHiddenData(response.body());
            if (data == null) return;

            //Gets the latest semester
            doc = response.parse();
            elements = doc.select("#ddlSemesters > option");
            Element element = elements.getLast();

            String semesterCode = element.attr("value");
            String semesterName = element.text();
            SemesterInfo selectedSemester = new SemesterInfo(semesterCode, semesterName);
            String defaultSemesterCode = selectedSemester.getSemesterCode();

            response = Jsoup.connect(url)
                    .data("__EVENTTARGET", "ddlSemesters")
                    .data("__EVENTARGUMENT", "")
                    .data("__LASTFOCUS", "")
                    .data("__VIEWSTATE", data.getViewState())
                    .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                    .data("__VIEWSTATEENCRYPTED", "")
                    .data("__EVENTVALIDATION", data.getEventValidation())
                    .data("RadioButtonList1", "DEP")
                    .data("ddlDept", selectedDep.getDepartmentCode())
                    .data("ddlSemesters", defaultSemesterCode)
                    .method(Connection.Method.POST)
                    .cookies(response.cookies())
                    .followRedirects(true)
                    .execute();

            data = getAspHiddenData(response.body());
            if (data == null) return;

            response = Jsoup.connect(url)
                    .data("__EVENTTARGET", "")
                    .data("__EVENTARGUMENT", "")
                    .data("__LASTFOCUS", "")
                    .data("__VIEWSTATE", data.getViewState())
                    .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                    .data("__VIEWSTATEENCRYPTED", "")
                    .data("__EVENTVALIDATION", data.getEventValidation())
                    .data("RadioButtonList1", "DEP")
                    .data("ddlDept", selectedDep.getDepartmentCode())
                    .data("ddlSemesters", defaultSemesterCode)
                    .data("Button_ViewAll", "View+information+for+all+courses+with+Syllabus+files")
                    .data("ddlCourse", "-1")
                    .method(Connection.Method.POST)
                    .cookies(response.cookies())
                    .followRedirects(true)
                    .execute();

            data = getAspHiddenData(response.body());
            if (data == null) return;

            String html = response.body();
            List<Integer> downloadInds = new ArrayList<>();
            doc = response.parse();
            Elements trTags = doc.select("#gridFileList > tbody > tr");
            for (int i = 1; i < trTags.size(); i++) {
                Element trTag = trTags.get(i);
                Elements tdTags = trTag.select("td");

                ClassInfo info = new ClassInfo(
                        tdTags.get(0).text(),
                        tdTags.get(1).text(),
                        tdTags.get(2).text());
                if (info.getCourseTitle().equalsIgnoreCase(courseTitle)) {
                    downloadInds.add(i - 1);
                }
            }

            for (Integer ind : downloadInds)
            {
                response = Jsoup.connect(url)
                        .data("__EVENTTARGET", "gridFileList")
                        .data("__EVENTARGUMENT", String.format("Select$%d", ind))
                        .data("__LASTFOCUS", "")
                        .data("__VIEWSTATE", data.getViewState())
                        .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                        .data("__VIEWSTATEENCRYPTED", "")
                        .data("__EVENTVALIDATION", data.getEventValidation())
                        .data("RadioButtonList1", "DEP")
                        .data("ddlDept", selectedDep.getDepartmentCode())
                        .data("ddlSemesters", defaultSemesterCode)
                        .data("ddlCourse", "-1")
                        .method(Connection.Method.POST)
                        .cookies(response.cookies())
                        .followRedirects(true)
                        .ignoreContentType(true)
                        .execute();

                String contentDisposition = response.header("Content-disposition");
                if (contentDisposition == null || contentDisposition.isEmpty()) continue;

                Matcher matcher = Pattern
                        .compile("filename=\"(.*?)\"")
                        .matcher(contentDisposition);
                if (!matcher.find()) continue;

                String fileName = matcher.group(1);

                // output here
                FileOutputStream  out = new FileOutputStream(new java.io.File(String.format("%s/%s", path, fileName)));
                out.write(response.bodyAsBytes());          
                out.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

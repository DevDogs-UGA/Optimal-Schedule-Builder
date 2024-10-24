import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Casey Lee
 * A class that allows users to get co/prerequisite classes from the uga bulletin site.
 */
public class BulletinScraper {
    /**
     * @param html the html of the site to get hidden aspx values
     * @return AspNetData object containing the hidden values
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
     * Gets a course info given the prefix (ex. CSCI) and number (1302) of a course.
     * @param prefix course name
     * @param number course number
     * @return a Course object containing information about the course
     */
    public static Course getCourseInfo(String prefix, int number) {
        try {
            String url = "https://bulletin.uga.edu/coursesHome";
            Connection.Response response = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .execute();

            AspNetData data = getAspHiddenData(response.body());
            if (data == null) return null;

            response = Jsoup.connect(url)
                    .data("ScriptManager1", "upControls|btnSearchPrefixCourse")
                    .data("__EVENTTARGET", "btnSearchPrefixCourse")
                    .data("__EVENTARGUMENT", "")
                    .data("__LASTFOCUS", "")
                    .data("__VIEWSTATE", data.getViewState())
                    .data("__VIEWSTATEGENERATOR", data.getViewStateGenerator())
                    .data("__SCROLLPOSITIONX", "0")
                    .data("__SCROLLPOSITIONY", "0")
                    .data("__EVENTVALIDATION", data.getEventValidation())
                    .data("txtEnterPrefix", prefix)
                    .data("txtEnterNumber", String.valueOf(number))
                    .data("ddlAllPrefixes", "-1")
                    .data("__ASYNCPOST", "true")
                    .data(":", "")

                    .method(Connection.Method.POST)
                    .cookies(response.cookies())
                    .followRedirects(true)
                    .execute();

            Document doc = response.parse();
            Elements trTags = doc.select("#lblCourseResultText > table > tbody > tr");

            String creditHours = trTags.get(1).select("td").get(1).text();
            String formattedCreditHours = creditHours.split("\\.")[1];

            String courseId = trTags.get(1).select("td").get(1).selectFirst("b").text();
            String coursePrefix = courseId.split(" ")[0];
            String courseNumber = courseId.split(" ")[1];


            String courseTitle = trTags.get(2).select("td").get(1).selectFirst("b").text();
            String courseDesc = trTags.get(3).select("td").get(1).text();


            int preCoRequisiteInd = 3;
            while (preCoRequisiteInd < trTags.size() && !trTags.get(preCoRequisiteInd).select("td").get(0).text().contains("Prerequisite"))
            {
                preCoRequisiteInd++;
            }

            Course course = new Course();
            if (preCoRequisiteInd < trTags.size())
            {
                String preCoRequisites = trTags.get(preCoRequisiteInd).select("td").get(1).text();
                course.setCoPrerequisiteClasses(preCoRequisites);
            }

            course.setCreditHours(formattedCreditHours);
            course.setSubject(coursePrefix);
            course.setCourseNumber(courseNumber);
            course.setTitle(courseTitle);
            course.setDescription(courseDesc);

            return course;
        }catch(IOException e){
            return null;
        }
    }

    /**
     * Gets the co/prerequisite courses given a course prefix and number.
     * If a class has multiple, it will return them in order. No duplicates will be stored.
     * @param prefix course name
     * @param number course number
     * @return a list of Course objects containing info about the co/prerequisite courses
     */
    public static List<Course> getPrerequisiteCourses(String prefix, int number) {
        Course course = getCourseInfo(prefix, number);

        if (course == null) return null;

        if (course.getCoPrerequisiteClasses() == null) return null; //No pre or co requisite classes found
        Matcher matcher = Pattern.compile("([A-Z]+) ([0-9]+)").matcher(course.getCoPrerequisiteClasses());

        List<Course> preRequisiteCourses = new ArrayList<>();
        while (matcher.find()) {
            for (int j = 1; j < matcher.groupCount(); j++) {
                String coursePrefix = matcher.group(j);
                String courseNum = matcher.group(j + 1);

                boolean skip = false;
                for (Course preRequisiteCourse : preRequisiteCourses)
                {
                    //Skip duplicates
                    if (preRequisiteCourse.getSubject().equalsIgnoreCase(coursePrefix) &&
                            preRequisiteCourse.getCourseNumber().equalsIgnoreCase(courseNum)) {
                        skip = true;
                        break;
                    }
                }
                if (skip) continue;

                Course prerequisiteCourse = getCourseInfo(coursePrefix, Integer.parseInt(courseNum));
                if (prerequisiteCourse != null) {
                    preRequisiteCourses.add(prerequisiteCourse);
                }
            }
        }

        return preRequisiteCourses;
    }
}

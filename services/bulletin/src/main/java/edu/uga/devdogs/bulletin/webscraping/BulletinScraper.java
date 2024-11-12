package edu.uga.devdogs.bulletin.webscraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BulletinScraper {
    // This method takes a line from the table and attempts to extract a course ID from it
    private static String getCourseIDFromTable(String text) {
        // Table entry will look something like "ABCD 1234." Look at the text until we reach the period.
        return text.substring(0,text.indexOf("."));
    }

    // This method will take the line from the table that contains the prerequisites and parse it
   private static ArrayList<PrerequisiteGroup> getPrerequisiteGroupFromTable(String text, String courseName) {
       Pattern coursePrefixRegex = Pattern.compile("\\b[A-Z]{4}\\b"); // i.e. "MATH"
       Pattern courseSuffixRegex = Pattern.compile("\\b\\d{4}[A-Z]?\\b"); // i.e. "1113"

       // I found that some departments call the "permission of department" requirement "permission of school"
       String modifiedText = text.replace("school", "department") + "END";

       // The groups that we will eventually return from this method.
       ArrayList<PrerequisiteGroup> prerequisiteGroups = new ArrayList<PrerequisiteGroup>();


       /* Sometimes multiple names for a course will be on one line (i.e. "ICON(ANTH)(GEOG)(FANR)(ECOL) 8111")...
         Split the "one" course title into all of its course names
         Later, we will take all the prerequisites we found for this course and "assign" them to all
         names that a class might have. */
       ArrayList<String> courseNamePrefixes = new ArrayList<>();
       ArrayList<String> courseNameSuffixes = new ArrayList<>();
       ArrayList<String> courseNames = new ArrayList<>();
        Matcher courseNamePrefixMatcher = coursePrefixRegex.matcher(courseName);
        Matcher courseNameSuffixMatcher = courseSuffixRegex.matcher(courseName);
       while (courseNamePrefixMatcher.find()) {
           courseNamePrefixes.add(courseNamePrefixMatcher.group());
       }
       while (courseNameSuffixMatcher.find()) {
           courseNameSuffixes.add(courseNameSuffixMatcher.group());
       }
       for (String prefix : courseNamePrefixes) {
           for (String suffix : courseNameSuffixes) {
               String course = prefix + " " + suffix;
               courseNames.add(course);
           }
       }

       for (String course : courseNames) {
           // Split up the string whereever it says "or" or "and", also handle special honors and department cases
           int index = 0;
           int startIndex = 0;
           ArrayList<String> strings = new ArrayList<>();
           while (index <= modifiedText.length()) {
               String str = modifiedText.substring(startIndex, index);
               if (str.contains(" or ") || str.contains(" and ")) {
                   strings.add(str);
                   startIndex = index;
               } else if (str.contains("END")) {
                   if (str.toLowerCase().contains("permission of honors")) {
                       strings.add("HONORS");
                   } else if (str.toLowerCase().contains("permission of department")) {
                       strings.add("DEPARTMENT");
                   } else {
                       strings.add(str.replace("END",""));
                   }

               }
               index++;
           }

           // By this point, the className will have already been set (we are going down the table from the top)
           PrerequisiteGroup currentGroup = new PrerequisiteGroup(course);

           for (String s : strings) {
               Matcher letterMatcher = coursePrefixRegex.matcher(s); // find all prefixes
               Matcher numberMatcher = courseSuffixRegex.matcher(s); // find all suffixes
               ArrayList<String> prefixes = new ArrayList<>();
               ArrayList<String> suffixes = new ArrayList<>();
               ArrayList<String> prerequisiteCourseNames = new ArrayList<>();

               while (letterMatcher.find()) {
                   prefixes.add(letterMatcher.group());
               }
               while (numberMatcher.find()) {
                   suffixes.add(numberMatcher.group());
               }

               for (String prefix : prefixes) {
                   for (String suffix : suffixes) {
                       String prerequisiteCourseName = prefix + " " + suffix;
                       //System.out.println(courseName);
                       currentGroup.addCourse(prerequisiteCourseName);
                   }
               }

               // Add all requirements to meet the prerequisite into the prerequisite group
               if (s.toLowerCase().contains("honors")) {
                   currentGroup.setRequiresHonors(true);
               } else if (s.toLowerCase().contains("department")) {
                   currentGroup.setCanSubstituteDepartmentPermission(true);
               }

               // "and" signals the start of a NEW prerequisite group
               if (s.contains("and")) {
                   prerequisiteGroups.add(currentGroup);
                   currentGroup = new PrerequisiteGroup(course);
               }

           }
           prerequisiteGroups.add(currentGroup);
       }


       return prerequisiteGroups;


   }
    public static ArrayList<PrerequisiteGroup> getPrerequisitesFromBulletin(String coursePrefix) {
        String className = "";
        ArrayList<PrerequisiteGroup> prerequisiteGroups = new ArrayList<PrerequisiteGroup>();
        //WebDriverManager.chromedriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        WebDriver driver = new FirefoxDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the bulletin site, enter the desired prefix into the prefix field, and click enter.
        driver.get("https://bulletin.uga.edu/coursesHome");
        WebElement prefixEntry = driver.findElement(By.id("txtEnterPrefix"));
        prefixEntry.sendKeys(coursePrefix);

        WebElement btn = driver.findElement(By.id("Imgbut_go_offPrefixCourse"));
        btn.click();

        // Wait for the next page to load, then parse the HTML.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lblCourseResultText")));
        Document document = Jsoup.parse(driver.getPageSource());

        // Find the table with the information about each course.
        Element courseResults = document.getElementById("lblCourseResultText");
        Elements resultsTables = courseResults.getElementsByClass("courseresultstable");
        boolean isCourseID = false;
        boolean isPrerequisites = false;
        for (Element table : resultsTables) {

            // Loop through each row of the table to find the information we need
            Elements allData = table.getElementsByTag("td");
            for (Element data : allData) {
                String text = data.text();

                // The row in the table contains the course ID - print it
                if (text.contains("Course ID")) {
                    // This is the table right before the course ID -- set a flag so we know to get it on the next loop.
                    isCourseID = true;
                } else if (isCourseID) {
                    // This variable is at the top of the method; save it for later, we need it when we are making the prereq groups.
                    className = getCourseIDFromTable(text);
                    isCourseID = false;


                // The row in the table contains the prerequisites, same deal w/ flag
                } else if (text.contains("Prerequisite")) {
                    isPrerequisites = true;
                } else if (isPrerequisites) {
                    // Get the prerequisites for this course...
                    ArrayList<PrerequisiteGroup> groups = getPrerequisiteGroupFromTable(text, className);
                    // and add it to the prerequisites for all courses that we've collected so far.
                    prerequisiteGroups.addAll(groups);
                    isPrerequisites = false;

                }





            }


        }
        // Close the browser
        driver.quit();
        return prerequisiteGroups;
    }

    public static void main(String[] args) {
        ArrayList<PrerequisiteGroup> groups = getPrerequisitesFromBulletin("csci");
        for (PrerequisiteGroup group : groups) {
            System.out.println(group);
        }
    }
}

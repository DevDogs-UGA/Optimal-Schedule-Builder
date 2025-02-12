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
    /**
     * Extracts the full course ID from the row of the table that contains it.
     * @param text The text from the table that contains the course ID at the beginning of it.
     * @return The extracted course ID as a string; i.e. "FANR 1100"
     */
    private static String getCourseIDFromTable(String text) {
        // Table entry will look something like "ABCD 1234." Look at the text until we reach the period.
        return text.substring(0,text.indexOf("."));
    }

    /**
     * Extracts the requirements of a course into a list of RequirementGroup classes.
     * @param text The text from the table that contains the requirements that we are looking for.
     * @param courseName The name of the course that these requirements will be associated with.
     * @return An ArrayList of RequirementGroup objects, each representing a set of requirements for the course
     */
   private static ArrayList<RequirementGroup> getRequirementGroupsFromTable(String text, String courseName) {
       Pattern coursePrefixRegex = Pattern.compile("\\b[A-Z]{4}\\b"); // i.e. "MATH"
       Pattern courseSuffixRegex = Pattern.compile("\\b\\d{4}[A-Z]?\\b"); // i.e. "1113"

       // I found that some departments call the "permission of department" requirement "permission of school"
       String modifiedText = text.replace("school", "department") + "END";

       // The groups that we will eventually return from this method.
       ArrayList<RequirementGroup> requirementGroups = new ArrayList<RequirementGroup>();


       /* Sometimes multiple names for a course will be on one line (i.e. "ICON(ANTH)(GEOG)(FANR)(ECOL) 8111")...
         Split the "one" course title into all of its course names
         Later, we will take all the requirements we found for this course and "assign" them to all
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
                   } else if (str.toLowerCase().contains("permission of ")) {
                       strings.add("permission");
                   } else {
                       strings.add(str.replace("END",""));
                   }

               }
               index++;
           }

           // By this point, the className will have already been set (we are going down the table from the top)
           RequirementGroup currentGroup = new RequirementGroup(course);

           for (String s : strings) {
               Matcher letterMatcher = coursePrefixRegex.matcher(s); // find all prefixes
               Matcher numberMatcher = courseSuffixRegex.matcher(s); // find all suffixes
               ArrayList<String> prefixes = new ArrayList<>();
               ArrayList<String> suffixes = new ArrayList<>();

               while (letterMatcher.find()) {
                   prefixes.add(letterMatcher.group());
               }
               while (numberMatcher.find()) {
                   suffixes.add(numberMatcher.group());
               }

               for (String prefix : prefixes) {
                   for (String suffix : suffixes) {
                       String requirementCourseName = prefix + " " + suffix;
                       currentGroup.addCourse(requirementCourseName);
                   }
               }

               // Add all requirements to meet the requirement into the requirement group
               if (s.toLowerCase().contains("honors")) {
                   currentGroup.setRequiresHonors(true);
               } else if (s.toLowerCase().contains("permission")) {
                   currentGroup.setCanSubstituteDepartmentPermission(true);
               }

               // "and" signals the start of a NEW requirement group
               if (s.contains("and")) {
                   requirementGroups.add(currentGroup);
                   currentGroup = new RequirementGroup(course);
               }

           }
           requirementGroups.add(currentGroup);
       }


       return requirementGroups;


   }

    /**
     * Retrieves requirement groups for all courses with a specific course prefix from the UGA bulletin.
     * @param coursePrefix the course prefix to search for (e.g., "MATH", "CSCI")
     * @param requirement the label used in the bulletin table to categorize the desired requirements (e.g., "Prerequisites:", "Corequisites:"). This determines which requirements this method will extract from the table.
     * @return a list of RequirementGroup objects containing the extracted requirements
     */
    private static ArrayList<RequirementGroup> getRequirementsFromBulletin(String coursePrefix, String requirement) {
        String className = "";
        ArrayList<RequirementGroup> requirementGroups = new ArrayList<RequirementGroup>();
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
        boolean isRequirement = false;
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


                // The row in the table contains the requirements, same deal w/ flag
                } else if (text.equals(requirement)) {
                    isRequirement = true;
                } else if (isRequirement) {
                    // Get the requirements for this course...
                    ArrayList<RequirementGroup> groups = getRequirementGroupsFromTable(text, className);
                    // and add it to the requirements for all courses that we've collected so far.
                    requirementGroups.addAll(groups);
                    isRequirement = false;

                }
            }
        }
        // Close the browser
        driver.quit();
        return requirementGroups;
    }

    /**
     * Retrieves prerequisite requirement groups for a specific course prefix from the UGA bulletin.
     *
     * @param coursePrefix the course prefix to search for (e.g., "MATH", "CSCI")
     * @return a list of RequirementGroup objects containing the prerequisite requirements
     */
    public static ArrayList<RequirementGroup> getPrerequisitesFromBulletin(String coursePrefix) {
        return getRequirementsFromBulletin(coursePrefix, "Prerequisite:");
    }

    /**
     * Retrieves corequisite requirement groups for a specific course prefix from the UGA bulletin.
     *
     * @param coursePrefix the course prefix to search for (e.g., "MATH", "CSCI")
     * @return a list of RequirementGroup objects containing the corequisite requirements
     */
    public static ArrayList<RequirementGroup> getCorequisitesFromBulletin(String coursePrefix) {
        return getRequirementsFromBulletin(coursePrefix, "Corequisite:");
    }

    /**
     * Retrieves "pre-or-co requisite" groups for a specific course prefix from the UGA bulletin.
     *
     * @param coursePrefix the course prefix to search for (e.g., "MATH", "CSCI")
     * @return a list of RequirementGroup objects containing the "pre-or-co requisite" requirements
     */
    public static ArrayList<RequirementGroup> getPreOrCorequisitesFromBulletin(String coursePrefix) {
        return getRequirementsFromBulletin(coursePrefix, "Pre or Corequisite:");
    }


    public static void main(String[] args) {
        ArrayList<RequirementGroup> groups = getPreOrCorequisitesFromBulletin("fanr");
        for (RequirementGroup group : groups) {
            System.out.println(group);
        }
    }
}

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
    public static void getBulletinData(String coursePrefix) {
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
                    isCourseID = true;
                } else if (isCourseID) {
                    String courseID = text.substring(0,text.indexOf("."));
                    System.out.println("course name: " + courseID);
                    isCourseID = false;


                // The row in the table contains the prerequisites
                } else if (text.contains("Prerequisite")) {
                    isPrerequisites = true;
                } else if (isPrerequisites) {
                    // I found that some departments call the "permission of department" requirement "permission of school"
                    String modifiedText = text.replace("school", "department") + "END";

                    Pattern uppercaseLettersRegex = Pattern.compile("\\b[A-Z]{4}\\b"); // i.e. "MATH"
                    Pattern courseNumberRegex = Pattern.compile("\\b\\d{4}[A-Z]?\\b"); // i.e. "1113"
                    System.out.println("RAW prerequisites:" + text);

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

                    ArrayList<PrerequisiteGroup> groups = new ArrayList<>();
                    PrerequisiteGroup currentGroup = new PrerequisiteGroup();

                    for (String s : strings) {
                        System.out.println(s);
                        Matcher letterMatcher = uppercaseLettersRegex.matcher(s); // find all prefixes
                        Matcher numberMatcher = courseNumberRegex.matcher(s); // find all suffixes
                        ArrayList<String> prefixes = new ArrayList<>();
                        ArrayList<String> suffixes = new ArrayList<>();

                        while (letterMatcher.find()) {
                            prefixes.add(letterMatcher.group());
                        }
                        while (numberMatcher.find()) {
                            suffixes.add(numberMatcher.group());
                        }

                        // Add all requirements to meet the prerequisite into the prerequisite group
                        if (prefixes.size() > 0 && suffixes.size() > 0) {
                            PrerequisiteClass prerequisite = new PrerequisiteClass(prefixes, suffixes);
                            currentGroup.addClass(prerequisite);
                        } else if (s.toLowerCase().contains("honors")) {
                            currentGroup.setRequiresHonors(true);
                        } else if (s.toLowerCase().contains("department")) {
                            currentGroup.setCanSubstituteDepartmentPermission(true);
                        }

                        // "and" signals the start of a NEW prerequisite group
                        if (s.contains("and")) {
                            groups.add(currentGroup);
                            currentGroup = new PrerequisiteGroup();
                        }

                    }
                    groups.add(currentGroup);
                    for (PrerequisiteGroup group : groups) {
                        System.out.println(group);
                    }
                    isPrerequisites = false;


                }





            }


        }




        // Close the browser
        driver.quit();
    }

    public static void main(String[] args) {
        getBulletinData("fanr");
    }
}

package edu.uga.devdogs.course_information.webscraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DescriptionScraper {
    public static String getCourseDescription(String coursePrefix, String courseSuffix) {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        WebDriver driver = new FirefoxDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://bulletin.uga.edu/coursesHome");

            WebElement prefixEntry = driver.findElement(By.id("txtEnterPrefix"));
            WebElement suffixEntry = driver.findElement(By.id("txtEnterNumber"));
            WebElement btn = driver.findElement(By.id("Imgbut_go_offPrefixCourse"));

            prefixEntry.sendKeys(coursePrefix);
            suffixEntry.sendKeys(courseSuffix);
            System.out.println("Entered Prefix: " + coursePrefix);
            System.out.println("Entered Suffix: " + courseSuffix);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

            // Wait for course results instead of relying on URL change
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lblCourseResultText")));
            Document document = Jsoup.parse(driver.getPageSource());

            Element courseResults = document.getElementById("lblCourseResultText");
            if (courseResults == null) return "Description not found";

            Elements resultsTables = courseResults.getElementsByClass("courseresultstable");
            boolean isDescription = false;

            for (Element table : resultsTables) {
                for (Element data : table.getElementsByTag("td")) {
                    String text = data.text().trim();
                    if (text.contains("Description")) {
                        isDescription = true;
                    } else if (isDescription) {
                        return text;
                    }
                }
            }
            return "Description not found";
        } catch (Exception e) {
            System.err.println("Error fetching course description: " + e.getMessage());
            return "Error retrieving description";
        } finally {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        String description = getCourseDescription("AAEC", "2580");
        System.out.println(description);
    }
}
 
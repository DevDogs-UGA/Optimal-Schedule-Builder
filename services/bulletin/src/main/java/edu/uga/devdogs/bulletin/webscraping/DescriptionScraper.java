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

public class DescriptionScraper {
    public static String getCourseDescription(String coursePrefix, String courseSuffix) {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        WebDriver driver = new FirefoxDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the bulletin site, enter the desired prefix into the prefix field, and click enter.
        driver.get("https://bulletin.uga.edu/coursesHome");
        WebElement prefixEntry = driver.findElement(By.id("txtEnterPrefix"));
        prefixEntry.sendKeys(coursePrefix);

        WebElement suffixEntry = driver.findElement(By.id("txtEnterNumber"));
        suffixEntry.sendKeys(courseSuffix);




        WebElement btn = driver.findElement(By.id("Imgbut_go_offPrefixCourse"));
        btn.click();

        // Wait for the next page to load, then parse the HTML.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lblCourseResultText")));
        Document document = Jsoup.parse(driver.getPageSource());

        // Find the table with the information about each course.

        Element courseResults = document.getElementById("lblCourseResultText");
        Elements resultsTables = courseResults.getElementsByClass("courseresultstable");
        boolean isDescription = false;
        for (Element table : resultsTables) {

            // Loop through each row of the table to find the information we need
            Elements allData = table.getElementsByTag("td");
            for (Element data : allData) {
                String text = data.text();
                // The row in the table contains the course ID - print it
                if (text.contains("Description")) {
                    isDescription = true;
                } else if (isDescription) {
                    return text;
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String description = getCourseDescription("fanr", "1100E");
        System.out.println(description);
    }
}

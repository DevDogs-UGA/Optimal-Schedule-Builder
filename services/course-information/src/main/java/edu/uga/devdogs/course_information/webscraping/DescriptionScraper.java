package edu.uga.devdogs.course_information.webscraping;


import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Removed incorrect import for jakarta.xml.bind.Element

import java.time.Duration;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DescriptionScraper {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public DescriptionScraper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getCourseDescription(String coursePrefix, String courseSuffix) {
        try {
            driver.get("https://bulletin.uga.edu/coursesHome");

            WebElement prefixEntry = driver.findElement(By.id("txtEnterPrefix"));
            WebElement suffixEntry = driver.findElement(By.id("txtEnterNumber"));
            WebElement btn = driver.findElement(By.id("Imgbut_go_offPrefixCourse"));

            prefixEntry.sendKeys(coursePrefix);
            suffixEntry.sendKeys(courseSuffix);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

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
            System.err.println("Error fetching course description (" + coursePrefix + " " + courseSuffix + "): " + e.getMessage());
            return "Error retrieving description";
        }
    }
}

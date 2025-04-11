package edu.uga.devdogs.course_information.webscraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class RateMyProfessorScraper {

    // Holds the data for RateMyProfessor
    public static class RMPData {
        public String firstName;
        public String lastName;
        public String id;

        public RMPData(String firstName, String lastName, String id) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.id = id;
        }
    }

    // Gets professor information from RateMyProfessor based on last name
    public static RMPData getRateMyProfessorInfoFromLastName(String lastName) {
        try {
            // Base URL (replace with actual search URL or construct dynamically)
            String url = "https://www.ratemyprofessors.com/search/teachers?query=" + lastName + "&sid=U.S.";
            Document doc = Jsoup.connect(url).get();

            // Find professor details (Adjust based on actual site structure)
            Elements results = doc.select(".TeacherCard");
            if (results.isEmpty()) {
                System.out.println("No professors found for: " + lastName);
                return null;
            }

            // Get first result (adjust selection if needed)
            Element professorCard = results.get(0);
            String profFirstName = professorCard.select(".result-header").text();
            String profLastName = professorCard.select(".result-header").text();  // adjust accordingly
            String profId = professorCard.attr("data-teacher-id");

            return new RMPData(profFirstName, profLastName, profId);
        } catch (IOException e) {
            System.err.println("Error while scraping RateMyProfessor data: " + e.getMessage());
            return null;
        }
    }

    // Get the rating of a professor by ID
    public static float getRating(String profId) {
        try {
            String url = "https://www.ratemyprofessors.com/ShowRatings.jsp?tid=" + profId;
            Document doc = Jsoup.connect(url).get();
            Elements ratingElement = doc.select(".grade");  // Adjust selector based on actual site structure
            if (!ratingElement.isEmpty()) {
                String ratingText = ratingElement.first().text();
                return Float.parseFloat(ratingText);
            }
        } catch (IOException e) {
            System.err.println("Error fetching rating for professor ID " + profId);
        }
        return 3.0f;  // Default rating
    }

    // Get difficulty level of a professor by ID
    public static float getDifficultyLevel(String profId) {
        try {
            String url = "https://www.ratemyprofessors.com/ShowRatings.jsp?tid=" + profId;
            Document doc = Jsoup.connect(url).get();
            Elements difficultyElement = doc.select(".difficulty");  // Adjust selector as needed
            if (!difficultyElement.isEmpty()) {
                String difficultyText = difficultyElement.first().text();
                return Float.parseFloat(difficultyText);
            }
        } catch (IOException e) {
            System.err.println("Error fetching difficulty level for professor ID " + profId);
        }
        return 3.0f;  // Default difficulty
    }

    // Get the percentage of students who would take the professor again
    public static int getWouldTakeAgainPercentage(String profId) {
        try {
            String url = "https://www.ratemyprofessors.com/ShowRatings.jsp?tid=" + profId;
            Document doc = Jsoup.connect(url).get();
            Elements wouldTakeAgainElement = doc.select(".would_take_again");  // Adjust selector accordingly
            if (!wouldTakeAgainElement.isEmpty()) {
                String wouldTakeAgainText = wouldTakeAgainElement.first().text().replace("%", "").trim();
                return Integer.parseInt(wouldTakeAgainText);
            }
        } catch (IOException e) {
            System.err.println("Error fetching 'Would Take Again' percentage for professor ID " + profId);
        }
        return 50;  // Default percentage
    }

    // Get the total number of reviews for a professor
    public static int getTotalReviews(String profId) {
        try {
            String url = "https://www.ratemyprofessors.com/ShowRatings.jsp?tid=" + profId;
            Document doc = Jsoup.connect(url).get();
            Elements reviewCountElement = doc.select(".count");  // Adjust based on actual structure
            if (!reviewCountElement.isEmpty()) {
                String reviewCountText = reviewCountElement.first().text().replace(",", "").trim();
                return Integer.parseInt(reviewCountText);
            }
        } catch (IOException e) {
            System.err.println("Error fetching total reviews for professor ID " + profId);
        }
        return 10;  // Default review count
    }
}

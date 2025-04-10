package edu.uga.devdogs.course_information.webscraping;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class RateMyProfessorScraper {

    public static int getRateMyProfessorId(String lastName) {
        String apiUrl = "https://www.ratemyprofessors.com/graphql";
        String query = "{\"query\":\"query SearchTeachers($query: String!) { newSearch { teachers(query: $query) { edges { node { legacyId firstName lastName school { name } } } } } }\",\"variables\":{\"query\":\"" + lastName + "\"}}";
    
        try {
            HttpResponse<String> response = HttpClient.newHttpClient()
                .send(HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(query))
                    .build(),
                HttpResponse.BodyHandlers.ofString());
    
            JSONObject jsonResponse = new JSONObject(response.body());
    
            if (!jsonResponse.has("data") || jsonResponse.isNull("data")) {
                System.err.println("RateMyProfessors API: No 'data' in response for " + lastName);
                return -1;
            }
    
            JSONArray edges = jsonResponse.getJSONObject("data")
                .getJSONObject("newSearch")
                .getJSONObject("teachers")
                .getJSONArray("edges");
    
            for (int i = 0; i < edges.length(); i++) {
                JSONObject node = edges.getJSONObject(i).getJSONObject("node");
                String schoolName = node.getJSONObject("school").getString("name");
    
                if ("University of Georgia".equals(schoolName)) {
                    String foundLast = node.getString("lastName");
                    if (foundLast.equalsIgnoreCase(lastName)) {
                        return node.getInt("legacyId");
                    }
                }
            }
    
            System.out.println("No UGA match found for: " + lastName);
        } catch (Exception e) {
            System.err.println("Error retrieving professor ID for " + lastName + ": " + e.getMessage());
            e.printStackTrace();
        }
    
        return -1;
    }

    private static String getStyledFeedbackItem(int id, int index) {
        String apiUrl = "https://www.ratemyprofessors.com/professor/" + id;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();


        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String html = response.body();

        Document doc = Jsoup.parse(html);

        Elements elements = doc.select("[class*=StyledFeedbackItem]");

        // Print out the matching elements
        Element targetElement = elements.get(index).child(0);
        return targetElement.text();
    }

    public static String getDepatment(int id) {
        String department = getStyledFeedbackItem(id, 0);
        String cleaned = department.replace("%", "");
        return cleaned;
    }

    public static int getWouldTakeAgainPercentage(int id) {
        String percentage = getStyledFeedbackItem(id, 0);
        String cleaned = percentage.replace("%", "");

        return Integer.parseInt(cleaned);



    }

    public static int getDifficultyLevel(int id) {
        String difficultyLevel = getStyledFeedbackItem(id, 1);

        return Integer.parseInt(difficultyLevel);
    }

    public static double getRating(int id) {
        String apiUrl = "https://www.ratemyprofessors.com/professor/" + id;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();


        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String html = response.body();

        Document doc = Jsoup.parse(html);

        // Select elements with a class name containing "StyledFeedbackItem"
        Elements elements = doc.select("[class*=RatingValue__Numerator]");
        String text = elements.first().text();
        return Double.parseDouble(text);

    }
    public static void main(String[] args) {

        int id = getRateMyProfessorId("Jessica Tripp");
        
        int percentage = getWouldTakeAgainPercentage(id);
        System.out.println(percentage);

        int difficulty = getDifficultyLevel(id);
        System.out.println(difficulty);

        double rating = getRating(id);
        System.out.println(rating);


    }

    public String getTotalReviews(int professorId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTotalReviews'");
    }
}

package edu.uga.devdogs.professor_rating.webscraping;

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

    public static int getRateMyProfessorId(String professorName) {
        String apiUrl = "https://www.ratemyprofessors.com/graphql";
        String authHeader = "Basic dGVzdDp0ZXN0";
        String jsonInputString = " {\"query\":\"query NewSearchTeachersQuery(\\n  $query: TeacherSearchQuery!\\n  $count: Int\\n  $includeCompare: Boolean!\\n) {\\n  newSearch {\\n    teachers(query: $query, first: $count) {\\n      didFallback\\n      edges {\\n        cursor\\n        node {\\n          id\\n          legacyId\\n          firstName\\n          lastName\\n          department\\n          departmentId\\n          school {\\n            legacyId\\n            name\\n            id\\n          }\\n          ...CompareProfessorsColumn_teacher @include(if: $includeCompare)\\n        }\\n      }\\n    }\\n  }\\n}\\n\\nfragment CompareProfessorsColumn_teacher on Teacher {\\n  id\\n  legacyId\\n  firstName\\n  lastName\\n  school {\\n    legacyId\\n    name\\n    id\\n  }\\n  department\\n  departmentId\\n  avgRating\\n  avgDifficulty\\n  numRatings\\n  wouldTakeAgainPercentRounded\\n  mandatoryAttendance {\\n    yes\\n    no\\n    neither\\n    total\\n  }\\n  takenForCredit {\\n    yes\\n    no\\n    neither\\n    total\\n  }\\n  ...NoRatingsArea_teacher\\n  ...RatingDistributionWrapper_teacher\\n}\\n\\nfragment NoRatingsArea_teacher on Teacher {\\n  lastName\\n  ...RateTeacherLink_teacher\\n}\\n\\nfragment RateTeacherLink_teacher on Teacher {\\n  legacyId\\n  numRatings\\n  lockStatus\\n}\\n\\nfragment RatingDistributionChart_ratingsDistribution on ratingsDistribution {\\n  r1\\n  r2\\n  r3\\n  r4\\n  r5\\n}\\n\\nfragment RatingDistributionWrapper_teacher on Teacher {\\n  ...NoRatingsArea_teacher\\n  ratingsDistribution {\\n    total\\n    ...RatingDistributionChart_ratingsDistribution\\n  }\\n}\\n\",\"variables\":{\"query\":{\"text\":\"" + professorName + "\"},\"count\":10,\"includeCompare\":false}}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .header("Authorization", authHeader)
                .POST(HttpRequest.BodyPublishers.ofString(jsonInputString, StandardCharsets.UTF_8))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response: " + response.body());
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONArray professors = jsonResponse.getJSONObject("data").getJSONObject("newSearch").getJSONObject("teachers").getJSONArray("edges");

            for (int i = 0; i < professors.length(); i++) {
                JSONObject teacherNode = professors.getJSONObject(i).getJSONObject("node");
                String firstName = teacherNode.getString("firstName");
                String lastName = teacherNode.getString("lastName");
                int id = teacherNode.getInt("legacyId");
                JSONObject schoolNode = teacherNode.getJSONObject("school");
                String schoolName = schoolNode.getString("name");
                if (schoolName.equals("University of Georgia")) {
                    System.out.println("Professor: " + firstName + " " + lastName + " " + schoolName + " " + id);
                    return id;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.parseInt(null);
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

    public static int getWouldTakeAgainPercentage(int id) {
        String percentage = getStyledFeedbackItem(id, 0);
        String cleaned = percentage.replace("%", "");

        return Integer.parseInt(cleaned);



    }

    public static int getDifficultyLevel(int id) {
        String difficultyLevel = getStyledFeedbackItem(id, 1);

        return Integer.parseInt(difficultyLevel);
    }

    private static double getRating(int id) {
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
}

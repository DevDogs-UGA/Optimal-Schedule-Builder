package edu.uga.devdogs.course_information.webscraping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class RateMyProfessorScraper {

    private static final String GRAPHQL_URL = "https://www.ratemyprofessors.com/graphql";
    private static final String TARGET_SCHOOL_NAME = "University of Georgia";
    private static final OkHttpClient client = new OkHttpClient();

    public static Double getRatingForProfessor(String lastName) {
        try {
            String query = """
                {
                  searchTeachers(query: "%s") {
                    edges {
                      node {
                        id
                        firstName
                        lastName
                        school {
                          name
                        }
                        avgRating
                      }
                    }
                  }
                }
                """.formatted(lastName);

            RequestBody body = RequestBody.create(
                    new ObjectMapper().writeValueAsString(new GraphQLQuery(query)),
                    MediaType.parse("application/json")
            );

            Request request = new Request.Builder()
                    .url(GRAPHQL_URL)
                    .post(body)
                    .header("Content-Type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                System.err.println("HTTP error fetching RMP data: " + response.code() + ", URL=" + GRAPHQL_URL);
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body().string());

            JsonNode edges = root.at("/data/searchTeachers/edges");

            if (!edges.isArray() || edges.size() == 0) {
                System.out.println("No RMP results found for: " + lastName);
                return null;
            }

            for (JsonNode edge : edges) {
                JsonNode node = edge.get("node");
                String schoolName = node.get("school").get("name").asText();
                String firstName = node.get("firstName").asText();
                String matchedLastName = node.get("lastName").asText();

                System.out.println("Found: " + firstName + " " + matchedLastName + " at " + schoolName);

                if (schoolName.equalsIgnoreCase(TARGET_SCHOOL_NAME)) {
                    double rating = node.get("avgRating").asDouble();
                    System.out.println("Matched " + firstName + " " + matchedLastName + " (UGA) with rating: " + rating);
                    return rating;
                }
            }

            System.out.println("No matching UGA professor found for: " + lastName);
            return null;

        } catch (IOException e) {
            System.err.println("Error while scraping RateMyProfessor data: " + e.getMessage());
            return null;
        }
    }

    // Helper class to wrap GraphQL query
    static class GraphQLQuery {
        public String query;

        public GraphQLQuery(String query) {
            this.query = query;
        }
    }

    public static void main(String[] args) {
        // Test a few example professors
        String[] lastNames = {"Shonkwiler", "Colson", "Yuan", "Nath", "Beveridge", "Kraemer"};

        for (String lastName : lastNames) {
            System.out.println("\n--- Searching for: " + lastName + " ---");
            Double rating = RateMyProfessorScraper.getRatingForProfessor(lastName);
            if (rating != null) {
                System.out.printf("RMP Rating for %s: %.2f%n", lastName, rating);
            } else {
                System.out.println("No rating found for " + lastName);
            }
        }
    }
}

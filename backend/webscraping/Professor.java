/**
 * @author Yuval Deshe
 * Represents an entry for a professor on the RateMyProfessors website.
 */
public class Professor {
    String name; // The name of the professor.
    double quality; // The overall quality of the professor.
    double wouldTakeAgainPercent; // The percentage of students who would take a class with this professor again.
    double difficultyRating; // The average difficulty of a professor's classes.
    int numOneStar, numTwoStar, numThreeStar, numFourStar, numFiveStar; // The count of each star-rating, ranging from 1-5 stars.
    int[] ratingDistribution = {numOneStar, numTwoStar, numThreeStar, numFourStar, numFiveStar}; // The distribution of each star-rating.
    String[] tags; // The tags associated with the professor.
    int numRatings; // The number of ratings that the professor has.
    String department; // The department this professor works in.
} // Professor

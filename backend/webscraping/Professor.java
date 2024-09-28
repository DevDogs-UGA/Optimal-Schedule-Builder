/**
 * @author Yuval Deshe
 * Represents an entry for a professor on the RateMyProfessors website.
 */
public class Professor {
    private String name; // The name of the professor.
    private double quality; // The overall quality of the professor.
    private double wouldTakeAgainPercent; // The percentage of students who would take 
                                          // a class with this professor again.
    private double difficultyRating; // The average difficulty of a professor's classes.
    private int numOneStar; // The number of one-star ratings the professor has.
    private int numTwoStar; // The number of two-star ratings the professor has.
    private int numThreeStar; // The number of three-star ratings the professor has.
    private int numFourStar; // The number of four-star ratings the professor has.
    private int numFiveStar; // The number of five-star ratings the professor has.
    private int[] ratingDistribution; // The distribution of each star-rating.
    private int numRatings; // The number of ratings that the professor has.
    private String[] tags; // The tags associated with the professor.
    private String department; // The department this professor works in.

    /**
     * Constructs a {@code Professor} object.
     */
    public Professor() {
        name = "";
        quality = 0;
        wouldTakeAgainPercent = 0;
        difficultyRating = 0;
        numOneStar = 0;
        numTwoStar = 0;
        numThreeStar = 0;
        numFourStar = 0;
        numFiveStar = 0;
        ratingDistribution = new int[] { numOneStar, numTwoStar, numThreeStar, numFourStar, numFiveStar };
        numRatings = 0;
        tags = null;
        department = "";
    } // Professor

    /**
     * Constructs a {@code Professor} object with all variables except {@code ratingDistribution} 
     * specified as parameters. Uses the values of the star ratings to set 
     * {@code ratingDistribution}.
     * 
     * @param name The name of the professor.
     * @param quality The overall quality of the professor.
     * @param wouldTakeAgainPercent The percentage of students who would take this professor again.
     * @param difficulty The average difficulty of a professor's classes.
     * @param numOneStar The number of one-star ratings given to this professor.
     * @param numTwoStar The number of two-star ratings given to this professor.
     * @param numThreeStar The number of three-star ratings given to this professor.
     * @param numFourStar The number of four-star ratings given to this professor.
     * @param numFiveStar The number of five-star ratings given to this professor.
     * @param numRatings The number of ratings that the professor has.
     * @param tags The tags associated with the professor.
     * @param department The department this professor works in.
     */
    @Override
    public Professor(String name, double quality, double wouldTakeAgainPercent, double difficultyRating,
                     int numOneStar, int numTwoStar, int numThreeStar, int numFourStar, 
                     int numFiveStar, int numRatings, String[] tags, String department) {
        this.name = name;
        this.quality = quality;
        this.wouldTakeAgainPercent = wouldTakeAgainPercent;
        this.difficultyRating = difficultyRating;
        this.numOneStar = numOneStar;
        this.numTwoStar = numTwoStar;
        this.numThreeStar = numThreeStar;
        this.numFourStar = numFourStar;
        this.numFiveStar = numFiveStar;
        this.numRatings = numRatings;
        this.ratingDistribution = new int[] {numOneStar, numTwoStar, numThreeStar, 
                                             numFourStar, numFiveStar};
        this.tags = tags;
        this.department = department;
    } // Professor

    /**
     * Constructs a {@code Professor} object with all variables specified as parameters.
     * 
     * @param name The name of the professor.
     * @param quality The overall quality of the professor.
     * @param wouldTakeAgainPercent The percentage of students who would take this professor again.
     * @param difficulty The average difficulty of a professor's classes.
     * @param numOneStar The number of one-star ratings given to this professor.
     * @param numTwoStar The number of two-star ratings given to this professor.
     * @param numThreeStar The number of three-star ratings given to this professor.
     * @param numFourStar The number of four-star ratings given to this professor.
     * @param numFiveStar The number of five-star ratings given to this professor.
     * @param ratingDistribution The distribution of each star-rating.
     * @param numRatings The number of ratings that the professor has.
     * @param tags The tags associated with the professor.
     * @param department The department this professor works in.
     */
    @Override
    public Professor(String name, double quality, double wouldTakeAgainPercent, double difficultyRating,
                     int numOneStar, int numTwoStar, int numThreeStar, int numFourStar, 
                     int numFiveStar, int[] ratingDistribution, int numRatings, String[] tags, 
                     String department) {
        this.name = name;
        this.quality = quality;
        this.wouldTakeAgainPercent = wouldTakeAgainPercent;
        this.difficultyRating = difficultyRating;
        this.numOneStar = numOneStar;
        this.numTwoStar = numTwoStar;
        this.numThreeStar = numThreeStar;
        this.numFourStar = numFourStar;
        this.numFiveStar = numFiveStar;
        this.ratingDistribution = ratingDistribution;
        this.numRatings = numRatings;
        this.tags = tags;
        this.department = department;
    } // Professor

    /**
     * Returns the name of the professor.
     *
     * @return the value of {@code name}.
     */
    public String getName() {
        return name;
    } // getName

    /**
     * Sets the name of the professor.
     * 
     * @param newName the new value of {@code name}.
     */
    public void setName(String newName) {
        name = newName;
    } // setname

    /**
     * Returns the quality of the professor.
     * 
     * @return the value of {@code quality}.
     */
    public double getQuality() {
        return quality;
    } // getQuality

    /**
     * Sets the quality of the professor.
     * 
     * @param newQuality the new value of {@code quality}
     */
    public void setQuality(double newQuality) {
        quality = newQuality;
    } // setQuality

    /**
     * Returns the percentage of students who would take a class with this professor again.
     * 
     * @return the value of {@code wouldTakeAgainPercent}.
     */
    public double getWouldTakeAgainPercent() {
        return wouldTakeAgainPercent;
    } // setWouldTakeAgainPercent

    /**
     * Sets the percentage of students who would take a class with this professor again.
     * 
     * @param newPercent the new value of {@code wouldTakeAgainPercent}.
     */
    public void setWouldTakeAgainPercent(double newWouldTakeAgainPercent) {
        wouldTakeAgainPercent = newWouldTakeAgainPercent;
    } // setWouldTakeAgainPercent

    /**
     * Returns the difficulty rating of a professor.
     * 
     * @return the value of {@code difficultyRating}
     */
    public double getDifficultyRating() {
        return difficultyRating;
    } // getDifficultyRating

    /**
     * Sets the difficulty rating of a professor.
     * 
     * @param newDifficultyRating the new value of {@code difficultyRating}.
     */
    public void setDifficultyRating(double newDifficultyRating) {
        difficultyRating = newDifficultyRating;
    } // setDifficultyRating

    /**
     *  Returns the number of one-star reviews.
     * 
     * @return the value of {@code numOneStar}.
     */
    public int getNumOneStar() {
        return numOneStar;
    } // getNumOneStar

    /**
     * Sets the number of one-star reviews.
     *
     * @param newNumOneStar the new value of {@code numOneStar}.
     */
    public void setNumOneStar(int newNumOneStar) {
        numOneStar = newNumOneStar;
    } // setNumOneStar

    /**
     *  Returns the number of two-star reviews.
     * 
     * @return the value of {@code numTwoStar}.
     */
    public int getNumTwoStar() {
        return numTwoStar;
    } // getNumTwoStar

    /**
     * Sets the number of two-star reviews.
     *
     * @param newNumOneStar the new value of {@code numTwoStar}.
     */
    public void setNumTwoStar(int newNumTwoStar) {
        numTwoStar = newNumTwoStar;
    } // setNumTwoStar

    /**
     *  Returns the number of three-star reviews.
     * 
     * @return the value of {@code numThreeStar}.
     */
    public int getNumThreeStar() {
        return numThreeStar;
    } // getNumThreeStar

    /**
     * Sets the number of three-star reviews.
     *
     * @param newNumOneStar the new value of {@code numThreeStar}.
     */
    public void setNumThreeStar(int newNumThreeStar) {
        numThreeStar = newNumThreeStar;
    } // setNumThreeStar

    /**
     *  Returns the number of four-star reviews.
     * 
     * @return the value of {@code numFourStar}.
     */
    public int getNumFourStar() {
        return numFourStar;
    } // getNumFourStar

    /**
     * Sets the number of four-star reviews.
     *
     * @param newNumOneStar the new value of {@code numFourStar}.
     */
    public void setNumFourStar(int newNumFourStar) {
        numFourStar = newNumFourStar;
    } // setNumFourStar

    /**
     *  Returns the number of five-star reviews.
     * 
     * @return the value of {@code numFiveStar}.
     */
    public int getNumFiveStar() {
        return numFiveStar;
    } // getNumFiveStar

    /**
     * Sets the number of five-star reviews.
     *
     * @param newNumOneStar the new value of {@code numFiveStar}.
     */
    public void setNumFiveStar(int newNumFiveStar) {
        numFiveStar = newNumFiveStar;
    } // setNumFiveStar

    /**
     * Returns the rating distribution of the professor.
     * 
     * @return the value of {@code ratingDistribution}.
     */
    public int[] getRatingDistribution() {
        return ratingDistribution;
    } // getRatingDistribution

    /**
     * Sets the rating distribution of the professor and updates the number of each star-review 
     * respectively.
     * 
     * @param newRatingDistribution the new value of {@code ratingDistribution}. Must be an int 
     * array of length 5.
     */
    public void setRatingDistribution(int[] newRatingDistribution) {
        // ensure the provided array has the required size
        if (newRatingDistribution.length != 5) {
            System.err.printn("Provided array did not have a length of 5 - " +
                              "did not update ratingDistribution.");
            return;
        } // if

        // Update the number of reviews accoringly with the new values that will be 
        // added to ratingDistribution
        setNumOneStar(newRatingDistribution[0]);
        setNumTwoStar(newRatingDistribution[1]);
        setNumThreeStar(newRatingDistribution[2]);
        setNumFourStar(newRatingDistribution[3]);
        setNumFiveStar(newRatingDistribution[4]);

        // Update ratingDistribution
        ratingDistribution = newRatingDistribution;
    } // setRatingDistribution

    /**
     * Sets the rating distribution of the professor by taking the number 
     * of each star-review as input.
     * 
     * @param newOneStar the new value of {@code numOneStar}.
     * @param newTwoStar the new value of {@code numTwoStar}.
     * @param newThreeStar the new value of {@code numThreeStar}.
     * @param newFourStar the new value of {@code numFourStar}.
     * @param newFiveStar the new value of {@code numFiveStar}.
     */
    public void setRatingDistribution(int newOneStar, int newTwoStar, int newThreeStar, 
                                      int newFourStar, int newFiveStar) {
        // Update the number of reviews accoringly with the new values 
        // that will be added to ratingDistribution
        setNumOneStar(newOneStar);
        setNumTwoStar(newTwoStar);
        setNumThreeStar(newThreeStar);
        setNumFourStar(newFourStar);
        setNumFiveStar(newFiveStar);
        // Update ratingDistribution with the new values
        ratingDistribution = new int[] {numOneStar, numTwoStar, numThreeStar, 
                                        numFourStar, numFiveStar};
    } // setRatingDistribution

    /**
     * Returns the number of ratings that the professor has.
     *
     * @return the value of {@code numRatings}.
     */
    public int getNumRatings() {
        return numRatings;
    } // getNumRatings

    /**
     * Sets the number of ratings that the professor has.
     * 
     * @param newNumRatings the new value of {@code numRatings}.
     */
    public void setNumRatings(int newNumRatings) {
        numRatings = newNumRatings;
    } // setNumRatings

    /**
     * Returns the tags associated with a professor.
     * 
     * @return the value of {@code tags}.
     */
    public String[] getTags() {
        return tags;
    } // getTags

    /**
     * Sets the tags associated with a professor.
     * 
     * @param newTags the new value of {@code tags}.
     */
    public void setTags(String[] newTags) {
        tags = newTags;
    } // setTags

    /**
     * Returns the department that the professor works in.
     *
     * @return the value of {@code department}.
     */
    public String getDepartment() {
        return department;
    } // getDepartment

    /**
     * Sets the department that the professor works in.
     * 
     * @param newDepartment the new value of {@code department}.
     */
    public void setDepartment(String newDepartment) {
        department = newDepartment;
    } // setDepartment
} // Professor
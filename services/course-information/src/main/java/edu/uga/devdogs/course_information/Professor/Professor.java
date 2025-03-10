package edu.uga.devdogs.course_information.Professor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;

@Entity
public class Professor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int professorId;

    private String firstName;
    private String lastName;
    private int totalReviews = 0;
    private float averageRating = 0.0f;
    private float difficultyRating = 0.0f;
    private int wouldTakeAgainRating;

    @OneToMany(mappedBy = "professor")
    private List<Class> classes;

    // Default constructor
    public Professor() {}

    // Constructor without ID
    public Professor(String firstName, String lastName, int totalReviews, float averageRating, float difficultyRating, int wouldTakeAgainRating, List<Class> classes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalReviews = totalReviews;
        this.averageRating = averageRating;
        this.difficultyRating = difficultyRating;
        this.wouldTakeAgainRating = wouldTakeAgainRating;
        this.classes = classes;
    }

    // Constructor with ID
    public Professor(int professorId, String firstName, String lastName, int totalReviews, float averageRating, float difficultyRating, int wouldTakeAgainRating, List<Class> classes) {
        this.professorId = professorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalReviews = totalReviews;
        this.averageRating = averageRating;
        this.difficultyRating = difficultyRating;
        this.wouldTakeAgainRating = wouldTakeAgainRating;
        this.classes = classes;
    }

    // Getters and Setters
    public int getProfessorId() {
        return professorId;
    }
public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public float getDifficultyRating() {
        return difficultyRating;
    }

    public void setDifficultyRating(float difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    public int getWouldTakeAgainRating() {
        return wouldTakeAgainRating;
    }

    public void setWouldTakeAgainRating(int wouldTakeAgainRating) {
        this.wouldTakeAgainRating = wouldTakeAgainRating;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }
}
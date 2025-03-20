// package edu.uga.devdogs.course_information.Schedule;

// import jakarta.persistence.*;
// import java.util.List;

// /**
//  * Entity class representing a schedule
//  */
// @Entity
// public class Schedule {

//     // Primary key for the Schedule table
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     // ID of the user to whom this schedule belongs
//     private Long userId;

//     // List of course sections associated with this schedule
//     // Stored in a separate table mapped by the schedule ID
//     @ElementCollection
//     @CollectionTable(name = "course_section", joinColumns = @JoinColumn(name = "schedule_id"))
//     @Column(name = "section")
//     private List<String> courseSections;

//     // Rate my Professor Rating
//     private double rating;

//     // Name of the schedule
//     private String name;

//     // Total number of hours allocated for the schedule
//     private int hours;

//     // Average walking time between classes in minutes
//     private int avgWalkTime;

//     // Default constructor
//     public Schedule() {
//     }

//     /**
//      * Parameterized constructor to initialize a Schedule object.
//      *
//      * @param userId        ID of the user
//      * @param courseSections List of course sections
//      * @param rating        Schedule rating
//      * @param name          Schedule name
//      * @param hours         Total hours
//      * @param avgWalkTime   Average walk time
//      */
//     public Schedule(Long userId, List<String> courseSections, double rating, String name, int hours, int avgWalkTime) {
//         this.userId = userId;
//         this.courseSections = courseSections;
//         this.rating = rating;
//         this.name = name;
//         this.hours = hours;
//         this.avgWalkTime = avgWalkTime;
//     }

//     // Getters and setters for all fields
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public Long getUserId() {
//         return userId;
//     }

//     public void setUserId(Long userId) {
//         this.userId = userId;
//     }

//     public List<String> getCourseSections() {
//         return courseSections;
//     }

//     public void setCourseSections(List<String> courseSections) {
//         this.courseSections = courseSections;
//     }

//     public double getRating() {
//         return rating;
//     }

//     public void setRating(double rating) {
//         this.rating = rating;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public int getHours() {
//         return hours;
//     }

//     public void setHours(int hours) {
//         this.hours = hours;
//     }

//     public int getAvgWalkTime() {
//         return avgWalkTime;
//     }

//     public void setAvgWalkTime(int avgWalkTime) {
//         this.avgWalkTime = avgWalkTime;
//     }

//     // Override toString to provide a human-readable representation of the object
//     @Override
//     public String toString() {
//         return "Schedule{" +
//                 "id=" + id +
//                 ", userId=" + userId +
//                 ", courseSections=" + courseSections +
//                 ", rating=" + rating +
//                 ", name='" + name + '\'' +
//                 ", hours=" + hours +
//                 ", avgWalkTime=" + avgWalkTime +
//                 '}';
//     }
// }

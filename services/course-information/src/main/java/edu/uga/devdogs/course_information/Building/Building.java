package edu.uga.devdogs.course_information.Building;

import edu.uga.devdogs.course_information.Class.ClassEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Java JPA entity representation for Building
 */
@Entity
public class Building implements Serializable {
    
    /*
     * Variables
     */

    @Id
    @JsonProperty("Building Code")
    private String buildingCode;  // Building Code

    @JsonProperty("Name")
    private String name;         // Name of the building

    @JsonProperty("Address")
    private String address;      // Address of the building
    
    @JsonProperty("Latitude")
    private double latitude;     // Latitude coordinate
    
    @JsonProperty("Longitude")
    private double longitude;    // Longitude coordinate

    /*
     * Relationships
     */
    @OneToMany(mappedBy = "building")
    private List<ClassEntity> classes;

    /*
     * Constructors
     */

    // Default constructor
    public Building() {}

    // Constructor with parameters
    public Building(String buildingCode, String name, String address, double latitude, double longitude) {
        this.buildingCode = buildingCode;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /*
     * Getters and Setters
     */
    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<ClassEntity> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassEntity> classes) {
        this.classes = classes;
    }

    /*
     * toString Method
     */
    @Override
    public String toString() {
        return "Building{" +
                "buildingCode=" + buildingCode +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

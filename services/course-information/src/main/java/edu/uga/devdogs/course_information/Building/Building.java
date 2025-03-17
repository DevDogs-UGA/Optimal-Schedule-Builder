package edu.uga.devdogs.course_information.Building;

import edu.uga.devdogs.course_information.Class.ClassEntity;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

/*
 * Java JPA entity representation for Building
 */
@Entity
public class Building implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long buildingId;

    private String buildingNumber;
    private String name;
    private String grid;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "building")
    private List<ClassEntity> classes;

    public Building() {}

    public Building(String buildingNumber, String name, String grid, double latitude, double longitude) {
        this.buildingNumber = buildingNumber;
        this.name = name;
        this.grid = grid;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getBuildingId() {
        return buildingId;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
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

    @Override
    public String toString() {
        return "Building [buildingNumber=" + buildingNumber 
                + ", name='" + name 
                + "', grid='" + grid 
                + "', latitude=" + latitude 
                + ", longitude=" + longitude + "]";
    }
}

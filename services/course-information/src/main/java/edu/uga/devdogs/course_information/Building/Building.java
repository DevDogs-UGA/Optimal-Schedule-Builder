package edu.uga.devdogs.course_information.Building;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

/*
 * Java JPA entity represention for Building
 */
@Entity
public class Building implements Serializable {
    
    /*
     * Variables
     */

    // serves as the id.
    @Id
    private int buildingNumber;

    private String name;

    private String grid;

    /*
     * Relationships
     * To-Do: add relationships (one-to-one, one-to-many, many-to-one, many-to-many) here
     */

    /*
     * Constructors
     */

    // Default constructor
    public Building() {
    }

    // Constructor w/o buildingNumber
    public Building(String name, String grid) {
        this.name = name;
        this.grid = grid;
    }

    // Constructor w/ buildingNumber
    public Building(int buildingNumber, String name, String grid) {
        this.buildingNumber = buildingNumber;
        this.name = name;
        this.grid = grid;
    }

    
    /*
     * Getters and Setters
     */

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
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


    /*
     * toString
     */
    @Override
    public String toString() {
        return "Building [ buildingNumber=" + buildingNumber 
                + ", name='" + name 
                + ", grid='" + grid 
                + ']';
    }

    
}

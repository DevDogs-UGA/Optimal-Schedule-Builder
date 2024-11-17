package edu.uga.devdogs.course_information.Building;

import edu.uga.devdogs.course_information.Class.Class;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;

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
    private long buildingNumber;

    private String name;

    private String grid;

    /*
     * Relationships
     * To-Do: add relationships (one-to-one, one-to-many, many-to-one, many-to-many) here
     */
    @OneToMany(mappedBy = "building")
    private List<Class> classes;

    /*
     * Constructors
     */

    // Default constructor
    public Building() {
    }

    // Constructor w/o buildingNumber
    public Building(String name, String grid, List<Class> classes) {
        this.name = name;
        this.grid = grid;
        this.classes = classes;
    }

    // Constructor w/o class list
    public Building(int buildingNumber, String name, String grid) {
        this.buildingNumber = buildingNumber;
        this.name = name;
        this.grid = grid;
    }

    // Constructor w/ buildingNumber
    public Building(int buildingNumber, String name, String grid, List<Class> classes) {
        this.buildingNumber = buildingNumber;
        this.name = name;
        this.grid = grid;
        this.classes = classes;
    }

    
    /*
     * Getters and Setters
     */

    public long getBuildingNumber() {
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

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
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

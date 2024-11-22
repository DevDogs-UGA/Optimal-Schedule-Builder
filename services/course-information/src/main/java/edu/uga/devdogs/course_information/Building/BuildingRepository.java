package edu.uga.devdogs.course_information.Building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BuildingRepository extends JpaRepository<Building, Long>{
    
    //This will get a building by its unique ID
    @Query("SELECT b from Building b WHERE b.id = ?1")
    Building findById(int id);

    //@Query("select u from Building where u.name = ?1")
    //Building getByName(String name);
}

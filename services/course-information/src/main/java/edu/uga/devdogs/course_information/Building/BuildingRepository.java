package edu.uga.devdogs.course_information.Building;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long>{
    
    //This will get a building by its unique ID
    Building getById(int id);

    //@Query("select u from Building where u.name = ?1")
    //Building getByName(String name);
}

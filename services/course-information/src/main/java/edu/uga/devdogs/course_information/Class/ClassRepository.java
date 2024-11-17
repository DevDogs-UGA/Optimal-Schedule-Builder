package edu.uga.devdogs.course_information.Class;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uga.devdogs.course_information.Building.Building;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

    // Find all classes held on specific days (e.g., "MWF" or "TR")
    List<Class> findByDays(String days);

    // Find classes that start at a specific time
    List<Class> findByStartTime(java.sql.Time startTime);

    // Find classes that end between two times
    List<Class> findByEndTimeBetween(java.sql.Time startTime, java.sql.Time endTime);

    // Find classes in a specific building
    List<Class> findByBuilding(Building building);

    // Find classes by campus
    List<Class> findByCampus(String campus);

    // Find classes by building and room number
    List<Class> findByBuildingAndRoom(Building building, String room);

    // Find a class by its ID
    List<Class> findByClassId(int classId);
    
}
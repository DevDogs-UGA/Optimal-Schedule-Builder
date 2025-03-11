package edu.uga.devdogs.course_information.Class;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uga.devdogs.course_information.Building.Building;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {

    // Find all classes held on specific days (e.g., "MWF" or "TR")
    List<ClassEntity> findAllByDays(String days);

    // Find classes that start at a specific time
    List<ClassEntity> findAllByStartTime(java.sql.Time startTime);

    // Find classes that end between two times
    List<ClassEntity> findAllByEndTimeBetween(java.sql.Time startTime, java.sql.Time endTime);

    // Find classes in a specific building
    List<ClassEntity> findAllByBuilding(Building building);

    // Find classes by campus
    List<ClassEntity> findAllByCampus(String campus);

    // Find classes by building and room number
    List<ClassEntity> findAllByBuildingAndRoom(Building building, String room);

    // Find a class by its ID
    List<ClassEntity> findAllByClassId(int classId);
    
    // Find a class by its Course Section ID
    List<ClassEntity> findByCourseSection_Id(int courseSectionId);
} 

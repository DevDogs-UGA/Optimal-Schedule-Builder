package edu.uga.devdogs.course_information.Class;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassEntityRepository extends JpaRepository<ClassEntity, Integer> {

    // Find all classes held on specific days (e.g., "MWF" or "TR")
    List<ClassEntity> findByDays(String days);

    // Find classes that start at a specific time
    List<ClassEntity> findByStartTime(java.sql.Time startTime);

    // Find classes that end between two times
    List<ClassEntity> findByEndTimeBetween(java.sql.Time startTime, java.sql.Time endTime);

    // Find classes in a specific building
    List<ClassEntity> findByBuilding(String building);

    // Find classes by campus
    List<ClassEntity> findByCampus(String campus);

    // Find classes by building and room number
    List<ClassEntity> findByBuildingAndRoom(String building, String room);

    // Find a class by its ID
    List<ClassEntity> findByClassId(int classId);
}
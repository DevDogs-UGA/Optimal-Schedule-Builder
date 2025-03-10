package edu.uga.devdogs.course_information.Schedule;

import com.example.collegeSchedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // Custom Query Methods
    List<Schedule> findByUserId(Long userId);

    List<Schedule> findByNameContaining(String name);

    List<Schedule> findByRatingGreaterThanEqual(double rating);

    List<Schedule> findByHoursBetween(int minHours, int maxHours);

    List<Schedule> findByAvgWalkTimeLessThanEqual(int maxAvgWalkTime);
}

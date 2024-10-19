package edu.uga.devdogs.course_information.Class;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassEntityRepository extends JpaRepository<ClassEntity, Integer> {

    
}
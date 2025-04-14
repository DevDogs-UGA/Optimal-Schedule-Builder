package edu.uga.devdogs.course_information.Professor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    @Query("SELECT p FROM Professor p WHERE p.professorId = ?1")
    Professor findById(int id);

    @Query("SELECT p FROM Professor p WHERE p.lastName = ?1")
    Professor findByLastName(String lastName);

    Professor findByLastNameAndFirstNameIgnoreCase(String lastName, String firstName);

    Professor findByLastNameAndDepartment(String lastName, String department);
    
}

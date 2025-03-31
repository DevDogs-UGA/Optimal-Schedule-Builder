package edu.uga.devdogs.course_information.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import edu.uga.devdogs.course_information.Professor.Professor;
import edu.uga.devdogs.course_information.Professor.ProfessorRepository;
import edu.uga.devdogs.course_information.exceptions.ProfessorNotFoundException;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor saveOrUpdateProfessor(Professor professor) {
        Professor existingProfessor = professorRepository.findByLastNameAndFirstNameIgnoreCase(
            professor.getLastName(), professor.getFirstName());

        if (existingProfessor != null) {
            existingProfessor.setTotalReviews(professor.getTotalReviews());
            existingProfessor.setAverageRating(professor.getAverageRating());
            existingProfessor.setDifficultyRating(professor.getDifficultyRating());
            existingProfessor.setWouldTakeAgainRating(professor.getWouldTakeAgainRating());
            return professorRepository.save(existingProfessor);
        } else {
            return professorRepository.save(professor);
        }
    }

    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id)
            .orElseThrow(() -> new ProfessorNotFoundException("Professor not found with ID: " + id));
    }

    public void deleteProfessor(Long id) {
        professorRepository.deleteById(id);
    }

    public float getProfessorAverageRating(String lastName, String firstName) {
        Professor professor = professorRepository.findByLastNameAndFirstNameIgnoreCase(lastName, firstName);
        if (professor == null) {
            throw new ProfessorNotFoundException("Professor " + firstName + " " + lastName + " not found");
        }
        return professor.getAverageRating();
    }

    public int getProfessorTotalReviews(String lastName, String firstName) {
        Professor professor = professorRepository.findByLastNameAndFirstNameIgnoreCase(lastName, firstName);
        if (professor == null) {
            throw new ProfessorNotFoundException("Professor " + firstName + " " + lastName + " not found");
        }
        return professor.getTotalReviews();
    }
}

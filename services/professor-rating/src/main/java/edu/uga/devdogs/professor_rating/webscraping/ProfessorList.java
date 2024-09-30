package edu.uga.devdogs.professor_rating.webscraping;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author Yuval Deshe
 * 
 * Represents the list of all professors at UGA on the RateMyProfessor website.
 */
public class ProfessorList {
    Logger logger = Logger.getLogger(getClass().getName()); // Used instead of printing to stderr.
    private List<String[]> professorData; // The list of professors. An entry in the professor
                                               // list should always formatted as 
                                               // {professorName, professorID}.

    /**
     * Constructs a {@code ProfessorList} object.
     */
    public ProfessorList() {
        professorData = new ArrayList<>();
    } // ProfessorList

    /**
     * Constructs a {@code ProfessorList} object with any amount of professor entries 
     * specified as parameters.
     *
     * @param professorEntry an entry for one professor, formatted {professorName, professorID}.
     */
    public ProfessorList(String[]... professorEntry) {
        professorData = new ArrayList<>();
        // If no entries are specified, do not add anything to the list.
        if (professorEntry.length == 0) {
            return;
        } // if
        for (String[] professor : professorEntry) {
            if (professor.length == 2) {
                professorData.add(professor);
            } else {
                logger.info("Invalid entry detected.");
                return;
            } // if
        } // if
    } // ProfessorList

    /**
     * Constructs a {@code ProfessorList} object with a pre-made list specified as a parameter.
     * 
     * @param professorData A list of professor entries, formatted {professorName, professorID}.
     */
    public ProfessorList(List<String[]> professorData) {
        this.professorData = professorData;
    } // ProfessorList
    
    /**
     * Returns a professor entry with a matching name to the {@code professorName} parameter.
     * Returns an empty array if there is no professor matching the specified {@code professorName}.
     * 
     * @param professorName The name of the professor.
     * @return The entry for the professor.
     */
    public String[] getProfessorWithName(String professorName) {
        for (String[] professor : professorData) {
            if (professor[0].equals(professorName)) {
                return professor;
            } // if
        } // for
        // Return an empty array if the professor was not found
        return new String[2];
    } // getProfessorEntryWithName

    /**
     * Returns a professor entry with a matching name to the {@code professorID} parameter.
     * Returns an empty array if there is no entry matching the specified {@code professorID}.
     * 
     * @param professorID The name of the professor.
     * @return The entry for the professor.
     */
    public String[] getProfessorWithID(String professorID) {
        for (String[] entry : professorData) {
            if (entry[1].equals(professorID)) {
                return entry;
            } // if
        } // for
        // Return an empty array if the professor was not found
        return new String[2];
    } // getProfessorEntryWithID

    /**
     * Returns true if the specified professor is in the list, or false otherwise.
     * 
     * @param professor The professor to search for, formatted {professorName, professorID}.
     * @return True if the professor is in the list, or false if the professor is not in the list.
     */
    public boolean findProfessor(String[] professor) {
        for (String[] entry : professorData) {
            if (Arrays.equals(entry, professor)) {
                return true;
            } // if
        } // for
        return false;
    } // findProfessor

    /**
     * Adds the specified professor to the {@code professorData}.
     * 
     * @param professor an entry for one professor, formatted {professorName, professorID}.
     */
    public void addProfessor(String[]... professors) {
        for (String[] professor : professors) {
            if (professor.length == 2) {
                professorData.add(professor);
            } else {
                logger.info("Invalid entry detected.");
            } // if
        }
    } // addProfessor
} // ProfessorList
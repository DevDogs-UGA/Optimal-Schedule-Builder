package edu.uga.devdogs.professor_rating.webscraping;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.logging.Level;

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
                logger.log(Level.INFO, "Invalid entry detected.");
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
     * Return the entire list of professors.
     * 
     * @return the List that holds all of the professor entries.
     */
    public List<String[]> getProfessorList() {
        return professorData;
    } // getProfessorList
    
    /**
     * Returns a professor entry with a matching name to the {@code professorName} parameter.
     * Returns an empty array if there is no professor matching the specified {@code professorName}.
     * 
     * @param professorName The name of the professor.
     * @return The entry for the professor.
     */
    public String[] getProfessorByName(String professorName) {
        for (String[] professor : professorData) {
            if (professor[0].equals(professorName)) {
                return professor;
            } // if
        } // for
        // Return an empty array if the professor was not found
        return new String[2];
    } // getProfessorEntryByName

    /**
     * Returns a professor entry with a matching name to the {@code professorID} parameter.
     * Returns an empty array if there is no entry matching the specified {@code professorID}.
     * 
     * @param professorID The name of the professor as a String.
     * @return The entry for the professor.
     */
    public String[] getProfessorByID(String professorID) {
        for (String[] entry : professorData) {
            if (entry[1].equals(professorID)) {
                return entry;
            } // if
        } // for
        // Return an empty array if the professor was not found
        return new String[2];
    } // getProfessorEntryByID

    /**
     * Returns a professor entry with a matching name to the {@code professorID} parameter.
     * Returns an empty array if there is no entry matching the specified {@code professorID}.
     * This version of the method accepts an Integer instead of a String.
     * 
     * @param professorID The name of the professor as an Integer.
     * @return The entry for the professor.
     */
    public String[] getProfessorByID(int professorID) {
        String professorIDString = Integer.toString(professorID);
        for (String[] entry : professorData) {
            if (entry[1].equals(professorIDString)) {
                return entry;
            } // if
        } // for
        // Return an empty array if the professor was not found
        return new String[2];
    } // getProfessorEntryByID

    /**
     * Checks if the specified professor is in the list. Returns true if the specified professor 
     * is in the list, or false otherwise. Professors should be formatted as 
     * {professorName, professorID}.
     * 
     * @param professor The professor to search for, formatted {professorName, professorID}.
     * @return True if the professor is in the list, or false if the professor is not in the list.
     */
    public boolean isProfessorInList(String[] professor) {
        for (String[] entry : professorData) {
            if (Arrays.equals(entry, professor)) {
                return true;
            } // if
        } // for
        return false;
    } // isProfessorInList

    /**
     * Adds the specified professor to the {@code professorData}.
     * 
     * @param professor an entry for one professor, formatted {professorName, professorID}.
     */
    public void addProfessor(String[]... professors) {
        for (String[] entry : professors) {
            if (entry.length == 2) {
                professorData.add(entry);
            } else {
                logger.log(Level.INFO, "Invalid entry detected.");
            } // if
        } // for
    } // addProfessor

    /**
     * Removes the professor whose name is specified, if it exists. Returns the removed
     * professor if it was removed, or an empty array if it was not found.
     * 
     * @param professorName The professor to be removed.
     * @return the professor that was removed (formatted {professorName, professorID}) if it was
     * removed, otherwise an empty array.
     */
    public String[] removeProfessorByName(String professorName) {
        for (int i = 0; i < professorData.size(); i++) {
            String[] entry = professorData.get(i);
            if (entry[0].equals(professorName)) {
                professorData.remove(i);
                return entry;
            } // if
        } // for
        // If a professor with the specified ID was not found.
        logger.log(Level.INFO, "Professor with name {0} not found.", professorName);
        return new String[2];
    } // removeProfessorByName

    /**
     * Removes the professor whose ID is specified, if it exists. Returns the removed
     * professor if it was removed, or an empty array if it was not found.
     * 
     * @param professorID the name of the professor as a String.
     * @return the professor that was removed (formatted {professorName, professorID}) if it was
     * removed, otherwise an empty array.
     */
    public String[] removeProfessorByID(String professorID) {
        for (int i = 0; i < professorData.size(); i++) {
            String[] entry = professorData.get(i);
            if (entry[1].equals(professorID)) {
                professorData.remove(i);
                return entry;
            } // if
        } // for
        // If a professor with the specified ID was not found.
        logger.log(Level.INFO, "Professor with ID {0} not found.", professorID);
        return new String[2];
    } // removeProfessorByID

    /**
     * Removes the professor whose ID is specified, if it exists. Returns the removed
     * professor if it was removed, or an empty array if it was not found.
     * This version of the method accepts an Integer instead of a String.
     * 
     * @param professorID the name of the professor as an Integer.
     * @return the professor that was removed (formatted {professorName, professorID}) if it was
     * removed, otherwise an empty array.
     */
    public String[] removeProfessorByID(int professorID) {
        String professorIDString = Integer.toString(professorID);
        for (int i = 0; i < professorData.size(); i++) {
            String[] entry = professorData.get(i);
            if (entry[1].equals(professorIDString)) {
                professorData.remove(i);
                return entry;
            } // if
        } // for
        // If a professor with the specified ID was not found.
        logger.log(Level.INFO, "Professor with ID {0} not found.", professorID);
        return new String[2];
    } // removeProfessorByID
} // ProfessorList
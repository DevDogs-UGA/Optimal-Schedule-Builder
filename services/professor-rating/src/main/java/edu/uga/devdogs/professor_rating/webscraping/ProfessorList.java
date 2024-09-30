package edu.uga.devdogs.professor_rating.webscraping;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Yuval Deshe
 * 
 * Represents the list of all professors at UGA on the RateMyProfessor website.
 */
public class ProfessorList {
    private ArrayList<String[]> professorList; // The list of professors. An entry in the professor
                                               // list should always formatted as 
                                               // {professorName, professorID}.

    /**
     * Constructs a {@code ProfessorList} object.
     */
    public ProfessorList() {
        professorList = new ArrayList<String[]>();
    } // ProfessorList

    /**
     * Constructs a {@code ProfessorList} object with any amount of professor entries 
     * specified as parameters.
     *
     * @param professorEntry an entry for one professor, formatted {professorName, professorID}.
     */
    public ProfessorList(String[]... professorEntry) {
        professorList = new ArrayList<String[]>();
        // If no entries are specified, do not add anything to the list.
        if (professorEntry.length == 0) {
            return;
        } // if
        for (String[] professor : professorEntry) {
            if (professor.length == 2) {
                professorList.add(professor);
            } else {
                System.out.println("Invalid entry detected.");
                return;
            } // if
        } // if
    } // ProfessorList

    /**
     * Constructs a {@code ProfessorList} object with a pre-made list specified as a parameter.
     * 
     * @param professorList A list of professor entries, formatted {professorName, professorID}.
     */
    public ProfessorList(ArrayList<String[]> professorList) {
        this.professorList = professorList;
    } // ProfessorList
    
    /**
     * Returns a professor entry with a matching name to the {@code professorName} parameter.
     * Returns null if there is no professor matching the specified {@code professorName}.
     * 
     * @param professorName The name of the professor.
     * @return The entry for the professor.
     */
    public String[] getProfessorWithName(String professorName) {
        for (String[] professor : professorList) {
            if (professor[0].equals(professorName)) {
                return professor;
            } // if
        } // for
        return null;
    } // getProfessorEntryWithName

    /**
     * Returns a professor entry with a matching name to the {@code professorID} parameter.
     * Returns null if there is no entry matching the specified {@code professorID}.
     * 
     * @param professorID The name of the professor.
     * @return The entry for the professor.
     */
    public String[] getProfessorWithID(String professorID) {
        for (String[] entry : professorList) {
            if (entry[1].equals(professorID)) {
                return entry;
            } // if
        } // for
        return null;
    } // getProfessorEntryWithID

    /**
     * Returns true if the specified professor is in the list, or false otherwise.
     * 
     * @param professor The professor to search for, formatted {professorName, professorID}.
     * @return True if the professor is in the list, or false if the professor is not in the list.
     */
    public boolean findProfessor(String[] professor) {
        for (String[] entry : professorList) {
            if (Arrays.equals(entry, professor)) {
                return true;
            } // if
        } // for
        return false;
    } // findProfessor

    /**
     * Adds the specified professor to the {@code professorList}.
     * 
     * @param professor an entry for one professor, formatted {professorName, professorID}.
     */
    public void addProfessor(String[]... professors) {
        for (String[] professor : professors) {
            if (professor.length == 2) {
                professorList.add(professor);
            } else {
                System.out.println("Invalid entry detected.");
            } // if
        }
    } // addProfessor
} // ProfessorList
package edu.uga.devdogs;

import com.google.gson.JsonDeserializer;

import java.time.LocalTime;

public record Course(String courseCode, Section[] sections) {

    public record Section(int CRN, Professor professor, Class[] classes) {

        public record Class(String[] days, LocalTime start, LocalTime end, String buildingName) { }

    }

}

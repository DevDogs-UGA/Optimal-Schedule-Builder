package edu.uga.devdogs.records;

import com.google.gson.annotations.SerializedName;

import java.time.LocalTime;

public record Course(String courseCode, Section[] sections) {

    public record Section(
            String crn,
            @SerializedName("professor_name") Professor professor,
            Class[] classes) {

        public record Class(String[] days, LocalTime startTime, LocalTime endTime, String buildingName) { }

    }

}

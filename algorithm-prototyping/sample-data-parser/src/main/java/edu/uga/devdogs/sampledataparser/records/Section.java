package edu.uga.devdogs.sampledataparser.records;

import com.google.gson.annotations.SerializedName;

public record Section(String courseCode,
                      String crn,
                      @SerializedName("professor_name") Professor professor,
                      Class[] classes) {
}

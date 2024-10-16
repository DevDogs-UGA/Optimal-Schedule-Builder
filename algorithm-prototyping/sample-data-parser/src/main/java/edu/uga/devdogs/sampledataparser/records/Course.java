package edu.uga.devdogs.sampledataparser.records;

public record Course(String courseCode,
                     Section[] sections) {
}

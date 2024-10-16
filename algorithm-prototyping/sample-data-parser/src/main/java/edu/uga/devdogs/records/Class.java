package edu.uga.devdogs.records;

import java.time.LocalTime;

public record Class(String[] days, LocalTime startTime, LocalTime endTime, String buildingName) { }

package edu.uga.devdogs.records;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record Class(DayOfWeek[] days, LocalTime startTime, LocalTime endTime, String buildingName) { }

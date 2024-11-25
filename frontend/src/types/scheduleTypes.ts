import { useState } from "react";

// TODO: Create an ARRAY of start and end times for each day. 

interface ClassData {
  classTitle: string;
  className: string;
  description: string;
  location: string;
  timeStart: string;
  timeEnd: string;
  timeDifference: number | null; // Optional, can be number or null
  professor: string;
  semester: string;
  credits: number;
  crn: number;
  color: string;
}

type DaySchedule = Record<string, ClassData[]>;

type WeekSchedule = Record<string, ClassData[]>;

export type { ClassData, DaySchedule, WeekSchedule };

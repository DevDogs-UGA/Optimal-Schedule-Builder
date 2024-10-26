interface ClassData {
  classTitle: string;
  location?: string;
  timeStart: string;
  timeEnd: string;
  timeDifference?: number | null; // Optional, can be number or null
  bgColor: string;
}

interface DaySchedule {
  [key: string]: ClassData[];
}

interface WeekSchedule {
  [day: string]: ClassData[];
}

export type { ClassData, DaySchedule, WeekSchedule };

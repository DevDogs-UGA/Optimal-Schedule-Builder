interface ClassData {
  classTitle: string;
  location?: string;
  timeStart: string;
  timeEnd: string;
  bgColor: string;
}

interface DaySchedule {
  [key: string]: ClassData[];
}

interface WeekSchedule {
  [day: string]: ClassData[];
}

export type { ClassData, DaySchedule, WeekSchedule };

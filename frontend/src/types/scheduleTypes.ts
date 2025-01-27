

interface ClassData {
  classTitle: string;
  className: string;
  description: string;
  locationLong: string;
  locationShort: string;
  prereq: string;
  coreq: string;
  professor: string;
  semester: string;

  credits: number;
  crn: number;
  openSeats: number;
  maxSeats: number;
  waitlist: number;

  bgColor: string;
  borderColor: string;

  timeStart: string;
  timeEnd: string;
  timeDifference: number | null; // Optional, can be number or null

  currentDay: string;
  otherTimes: [string, string, string]; // Used to support classes with two or more locations/times
}

type DaySchedule = Record<string, ClassData[]>;

type WeekSchedule = Record<string, ClassData[]>;

export type { ClassData, DaySchedule, WeekSchedule };

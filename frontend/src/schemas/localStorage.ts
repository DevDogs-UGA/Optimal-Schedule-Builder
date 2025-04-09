import { dummyData1, dummyData2 } from "@/components/schedules/dummySchedules";
import * as z from "zod";

/**
 * The default export must be a record of Zod object schemas whose
 * keys/values are JSON-serializable (i.e., records/arrays of strings,
 * numbers, booleans, and nulls). Further, each must have either a
 * default or be nullable.
 */

/**
 * Using `.catch(...)` on the schema gives a default value if there
 * is an error parsing the value in localStorage (or if the key is
 * not present in localStorage). The default value passed to
 * `.catch(...)` must match the original schema.
 */

/**
 * Using `.nullable()` is identical in behavior to `.catch(null)`,
 * except the schema does not necessarily have to match null.
 */

const ClassData = z.object({
  classTitle: z.string(),
  className: z.string(),
  description: z.string(),
  locationLong: z.string(),
  locationShort: z.string(),
  prereq: z.string(),
  coreq: z.string(),
  professor: z.string(),
  semester: z.string(),

  credits: z.number(),
  crn: z.number(),
  openSeats: z.number(),
  maxSeats: z.number(),
  waitlist: z.number(),

  bgColor: z.string(),
  borderColor: z.string(),

  timeStart: z.string(),
  timeEnd: z.string(),
  timeDifference: z.number().nullable(),

  currentDay: z.string(),
  otherTimes: z.tuple([z.string(), z.string(), z.string()]),
});

const WeekSchedule = z.record(z.string(), ClassData.array());
const DaySchedule = z.record(z.string(), ClassData.array());

const localStorage = {
  schedules: z.object({
    id: z.string().uuid(),
    title: z.string(),
    data: WeekSchedule,
    pinned: z.boolean(),
  }).array().catch([
    // Remove these objects when we have generated schedules getting auto-saved to local storage
    {
      id: crypto.randomUUID(),
      title: "Schedule 1",
      data: dummyData1,
      pinned: false,
    },
    {
      id: crypto.randomUUID(),
      title: "Schedule 2",
      data: dummyData2,
      pinned: false,
    }
  ])
};

export type SavedPlan = z.infer<typeof localStorage["schedules"]>[number];
export type ClassData = z.infer<typeof ClassData>;
export type DaySchedule = z.infer<typeof DaySchedule>;
export type WeekSchedule = z.infer<typeof WeekSchedule>;
export default localStorage;

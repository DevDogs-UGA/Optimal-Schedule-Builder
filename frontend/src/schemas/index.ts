import * as z from "zod";

export const Building = z.object({
  buildingNumber: z.number().int(),
  name: z.string(),
  grid: z.string(),
  classes: z.unknown().array(), // TODO: This field represents a join of potentially-circular references
});

export const Class = z.object({
  classId: z.number().int(),
  days: z.string(),
  startTime: z.string().datetime(),
  endTime: z.string().datetime(),
  room: z.string(),
  campus: z.string(),
  courseSection: z.unknown(), // TODO: This field represents a join of potentially-circular references
  building: z.unknown(), // TODO: This field represents a join of potentially-circular references
});

export const CourseSection = z.object({
  courseSectionId: z.number().int(),
  crn: z.number().int(),
  sec: z.number().int(),
  stat: z.string().length(1),
  creditHoursLow: z.number(),
  creditHoursHigh: z.number(),
  instructor: z.string(),
  term: z.number().int(),
  classSize: z.number().int(),
  seatsAvailable: z.number().int(),
  year: z.number().int(),
  course: z.unknown(), // TODO: This field represents a join of potentially-circular references
  class: z.unknown(), // TODO: This field represents a join of potentially-circular references
});

export const Course = z.object({
  courseId: z.number().int(),
  subject: z.string(),
  courseNumber: z.string(),
  title: z.string(),
  department: z.string(),
  courseSections: z.unknown().array(), // TODO: This field represents a join of potentially-circular references
  courseDescription: z.string(),
  athenaTitle: z.string(),
  equivalentCourses: z.unknown().array(), // TODO: This field represents a join of potentially-circular references
  prerequisiteCourses: z.unknown().array(), // TODO: This field represents a join of potentially-circular references
  semesterCourseOffered: z.string(),
  gradingSystem: z.string(),
});

export interface RouteMap<Queries extends string> {
  endpoint: string;
  queries: Record<
    Queries,
    {
      route: string;
      params:
        | z.AnyZodObject
        | z.ZodUnion<[z.AnyZodObject, ...z.AnyZodObject[]]>;
      result: z.ZodTypeAny;
    }
  >;
}

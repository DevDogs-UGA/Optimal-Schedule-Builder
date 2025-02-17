import * as z from "zod";
import { Building, Course, CourseSection } from ".";

/**
 * At some point in the future, most/all of this could could be
 * programatically generated using the swagger.json or OpenAPI
 * docs, but the services code needs to be in a more complete
 * state for that to be s=possible.
 */

/**
 * @see https://docs.google.com/document/d/1UCqmfoyiv9WarZpkEv7axqQeUps-_FyH7HGqdeL9mx8/edit?tab=t.0#heading=h.rx2mk3uqskyb
 */
export const bulletin = {
  endpoint: "https://base.url.for.bulletin.service/",
  queries: {
    /**
     * Searches for all courses matching the provided term.
     */
    getCoursesByTerm: {
      route: "/term",
      params: z.object({
        /**
         * @param term The name of the term to search for courses in.
         */
        term: z.string(),
      }),
      result: Course.array(),
    },
    getCourseInfo: {
      route: "/getCourseById",
      params: z.object({
        /**
         * @param courseId The ID of the course to search for.
         */
        courseId: z.string(),
      }),
      result: Course.nullable(),
    },
    /**
     * Retrieves an array of courses based on the specified parameters.
     */
    getCourses: {
      route: "/courses",
      params: z.object({
        /**
         * @param creditHours The required number of credit hours for the courses.
         */
        creditHours: z.number().transform(String),
        /**
         * @param majorCode The major code, e.g. "CSCI" (Optional)
         */
        majorCode: z.string().optional(),
        /**
         * @param majorCode The class level, e.g. 4000 (Optional)
         */
        classLevel: z.number().optional().transform(String),
      }),
      result: Course.array(),
    },
    /**
     * Retrieves pre-requisite courses for a given course ID or CRN.
     */
    getPreReqs: {
      route: "/course/prereqs",
      params: z.union([
        z.object({
          /**
           * @param courseId The id of the course to retrieve
           */
          courseId: z.string(),
        }),
        z.object({
          /**
           * @param crn The CRN of the course to retrieve
           */
          crn: z.string(),
        }),
      ]),
      result: Course.array(),
    },
    /**
     * Retrieves co-requisite courses for a given course ID or CRN.
     */
    getCoReqs: {
      route: "/course/coreqs",
      params: z.union([
        z.object({
          /**
           * @param courseId The id of the course to retrieve
           */
          courseId: z.string(),
        }),
        z.object({
          /**
           * @param crn The CRN of the course to retrieve
           */
          crn: z.string(),
        }),
      ]),
      result: Course.array(),
    },
    /**
     * Gets the special types of a course (honors/lab/online) from a CRN.
     */
    getSpecialCourseTypesFromCRN: {
      route: "/course/specialCourseTypes",
      params: z.object({
        /**
         * @param crn The CRN of the course to find the special types.
         */
        crn: z.string(),
      }),
      result: z.string().array(),
    },
    /**
     * Queries course sections from class time and optionally class name.
     */
    getCourseSections: {
      route: "/course/sections",
      params: z.object({
        /**
         * @param timeSlot The timeslot range to use for this (10:00 AM - 11:15 AM).
         */
        timeSlot: z
          .string()
          .regex(
            /(0|1)?\d:\d{2}\s+[ap]\.?(m\.?)?\s-\s(0|1)?\d:\d{2}\s+[ap]\.?(m\.?)?/gi,
          ),
        /**
         * @param crn The crn course to retrieve course sections (optional).
         */
        crn: z.string().optional(),
      }),
      result: CourseSection.array(),
    },
    /**
     * Retrieves a list of sections that matches the requirements given.
     */
    getRequirementCourses: {
      route: "/requirement",
      params: z.object({
        /**
         * @param requirement The string name for a requirement
         */
        requirement: z.string(),
      }),
      result: CourseSection.array(),
    },
    /**
     * Retrieves a list of all academic subjects.
     */
    getAllSubjects: {
      route: "/subjects",
      params: z.object({}),
      result: z.string().array(),
    },
  },
};

/**
 * @see https://docs.google.com/document/d/1UCqmfoyiv9WarZpkEv7axqQeUps-_FyH7HGqdeL9mx8/edit?tab=t.wy7qsyqhjgu6#heading=h.cazkc7a4f09t
 */
export const courseInformation = {
  endpoint: "https://base.url.for.course-information.service/",
  queries: {
    /**
     * Searches for all course sections whose instructor matches the provided name.
     */
    getCoursesByProfessor: {
      route: "/professor",
      params: z.object({
        /**
         * @param professor Name of the instructor to search for.
         */
        professor: z.string(),
      }),
      result: CourseSection.array(),
    },
    /**
     * Retrieves an array of courses for a given major.
     */
    getCoursesByMajor: {
      route: "/coursesByMajor",
      params: z.object({
        /**
         * @param major The major identifier for which to select courses (e.g. CSCI)
         */
      }),
      result: Course.array(),
    },
    /**
     * Gets the details of a specified CRN
     */
    getCourseEntity: {
      route: "/section-by-crn",
      params: z.object({
        /**
         * @param crn The CRN of the section
         */
        crn: z.string(),
      }),
      result: CourseSection.array(),
    },
    /**
     * Retrieves course information based on Athena name.
     */
    getCourseByAthenaName: {
      route: "/course-by-athena-name",
      params: z.object({
        /**
         * @param athenaName The Athena name of the course
         */
        athenaName: z.string(),
      }),
      result: Course.array(),
    },
    /**
     * Gets all buildings
     */
    getAllBuildings: {
      route: "/buildings",
      params: z.object({}),
      result: Building.array(),
    },
  },
};

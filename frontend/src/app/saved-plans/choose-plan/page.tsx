"use client";
// Returning User: -> Schedule View (\schedules)
// New User: -> Schedule View (\schedules)

// make class blocks clickable to access more
import { Navbar } from "@/components/Navbar";
import { Button } from "@/components/ui/Button";
import { type WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import SavedPlan from "@/components/saved-plans/SavedPlan";

export default function ChoosePlan() {
  // Unused dummy data, uncomment to use
  /*
  const savedPlansList = [
    {
      href: "/schedules",
      text: "Plan 1",
    },
    {
      href: "/saved-plans",
      text: "Plan 2",
    },
  ];
  */

  const weekScheduleData: WeekScheduleType = {
    Monday: [
      {
        classTitle: "CSCI 3030",
        className: "Computing, Ethics, and Society",
        description:
          "Introduction to social and ethical issues relating to computer science and information technology. Topics include privacy, intellectual property, open-source software, the digital divide, globalization, professional ethics, social justice issues, and current events. Students should have a working knowledge of personal computing.",
        locationLong: "Boyd Research and Education Center 100",
        locationShort: "Boyd 100",
        prereq: "ENGL 1102",
        coreq: "",
        professor: "John Doe",
        semester: "Spring",
        timeStart: "8:00 am",
        timeEnd: "8:50 am",
        timeDifference: null,
        credits: 3,
        crn: 26510,
        openSeats: 100,
        maxSeats: 100,
        waitlist: 0,
        bgColor: "bg-bulldog-red",
        borderColor: "border-bulldog-red",
        currentDay: "MW",
        otherTimes: ["F", "8:00 am - 8:50 am", "Chemistry 100"],
      },

      {
        classTitle: "CSCI 1302",
        className: "Software Development",
        description: "The course description will be displayed here.",
        locationLong: "Conner Hall 100",
        locationShort: "Conner 100",
        prereq: "ENGL 1102",
        coreq: "",
        professor: "John Doe",
        semester: "All",
        timeStart: "10:20 am",
        timeEnd: "11:10 am",
        timeDifference: null,
        credits: 4,
        crn: 26510,
        openSeats: 100,
        maxSeats: 100,
        waitlist: 0,
        bgColor: "bg-bulldog-red",
        borderColor: "border-bulldog-red",
        currentDay: "M",
        otherTimes: ["TR", "9:35 am - 10:50 am", "Food Sci 100"],
      },

      {
        classTitle: "BIOL 1103",
        className: "Topics in Biology",
        description: "The course description will be displayed here.",
        locationLong: "Science Learning Center 100",
        locationShort: "SLC 100",
        prereq: "",
        coreq: "BIOL 1103L",
        professor: "Jane Doe",
        semester: "Spring/Fall",
        timeStart: "1:30 pm",
        timeEnd: "2:20 pm",
        timeDifference: null,
        credits: 3,
        crn: 26510,
        openSeats: 100,
        maxSeats: 100,
        waitlist: 0,
        bgColor: "bg-dev-dog-blue",
        borderColor: "border-dev-dog-blue",

        currentDay: "MWF",
        otherTimes: ["", "", ""],
      },
    ],

    Tuesday: [
      {
        classTitle: "CSCI 1302",
        className: "Software Development",
        description: "The course description will be displayed here.",
        locationLong: "Food Science 100",
        locationShort: "Food Sci 100",
        prereq: "ENGL 1102",
        coreq: "",
        professor: "John Doe",
        semester: "All",
        timeStart: "9:35 am",
        timeEnd: "10:50 am",
        timeDifference: null,
        credits: 4,
        crn: 26510,
        openSeats: 100,
        maxSeats: 100,
        waitlist: 0,
        bgColor: "bg-bulldog-red",
        borderColor: "border-bulldog-red",
        currentDay: "TR",
        otherTimes: ["M", "10:20 am - 11:10 am", "Conner 100"],
      },

      {
        classTitle: "MUSI 2300",
        className: "Music in Athens",
        description:
          "Explores national and international musical and cultural trends. The course draws from Athens, Georgia’s diverse musical communities and their histories, exploring the rock, hip hop, and indie popular music scenes along with African-American traditions, folk and traditional musics, and the emergent Latin music scene.",
        locationLong: "Hugh Hudgson School of Music 100",
        locationShort: "Hugh Hudgson 100",
        prereq: "",
        coreq: "",
        professor: "Jane Doe",
        semester: "Spring",
        timeStart: "12:45 pm",
        timeEnd: "2:00 pm",
        timeDifference: null,
        credits: 3,
        crn: 26510,
        openSeats: 100,
        maxSeats: 100,
        waitlist: 0,
        bgColor: "bg-lake-herrick",
        borderColor: "border-lake-herrick",
        currentDay: "TR",
        otherTimes: ["", "", ""],
      },
    ],

    Wednesday: [
      {
        classTitle: "CSCI 3030",
        className: "Computing, Ethics, and Society",
        description: "The course description will be displayed here.",
        locationLong: "Boyd Research and Education Center 100",
        locationShort: "Boyd 100",
        prereq: "ENGL 1102",
        coreq: "",
        professor: "John Doe",
        semester: "Spring",
        timeStart: "8:00 am",
        timeEnd: "8:50 am",
        timeDifference: null,
        credits: 3,
        crn: 26510,
        openSeats: 100,
        maxSeats: 100,
        waitlist: 0,
        bgColor: "bg-bulldog-red",
        borderColor: "border-bulldog-red",
        currentDay: "MW",
        otherTimes: ["F", "8:00 am - 8:50 am", "Chemistry 100"],
      },

      {
        classTitle: "BIOL 1103",
        className: "Topics in Biology",
        description: "The course description will be displayed here.",
        locationLong: "Science Learning Center 100",
        locationShort: "SLC 100",
        prereq: "",
        coreq: "BIOL 1103L",
        professor: "Jane Doe",
        semester: "Spring/Fall",
        timeStart: "1:30 pm",
        timeEnd: "2:20 pm",
        timeDifference: null,
        credits: 3,
        crn: 26510,
        openSeats: 100,
        maxSeats: 100,
        waitlist: 0,
        bgColor: "bg-dev-dog-blue",
        borderColor: "border-dev-dog-blue",
        currentDay: "MWF",
        otherTimes: ["", "", ""],
      },
    ],

    Thursday: [
      {
        classTitle: "CSCI 1302",
        className: "Software Development",
        description: "The course description will be displayed here.",
        locationLong: "Food Science 100",
        locationShort: "Food Sci 100",
        prereq: "ENGL 1102",
        coreq: "",
        professor: "John Doe",
        semester: "Spring",
        timeStart: "9:35 am",
        timeEnd: "10:50 am",
        timeDifference: null,
        credits: 4,
        crn: 26510,
        openSeats: 100,
        maxSeats: 100,
        waitlist: 0,
        bgColor: "bg-bulldog-red",
        borderColor: "border-bulldog-red",
        currentDay: "TR",
        otherTimes: ["M", "10:20 am - 11:10 am", "Conner 100"],
      },

      {
        classTitle: "MUSI 2300",
        className: "Music in Athens",
        description: "The course description will be displayed here.",
        locationLong: "Hugh Hudgson School of Music 100",
        locationShort: "Hugh Hudgson 100",
        prereq: "ENGL 1102",
        coreq: "",
        professor: "Jane Doe",
        semester: "Spring",
        timeStart: "12:45 pm",
        timeEnd: "2:00 pm",
        timeDifference: null,
        credits: 3,
        crn: 26510,
        openSeats: 100,
        maxSeats: 100,
        waitlist: 0,
        bgColor: "bg-lake-herrick",
        borderColor: "border-lake-herrick",
        currentDay: "TR",
        otherTimes: ["", "", ""],
      },

      {
        classTitle: "BIOL 1103L",
        className: "Concepts in Biology Laboratory",
        description: "The course description will be displayed here.",
        locationLong: "Science Learning Center 100",
        locationShort: "SLC 100",
        prereq: "",
        coreq: "BIOL 1103",
        professor: "Jane Doe",
        semester: "Spring/Fall",
        timeStart: "2:20 pm",
        timeEnd: "4:15 pm",
        timeDifference: null,
        credits: 1,
        crn: 26510,
        openSeats: 100,
        maxSeats: 100,
        waitlist: 0,
        bgColor: "bg-dev-dog-blue",
        borderColor: "border-dev-dog-blue",
        currentDay: "R",
        otherTimes: ["", "", ""],
      },
    ],

    Friday: [
      {
        classTitle: "CSCI 3030",
        className: "Computing, Ethics, and Society",
        description: "The course description will be displayed here.",
        locationLong: "Boyd Research and Education Center 100",
        locationShort: "Chemistry 100",
        prereq: "ENGL 1102",
        coreq: "",
        professor: "John Doe",
        semester: "Spring",
        timeStart: "8:00 am",
        timeEnd: "8:50 am",
        timeDifference: null,
        credits: 3,
        crn: 26510,
        openSeats: 100,
        maxSeats: 100,
        waitlist: 0,
        bgColor: "bg-bulldog-red",
        borderColor: "border-bulldog-red",
        currentDay: "F",
        otherTimes: ["MW", "8:00 - 8:50 am", "Boyd 100"],
      },

      {
        classTitle: "BIOL 1103",
        className: "Topics in Biology",
        description: "The course description will be displayed here.",
        locationLong: "Science Learning Center 100",
        locationShort: "SLC 100",
        prereq: "",
        coreq: "BIOL 1103L",
        professor: "Jane Doe",
        semester: "Spring/Fall",
        timeStart: "1:30 pm",
        timeEnd: "2:20 pm",
        timeDifference: null,
        credits: 3,
        crn: 26510,
        openSeats: 100,
        maxSeats: 100,
        waitlist: 0,
        bgColor: "bg-dev-dog-blue",
        borderColor: "border-dev-dog-blue",
        currentDay: "MWF",
        otherTimes: ["", "", ""],
      },
    ],

    // Add more days as needed
  };

  return (
    <div className="min-h-screen">
      <Navbar />
      {/* Options for creating and modifying saved plans */}
      <div className="mt-12 flex flex-row flex-nowrap justify-between gap-1 text-center">
        <h1 className="ml-8 text-2xl font-bold">
          Select a plan or create a new one.
        </h1>
        <div className="mr-8 flex flex-row gap-1">
          <Button
            className="m-2 rounded-lg border-2 border-nearly-black bg-bulldog-red px-4 py-2 font-semibold text-black hover:bg-glory-glory-red"
            text="Delete"
          ></Button>
          <Button
            className="m-2 rounded-lg border-2 border-pebble-gray bg-dusty-pink px-4 py-2 font-semibold text-black hover:bg-barely-pink"
            text="Edit"
          ></Button>
          <Button
            className="m-2 rounded-lg border-2 border-pebble-gray bg-dusty-pink px-4 py-2 font-semibold text-black hover:bg-barely-pink"
            text="Save"
          ></Button>
          <Button
            className="m-2 rounded-lg border-2 border-pebble-gray bg-dusty-pink px-4 py-2 font-semibold text-black hover:bg-barely-pink"
            text="Create"
          ></Button>
        </div>
      </div>

      {/* List of saved plans (placeholder list of 2): We can add more when the saving functionality is actually implemented.
      Should this be an array? */}
      <div className="mt-10 flex flex-col flex-nowrap items-center justify-center gap-1">
        <SavedPlan
          className="onClick={selectSavedPlan} m-10 h-[20vh] w-[80vw] !rounded-3xl border-2 border-pebble-gray bg-dusty-pink text-3xl font-semibold text-black hover:bg-barely-pink"
          planTitle="Plan 1"
          plan={weekScheduleData}
        ></SavedPlan>
        <SavedPlan
          className="m-10 h-[20vh] w-[80vw] !rounded-3xl border-2 border-pebble-gray bg-dusty-pink text-3xl font-semibold text-black hover:bg-barely-pink"
          planTitle="Plan 2"
          plan={weekScheduleData}
        ></SavedPlan>
      </div>
    </div>
  );
}

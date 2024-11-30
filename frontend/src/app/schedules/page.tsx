// Returning User: -> Saved Plans
// New User: -> Saved Plans

import { type WeekSchedule as WeekScheduleType } from "../../types/scheduleTypes";
import WeekSchedule from "@/components/schedules/WeekSchedule";
import Link from "next/link";

export default function SchedulePage() {
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
        bgColor: "bulldog-red",

        currentDay: "MW",
        otherTimes: ["F", "8:00 - 8:50 am", "Chemistry 100"],
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
        bgColor: "bulldog-red",

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
        bgColor: "lake-herrick",

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
        bgColor: "bulldog-red",

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
        bgColor: "lake-herrick",

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
        bgColor: "bulldog-red",

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
        bgColor: "lake-herrick",

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
        bgColor: "bulldog-red",

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
        bgColor: "lake-herrick",

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
        bgColor: "lake-herrick",

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
        bgColor: "bulldog-red",

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
        bgColor: "bulldog-red",

        currentDay: "MWF",
        otherTimes: ["", "", ""],
      },
    ],

    // Add more days as needed
  };

  // Sorts the classes by start time
  Object.keys(weekScheduleData).forEach((day) => {
    weekScheduleData[day]?.sort((a, b) => {
      const timeA = new Date(`1970/01/01 ${a.timeStart}`); // Any date can be used since we're only comparing times
      const timeB = new Date(`1970/01/01 ${b.timeStart}`);
      return timeA.getTime() - timeB.getTime();
    });
  });

  return (
    <div className="mx-auto min-h-screen w-[100%]">
      <div className="p-7 text-center text-blue-600">
        <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
          <Link href={"/route-map"}>Route Map</Link>
        </button>
      </div>
      <WeekSchedule weekData={weekScheduleData} />
    </div>
  );
}

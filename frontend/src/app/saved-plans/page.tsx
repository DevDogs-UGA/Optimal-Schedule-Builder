"use client";
import {useEffect, useState} from 'react';
import { Navbar } from "@/components/Navbar";
import { type WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import SavedPlan from "@/components/saved-plans/SavedPlan";

// Dummy Schedule #1: Remove as necessary
// Be sure to update when the WeekSchedule component changes!
const dummySchedule: WeekScheduleType = {
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
}

// Dummy Schedule #2: Remove as necessary
// Be sure to update when the WeekSchedule component changes!
const dummySchedule2: WeekScheduleType = {
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
      timeStart: "1:30 pm",
      timeEnd: "2:20 pm",
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
      timeStart: "9:10 am",
      timeEnd: "10:00 am",
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
      timeStart: "1:30 pm",
      timeEnd: "2:20 pm",
      timeDifference: null,
      credits: 3,
      crn: 26510,
      openSeats: 100,
      maxSeats: 100,
      waitlist: 0,
      bgColor: "bg-bulldog-red",
      borderColor: "border-bulldog-red",
      currentDay: "MW",
      otherTimes: ["F", "1:30 pm - 2:20 pm", "Chemistry 100"],
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
      timeStart: "9:10 am",
      timeEnd: "10:00 am",
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
      timeStart: "1:30 pm",
      timeEnd: "2:20 pm",
      timeDifference: null,
      credits: 3,
      crn: 26510,
      openSeats: 100,
      maxSeats: 100,
      waitlist: 0,
      bgColor: "bg-bulldog-red",
      borderColor: "border-bulldog-red",
      currentDay: "F",
      otherTimes: ["MW", "1:30 pm - 2:20 pm", "Boyd 100"],
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
      timeStart: "9:10 am",
      timeEnd: "10:00 am",
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
}
// Handles retrieval of saved plans from local storage and displays a list of them for the user to select
export default function SavedPlans() {

  // Parameters for a saved plan
  interface SavedPlanType {
    title: string;
    data: WeekScheduleType;
  }

  const [savedPlans, setSavedPlans] = useState<SavedPlanType[]>([]);

  useEffect(() => {

    try {
      // Save the dummy schedules to browser local storage 
      console.log("Saving to local storage"); // for debugging
      localStorage.setItem('Schedule 1', JSON.stringify(dummySchedule));
      localStorage.setItem('Schedule 2', JSON.stringify(dummySchedule2));
      localStorage.setItem('Schedule 3', JSON.stringify(dummySchedule));
      localStorage.setItem('Schedule 4', JSON.stringify(dummySchedule2));
      localStorage.setItem('Schedule 5', JSON.stringify(dummySchedule));
      console.log("Schedules saved"); // for debugging

      // Retrieve schedules from local storage and populate the savedPlans array
      const plans = [];
      // Loop through local storage
      for (let i = 0; i < localStorage.length; i++) {
        const key = localStorage.key(i);
        // Retrieve key for local storage element
        if (key) {
          // Retrieve data matching key
          const data = localStorage.getItem(key);
          // Only proceed if the data looks like a WeekSchedule component
          if (data && data.startsWith('{"Monday"')) {
            // Parse the data from the JSON
            plans.push({
              title: key,
              data: JSON.parse(data)
          });
        }
      }
    }
    setSavedPlans(plans);
  } 
  catch (error) {
    console.error("Error", error);
  }
}, []);

    return (
      <div className="min-h-screen">
        <Navbar />

        <div className="flex flex-col flex-nowrap items-center bg-barely-pink border-black border-2 rounded-xl w-4/5 h-[85vh] ml-auto mr-auto mt-20 mb-10 overflow-y-auto">
          {savedPlans.map((plan,index) => (
            <SavedPlan
              key={index}
              planTitle={plan.title}
              plan={plan.data}
            />
          ))}
        </div>
      </div>
    ); 

  }

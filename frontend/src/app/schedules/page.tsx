import { type WeekSchedule as WeekScheduleType } from "../../types/scheduleTypes";
import WeekSchedule from "@/components/schedules/WeekSchedule";

export default function SchedulePage() {
  const weekScheduleData: WeekScheduleType = {
    Monday: [
      {
        classTitle: "CSCI 3030",
        className: "Computing, Ethics, and Society",
        description: "",
        location: "Park Hall",
        timeStart: "8:00 am",
        timeEnd: "9:15 am",
        timeDifference: null,
        professor: "John Doe",
        semester: "Spring 2025",
        credits: 3,
        crn: 26510,
        color: "bg-bulldog-red",
      },
      {
        classTitle: "BIOL 1103",
        timeStart: "9:35 am",
        timeEnd: "10:50 am",
        color: "gray-600",
      },
      {
        classTitle: "MUSIC 2300",
        location: "Hugh Hodgson School of Music",
        timeStart: "12:45 pm",
        timeEnd: "2:00 pm",
        color: "indigo-500",
      },
    ],
    Tuesday: [
      {
        classTitle: "CSCI 1301",
        timeStart: "8:35 am",
        timeEnd: "10:50 am",
        color: "green-600",
      },
      {
        classTitle: "PHYS 2000",
        location: "Physics Building",
        timeStart: "4:00 pm",
        timeEnd: "5:00 pm",
        color: "teal-500",
      },
    ],
    Wednesday: [
      {
        classTitle: "CSCI 3030",
        location: "Park Hall",
        timeStart: "8:00 am",
        timeEnd: "9:15 am",
        color: "red-600",
      },
      {
        classTitle: "BIOL 1103",
        timeStart: "9:35 am",
        timeEnd: "10:50 am",
        color: "gray-600",
      },
      {
        classTitle: "MUSIC 2300",
        location: "Hugh Hodgson School of Music",
        timeStart: "12:45 pm",
        timeEnd: "2:00 pm",
        color: "indigo-500",
      },
    ],
    Thursday: [
      {
        classTitle: "CSCI 1301",
        timeStart: "8:35 am",
        timeEnd: "10:50 am",
        color: "green-600",
      },
      {
        classTitle: "PHYS 2000",
        location: "Physics Building",
        timeStart: "4:00 pm",
        timeEnd: "5:00 pm",
        color: "teal-500",
      },
    ],
    Friday: [
      {
        classTitle: "CSCI 3030",
        location: "Park Hall",
        timeStart: "8:00 am",
        timeEnd: "9:15 am",
        color: "red-600",
      },
      {
        classTitle: "BIOL 1103",
        timeStart: "9:35 am",
        timeEnd: "10:50 am",
        color: "gray-600",
      },
      {
        classTitle: "MUSIC 2300",
        location: "Hugh Hodgson School of Music",
        timeStart: "12:45 pm",
        timeEnd: "2:00 pm",
        color: "indigo-500",
      },
    ],

    // Add more days as needed
  };

  // Sorts the Classes by Start Time
  Object.keys(weekScheduleData).forEach((day) => {
    weekScheduleData[day]?.sort((a, b) => {
      const timeA = new Date(`1970/01/01 ${a.timeStart}`); // Any date can be used since we're only comparing times
      const timeB = new Date(`1970/01/01 ${b.timeStart}`);
      return timeA.getTime() - timeB.getTime();
    });
  });

  return (
    <div className="mx-auto min-h-screen w-[100%]">
      <WeekSchedule weekData={weekScheduleData} />
    </div>
  );
}

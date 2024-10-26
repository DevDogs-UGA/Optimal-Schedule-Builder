import { WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import WeekSchedule from "@/components/schedules/WeekSchedule";

export default function SchedulePage() {
  const weekScheduleData: WeekScheduleType = {
    Monday: [
      {
        classTitle: "CSCI 3030",
        location: "Park Hall",
        timeStart: "8:00 am",
        timeEnd: "9:15 am",
        bgColor: "bg-red-600",
      },
      {
        classTitle: "BIOL 1103",
        timeStart: "9:35 am",
        timeEnd: "10:50 am",
        bgColor: "bg-gray-600",
      },
      {
        classTitle: "MUSIC 2300",
        location: "Hugh Hodgson School of Music",
        timeStart: "12:45 pm",
        timeEnd: "2:00 pm",
        bgColor: "bg-teal-500",
      },
      
      {
        classTitle: "MUSIC 2300",
        location: "Hugh Hodgson School of Music",
        timeStart: "12:45 pm",
        timeEnd: "2:00 pm",
        bgColor: "bg-teal-500",
      },
      {
        classTitle: "MUSIC 2300",
        location: "Hugh Hodgson School of Music",
        timeStart: "12:45 pm",
        timeEnd: "2:00 pm",
        bgColor: "bg-teal-500",
      },
    ],
    Tuesday: [
      {
        classTitle: "CSCI 4800",
        location: "Boyd",
        timeStart: "8:00 am",
        timeEnd: "9:15 am",
        bgColor: "bg-red-600",
      },
      {
        classTitle: "BIOL 1103",
        timeStart: "9:35 am",
        timeEnd: "10:50 am",
        bgColor: "bg-gray-600",
      },
      {
        classTitle: "MUSIC 2300",
        location: "Hugh Hodgson School of Music",
        timeStart: "12:45 pm",
        timeEnd: "2:00 pm",
        bgColor: "bg-blue-500",
      },
    ],
    Wednesday: [
      {
        classTitle: "CSCI 3030",
        location: "Park Hall",
        timeStart: "8:00 am",
        timeEnd: "9:15 am",
        bgColor: "bg-red-600",
      },
      {
        classTitle: "BIOL 1103",
        timeStart: "9:35 am",
        timeEnd: "10:50 am",
        bgColor: "bg-gray-600",
      },
      {
        classTitle: "MUSIC 2300",
        location: "Hugh Hodgson School of Music",
        timeStart: "12:45 pm",
        timeEnd: "2:00 pm",
        bgColor: "bg-indigo-500",
      },
    ],
    Thursday: [
      {
        classTitle: "CSCI 3030",
        location: "Park Hall",
        timeStart: "8:00 am",
        timeEnd: "9:15 am",
        bgColor: "bg-pink-600",
      },
      {
        classTitle: "BIOL 1103",
        timeStart: "9:35 am",
        timeEnd: "10:50 am",
        bgColor: "bg-green-600",
      },
      {
        classTitle: "MUSIC 2300",
        location: "Hugh Hodgson School of Music",
        timeStart: "12:45 pm",
        timeEnd: "2:00 pm",
        bgColor: "bg-teal-500",
      },
    ],
    Friday: [
      {
        classTitle: "CSCI 3030",
        location: "Park Hall",
        timeStart: "8:00 am",
        timeEnd: "9:15 am",
        bgColor: "bg-red-600",
      },
      {
        classTitle: "BIOL 1103",
        timeStart: "9:35 am",
        timeEnd: "10:50 am",
        bgColor: "bg-gray-600",
      },
      {
        classTitle: "MUSIC 2300",
        location: "Hugh Hodgson School of Music",
        timeStart: "12:45 pm",
        timeEnd: "2:00 pm",
        bgColor: "bg-yellow-500",
      },
    ],

    // Add more days as needed
  };

  return (
    <div className="min-h-screen w-[85%] mx-auto">
      <WeekSchedule weekData={weekScheduleData} />
    </div>
  );
}

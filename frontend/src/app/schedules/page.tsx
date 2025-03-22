// Returning User: -> Saved Plans
// New User: -> Saved Plans
'use client';
import { type WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import WeekSchedule from "@/components/schedules/WeekSchedule";
import Link from "next/link";
import { useSearchParams } from 'next/navigation';

// Page for viewing a generated schedule / saved plan
export default function SchedulePage() {

  // Retrieve the schedule data and title
  const searchParams = useSearchParams();
  const savedPlan = searchParams.get('data');
  const planTitle = searchParams.get('title');

  // Attempt to parse the schedule JSON string
  let parsedPlan;
  try {
    parsedPlan = savedPlan ? JSON.parse(savedPlan) : null;
  } catch (error) {
    console.error("Failed to parse plan data:", error);
    parsedPlan = null;
  }

  // If the schedule from local storage is somehow null, use an empty schedule to prevent errors
  // Otherwise, use the retrieved schedule
  const schedule: WeekScheduleType = parsedPlan || {
    Monday: [],
    Tuesday: [],
    Wednesday: [],
    Thursday: [],
    Friday: []
  };

  // Sorts the classes by start time
  Object.keys(schedule).forEach((day) => {
    schedule[day]?.sort((a, b) => {
      const timeA = new Date(`1970/01/01 ${a.timeStart}`); // Any date can be used since we're only comparing times
      const timeB = new Date(`1970/01/01 ${b.timeStart}`);
      return timeA.getTime() - timeB.getTime();
    });
  });

  // Render the schedule
  return (
    <div className="mx-auto min-h-screen w-[100%]">
      <div className="p-7 text-center text-blue-600">
        <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
          <Link href={"/route-map"}>Route Map</Link>
        </button>
      </div>
      <WeekSchedule weekData={schedule} />
    </div>
  );
}

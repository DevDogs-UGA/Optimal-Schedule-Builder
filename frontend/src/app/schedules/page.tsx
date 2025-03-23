// Returning User: -> Saved Plans
// New User: -> Saved Plans
'use client';
import { type WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import WeekSchedule from "@/components/schedules/WeekSchedule";
import Link from "next/link";
import { useSearchParams } from 'next/navigation';
import { useState, useEffect } from "react";
import { PiHeart } from "react-icons/pi";
import { PiArrowLeft } from "react-icons/pi";
import { PiArrowRight } from "react-icons/pi";
import { PiX } from "react-icons/pi";


// Page for viewing a generated schedule / saved plan
export default function SchedulePage() {

  // Retrieve the current schedule data and title (from the SavedPlan component)
  const searchParams = useSearchParams();
  const savedPlan = searchParams.get('data');
  const planTitle = searchParams.get('title');

  // Parameters for a saved schedule
  interface SavedPlanType {
    title: string;
    data: WeekScheduleType;
  }
  
  // List of the user's saved schedules
  const [savedPlans, setSavedPlans] = useState<SavedPlanType[]>([]);
  // Used to enable selection of any schedule from the list
  const [currentPlanIndex, setCurrentPlanIndex] = useState(0);
  
  useEffect(() => {
    try {
      // Retrieve schedules from local storage and populate the plans array
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
      // savedPlans = plans. Increases the scope of the plans array 
      setSavedPlans(plans);

      // Set initial current plan based on the SavedPlan arguments
      if (savedPlan && planTitle) {
        const initialPlan = {
          title: planTitle,
          data: JSON.parse(savedPlan)
        };
        // Get the index of the current plan and update the current plan index
        const initialIndex = plans.findIndex(plan => plan.title === planTitle);
        setCurrentPlanIndex(initialIndex !== -1 ? initialIndex : 0);
      }
    } catch (error) {
      console.error("Failed to parse local storage data: ", error);
    }
  }, [savedPlan, planTitle]);

  /* The current plan/schedule is what is displayed on the page.
  * For safety, an empty schedule is assigned if the data from the array is null or cannot be read.
  */
  const currentPlan = savedPlans[currentPlanIndex] || 
  { title: "", data: {Monday: [], Tuesday: [], Wednesday: [], Thursday: [], Friday: [] } };

  // When the left arrow is clicked, the current plan index is decreased to show the previous schedule in the list
  const previousPlan = () => {
    if (currentPlanIndex > 0) {
      setCurrentPlanIndex(currentPlanIndex - 1);
    }
  };

  // When the right arrow is clicked, the current plan index is increased to show the next schedule in the list
  const nextPlan = () => {
    if (currentPlanIndex < savedPlans.length - 1) {
      setCurrentPlanIndex(currentPlanIndex + 1);
    }
  };

  // Sorts the classes on the schedule display by start time
  Object.keys(currentPlan.data).forEach((day) => {
    currentPlan.data[day]?.sort((a, b) => {
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
      {/* Container for schedule display, schedule cycling, and save/exit buttons */}
      <div className="flex flex-col ml-auto mr-auto w-[90vw] h-[85vh] border-2 border-black rounded-lg z-1 bg-barely-pink">
        {/* Properly positions the cycling bar and the save/exit buttons */}
        <div className="flex flex-row">
          {/* Cycling bar; changes the displayed schedule when arrows are clicked */}
          <div className="flex flex-row items-center ml-auto mr-auto mt-5 mb-5 bg-white rounded-lg w-[30vw] h-10">
            <button onClick={previousPlan}>
              <PiArrowLeft size={32} className="ml-5 hover:fill-bulldog-red" />
            </button>
            <h1 className="ml-auto mr-auto text-2xl font-bold"> 
              {currentPlan.title} 
            </h1>
            <button onClick={nextPlan}>
              <PiArrowRight size={32} className="mr-5 hover:fill-bulldog-red" />
            </button>
          </div>
          {/* Save/exit buttons: exit returns to saved plan list */}
          <div className="flex flex-row">
            <PiHeart size={32} className="mt-5 mr-3 fill-bulldog-red" />
            <Link href={"/saved-plans"}>
              <PiX size={32} className="mt-5 mr-3 hover:fill-bulldog-red" />
            </Link>
          </div>
        </div>
        {/* Schedule display container */}
        <div className="flex flex-row">
          <WeekSchedule weekData={currentPlan.data} />
        </div>
      </div>
    </div>
  );
}

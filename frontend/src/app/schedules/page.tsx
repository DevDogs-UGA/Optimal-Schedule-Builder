// Returning User: -> Saved Plans
// New User: -> Saved Plans
"use client";
import { type WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import WeekSchedule from "@/components/schedules/WeekSchedule";
import Link from "next/link";
import { useSearchParams } from "next/navigation";
import { useState, useEffect } from "react";
import { PiHeart } from "react-icons/pi";
import { PiArrowLeft } from "react-icons/pi";
import { PiArrowRight } from "react-icons/pi";
import { PiX } from "react-icons/pi";

// Page for viewing a generated schedule / saved plan
export default function SchedulePage() 
{
  // COURSE BLOCK BACKGROUND COLORS ON SCHEDULE DISPLAY
  // Background colors for course blocks
  const bgColors = [
    "bg-[#cc0128]",
    "bg-[#bc8da7]",
    "bg-[#0db1b1]",
    "bg-[#53917e]",
    "bg-[#202c59]",
  ];

  // Map colors to course (if the course includes a lab, drop the L from the string)
  const colorMapping: Record<string, string> = {};
  const usedColors: Set<string> = new Set<string>();

  function getBgColorForClass(classTitle: string): string {
    // Check if color has already been used
    if (colorMapping[classTitle]) {
      return colorMapping[classTitle];
    }

    // Find an unused color
    let colorToAssign: string | undefined;

    // Assign unused color
    if (usedColors.size < bgColors.length) {
      for (const color of bgColors) {
        if (!usedColors.has(color)) {
          colorToAssign = color;
          usedColors.add(color); // Mark color as used
          break;
        }
      }
    } else {
      // All colors have been used, so reset colors to be reused
      usedColors.clear();

      // Reassign the color to the first class that needs a color
      colorToAssign = bgColors[usedColors.size % bgColors.length];
      usedColors.add(colorToAssign!); // ! asserts variable to not be read as undefined
    }
    // If no color assigned
    if (!colorToAssign) {
      colorToAssign = "bg-gray-500";
    }

    // Store the assign color for classTitle
    colorMapping[classTitle] = colorToAssign;
    return colorToAssign;
  }

  // RETRIEVING SCHEDULES FROM BROWSER LOCAL STORAGE
  // Retrieve the current schedule data and title (from the SavedPlan component)
  const searchParams = useSearchParams();
  const savedPlan = searchParams.get("data");
  const planTitle = searchParams.get("title");

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
              data: JSON.parse(data),
            });
          }
        }
      }
      // Sort plans by title
      plans.sort((a, b) => a.title.localeCompare(b.title));
      // savedPlans = plans. Increases the scope of the plans array
      setSavedPlans(plans);

      // Set initial current plan based on the SavedPlan arguments
      if (savedPlan && planTitle) {
        const initialPlan = {
          title: planTitle,
          data: JSON.parse(savedPlan),
        };
        // Get the index of the current plan and update the current plan index
        const initialIndex = plans.findIndex(
          (plan) => plan.title === planTitle,
        );
        setCurrentPlanIndex(initialIndex !== -1 ? initialIndex : 0);
      }
    } catch (error) {
      console.error("Failed to parse local storage data: ", error);
    }
  }, [savedPlan, planTitle]);

  /* The current plan/schedule is what is displayed on the page.
   * For safety, an empty schedule is assigned if the data from the array is null or cannot be read.
   */
  const currentPlan = savedPlans[currentPlanIndex] || {
    title: "",
    data: { Monday: [], Tuesday: [], Wednesday: [], Thursday: [], Friday: [] },
  };

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
      <div className="z-1 ml-auto mr-auto flex h-[85vh] w-[90vw] flex-col rounded-lg border-2 border-black bg-barely-pink">
        {/* Properly positions the cycling bar and the save/exit buttons */}
        <div className="flex flex-row">
          {/* Cycling bar; changes the displayed schedule when arrows are clicked */}
          <div className="mb-5 ml-auto mr-auto mt-5 flex h-10 w-[30vw] flex-row items-center rounded-lg bg-white">
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
            <PiHeart size={32} className="mr-3 mt-5 fill-bulldog-red" />
            <Link href={"/saved-plans"}>
              <PiX size={32} className="mr-3 mt-5 hover:fill-bulldog-red" />
            </Link>
          </div>
        </div>
        {/* Schedule display container */}
        <div className="flex flex-row flex-grow overflow-y-auto">
          <WeekSchedule weekData={currentPlan.data} />
        </div>
      </div>
    </div>
  );
}

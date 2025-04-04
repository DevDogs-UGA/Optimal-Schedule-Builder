"use client";
import { type WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import WeekSchedule from "@/components/schedules/WeekSchedule";
import Link from "next/link";
import { useSearchParams } from "next/navigation";
import { useState, useEffect } from "react";
import { PiArrowLeft, PiArrowRight, PiX } from "react-icons/pi";
import { FaHeart, FaRegHeart } from "react-icons/fa";

export default function ScheduleDisplay({ bgColors, borderColors }: { bgColors: string[], borderColors: string[] } ) {
  // Map colors to course (if the course includes a lab, drop the L from the string)
  const colorMapping: Record<string, { bgColor: string | undefined, borderColor: string | undefined }> = {};
  const usedColors: Set<string> = new Set<string>();

  // Function to assign a different color to each course block on the schedule
  function getCourseBlockColors(classTitle: string): {bgColor: string | undefined, borderColor: string | undefined} {
    // Course block colors:
    // Check if color has already been used
    if (colorMapping[classTitle]) {
      return colorMapping[classTitle];
    }

    // Find an unused color
    let bgColorToAssign: string | undefined;
    let borderColorToAssign: string | undefined;

    // Assign unused color
    if (usedColors.size < bgColors.length) {
      for (const color of bgColors) {
        if (!usedColors.has(color)) {
          bgColorToAssign = color;
          borderColorToAssign = borderColors[bgColors.indexOf(color)]; // Find the same color in the border colors list
          usedColors.add(color); // Mark color as used
          break;
        }
      }
    } else {
      // All colors have been used, so reset colors to be reused
      usedColors.clear();

      // Reassign the color to the first class that needs a color
      bgColorToAssign = bgColors[usedColors.size % bgColors.length];
      borderColorToAssign = borderColors[usedColors.size % borderColors.length];
      usedColors.add(bgColorToAssign!); // ! asserts variable to not be read as undefined
    }
    // If no color assigned
    if (!bgColorToAssign) {
      bgColorToAssign = "bg-gray-500";
      borderColorToAssign = "border-gray-500";
    }

    // Store the assign color for classTitle
    colorMapping[classTitle] = {bgColor: bgColorToAssign, borderColor: borderColorToAssign };
    return colorMapping[classTitle];
  }

  // Retrieve the current schedule data and title (from the SavedPlan component)
  const searchParams = useSearchParams();
  const savedPlan = searchParams.get("data") ?? "";
  const planTitle = searchParams.get("title") ?? "";
  const isPinned = searchParams.get("pinned") ?? "";

  // Parameters for a saved schedule
  interface SavedPlanType {
    title: string;
    data: WeekScheduleType;
    pinned: boolean;
  }

  // List of the user's saved schedules
  const [savedPlans, setSavedPlans] = useState<SavedPlanType[]>([]);

  // Used to enable selection of any schedule from the list
  const [currentPlanIndex, setCurrentPlanIndex] = useState(0);

  useEffect(() => {
    try {
      // Retrieve schedules from local storage and populate the plans array
      const plans: SavedPlanType[] = [];
      // Loop through local storage
      for (let i = 0; i < localStorage.length; i++) {
        const key = localStorage.key(i);
        // Retrieve key for local storage element
        if (key) {
          // Retrieve data matching key
          const data = localStorage.getItem(key);
          if (data?.startsWith(`{"data":{`)) {
            const parsedData = JSON.parse(data) as SavedPlanType;
            if (parsedData.data && parsedData.pinned !== undefined) {
              // Parse the data from the JSON
              plans.push({
                title: key,
                data: parsedData.data,
                pinned: parsedData.pinned,
              });
            }
          }
        }
      }
      // Sort plans by title, and then again by pinned status
      plans.sort(
        (a, b) =>
          Number(b.pinned) - Number(a.pinned) || a.title.localeCompare(b.title),
      );

      // savedPlans = plans. Increases the scope of the plans array
      setSavedPlans(plans);

      // Get the index of the current plan and update the current plan index (trim any whitespace)
      const initialIndex = plans.findIndex(
        (plan) => plan.title.trim() === planTitle.trim(),
      );
      setCurrentPlanIndex(initialIndex !== -1 ? initialIndex : 0);
    } catch (error) {
      console.error("Failed to parse local storage data: ", error);
    }
  }, [savedPlan, planTitle, isPinned]);

  /* The current plan/schedule is what is displayed on the page.
   * For safety, an empty schedule is assigned if the data from the array is null or cannot be read.
   */
  const currentPlan = savedPlans[currentPlanIndex] ?? {
    title: "Schedule",
    pinned: false,
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

  // When the heart icon is clicked, the schedule will be considered "pinned" when returning to the plans list page
  const pinPlan = () => {
    if (currentPlan.title) {
      setSavedPlans((prevPlans) => {
        const updatedPlans = prevPlans.map((plan) => {
          if (plan.title === currentPlan.title) {
            // Toggle the pinned status
            const updatedPlan = { ...plan, pinned: !plan.pinned };
            // Save the updated plan back to local storage
            localStorage.setItem(
              currentPlan.title,
              JSON.stringify({ data: plan.data, pinned: updatedPlan.pinned }),
            );
            return updatedPlan;
          }
          return plan;
        });

        // Sort plans again to reflect the new pin
        updatedPlans.sort(
          (a, b) =>
            Number(b.pinned) - Number(a.pinned) ||
            a.title.localeCompare(b.title),
        );

        // Save sorted plans back to local storage
        updatedPlans.forEach((plan) => {
          localStorage.setItem(
            plan.title,
            JSON.stringify({ data: plan.data, pinned: plan.pinned }),
          );
        });

        // Update the current plan index to reflect the new order
        const newIndex = updatedPlans.findIndex(
          (plan) => plan.title === currentPlan.title,
        );
        setCurrentPlanIndex(newIndex);
        return updatedPlans;
      });
    }
  };

  // Sorts the classes on the schedule display by start time
  Object.keys(currentPlan.data).forEach((day) => {
    currentPlan.data[day]?.sort((a, b) => {
      const timeA = new Date(`1970/01/01 ${a.timeStart}`); // Any date can be used since we're only comparing times
      const timeB = new Date(`1970/01/01 ${b.timeStart}`);
      return timeA.getTime() - timeB.getTime();
    });

    // Assign background and border colors to each class
    currentPlan.data[day]?.forEach((classItem) => {
      const courseBlockColors = getCourseBlockColors(classItem.classTitle);
      if (courseBlockColors.bgColor && courseBlockColors.borderColor){
        classItem.bgColor = courseBlockColors.bgColor;
        classItem.borderColor = courseBlockColors.borderColor;
      }
    });
  });

  // Variables to control if the next and back buttons should appear
  const canCycleBack = currentPlanIndex > 0;
  const canCycleNext = currentPlanIndex < savedPlans.length - 1;

  // Render the schedule
  return (
    <div className="mx-auto min-h-screen w-[100%]">
      {/* Container for schedule display, schedule cycling, and save/exit buttons */}
      <div className="z-1 ml-auto mr-auto flex h-[85vh] w-[90vw] flex-col rounded-lg border-2 border-black bg-barely-pink">
        {/* Properly positions the cycling bar and the save/exit buttons */}
        <div className="flex flex-row">
          {/* Cycling bar; changes the displayed schedule when arrows are clicked */}
          <div className="mb-5 ml-auto mr-auto mt-5 flex h-10 w-[30vw] flex-row items-center rounded-lg bg-white">
            {canCycleBack ? (
              <button onClick={previousPlan}>
                <PiArrowLeft
                  size={32}
                  className="ml-5 hover:fill-bulldog-red"
                />
              </button>
            ) : (
              <PiArrowLeft size={32} className="ml-5 fill-pebble-gray" />
            )}
            <h1 className="ml-auto mr-auto text-2xl font-bold">
              {currentPlan.title}
            </h1>
            {canCycleNext ? (
              <button onClick={nextPlan}>
                <PiArrowRight
                  size={32}
                  className="mr-5 hover:fill-bulldog-red"
                />
              </button>
            ) : (
              <PiArrowRight size={32} className="mr-5 fill-pebble-gray" />
            )}
          </div>
          {/* Save/exit buttons: exit returns to saved plan list */}
          <div className="flex flex-row">
            <div onClick={pinPlan}>
              {currentPlan.pinned ? (
                <FaHeart
                  size={30}
                  className="mr-3 mt-5 fill-glory-glory-red transition"
                />
              ) : (
                <FaRegHeart
                  size={30}
                  className="mr-3 mt-5 transition hover:fill-glory-glory-red"
                />
              )}
            </div>
            <Link href={"/my-plans"}>
              <PiX size={32} className="mr-3 mt-5 hover:fill-bulldog-red" />
            </Link>
          </div>
        </div>
        {/* Schedule display container */}
        <div className="flex flex-grow flex-row overflow-y-auto">
          <WeekSchedule weekData={currentPlan.data} />
        </div>
      </div>
    </div>
  );
}

"use client";
import { Button } from "@/components/ui/Button";
import { type WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import WeekSchedule from "@/components/schedules/WeekSchedule";
import { useState, useEffect } from "react";

interface PlanDisplayProps {
  planTitle?: string;
  plan?: WeekScheduleType;
  index?: number;
}

// Saved plan display popup (major WIP)
function PlanDisplay({ plan, planTitle }: PlanDisplayProps) {

  if (!plan) {
    return <div></div>;
  }
  return (
    <div>
      <WeekSchedule weekData={plan} />
    </div>
  );
}

export default function SavedPlan({
  planTitle,
  plan,
}: PlanDisplayProps) {

  const [planButtonClicked, setplanButtonClicked] = useState(false);
  const savedPlanDisplay = () => {
    setplanButtonClicked(!planButtonClicked);
  };

  /* TO DO HERE:
  * Figure out where to get the icons
  * Figure out how to make icons function as buttons
  * Make the schedule popup actually work
  */
  return (
    <div className="relative">
      <div className="flex flex-row items-center m-5 w-[70vw] h-24 rounded-xl bg-glory-glory-red">
      </div>
      {/* Saved plan container */}
      <div className="absolute top-0 flex flex-row items-center m-5 w-[70vw] h-20 z-10 rounded-xl border-2 border-black bg-white">
        {/* Paw icon */}
        <img src="/images/blackpaw.svg" alt="black paw" className="h-12 w-12 m-4" />
        {/* Plan title */}
        <h1 className="text-3xl"> 
          {planTitle} 
        </h1>
        {/* Buttons container */}
        <div className="flex items-center justify-end ml-auto">
          {/* Rename button */}
          <Button
            className="m-2"
            text="Rename"
          ></Button>
          {/* Save button */}
          <Button
            className="m-2"
            text="Save"
          ></Button>
          {/* Edit button */}
          <Button
            className="m-2"
            onClick={savedPlanDisplay}
            text="Edit"
          ></Button>
          {planButtonClicked && <PlanDisplay plan={plan} planTitle={planTitle} />}
        </div>

      </div>
    </div>
  );
}

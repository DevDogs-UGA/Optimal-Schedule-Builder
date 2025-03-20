"use client";
import { Button } from "@/components/ui/Button";
import { type WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import WeekSchedule from "@/components/schedules/WeekSchedule";
import { useState } from "react";

interface PlanDisplayProps {
  className?: string;
  planTitle?: string;
  plan?: WeekScheduleType;
}

// "planTitle" parameter never used
function PlanDisplay({ plan }: PlanDisplayProps) {
  // create some className variables to be used with both return statements.
  // we need a popup to hold the schedule component and its associated plan title
  // <WeekSchedule weekData={plan} />

  // Unused planContainer const
  //const planContainer = "fixed inset-0 z-40 flex items-center justify-center bg-white bg-opacity-50";

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
  className,
  planTitle,
  plan,
}: PlanDisplayProps) {
  const [planButtonClicked, setplanButtonClicked] = useState(false);
  const savedPlanDisplay = () => {
    setplanButtonClicked(!planButtonClicked);
  };
  return (
    <div>
      <Button
        className={className}
        onClick={savedPlanDisplay}
        text={planTitle}
      ></Button>
      {planButtonClicked && <PlanDisplay plan={plan} planTitle={planTitle} />}
    </div>
  );
}

"use client";
import { Button } from "@/components/ui/Button";
import { type WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import WeekSchedule from "@/components/schedules/WeekSchedule";
import { useState, useEffect } from "react";

interface PlanDisplayProps {
  planTitle?: string;
  plan?: WeekScheduleType;
}

function PlanDisplay({ plan, planTitle }: PlanDisplayProps) {
  // create some className variables to be used with both return statements.
  // we need a popup to hold the schedule component and its associated plan title
  // <WeekSchedule weekData={plan} />

  const planContainer =
    "fixed inset-0 z-40 flex items-center justify-center bg-white bg-opacity-50";

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
  const buttonStyle="m-10 h-[10vh] w-[70vw] !rounded-3xl border-2 border-pebble-gray bg-dusty-pink text-3xl font-semibold text-black hover:bg-barely-pink";

  return (
    <div>
      <div >
      <Button
        className={buttonStyle}
        onClick={savedPlanDisplay}
        text={planTitle}
      ></Button>
      {planButtonClicked && <PlanDisplay plan={plan} planTitle={planTitle} />}
      </div>
    </div>
  );
}

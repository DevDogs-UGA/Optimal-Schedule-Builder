"use client";

import { Suspense } from "react";
import ScheduleDisplay from "@/components/schedules/ScheduleDisplay";
import background from "../../../../public/images/background.png";

interface Props {
  params: {
    id: string;
  }
}

// Page for viewing a generated schedule / saved plan
export default function SchedulePage({ params }: Props) {
// Render the schedule
  return (
    <div
      className="relative -mb-[3.625rem] -mt-[3.625rem] min-h-screen bg-cover bg-fixed bg-bottom bg-no-repeat pt-2"
      style={{
        backgroundImage: `url(${background.src})`,
      }}
    >
      {/* Schedule display container */}
      <div className="flex flex-grow flex-row overflow-y-auto pt-32">
        <Suspense fallback={<div>Loading...</div>}>
          <ScheduleDisplay id={params.id} />
        </Suspense>
      </div>
    </div>
  );
}

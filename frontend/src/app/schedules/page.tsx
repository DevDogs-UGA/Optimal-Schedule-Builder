// Returning User: -> Saved Plans
// New User: -> Saved Plans
"use client";
import { Suspense } from "react";
import ScheduleDisplay from "@/components/schedules/ScheduleDisplay";
import Link from "next/link";

// Page for viewing a generated schedule / saved plan
export default function SchedulePage() {
  // Background colors for course blocks
  const bgColors = [
    "bg-[#cc0128]",
    "bg-[#bc8da7]",
    "bg-[#0db1b1]",
    "bg-[#53917e]",
    "bg-[#202c59]",
  ];

  // Render the schedule
  return (
    <div className="mx-auto min-h-screen w-[100%]">
      <div className="p-7 text-center text-blue-600">
        <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
          <Link href={"/route-map"}>Route Map</Link>
        </button>
      </div>
      {/* Schedule display container */}
      <div className="flex flex-grow flex-row overflow-y-auto">
        <Suspense fallback={<div>Loading...</div>}>
          <ScheduleDisplay bgColors={bgColors} />
        </Suspense>
      </div>
    </div>
  );
}

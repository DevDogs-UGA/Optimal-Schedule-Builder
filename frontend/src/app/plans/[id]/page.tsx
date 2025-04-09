"use client";

import { Suspense } from "react";
import ScheduleDisplay from "@/components/schedules/ScheduleDisplay";
import Link from "next/link";

interface Props {
  params: {
    id: string;
  }
}

// Page for viewing a generated schedule / saved plan
export default function SchedulePage({ params }: Props) {
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
          <ScheduleDisplay id={params.id} />
        </Suspense>
      </div>
    </div>
  );
}

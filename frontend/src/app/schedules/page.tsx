"use client";
import { Suspense } from "react";
import ScheduleDisplay from "@/components/schedules/ScheduleDisplay";
import Link from "next/link";
import background from "../../../public/images/background.png";
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

  const borderColors = [
    "border-[#cc0128]",
    "border-[#bc8da7]",
    "border-[#0db1b1]",
    "border-[#53917e]",
    "border-[#202c59]",
  ];

  // Render the schedule
  return (
    <div className="relative min-h-screen -mt-[3.625rem] pt-2 -mb-[3.625rem] bg-cover bg-bottom bg-no-repeat bg-fixed"
        style={{
          backgroundImage: `url(${background.src})`,
        }
      }>
      {/* Schedule display container */}
      <div className="pt-32 flex flex-grow flex-row overflow-y-auto">
        <Suspense fallback={<div>Loading...</div>}>
          <ScheduleDisplay bgColors={bgColors} borderColors={borderColors} />
        </Suspense>
      </div>
    </div>
  );
}

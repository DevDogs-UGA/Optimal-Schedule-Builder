"use client";
import { Button } from "@/components/ui/Button";
import { type WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import { PiNotePencil } from "react-icons/pi";
import { PiHeart } from "react-icons/pi";
import { PiArrowsOut } from "react-icons/pi";
import Image from "next/image";
import Link from "next/Link";

interface PlanDisplayProps {
  index?: number;
  planTitle: string;
  plan: WeekScheduleType;
}

// Saved Plan Component. 
// Contains each "banner" and action buttons for each of the user's saved plans.
export default function SavedPlan({
  planTitle,
  plan,
}: PlanDisplayProps) {

  return (
    <div className="relative">
      <div className="flex flex-row items-center m-5 w-[70vw] h-24 rounded-xl bg-glory-glory-red">
      </div>
      {/* Saved plan container */}
      <div className="absolute top-0 flex flex-row items-center m-5 w-[70vw] h-20 z-10 rounded-xl border-2 border-black bg-white">
        {/* Paw icon */}
        <Image
          src="/images/blackpaw.svg"
          width={50}
          height={50}
          className="m-4"
          alt="black paw"
        />
        {/* Plan title */}
        <h1 className="text-4xl"> 
          {planTitle} 
        </h1>
        {/* Buttons container */}
        <div className="flex items-center justify-end ml-auto">
          {/* Save button */}
          <PiHeart
              size={60}
              className="m-2 fill-black transition hover:fill-bulldog-red" 
            />
          {/* Open button (redirects to the schedules page with the schedule data)*/}
          <Link href={`/schedules?title=${encodeURIComponent(planTitle)}&data=${encodeURIComponent(JSON.stringify(plan))}`}>
            <PiArrowsOut
              size={60}
              className="m-2 fill-black transition hover:fill-bulldog-red" 
            />
          </Link>
        </div>
      </div>
    </div>
  );
}

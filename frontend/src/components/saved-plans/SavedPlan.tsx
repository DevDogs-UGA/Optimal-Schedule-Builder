"use client";
import DeletePlan from "../ui/DeletePlan";
import { type WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import { PiNotePencil } from "react-icons/pi";
import { PiHeart } from "react-icons/pi";
import { PiArrowsOut } from "react-icons/pi";
import { PiTrash } from "react-icons/pi";
import Image from "next/image";
import Link from "next/link";
import { Card } from "../ui/Card";

interface PlanDisplayProps {
  index?: number;
  planTitle: string;
  plan: WeekScheduleType;
  onDelete: () => void;
}

// Saved Plan Component.
// Contains each "banner" and action buttons for each of the user's saved plans.
export default function SavedPlan({
  planTitle,
  plan,
  onDelete,
}: PlanDisplayProps) {
  <div>
    <Card></Card>
  </div>;
  return (
    <div className="relative">
      <div className="m-5 flex h-24 w-[70vw] flex-row items-center rounded-xl bg-glory-glory-red"></div>
      {/* Saved plan container */}
      <div className="absolute top-0 z-10 m-5 flex h-20 w-[70vw] flex-row items-center rounded-xl border-2 border-black bg-white">
        {/* Paw icon */}
        <Image
          src="/images/blackpaw.svg"
          width={50}
          height={50}
          className="m-4"
          alt="black paw"
        />
        {/* Plan title */}
        <h1 className="text-4xl">{planTitle}</h1>
        {/* Buttons container */}
        <div className="ml-auto flex items-center justify-end">
          {/* Save button: WIP */}
          <PiHeart
            size={60}
            className="m-2 fill-black fill-bulldog-red transition"
          />
          {/* Open button (redirects to the schedules page with the schedule data)*/}
          <Link
            href={`/schedules?title=${encodeURIComponent(planTitle)}&data=${encodeURIComponent(JSON.stringify(plan))}`}
          >
            <PiArrowsOut
              size={60}
              className="m-2 fill-black transition hover:fill-bulldog-red"
            />
          </Link>
          <PiTrash
            size={60}
            className="m-2 hover:fill-bulldog-red"
            onClick={onDelete}
          />
        </div>
      </div>
    </div>
  );
}

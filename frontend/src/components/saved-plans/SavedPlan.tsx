"use client";
import { type WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import { PiNotePencil, PiArrowsOut, PiTrash } from "react-icons/pi";
import { FaHeart, FaRegHeart } from "react-icons/fa";
import Image from "next/image";
import Link from "next/link";

interface PlanDisplayProps {
  index?: number;
  plan: WeekScheduleType;
  planTitle: string;
  pinned: boolean;
  onPin: () => void;
  onDelete: () => void;
}

// Saved Plan Component.
// Contains each "banner" and action buttons for each of the user's saved plans.
export default function SavedPlan({
  plan,
  planTitle,
  pinned,
  onPin,
  onDelete,
}: PlanDisplayProps) {
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
          {/* Pin button (gives a saved plan priority over others*/}
          <div onClick={onPin}>
            {pinned ? (
              <FaHeart size={50} className="m-2 fill-glory-glory-red transition" />
            ) : (
              <FaRegHeart size={50} className="m-2 transition hover:fill-glory-glory-red" />
            )}
          </div>
          {/* Open button (redirects to the schedules page with the schedule data)*/}
          <Link
            href={`/schedules?title=${encodeURIComponent(planTitle)}
            &pinned=${encodeURIComponent(JSON.stringify(pinned))}
            &data=${encodeURIComponent(JSON.stringify(plan))}`}
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

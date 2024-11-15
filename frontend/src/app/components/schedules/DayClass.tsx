"use client";

import { type ClassData } from "../../../types/scheduleTypes";
import { useState } from "react";

interface DayClassProps extends ClassData {
  className?: string;
}

function CourseInfo() {
  return (
    <div className="place-content-center absolute top-full left-0 bg-white border border-gray-300 shadow-lg z-10 p-10">
      insert class info here
    </div>
  );
}

export default function DayClass({
  classTitle,
  location,
  timeStart,
  timeEnd,
  bgColor,
  timeDifference,
  className = "",
}: DayClassProps) {

  const [courseBlockClicked, setcourseBlockClicked] = useState(false);

  const courseBlockInfo = () => {
    setcourseBlockClicked(!courseBlockClicked);
  }

  return (
    <div className={`relative ${className}`} onClick={courseBlockInfo}>
      <div
        className={`w-full rounded-lg p-4 transition duration-150 ease-in-out hover:bg-black ${bgColor} flex justify-between`}
        style={{
          position: "absolute",
          top: timeDifference ? `${timeDifference * 0.9}px` : "0px",
        }}
      >
        <div className="">
          <h2 className="font-bold text-white">{classTitle}</h2>
          {location && <p className="text-sm text-white/90">{location}</p>}
        </div>
        <div className="text-right text-sm text-white/90">
          <p>{timeStart}</p>
          <p>{timeEnd}</p>
        </div>
      </div>
      {courseBlockClicked && <CourseInfo />}
    </div>
  );
}

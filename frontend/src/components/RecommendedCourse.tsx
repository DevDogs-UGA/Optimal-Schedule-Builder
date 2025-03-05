"use client";

import { Button } from "./ui/Button";

interface props {
  course?: string;
  courseNumber?: string;
  professor?: string;
  handleClick?: () => void;
}

export function RecommendedCourse({
  course,
  courseNumber,
  professor,
  handleClick,
}: props) {
  return (
    <a className="cursor-pointer" onClick={handleClick}>
      <div className="transitition flex w-96 flex-row items-center justify-between gap-x-2 rounded-lg bg-[#F0ECEC] pl-4 duration-200 hover:bg-[#DAD6D6] hover:ease-in-out active:bg-[#BFBABA] active:transition-none">
        <div className="h-full w-28 truncate">{course}</div>
        <div className="h-full w-20 truncate">{courseNumber}</div>
        <div className="h-full w-36 truncate">{professor}</div>
        <div className="h-full w-16">
          <Button className="bg-transparent" text="+" />
        </div>
      </div>
    </a>
  );
}

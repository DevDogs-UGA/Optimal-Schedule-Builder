"use client";

import { type ClassData } from "../../../types/scheduleTypes";
import { useState } from "react";

interface DayClassProps extends ClassData {
  onClose?: () => void;
}

function CourseInfo({
  classTitle,
  className,
  description,
  location,
  timeStart,
  timeEnd,
  professor,
  semester,
  credits,
  crn,
  bgColor,
  onClose,
}: DayClassProps) {
  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-white bg-opacity-50 ">
      <div className="bg-white p-8 border rounded-lg max-w-lg w-full" style={{backgroundColor: bgColor}}>
        <button onClick={onClose} className="absolute top-4 right-4 text-x1 font-bold">&times;</button>
        <h2 className="text-xl font-bold"> {classTitle}: {className} </h2>
        <p> {classTitle}: {className} </p>
        <p> Location: {location} </p>
        <p> Professor: {professor} </p>
        <p> Semester: {semester} </p>
        <p> Credit Hours: {credits} </p>
        <p> {description} </p>
      </div>
      <div className="bg-white p-8 border rounded-lg max-w-lg w-full"> 
        <h2 className="text-xl font-bold"> Weekly Schedule: </h2>
        <p> {timeStart} - {timeEnd} </p>
      </div>
    </div>

  );
}

export default function DayClass({
  classTitle,
  className,
  description,
  location,
  timeStart,
  timeEnd,
  timeDifference,
  professor,
  semester,
  credits,
  crn,
  bgColor,
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
      {courseBlockClicked && (
        <CourseInfo 
          classTitle={classTitle}
          className={className} 
          description={description}
          location={location}
          timeStart={timeStart}
          timeEnd={timeEnd}
          timeDifference={timeDifference}
          professor={professor}
          semester={semester}
          credits={credits}
          crn={crn}
          bgColor={bgColor}
          onClose={courseBlockInfo}
        />
      )}
    </div>
  );
}

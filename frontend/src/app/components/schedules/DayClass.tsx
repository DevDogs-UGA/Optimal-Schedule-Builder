"use client";

import { type ClassData } from "../../../types/scheduleTypes";
import { useState, useEffect } from "react";

/* TODO HERE:
  1. Add logic for 
*/

interface DayClassProps extends ClassData {
  onClose?: () => void;
}

// Allows course block info window to resize itself relative to the screen size
function useResize() {
  const [width, setWidth] = useState("80vw");
  const [height, setHeight] = useState("70vh");

  useEffect(() => {
    const handleResize = () => {
      setWidth(`${window.innerWidth * 0.8}px`);
      setHeight(`${window.innerHeight * 0.7}px`);
    };
    window.addEventListener("resize", handleResize);

  }, []);

  return { width, height };
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
  color,
  onClose,
}: DayClassProps) {

  // const borderColor = "border-" + color;
  const outerBorder = `border-4 border-${color} rounded`;

  const { width, height } = useResize();

  return (

    <div 
      className={`z-40 fixed inset-0 flex items-center justify-center bg-white bg-opacity-50`}
    >
      <div
        className={`relative bg-white rounded-lg flex flex-col ${outerBorder}`}
        style={{
          width, height, maxWidth: "90vw", maxHeight: "90vh",
        }} 
      >
        {/* Header */}
        <div className={`z-50 ${color} p-8 border-b rounded-t-lg`}>
          <h2 className="font-bold text-2xl text-white">{classTitle}: {className}</h2>
        </div>

        {/* Content */}
        <div className="flex flex-1 overflow-hidden">

          {/* Course Info */}
          <div className="p-8 border-r overflow-auto w-1/2" >
            <button onClick={onClose} className="absolute top-4 right-4 text-x1 font-bold">&times;</button>
            <p> Location: {location} </p>
            <p> Professor: {professor} </p>
            <p> Semester: {semester} </p>
            <p> Credit Hours: {credits} </p>
            <p> {description} </p>
          </div>

          {/* Weekly Schedule for Class */}
          <div className="p-8 overflow-auto w-1/2"> 
            <h2 className="text-xl font-bold"> Weekly Schedule: </h2>
            <p> {timeStart} - {timeEnd} </p>
          </div>
        </div>
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
  color,
}: DayClassProps) {

  const [courseBlockClicked, setcourseBlockClicked] = useState(false);
  const courseBlockInfo = () => {
    setcourseBlockClicked(!courseBlockClicked);
  }


  return (
    <div className={`relative ${className}`} onClick={courseBlockInfo}>
      <div
        className={`w-full rounded-lg p-4 transition duration-150 ease-in-out hover:bg-black ${color} flex justify-between`}
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
          color={color}
          onClose={courseBlockInfo}
        />
      )}
    </div>
  );
}

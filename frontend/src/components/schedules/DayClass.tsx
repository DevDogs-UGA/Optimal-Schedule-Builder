"use client";
import { type ClassData } from "@/types/scheduleTypes";
import { useState, useEffect } from "react";

type DayClassProps = ClassData;

// Convert 12-hours to 24-hours
function convertToMinutes(time: string): number {
  const [hour, minute] = time.split(":").map((val) => parseInt(val, 10));
  const suffix = time.slice(-2).toLowerCase();
  let convertedTime = hour! * 60 + minute!; // The ! assers the tyoe of hour and minute as number since they could be undefined. If there's an error it means one of these is undefined.
  if (suffix === "pm" && hour !== 12) {
    convertedTime += 12 * 60;
  }
  if (suffix === "am" && hour === 12) {
    convertedTime -= 12 * 60;
  }
  return convertedTime;
}

// Duration of course for the day
function getDuration(timeStart: string, timeEnd: string): number {
  const startMinutes = convertToMinutes(timeStart);
  const endMinutes = convertToMinutes(timeEnd);
  return endMinutes - startMinutes;
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

// Creates array for week schedule display table
function getWeekLayout(
  otherTimes: string[],
  currentDay: string,
  timeStart: string,
  timeEnd: string,
  locationShort: string,
): string[] {
  // 10 strings for week display
  // 2 for each day: the time and location
  const weekInfo: string[] = ["", "", "", "", "", "", "", "", "", ""];
  const otherDays: string = otherTimes[0] ?? "";

  // Add the current day's time and location to the table
  for (let i = 0; i <= currentDay.length; i++) {
    switch (currentDay[i]) {
      case "M":
        weekInfo[0] = timeStart + " - " + timeEnd;
        weekInfo[1] = locationShort;
        break;

      case "T":
        weekInfo[2] = timeStart + " - " + timeEnd;
        weekInfo[3] = locationShort;
        break;

      case "W":
        weekInfo[4] = timeStart + " - " + timeEnd;
        weekInfo[5] = locationShort;
        break;

      case "R":
        weekInfo[6] = timeStart + " - " + timeEnd;
        weekInfo[7] = locationShort;
        break;

      case "F":
        weekInfo[8] = timeStart + " - " + timeEnd;
        weekInfo[9] = locationShort;
        break;

      default:
        break;
    }
  }

  // Add the other days the class has times on, if applicable
  for (let i = 0; i <= otherDays.length; i++) {
    switch (otherDays[i]) {
      // Monday
      case "M":
        weekInfo[0] = otherTimes[1] ?? "";
        weekInfo[1] = otherTimes[2] ?? "";
        break;
      // Tuesday
      case "T":
        weekInfo[2] = otherTimes[1] ?? "";
        weekInfo[3] = otherTimes[2] ?? "";
        break;
      // Wednesday
      case "W":
        weekInfo[4] = otherTimes[1] ?? "";
        weekInfo[5] = otherTimes[2] ?? "";
        break;
      // Thursday
      case "R":
        weekInfo[6] = otherTimes[1] ?? "";
        weekInfo[7] = otherTimes[2] ?? "";
        break;
      // Friday
      case "F":
        weekInfo[8] = otherTimes[1] ?? "";
        weekInfo[9] = otherTimes[2] ?? "";
        break;
      // Nothing left to search
      default:
        break;
    }
  }
  return weekInfo;
}

function CourseInfo({
  classTitle,
  className,
  description,
  locationLong,
  locationShort,
  prereq,
  coreq,
  professor,
  semester,
  credits,
  crn,
  // Uncomment to use parameters, currently unused
  // openSeats,
  // maxSeats,
  // waitlist,
  bgColor,
  borderColor,
  timeStart,
  timeEnd,
  currentDay,
  otherTimes,
}: DayClassProps) {
  // Borders
  const outerBorder = `border-b-2 border-r-2 border-l-2 ${borderColor} rounded-3xl`;
  const innerBorder = `border-r-2 ${borderColor}`;
  // Dynamic dimensions based on browser window size
  const { width, height } = useResize();
  // Store class times and locations for the whole week
  const weekInfo = getWeekLayout(
    otherTimes,
    currentDay,
    timeStart,
    timeEnd,
    locationShort,
  );

  return (
    <div
      className={`fixed inset-0 z-40 flex items-center justify-center bg-white bg-opacity-50`}
    >
      <div
        className={`relative flex flex-col rounded-lg bg-white ${outerBorder}`}
        style={{
          width,
          height,
          maxWidth: "90vw",
          maxHeight: "90vh",
        }}
      >
        {/* Header: Course name and CRN */}
        <div className={`relative z-50 ${bgColor} rounded-lg p-8`}>
          <div className="text-right font-bold text-white/90">
            <p>CRN: {crn}</p>
          </div>
          <h2 className="text-3xl font-bold text-white">
            {classTitle}: {className}
          </h2>
        </div>

        {/* Content */}
        <div className="flex flex-1 overflow-hidden">
          {/* Course Info:
            Lists the course's essential attributes and a description at the bottom.*/}
          <div className={`p-8 ${innerBorder} w-1/2 overflow-auto`}>
            <p>
              {" "}
              <b>Location:</b> {locationLong}{" "}
            </p>
            <p>
              {" "}
              <b>Professor:</b> {professor}{" "}
            </p>{" "}
            <br></br>
            <p>
              {" "}
              <b>Semester:</b> {semester}{" "}
            </p>
            <p>
              {" "}
              <b>Credit Hours:</b> {credits}{" "}
            </p>{" "}
            <br></br>
            <p>
              {" "}
              <b>Prerequisites:</b> {prereq}{" "}
            </p>
            <p>
              {" "}
              <b>Corequisites:</b> {coreq}{" "}
            </p>{" "}
            <br></br>
            <p> {description} </p>
          </div>

          {/* Weekly Schedule for Class:
              Right half of course block info window. Shows times and locations for the course throughout the entire week at a glance.*/}
          <div className={`w-1/2 overflow-auto p-8`}>
            <p className="text-center text-2xl font-bold underline">
              {" "}
              Weekly Schedule:{" "}
            </p>
            <br></br>
            <div className="items-center overflow-x-auto">
              <table className="w-full table-auto border border-black">
                <thead>
                  <tr>
                    <th className="border border-black p-2 text-center underline">
                      Day
                    </th>
                    <th className="border border-black p-2 text-center underline">
                      Time
                    </th>
                    <th className="border border-black p-2 text-center underline">
                      Location
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td className="border border-black p-2 text-center font-bold">
                      Monday
                    </td>
                    <td className="border border-black p-2 text-center">
                      {weekInfo[0]}
                    </td>
                    <td className="border border-black p-2 text-center">
                      {weekInfo[1]}
                    </td>
                  </tr>
                  <tr>
                    <td className="border border-black p-2 text-center font-bold">
                      Tuesday
                    </td>
                    <td className="border border-black p-2 text-center">
                      {weekInfo[2]}
                    </td>
                    <td className="border border-black p-2 text-center">
                      {weekInfo[3]}
                    </td>
                  </tr>
                  <tr>
                    <td className="border border-black p-2 text-center font-bold">
                      Wednesday
                    </td>
                    <td className="border border-black p-2 text-center">
                      {weekInfo[4]}
                    </td>
                    <td className="border border-black p-2 text-center">
                      {weekInfo[5]}
                    </td>
                  </tr>
                  <tr>
                    <td className="border border-black p-2 text-center font-bold">
                      Thursday
                    </td>
                    <td className="border border-black p-2 text-center">
                      {weekInfo[6]}
                    </td>
                    <td className="border border-black p-2 text-center">
                      {weekInfo[7]}
                    </td>
                  </tr>
                  <tr>
                    <td className="border border-black p-2 text-center font-bold">
                      Friday
                    </td>
                    <td className="border border-black p-2 text-center">
                      {weekInfo[8]}
                    </td>
                    <td className="border border-black p-2 text-center">
                      {weekInfo[9]}
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
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
  locationLong,
  locationShort,
  prereq,
  coreq,
  professor,
  semester,
  credits,
  crn,
  openSeats,
  maxSeats,
  waitlist,
  bgColor,
  borderColor,
  timeStart,
  timeEnd,
  timeDifference,
  currentDay,
  otherTimes,
}: DayClassProps) {
  // Find course duartion
  const duration = getDuration(timeStart, timeEnd);

  // Position of class block based on start time
  const startPosition = `${timeDifference ? `${timeDifference * 0.8}px` : "0px"}`;

  // Height of class block based on duration
  const classHeight = `${duration * 0.9}px`;

  // Open course block info popup
  const [courseBlockClicked, setcourseBlockClicked] = useState(false);
  const courseBlockInfo = () => {
    setcourseBlockClicked(!courseBlockClicked);
  };

  return (
    <div className={`relative ${className}`} onClick={courseBlockInfo}>
      <div
        className="absolute inset-0 border-r-2 bg-gray-100"
        style={{
          height: "100%",
          width: "100%",
          backgroundSize: "100% 60px",
        }}
      ></div>

      <div
        className={`w-full rounded-lg p-4 transition duration-150 ease-in-out hover:bg-black ${bgColor} flex items-center justify-between`}
        style={{
          position: "absolute",
          top: startPosition, //timeDifference ? `${timeDifference * 0.9}px` : "0px",
          height: classHeight,
        }}
      >
        <div>
          <h2 className="font-bold text-white">{classTitle}</h2>
          {locationShort && (
            <p className="text-sm text-white/90">{locationShort}</p>
          )}
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
          locationLong={locationLong}
          locationShort={locationShort}
          prereq={prereq}
          coreq={coreq}
          professor={professor}
          semester={semester}
          credits={credits}
          crn={crn}
          openSeats={openSeats}
          maxSeats={maxSeats}
          waitlist={waitlist}
          bgColor={bgColor}
          borderColor={borderColor}
          timeStart={timeStart}
          timeEnd={timeEnd}
          timeDifference={timeDifference}
          currentDay={currentDay}
          otherTimes={otherTimes}
        />
      )}
    </div>
  );
}

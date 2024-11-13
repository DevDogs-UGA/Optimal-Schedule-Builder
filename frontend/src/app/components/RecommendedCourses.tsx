"use client";

import { RecommendedCourse } from "./RecommendedCourse";
import { Button } from "./ui/Button";

export function RecommendedCourses() {
  function handleClick() {
    let i = 2;
    console.log(i);
  }

  function handleClick2() {
    console.log("Test");
  }

  return (
    <div className="w-[28rem] border-2 border-[#CDB3B8] bg-white">
      <div className="my-4">
        <h1 className="mb-8 text-center text-2xl font-extrabold">
          Recommended Courses
        </h1>
        <div className="relative mx-auto my-auto flex w-96 flex-col gap-y-4">
          <RecommendedCourse
            course="MATH 2270"
            courseNumber="834128"
            professor="Person Person"
            handleClick={() => handleClick()}
          />
          <RecommendedCourse
            course="ECOL 2010"
            courseNumber="491753"
            professor="PersonWithLongName"
            handleClick={() => handleClick2()}
          />
          <RecommendedCourse
            course="STAT 2010"
            courseNumber="548105"
            professor="Person PersonWithAReallyLongName"
            handleClick={() => handleClick()}
          />
          <RecommendedCourse
            course="IDEA 2260"
            courseNumber="804832"
            professor="Person"
            handleClick={() => handleClick2()}
          />
        </div>
      </div>
    </div>
  );
}

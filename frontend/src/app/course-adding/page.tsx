"use client";

import { SearchFilter } from "@/components/Filters";
import { Navbar } from "@/components/Navbar";
import { AddCourses } from "@/components/courses/AddCourses";
import CourseDisplay from "@/components/courses/CourseDisplay";
import type { Course } from "@/schemas/serverQueries";
import Link from "next/link";
import { useCallback, useState } from "react";

export default function Courses() {
  const [courses, setCourses] = useState<Course[]>([]);

  const handleAddCourse = useCallback((course: Course) => {
    setCourses((courses) => [
      // Forbid more than one of the same course
      ...courses.filter((c) => c.courseId !== course.courseId),
      course,
    ]);
  }, []);

  const handleRemoveCourse = useCallback((course: Course) => {
    setCourses((courses) =>
      courses.filter((c) => c.courseId !== course.courseId),
    );
  }, []);

  return (
    <div className="min-h-screen">
      <Navbar />
      {/* Wrapper for the two headings, Add Courses, Couse Display, and Search Filter components */}
      <div className="px-4 pb-4 pt-8 md:px-12 lg:px-24">
        <div className="flex flex-col gap-8">
          <div className="flex flex-col gap-8 md:flex-row">
            <div className="w-full md:w-2/3">
              <h1 className="p-2 pl-1 text-center text-3xl font-black md:text-left">
                Add Courses
              </h1>
              <AddCourses onAddCourse={handleAddCourse} />
            </div>
            <div className="w-full md:w-1/3">
              <h1 className="p-2 pl-1 text-center text-3xl font-black md:text-left">
                Courses
              </h1>
              <CourseDisplay
                courses={courses}
                onRemoveCourse={handleRemoveCourse}
              />
            </div>
          </div>
          <div>
            <SearchFilter />
          </div>
          <section className="flex flex-col gap-3 sm:flex-row sm:justify-center">
            <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
              <Link href={"/generate-schedule"}>Generate Schedule</Link>
            </button>
          </section>
        </div>
      </div>
    </div>
  );
}

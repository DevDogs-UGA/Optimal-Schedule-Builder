"use client";

import type { Course } from "@/schemas/serverQueries";
import { useCallback, useState } from "react";
import { PiCaretDownBold } from "react-icons/pi";
import { AddCourses } from "./AddCourses";
import RegisteredClass from "./RegisteredClass";

interface Props {
  /**
   *  The current URL search params.
   */
  searchParams: Record<string, string | string[] | undefined>;
}

export default function CourseDisplay({ searchParams }: Props) {
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
    <section className="flex flex-col gap-8 md:flex-row">
      <div className="w-full md:w-2/3">
        <h1 className="p-2 pl-1 text-center text-3xl font-black md:text-left">
          Add Courses
        </h1>
        <AddCourses searchParams={searchParams} onAddCourse={handleAddCourse} />
      </div>
      <div className="w-full md:w-1/3">
        <h1 className="p-2 pl-1 text-center text-3xl font-black md:text-left">
          Courses
        </h1>
        <div className="relative">
          {/* Container for course list */}
          <div className="no-scrollbar relative flex flex-1 flex-col overflow-x-hidden overflow-y-scroll scroll-smooth border-4 border-dusty-pink bg-white py-4">
            {/*Course item */}
            {courses.map((course) => (
              <RegisteredClass
                key={course.courseId}
                subject={course.subject}
                courseNumber={course.courseNumber}
                courseName={course.athenaTitle}
                onClick={() => handleRemoveCourse(course)}
              />
            ))}
          </div>
          {/* Styling div to add spacing and hold down arrow notification in case there is more items */}
          <div className="absolute bottom-0 left-0 flex h-6 w-full justify-center border-4 border-t-0 border-dusty-pink bg-white">
            {courses.length > 5 && (
              <PiCaretDownBold className="size-30 h-auto w-auto animate-bounce" />
            )}
          </div>
        </div>
      </div>
    </section>
  );
}

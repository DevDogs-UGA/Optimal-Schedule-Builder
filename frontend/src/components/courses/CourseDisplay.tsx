"use client";

import type { Course } from "@/schemas/serverQueries";
import { PiCaretDownBold } from "react-icons/pi";
import RegisteredClass from "./RegisteredClass";

interface Props {
  courses: Course[];
  onRemoveCourse?: (course: Course) => void;
}

export default function CourseDisplay({ courses, onRemoveCourse }: Props) {
  return (
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
            onClick={() => onRemoveCourse?.(course)}
          />
        ))}
      </div>
      {/* Styling div to add spacing and hold down arrow notification in case there is more items */}
      <div className="absolute bottom-0 left-0 flex h-6 w-full justify-center border-4 border-t-0 border-dusty-pink bg-white">
        {courses.length > 5 && (
          <PiCaretDownBold className="h-auto w-auto animate-bounce size-30" />
        )}
      </div>
    </div>
  );
}

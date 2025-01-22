import React, { useEffect, useState } from "react";
import RegisteredClass from "./RegisteredClass";
import Image from "next/Image";

//TODO: replace dummy courseData
//Depending on how the data is stored and the way it is stored, this component will have to be adjusted

//Dummy Data
const courseData = [
  {
    subject: "CSCI",
    courseNumber: "2370",
    courseName: "Data Structures",
  },
  {
    subject: "MATH",
    courseNumber: "2200",
    courseName: "Calculus",
  },
  {
    subject: "PHYS ",
    courseNumber: "1211/1221L",
    courseName: "Principle of Physics for Scientists and Engineers ",
  },
  {
    subject: "Subject",
    courseNumber: "0",
    courseName: "Class Name",
  },
  {
    subject: "Subject",
    courseNumber: "0",
    courseName: "Class Name",
  },
  {
    subject: "Subject",
    courseNumber: "0",
    courseName: "Class Name",
  },
];

//Display lists of selected courses
const CourseDisplay = () => {
  useEffect(() => {
    setCourseList(courseData);
  }, [courseData]);

  const removeCourse = (index: number) => {
    setCourseList(courseList.filter((_, i) => i !== index));
  };

  const [courseList, setCourseList] = useState(courseData);

  return (
    <div className="relative">
      {/* Container for course list */}
      <div className="no-scrollbar relative flex h-80 flex-1 flex-col overflow-y-scroll scroll-smooth border-4 border-dusty-pink bg-white py-4">
        {/*Course item */}
        {courseList.map((course, index) => (
          <RegisteredClass
            key={index}
            coursePrefix={course.subject}
            classNumber={course.courseNumber}
            fullClassName={course.courseName}
            onClick={() => removeCourse(index)}
          />
        ))}
      </div>
      {/* Styling div to add spacing and hold down arrow notification in case there is more items */}
      <div className="absolute bottom-0 left-0 flex h-6 w-full justify-center bg-white">
        {courseList.length > 5 && (
          <Image
            src="/images/downArrow.svg"
            width={30}
            height={30}
            alt="scroll down"
            className="animate-bounce object-contain"
          ></Image>
        )}
      </div>
    </div>
  );
};

export default CourseDisplay;

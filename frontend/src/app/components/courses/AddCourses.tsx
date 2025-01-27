"use client";
import { Button } from "../ui/Button";
import { SearchFilter } from "./SearchFilter";
import { useState } from "react";

interface TabProps {
  label: string;
  onClick?: () => void;
  className: string;
}

const TabButton = ({ label, onClick, className }: TabProps) => {
  return (
    <button
      onClick={onClick}
      className={`w-1/3 rounded-bl-none rounded-br-none rounded-tl-lg rounded-tr-lg px-4 py-2 text-left font-bold capitalize duration-150 ${className}`}
    >
      {label}
    </button>
  );
};

export const AddCourses = () => {
  const [currentTab, setCurrentTab] = useState(0);
  const tabs = ["section", "instructor", "crn"];
  const handleTabChange = (index: number) => {
    setCurrentTab(index);
  };

  //TODO: Replace all dummy data with their respective items
  const subjectData: string[] = [];
  const courseData: string[] = [];
  const instructorData: string[] = [];
  const CRNData: string[] = [];

  return (
    <div className="min-w-full h-full">
      <div className="flex gap-2">
        {tabs.map((tab, index) => (
          <TabButton
            key={index}
            label={`By ${tab === "crn" ? "CRN" : tab}`}
            onClick={() => handleTabChange(index)}
            className={
              currentTab === index
                ? "bg-bulldog-red text-white"
                : "bg-dusty-pink text-[#CFBEBE]"
            }
          />
        ))}
      </div>
      <main className="justify-between gap-4 border-4 border-dusty-pink bg-barely-pink px-12 py-11">
        <div className="items-center justify-center space-y-8 py-16">
          {currentTab === 0 && (
            <>
              <SearchFilter text="Subject" items={subjectData} />
              <SearchFilter text="Course" items={courseData} />
            </>
          )}
          {currentTab === 1 && (
            <>
              <SearchFilter text="Instructor" items={instructorData} />
              <SearchFilter text="Course" items={courseData} />
            </>
          )}
          {currentTab === 2 && (
            <>
              <SearchFilter text="CRN" items={CRNData} className="mb-20" />
            </>
          )}
        </div>
        <section className="flex flex-col gap-4 sm:flex-row sm:justify-end">
          <Button text="Search" className="text-white" />
          <Button text="Add" className="text-white" />
        </section>
      </main>
    </div>
  );
};

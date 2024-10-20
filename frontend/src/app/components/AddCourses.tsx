"use client";
import { Button } from "./Button";
import { SearchSelector } from "./SearchSelector";
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

  return (
    <div className="min-w-fit">
      <div className="flex gap-2">
        {tabs.map((tab, index) => (
          <TabButton
            key={index}
            label={`By ${tab}`}
            onClick={() => handleTabChange(index)}
            className={
              currentTab === index
                ? "bg-bulldog-red text-white"
                : "bg-dusty-pink text-[#CFBEBE]"
            }
          />
        ))}
      </div>
      <main className="bg-barely-pink border-dusty-pink justify-between gap-4 border-2 px-12 py-8">
        <div className="items-center justify-center space-y-4 py-12">
          {currentTab === 0 ? (
            <>
              <SearchSelector text="Subject" />
              <SearchSelector text="Course" />
            </>
          ) : currentTab === 1 ? (
            <>
              <SearchSelector text="Instructor" />
              <SearchSelector text="Course" />
            </>
          ) : currentTab === 2 ? (
            <>
              <SearchSelector text="CRN" />
            </>
          ) : null}
        </div>
        <section className="flex justify-end gap-4">
          <Button text="Search" />
          <Button text="Add" />
        </section>
      </main>
    </div>
  );
};

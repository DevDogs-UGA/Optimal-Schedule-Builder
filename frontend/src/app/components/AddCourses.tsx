"use client";
import { Button } from "./Button";
import { SearchSelector } from "./SearchSelector";
import { useState } from "react";

interface TabProps {
  text?: string;
  isActive?: boolean;
  onClick?: () => void;
}

const TabButton = ({ text, isActive, onClick }: TabProps) => {
  return (
    <Button
      className={`w-1/3 rounded-bl-none rounded-br-none capitalize`}
      text={text}
    />
  );
};

export const AddCourses = () => {
  const tabs = ["section", "instructor", "crn"];
  const [activeTab, setActiveTab] = useState(0);

  const handleChangeTab = (index) => {
    setActiveTab(index);
  };

  return (
    <div className="min-w-fit">
      <div className="flex gap-2">
        {tabs.map((tab, index) => (
          <TabButton key={index} text={`By ${tab}`} />
        ))}
      </div>
      <main className="bg-barely-pink border-dusty-pink justify-between gap-4 border-2 px-12 py-8">
        <div className="items-center justify-center space-y-4 py-12">
          <SearchSelector text="Subject" />
          <SearchSelector text="Course" />
        </div>
        <section className="flex justify-end gap-4">
          <Button text="Search" />
          <Button text="Add" />
        </section>
      </main>
    </div>
  );
};

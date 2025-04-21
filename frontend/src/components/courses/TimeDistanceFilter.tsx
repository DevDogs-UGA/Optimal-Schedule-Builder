"use client";

import { useState } from "react";
import { DropdownSearchInput } from "../ui/DropdownSearchInput";

// Create time slots from 8:00 AM to 10:00 PM
const createTimeSlots = () => {
  const times = [];
  for (let hr = 8; hr <= 22; hr++) {
    const hour = hr <= 12 ? hr : hr - 12; // 24hr to 12hr format
    const amPm = hr < 12 ? "AM" : "PM";
    times.push(`${hour}:00 ${amPm}`);
  }
  return times;
};

export function TimeDistanceFilter() {
  // Tracks selected start time
  const [startTime, setStartTime] = useState<string>("");
  // Tracks selected end time
  const [endTime, setEndTime] = useState<string>("");
  // Stores the valid end times based on selected start time
  const [availableEndTimes, setAvailableEndTimes] = useState<string[]>([]);

  const allTimeSlots = createTimeSlots();

  /* Handler function for when a start time is selected.
  / If start time sleceted, update available end times.
  / Always clears end time when start time changed. */
  const handleStartTimeSelect = (value: string) => {
    setStartTime(value); // Update start time state

    if (!value) {
      // If no start time selected
      setAvailableEndTimes([]);
      setEndTime("");
    } else {
      // If start time is selected
      const startIndex = allTimeSlots.indexOf(value);
      // Get all times after the selected start time
      const validEndTimes = allTimeSlots.slice(startIndex + 1);
      setAvailableEndTimes(validEndTimes); // Update available end times
      setEndTime("");
    }
  };

  return (
    <div className="contents">
      {/* Start Time Dropdown */}
      <DropdownSearchInput
        labelText="Start Time"
        items={allTimeSlots} // All possible time slots
        placeholder="Start Time"
        className="border-pebble-gray"
        onSelect={handleStartTimeSelect}
        selectedItem={startTime}
      />

      {/* End Time Dropdown */}
      <DropdownSearchInput
        labelText="End Time"
        key={startTime} // Forces component refresh when start time changes
        items={availableEndTimes} // Only shows valid end times
        placeholder={startTime ? "End Time" : "Select Start Time"}
        className="border-pebble-gray"
        onSelect={(value) => setEndTime(value)}
        selectedItem={endTime}
      />
    </div>
  );
}

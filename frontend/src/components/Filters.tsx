"use client";

import { useMemo, useState } from "react";
import Combobox from "./ui/Combobox";

function options<T extends string>(items: T[]) {
  return items.map((item) => ({ value: item, content: item }));
}

const timeOptions = options([
  "8 AM",
  "9 AM",
  "10 AM",
  "11 AM",
  "12 PM",
  "1 PM",
  "2 PM",
  "3 PM",
  "4 PM",
  "5 PM",
  "6 PM",
  "8 PM",
  "9 PM",
  "10 PM",
]);

const termOptions = options([
  `Spring ${new Date().getFullYear()}`,
  `Summer ${new Date().getFullYear()}`,
  `Fall ${new Date().getFullYear()}`,
]);

const campusOptions = options([
  "Athens",
  "Buckhead",
  "Griffin",
  "Gwinnett",
  "Online",
  "Tifton",
]);

export default function Filters() {
  const [startTime, setStartTime] = useState<string | undefined>(
    timeOptions[0]?.value,
  );

  const [endTime, setEndTime] = useState<string | undefined>(
    timeOptions[timeOptions.length - 1]?.value,
  );

  const startTimeOptions = useMemo(
    () =>
      timeOptions.slice(
        0,
        timeOptions.findIndex((time) => time?.value === endTime),
      ),
    [endTime],
  );

  const endTimeOptions = useMemo(
    () =>
      timeOptions.slice(
        timeOptions.findIndex((time) => time?.value === startTime) + 1,
      ),
    [startTime],
  );

  const [minCreditHours, setMinCreditHours] = useState<number>(12);
  const [maxCreditHours, setMaxCreditHours] = useState<number>(18);

  return (
    <section className="flex flex-col gap-6 rounded-lg border-2 border-[#A79A9D] bg-barely-pink px-4 py-6 sm:gap-9 sm:px-8 sm:py-9">
      <h1 className="w-full text-center text-xl font-bold sm:text-2xl lg:text-left">
        Filters
      </h1>

      <fieldset className="grid grid-cols-[1fr_2fr] gap-x-3 gap-y-4 text-right text-sm sm:grid-cols-[repeat(2,1fr_2fr)] sm:gap-y-6 md:text-base lg:grid-flow-col lg:grid-cols-[repeat(3,1fr_2fr)] lg:grid-rows-3">
        <label className="col-span-2 grid grid-cols-subgrid items-center">
          <span className="pl-3 text-right font-bold">Start Time</span>
          <Combobox
            defaultValue={startTime}
            name="prefStartTime"
            onChange={setStartTime}
            options={startTimeOptions}
            preserveOrdering
            required
            searchPlaceholder="Search Start Times"
            selectPlaceholder="Select a Start Time"
          />
        </label>

        <label className="col-span-2 grid grid-cols-subgrid items-center">
          <span className="pl-3 text-right font-bold">End Time</span>
          <Combobox
            defaultValue={endTime}
            name="prefEndTime"
            onChange={setEndTime}
            options={endTimeOptions}
            preserveOrdering
            required
            searchPlaceholder="Search End Times"
            selectPlaceholder="Select an End Time"
          />
        </label>

        <div className="contents grid-cols-subgrid items-center lg:col-span-2 lg:grid">
          <label className="col-span-full flex items-center justify-center gap-4 border-b-2 border-pebble-gray/40 pb-4 text-neutral-700 hover:text-black sm:pb-6 lg:col-start-2 lg:justify-start lg:border-none lg:pb-0">
            <input
              className="form-checkbox size-6 rounded-md border-2 border-limestone text-bulldog-red hover:border-pebble-gray focus:ring-bulldog-red"
              name="walking"
              type="checkbox"
            />
            <span className="text-balance text-left leading-tight">
              Walking Distance Between Classes
            </span>
          </label>
        </div>

        <label className="col-span-2 grid grid-cols-subgrid items-center">
          <span className="pl-3 text-right font-bold">Term</span>
          <Combobox
            name="term"
            options={termOptions}
            preserveOrdering
            required
            searchPlaceholder="Search Open Terms"
            selectPlaceholder="Select a Term"
          />
        </label>

        <label className="col-span-2 grid grid-cols-subgrid items-center">
          <span className="pl-3 text-right font-bold">Campus</span>
          <Combobox
            name="inputCampus"
            options={campusOptions}
            required
            searchPlaceholder="Search Campuses"
            selectPlaceholder="Select a Campus"
          />
        </label>

        <div className="contents grid-cols-subgrid items-center lg:col-span-2 lg:grid">
          <label className="col-span-full flex items-center justify-center gap-4 border-b-2 border-pebble-gray/40 pb-4 text-neutral-700 hover:text-black sm:pb-6 lg:col-start-2 lg:justify-start lg:border-none lg:pb-0">
            <input
              className="form-checkbox size-6 rounded-md border-2 border-limestone text-bulldog-red hover:border-pebble-gray focus:ring-bulldog-red"
              name="showFilledClasses"
              type="checkbox"
            />
            <span className="text-balance text-left leading-tight">
              Include Waitlisted Course Sections
            </span>
          </label>
        </div>

        <label className="col-span-2 grid grid-cols-subgrid items-center">
          <span className="pl-3 text-right font-bold">Min Credit Hours</span>
          <input
            className="flex w-full items-center gap-6 rounded-md border-2 border-limestone bg-white px-3 py-1.5 transition-[box-shadow,border-color] disabled:cursor-not-allowed disabled:opacity-60 [&:not(:disabled):hover]:border-pebble-gray [&:not(:disabled):hover]:shadow-sm"
            min={1}
            max={maxCreditHours - 1}
            name="minCreditHours"
            onChange={(e) => setMinCreditHours(parseInt(e.currentTarget.value))}
            required
            type="number"
            value={minCreditHours}
          />
        </label>

        <label className="col-span-2 grid grid-cols-subgrid items-center">
          <span className="pl-3 text-right font-bold">Max Credit Hours</span>
          <input
            className="flex w-full items-center gap-6 rounded-md border-2 border-limestone bg-white px-3 py-1.5 transition-[box-shadow,border-color] disabled:cursor-not-allowed disabled:opacity-60 [&:not(:disabled):hover]:border-pebble-gray [&:not(:disabled):hover]:shadow-sm"
            min={minCreditHours + 1}
            max={18}
            name="maxCreditHours"
            onChange={(e) => setMaxCreditHours(parseInt(e.currentTarget.value))}
            required
            type="number"
            value={maxCreditHours}
          />
        </label>
      </fieldset>
    </section>
  );
}

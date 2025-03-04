import { useEffect, useState } from "react";
import { DropdownSearchInput } from "./ui/DropdownSearchInput";
import { DropdownTagInput } from "./ui/DropdownTagInput";

interface props {
  text?: string;
}

//TODO: replace dummy data placeholders with respective items for dropdowns
const termList = [
  `Fall ${new Date().getFullYear()}`,
  `Spring ${new Date().getFullYear() + 1}`,
  `Summer ${new Date().getFullYear() + 1}`,
];
const instructionlist = [
  "Lecture",
  "Seminar",
  "Lab",
  "Online",
  "Hybrid",
  "Workshops",
  "Recitations",
  "Studio",
  "Independent Study",
  "Fieldwork",
];
const campusList = [
  "Athens",
  "Buckhead",
  "Griffin",
  "Gwinnett",
  "Online",
  "Tifton",
];
const educationLevelList = ["Undergraduate", "Graduate"];
let minmHourList: string[];
let maxmHourList: string[];
const hours = ["1 hour", "2 hours", "3 hours", "4 hours", "5 hours", "6 hours", "7 hours", "8 hours", "9 hours", "10 hours", "11 hours", "12 hours", "13 hours", "14 hours", "15 hours", "16 hours", "17 hours", "18 hours", "19 hours", "20 hours", "21 hours", "22 hours", "23 hours", "24 hours"];

export const SearchFilter = ({}: props) => {
  const [minmHours, setSelectedMinmHours] = useState<string | null>();
  const [maxmHours, setSelectedMaxmHours] = useState<string | null>();

  // State for filtered dropdown lists
  const [minmHourList, setMinmHourList] = useState<string[]>(hours);
  const [maxmHourList, setMaxmHourList] = useState<string[]>(hours);

  // Update dropdown lists when minmHours changes
  useEffect(() => {
    if (minmHours !== null) {
      setMaxmHourList((prev) => [...hours.slice(Number(minmHours?.split(" ")[0]))]);
    } else {
      setMaxmHourList(hours);
    }
  }, [minmHours]);

  // Update dropdown lists when maxmHours changes
  useEffect(() => {
    if (maxmHours !== null) {
      setMinmHourList([...hours.slice(0, Number(maxmHours?.split(" ")[0]))]); 
    } else {
      setMinmHourList(hours); 
    }
  }, [maxmHours]);


  return (
    <div>
      <div className="flex flex-col border-4 border-dusty-pink bg-barely-pink p-2">
        {" "}
        {/* Div for Whole Filter Component*/}
        <h1 className="m-4 text-center text-lg font-extrabold sm:m-2 sm:ml-8 sm:mt-8 sm:w-full sm:text-left sm:text-2xl sm:font-extrabold">
          Filters
        </h1>{" "}
        {/* h1 For Filter Title */}
        <div className="sm:flex sm:flex-row sm:justify-around sm:px-8 sm:py-4">
          {" "}
          {/* Div for filters*/}
          <div className="m-4 grid grid-cols-2 justify-center gap-4 sm:flex sm:flex-col lg:grid lg:grid-cols-2">
            {" "}
            {/* Div For Column 1*/}
            <div className="text-right md:text-left">
              {" "}
              {/* Course Status Label */}
              <label htmlFor="open" className="text-right font-extrabold">
                Course Status:
              </label>
            </div>
            <div className="flex flex-col space-y-5">
              {" "}
              {/* Div For Checkboxes */}
              <div className="font-bold sm:flex sm:items-center sm:space-x-3">
                <input
                  title="open"
                  type="checkbox"
                  id="open"
                  name="open"
                  className="h-5 w-5 cursor-pointer rounded-full accent-red-700 peer-checked:opacity-100"
                ></input>
                <label htmlFor="open" className="text-black-700 m-1">
                  Open
                </label>
              </div>
              <div className="font-bold sm:flex sm:items-center sm:space-x-3">
                <input
                  title="waitlist"
                  type="checkbox"
                  id="waitlist"
                  name="waitlist"
                  className="h-5 w-5 cursor-pointer rounded-full accent-red-700 peer-checked:opacity-100"
                ></input>
                <label htmlFor="sm:waitlist" className="sm:text-black-700 m-1">
                  Waitlist
                </label>
              </div>
              <div className="font-bold sm:flex sm:items-center sm:space-x-3">
                <input
                  title="closed"
                  type="checkbox"
                  id="closed"
                  name="closed"
                  className="h-5 w-5 cursor-pointer rounded-full accent-red-700 peer-checked:opacity-100"
                ></input>
                <label htmlFor="closed" className="sm:text-black-700 m-1">
                  Closed
                </label>
              </div>
            </div>
          </div>
          <div className="m-4 grid grid-cols-2 gap-4 sm:flex">
            {" "}
            {/* Div For Column 2 */}
            <div className="grid grid-rows-3 gap-4">
              {" "}
              {/* Div For Labels */}
              <label className="text-right font-extrabold">Term:</label>
              <label className="text-right font-extrabold">Instruction:</label>
              <label className="text-right font-extrabold">Campus:</label>
            </div>
            <div className="grid max-w-56 grid-rows-3 gap-4">
              {" "}
              {/* Div For Dropdowns */}
              <DropdownSearchInput items={termList} placeholder="Enter Term" />
              <DropdownTagInput
                items={instructionlist}
                placeholder="Enter Instruction"
              />
              <DropdownSearchInput
                items={campusList}
                placeholder="Enter Campus"
              />
            </div>
          </div>
          <div className="m-4 grid grid-cols-2 gap-4 sm:flex">
            {" "}
            {/* Div For Column 3 in Filters*/}
            <div className="grid grid-rows-3 gap-4">
              {" "}
              {/* Div For Labels */}
              <label className="text-right font-extrabold">Levels:</label>
              <label className="text-right font-extrabold">Min Credit Hours:</label>
              <label className="text-right font-extrabold">Max Credit Hours:</label>
            </div>
            <div className="grid max-w-56 grid-rows-3 gap-4">
              {" "}
              {/* Div For Dropdowns */}
              <DropdownSearchInput
                items={educationLevelList}
                placeholder="Enter Level"
              />
              <DropdownSearchInput
                items={minmHourList}
                placeholder="Enter Min Credit Hours"
                selectedItem={minmHours}
                onSelect={(value) => setSelectedMinmHours(value)} 
              />
              <DropdownSearchInput
                key={maxmHourList.length}
                items={maxmHourList}
                placeholder="Enter Max Credit Hours"
                selectedItem={maxmHours}
                onSelect={(value) => setSelectedMaxmHours(value)} 
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

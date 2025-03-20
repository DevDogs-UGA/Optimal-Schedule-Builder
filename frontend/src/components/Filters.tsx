import { DropdownSearchInput } from "./ui/DropdownSearchInput";
import DropdownTagInput from "./ui/DropdownTagInput";
import { TimeDistanceFilter } from "./courses/TimeDistanceFilter";

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
const creditHourList: string[] = [];
const buildingList: string[] = [];

const dropdownStyling = "border-pebble-gray";

export const SearchFilter = ({}: props) => {
  return (
    <>
      <div className="flex flex-col border-4 border-dusty-pink bg-barely-pink p-2">
        {" "}
        {/* Div for Whole Filter Component*/}
        <h1 className="m-4 text-center text-lg font-extrabold sm:m-2 sm:ml-8 sm:mt-8 sm:w-full sm:text-left sm:text-2xl sm:font-extrabold">
          Filters
        </h1>{" "}
        {/* h1 For Filter Title */}
        <div className="sm:flex sm:flex-row sm:justify-around sm:px-8 sm:py-4">
          {" "}
          {/* Div for filters */}
          <div className="m-4 grid grid-cols-2 gap-4 sm:flex">
            {" "}
            {/* Div For Column 1: Time and Distance Filters */}
            <TimeDistanceFilter />

            <div className="text-right">
              {" "}
              {/* Course Status Label */}
              <label htmlFor="open" className="text-right font-extrabold">
                Course Status:
              </label>
            </div>

            <div className="flex flex-col space-y-5">
              <div className="font-bold sm:flex sm:items-center sm:space-x-3">
                <input
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
            {/* Div For Column 3 */}
            <div className="grid grid-rows-3 gap-4">
              {" "}
              {/* Div For Labels */}
              <label className="text-right font-extrabold">Term:</label>
              <label className="text-right font-extrabold">Instruction:</label>
              <label className="text-right font-extrabold">Campus:</label>
            </div>
            <div className="grid grid-rows-3 gap-4">
              {" "}
              {/* Div For Dropdowns */}
              <DropdownSearchInput
                items={termList}
                placeholder="Enter Term"
                className={dropdownStyling}
              />
              <DropdownTagInput
                items={instructionlist}
                placeholder="Enter Instruction"
                className={dropdownStyling}
              />
              <DropdownSearchInput
                items={campusList}
                placeholder="Enter Campus"
                className={dropdownStyling}
              />
            </div>
          </div>
          <div className="m-4 grid grid-cols-2 gap-4 sm:flex">
            {" "}
            {/* Div For Column 4 in Filters*/}
            <div className="grid grid-rows-3 gap-4">
              {" "}
              {/* Div For Labels */}
              <label className="text-right font-extrabold">Levels:</label>
              <label className="text-right font-extrabold">Credit Hours:</label>
              <label className="text-right font-extrabold">Building:</label>
            </div>
            <div className="grid grid-rows-3 gap-4">
              {" "}
              {/* Div For Dropdowns */}
              <DropdownSearchInput
                items={educationLevelList}
                placeholder="Enter Level"
                className={dropdownStyling}
              />
              <DropdownTagInput
                items={creditHourList}
                placeholder="Enter Credit Hours"
                className={dropdownStyling}
              />
              <DropdownSearchInput
                items={buildingList}
                placeholder="Enter Building"
                className={dropdownStyling}
              />
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

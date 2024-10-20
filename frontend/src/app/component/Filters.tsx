export default function Filters() {
  //pass props as HTML attributes with argument name
  return (
    <>
      <div className="m-4 w-2/3 rounded-lg border border-gray-200 bg-[#fff1f2] p-4">
        {" "}
        {/* Filter Component*/}
        <h1 className="m-2 ml-4 w-full text-2xl font-bold">Filters</h1>{" "}
        {/* Filter Title */}
        <div className="mb-4 ml-8 mr-8 mt-4 flex flex-row justify-center space-x-36">
          {" "}
          {/* Filters*/}
          <div className="grid grid-cols-2 gap-4">
            {" "}
            {/* Column 1*/}
            <div className="">
              {" "}
              {/* Course Status Label */}
              <label htmlFor="open" className="font-bold">
                Course Status:
              </label>
            </div>
            <div className="flex flex-col space-y-5">
              {" "}
              {/* Checkboxes */}
              <div className="flex items-center space-x-3">
                <input
                  type="checkbox"
                  id="open"
                  name="open"
                  className="h-4 w-4 rounded-full accent-red-700"
                ></input>
                <label htmlFor="open" className="text-gray-700">
                  Open
                </label>
              </div>
              <div className="flex items-center space-x-3">
                <input
                  type="checkbox"
                  id="waitlist"
                  name="waitlist"
                  className="h-4 w-4 rounded-full accent-red-700"
                ></input>
                <label htmlFor="waitlist" className="text-black-700">
                  Waitlist
                </label>
              </div>
              <div className="flex items-center space-x-3">
                <input
                  type="checkbox"
                  id="closed"
                  name="closed"
                  className="h-4 w-4 rounded-full accent-red-700"
                ></input>
                <label htmlFor="closed" className="text-black-700">
                  Closed
                </label>
              </div>
            </div>
          </div>
          <div className="grid grid-cols-2 gap-4">
            {" "}
            {/* Column 2 */}
            <div className="grid grid-rows-3 gap-4">
              {" "}
              {/* Labels */}
              <label className="text-right font-bold">Term:</label>
              <label className="text-right font-bold">Instruction:</label>
              <label className="text-right font-bold">Campus:</label>
            </div>
            <div className="grid grid-rows-3 gap-4">
              {" "}
              {/* Dropdowns */}
              <Dropdown />
              <Dropdown />
              <Dropdown />
            </div>
          </div>
          <div className="grid grid-cols-2 gap-4">
            {" "}
            {/* Column 3 */}
            <div className="grid grid-rows-3 gap-4">
              {" "}
              {/* Labels */}
              <label className="text-right font-bold">Levels:</label>
              <label className="text-right font-bold">Credit Hours:</label>
              <label className="text-right font-bold">Building:</label>
            </div>
            <div className="grid grid-rows-3 gap-4">
              {" "}
              {/* Dropdowns */}
              <Dropdown />
              <Dropdown />
              <Dropdown />
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

//placeholder for dropdown
export function Dropdown() {
  return (
    <>
      <div className="w-full">
        <select name="cars" id="cars" className="text-md border bg-gray-50">
          <option value="volvo">Lorem ipsum</option>
        </select>
      </div>
    </>
  );
}

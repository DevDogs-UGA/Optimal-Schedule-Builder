interface props {
  text?: string;
}

export const SearchFilter = ({ text }: props) => {
  return (
    <>
      <div className="sborder-gray-200 p-2sm:p-4 m-3 w-auto sm:w-2/3 rounded-lg border bg-[#fff1f2] sm:m-4">
        {" "}
        {/* Filter Component*/}
        <h1 className="m-4 text-center text-lg font-bold sm:m-2 sm:ml-8 sm:mt-8 sm:w-full sm:text-left sm:text-2xl sm:font-bold">
          Filters
        </h1>
        {" "}
        {/* Filter Title */}
        <div className="sm:mb-4 sm:ml-8 sm:mr-8 sm:mt-4 sm:flex sm:flex-row sm:justify-center sm:space-x-36">
          {" "}
          {/* Filters*/}
          <div className="m-4 grid grid-cols-2 gap-4">
            {" "}
            {/* Column 1*/}
            <div className="text-right">
              {" "}
              {/* Course Status Label */}
              <label htmlFor="open" className="text-right font-bold">
                Course Status:
              </label>
            </div>
            <div className="flex flex-col space-y-5">
              {" "}
              {/* Checkboxes */}
              <div className="sm:flex sm:items-center sm:space-x-3">
                <input
                  type="checkbox"
                  id="open"
                  name="open"
                  className="h-4 w-4 rounded-full accent-red-700"
                ></input>
                <label htmlFor="open" className="text-black-700 m-1">
                  Open
                </label>
              </div>
              <div className="sm:flex sm:items-center sm:space-x-3">
                <input
                  type="checkbox"
                  id="waitlist"
                  name="waitlist"
                  className="h-4 w-4 rounded-full accent-red-700"
                ></input>
                <label htmlFor="sm:waitlist" className="sm:text-black-700 m-1">
                  Waitlist
                </label>
              </div>
              <div className="sm:flex sm:items-center sm:space-x-3">
                <input
                  type="checkbox"
                  id="closed"
                  name="closed"
                  className="h-4 w-4 rounded-full accent-red-700"
                ></input>
                <label htmlFor="closed" className="sm:text-black-700 m-1">
                  Closed
                </label>
              </div>
            </div>
          </div>
          <div className="m-4 grid grid-cols-2 gap-4">
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
          <div className="m-4 grid grid-cols-2 gap-4">
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
};

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

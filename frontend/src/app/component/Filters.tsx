export default function Filters() {
    //pass props as HTML attributes with argument name
    return (
      <>
      <div className="bg-[#fff1f2] p-4 border border-gray-200 rounded-lg m-4 w-2/3"> {/* Filter Component*/}

          <h1 className="font-bold w-full m-2 ml-4 text-2xl">Filters</h1> {/* Filter Title */}

          <div className="flex flex-row space-x-36 mt-4 mb-4 mr-8 ml-8 justify-center "> {/* Filters*/}
              <div className = "grid grid-cols-2 gap-4">  {/* Column 1*/}
                  <div className=""> {/* Course Status Label */}
                    <label htmlFor="open" className="font-bold">Course Status:</label>
                  </div> 

                  <div className="flex flex-col space-y-5"> {/* Checkboxes */}
                    <div className="flex items-center space-x-3"> 
                      <input type="checkbox" id="open" name="open" className="w-4 h-4 accent-red-700 rounded-full"></input>
                      <label htmlFor="open" className="text-gray-700">Open</label>
                    </div>

                    <div className="flex items-center space-x-3"> 
                      <input type="checkbox" id="waitlist" name="waitlist" className="w-4 h-4 accent-red-700  rounded-full"></input>
                      <label htmlFor="waitlist" className="text-black-700">Waitlist</label>
                    </div> 

                    <div className="flex items-center space-x-3">
                      <input type="checkbox" id="closed" name="closed" className="w-4 h-4 accent-red-700  rounded-full"></input>
                      <label htmlFor="closed" className="text-black-700">Closed</label>
                    </div>
                  </div>
              </div> 
              
              <div className = "grid grid-cols-2 gap-4"> {/* Column 2 */}
              
                <div className="grid grid-rows-3 gap-4 "> {/* Labels */}
                  <label className="font-bold  text-right">Term:</label>
                  <label className="font-bold  text-right">Instruction:</label>
                  <label className="font-bold  text-right">Campus:</label>
                </div>

                <div className="grid grid-rows-3 gap-4"> {/* Dropdowns */}
                  <Dropdown />
                  <Dropdown />
                  <Dropdown />
                </div>
                
              </div>

              <div className = "grid grid-cols-2 gap-4"> {/* Column 3 */}

              <div className="grid grid-rows-3 gap-4 "> {/* Labels */}
                  <label className="font-bold  text-right">Levels:</label>
                  <label className="font-bold  text-right">Credit Hours:</label>
                  <label className="font-bold  text-right">Building:</label>
                </div>

                <div className="grid grid-rows-3 gap-4"> {/* Dropdowns */}
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
          <select name="cars" id="cars" className="bg-gray-50 border text-md">
            <option value="volvo">Lorem ipsum</option>
          </select>
        </div>
      </>
    ) 
  }
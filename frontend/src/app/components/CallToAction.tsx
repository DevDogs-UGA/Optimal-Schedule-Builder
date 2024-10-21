import React from "react";

const CallToAction = () => {
  return (
    <div className="flex">
      {/* Left side label */}
      <div className="relative left-[2rem] flex items-center bg-BarelyPink px-6 py-2 rounded-full">
        <span className="text-GroutGray w-[10rem] p-2 text-left">Ready?</span>
      </div>

      {/* Right side button */}
      <button className="relative right-[2rem] flex items-center trasition ease-in-out duration-300 hover:bg-[#8b0923] bg-BulldogRed text-white px-6 py-2 rounded-full">
        <span className="mr-2 p-2 w-[10rem] text-left">Get Started</span>
        <div className="bg-white p-2 rounded-full mr-[30%]">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth={2}
            stroke="currentColor"
            className="w-4 h-4 text-Gold"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M13 10V3L4 14h7v7l9-11h-7z"
            />
          </svg>
        </div>
      </button>
    </div>
  );
};

export default CallToAction;

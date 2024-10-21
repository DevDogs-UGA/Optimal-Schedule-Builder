import React from "react";

const CallToAction = () => {
  return (
    <div className="flex">
      {/* Left side label */}
      <div className="relative left-[2rem] flex items-center rounded-full bg-BarelyPink px-6 py-2">
        <span className="w-[10rem] p-2 text-left text-GroutGray">Ready?</span>
      </div>

      {/* Right side button */}
      <button className="trasition relative right-[2rem] flex items-center rounded-full bg-BulldogRed px-6 py-2 text-white duration-300 ease-in-out hover:bg-[#8b0923]">
        <span className="mr-2 w-[10rem] p-2 text-left">Get Started</span>
        <div className="mr-[30%] rounded-full bg-white p-2">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth={2}
            stroke="currentColor"
            className="h-4 w-4 text-Gold"
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

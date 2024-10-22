import React from "react";

export function CallToAction() {
  return (
    <div className="flex">
      {/* Left side label */}
      <div className="bg-barely-pink relative left-8 flex items-center rounded-full px-6 py-2">
        <span className="text-grout-gray w-32 p-2 text-left md:w-40">
          Ready?
        </span>
      </div>

      {/* Right side button */}
      <button className="trasition bg-bulldog-red relative right-8 flex items-center rounded-full px-6 py-2 text-white duration-300 ease-in-out hover:bg-[#8b0923]">
        <span className="mr-2 w-24 text-left font-bold md:w-40 md:p-2">
          Get Started
        </span>
        <div className="rounded-full bg-white p-2">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth={2}
            stroke="currentColor"
            className="text-gold h-4 w-4"
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
}

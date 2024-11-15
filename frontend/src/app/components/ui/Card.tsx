"use client";

import { Button } from "./Button";

interface props {
  image?: string;
  heading?: string;
  text?: string;
  handleClick?: () => void;
}

export function Card({ image, heading, text, handleClick }: props) {
  return (
    <div className="flex flex-col items-center">
      <div className="h-1 w-full max-w-lg mx-auto bg-[#B3072A]"></div>
      <div className="relative flex w-full max-w-md flex-col items-center justify-center mx-auto bg-[#fffafa] bg-[url('/images/paws.svg')] p-8 pb-32 sm:max-w-lg sm:p-16 overflow-hidden">
        {/* Image */}
        {image &&(
          <div className="p-4 pt-0 sm:p-8">
            <img className="min-h-24 sm:min-h-36" src={image} alt="Image" />
          </div>
        )}
        {/* Heading */}
        <div>
          <h1 className="mt-0 text-center text-2xl font-extrabold sm:mt-8">
            {heading}
          </h1>
        </div>
        {/* Text */}
        <div>
          <p className="m-2 text-center text-md leading-6 text-[#151618]">
            {text}
          </p>
        </div>
        {/* Button (handleClick) */}
        <div>
          <Button
            onClick={handleClick}
            className="m-4 border-2 text-[#FFFAFA] border-[#B3072A] active:bg-[#FFFAFA] active:text-[#B3072A] sm:m-8"
            text="OK"
          />
        </div>
      </div>
    </div>
  );
}

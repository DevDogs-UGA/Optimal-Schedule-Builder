"use client";
import { useState } from "react";
import { Button } from "./Button";

export function Card({
  image,
  heading,
  text,
  handleClick,
}: {
  image: any;
  heading: any;
  text: any;
  handleClick: any;
}) {
  return (
    <div>
      <div className="h-1 w-64 bg-[#B3072A] sm:w-96"></div>
      <div className="relative flex w-64 flex-col items-center justify-center bg-[#fffafa] bg-[url('/images/paws.svg')] p-8 pb-4 sm:w-96 sm:p-16 sm:pb-8">
        <div className="p-4 pt-0 sm:p-8">
          <img className="min-h-24 sm:min-h-36" src={image} alt="Image" />
        </div>
        <div>
          <h1 className="mt-0 text-center text-2xl font-extrabold sm:mt-8">
            {heading}
          </h1>
        </div>
        <div>
          <p className="m-2 text-center text-sm leading-4 text-[#151618]">
            {text}
          </p>
        </div>
        <div>
          <Button
            onClick={handleClick}
            className="m-4 border-2 border-[#B3072A] active:bg-[#FFFAFA] active:text-[#B3072A] sm:m-8"
            text="OK"
          />
          {/* <button
            className="z-20 m-4 border-2 border-[#B3072A] bg-[#B3072A] px-24 py-4 text-[#FFFAFA] active:bg-[#FFFAFA] active:text-[#B3072A] sm:m-8"
            onClick={() => handleClick()}
          >
            Ok
          </button> */}
        </div>
        {/* <img src="/images/blackpaw.svg" className="absolute h-52 sm:h-80 opacity-10 top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 z-0"></img> */}
      </div>
    </div>
  );
}

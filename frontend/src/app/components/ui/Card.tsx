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
        {/* Image */}
        <div className="p-4 pt-0 sm:p-8">
          <img className="min-h-24 sm:min-h-36" src={image} alt="Image" />
        </div>
        {/* Heading */}
        <div>
          <h1 className="mt-0 text-center text-2xl font-extrabold sm:mt-8">
            {heading}
          </h1>
        </div>
        {/* Text */}
        <div>
          <p className="m-2 text-center text-sm leading-4 text-[#151618]">
            {text}
          </p>
        </div>
        {/* Button (handleClick */}
        <div>
          <Button
            onClick={handleClick}
            className="m-4 border-2 border-[#B3072A] active:bg-[#FFFAFA] active:text-[#B3072A] sm:m-8"
            text="OK"
          />
        </div>
      </div>
    </div>
  );
}

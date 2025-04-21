"use client";

import { Button } from "./Button";
import Image from "next/image";

interface props {
  image?: string;
  heading?: string;
  text?: string;
  handleClick?: () => void;
}

export function Card({ image, heading, text, handleClick }: props) {
  return (
    <div className="flex flex-col items-center">
      <div className="mx-auto h-1 w-full max-w-lg bg-[#B3072A]"></div>
      <div className="relative mx-auto flex w-full max-w-md flex-col items-center justify-center overflow-hidden bg-[#fffafa] bg-[url('/images/paws.svg')] p-8 pb-32 sm:max-w-lg sm:p-16">
        {/* Image */}
        {image && (
          <div className="p-4 pt-0 sm:p-8">
            <Image src={image} alt="Image" height={100} width={100} />
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
          <p className="text-md m-2 text-center leading-6 text-[#151618]">
            {text}
          </p>
        </div>
        {/* Button (handleClick) */}
        <div>
          <Button
            onClick={handleClick}
            className="m-4 border-2 border-[#B3072A] text-[#FFFAFA] active:bg-[#FFFAFA] active:text-[#B3072A] sm:m-8"
            text="OK"
          />
        </div>
      </div>
    </div>
  );
}

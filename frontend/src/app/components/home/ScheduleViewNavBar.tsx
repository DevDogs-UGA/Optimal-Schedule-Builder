"use client";

import React, { useState } from "react";
import Image from "next/image";
import Link from "next/link";

export function ScheduleViewNavBar() {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);

  const toggleNavbar = (): void => {
    setIsDropdownOpen(!isDropdownOpen);
  };

  return (
    <div className="relative z-[999] w-full">
      <div className="fixed left-1/2 top-0 flex h-[4.5rem] w-full -translate-x-1/2 items-center justify-between rounded-none border border-[#F8E6EA] border-opacity-40 bg-[#F8E6EA] bg-opacity-80 shadow-lg shadow-black/[0.03] backdrop-blur-[0.5rem] sm:top-6 sm:h-[4rem] sm:w-[36rem] sm:rounded-full md:h-[4rem] md:w-[45rem] lg:w-[60rem] xl:w-[80rem]">
        {/* Logo and Title */}
        <div className="flex items-center space-x-2 pl-8">
          <Link href="/" className="flex items-center gap-3">
            <Image
              className="block"
              src="/images/blackpaw.svg"
              width={40}
              height={40}
              alt="black paw"
            />
            <h1 className="text-[30px] font-[600]">DogDays</h1>
          </Link>
        </div>

        {/* Hamburger button for mobile */}
        <div className="pr-5 sm:hidden">
          <button onClick={toggleNavbar}>
            {isDropdownOpen ? (
              <Image
                width="30"
                height="30"
                alt="expanded icon"
                src="/images/expanded.svg"
              />
            ) : (
              <Image
                width="30"
                height="30"
                alt="dropdown icon"
                src="/images/dropdown.svg"
              />
            )}
          </button>
        </div>

        {/* Desktop Menu */}
        <div className="mr-8 hidden flex-nowrap items-center justify-around gap-8 text-[20px] font-bold sm:flex">
          <Link href="/">
            <button className="rounded-full px-2 py-1 transition duration-300 ease-in-out hover:bg-bulldog-red/50 md:px-4">
              Modify
            </button>
          </Link>
          <Link href="/">
            <button className="rounded-full px-2 py-1 transition duration-300 ease-in-out hover:bg-bulldog-red/50 md:px-4">
              Shuffle
            </button>
          </Link>
          <Link href="/">
            <button className="rounded-full px-2 py-1 transition duration-300 ease-in-out hover:bg-bulldog-red/50 md:px-4">
              List View
            </button>
          </Link>
        </div>

        {/* Mobile Dropdown */}
        {isDropdownOpen && (
          <div className="absolute left-0 top-[4.5rem] z-[998] w-full flex-col items-center justify-start rounded-b-lg bg-[#F8E6EA] shadow-lg sm:hidden">
            <Link href="/">
              <button className="block w-full px-4 py-3 text-left text-[1.2rem] font-bold transition duration-300 ease-in-out hover:bg-bulldog-red/50">
                Modify
              </button>
            </Link>
            <Link href="/">
              <button className="block w-full px-4 py-3 text-left text-[1.2rem] font-bold transition duration-300 ease-in-out hover:bg-bulldog-red/50">
                Shuffle
              </button>
            </Link>
            <Link href="/">
              <button className="block w-full px-4 py-3 text-left text-[1.2rem] font-bold transition duration-300 ease-in-out hover:bg-bulldog-red/50">
                List View
              </button>
            </Link>
          </div>
        )}
      </div>
    </div>
  );
}

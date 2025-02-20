"use client";

import React, { useState, useEffect } from "react";
import Image from "next/image";
import Link from "next/link";
import Notifications from "./Notifications";
import { PiGear, PiGearFill } from "react-icons/pi";

// Custom hook for scroll direction
function useScrollDirection() {
  const [scrollDirection, setScrollDirection] = useState("up");
  const [prevScroll, setPrevScroll] = useState(0);

  useEffect(() => {
    const handleScroll = () => {
      const currentScroll = window.scrollY;

      // Only trigger hide/show after scrolling a bit (e.g., 10px)
      if (Math.abs(currentScroll - prevScroll) < 10) return;

      const direction = currentScroll > prevScroll ? "down" : "up";
      if (direction !== scrollDirection) {
        setScrollDirection(direction);
      }
      setPrevScroll(currentScroll > 0 ? currentScroll : 0);
    };

    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, [scrollDirection, prevScroll]);

  return scrollDirection;
}

export function Navbar({ keyword = "default" }) {
  const scrollDirection = useScrollDirection();

  let navContent;

  // This switch statement allows the Navbar to be conditionally rendered based on the needs of specific pages
  // For the default Navbar, give the component a prop of keyword = "default" or leave it off
  // For specific pages, like /schedules, keyword = "schedules"
  // Add more variants with more switch cases
  switch (keyword) {
    // Default Navbar
    case "default":
      navContent = (
        <>
          {/* Logo and Title */}
          <Link
            href="/"
            className="tems-center flex gap-2 bg-inherit py-4 sm:gap-4 sm:py-0"
          >
            <Image
              className="block fill-current invert"
              src="/images/blackpaw.svg"
              width={40}
              height={40}
              alt="white paw"
            />
            <h1 className="text-3xl font-bold text-white">DogDays</h1>
          </Link>
          {/* Space filler div */}
          <div className="flex-1"></div>

          {/* Navbar options for default navbar */}
          <ul className="absolute left-0 top-full -z-10 flex w-full -translate-y-full flex-col flex-nowrap items-center border-[#222233]/40 bg-[#222233]/80 pb-3 text-xl font-semibold opacity-0 shadow-lg backdrop-blur-[0.5rem] transition-all group-data-[state=open]:translate-y-0 group-data-[state=open]:opacity-100 sm:relative sm:z-auto sm:w-auto sm:translate-y-0 sm:flex-row sm:justify-end sm:gap-[inherit] sm:p-0 sm:opacity-100 sm:shadow-none">
            <li className="contents">
              <Link
                className="px-6 py-3 text-center text-white transition hover:text-[#e4212b] sm:rounded-lg sm:py-1 md:px-4"
                href="/saved-plans"
              >
                My Plans
              </Link>
            </li>

            <li className="contents">
              <Link href="/settings">
                <PiGear
                  size={40}
                  className="fill-white transition hover:fill-[#e4212b]"
                />
              </Link>
            </li>
          </ul>

          {/* Navbar options for mobile default */}
          <div className="relative flex flex-row-reverse items-center justify-around text-xl font-semibold sm:hidden sm:flex-row">
            {/* Hamburger button for mobile */}

            <Link href="/settings">
              <PiGear
                size={40}
                className="fill-white transition hover:fill-[#e4212b]"
              />
            </Link>

            <Link
              className="px-2 py-3 text-center text-white transition hover:text-[#e4212b] sm:hidden sm:rounded-lg sm:py-1 md:px-4"
              href="/saved-plans"
            >
              My Plans
            </Link>
          </div>
        </>
      );
      break;

    // Navbar for schedules Page
    case "schedules":
      navContent = (
        <>
          {/* Logo and Title */}
          <Link
            href="/"
            className="tems-center flex gap-2 bg-inherit py-4 sm:gap-4 sm:py-0"
          >
            <Image
              className="block fill-current invert"
              src="/images/blackpaw.svg"
              width={40}
              height={40}
              alt="white paw"
            />
            <h1 className="text-3xl font-bold text-white">DogDays</h1>
          </Link>

          {/* Space filler div */}
          <div className="flex-1"></div>

          {/* Navbar Options for schedules page*/}
          <ul className="absolute left-0 top-full -z-10 flex w-full -translate-y-full flex-col flex-nowrap items-center border-[#222233]/40 bg-[#222233]/80 pb-3 text-xl font-semibold opacity-0 shadow-lg backdrop-blur-[0.5rem] transition-all group-data-[state=open]:translate-y-0 group-data-[state=open]:opacity-100 sm:relative sm:z-auto sm:w-auto sm:translate-y-0 sm:flex-row sm:justify-end sm:gap-[inherit] sm:p-0 sm:opacity-100 sm:shadow-none">
            <li className="contents">
              <Link
                className="px-6 py-3 text-center text-white transition hover:text-[#e4212b] sm:rounded-lg sm:py-1 md:px-4"
                href="/"
              >
                Modify
              </Link>
            </li>

            <li className="contents">
              <Link
                className="px-6 py-3 text-center text-white transition hover:text-[#e4212b] sm:rounded-lg sm:py-1 md:px-4"
                href="/"
              >
                Shuffle
              </Link>
            </li>

            <li className="contents">
              <Link
                className="px-6 py-3 text-center text-white transition hover:text-[#e4212b] sm:rounded-lg sm:py-1 md:px-4"
                href="/"
              >
                List View
              </Link>
            </li>

            <li className="contents">
              <Link href="/settings">
                <PiGear
                  size={40}
                  className="fill-white transition hover:fill-[#e4212b]"
                />
              </Link>
            </li>
          </ul>

          {/* Navbar Options for mobile on schedules page */}
          <div className="relative flex flex-row-reverse items-center justify-around text-xl font-semibold sm:hidden sm:flex-row">
            {/* Hamburger button for mobile */}

            <Link href="/settings">
              <PiGear
                size={40}
                className="fill-white transition hover:fill-[#e4212b]"
              />
            </Link>

            <Link
              className="px-2 py-3 text-center text-white transition hover:text-[#e4212b] sm:hidden sm:rounded-lg sm:py-1 md:px-4"
              href="/"
            >
              List View
            </Link>

            <Link
              className="px-2 py-3 text-center text-white transition hover:text-[#e4212b] sm:hidden sm:rounded-lg sm:py-1 md:px-4"
              href="/"
            >
              Shuffle
            </Link>

            <Link
              className="px-2 py-3 text-center text-white transition hover:text-[#e4212b] sm:hidden sm:rounded-lg sm:py-1 md:px-4"
              href="/"
            >
              Modify
            </Link>
          </div>
        </>
      );
      break;
  }

  return (
    <nav
      className="data group sticky z-[999] flex w-full -translate-y-[130%] items-center rounded-none border border-[#222233] bg-[#222233] px-3 shadow-lg shadow-black/5 backdrop-blur-[0.5rem] transition-all data-[scroll=up]:translate-y-0 data-[state=open]:shadow-none data-[state=open]:delay-0 sm:top-5 sm:mx-auto sm:max-w-[calc(min(1280px,100vw)-4rem)] sm:rounded-full sm:px-6 sm:py-2"
      data-scroll={scrollDirection}
    >
      {navContent}
    </nav>
  );
}

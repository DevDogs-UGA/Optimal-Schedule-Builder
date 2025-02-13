"use client";

import React, { useState, useEffect } from "react";
import Image from "next/image";
import Link from "next/link";
import Notifications from "./Notifications";

// Simulate request to backend.
let notifications = [
  {
    id: "1",
    content: (
      <p className="flex flex-col gap-2">
        A spot has opened up in one of your saved classes (&ldquo;class name
        here&rdquo;)!{" "}
        <Link
          className="text-sky-600 underline"
          href="https://athena.uga.edu"
          target="_blank"
        >
          Register now on Athena
        </Link>
      </p>
    ),
  },
  {
    id: "2",
    content: (
      <p className="flex flex-col gap-2">
        One of your saved classes is almost completely filled!
        <Link
          className="text-sky-600 underline"
          href="https://athena.uga.edu"
          target="_blank"
        >
          Register now on Athena
        </Link>
      </p>
    ),
  },
];

async function closeNotification(id: string) {
  await new Promise((resolve) => setTimeout(resolve, 2000));
  notifications = notifications.filter((item) => item.id !== id);
  return { success: true };
}

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

export function Navbar() {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const scrollDirection = useScrollDirection();

  const toggleNavbar = (): void => {
    setIsDropdownOpen(!isDropdownOpen);
  };

  return (
    <div
      className={`sticky z-[999] w-full transition-transform duration-300 sm:top-5 ${
        scrollDirection === "down" ? "-translate-y-[130%]" : "translate-y-0"
      }`}
    >
      <div className="flex h-[4.5rem] w-full items-center justify-between rounded-none border border-[#F8E6EA] border-opacity-40 bg-[#F8E6EA] bg-opacity-80 shadow-lg shadow-black/[0.03] backdrop-blur-[0.5rem] sm:mx-auto sm:h-[4rem] sm:w-[36rem] sm:rounded-full md:h-[4rem] md:w-[45rem] lg:w-[60rem] xl:w-[80rem]">
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
              App
            </button>
          </Link>
          <Link href="/">
            <button className="rounded-full px-2 py-1 transition duration-300 ease-in-out hover:bg-bulldog-red/50 md:px-4">
              About Us
            </button>
          </Link>
          <Link href="/">
            <button className="rounded-full px-2 py-1 transition duration-300 ease-in-out hover:bg-bulldog-red/50 md:px-4">
              Contact
            </button>
          </Link>
          <Notifications
            initialItems={notifications}
            closeAction={closeNotification}
          />
        </div>

        {/* Mobile Dropdown */}
        {isDropdownOpen && (
          <div className="absolute left-0 top-[4.5rem] z-[998] w-full flex-col items-center justify-start rounded-none bg-[#F8E6EA] shadow-lg sm:hidden">
            <Link href="/">
              <button className="block w-full px-4 py-3 text-left text-[1.2rem] font-bold transition duration-300 ease-in-out hover:bg-bulldog-red/50">
                App
              </button>
            </Link>
            <Link href="/">
              <button className="block w-full px-4 py-3 text-left text-[1.2rem] font-bold transition duration-300 ease-in-out hover:bg-bulldog-red/50">
                About Us
              </button>
            </Link>
            <Link href="/">
              <button className="block w-full px-4 py-3 text-left text-[1.2rem] font-bold transition duration-300 ease-in-out hover:bg-bulldog-red/50">
                Contact
              </button>
            </Link>
            <Notifications
              initialItems={notifications}
              closeAction={closeNotification}
            />
          </div>
        )}
      </div>
    </div>
  );
}

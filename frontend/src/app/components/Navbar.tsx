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

  return (
    <nav
      className="data group sticky z-[999] flex w-full -translate-y-[130%] items-center gap-3 rounded-none border border-[#F8E6EA]/40 bg-[#F8E6EA]/80 px-6 shadow-lg shadow-black/5 backdrop-blur-[0.5rem] transition-all data-[scroll=up]:translate-y-0 data-[state=open]:shadow-none data-[state=open]:delay-0 sm:top-5 sm:mx-auto sm:max-w-[calc(min(1280px,100vw)-4rem)] sm:rounded-full sm:px-6 sm:py-2"
      data-scroll={scrollDirection}
      data-state={isDropdownOpen ? "open" : "closed"}
    >
      {/* Logo and Title */}
      <Link
        href="/"
        className="flex flex-1 items-center gap-4 bg-inherit py-3 sm:py-0"
      >
        <Image
          className="block"
          src="/images/blackpaw.svg"
          width={40}
          height={40}
          alt="black paw"
        />
        <h1 className="text-3xl font-bold">DogDays</h1>
      </Link>

      <ul className="absolute left-0 top-full -z-10 flex w-full -translate-y-full flex-col flex-nowrap border-[#F8E6EA]/40 bg-[#F8E6EA]/80 pb-3 text-xl font-bold opacity-0 shadow-lg backdrop-blur-[0.5rem] transition-all group-data-[state=open]:translate-y-0 group-data-[state=open]:opacity-100 sm:relative sm:z-auto sm:w-auto sm:translate-y-0 sm:flex-row sm:justify-end sm:gap-[inherit] sm:p-0 sm:opacity-100 sm:shadow-none">
        <li className="contents">
          <Link
            className="px-6 py-3 transition hover:bg-bulldog-red/40 sm:rounded-lg sm:py-1 md:px-4"
            href="/"
          >
            App
          </Link>
        </li>
        <li className="contents">
          <Link
            className="px-6 py-3 transition hover:bg-bulldog-red/40 sm:rounded-lg sm:py-1 md:px-4"
            href="/"
          >
            About Us
          </Link>
        </li>
        <li className="contents">
          <Link
            className="px-6 py-3 transition hover:bg-bulldog-red/40 sm:rounded-lg sm:py-1 md:px-4"
            href="/"
          >
            Contact
          </Link>
        </li>
      </ul>

      <div className="relative flex flex-row-reverse items-center justify-around gap-4 text-xl font-bold sm:flex-row">
        {/* Hamburger button for mobile */}
        <button
          className="flex size-[30px] cursor-default flex-col items-center justify-center gap-[3px] sm:hidden"
          onClick={() => setIsDropdownOpen((open) => !open)}
          type="button"
        >
          <span className="h-0.5 w-[18px] rounded-full bg-black transition-transform group-data-[state=open]:translate-y-[5px] group-data-[state=open]:rotate-45" />
          <span className="h-0.5 w-[18px] rounded-full bg-black transition-transform group-data-[state=open]:-rotate-45" />
          <span className="h-0.5 w-[18px] rounded-full bg-black transition-transform group-data-[state=open]:-translate-y-[5px] group-data-[state=open]:rotate-45" />
        </button>

        <Notifications
          initialItems={notifications}
          closeAction={closeNotification}
        />
      </div>
    </nav>
  );
}

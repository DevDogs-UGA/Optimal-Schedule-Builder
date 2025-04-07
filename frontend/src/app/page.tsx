"use client";

// Returning User: option(saved filter preferences, manual entry, saved plans)
// New User: option(saved filter preferences, questionnaire page, saved plans)

import { Navbar } from "@/components/Navbar";
import Image from "next/image";
import Link from "next/link";
import background from "../../public/images/background.png";
import devdogCobranded from "../../public/images/devdogCobranded.png";

export default function Home() {
  return (
    <>
      <Navbar />
      <div
        className="relative -mt-[3.625rem] flex h-[calc(100vh-100px)] flex-1 flex-col items-center justify-center gap-16 overflow-hidden bg-cover bg-fixed bg-bottom bg-no-repeat px-4 pt-24 text-center"
        style={{
          backgroundImage: `url(${background.src})`,
        }}
      >
        <div className="flex flex-col items-center px-6 py-8 min-[480px]:px-12 sm:px-16 md:max-w-[80%]">
          <div className="flex w-full flex-col items-center rounded-3xl bg-gradient-to-b from-white/50 to-transparent p-8 backdrop-blur-sm">
            <div className="flex translate-y-2 justify-center text-nowrap text-5xl md:translate-y-5 md:text-6xl lg:text-7xl">
              <h2 className="font-extrabold text-slate-800">Bulldog Planner</h2>
            </div>
            {/* Hero Section */}
            <div className="flex w-full flex-col items-center pl-10 pr-10">
              <div className="flex w-full items-center justify-center gap-8">
                <div className="flex translate-x-5 flex-col items-start">
                  <div className="flex items-center">
                    <div className="mr-4 h-20 border-l-4 border-bulldog-red"></div>
                    <div className="flex h-20 flex-col justify-center text-nowrap">
                      <span className="text-nowrap text-left text-2xl font-bold leading-tight text-bulldog-red sm:text-3xl xl:text-4xl">
                        An Optimized
                        <br />
                        Schedule Builder
                      </span>
                    </div>
                  </div>
                  <span className="mt-5 text-left font-medium text-bulldog-red sm:text-lg xl:text-2xl">
                    For Students, By Students
                  </span>
                </div>
                <Image
                  alt="UGA Dev Dogs logo"
                  height={220}
                  width={220}
                  className="h-[180px] w-[180px] translate-x-0 sm:h-[200px] sm:w-[200px] md:h-[220px] md:w-[220px] lg:-translate-x-1"
                  src={devdogCobranded}
                />
              </div>
              {/* Ready and Start Now Button */}
              <div className="flex w-full min-w-[350px] max-w-lg -translate-y-0 items-center justify-center text-xl font-bold md:-translate-y-5 xl:-translate-y-3 xl:text-2xl">
                <p className="-mr-8 w-1/2 cursor-default rounded-l-full bg-[#F8E6EA] bg-dusty-pink px-6 py-4 text-left text-neutral-600/40 sm:py-5">
                  Ready?
                </p>
                <Link
                  className="flex w-3/5 items-center text-nowrap rounded-full bg-bulldog-red px-6 py-4 pl-8 pr-1.5 text-white shadow-md sm:py-5"
                  href="/create"
                >
                  Start Now!{" "}
                  <span className="-my-2 ml-auto mr-3 flex size-12 items-center justify-center rounded-full border-2 border-red-900 bg-white pt-0.5 text-[1.5rem] leading-none">
                    🚀
                  </span>
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

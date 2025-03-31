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
      <div className="relative -mt-[3.625rem] flex flex-1 flex-col items-center justify-center gap-16 px-4 pt-24 text-center">

        <div className="flex flex-col items-center px-6 py-8 min-[480px]:px-12 sm:px-16 md:max-w-[80%] lg:max-w-[60%]">
          <div className="flex flex-col items-center w-full">
            <div className="flex justify-center text-6xl md:text-7xl xl:text-8xl">
              <h2 className="font-extrabold text-slate-800">Bulldog Planner</h2>
            </div>

            <div className="w-full flex flex-col items-center">
              <div className="flex items-center justify-center gap-8 w-full">
                <div className="flex flex-col items-start">
                  <div className="flex items-start">
                    <div className="border-l-4 border-bulldog-red h-20 mr-4"></div>
                    <div className="flex flex-col">
                      <span className="text-2xl sm:text-3xl xl:text-4xl font-bold text-bulldog-red leading-tight text-left">
                        An Optimized<br />
                        Schedule Builder
                      </span>
                    </div>
                  </div>
                  <span className="text-left sm:text-lg xl:text-2xl font-medium text-bulldog-red mt-5">
                    For Students, By Students
                  </span>
                </div>
                <Image 
                  alt="UGA Dev Dogs logo" 
                  className="flex-shrink-0" 
                  height={220} 
                  width={220} 
                  src={devdogCobranded}
                />
              </div>

              <div className="flex items-center justify-center text-xl font-bold xl:text-2xl w-full max-w-lg">
                <p className="-mr-8 w-1/2 cursor-default rounded-l-full bg-[#F8E6EA] bg-dusty-pink px-6 py-5 text-left text-neutral-600/40 shadow-inner">
                  Ready?
                </p>
                <Link
                  className="w-3/5 flex items-center gap-4 rounded-full border-2 border-red-900 bg-bulldog-red py-4 pl-8 pr-1.5 text-white shadow-md"
                  href="/create"
                >
                  Get Started!{" "}
                  <span className="-my-2 flex size-12 items-center justify-center rounded-full border-2 border-red-900 bg-white pt-0.5 text-[1.5rem] leading-none shadow-sm ml-auto mr-1">
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

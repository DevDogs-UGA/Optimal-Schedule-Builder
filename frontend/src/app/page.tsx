// Returning User: option(saved filter preferences, manual entry, saved plans)
// New User: option(saved filter preferences, questionnaire page, saved plans)

import Link from "next/link";

import { CallToAction } from "./components/home/CallToAction";
import { HomepageAlert } from "./components/home/HomepageAlert";
import { LabPairingSystem } from "./components/home/LabPairingSystem";
import { Minimap } from "./components/home/Minimap";
import { ScheduleSave } from "./components/home/ScheduleSave";
import { Navbar } from "./components/Navbar";

export default function Home() {
  return (
    <div className="min-h-screen">
      <Navbar />
      <div className="flex h-[calc(100vh-5rem)] flex-grow flex-col items-center justify-center text-center">
        <div className="space-y-4">
          <h2 className="text-center text-xl font-semibold text-bulldog-red md:text-2xl">
            For Students, By Students
          </h2>
          <h1 className="text-4xl font-bold md:text-6xl">
            An Optimized 🚀 Schedule Builder
          </h1>
          <h3 className="md:text-lg">Developed by UGA DevDogs ©</h3>
        </div>
        <div className="pt-10">
          <CallToAction />
        </div>
        <div className="mt-[8rem] flex items-center gap-4">
          <div className="flex flex-col items-center justify-end gap-4">
            <Minimap />
            <ScheduleSave />
          </div>
          <Link href="/">
            <div className="h-[10rem] w-[10rem] rounded-xl bg-[#F3EDED] transition duration-300 ease-in-out hover:bg-[#c3a5a5] sm:h-[10rem] sm:w-[20rem] md:h-[12rem] md:w-[25rem] lg:h-[15rem] lg:w-[30rem]"></div>
          </Link>
          <div className="flex flex-col items-center justify-end gap-4">
            <LabPairingSystem />
            <HomepageAlert />
          </div>
          <div className="text-blue-600">
            <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
              <Link href={"/past-credits"}>Past Credits</Link>
            </button>
          </div>
          <div className="text-blue-600">
            <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
              <Link href={"/saved-plans"}>Saved Plans</Link>
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

import Link from "next/link";

import { CallToAction } from "./components/home/CallToAction";
import { Minimap } from "./components/home/Minimap";
import { ScheduleSave } from "./components/home/ScheduleSave";
import { HomepageAlert } from "./components/home/HomepageAlert";
import { LabPairingSystem } from "./components/home/LabPairingSystem";

export default function Home() {
  return (
    <div className="flex h-[calc(100vh-5rem)] flex-grow flex-col items-center justify-center pt-20 text-center">
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
      </div>
    </div>
  );
}

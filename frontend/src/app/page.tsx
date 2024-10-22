import React from "react";
import CallToAction from "./components/CallToAction";
import Link from "next/link";
import Minimap from "./components/Minimap";
import ScheduleSave from "./components/ScheduleSave";
import HomepageAlert from "./components/HomepageAlert";
import LabPairingSystem from "./components/LabPairingSystem";

const Homepage = () => {
  return (
    <div className="relative w-full">
      {/* Background Images */}
      <div className="absolute left-0 top-0 h-[50%] w-[50%]">
        <div className="h-[75%] w-[75%] bg-[url('/images/pawsImage.png')] bg-contain bg-no-repeat"></div>
        <div className="absolute left-[20rem] top-[10rem] hidden h-[75%] w-[75%] bg-[url('/images/pawsImage2.png')] bg-contain bg-no-repeat md:block"></div>
        <div className="absolute left-[40rem] top-[35rem] hidden h-[75%] w-[75%] bg-[url('/images/pawsImage3.png')] bg-contain bg-no-repeat lg:block"></div>
      </div>

      {/* Main Content */}
      <div className="relative z-10 flex flex-col items-center justify-center pt-20 text-center">
        <h2 className="mt-8 text-center text-[2rem] font-semibold text-BulldogRed">
          For Students, By Students
        </h2>
        <h1 className="text-[3rem] font-bold">
          An Optimized 🚀 Schedule Builder
        </h1>
        <h3 className="text-[1rem]">Developed by UGA DevDogs ©</h3>
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
    </div>
  );
};

export default Homepage;

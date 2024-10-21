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
      <div className="absolute top-0 left-0 w-[50%] h-[50%]">
        <div className="bg-[url('/images/pawsImage.png')] bg-no-repeat bg-contain w-[75%] h-[75%]"></div>
        <div className="hidden md:block absolute top-[10rem] left-[20rem] w-[75%] h-[75%] bg-[url('/images/pawsImage2.png')] bg-no-repeat bg-contain"></div>
        <div className="hidden lg:block absolute top-[35rem] left-[40rem] w-[75%] h-[75%] bg-[url('/images/pawsImage3.png')] bg-no-repeat bg-contain"></div>
      </div>

      {/* Main Content */}
      <div className="flex flex-col text-center justify-center items-center pt-20 relative z-10">
        <h2 className="text-center text-BulldogRed font-semibold text-[2rem]">
          For Students, By Students
        </h2>
        <h1 className="text-[3rem] font-bold">
          An Optimized 🚀 Schedule Builder
        </h1>
        <h3 className="text-[1rem]">Developed by UGA DevDogs ©</h3>
        <div className="pt-10">
          <CallToAction />
        </div>
        <div className="flex items-center gap-4 mt-[8rem]">
          <div className="flex flex-col gap-4 items-center justify-end">
            <Minimap />
            <ScheduleSave />
          </div>
          <Link href="/">
            <div className="transition ease-in-out duration-300 bg-[#F3EDED] w-[10rem] h-[10rem] sm:w-[20rem] sm:h-[10rem] md:w-[25rem] md:h-[12rem] lg:w-[30rem] lg:h-[15rem] rounded-xl hover:bg-[#c3a5a5]"></div>
          </Link>
          <div className="flex flex-col gap-4 items-center justify-end">
            <LabPairingSystem />
            <HomepageAlert />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Homepage;

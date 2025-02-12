// Returning User: option(saved filter preferences, manual entry, saved plans)
// New User: option(saved filter preferences, questionnaire page, saved plans)

import Image from "next/image";
import Link from "next/link";
import background from "../../public/images/background.png";
import devdog from "../../public/images/devdog.png";
import { Navbar } from "@/components/Navbar";

export default function Home() {
  return (
    <>
      <Navbar />
      <div className="relative -mt-[3.625rem] flex flex-1 flex-col items-center justify-center gap-16 px-4 pt-24 text-center">
        <Image
          alt=""
          className="absolute top-14 -z-10 max-h-full w-screen object-cover sm:top-0"
          sizes="100vw"
          src={background}
        />
        <Image
          alt=""
          className="absolute -bottom-36 -z-10 w-screen rotate-180 sm:hidden"
          sizes="100vw"
          src={background}
        />

        <div className="flex flex-col items-center gap-20 rounded-md bg-gradient-to-bl from-white/50 to-pink-100/50 px-6 py-16 backdrop-blur-sm min-[480px]:px-12 sm:px-16 md:contents">
          <div className="flex gap-6 text-6xl md:text-7xl xl:text-8xl">
            <h2 className="font-extrabold text-slate-800">DogDays</h2>
            <figure className="size-[1em]">
              <Image
                alt="UGA Dev Dogs logo"
                className=""
                height={128}
                src={devdog}
              />
            </figure>
          </div>

          <div className="flex flex-col gap-8 rounded-md from-white/50 to-pink-100/50 md:bg-gradient-to-bl md:px-16 md:py-8 md:backdrop-blur-sm">
            <p className="flex flex-col font-bold text-bulldog-red">
              <span className="text-xl sm:text-2xl xl:text-3xl">
                An Optimized Schedule Builder
              </span>
              <span className="sm:text-lg xl:text-2xl">
                For Students, By Students
              </span>
            </p>

            <div className="flex items-center text-lg font-bold xl:text-xl">
              <p className="-mr-6 flex-1 cursor-default rounded-l-full bg-[#F8E6EA] bg-dusty-pink px-4 py-3 text-left text-neutral-600/40 shadow-inner">
                Ready?
              </p>
              <Link
                className="flex items-center gap-10 rounded-full border-2 border-red-900 bg-bulldog-red py-2.5 pl-4 pr-1.5 text-white shadow-md"
                href="/"
              >
                Join Now!{" "}
                <span className="-my-2 flex size-10 items-center justify-center rounded-full border-2 border-red-900 bg-white pt-0.5 text-[1.5rem] leading-none shadow-sm">
                  🚀
                </span>
              </Link>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

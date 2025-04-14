import Filters from "@/components/Filters";
import { Navbar } from "@/components/Navbar";
import CourseDisplay from "@/components/courses/CourseDisplay";
import { PiSparkleBold } from "react-icons/pi";
import background from "../../../public/images/background.png";

interface Props {
  /**
   *  The current URL search params.
   */
  searchParams: Record<string, string | string[] | undefined>;
}

export default function Page({ searchParams }: Props) {
  return (
    <div
      className="min-h-screen bg-cover bg-fixed bg-bottom bg-no-repeat"
      style={{
        backgroundImage: `url(${background.src})`,
      }}
    >
      <Navbar />
      <main className="flex flex-col gap-8 px-4 pb-4 pt-8 xl:px-24">
        <CourseDisplay searchParams={searchParams} />
        <Filters />
        <section className="flex justify-center">
          <button className="flex items-center gap-2 self-end rounded-md border-2 border-red-800 bg-bulldog-red px-6 py-2 font-medium text-white transition-[background-color,border-color,box-shadow] disabled:cursor-not-allowed disabled:opacity-60 sm:gap-3 sm:px-8 sm:py-2.5 sm:text-lg [&:not(:disabled)]:hover:border-red-950 [&:not(:disabled)]:hover:bg-red-800 [&:not(:disabled)]:hover:shadow-md">
            <PiSparkleBold />
            Generate Schedule
          </button>
        </section>
      </main>
    </div>
  );
}

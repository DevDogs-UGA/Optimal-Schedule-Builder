import { SearchFilter } from "@/components/Filters";
import { Navbar } from "@/components/Navbar";
import CourseDisplay from "@/components/courses/CourseDisplay";
import Link from "next/link";

interface Props {
  /**
   *  The current URL search params.
   */
  searchParams: Record<string, string | string[] | undefined>;
}

export default function Page({ searchParams }: Props) {
  return (
    <div className="min-h-screen">
      <Navbar />
      <main className="flex flex-col gap-8 px-4 pb-4 pt-8 md:px-12 lg:px-24">
        <CourseDisplay searchParams={searchParams} />
        <SearchFilter />
        <section className="flex flex-col gap-3 sm:flex-row sm:justify-center">
          <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
            <Link href={"/generate-schedule"}>Generate Schedule</Link>
          </button>
        </section>
      </main>
    </div>
  );
}

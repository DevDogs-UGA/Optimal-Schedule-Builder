// Returning User: -> Past Credit Data
// New User: -> Past Credit Data page -> option(Home or Schedule Generaton(\schedules))
import { Navbar } from "@/components/Navbar";
import Link from "next/link";

export default function Questionnaire() {
  return (
    <div className="min-h-screen">
      <Navbar />
      <div className="flex h-[calc(100vh-5rem)] flex-grow flex-col items-center justify-center text-center">
        <div className="space-y-4">
          <h1 className="text-4xl font-bold md:text-6xl">Questionnaire</h1>
        </div>
        <div className="text-blue-600">
          <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
            <Link href={"/generate-schedule"}>Freshmen Click Here</Link>
          </button>
        </div>
        <br></br>
        <div className="text-blue-600">
          <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
            <Link href={"/credit-data"}>Past Credits Data</Link>
          </button>
        </div>
        <br></br>
        <div>
          <Link href={"/credit-data"} passHref>
            <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
              Past Credits Data
            </button>
          </Link>
        </div>
      </div>
    </div>
  );
}

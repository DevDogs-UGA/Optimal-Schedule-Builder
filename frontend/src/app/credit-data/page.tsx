// Returning User: -> Past Credit Data
// New User: -> Past Credit Data page -> option(Home or Schedule Generaton(\schedules))
import { Navbar } from "@/components/Navbar";
import { Button } from "@/components/ui/Button";
import Link from "next/link";

export default function CreditData() {
  return (
    <div className="min-h-screen">
      <Navbar />
      <div className="flex h-[calc(100vh-5rem)] flex-grow flex-col items-center justify-center text-center">
        <div className="space-y-4">
          <h1 className="text-4xl font-bold md:text-6xl">Past Credit Data</h1>
        </div>
        <div>
          <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
            <Link href={"/generate-schedule"}>Generate Schedules</Link>
          </button>
        </div>
        <br></br>
        <div>
          <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
            <Link href={"/manual-entry"}>Manual Entry</Link>
          </button>
        </div>
        <br></br>
        <div>
          <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
            <Link href={"/"}>Go Back Home</Link>
          </button>
        </div>
      </div>
    </div>
  );
}

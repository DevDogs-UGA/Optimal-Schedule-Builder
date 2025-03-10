// Returning User: -> Schedule View (\schedules)
// New User: -> Schedule View (\schedules)

"use client";
// make class blocks clickable to access more
import { Navbar } from "@/components/Navbar";
import { Button } from "@/components/ui/Button";
import Link from "next/link";

export default function SavedPlans() {
  return (
    <div className="min-h-screen">
      <Navbar />
      <div className="flex h-[calc(100vh-5rem)] flex-grow flex-col items-center justify-center text-center">
        <div className="mt-10 flex flex-col flex-nowrap items-center justify-center gap-1">
          <h1 className="mb-5 text-4xl font-bold">
            There are currently no saved plans.
          </h1>
          <Link href="/saved-plans/choose-plan">
            <Button
              className="rounded-lg border-2 border-pebble-gray bg-dusty-pink px-8 py-4 text-lg font-semibold text-black"
              text="Create"
            ></Button>
          </Link>
        </div>
      </div>
    </div>
  );
}

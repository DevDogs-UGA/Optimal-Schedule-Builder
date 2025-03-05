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
        <div className="flex flex-col mt-10 justify-center items-center flex-nowrap gap-1">
          <h1 className="text-4xl font-bold mb-5">There are currently no saved plans.</h1>
          <Link href="/saved-plans/choose-plan">
            <Button className="border-2 border-pebble-gray rounded-lg bg-dusty-pink px-8 py-4 font-semibold text-black text-lg" text="Create">
            </Button>
          </Link>
        </div>
      </div>
    </div>
  );
}

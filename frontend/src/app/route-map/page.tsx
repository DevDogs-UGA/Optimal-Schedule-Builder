// Returning User: option to return Home or return Schedule View
// New User: option to return Home or returen Schdeule View
import { Navbar } from "@/components/Navbar";
import Link from "next/link";

export default function RouteMap() {
  return (
    <div className="min-h-screen">
      <Navbar />
      <div className="flex h-[calc(100vh-5rem)] flex-grow flex-col items-center justify-center text-center">
        <div className="space-y-4">
          <h1 className="text-4xl font-bold md:text-6xl">Route Map Page</h1>
        </div>
        <div>
          <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
            <Link href={"/"}>Return Home</Link>
          </button>
        </div>
      </div>
    </div>
  );
}

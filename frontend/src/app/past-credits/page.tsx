// Returning User: -> Schedule View (\schedules)
// New User: -> Schedule View (\schedules)

// make class blocks clickable to access more
import { Navbar } from "@/components/Navbar";
import Link from "next/link";

export default function PastCredits() {
  return (
    <div className="min-h-screen">
      <Navbar />
      <div className="flex h-[calc(100vh-5rem)] flex-grow flex-col items-center justify-center text-center">
        <div className="space-y-4">
          <h1 className="text-4xl font-bold md:text-6xl">Past Credits Page</h1>
          <div>
            <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
              <Link href={"/questionnaire"}>Questionnaire</Link>
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

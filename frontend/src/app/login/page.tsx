// If returning user -> Home
// If new user -> Signup

import { Navbar } from "@/components/Navbar";
import Link from "next/link";

export default function Login() {
  return (
    <div className="min-h-screen">
      <Navbar />
      <div className="flex h-[calc(100vh-5rem)] flex-grow flex-col items-center justify-center text-center">
        <div className="space-y-4">
          <h1 className="text-4xl font-bold md:text-6xl">Login Page</h1>
        </div>
        <div>
          <button className="rounded-lg bg-bulldog-red px-4 py-2 font-semibold text-white">
            <Link href={"/"}>Home</Link>
          </button>
        </div>
      </div>
    </div>
  );
}

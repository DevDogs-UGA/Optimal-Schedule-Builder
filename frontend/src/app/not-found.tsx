// Custom 404 Page
"use client";

import { Button } from "@/components/ui/Button";
import Link from "next/link";

export default function NotFound() {
  return (
    <div className="flex h-screen w-full flex-col items-center justify-center gap-10 text-center">
      <h1 className="block text-[4rem]">404 Page Not Found</h1>

      <Link href="/">
        <Button
          className="h-[4rem] w-[10rem] transition duration-300 ease-in-out hover:bg-black"
          text="Home"
        />
      </Link>
    </div>
  );
}

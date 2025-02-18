"use client";

// Returning User: option(saved filter preferences, manual entry, saved plans)
// New User: option(saved filter preferences, questionnaire page, saved plans)

import { Navbar } from "@/components/Navbar";
import Image from "next/image";
import Link from "next/link";
import background from "../../public/images/background.png";
import devdog from "../../public/images/devdog.png";
import useLocalStorage from "@/hooks/useLocalStorage";
import { useCallback } from "react";

function Counter() {
  const [count, setCount] = useLocalStorage("storage");

  const handleClick = useCallback(() => {
    setCount((c) => ({ count: c.count + 1 }))
  }, [setCount]);

  return (
    <div>
      <p>{count.count}</p>
      <button onClick={handleClick}>Add One</button>
    </div>
  );
}

export default function Home() {
  return (
    <>
      <Navbar />
      <Counter />
    </>
  );
}

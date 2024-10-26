import "../../styles/globals.css";
import { type Metadata } from "next";

import { ScheduleViewNavBar } from "@/components/home/ScheduleViewNavBar";

export const metadata: Metadata = {
  title: "Generated Schedule",
  description:
    "Optimal Schedule Builder web application for UGA students to generate and design optimal academic schedules",
  icons: [{ rel: "icon", url: "/favicon.ico" }],
};

export default function RootLayout({
  children,
}: Readonly<{ children: React.ReactNode }>) {
  return (
    <>
      <ScheduleViewNavBar />
      {children}
    </>
  );
}

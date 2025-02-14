import "@/styles/globals.css";
import { Heebo } from "next/font/google";
import { type Metadata } from "next";
import { Footer } from "@/components/Footer";

const heebo = Heebo({ subsets: ["latin"], variable: "--font-sans" });

export const metadata: Metadata = {
  title: "UGA Optimal Schedule Builder",
  description:
    "Optimal Schedule Builder web application for UGA students to generate and design optimal academic schedules",
  icons: [{ rel: "icon", url: "/favicon.ico" }],
};

export default function RootLayout({
  children,
}: Readonly<{ children: React.ReactNode }>) {
  return (
    <html lang="en" className={heebo.variable}>
      <body className="flex min-h-screen flex-col">
        <main className="relative flex flex-1 flex-col">{children}</main>
        <Footer />
      </body>
    </html>
  );
}

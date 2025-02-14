import "@/styles/globals.css";
import { Heebo } from "next/font/google";
import { type Metadata } from "next";
import { Footer } from "@/components/Footer";
import Image from "next/image"; // Ensure this is imported

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
      <body className="relative flex min-h-screen flex-col bg-background">
        <Image
          className="absolute left-0 top-0 -z-10"
          src="/images/paws.svg"
          alt="Paws"
          width={300}
          height={300}
        />
        <Image
          className="absolute bottom-0 right-0 -z-10"
          src="/images/paws.svg"
          alt="Paws"
          width={300}
          height={300}
        />
        <Image
          className="absolute bottom-1/3 right-1/4 -z-10 hidden lg:block"
          src="/images/paws.svg"
          alt="Paws"
          width={300}
          height={300}
        />

        <main className="relative flex flex-1 flex-col">
          <div className="mb-[18rem]">{children}</div>
        </main>
        <Footer />
      </body>
    </html>
  );
}

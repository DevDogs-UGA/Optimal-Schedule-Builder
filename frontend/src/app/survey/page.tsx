import { QuestionnareForm } from "@/components/survey/QuestionnaireForm";
import { Navbar } from "../components/Navbar";
import Image from "next/image";

export default function page() {
  return (
    <>
      <Navbar />
      <div className="min-w-screen flex min-h-screen items-center justify-center">
        <main className="flex flex-col border-dusty-pink sm:mx-8 sm:flex-row sm:overflow-hidden sm:rounded-xl sm:border-4 sm:shadow-2xl">
          <div className="flex items-center justify-center text-wrap border-4 border-b-0 border-barely-pink bg-white p-5 text-6xl font-bold sm:border-0 sm:bg-barely-pink sm:pb-[30%] sm:pl-14 md:w-1/2">
            <div className="flex flex-col">
              <h1 className="sm:text-nearly-black">
                Let&apos;s Get You <br /> Started!
              </h1>
              <Image
                className="absolute rotate-45"
                width={400}
                height={400}
                src="/images/paws.svg"
                alt={"paws overlay"}
              />
            </div>
          </div>
          <QuestionnareForm className="border-4 border-t-0 border-barely-pink bg-white shadow-md sm:border-0 sm:shadow-none lg:w-2/3" />
        </main>
      </div>
    </>
  );
}

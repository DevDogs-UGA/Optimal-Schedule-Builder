import Image from "next/image";
import { QuestionnareForm } from "@/components/survey/QuestionnaireForm";
const page = () => {
  return (
    // PAGE CONTAINER
    <div className="min-w-screen flex min-h-screen items-center justify-center overflow-x-hidden pb-32">
      {/* MODAL CONTAINER */}
      <main className="flex w-full flex-col rounded-lg sm:my-20 sm:flex-row sm:border-4 sm:border-dusty-pink sm:bg-barely-pink sm:shadow-2xl lg:mx-32 lg:max-w-5xl">
        {/* LEFT SECTION */}
        <div className="flex min-w-min justify-center sm:w-2/5 sm:p-8 sm:pb-60 sm:pt-32 md:px-12">
          <Image
            className="absolute"
            width={400}
            height={400}
            src="/images/paws.svg"
            alt={"paws overlay"}
            draggable="false"
          />
          <h1 className="z-10 text-7xl font-bold text-nearly-black">
            Let&apos;s Get <br />
            You <br /> Started!
          </h1>
        </div>
        {/* RIGHT SECTION */}
        <div className="flex flex-1 items-center justify-center p-8 sm:bg-white lg:p-20">
          <QuestionnareForm />
        </div>
      </main>
    </div>
  );
};

export default page;

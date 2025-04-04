import { PiInstagramLogoBold, PiLinkedinLogoBold } from "react-icons/pi";
import Image from "next/image";

export function Footer() {
  return (
    <footer className="m-4 flex flex-row items-center justify-center gap-y-0 rounded-md border-2 border-neutral-500 bg-neutral-200 px-4 py-0 py-3 text-neutral-600 sm:gap-y-1 sm:py-2 md:gap-x-4 lg:gap-x-6 lg:px-6">
      <nav className="contents">
        <div className="flex w-1/5 flex-col justify-start gap-x-4 gap-y-1 text-lg sm:flex-row sm:gap-y-0 sm:text-base">
          <a
            rel="noopener"
            className="col-span-2 flex items-center justify-center text-nowrap text-center hover:text-slate-900"
            href="https://devdogs.uga.edu/"
            target="_blank"
          >
            About Us
          </a>
          <a
            className="flex items-center justify-center text-center hover:text-slate-900"
            href="https://linktr.ee/devdogs"
            target="_blank"
            rel="noopener"
          >
            Contact
          </a>
        </div>

        {/* WordmarkMascot image */}
        <div className="flex h-full grow flex-col items-center justify-center py-1 sm:flex-row">
          <Image
            src="/images/DevDogsLogo.png"
            alt="UGA DevDogs Logo"
            width={100}
            height={100}
            className="m-2"
            priority
          />

          <Image
            src="/images/GoogleDevLogo.png"
            alt="UGA GDG Logo"
            width={160}
            height={160}
            className="m-2"
            priority
          />
        </div>

        {/* Removed Terms of Use */}
        <a
          title="blank"
          className="col-span-2 flex items-center justify-center text-center hover:text-slate-900 min-[480px]:col-span-1"
          href="#"
        ></a>

        <div className="flex w-1/5 flex-row justify-end">
          <a
            title="Instagram"
            rel="noopener"
            className="flex items-center justify-end justify-center px-2 text-4xl text-neutral-500 hover:text-slate-900 sm:text-2xl"
            href="https://www.instagram.com/devdogs_uga/"
            target="_blank"
          >
            <PiInstagramLogoBold />
          </a>
          <a
            title="LinkedIn"
            rel="noopener"
            className="flex items-center justify-start justify-center px-2 text-4xl text-neutral-500 hover:text-slate-900 sm:text-2xl"
            href="https://www.linkedin.com/company/devdogs-uga/"
            target="_blank"
          >
            <PiLinkedinLogoBold />
          </a>
        </div>
      </nav>
    </footer>
  );
}

import { PiInstagramLogoBold, PiLinkedinLogoBold } from "react-icons/pi";
import Image from "next/image";

export function Footer() {
  return (
    <footer className="m-4 grid grid-cols-6 grid-rows-3 gap-y-0 rounded-md border-2 border-neutral-500 bg-neutral-200 px-4 py-0 text-neutral-600 min-[480px]:grid-cols-[1fr_repeat(3,1.5fr)_1fr] min-[480px]:grid-rows-2 min-[480px]:gap-y-1.5 min-[480px]:py-3 sm:gap-y-1 sm:py-2 md:grid-cols-[repeat(2,max-content)_1fr_repeat(3,max-content)] md:grid-rows-1 md:gap-x-4 lg:gap-x-6 lg:px-6">
      {/* WordmarkMascot image */}
      <div className="col-span-full flex h-full items-center justify-center py-1 sm:row-start-2 md:col-span-1 md:col-start-3 md:row-start-1">
        <Image
          src="/images/WordmarkMascot.png"
          alt="UGA DevDogs and GDG Logos"
          width={250}
          height={40}
          className="mx-auto object-contain"
          priority
        />
      </div>

      <nav className="contents">
        <a
          rel="noopener"
          className="col-span-2 flex items-center justify-center text-center hover:text-slate-900 min-[480px]:col-span-1 min-[480px]:col-start-2 md:col-start-1 md:row-start-1"
          href="https://devdogs.uga.edu/"
          target="_blank"
        >
          About Us
        </a>

        <a
          className="col-span-2 flex items-center justify-center text-center hover:text-slate-900 min-[480px]:col-span-1"
          href="#"
        >
          Terms of Use
        </a>

        <a
          className="col-span-2 flex items-center justify-center text-center hover:text-slate-900 min-[480px]:col-span-1"
          href="#"
        >
          Contact
        </a>

        <a
          title="Instagram"
          rel="noopener"
          className="col-span-3 flex items-center justify-end px-2 text-2xl text-neutral-500 hover:text-slate-900 min-[480px]:col-span-1 min-[480px]:col-start-1 min-[480px]:row-start-2 min-[480px]:justify-center sm:row-start-1 md:col-start-5 md:px-0"
          href="https://www.instagram.com/devdogs_uga/"
          target="_blank"
        >
          <PiInstagramLogoBold />
        </a>
        <a
          title="LinkedIn"
          rel="noopener"
          className="col-span-3 flex items-center justify-start px-2 text-2xl text-neutral-500 hover:text-slate-900 min-[480px]:col-span-1 min-[480px]:justify-center md:px-0"
          href="https://www.linkedin.com/company/devdogs-uga/"
          target="_blank"
        >
          <PiLinkedinLogoBold />
        </a>
      </nav>
    </footer>
  );
}

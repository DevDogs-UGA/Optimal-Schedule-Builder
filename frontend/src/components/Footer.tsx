import { PiInstagramLogoBold, PiLinkedinLogoBold } from "react-icons/pi";

export function Footer() {
  return (
    <footer className="m-4 grid grid-cols-6 grid-rows-3 gap-y-1.5 rounded-md border-2 border-neutral-500 bg-neutral-200 px-4 py-3 text-neutral-600 min-[480px]:grid-cols-[1fr_repeat(3,1.5fr)_1fr] min-[480px]:grid-rows-2 min-[480px]:gap-y-2 min-[480px]:py-4 sm:gap-y-1.5 sm:py-3 md:grid-cols-[repeat(2,max-content)_1fr_repeat(3,max-content)] md:grid-rows-1 md:gap-x-4 lg:gap-x-6 lg:px-6">
      <h2 className="col-span-full text-center text-slate-900 sm:row-start-2 md:col-span-1 md:col-start-3 md:row-start-1">
        Developed by UGA DevDogs &copy;
      </h2>

      <nav className="contents">
        <a
          className="col-span-2 text-center hover:text-slate-900 min-[480px]:col-span-1 min-[480px]:col-start-2 md:col-start-1 md:row-start-1"
          href="https://devdogs.uga.edu/"
          target="_blank"
        >
          About Us
        </a>

        <a
          className="col-span-2 text-center hover:text-slate-900 min-[480px]:col-span-1"
          href="#"
        >
          Terms of Use
        </a>

        <a
          className="col-span-2 text-center hover:text-slate-900 min-[480px]:col-span-1"
          href="#"
        >
          Contact
        </a>

        <a
          className="col-span-3 flex justify-end px-2 text-2xl text-neutral-500 hover:text-slate-900 min-[480px]:col-span-1 min-[480px]:col-start-1 min-[480px]:row-start-2 min-[480px]:justify-center sm:row-start-1 md:col-start-5 md:px-0"
          href="https://www.instagram.com/devdogs_uga/"
          target="_blank"
        >
          <PiInstagramLogoBold />
        </a>
        <a
          className="col-span-3 flex justify-start px-2 text-2xl text-neutral-500 hover:text-slate-900 min-[480px]:col-span-1 min-[480px]:justify-center md:px-0"
          href="https://www.linkedin.com/company/devdogs-uga/"
          target="_blank"
        >
          <PiLinkedinLogoBold />
        </a>
      </nav>
    </footer>
  );
}

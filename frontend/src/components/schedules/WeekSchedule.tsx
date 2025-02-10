"use client";

import { differenceInMinutes } from "date-fns";
import { useCallback, useEffect, useRef, useState } from "react";
import { PiCaretDoubleRightBold } from "react-icons/pi";
import { type WeekSchedule as WeekScheduleType } from "../../../types/scheduleTypes";
import DayClass from "./DayClass";

interface WeekScheduleProps {
  weekData: WeekScheduleType;
}

export default function WeekSchedule({ weekData }: WeekScheduleProps) {
  const scrollportRef = useRef<HTMLElement>(null);
  const [next, setNext] = useState<string | undefined>(undefined);
  const [prev, setPrev] = useState<string | undefined>(undefined);

  /**
   * Handler updating next/prev scroll buttons for scroll/resize events.
   */
  const handleScroll = useCallback(function (this: HTMLElement) {
    const bounds = this.getBoundingClientRect();
    const children = [...this.children];

    setPrev(
      children
        .findLast((child) => child.getBoundingClientRect().left < 0)
        ?.getAttribute("data-title") ?? undefined,
    );

    setNext(
      children
        .find((child) => child.getBoundingClientRect().left > bounds.width)
        ?.getAttribute("data-title") ?? undefined,
    );
  }, []);

  /**
   * Scroll scrollport left by one day.
   */
  const scrollLeft = useCallback(() => {
    if (scrollportRef.current === null) {
      return;
    }

    const scrollport = scrollportRef.current;
    const target = scrollport.querySelector(":last-of-type")!;
    scrollport.scrollLeft -= target.clientWidth;
  }, []);

  /**
   * Scroll scrollport right by one day.
   */
  const scrollRight = useCallback(() => {
    if (scrollportRef.current === null) {
      return;
    }

    const scrollport = scrollportRef.current;
    const target = scrollport.querySelector(":last-of-type")!;
    scrollport.scrollLeft += target.clientWidth;
  }, []);

  // Setup/cleanup for scroll/resize events.
  useEffect(() => {
    if (!("window" in globalThis) || scrollportRef.current === null) {
      return;
    }

    const controller = new AbortController();

    handleScroll.apply(scrollportRef.current);

    scrollportRef.current.addEventListener("scroll", handleScroll, controller);

    window.addEventListener(
      "resize",
      handleScroll.bind(scrollportRef.current),
      controller,
    );

    return () => controller.abort();
  }, [handleScroll]);

  return (
    <div className="relative z-0 mx-auto w-screen max-w-[1800px] overflow-hidden px-8">
      <button
        className="absolute left-0 top-0 z-10 flex h-full w-8 rotate-180 items-center justify-between border-l-2 border-pink-900 bg-pink-50 py-4 text-center font-bold text-pink-900 transition-[left] [writing-mode:vertical-lr] 2xl:hidden [&:not([data-scroll-target])]:-left-8"
        data-scroll-target={prev}
        onClick={scrollLeft}
        type="button"
      >
        <PiCaretDoubleRightBold />
        {prev}
        <PiCaretDoubleRightBold />
      </button>

      <section
        className="grid h-[540px] w-full snap-x snap-mandatory grid-cols-[1rem_repeat(5,calc((100%-1rem)/var(--cols)))] overflow-x-auto scroll-smooth [--cols:1] sm:[--cols:2] md:overflow-hidden lg:[--cols:3] xl:[--cols:4] 2xl:[--cols:5]"
        ref={scrollportRef}
      >
        <div />

        {Object.entries(weekData).map(([day, classes]) => (
          <div className="snap-end snap-always pr-4" data-title={day} key={day}>
            <article className="flex h-full w-full flex-col gap-4 rounded-xl bg-pink-200/50 px-4 py-6">
              <h2 className="text text-xl font-bold">{day}</h2>
              <div>
                {classes.map((classData, index) => (
                  <DayClass
                    key={`${day}-${classData.classTitle}-${index}`}
                    {...classData}
                    timeDifference={differenceInMinutes(
                      new Date(`1970/01/01 ${classData.timeStart}`),
                      new Date("1970/01/01 8:00 am"),
                    )}
                  />
                ))}
              </div>
            </article>
          </div>
        ))}
      </section>

      <button
        className="absolute right-0 top-0 z-10 flex h-full w-8 items-center justify-between border-l-2 border-pink-900 bg-pink-50 py-4 text-center font-bold text-pink-900 transition-[right] [writing-mode:vertical-rl] 2xl:hidden [&:not([data-scroll-target])]:-right-8"
        data-scroll-target={next}
        onClick={scrollRight}
        type="button"
      >
        <PiCaretDoubleRightBold />
        {next}
        <PiCaretDoubleRightBold />
      </button>
    </div>
  );
}

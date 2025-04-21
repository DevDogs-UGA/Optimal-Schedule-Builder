"use client";

import WeekSchedule from "@/components/schedules/WeekSchedule";
import useLocalStorage from "@/hooks/useLocalStorage";
import Link from "next/link";
import { useCallback, useMemo } from "react";
import {
  PiArrowLeftBold,
  PiArrowRightBold,
  PiHeartBold,
  PiHeartFill,
  PiXBold,
} from "react-icons/pi";

interface Props {
  id: string;
}

export default function ScheduleDisplay({ id }: Props) {
  const [savedPlans, setSavedPlans] = useLocalStorage("schedules");

  const pinPlan = useCallback(() => {
    setSavedPlans((plans) =>
      plans.map((plan) =>
        plan.id === id ? { ...plan, pinned: !plan.pinned } : plan,
      ),
    );
  }, [id, setSavedPlans]);

  const currentPlanIndex = useMemo(
    () => savedPlans.findIndex((plan) => plan.id === id),
    [savedPlans, id],
  );

  const handleInputKeyUp = useCallback(
    (e: React.KeyboardEvent<HTMLInputElement>) => {
      if (e.key === "Enter") {
        e.preventDefault();
        e.currentTarget.blur();
      }
    },
    [],
  );

  const handleChangeTitle = useCallback(
    (e: React.FocusEvent<HTMLInputElement>) => {
      const title = e.currentTarget.value.trim();

      if (title.length < 1) {
        if (savedPlans[currentPlanIndex]) {
          e.currentTarget.value = savedPlans[currentPlanIndex].title;
        }
        return;
      }

      e.currentTarget.value = title;
      setSavedPlans((plans) =>
        plans.map((plan) => (plan.id === id ? { ...plan, title } : plan)),
      );
    },
    [currentPlanIndex, id, savedPlans, setSavedPlans],
  );

  if (currentPlanIndex === -1) {
    return null;
  }

  const { data, title, pinned } = savedPlans[currentPlanIndex]!;

  // Render the schedule
  return (
    <div className="mx-auto min-h-screen w-[100%]">
      {/* Container for schedule display, schedule cycling, and save/exit buttons */}
      <div className="z-1 ml-auto mr-auto flex h-[85vh] w-[90vw] flex-col rounded-lg border-2 border-black bg-barely-pink pt-2">
        {/* Properly positions the cycling bar and the save/exit buttons */}
        <div className="flex flex-row items-center justify-between px-12 py-2">
          <button type="button" className="cursor-default" onClick={pinPlan}>
            {pinned ? (
              <PiHeartFill className="size-8 text-glory-glory-red transition" />
            ) : (
              <PiHeartBold className="m-0.5 size-7 transition-[color,width,height,margin] hover:m-0 hover:size-8 hover:text-glory-glory-red" />
            )}
          </button>

          {/* Cycling bar; changes the displayed schedule when arrows are clicked */}
          <div className="flex flex-row items-center justify-center gap-0.5 rounded-lg bg-white">
            {savedPlans.length > 1 && (
              <Link
                className="rounded-l-lg border-2 border-white bg-white px-2 py-1 text-2xl transition-colors hover:border-gray-400 hover:bg-gray-100"
                href={`/plans/${savedPlans[(currentPlanIndex + savedPlans.length - 1) % savedPlans.length]!.id}`}
                title={
                  savedPlans[
                    (currentPlanIndex + savedPlans.length - 1) %
                      savedPlans.length
                  ]!.title
                }
              >
                <PiArrowLeftBold />
              </Link>
            )}

            <input
              className="rounded-sm border-2 border-white pb-px pt-0.5 text-center text-xl font-semibold hover:border-gray-500 focus:border-gray-300"
              defaultValue={title}
              maxLength={32}
              onBlur={handleChangeTitle}
              onKeyUp={handleInputKeyUp}
            />

            {savedPlans.length > 1 && (
              <Link
                className="rounded-r-lg border-2 border-white bg-white px-2 py-1 text-2xl transition-colors hover:border-gray-400 hover:bg-gray-100"
                href={`/plans/${savedPlans[(currentPlanIndex + 1) % savedPlans.length]!.id}`}
                title={
                  savedPlans[(currentPlanIndex + 1) % savedPlans.length]!.title
                }
              >
                <PiArrowRightBold />
              </Link>
            )}
          </div>
          {/* Save/exit buttons: exit returns to saved plan list */}
          <Link
            href="/plans"
            className="transition-colors hover:text-bulldog-red"
          >
            <PiXBold className="size-7" />
          </Link>
        </div>
        {/* Schedule display container */}
        <div className="flex flex-grow flex-row overflow-y-auto">
          <WeekSchedule weekData={data} />
        </div>
      </div>
    </div>
  );
}

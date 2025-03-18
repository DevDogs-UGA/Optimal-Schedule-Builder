"use client";
import { Navbar } from "@/components/Navbar";
import { Button } from "@/components/ui/Button";
import { type WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import SavedPlan from "@/components/saved-plans/SavedPlan";

export default function ChoosePlan() {
  /* Array of saved plans:
  * Full of dummy data at the moment
  * Add a loop to dynamically populate array from local storage when possible */
  const savedPlans = [
    { title: "Schedule 1"},
    { title: "Schedule 2"},
    { title: "Schedule 3"},
    { title: "Schedule 4"},
    { title: "Schedule 5"},
    { title: "Schedule 6"},
    { title: "Schedule 7"},
  ]

    return (
      <div className="min-h-screen">
        <Navbar />

        <div className="flex flex-col flex-nowrap items-center bg-barely-pink border-black border-2 rounded-xl w-4/5 h-[85vh] ml-auto mr-auto mt-20 mb-10 overflow-y-auto">
          {savedPlans.map((plan,index) => (
            <SavedPlan
              key={index}
              planTitle={plan.title}
            />
          ))}
        </div>
      </div>
    );
  }

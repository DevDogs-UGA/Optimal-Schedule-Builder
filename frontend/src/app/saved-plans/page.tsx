"use client";
import { useEffect, useState } from "react";
import { Navbar } from "@/components/Navbar";
import { type WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import SavedPlan from "@/components/saved-plans/SavedPlan";
import DeletePlan from "@/components/ui/DeletePlan";
import { dummyData1, dummyData2 } from "@/components/schedules/dummySchedules";

// Remove these objects when we have generated schedules getting auto-saved to local storage
const dummySchedule1 = {
  data: dummyData1,
  pinned: false,
}

const dummySchedule2 = {
  data: dummyData2,
  pinned: false,
}

// Handles retrieval of saved plans from local storage and displays a list of them for the user to select
export default function SavedPlans() {
  // Parameters for a saved plan
  interface SavedPlanType {
    title: string;
    data: WeekScheduleType;
    pinned: boolean;
  }

  // useState variables for updating the saved plans list + specifying a plan to pin or delete
  const [savedPlans, setSavedPlans] = useState<SavedPlanType[]>([]);
  const planToPin = useState<string | null>(null);
  const [planToDelete, setPlanToDelete] = useState<string | null>(null);

  useEffect(() => {
    try {

      // Get the first item in local storage
      let key = localStorage.key(0);

      // If local storage is empty, save the dummy schedules there
      // REMOVE when we know we have working live data
      if (!key) {
        localStorage.setItem("Schedule 1", JSON.stringify(dummySchedule1));
        localStorage.setItem("Schedule 2", JSON.stringify(dummySchedule2));
        localStorage.setItem("Schedule 3", JSON.stringify(dummySchedule1));
        localStorage.setItem("Schedule 4", JSON.stringify(dummySchedule2));
        localStorage.setItem("Schedule 5", JSON.stringify(dummySchedule1));
      }
      
      // Retrieve schedules from local storage and populate the plans array
      const plans = [];
      // Loop through local storage
      for (let i = 0; i < localStorage.length; i++) {
        key = localStorage.key(i);
        // Retrieve key for local storage element
        if (key) {
          // Retrieve data matching key
          const data = localStorage.getItem(key);
          if (data) {
            const parsedData = JSON.parse(data) as SavedPlanType;
            if (parsedData.data && parsedData.pinned !== undefined) {
              // Parse the data from the JSON
              plans.push({
                title: key,
                data: parsedData.data,
                pinned: parsedData.pinned,
              });
            }
          }
        }
      }
      // Sort plans by title, because apparently the browser can't be bothered to keep the order straight
      plans.sort((a, b) => a.title.localeCompare(b.title));
      plans.sort((a, b) => Number(b.pinned) - Number(a.pinned));
      setSavedPlans(plans);
    } catch (error) {
      // If something goes wrong, this message should be displayed in the browser console
      console.error("Failed to parse local storage data: ", error);
    }
  }, []);

  // When the heart is clicked, the plan is pinned and takes priority in the list
  const pinPlan = (title: string) => {
    if (planToPin) {
      setSavedPlans((prevPlans) => {
        const updatedPlans = prevPlans.map((plan => {
          if (plan.title === title) {
            // Toggle the pinned status
            const updatedPlan = { ...plan, pinned: !plan.pinned };
            // Save the updated plan back to local storage
            localStorage.setItem(title, JSON.stringify({ data: plan.data, pinned: updatedPlan.pinned }));
            return updatedPlan;
          }
          return plan;
        }));

        // Sort plans so that pinned plans come first
        updatedPlans.sort((a,b) => Number(b.pinned) - Number(a.pinned));

        // Save sorted plans back to local storage
        updatedPlans.forEach((plan => {
          localStorage.setItem(plan.title, JSON.stringify({ data: plan.data, pinned: plan.pinned }));
        }));
        return updatedPlans;
      });
    };
  };

  const confirmDeletePlan = (title: string) => {
    setPlanToDelete(title);
  };

  const cancelDelete = () => {
    setPlanToDelete(null);
  };

  const deletePlan = () => {
    if (planToDelete) {
      localStorage.removeItem(planToDelete);
      setSavedPlans((prevPlans) =>
        prevPlans.filter((plan) => plan.title !== planToDelete),
      );
      setPlanToDelete(null);
    }
  };

  return (
    <div className="min-h-screen">
      <Navbar />

      <div className="z-1 mb-10 ml-auto mr-auto mt-20 flex h-[85vh] w-4/5 flex-col flex-nowrap items-center overflow-y-auto rounded-xl border-2 border-black bg-barely-pink">
        {savedPlans.map((plan, index) => (
          <SavedPlan
            key={index}
            plan={plan.data}
            planTitle={plan.title}
            pinned={plan.pinned}
            onPin={() => pinPlan(plan.title)}
            onDelete={() => confirmDeletePlan(plan.title)}
          />
        ))}
      </div>
      {planToDelete && (
        <DeletePlan
          onConfirm={deletePlan}
          onCancel={cancelDelete}
          planTitle={planToDelete}
        />
      )}
    </div>
  );
}

"use client";
import { Navbar } from "@/components/Navbar";
import SavedPlan from "@/components/saved-plans/SavedPlan";
import DeletePlan from "@/components/ui/DeletePlan";
import useLocalStorage from "@/hooks/useLocalStorage";
import { motion } from "motion/react";
import Link from "next/link";
import { useState } from "react";

// Handles retrieval of saved plans from local storage and displays a list of them for the user to select
export default function MyPlans() {
  // useState variables for updating the saved plans list + specifying a plan to pin or delete
  const [savedPlans, setSavedPlans] = useLocalStorage("schedules");
  const [planToDelete, setPlanToDelete] = useState<string | null>(null);

  // When the heart is clicked, the plan is pinned and takes priority in the list
  const pinPlan = (id: string) => {
    setSavedPlans((prevPlans) =>
      prevPlans.map((plan) =>
        id === plan.id ? { ...plan, pinned: !plan.pinned } : plan,
      ),
    );
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
    <div className="min-h-screen bg-barely-pink">
      <Navbar />

      <div className="z-1 mb-0 ml-[10%] mr-auto mt-20 flex h-[8vh] w-[25%] overflow-y-auto rounded-t-lg border-l-2 border-r-2 border-t-2 border-black bg-red-700">
        <h1 className="mb-auto ml-auto mr-auto mt-auto text-4xl font-bold text-white">
          My Plans
        </h1>
      </div>

      {savedPlans.length === 0 && (
        // if savedPlans array is empty: direct user to the schedule builder
        <div className="z-1 mb-10 ml-auto mr-auto mt-0 flex h-[85vh] w-4/5 flex-col flex-nowrap items-center overflow-y-auto rounded-xl rounded-tl-none border-2 border-black bg-white">
          <div className="m-auto flex flex-col items-center justify-center">
            <h1 className="text-3xl font-bold">
              You don&apos;t have any saved plans yet.{" "}
            </h1>
            <Link href="/create">
              <button className="mt-5 rounded-lg bg-bulldog-red px-8 py-4 text-xl font-bold text-white hover:bg-black">
                Create
              </button>
            </Link>
          </div>
        </div>
      )}

      <div className="z-1 mb-10 ml-auto mr-auto mt-0 flex h-[85vh] w-4/5 flex-col flex-nowrap items-center gap-6 overflow-y-auto rounded-xl rounded-tl-none border-2 border-black bg-white py-10">
        {savedPlans
          .toSorted((a, b) => (a.pinned !== b.pinned ? (a.pinned ? -1 : 1) : 0))
          .map((plan) => (
            <motion.div
              layout
              key={plan.id}
              transition={{
                type: "spring",
                damping: 20,
                stiffness: 120,
                duration: 100,
              }}
              className="relative"
            >
              <SavedPlan
                plan={plan}
                onPin={() => pinPlan(plan.id)}
                onDelete={() => confirmDeletePlan(plan.title)}
              />
            </motion.div>
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

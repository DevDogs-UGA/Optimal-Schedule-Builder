"use client";

import { Button } from "./Button";

interface DeletePlanProps {
  onConfirm: () => void;
  onCancel: () => void;
  planTitle: string;
}

export default function DeletePlan({ onConfirm, onCancel, planTitle }: DeletePlanProps) {
  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
      <div className="border-2 border-black bg-barely-pink p-5 rounded-lg">
        <div className="flex flex-col items-center">
          <h2 className="text-black text-xl mb-4">Are you sure you want to delete the plan "{planTitle}"?</h2>
          <div className="flex flex-row space-x-4">
          <Button
                className="text-white hover:bg-black px-8 mr-5 py-2"
                text="Yes"
                onClick={onConfirm}
            >
            </Button>
            <Button
                className="text-white hover:bg-black px-8 ml-5 py-2"
                text="No"
                onClick={onCancel}
            >
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
}
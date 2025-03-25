"use client";

import { Button } from "./Button";

interface DeletePlanProps {
  onConfirm: () => void;
  onCancel: () => void;
  planTitle: string;
}

export default function DeletePlan({
  onConfirm,
  onCancel,
  planTitle,
}: DeletePlanProps) {
  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
      <div className="rounded-lg border-2 border-black bg-barely-pink p-5">
        <div className="flex flex-col items-center">
          <h2 className="mb-4 text-xl text-black">
            Are you sure you want to delete the plan &quot{planTitle}&quot?
          </h2>
          <div className="flex flex-row space-x-4">
            <Button
              className="mr-5 px-8 py-2 text-white hover:bg-black"
              text="Yes"
              onClick={onConfirm}
            ></Button>
            <Button
              className="ml-5 px-8 py-2 text-white hover:bg-black"
              text="No"
              onClick={onCancel}
            ></Button>
          </div>
        </div>
      </div>
    </div>
  );
}

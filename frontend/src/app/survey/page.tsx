"use client";
import Dropdown from "@/components/Dropdown";
import { useState } from "react";
import { z } from "zod";

//TODO: Style the questionnaire page
//TODO: Create handler to trigger zod schema errors on page

//Schema created using zod to test validation in the form

const surveySchema = z.object({
  major: z.string().min(1, { message: "major is required" }),
  distance: z
    .string()
    .regex(/^[^-]*$/, "distane must be positive")
    .min(1, { message: "distance is requried" }),
  semester: z.string().min(1, { message: "semester is required" }),
});

export default function QuestionnarePage() {
  function formAction(formData: FormData) {
    const formValues = Object.fromEntries(formData);
    try {
      const result = surveySchema.parse(formValues);
      const surveyData = JSON.stringify(result);
      console.log(surveyData);
    } catch (err) {
      if (err instanceof z.ZodError) {
        err.errors.forEach((error) => {
          console.error(error.message);
        });
      }
    }
  }

  const data = ["comptuer science", "mandarin", "biology"];
  return (
    <main>
      <form action={formAction}>
        <Dropdown
          items={data}
          type="text"
          name="major"
          className="border-2 border-gray-600 text-black"
        />
        <Dropdown
          type="number"
          name="distance"
          className="border-2 border-gray-600 text-black"
        />
        <Dropdown
          type="text"
          name="semester"
          className="border-2 border-gray-600 text-black"
        />
        <br />
        <button>Submit</button>
      </form>
    </main>
  );
}

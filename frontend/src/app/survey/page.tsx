"use client";
import Dropdown from "../components/ui/Dropdown";
import { z } from "zod";
import { useRef, useState } from "react";
import { Button } from "../components/ui/Button";

//TODO: Style the questionnaire page
//TODO: Create handler to trigger zod schema errors on page
//TODO: Replace dummy data placeholder with actual data

//Schema to test validation in the form
const surveySchema = z.object({
  major: z.string().min(1, { message: "Major is required" }),
  distance: z.string().min(1, { message: "Distance is requried" }),
  hours: z.string().min(1, { message: "Credit Hours are requried" }),
  semester: z.string().regex(/^(fall|spring).*$/i, "Semester is required"),
});

export default function QuestionnarePage() {
  const formRef = useRef<HTMLFormElement>(null);
  const [isFormSubmitted, setIsFormSubmitted] = useState(false);

  //The submitForm function takes in the information and validates it using the schema before creating an object with the survey information
  function submitForm(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (formRef.current) {
      const formData = new FormData(formRef.current);
      const formValues = Object.fromEntries(formData);
      try {
        const result = surveySchema.parse(formValues);
        setIsFormSubmitted(true);
        setTimeout(() => setIsFormSubmitted(false), 1000);
        console.log(result);
      } catch (err) {
        if (err instanceof z.ZodError) {
          err.errors.forEach((error) => {
            console.error(error.message);
          });
        }
      }
    }
  }

  //TODO: This data needs to be replaced by actual major data.
  const dummyData = [
    "Psychology",
    "Biology/Biological Sciences",
    "Computer Science",
    "Computer and Information Sciences",
    "Marketing/marketing management",
    "Finance",
  ];
  const semesterList = [
    `Fall ${new Date().getFullYear()}`,
    `Spring ${new Date().getFullYear() + 1}`,
  ];

  const dropdownStyling = "select-none rounded-md text-black w-56";

  return (
    <div className="flex h-screen items-center justify-center">
      <main>
        <form
          ref={formRef}
          onSubmit={submitForm}
          onKeyDown={(e) => e.key != "Enter"}
          className="max-w flex flex-col gap-6 rounded-2xl bg-dev-dog-blue p-12 text-center text-white"
        >
          <div>
            <h1>What&apos;s your major?</h1>
            <Dropdown
              items={dummyData}
              name="major"
              className={dropdownStyling}
              formStatus={isFormSubmitted}
              placeholder="Enter your major"
            />
          </div>
          <div>
            <h1>Distance between classes?</h1>
            <Dropdown
              type="number"
              name="distance"
              className={dropdownStyling}
              formStatus={isFormSubmitted}
              placeholder="Enter distance to class"
              min="0"
            />
          </div>
          <div>
            <h1>How many credit hours?</h1>
            <Dropdown
              type="number"
              name="hours"
              className={dropdownStyling}
              formStatus={isFormSubmitted}
              placeholder="Enter credit hours"
              min="0"
            />
          </div>
          <div>
            <h1>Which semester?</h1>
            <Dropdown
              items={semesterList}
              name="semester"
              className={dropdownStyling}
              formStatus={isFormSubmitted}
              placeholder="Enter semester"
            />
          </div>
          <Button text="Submit" className="text-white" />
        </form>
      </main>
    </div>
  );
}

"use client";
import Dropdown from "../components/ui/DropDown";
import { z } from "zod";
import { useRef } from "react";
import { Button } from "../components/ui/Button";
import useForm from "../hooks/useForm";

//TODO: Style the questionnaire page
//TODO: Create handler to trigger zod schema errors on page

//Schema created using zod to test validation in the form
const surveySchema = z.object({
  major: z.string().min(1, { message: "Major is required" }),
  distance: z.string().min(1, { message: "Distance is requried" }),
  hours: z.string().min(1, { message: "Credit Hours are requried" }),
  semester: z.string().regex(/^(fall|stpring).*$/i, "Semester is required"),
});

export default function QuestionnarePage() {
  const initialState = {
    major: "",
    distance: "",
    hours: "",
    semester: "",
  };

  const { values, handleChange, resetForm } = useForm(initialState);
  console.log(values);
  // const formRef = useRef<HTMLFormElement>(null);
  // function formAction(event: React.FormEvent<HTMLFormElement>) {
  //   event.preventDefault();
  //   if (formRef.current) {
  //     const formData = new FormData(formRef.current);
  //     const formValues = Object.fromEntries(formData);
  //     try {
  //       const result = surveySchema.parse(formValues);
  //       // const surveyData = JSON.stringify(result);
  //       console.log(result);
  //     } catch (err) {
  //       if (err instanceof z.ZodError) {
  //         err.errors.forEach((error) => {
  //           console.error(error.message);
  //         });
  //       }
  //     }
  //   }
  // }

  const dummyData = [
    "Web Programming",
    "Data Structures",
    "Biology",
    "Geology",
    "Algorithms",
    "Software Developement",
    "For example a really long class title",
  ];
  const semesterList = [
    `Fall ${new Date().getFullYear()}`,
    `Spring ${new Date().getFullYear() + 1}`,
  ];
  const dropdownStyling =
    "select-none rounded-md border-2 px-3 py-1 text-black focus:outline-none";
  return (
    <div className="flex h-screen items-center justify-center">
      <main>
        <form
          // ref={formRef}
          // onSubmit={formAction}
          className="flex flex-col gap-6 rounded-2xl bg-dev-dog-blue p-12 text-center"
        >
          <div>
            <h1>What&apos;s your major?</h1>
            <Dropdown
              items={dummyData}
              type="text"
              name="major"
              className={dropdownStyling}
              placeholder="Enter your major"
              onChange={handleChange}
            />
          </div>
          <div>
            <h1>Distance between classes?</h1>
            <Dropdown
              type="number"
              name="distance"
              className={dropdownStyling}
              placeholder="Enter distance to class"
              onChange={handleChange}
              min="0"
            />
          </div>
          <div>
            <h1>How many credit hours?</h1>
            <Dropdown
              type="number"
              name="hours"
              className={dropdownStyling}
              placeholder="Enter credit hours"
              onChange={handleChange}
              min="0"
            />
          </div>
          <div>
            <h1>Which semester?</h1>
            <Dropdown
              items={semesterList}
              type="text"
              name="semester"
              className={dropdownStyling}
              placeholder="Enter semester"
              onChange={handleChange}
            />
          </div>
          <Button text="Submit" className="text-white" />
        </form>
      </main>
    </div>
  );
}

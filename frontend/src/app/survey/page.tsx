"use client";
import DropDown from "@/components/DropDown";
import useForm from "../hooks/useForm";
import { useState } from "react";
import { z } from "zod";

//TODO: Style the questionnaire page
//TODO: Clarify the specification/typing of form inputs
//TODO: Create/add dropdown functionality to input fields
//TODO: Create handler to trigger zod schema errors on page

//Schema created using zod to test validation in the form
// const schema = z.object({
//   major: z.string().min(1, { message: "Major is required" }),
//   distance: z
//     .string()
//     .refine((val) => val.trim() !== "", { message: "Distance is required" })
//     .transform((val) => parseFloat(val))
//     .refine((val) => !isNaN(val), { message: "Expected a number" })
//     .refine((val) => val >= 0, { message: "Distance must be positive" }),
//   creditHours: z
//     .string()
//     .refine((val) => val.trim() !== "", {
//       message: "Credit Hours are required",
//     })
//     .transform((val) => parseFloat(val))
//     .refine((val) => !isNaN(val), { message: "Expected a number" })
//     .refine((val) => val >= 0, { message: "Credit Hours must be positive" }),
//   semester: z.string().min(1, { message: "Semester is required" }),
// });

export default function QuestionnarePage() {
  //This uses a custom useForm hook to create the form
  const { values, handleChange, resetForm } = useForm({
    major: "",
    distance: "",
    creditHours: "",
    semester: "",
  });

  // const [items, setItems] = useState(["aaclass1", "bbbbclass2", "class3cc"]);
  // const [filteredItems, setFilteredItems] = useState<string[]>([]);

  // const handleQuery = (e: React.ChangeEvent<HTMLInputElement>) => {
  //   setFilteredItems(
  //     items.filter((item) => {
  //       return item.toLowerCase().includes(e.target.value.toLowerCase());
  //     }),
  //   );
  //   handleChange(e);
  // };

  // The handleSubmit function will validate the form values and conver the data into JSON
  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    //The try catch will catch any validation errors using the Zod schema
    try {
      // schema.parse(values);
      const surveyData = JSON.stringify(values);
      console.log(surveyData);
      resetForm();
    } catch (err) {
      if (err instanceof z.ZodError) {
        err.errors.forEach((error) => {
          console.error(error.message);
        });
      }
    }
  };

  return (
    <div>
      <h1>Help Us Out First!</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>{"What's your major?"}</label>
          <DropDown />
        </div>
        <div>
          <label>{"Distance between classes?"}</label>
          <input
            type="number"
            name="distance"
            value={values.distance}
            onChange={handleChange}
            className="border-2 border-gray-300"
          />
        </div>
        <div>
          <label>{"How many credit hours?"}</label>
          <input
            type="number"
            name="creditHours"
            value={values.creditHours}
            onChange={handleChange}
            className="border-2 border-gray-300"
          />
        </div>
        <div>
          <label>{"Which semester?"}</label>
          <input
            type="text"
            name="semester"
            value={values.semester}
            onChange={handleChange}
            className="border-2 border-gray-300"
          />
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

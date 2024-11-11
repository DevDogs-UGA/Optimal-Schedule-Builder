"use client";
import useForm from "../hooks/useForm";
import { z } from "zod";

//TODO: Style the questionnaire page
//TODO: Clarify the specification/typing of form inputs
//TODO: Create/add dropdown functionality to input fields

//Schema created using zod to test validation in the form
const schema = z.object({
  major: z.string().min(1, { message: "Major is required" }),
  distance: z.number().positive({ message: "Distance must be positive" }),
  creditHours: z
    .number()
    .int()
    .positive({ message: "Credit Hours must be a positive integer" }),
  semester: z.string().min(1, { message: "Semester is required" }),
});

export default function QuestionnarePage() {
  //This uses a custom useForm hook to create the form
  const { values, handleChange, resetForm } = useForm({
    major: "",
    distance: "",
    creditHours: "",
    semester: "",
  });

  // The handleSubmit function will validate the form values and conver the data into JSON
  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    //The try catch will catch any validation errors using the Zod schema
    try {
      schema.parse(values);
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
          <input
            type="text"
            name="major"
            value={values.major}
            onChange={handleChange}
            className="border-2 border-gray-300"
          />
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

"use client";
import { z } from "zod";
import { type MouseEvent, useEffect, useRef, useState } from "react";
import { Button } from "../ui/Button";
import { Dropdown } from "@/components/ui/Dropdown";
import { ToggleButton } from "./ToggleButton";
import { TimeSelector } from "./TimeSelector";

//TODO: Replace dummy data placeholder with actual data

interface Props {
  className?: string;
}

//Schema to test validation in the form
const surveySchema = z.object({
  major: z.string().min(1, { message: "Major is required" }),
  //Days is saved into the formData as a string, so that's why this is parsing it from a stirng into an array to check for values
  days: z
    .string()
    .transform((str) => JSON.parse(str) as string[])
    .refine((arr: string[]) => arr.length > 0, {
      message: "Please Select Days",
    }),

  minDistance: z.string().min(1, { message: "Min value requried" }),
  maxDistance: z.string().min(1, { message: "Max value requried" }),
  minCreditHours: z.string().min(1, { message: "Min value requried" }),
  maxCreditHours: z.string().min(1, { message: "Max value required" }),
  term: z
    .string()
    .regex(/^(fall|spring|summer).*$/i, "Please enter valid term"),
});

export const QuestionnareForm = ({ className }: Props) => {
  const formRef = useRef<HTMLFormElement>(null);
  const [isFormSubmitted, setIsFormSubmitted] = useState(false);
  const [errorList, setErrorList] = useState<z.ZodIssue[]>([]);
  const [preferredDays, setPreferredDays] = useState<string[]>([]);
  const [daysErrorMessage, setDaysErrorMessage] = useState<string>("");

  //To save start and end time from TimeSelector component
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");

  let formResults = "";

  //The submitForm function takes in the information and validates it using the schema before creating an object with the survey information
  const submitForm = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (formRef.current) {
      const formData = new FormData(formRef.current);
      //The days list isn't an form input, so my solution was to append the list as a string to be parsed and validated
      formData.append("days", JSON.stringify(preferredDays));
      formData.append("classStartTime", startTime);
      formData.append("classEndTime", endTime);
      const formValues = Object.fromEntries(formData.entries());
      try {
        //If we want an object instead of JSON, then remove the JSON.stringify
        formResults = JSON.stringify(surveySchema.parse(formValues));
        console.log(formResults);
        clearForm();
      } catch (err) {
        if (err instanceof z.ZodError) {
          const errors = err.errors;
          setErrorList(errors);
          console.log("errors", errorList);
        }
      }
    }
  };

  //Clears form inputs and any related lists
  const clearForm = () => {
    setIsFormSubmitted(true);
    setErrorList([]);
    setPreferredDays([]);
    setTimeout(() => setIsFormSubmitted(false), 1000);
  };

  //Changes the styling on the buttons and filters on chosen days
  const selectDays = (e: React.MouseEvent<HTMLButtonElement>, text: string) => {
    e.preventDefault();
    const days = preferredDays.includes(text)
      ? preferredDays.filter((item) => item !== text)
      : [...preferredDays, text];

    setPreferredDays(days);
  };

  //This captures the error message for "days" so it can be used in the span.
  useEffect(() => {
    if (errorList) {
      const error = errorList.find((err) => err.path[0] === "days");
      setDaysErrorMessage(error?.message ?? "");
    }
  }, [errorList]);

  const getStartTime = (time: string) => {
    setStartTime(time);
  };
  const getEndTime = (time: string) => {
    setEndTime(time);
  };

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
    `Summer ${new Date().getFullYear() + 1}`,
  ];
  const creditHoursList = Array.from({ length: 18 }, (_, i) =>
    (i + 1).toString().padStart(2, "0"),
  );
  const days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"];
  const dropdownStyling = "select-none text-black";

  return (
    <div className={className}>
      <form
        ref={formRef}
        onSubmit={submitForm}
        onKeyDown={(e) => e.key != "Enter"}
        className="lg:y-20 mb-40 flex w-full flex-col gap-8 p-8 font-bold"
      >
        <div>
          <h1>What&apos;s your major?</h1>
          <Dropdown
            name="major"
            items={dummyData}
            className={dropdownStyling}
            isFormSubmitted={isFormSubmitted}
            placeholder="Enter your major"
            errorList={errorList}
          />
        </div>
        <div>
          <h1>Select preferred days</h1>
          <div className="flex w-full items-center">
            {days.map((day, index) => (
              <ToggleButton
                onClick={(e: MouseEvent<HTMLButtonElement>) =>
                  selectDays(e, day)
                }
                key={index}
                text={day[0]}
                className={`${index === 0 && "rounded-l-md border-r-0 sm:border-r-2"} ${index === days.length - 1 && "rounded-r-md border-r-2 sm:border-l-2"}`}
                isFormSubmitted={isFormSubmitted}
              />
            ))}
          </div>
          {daysErrorMessage && (
            <span className="font-thin text-bulldog-red">
              {"* " + daysErrorMessage}
            </span>
          )}
        </div>
        <div>
          <h1>Preferred start and end time?</h1>
          <div className="flex flex-col gap-4 lg:flex-row lg:items-center">
            <TimeSelector
              onChange={getStartTime}
              isFormSubmitted={isFormSubmitted}
              placeholder="AM"
            />
            <TimeSelector
              onChange={getEndTime}
              isFormSubmitted={isFormSubmitted}
              placeholder="PM"
            />
          </div>
        </div>
        <div>
          <h1>Distance between classes?</h1>
          <div className="flex items-center gap-4">
            <Dropdown
              type="number"
              name="minDistance"
              className={"select-none text-black"}
              isFormSubmitted={isFormSubmitted}
              placeholder="Min"
              min="0"
              errorList={errorList}
            />
            <h1>-</h1>
            <Dropdown
              type="number"
              name="maxDistance"
              className={"select-none text-black"}
              isFormSubmitted={isFormSubmitted}
              placeholder="Max"
              min="0"
              errorList={errorList}
            />
          </div>
        </div>
        <div>
          <h1>How many credit hours?</h1>
          <div className="flex items-center gap-4">
            <Dropdown
              type="number"
              name="minCreditHours"
              className={dropdownStyling}
              isFormSubmitted={isFormSubmitted}
              placeholder="Min"
              min="0"
              items={creditHoursList}
              errorList={errorList}
            />
            <h1>-</h1>
            <Dropdown
              type="number"
              name="maxCreditHours"
              className={dropdownStyling}
              isFormSubmitted={isFormSubmitted}
              placeholder="Max"
              min="0"
              items={creditHoursList}
              errorList={errorList}
            />
          </div>
        </div>
        <div>
          <h1>Which term?</h1>
          <Dropdown
            name="term"
            items={semesterList}
            className={dropdownStyling}
            isFormSubmitted={isFormSubmitted}
            placeholder="Enter term"
            errorList={errorList}
          />
        </div>
        <section className="mt-4 flex h-12 gap-5">
          <Button
            text="clear"
            type="reset"
            className="w-1/2 border-2 bg-white text-nearly-black transition ease-in-out hover:border-bulldog-red hover:text-bulldog-red"
            onClick={clearForm}
          />
          <Button
            text="submit"
            type="submit"
            className="w-1/2 border-dusty-pink text-white transition ease-in-out hover:border-2 hover:border-nearly-black hover:bg-green-400 hover:text-nearly-black"
          />
        </section>
      </form>
    </div>
  );
};

import Image from "next/image";
import React, { useEffect, useState } from "react";
import ToggleButton from "./ToggleButton";

interface TimeselectorProps {
  name?: string;
  clearState?: boolean;
  className?: string;
}

//Component to input time
const Timeselector = ({
  name = "",
  clearState,
  className,
}: TimeselectorProps) => {
  const [value, setValue] = useState("");
  const [meridian, setMeridian] = useState("AM");
  const formatInput = (input: string) => {
    const isColon = input.trim().endsWith(":");
    const inputValue = input.replace(/[^0-9]/g, "").slice(0, 4);

    //This formats the time input to have the colon after minutes
    let formattedValue =
      inputValue.length > 2
        ? `${inputValue.slice(0, 2)}:${inputValue.slice(2)}`
        : inputValue;
    if (isColon && formattedValue.length === 2) {
      formattedValue = formattedValue + ":";
    }

    return formattedValue;
  };

  //Handles the input change
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValue(formatInput(e.target.value));
  };

  //handles the meridian toggle button
  const changeMeridian = (e: React.MouseEvent<HTMLButtonElement>) => {
    const text = e.currentTarget.textContent === "AM" ? "PM" : "AM";
    if (text) {
      setMeridian(text);
    }
  };

  //handles quick input using "enter" key to skip to seconds
  const handleKeyPress = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === " ") {
      if (value.length >= 1 && value.length < 3) {
        setValue(value.padStart(2, "0") + ":");
      }
    }
  };

  //Use state to handle reseting the input and meridian button
  useEffect(() => {
    if (clearState) {
      setValue("");
      setMeridian("AM");
    }
  }, [clearState]);

  return (
    <div className="relative">
      {/* USER INPUT */}
      <input
        value={value}
        placeholder={"00:00"}
        onChange={handleChange}
        onKeyDown={handleKeyPress}
        maxLength={5}
        className={`w-full rounded-md border-2 px-12 py-2 outline-0 hover:border-pebble-gray ${className}`}
      />
      {/* READONLY INPUT TO CAPTURE CURRENT FORMATTED VALUE W/MERIDIAN TO SEND TO FORMDATA  */}
      <input
        title="time input"
        type="text"
        value={`${value ? value : "0:00"} ${meridian}`}
        name={name}
        className="hidden"
        readOnly
      />
      {/* TIME ICON */}
      <Image
        src="./images/timeIcon.svg"
        alt="time icon"
        width={20}
        height={20}
        className="absolute left-4 top-3"
        draggable="false"
      />
      {/* MERIDIAN TOGGLE BUTTON */}
      <ToggleButton
        text="AM"
        toggledText="PM"
        untoggledStyle="opacity-35 hover:opacity-100"
        toggledStyle="opacity-100 hover:opacity-50 text-bulldog-red"
        className="absolute -top-1.5 right-0 border-none bg-transparent p-4 hover:bg-transparent"
        onClick={changeMeridian}
        clearState={clearState}
      />
    </div>
  );
};

export default Timeselector;

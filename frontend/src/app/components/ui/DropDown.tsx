"use client";

import React, { useEffect, useState, useRef, forwardRef } from "react";
import { type z } from "zod";

interface DropdownProps {
  items?: string[];
  type?: string;
  name?: string;
  min?: string;
  className?: string;
  isFormSubmitted?: boolean;
  errorList?: z.ZodIssue[];
  placeholder?: string;
  readOnly?: boolean;
}

//Uses forwardRef to get the input field props to the parent
export const Dropdown = forwardRef<HTMLInputElement, DropdownProps>(
  (
    {
      items = [],
      type = "text",
      name,
      min,
      className,
      isFormSubmitted,
      errorList,
      placeholder,
      readOnly,
    },
    ref,
  ) => {
    //Handles Dropdown
    const [isOpen, setIsOpen] = useState(false);
    const [query, setQuery] = useState("");
    const [filteredData, setFilteredData] = useState<string[]>(items);
    const dropdownRef = useRef<HTMLUListElement>(null);

    //Handles errors
    const [error, setError] = useState<z.ZodIssue>();

    //Handles closing dropdown menu when clicking off of it
    useEffect(() => {
      const handleClose = (e: MouseEvent) => {
        if (!dropdownRef.current?.contains(e.target as Node)) {
          setIsOpen(false);
        }
      };
      document.addEventListener("mousedown", handleClose);
      return () => {
        document.removeEventListener("mousedown", handleClose);
      };
    }, [isOpen]);

    //Filters dropdown data using the current search query with regex
    const handleQuery = (e: React.ChangeEvent<HTMLInputElement>) => {
      const regex = new RegExp("^" + e.currentTarget.value, "i");
      const filter = items.filter((item) => regex.test(item));
      setFilteredData(filter);
    };

    //Handles clearing inputs
    useEffect(() => {
      setQuery("");
    }, [isFormSubmitted]);

    useEffect(() => {
      if (errorList) {
        setError(errorList.find((err) => err.path[0] === name));
      }
    }, [errorList, name]);

    const sharedStyling = "w-full rounded-md border-2 ";

    return (
      <div className="relative">
        <input
          ref={ref}
          id="input"
          onClick={() => setIsOpen(true)}
          onInput={(e) => {
            setIsOpen(true);
            setQuery(e.currentTarget.value);
            setError(undefined);
          }}
          onChange={handleQuery}
          value={query}
          type={type}
          name={name}
          className={`h-12 px-2 py-1 focus:outline-none ${sharedStyling} ${error ? "border-red-600" : " "} ${className}`}
          min={min}
          placeholder={placeholder}
          autoComplete="off"
          readOnly={readOnly}
        />
        {error && (
          <div className="absolute font-thin text-bulldog-red">
            {"* " + error.message}
          </div>
        )}
        {isOpen && filteredData.length > 0 && (
          <ul
            ref={dropdownRef}
            className={`absolute z-50 max-h-44 overflow-y-scroll bg-white text-black ${sharedStyling} ${className} scroll-smooth`}
          >
            {filteredData.map((item, index) => (
              <li
                className="px-2 py-1 text-left transition ease-in-out hover:bg-limestone"
                onClick={(e) => {
                  setQuery(e.currentTarget.textContent ?? "");
                  setIsOpen(false);
                  setError(undefined);
                }}
                key={index}
              >
                {item}
              </li>
            ))}
          </ul>
        )}
      </div>
    );
  },
);

Dropdown.displayName = "Dropdown";

"use client";

import React, { useState, useEffect, useRef } from "react";
import Image from "next/image";

interface searchFilterProps {
  name?: string;
  items?: string[];
  type?: string;
  placeholder?: string;
  className?: string;
  clearState?: boolean;
}

//Dropdown Tag Input component that contains a search input and dropdown feature
export const DropdownTagInput = ({
  name = "",
  items = [],
  type = "text",
  placeholder = "",
  className = "",
  clearState,
}: searchFilterProps) => {
  const dropdownRef = useRef<HTMLUListElement>(null);
  const [isOpen, setIsOpen] = useState(false);
  const [query, setQuery] = useState("");
  const [filteredData, setFilteredData] = useState<string[]>(items);
  const [tags, setTags] = useState<string[]>([]);

  //Handles filtering the search input
  const handleQuery = (e: React.ChangeEvent<HTMLInputElement>) => {
    setQuery(e.currentTarget.value);
    const regex = new RegExp("^" + e.currentTarget.value, "i");
    const filter = items.filter((item) => regex.test(item));
    setFilteredData(filter);
  };

  //Handles clicking on a dropdown item
  const addTag = (e: React.MouseEvent) => {
    const text = e.currentTarget.textContent ?? "";
    setTags((prev) => [...prev, text]);
    setIsOpen((prev) => !prev);
  };

  //Handles removing tag
  //The e.detail returns the number of times the event is triggered.
  //Keyboard events will always be 0. This is to prevent the "enter" key from triggering the remove tag button on accident. --> This is just for using keyboard to select choices instead of clicking.
  const removeTag = (
    e:
      | React.MouseEvent<HTMLButtonElement>
      | React.KeyboardEvent<HTMLButtonElement>,
    index: number,
  ) => {
    e.preventDefault();

    if (e.detail > 0) {
      setTags(tags.filter((_, i) => i !== index));
    }
  };

  //Handles Enter Keypress
  const handleKeyPress = (e: React.KeyboardEvent) => {
    if (e.key === "Enter") {
      e.preventDefault();
      const activeElement = document.activeElement as HTMLElement;
      if (activeElement.tagName === "INPUT" && query !== "") {
        setTags((prev) => [...prev, query]);
        setIsOpen((prev) => !prev);
        setQuery("");
      } else if (activeElement.tagName === "LI") {
        const text = activeElement.textContent ?? "";
        setTags((prev) => [...prev, text]);
        setIsOpen((prev) => !prev);
        setQuery("");
      }
    }
  };

  //Handles closing dropdown menu when clicked off
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

  //This useEffect is to handle clearing the input when the form is submitted or cleared
  useEffect(() => {
    if (clearState && query !== "") {
      setQuery("");
    }
  }, [clearState, query]);

  return (
    // CONTAINER FOR ENTIRE COMPONENT
    <div className="relative min-w-full">
      <div
        className={`rounded-md border-2 border-pebble-gray ${className} lg:flex`}
      >
        {/* //CONTAINER FOR TAGS */}
        <div
          className={`border-pebble-gray bg-white ${className} no-scrollbar flex-shrink-1 flex items-center gap-7 overflow-x-scroll border-none lg:gap-4 ${tags.length !== 0 ? "px-2 py-1" : ""} ${tags.length === 1 ? "min-w-fit" : ""} lg:max-w-28`}
        >
          {/* INDIVIDUAL TAGS */}
          {tags.length !== 0 &&
            tags.map((index, key) => (
              <div
                key={key}
                className="relative h-6 rounded-l-md bg-bulldog-red px-2 lg:mr-3"
              >
                {index}
                {/* REMOVE BUTTON */}
                <button
                  type="button"
                  title="Remove Tag"
                  className="absolute -right-5 z-10 h-6 w-6 rounded-r-md bg-bulldog-red"
                  onClick={(e) => removeTag(e, key)}
                >
                  <Image
                    src="./images/removeButton.svg"
                    layout="fixed"
                    height={20}
                    width={20}
                    alt="Remove Tag"
                    objectFit="contain"
                    draggable="false"
                  />
                </button>
              </div>
            ))}
        </div>
        <input
          name={name}
          value={query}
          type={type}
          min={0}
          onChange={handleQuery}
          onClick={() => setIsOpen((prev) => !prev)}
          placeholder={placeholder}
          className={`${className} relative w-full rounded-md border-none p-2 outline-none lg:flex-shrink`}
          autoComplete="off"
          onKeyDown={handleKeyPress}
        />
      </div>
      {/* DROPDOWN MENU */}
      {isOpen && filteredData.length !== 0 && (
        <ul
          ref={dropdownRef}
          className={`absolute max-h-52 w-full overflow-y-scroll scroll-smooth rounded-md border-2 border-pebble-gray bg-white ${className} z-10 px-0`}
        >
          {/* DROPDOWN ITEMS */}
          {filteredData.map((item, index) => (
            <li
              key={index}
              onClick={(e) => addTag(e)}
              onKeyDown={handleKeyPress}
              className="bg-inherit px-2 py-1 hover:brightness-75"
              tabIndex={0}
            >
              {item}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

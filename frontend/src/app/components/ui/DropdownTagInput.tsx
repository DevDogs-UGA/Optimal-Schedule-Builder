"use client";

import React, { useEffect, useState, useRef } from "react";
import Image from "next/image";

interface DropdownTagInputProps {
  items?: string[];
  type?: string;
  name?: string;
  min?: string;
  className?: string;
  formStatus?: boolean;
  placeholder?: string;
}

export default function DropdownTagInput({
  items = [],
  type = "text",
  name,
  className,
  min,
  placeholder,
  formStatus,
}: DropdownTagInputProps) {
  const [isOpen, setIsOpen] = useState(false);
  const [query, setQuery] = useState("");
  const [filteredData, setFilteredData] = useState<string[]>(items);
  const [tags, setTags] = useState<string[]>([]);
  const dropdownRef = useRef<HTMLUListElement>(null);
  const searchBarRef = useRef<HTMLDivElement>(null);

  //Hanldles input clears when a form is submitted
  useEffect(() => {
    setQuery("");
  }, [formStatus]);

  //Handles closing dropdown menu when clicking off of it
  useEffect(() => {
    const handleClose = (e: MouseEvent) => {
      if (!dropdownRef.current?.contains(e.target as Node)) {
        setIsOpen(false);
      }
    };
    document.addEventListener("mousedown", handleClose);
    return () => document.removeEventListener("mousedown", handleClose);
  }, [isOpen]);

  //Handles horizontal scrolling using the scrollwheel on tag container
  useEffect(() => {
    const scroll = searchBarRef.current;
    if (scroll) {
      const onWheel = (e: WheelEvent) => {
        e.preventDefault();
        scroll.scrollBy(e.deltaY, 0);
      };
      scroll.addEventListener("wheel", onWheel);
      return () => scroll.removeEventListener("wheel", onWheel);
    }
  }, []);

  //Filters dropdown data using the current search query with regex
  const handleQuery = (e: React.ChangeEvent<HTMLInputElement>) => {
    const regex = new RegExp("^" + e.currentTarget.value, "i");
    const filter = items.filter((item) => regex.test(item));
    setFilteredData(filter);
  };

  //Adding tags
  const addTag = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === "Enter") {
      const input = e.target as HTMLInputElement;
      setTags([...tags, input.value]);
      setQuery("");
    }
  };

  //Removing tags
  const removeTag = (index: number) => {
    setTags([...tags.filter((_, i) => i !== index)]);
  };

  return (
    <div id="dropdown-container" className="relative">
      <div
        ref={searchBarRef}
        className={`flex h-12 w-full items-center overflow-x-auto overflow-y-hidden rounded-md border-2 bg-white p-1 hover:scroll-smooth ${className}`}
      >
        <ul className="flex max-w-44 gap-2 p-1">
          {tags.length > 0 &&
            tags?.map((item, index) => (
              <li key={index}>
                <span className="flex items-center justify-center gap-2 rounded-md bg-bulldog-red px-2 py-1 text-white">
                  {item}
                  <button
                    onClick={() => removeTag(index)}
                    className="flex h-4 w-4 items-center justify-center text-black"
                  >
                    <Image
                      height={20}
                      width={20}
                      src="/images/removeTagButton.svg"
                      alt="Button to remove tabs"
                    />
                  </button>
                </span>
              </li>
            ))}
          <input
            onClick={() => setIsOpen(true)}
            onInput={(e) => {
              setIsOpen(true);
              setQuery(e.currentTarget.value);
            }}
            onChange={handleQuery}
            value={query}
            type={type}
            name={name}
            className={`rounded-md focus:outline-none ${tags.length > 0 ? "w-24" : "w-100%"} ${className}`}
            min={min}
            onKeyUp={addTag}
            placeholder={placeholder}
            autoComplete="off"
          />
        </ul>
      </div>
      {isOpen && filteredData.length > 0 && (
        <ul
          ref={dropdownRef}
          className={`absolute z-50 max-h-44 w-full overflow-y-scroll rounded-md border-2 bg-white text-black ${className}`}
        >
          {filteredData.map((item, index) => (
            <li
              className="px-2 py-1 text-left transition ease-in-out hover:bg-limestone"
              onClick={(e) => {
                setTags([...tags, e.currentTarget.textContent ?? ""]);
                setIsOpen(false);
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
}

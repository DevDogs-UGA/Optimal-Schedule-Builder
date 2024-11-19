"use client";

import React, { useEffect, useState, useRef } from "react";

interface DropdownProps {
  items?: string[];
  type?: string;
  name?: string;
  className?: string;
  min?: string;
  placeholder?: string;
  formStatus?: boolean;
}

export default function Dropdown({
  items = [],
  type = "text",
  name,
  className,
  min,
  placeholder,
  formStatus,
}: DropdownProps) {
  const [isOpen, setIsOpen] = useState(false);
  const [query, setQuery] = useState("");
  const [filteredData, setFilteredData] = useState<string[]>(items);
  const dropdownRef = useRef<HTMLUListElement>(null);

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

  const handleQuery = (event: React.ChangeEvent<HTMLInputElement>) => {
    const regex = new RegExp("^" + event.currentTarget.value, "i");
    const filter = items.filter((item) => regex.test(item));
    setFilteredData(filter);
  };

  useEffect(() => {
    setQuery("");
  }, [formStatus]);

  return (
    <>
      <input
        id="input"
        onClick={() => setIsOpen(true)}
        onInput={(e) => {
          setIsOpen(true);
          setQuery(e.currentTarget.value);
        }}
        onChange={handleQuery}
        value={query}
        type={type}
        name={name}
        className={`border-2 px-2 py-1 focus:outline-none ${className}`}
        min={min}
        placeholder={placeholder}
        autoComplete="off"
      />

      {isOpen && filteredData.length > 0 && (
        <ul
          ref={dropdownRef}
          className={`absolute bg-white text-black ${className} max-h-44 overflow-y-scroll`}
        >
          {filteredData.map((item, index) => (
            <li
              className="p-1 text-left hover:bg-limestone"
              onClick={(e) => {
                setQuery(e.currentTarget.textContent ?? "");
                setIsOpen(false);
              }}
              key={index}
            >
              {item}
            </li>
          ))}
        </ul>
      )}
    </>
  );
}

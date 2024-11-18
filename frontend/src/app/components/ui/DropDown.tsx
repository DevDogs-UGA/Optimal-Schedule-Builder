"use client";

import React, { useEffect, useState, useRef } from "react";

interface DropdownProps {
  items?: string[];
  type?: string;
  name?: string;
  className?: string;
  min?: string;
  placeholder?: string;
  onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

export default function Dropdown({
  items = [],
  type = "text",
  name,
  className,
  min,
  placeholder,
  onChange,
}: DropdownProps) {
  const [isOpen, setIsOpen] = useState(false);
  const [query, setQuery] = useState("");
  const [filteredData, setFilteredData] = useState<string[]>(items);
  const dropdownRef = useRef<HTMLUListElement>(null);

  const handleQuery = (data: string) => {
    const regex = new RegExp("^" + data, "i");
    const filter = items.filter((item) => regex.test(item));
    setFilteredData(filter);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const data = e.target.value;
    setQuery(data);
    handleQuery(data);
    if (onChange) {
      onChange(e);
    }
  };
  const handleItemClick = (e: React.MouseEvent<HTMLLIElement>) => {
    const data = e.currentTarget.textContent;
    setQuery(data ?? "");
    handleQuery(data ?? "");
    setIsOpen(false);
  };

  useEffect(() => {
    const handleClose = (e: MouseEvent) => {
      if (!dropdownRef.current?.contains(e.target as Node) && isOpen) {
        setIsOpen(false);
      }
    };
    document.addEventListener("mousedown", handleClose);
    return () => {
      document.removeEventListener("mousedown", handleClose);
    };
  });

  return (
    <section>
      <input
        onClick={() => setIsOpen(true)}
        onInput={handleInputChange}
        value={query}
        type={type}
        name={name}
        className={className}
        min={min}
        placeholder={placeholder}
      />

      {isOpen && type === "text" && (
        <ul
          ref={dropdownRef}
          className={`absolute bg-white text-black ${className}`}
        >
          {filteredData.map((item, index) => (
            <li className="text-left" onClick={handleItemClick} key={index}>
              {item}
            </li>
          ))}
        </ul>
      )}
    </section>
  );
}

"use client";

import React, { useEffect, useState, useRef } from "react";

interface DropdownProps {
  items?: string[];
  type?: string;
  name?: string;
  className?: string;
}

export default function Dropdown({
  items = [],
  type,
  name,
  className,
}: DropdownProps) {
  const [isOpen, setIsOpen] = useState(false);
  const [query, setQuery] = useState("");
  const [filteredData, setFilteredData] = useState<string[]>(items);
  const dropdownRef = useRef<HTMLUListElement>(null);

  const handleItemClick = (e: React.MouseEvent<HTMLLIElement>) => {
    setQuery(e.currentTarget.textContent ?? "");
    setIsOpen(false);
  };

  const handleQuery = (e: React.ChangeEvent<HTMLInputElement>) => {
    setQuery(e.target.value);
    const regex = new RegExp("^" + e.target.value, "i");
    const filter = items.filter((item) => regex.test(item));
    setFilteredData(filter);
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
        onChange={handleQuery}
        value={query}
        type={type}
        name={name}
        className={className}
      />
      <ul ref={dropdownRef} className="absolute bg-white text-black">
        {isOpen &&
          filteredData.map((item, index) => (
            <li onClick={handleItemClick} key={index}>
              {item}
            </li>
          ))}
      </ul>
    </section>
  );
}

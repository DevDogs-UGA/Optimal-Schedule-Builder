"use client";
import React from "react";
import Image from "next/Image";

interface RegisteredClassProps {
  classHeading: string;
  classNumber: string;
  fullClassName: string;
  onClick: () => void;
}

const RegisteredClass = ({
  classHeading,
  classNumber,
  fullClassName,
  onClick,
}: RegisteredClassProps) => {
  return (
    <div className="flex min-w-60 bg-white py-1 pl-8 hover:brightness-95">
      <button
        className="h-8 w-8 flex-shrink-0 rounded-sm"
        title="Remove Course"
        onClick={() => onClick()}
      >
        <Image
          src="images/removeButton.svg"
          alt="Remove Course"
          width={20}
          height={20}
          className="rounded-sm"
        />
      </button>
      <div className="overflow-hidden text-ellipsis">
        <h1 className="font-bold">{`${classHeading} ${classNumber}`}</h1>
        <span className="text-nowrap">{fullClassName}</span>
      </div>
    </div>
  );
};

export default RegisteredClass;

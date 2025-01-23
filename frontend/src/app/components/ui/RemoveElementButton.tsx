"use client";
import Image from "next/image";
interface buttonProps {
  width: number;
  height: number;
  onClick: () => void;
}

const RemoveElementButton = ({ width, height, onClick }: buttonProps) => {
  return (
    <button
      className="h-8 w-8 flex-shrink-0 rounded-sm"
      title="Remove Course"
      onClick={() => onClick()}
    >
      <Image
        src="images/removeButton.svg"
        alt="Remove Course"
        width={width}
        height={height}
        className="h-auto w-auto rounded-sm"
        draggable="false"
      />
    </button>
  );
};

export default RemoveElementButton;

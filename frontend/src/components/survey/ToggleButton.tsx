import React, { useState, useEffect } from "react";

interface toggleButtonData {
  key?: number;
  text?: string;
  data?: string;
  onClick?: (e: React.MouseEvent<HTMLButtonElement>) => void;
  clearState?: boolean;
}

interface toggleButtonProps {
  toggledText?: string;
  toggledStyle?: string;
  untoggledStyle?: string;
  className?: string;
}

const ToggleButton = ({
  text = "",
  data = "",
  onClick,
  clearState = false,
  toggledText = text,
  className = "",
  toggledStyle = className,
  untoggledStyle = className,
}: toggleButtonProps & toggleButtonData) => {
  const [toggled, setToggled] = useState(false);

  useEffect(() => {
    if (clearState) {
      setToggled(false);
    }
  }, [clearState]);

  return (
    <button
      className={`duration-300 ease-in-out ${className} ${toggled ? toggledStyle : untoggledStyle}`}
      data-value={data}
      onClick={(e: React.MouseEvent<HTMLButtonElement>) => {
        e.preventDefault();
        if (onClick) {
          onClick(e);
        }
        setToggled((prev) => !prev);
      }}
    >
      {toggled ? toggledText : text}
    </button>
  );
};

export default ToggleButton;

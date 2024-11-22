import { useEffect, useState } from "react";

interface ToggleButtonProps {
  text?: string;
  onClick?: (e: React.MouseEvent<HTMLButtonElement>) => void;
  className?: string;
  isFormSubmitted?: boolean;
}

export const ToggleButton = ({
  text,
  className,
  onClick,
  isFormSubmitted,
}: ToggleButtonProps) => {
  const [isToggled, setIsToggled] = useState(false);
  const sharedStyling =
    "text-xl border-r-0 border-2 sm:border-2 px-[7.5%] py-[5%] duration-300 ease-in-out ";
  useEffect(() => {
    if (isFormSubmitted) {
      setIsToggled(false);
    }
  }, [isFormSubmitted]);
  return (
    <button
      className={
        !isToggled
          ? `bg-white text-nearly-black hover:border-pink-200 hover:bg-dusty-pink ${sharedStyling} ${className}`
          : `border-bulldog-red bg-bulldog-red text-white duration-300 ease-in-out hover:border-dusty-pink hover:bg-red-800 ${sharedStyling} ${className}`
      }
      onClick={(e) => {
        e.preventDefault();
        setIsToggled(!isToggled);
        if (onClick) {
          onClick(e);
        }
      }}
    >
      {text}
    </button>
  );
};

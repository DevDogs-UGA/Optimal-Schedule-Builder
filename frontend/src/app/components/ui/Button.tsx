interface props {
  onClick?: () => void;
  className?: string;
  text?: string;
  type?: "submit" | "reset" | "button";
}

export const Button = ({ onClick, className, text, type }: props) => {
  return (
    <button
      className={`rounded-lg bg-bulldog-red px-4 py-2 font-semibold uppercase ${className}`}
      onClick={(e) => {
        if (type === "button") {
          e.preventDefault();
        }
        if (onClick) {
          onClick();
        }
      }}
      type={type}
    >
      {text}
    </button>
  );
};

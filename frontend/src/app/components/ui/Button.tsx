interface props {
  onClick?: () => void;
  className?: string;
  text?: string;
}

export const Button = ({ onClick, className, text }: props) => {
  return (
    <button
      className={`rounded-lg bg-bulldog-red px-4 py-2 font-semibold ${className} uppercase text-white}`}
      onClick={onClick}
    >
      {text}
    </button>
  );
};

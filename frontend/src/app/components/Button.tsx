interface props {
  onClick?: () => void;
  className?: string;
  text?: string;
}

export const Button = ({ onClick, className, text }: props) => {
  return (
    <button className={`${className}`} onClick={onClick}>
      {text}
    </button>
  );
};

interface props {
  text?: string;
}

export const SearchFilter = ({ text }: props) => {
  return (
    <div className="flex w-4/5 items-center justify-between">
      <label className="text-xl font-bold">{text}</label>
      {/*TODO: replace placeholder w/ dropdown component*/}
      <input
        type="text"
        className="w-3/4 border-2 border-dusty-pink"
        placeholder="Dropdown Placeholder"
      />
    </div>
  );
};

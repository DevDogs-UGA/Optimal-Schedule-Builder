interface props {
  text?: string;
}

export const SearchFilter = ({ text }: props) => {
  return (
    <div className="item-center flex flex-col sm:w-4/5 sm:flex-row sm:items-center sm:justify-between">
      <label className="text-xl font-bold">{text}</label>
      {/*TODO: replace placeholder w/ dropdown component*/}
      <input
        type="text"
        className="border-2 border-dusty-pink sm:w-3/4"
        placeholder="Dropdown Placeholder"
      />
    </div>
  );
};

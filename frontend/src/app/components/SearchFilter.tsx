interface props {
  text?: string;
}

export const SearchFilter = ({ text }: props) => {
  return (
    <div className="item-center xs:w-4/5 xs:flex-row xs:items-center xs:justify-between flex flex-col">
      <label className="text-xl font-bold">{text}</label>
      {/*TODO: replace placeholder w/ dropdown component*/}
      <input
        type="text"
        className="xs:w-3/4 border-2 border-dusty-pink"
        placeholder="Dropdown Placeholder"
      />
    </div>
  );
};

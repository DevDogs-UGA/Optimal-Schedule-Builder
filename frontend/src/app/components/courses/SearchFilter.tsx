import { DropdownSearchInput } from "../ui/DropdownSearchInput";

interface props {
  text?: string;
  items?: string[];
  className?: string;
}

export const SearchFilter = ({ text, items, className }: props) => {
  return (
    <div className={`sm:grid sm:grid-cols-5 sm:items-center ${className}`}>
      <label className="col-start-1 text-xl font-bold">{text}</label>
      <div className="col-span-3">
        <DropdownSearchInput items={items} />
      </div>
    </div>
  );
};

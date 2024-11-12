import { useState } from "react";

export default function DropDown() {
  const data = ["Computer Programming", "Biology", "Mandarin Chinese"];
  const [query, setQuery] = useState("");
  const [filteredData, setFilteredData] = useState<string[]>([]);

  const handleQuery = (e: React.ChangeEvent<HTMLInputElement>) => {
    setQuery(e.target.value);
    const regex = new RegExp(e.target.value, "i");
    setFilteredData(data.filter((item) => regex.test(item)));
    console.log(filteredData);
  };

  return (
    <>
      <input
        type="text"
        name="major"
        value={query}
        onChange={handleQuery}
        className="border-2 border-gray-300"
      />
      {filteredData.map((item) => {
        <li>{item}</li>;
      })}
    </>
  );
}

import RemoveElementButton from "../ui/RemoveElementButton";
import { useState } from "react";

interface RegisteredClassProps {
  subject: string;
  courseNumber: string;
  courseName: string;
  onClick: () => void;
}

//Course component to show the class major, number, and name
const RegisteredClass = ({
  subject,
  courseNumber,
  courseName,
  onClick,
}: RegisteredClassProps) => {
  const [removeElement, setRemoveElement] = useState(false);
  const removeAnimation = () => {
    setRemoveElement(true);
    setTimeout(() => {
      onClick();
      setRemoveElement(false);
    }, 100);
  };

  return (
    <article
      className={`flex min-w-60 bg-white py-1 pl-8 duration-100 ease-out hover:translate-x-1 hover:brightness-95 ${removeElement ? "opacity-0" : ""}`}
    >
      <RemoveElementButton height={20} width={20} onClick={removeAnimation} />
      <div className="overflow-hidden text-ellipsis">
        <h1 className="font-bold">{`${subject} ${courseNumber}`}</h1>
        <span className="text-nowrap">{courseName}</span>
      </div>
    </article>
  );
};

export default RegisteredClass;

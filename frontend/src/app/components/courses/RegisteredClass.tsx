import RemoveElementButton from "../ui/RemoveElementButton";

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
  return (
    <div className="flex min-w-60 bg-white py-1 pl-8 hover:brightness-95">
      <RemoveElementButton height={20} width={20} onClick={onClick} />
      <div className="overflow-hidden text-ellipsis">
        <h1 className="font-bold">{`${subject} ${courseNumber}`}</h1>
        <span className="text-nowrap">{courseName}</span>
      </div>
    </div>
  );
};

export default RegisteredClass;

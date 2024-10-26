import { ClassData } from "../../../types/scheduleTypes";
import DayClass from "@/components/schedules/DayClass";

interface DayScheduleBoardProps {
  day: string;
  classesInfo: ClassData[];
}

export default function DayScheduleBoard({
  day,
  classesInfo,
}: DayScheduleBoardProps) {
  const getTimeDifference = (time1: string, time2: string): number => {
    const date1 = new Date(`1970/01/01 ${time1}`);
    const date2 = new Date(`1970/01/01 ${time2}`);
    const differenceInMinutes = Math.abs(
      (date1.getTime() - date2.getTime()) / (1000 * 60),
    );
    return differenceInMinutes;
  };

  return (
    <div className="mx-auto mt-10 h-[70vh] w-full max-w-md rounded-xl bg-pink-100 p-6">
      <h2 className="text mb-4 text-xl font-bold">{day}</h2>
      <div className="space-y-2">
        {classesInfo.map((classData, index) => {
          const nextClass = classesInfo[index + 1];
          const timeDifference = nextClass
            ? getTimeDifference(classData.timeStart, nextClass.timeStart)
            : null;

          return (
            <DayClass
              key={`${day}-${classData.classTitle}-${index}`}
              {...classData}
              timeDifference={timeDifference} // Pass the difference as a prop
            />
          );
        })}
      </div>
    </div>
  );
}

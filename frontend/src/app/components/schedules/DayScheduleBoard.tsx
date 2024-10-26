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

    // If time2 is earlier than time1, adjust by adding 24 hours to time2

    date2.setHours(date2.getHours() + 12);
    date1.setHours(date1.getHours() + 12);

    const differenceInMinutes = Math.abs(
      (date2.getTime() - date1.getTime()) / (1000 * 60),
    );
    return differenceInMinutes;
  };

  return (
    <div className="mx-auto mt-10 h-[70vh] w-full max-w-md rounded-xl bg-pink-100 p-6">
      <h2 className="text mb-4 text-xl font-bold">{day}</h2>
      <div className="">
        {classesInfo.map((classData, index) => {
          const referenceTime = "8:00 am"; // Always compare to 8:00 AM
          const timeDifference = getTimeDifference(
            referenceTime,
            classData.timeStart,
          );

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

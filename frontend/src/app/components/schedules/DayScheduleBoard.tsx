import { ClassData } from "@/types/scheduleTypes";
import DayClass from "@/components/schedules/DayClass";

interface DayScheduleBoardProps {
  day: string;
  classesInfo: ClassData[];
}

export default function DayScheduleBoard({
  day,
  classesInfo,
}: DayScheduleBoardProps) {
  return (
    <div className="h-[70vh] mx-auto mt-10 w-full rounded-xl max-w-md bg-pink-100 p-6">
      <h2 className="mb-4 text-xl font-bold">{day}</h2>
      <div className="space-y-2">
        {classesInfo.map((classData, index) => (
          <DayClass
            key={` ${day}-${classData.classTitle}-${index}`}
            {...classData}
          />
        ))}
      </div>
    </div>
  );
}

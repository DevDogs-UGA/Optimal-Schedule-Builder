import { WeekSchedule as WeekScheduleType } from "@/types/scheduleTypes";
import DayScheduleBoard from "@/components/schedules/DayScheduleBoard";

interface WeekScheduleProps {
  weekData: WeekScheduleType;
}

export default function WeekSchedule({ weekData }: WeekScheduleProps) {
  return (
    <div className="3xl:grid-cols-5 grid grid-cols-1 gap-4 p-6 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 2xl:grid-cols-5 ">
      {Object.entries(weekData).map(([day, classes]) => (
        <DayScheduleBoard key={day} day={day} classesInfo={classes} />
      ))}
    </div>
  );
}

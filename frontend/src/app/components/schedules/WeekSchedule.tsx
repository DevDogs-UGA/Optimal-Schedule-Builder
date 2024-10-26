import { type WeekSchedule as WeekScheduleType } from "../../../types/scheduleTypes";
import DayScheduleBoard from "@/components/schedules/DayScheduleBoard";
import EmblaCarousel from "../ui/EmblaCarousel";

interface WeekScheduleProps {
  weekData: WeekScheduleType;
}

export default function WeekSchedule({ weekData }: WeekScheduleProps) {
  // Convert each DayScheduleBoard into a carousel slide
  const slides = Object.entries(weekData).map(([day, classes]) => (
    <DayScheduleBoard key={day} day={day} classesInfo={classes} />
  ));

  return (
    <div className="mx-auto min-h-screen md:w-[85%]">
      {/* Mobile View: Show Carousel */}
      <div className="block md:hidden">
        <EmblaCarousel slides={slides} />
      </div>

      {/* Desktop View: Show Grid */}
      <div className="hidden grid-cols-1 gap-4 p-6 md:grid md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 2xl:grid-cols-5">
        {Object.entries(weekData).map(([day, classes]) => (
          <DayScheduleBoard key={day} day={day} classesInfo={classes} />
        ))}
      </div>
    </div>
  );
}

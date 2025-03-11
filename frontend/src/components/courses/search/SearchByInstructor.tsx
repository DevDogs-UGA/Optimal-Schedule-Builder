import { Error } from "@/components/Toasts";
import Combobox from "@/components/ui/Combobox";
import useQuery from "@/hooks/useQuery";
import useToast from "@/hooks/useToast";
import type { Course } from "@/schemas/serverQueries";
import { useCallback, useEffect, useMemo, useState } from "react";
import { PiNetworkSlashDuotone } from "react-icons/pi";

type InputState =
  | Record<string, never>
  | {
      instructor: string;
    }
  | {
      instructor: string;
      course: string;
    };

interface Props {
  defaultValue?: InputState;
  onChange?: (course: Course | null) => void;
  onInput?: (value: InputState) => void;
}

export default function SearchByInstructor({
  defaultValue,
  onChange,
  onInput,
}: Props) {
  const [instructor, setInstructor] = useState(defaultValue?.instructor);
  const dispatchError = useToast(Error);

  const instructorsQuery = useQuery(
    "getAllInstructors",
    {},
    {
      initialData: [
        "Brad Barnes",
        "Michael Cotterell",
        "Salvatore Lamarca",
        "Sachin Meena",
        "Bill Hollingsworth",
      ],
    },
  );

  const coursesQuery = useQuery(
    "getCoursesByProfessor",
    { professor: instructor ?? "" },
    {
      enabled: instructor !== undefined,
      //@ts-expect-error Dummy data
      initialData:
        instructor === "Sachin Meena"
          ? [
              {
                athenaTitle: "Data Structures",
                courseNumber: "2720",
                courseId: 2720,
                subject: "CSCI",
              },
              {
                athenaTitle: "Algorithms",
                courseNumber: "4470",
                courseId: 4300,
                subject: "CSCI",
              },
            ]
          : [],
    },
  );

  const instructors = useMemo(
    () => instructorsQuery.data?.map((value) => ({ value, content: value })),
    [instructorsQuery.data],
  );

  const courses = useMemo(
    () =>
      coursesQuery.data?.map((course) => ({
        value: course.courseId.toString(),
        content: `(${course.courseNumber}) ${course.athenaTitle}`,
      })),
    [coursesQuery.data],
  );

  const handleInstructorChange = useCallback(
    (value: string | undefined) => {
      setInstructor(value);
      onInput?.(value ? { instructor: value } : {});
    },
    [setInstructor, onInput],
  );

  const handleCourseChange = useCallback(
    (courseId: string | undefined) => {
      if (instructor === undefined) {
        return;
      }

      const id = Number(courseId);
      const course = coursesQuery.data?.find(
        (course) => course.courseId === id,
      );

      if (course === undefined) {
        onChange?.(null);
        return;
      }

      onInput?.({ instructor, course: courseId });
      onChange?.(course);
    },
    [onChange, coursesQuery.data, instructor, onInput],
  );

  useEffect(() => {
    if (
      !(defaultValue && "instructor" in defaultValue && instructorsQuery.data)
    ) {
      return;
    }

    if (!instructorsQuery.data.includes(defaultValue.instructor)) {
      onInput?.({});
      onChange?.(null);
      return;
    }

    if (!("course" in defaultValue && coursesQuery.data)) {
      return;
    }

    const course = coursesQuery.data.find(
      (c) => c.courseId === Number(defaultValue.course),
    );

    if (course !== undefined) {
      onChange?.(course);
      return;
    }

    onInput?.({ instructor: defaultValue.instructor });
    onChange?.(null);
  }, [
    defaultValue,
    instructorsQuery.data,
    coursesQuery.data,
    onChange,
    onInput,
  ]);

  useEffect(() => {
    if (instructorsQuery.isError) {
      dispatchError({
        icon: PiNetworkSlashDuotone,
        message: "Could not connect to course information service.",
      });
    }
  }, [instructorsQuery.isError, dispatchError]);

  useEffect(() => {
    if (coursesQuery.isError) {
      dispatchError({
        icon: PiNetworkSlashDuotone,
        message: "Could not connect to course information service.",
      });
    }
  }, [coursesQuery.isError, dispatchError]);

  return (
    <fieldset className="grid w-full max-w-96 grid-cols-[max-content_1fr] grid-rows-2 items-center gap-x-3 gap-y-6">
      <label className="contents">
        <span className="text-lg font-bold">Instructor:</span>
        <Combobox
          defaultValue={defaultValue?.instructor}
          items={instructors}
          searchPlaceholder="Search instructors..."
          selectPlaceholder="Select an Instructor"
          onChange={handleInstructorChange}
        />
      </label>

      <label className="contents">
        <span className="text-lg font-bold">Course:</span>
        <Combobox
          defaultValue={
            defaultValue && "course" in defaultValue
              ? defaultValue.course
              : undefined
          }
          disabled={instructor === undefined}
          items={courses}
          onChange={handleCourseChange}
          searchPlaceholder={`Search ${instructor?.split(" ")[1] ?? ""} courses...`}
          selectPlaceholder={
            instructor
              ? `Select a ${instructor?.split(" ")[1] ?? ""} Course`
              : "Awaiting Instructor Selection..."
          }
        />
      </label>
    </fieldset>
  );
}

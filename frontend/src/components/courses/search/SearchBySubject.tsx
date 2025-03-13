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
      subject: string;
    }
  | {
      subject: string;
      course: string;
    };

interface Props {
  /**
   * The input state for the initial search query.
   */
  defaultValue?: InputState;
  /**
   * An event listener which fires when a course is selected.
   * @param course The selected course.
   */
  onChange?: (course: Course | null) => void;
  /**
   * An event listener which fires when the search query is
   * updates.
   * @param value The input state associated for the search query.
   */
  onInput?: (value: InputState) => void;
}

/**
 * Search for subjects by code. Then, once a subject is
 * selected, search for listed under the selected subject.
 */
export default function SearchBySubject({
  defaultValue,
  onChange,
  onInput,
}: Props) {
  const [subject, setSubject] = useState(defaultValue?.subject);
  const dispatchError = useToast(Error);

  const subjectsQuery = useQuery(
    "getAllSubjects",
    {},
    {
      // TODO: Remove dummy data once a connection can be made to the course information service
      initialData: ["CSCI", "MATH", "ARTI", "PHYS", "PHIL", "ENGR", "STAT"],
    },
  );

  const coursesQuery = useQuery(
    "getCoursesByMajor",
    { major: subject ?? "" },
    {
      enabled: subject !== undefined,
      // TODO: Remove dummy data once a connection can be made to the course information service
      //@ts-expect-error Dummy data
      initialData:
        subject === "CSCI"
          ? [
              {
                athenaTitle: "Software Development",
                courseNumber: "1302",
                courseId: 1302,
                subject: "CSCI",
              },
              {
                athenaTitle: "Systems Programming",
                courseNumber: "1730",
                courseId: 1730,
                subject: "CSCI",
              },
              {
                athenaTitle: "Data Structures",
                courseNumber: "2720",
                courseId: 2720,
                subject: "CSCI",
              },
              {
                athenaTitle: "Web Programming",
                courseNumber: "4300",
                courseId: 4300,
                subject: "CSCI",
              },
            ]
          : [],
    },
  );

  const subjects = useMemo(
    () => subjectsQuery.data?.map((value) => ({ value, content: value })),
    [subjectsQuery.data],
  );

  const courses = useMemo(
    () =>
      coursesQuery.data?.map((course) => ({
        value: course.courseId.toString(),
        content: `(${course.courseNumber}) ${course.athenaTitle}`,
      })),
    [coursesQuery.data],
  );

  const handleSubjectChange = useCallback(
    (value: string | undefined) => {
      setSubject(value);
      onInput?.(value ? { subject: value } : {});
    },
    [setSubject, onInput],
  );

  const handleCourseChange = useCallback(
    (courseId: string | undefined) => {
      if (subject === undefined) {
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

      onInput?.({ subject, course: courseId });
      onChange?.(course);
    },
    [onChange, coursesQuery.data, subject, onInput],
  );

  /**
   * If the provided `defaultValue` is invalid,
   * reset the input state and selected course.
   */
  useEffect(() => {
    if (!(defaultValue && "subject" in defaultValue && subjectsQuery.data)) {
      return;
    }

    if (!subjectsQuery.data.includes(defaultValue.subject)) {
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

    onInput?.({ subject: defaultValue.subject });
    onChange?.(null);
  }, [defaultValue, subjectsQuery.data, coursesQuery.data, onChange, onInput]);

  useEffect(() => {
    if (subjectsQuery.isError) {
      dispatchError({
        icon: PiNetworkSlashDuotone,
        message: "Could not connect to course information service.",
      });
    }
  }, [subjectsQuery.isError, dispatchError]);

  useEffect(() => {
    if (coursesQuery.isError) {
      dispatchError({
        icon: PiNetworkSlashDuotone,
        message: "Could not connect to course information service.",
      });
    }
  }, [coursesQuery.isError, dispatchError]);

  return (
    <fieldset className="grid w-full max-w-96 grid-cols-[max-content_1fr] grid-rows-2 items-center gap-x-3 gap-y-8">
      <label className="contents">
        <span className="text-lg font-bold">Subject:</span>
        <Combobox
          defaultValue={defaultValue?.subject}
          options={subjects}
          searchPlaceholder="Search subjects..."
          selectPlaceholder="Select a Subject"
          onChange={handleSubjectChange}
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
          disabled={subject === undefined}
          options={courses}
          onChange={handleCourseChange}
          searchPlaceholder={`Search ${subject} courses...`}
          selectPlaceholder={
            subject
              ? `Select a ${subject} Course`
              : "Awaiting Subject Selection..."
          }
        />
      </label>
    </fieldset>
  );
}

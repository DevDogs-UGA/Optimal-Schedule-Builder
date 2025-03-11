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
      crn: string;
    };

interface Props {
  defaultValue?: InputState;
  onChange?: (course: Course | null) => void;
  onInput?: (value: InputState) => void;
}

export default function SearchByCRN({
  defaultValue,
  onChange,
  onInput,
}: Props) {
  const [crn, setCRN] = useState(defaultValue?.crn);
  const dispatchError = useToast(Error);

  const crnsQuery = useQuery("getAllCRNs", {}, { initialData: [123456] });

  const courseSectionsQuery = useQuery(
    "getCourseEntity",
    { crn: crn ?? "" },
    {
      enabled: crn !== undefined,
      // TODO: update with better dummy data
      initialData: [
        {
          crn: 123456,
          //@ts-expect-error Dummy data
          course: {
            athenaTitle: "Software Development",
            courseNumber: "1302",
            courseId: 1302,
            subject: "CSCI",
          },
        },
      ],
    },
  );

  const crns = useMemo(
    () =>
      crnsQuery.data?.map((value) => ({
        value: String(value),
        content: String(value),
      })),
    [crnsQuery.data],
  );

  const course = useMemo(
    () =>
      courseSectionsQuery.data?.find((course) => course.crn === Number(crn))
        ?.course,
    [courseSectionsQuery.data, crn],
  );

  const handleCrnChange = useCallback(
    (value: string | undefined) => {
      setCRN(value);
      onInput?.(value ? { crn: value } : {});
    },
    [setCRN, onInput],
  );

  useEffect(() => {
    if (
      !(
        defaultValue &&
        "crn" in defaultValue &&
        crnsQuery.data &&
        crnsQuery.data.includes(Number(defaultValue.crn))
      )
    ) {
      return;
    }

    onInput?.({});
    onChange?.(null);
    return;
  }, [
    defaultValue,
    crnsQuery.data,
    courseSectionsQuery.data,
    onChange,
    onInput,
  ]);

  useEffect(() => {
    console.log(course);
    onChange?.(course ?? null);
  }, [course]);

  useEffect(() => {
    if (crnsQuery.isError) {
      dispatchError({
        icon: PiNetworkSlashDuotone,
        message: "Could not connect to course information service.",
      });
    }
  }, [crnsQuery.isError, dispatchError]);

  useEffect(() => {
    if (courseSectionsQuery.isError) {
      dispatchError({
        icon: PiNetworkSlashDuotone,
        message: "Could not connect to course information service.",
      });
    }
  }, [courseSectionsQuery.isError, dispatchError]);

  return (
    <fieldset className="grid w-full max-w-96 grid-cols-[max-content_1fr] grid-rows-[max-content_1fr] items-center gap-x-3 gap-y-3">
      <label className="contents">
        <span className="text-lg font-bold">CRN:</span>
        <Combobox
          defaultValue={defaultValue?.crn}
          items={crns}
          searchPlaceholder="Search CRNs..."
          selectPlaceholder="Select a CRN"
          onChange={handleCrnChange}
        />
      </label>

      {course && (
        <article className="col-start-2 flex flex-col rounded-md border border-dashed border-bulldog-red bg-white px-3 py-2 text-sm text-red-900 shadow-inner">
          <h3 className="font-semibold">
            {course.subject} {course.courseNumber}
          </h3>
          <p className="italic">{course.athenaTitle}</p>
        </article>
      )}
    </fieldset>
  );
}

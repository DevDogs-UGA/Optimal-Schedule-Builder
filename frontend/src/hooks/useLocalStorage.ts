import localStorageSchema from "@/schemas/localStorage";
import {
  Dispatch,
  SetStateAction,
  useCallback,
  useEffect,
  useMemo,
  useReducer,
  useState,
} from "react";
import * as z from "zod";

type LocalStorageSchema = typeof localStorageSchema;

const json = z
  .string()
  .transform((str, ctx) => {
    try {
      return JSON.parse(str);
    } catch (e) {
      ctx.addIssue({ code: "custom", message: "Invalid JSON" });
      return z.NEVER;
    }
  })
  .catch(null);

/**
 * This hook is similar to `useState()`, except that the data
 * it stores is preserved in localStorage (i.e., on the user's
 * device). The type of the data is defined in `@schema/localStorage`;
 * every schema must be associated with a key.
 *
 * @param key The key to store/fetch the data to/from.
 */
export default function useLocalStorage<K extends keyof LocalStorageSchema>(
  key: K,
) {
  if (
    !localStorageSchema[key].isNullable() &&
    !("removeCatch" in localStorageSchema[key])
  ) {
    throw new Error(
      "Local storage schema must end in `.nullable()` or `.catch({...})`.",
    );
  }

  const schema = useMemo(() => json.pipe(localStorageSchema[key]), [key]);
  type Schema = LocalStorageSchema[K];

  const [state, setState] = useReducer(
    (prevState: z.infer<Schema>, action: SetStateAction<z.infer<Schema>>) => {
      const newState =
        typeof action === "function" ? action(prevState) : action;

      window.localStorage.setItem(key, JSON.stringify(newState));

      return newState;
    },
    schema.parse(null),
  );

  const handleStorage = useCallback(
    (e: StorageEvent) => {
      if (e.key !== key) {
        return;
      }

      setState(schema.parse(e.newValue));
    },
    [key, schema],
  );

  useEffect(() => {
    const controller = new AbortController();

    setState(schema.parse(window.localStorage.getItem(key)));
    window.addEventListener("storage", handleStorage);

    return () => controller.abort();
  }, [key, setState, handleStorage]);

  return [state, setState] as const;
}

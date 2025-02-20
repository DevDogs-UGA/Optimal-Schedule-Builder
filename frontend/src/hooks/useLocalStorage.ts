import localStorageSchema from "@/schemas/localStorage";
import { type SetStateAction, useCallback, useEffect, useReducer } from "react";
import type * as z from "zod";

type LocalStorageSchema = typeof localStorageSchema;

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

  const schema = localStorageSchema[key];
  type Schema = z.infer<typeof schema>;

  const [state, setState] = useReducer(
    (prevState: Schema, action: SetStateAction<Schema>) => {
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

      try {
        setState(schema.parse(JSON.parse(e.newValue ?? "")));
      } catch {
        setState(null);
      }
    },
    [key, schema],
  );

  useEffect(() => {
    const controller = new AbortController();

    try {
      setState(
        schema.parse(JSON.parse(window.localStorage.getItem(key) ?? "")),
      );
    } catch {
      setState(null);
    }

    window.addEventListener("storage", handleStorage);

    return () => controller.abort();
  }, [key, schema, setState, handleStorage]);

  return [state, setState] as const;
}

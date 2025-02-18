import { useCallback, useEffect, useState } from "react";
import localStorageSchema from "@/schemas/localStorage";
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
  const schema = localStorageSchema[key];
  type Schema = LocalStorageSchema[K];

  if (!schema.isNullable() && !("removeCatch" in schema)) {
    throw new Error(
      "Local storage schema must end in `.nullable()` or `.catch({...})`.",
    );
  }

  const [state, setState] = useState<z.infer<Schema>>(schema.parse(null));

  const handleStorage = useCallback(
    (e: StorageEvent) => {
      if (e.key !== key) {
        return;
      }

      try {
        setState(schema.parse(e.newValue));
      } catch {
        setState(null as z.infer<LocalStorageSchema[K]>);
      }
    },
    [key, schema],
  );

  /**
   * Dispatches a state update with the provided value while
   * saving the provided value in local storage.
   *
   * @param value The value to store.
   */
  const writeToStorage = useCallback(
    (
      value:
        | z.infer<Schema>
        | ((previousValue: z.infer<Schema>) => z.infer<Schema>),
    ) => {
      if (typeof value !== "function") {
        window.localStorage.setItem(key, JSON.stringify(value));
        return;
      }

      try {
        window.localStorage.setItem(
          key,
          JSON.stringify(value(schema.parse(window.localStorage.getItem(key)))),
        );
      } catch {
        window.localStorage.removeItem(key);
      }
    },
    [key],
  );

  useEffect(() => {
    const controller = new AbortController();

    try {
      setState(schema.parse(window.localStorage.getItem(key)));
    } catch {
      setState(null as z.infer<LocalStorageSchema[K]>);
    }

    window.addEventListener("storage", handleStorage, {
      signal: controller.signal,
    });

    return () => controller.abort();
  }, [key, handleStorage, schema]);

  return [state, writeToStorage] as const;
}

import { useCallback, useEffect, useState } from "react";
import * as localStorageSchema from "@/schemas/localStorage";
import type * as z from "zod";

type LocalStorageSchema = typeof localStorageSchema;

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

  const writeToStorage = useCallback(
    (value: z.infer<Schema>) => {
      window.localStorage.setItem(key, JSON.stringify(value));
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
  }, [key]);

  return [state, writeToStorage] as const;
}
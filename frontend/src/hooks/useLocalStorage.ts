import localStorageSchema from "@/schemas/localStorage";
import { usePathname } from "next/navigation";
import { type Reducer, useCallback, useEffect, useId, useReducer } from "react";
import type * as z from "zod";

type LocalStorageSchema = typeof localStorageSchema;

interface Options {
  /**
   * Sets the "scope" for the locally stored state.
   * - `"global"` State is accessible from anywhere on the same website (default).
   * - `"page"` The current pathname is appended to the key, meaning state is only accesible from the same page.
   * - `"component"` The current pathname and component ID (as determined by `useId()`) are appended to the key, meaning state is only accessible from an individual rendering of a component.
   * @see https://react.dev/reference/react/useId
   */
  scope: "global" | "page" | "component";
}

/**
 * This hook is similar to `useState()`, except that the data
 * it stores is preserved in localStorage (i.e., on the user's
 * device). The type of the data is defined in `@schema/localStorage`;
 * every schema must be associated with a key.
 *
 * @param key The key to store/fetch the data to/from.
 * @param options Micro-mange the hook's behavior.
 */
export default function useLocalStorage<K extends keyof LocalStorageSchema>(
  key: K,
  options?: Options,
) {
  if (
    !localStorageSchema[key].isNullable() &&
    !("removeCatch" in localStorageSchema[key])
  ) {
    throw new Error(
      "Local storage schema must end in `.nullable()` or `.catch({...})`.",
    );
  }

  const pathname = usePathname();
  const id = useId();

  const schema = localStorageSchema[key];
  type OutputSchema = z.output<typeof schema>;
  type InputSchema = z.input<typeof schema>;

  const itemKey =
    key +
    (options?.scope === "component"
      ? `@${id}@${pathname}`
      : options?.scope === "page"
        ? `@${id}`
        : "");

  const [state, setState] = useReducer<
    Reducer<
      OutputSchema,
      InputSchema | ((prevState: OutputSchema) => InputSchema)
    >
  >((prevState, action) => {
    const newState = schema.parse(
      action instanceof Function ? action(prevState) : action,
    );
    console.log({ newState });

    window.localStorage.setItem(itemKey, JSON.stringify(newState));

    return newState;
  }, schema.parse(null));

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

"use client";

import * as Popover from "@radix-ui/react-popover";
import { matchSorter } from "match-sorter";
import {
  useCallback,
  useEffect,
  useId,
  useMemo,
  useReducer,
  useRef,
  useState,
} from "react";
import { PiCaretUpDown, PiCheckBold, PiMagnifyingGlass } from "react-icons/pi";

interface Option {
  /**
   * A unique identifier for this option.
   */
  value: string;
  /**
   * The text content displayed for this option. This text is used to
   * filter the visible options when the user types in the combobox.
   */
  content: string;
}

interface Props {
  /**
   * The value for the initially selected option.
   */
  defaultValue?: string;
  /**
   * Prevents user input if `true`. The Combobox will display as
   * translucent and use the `not-allowed` cursor when hovered.
   */
  disabled?: boolean;
  /**
   * An array of items to render as options.
   */
  options?: Option[];
  /**
   * The name passed to the underlying `<select />` element.
   */
  name?: string;
  /**
   * An event handler which fires when a new item is selected.
   * @param value The `value` of the selected item. This may be `undefined` if the form is reset.
   */
  onChange?: (value: string | undefined) => void;
  /**
   * Indicates whether the current
   */
  required?: boolean;
  /**
   * Disable automatic lexicographic sorting of options
   */
  preserveOrdering?: boolean;
  /**
   * The placeholder text displayed when no item is selected.
   */
  selectPlaceholder?: string;
  /**
   * The placeholder text displayed in the search input when the
   * popover is open.
   */
  searchPlaceholder?: string;
}

/**
 * A wrapper for `<select />` which displays a popover
 * on top of the original element. This popover has a
 * search input, allowing for the user to filter the
 * visible options for selection. Keyboard nagivation
 * and selection of the visible options is supported.
 * Options are NOT rendered in a virtual list; this may
 * result in performance issues for excessively long
 * lists of items.
 */
export default function Combobox({
  defaultValue,
  disabled,
  name,
  onChange,
  options,
  preserveOrdering,
  required,
  searchPlaceholder,
  selectPlaceholder,
}: Props) {
  const id = useId();
  const fieldset = useRef<HTMLFieldSetElement>(null);
  const select = useRef<HTMLSelectElement>(null);
  const [open, setOpen] = useState(false);
  const [filter, setFilter] = useState("");

  const [selected, setSelectedId] = useReducer(
    /**
     * Finds the option in `options` associated with the provided
     * `value`. This will be `undefined` if the value cannot be
     * found.
     * @param _prevState This parameter is ignored.
     * @param action The `value` to search for in `options`.
     * @return The associated `Option`.
     */
    (_prevState: Option | undefined, action: string | undefined) => {
      return options?.find((item) => item.value === action);
    },
    undefined,
  );

  const [highlighted, setHighlighted] = useState(
    (selected ?? options?.[0])?.value,
  );

  const filteredOptions = useMemo(
    () =>
      options
        ? matchSorter(options, filter, {
            ...(preserveOrdering
              ? { baseSort: (a, b) => (a.index < b.index ? -1 : 1) }
              : {}),
            keys: ["content"],
          })
        : [],
    [options, filter, preserveOrdering],
  );

  const handleChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
    setFilter(e.target.value);
  }, []);

  /**
   * Add functionality when the enter or up/down arrow keys are
   * pressed.
   */
  const handleKeydown = useCallback(
    (e: React.KeyboardEvent<HTMLInputElement>) => {
      if (e.key === "Enter") {
        e.preventDefault();
        onChange?.(highlighted);
        setSelectedId(highlighted);
        setOpen(false);
        return;
      }

      if (e.key === "ArrowUp") {
        e.preventDefault();
        setHighlighted(
          (h) =>
            filteredOptions[
              (((filteredOptions.findIndex((item) => item.value === h) - 1) %
                filteredOptions.length) +
                filteredOptions.length) %
                filteredOptions.length
            ]?.value,
        );
        return;
      }

      if (e.key === "ArrowDown") {
        e.preventDefault();
        setHighlighted(
          (h) =>
            filteredOptions[
              (((filteredOptions.findIndex((item) => item.value === h) + 1) %
                filteredOptions.length) +
                filteredOptions.length) %
                filteredOptions.length
            ]?.value,
        );
        return;
      }

      setHighlighted(filteredOptions[0]?.value);
    },
    [highlighted, filteredOptions, onChange],
  );

  /**
   * Resets the Combobox to the initial state.
   */
  const handleReset = useCallback(() => {
    setOpen(false);
    setFilter("");
    setHighlighted(options?.[0]?.value);
    setSelectedId(undefined);
    onChange?.(undefined);
  }, [options, onChange]);

  /**
   * Update the selected option when the default value
   * changes.
   */
  useEffect(() => {
    setSelectedId(defaultValue);
  }, [defaultValue]);

  /**
   * Reset the option filter when the popover is closed.
   */
  useEffect(() => {
    if (!open) {
      setFilter("");
      setHighlighted(selected?.value);
      setOpen(false);
    }
  }, [open, selected]);

  /**
   * When `highlighted` changes, we make sure that the popover
   * is scrolled such that the currently highlighted option is
   * visible in the scrollport.
   */
  useEffect(() => {
    if (highlighted === undefined) {
      return;
    }

    requestAnimationFrame(() => {
      const item: HTMLElement | undefined | null =
        fieldset.current?.querySelector(
          `label:has([name="${name ?? id}"][value="${highlighted}"])`,
        );

      if (!fieldset.current || !item) {
        return;
      }

      const fieldsetTop = fieldset.current.scrollTop;
      const fieldsetBottom = fieldsetTop + fieldset.current.clientHeight;
      const itemTop = item.offsetTop;
      const itemBottom = itemTop + item.clientHeight;

      if (itemBottom > fieldsetBottom) {
        item.scrollIntoView({ block: "end" });
      }

      if (itemTop < fieldsetTop) {
        item.scrollIntoView({ block: "start" });
      }
    });
  }, [highlighted, id, name, open]);

  /**
   * Trigger `handleReset(...)` when the parent form is reset.
   */
  useEffect(() => {
    if (!select.current?.form) {
      return;
    }

    const controller = new AbortController();
    select.current.form.addEventListener("reset", handleReset, controller);
    return () => controller.abort();
  }, [handleReset]);

  return (
    <Popover.Root open={open} onOpenChange={setOpen}>
      <Popover.Trigger
        disabled={disabled === true || options === undefined}
        data-loading={disabled !== true && options === undefined}
        asChild
      >
        <button className="flex w-full cursor-default items-center gap-6 rounded-md border-2 border-limestone bg-white px-3 py-1.5 transition-[box-shadow,border-color] disabled:cursor-not-allowed disabled:opacity-60 data-[state=open]:pointer-events-none data-[loading=true]:cursor-wait [&:not(:disabled):hover]:border-pebble-gray [&:not(:disabled):hover]:shadow-sm">
          <select
            className="peer pointer-events-none hidden flex-1 appearance-none bg-transparent has-[option:checked]:block"
            tabIndex={-1}
            name={name}
            onChange={(e) => setSelectedId(e.target.value)}
            ref={select}
            required={required}
            value={selected?.value}
          >
            {selected && (
              <option value={selected.value}>{selected.content}</option>
            )}
          </select>
          <span className="flex-1 text-left text-neutral-600 peer-has-[option:checked]:hidden">
            {disabled !== true && options === undefined
              ? "Loading..."
              : (defaultValue ?? selectPlaceholder ?? "Select one")}
          </span>
          <PiCaretUpDown />
        </button>
      </Popover.Trigger>

      <Popover.Portal>
        <Popover.Content className="-mt-[var(--radix-popover-trigger-height)] flex max-h-56 w-[var(--radix-popover-trigger-width)] max-w-[calc(100dvw-1rem)] flex-col gap-1 rounded-md border-2 border-pebble-gray bg-white px-1 py-1 shadow-lg">
          <label className="peer flex w-full items-center gap-2 rounded-sm bg-neutral-100 px-2 py-1">
            <PiMagnifyingGlass className="text-neutral-700" />
            <input
              className="flex-1 bg-transparent placeholder:text-neutral-700 focus:outline-none"
              onChange={handleChange}
              onKeyDown={handleKeydown}
              placeholder={searchPlaceholder ?? "Search items..."}
              value={filter}
            />
          </label>

          <fieldset
            className="relative flex max-h-[140px] snap-y snap-mandatory flex-col gap-1 overflow-y-auto"
            ref={fieldset}
          >
            {filteredOptions.map(({ value, content }) => (
              <label
                className="flex snap-start items-center gap-2 rounded-sm py-1 pr-2 has-[:checked]:font-medium data-[active=true]:bg-limestone"
                data-active={highlighted === value}
                key={value}
                onMouseEnter={() => setHighlighted(value)}
              >
                <input
                  className="peer appearance-none"
                  name={name ?? id}
                  checked={selected?.value === value}
                  onChange={() => {
                    onChange?.(value);
                    setHighlighted(value);
                    setOpen(false);
                    setSelectedId(value);
                  }}
                  value={value}
                  type="radio"
                />
                <PiCheckBold className="text-midnight-blue opacity-0 peer-checked:opacity-100" />
                {content}
              </label>
            ))}

            {filteredOptions.length === 0 && (
              <p className="px-2 py-1 text-sm italic text-neutral-700">
                No results.
              </p>
            )}
          </fieldset>
        </Popover.Content>
      </Popover.Portal>
    </Popover.Root>
  );
}

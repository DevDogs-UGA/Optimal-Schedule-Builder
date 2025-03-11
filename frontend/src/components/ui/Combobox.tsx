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

interface Item {
  value: string;
  content: string;
}

interface Props {
  defaultValue?: string;
  disabled?: boolean;
  items?: Item[];
  name?: string;
  onChange?: (value: string | undefined) => void;
  required?: boolean;
  selectPlaceholder?: string;
  searchPlaceholder?: string;
}

export default function Combobox({
  defaultValue,
  disabled,
  items,
  name,
  onChange,
  required,
  searchPlaceholder,
  selectPlaceholder,
}: Props) {
  const id = useId();
  const fieldset = useRef<HTMLFieldSetElement>(null);
  const select = useRef<HTMLSelectElement>(null);
  const [open, setOpen] = useState(false);
  const [filter, setFilter] = useState("");
  const reducer = useCallback(
    (_prevState: Item | undefined, action: string | undefined) => {
      return items?.find((item) => item.value === action);
    },
    [items],
  );

  const [selected, setSelectedId] = useReducer(
    reducer,
    defaultValue,
    reducer.bind(null, undefined),
  );

  const [highlighted, setHighlighted] = useState(
    (selected ?? items?.[0])?.value,
  );

  const filteredItems = useMemo(
    () => (items ? matchSorter(items, filter, { keys: ["content"] }) : []),
    [items, filter],
  );

  const handleChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
    setFilter(e.target.value);
  }, []);

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
            filteredItems[
              (((filteredItems.findIndex((item) => item.value === h) - 1) %
                filteredItems.length) +
                filteredItems.length) %
                filteredItems.length
            ]?.value,
        );
        return;
      }

      if (e.key === "ArrowDown") {
        e.preventDefault();
        setHighlighted(
          (h) =>
            filteredItems[
              (((filteredItems.findIndex((item) => item.value === h) + 1) %
                filteredItems.length) +
                filteredItems.length) %
                filteredItems.length
            ]?.value,
        );
        return;
      }

      setHighlighted(filteredItems[0]?.value);
    },
    [highlighted, filteredItems],
  );

  const handleReset = useCallback(() => {
    setOpen(false);
    setFilter("");
    setHighlighted(items?.[0]?.value);
    setSelectedId(undefined);
    onChange?.(undefined);
  }, [items, onChange]);

  useEffect(() => {
    if (!open) {
      setFilter("");
      setHighlighted(selected?.value);
      setOpen(false);
    }
  }, [open, selected]);

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
        disabled={disabled === true || items === undefined}
        data-loading={disabled !== true && items === undefined}
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
            {disabled !== true && items === undefined
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
            />
          </label>

          <fieldset
            className="relative flex flex-1 snap-y snap-mandatory flex-col gap-1 overflow-y-auto"
            ref={fieldset}
          >
            {filteredItems.map(({ value, content }) => (
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

            {filteredItems.length === 0 && (
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

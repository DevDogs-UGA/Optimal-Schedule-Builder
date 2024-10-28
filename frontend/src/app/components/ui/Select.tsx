"use client";

import * as React from "react";
import { TriangleDownIcon } from "@radix-ui/react-icons";
import * as SelectPrimitive from "@radix-ui/react-select";
import { cn } from "@/../lib/utils";

/**
 * This component is a wrapper around Radix UI's Select component.
 * It provides a styled select dropdown with customizable options.
 *
 * @component Select - The main component that wraps the select functionality.
 */
const Select = SelectPrimitive.Root;

/**
 * A component to group select items together.
 *
 * @component SelectGroup - A component to group select items.
 */
const SelectGroup = SelectPrimitive.Group;

/**
 * A component to display the selected value in the select dropdown.
 *
 * @component SelectValue - A component to display the selected value.
 */
const SelectValue = SelectPrimitive.Value;

/**
 * A component that triggers the select dropdown.
 *
 * @component SelectTrigger - A component that triggers the select dropdown.
 * @param {React.ComponentPropsWithoutRef<typeof SelectPrimitive.Trigger>} props - Use `asChild` prop to render custom trigger.
 */
const SelectTrigger = React.forwardRef<
  React.ElementRef<typeof SelectPrimitive.Trigger>,
  React.ComponentPropsWithoutRef<typeof SelectPrimitive.Trigger>
>(({ className, children, ...props }, ref) => (
  <SelectPrimitive.Trigger
    ref={ref}
    className={cn(
      "flex h-9 w-full min-w-[12rem] items-center justify-between whitespace-nowrap border border-pebble-gray px-3 py-1.5 text-sm ring-offset-background placeholder:text-mud-gray focus:outline-none focus:ring-1 disabled:cursor-not-allowed disabled:opacity-50 [&>span]:truncate",
      className,
    )}
    {...props}
  >
    {children}
    <SelectPrimitive.Icon asChild className="-mr-2">
      <TriangleDownIcon className="size-9 border-l border-pebble-gray pl-1 text-pebble-gray" />
    </SelectPrimitive.Icon>
  </SelectPrimitive.Trigger>
));
SelectTrigger.displayName = SelectPrimitive.Trigger.displayName;

/**
 * A component that displays the content of the select dropdown.
 *
 * @component SelectContent - A component that displays the content of the select dropdown.
 * @param {React.ComponentPropsWithoutRef<typeof SelectPrimitive.Content>} props - To customize the width, use the `className` prop.
 */
const SelectContent = React.forwardRef<
  React.ElementRef<typeof SelectPrimitive.Content>,
  React.ComponentPropsWithoutRef<typeof SelectPrimitive.Content>
>(({ className, children, position = "popper", ...props }, ref) => (
  <SelectPrimitive.Portal>
    <SelectPrimitive.Content
      ref={ref}
      className={cn(
        "relative z-50 max-h-96 w-full overflow-hidden border-x border-b border-pebble-gray bg-white",
        className,
      )}
      position={position}
      {...props}
    >
      <SelectPrimitive.Viewport
        className={cn(
          position === "popper" &&
            "h-[var(--radix-select-trigger-height)] w-full min-w-[var(--radix-select-trigger-width)]",
        )}
      >
        {children}
      </SelectPrimitive.Viewport>
    </SelectPrimitive.Content>
  </SelectPrimitive.Portal>
));
SelectContent.displayName = SelectPrimitive.Content.displayName;

/**
 * A component that represents an item in the select dropdown.
 *
 * @component SelectItem - A component that represents an item in the select dropdown.
 * @param {React.ComponentPropsWithoutRef<typeof SelectPrimitive.Item>} props - Use to customize the item.
 */
const SelectItem = React.forwardRef<
  React.ElementRef<typeof SelectPrimitive.Item>,
  React.ComponentPropsWithoutRef<typeof SelectPrimitive.Item>
>(({ className, children, ...props }, ref) => (
  <SelectPrimitive.Item
    ref={ref}
    className={cn(
      "relative flex w-full min-w-0 cursor-pointer select-none items-center px-1.5 py-0.5 text-sm outline-none focus:bg-barely-pink data-[disabled]:pointer-events-none data-[state=checked]:bg-limestone data-[disabled]:opacity-50 [&>span]:truncate",
      className,
    )}
    {...props}
  >
    <SelectPrimitive.ItemText>{children}</SelectPrimitive.ItemText>
  </SelectPrimitive.Item>
));
SelectItem.displayName = SelectPrimitive.Item.displayName;

export {
  Select,
  SelectGroup,
  SelectValue,
  SelectTrigger,
  SelectContent,
  SelectItem,
};

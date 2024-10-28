import { clsx, type ClassValue } from "clsx";
import { twMerge } from "tailwind-merge";

/**
 * Combines class names and merges Tailwind CSS classes.
 * @param inputs - Class names to combine.
 * @returns A string of combined class names.
 * @example cn('text-white', condition ? 'bg-blue-500' : 'bg-red-500')
 */
export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

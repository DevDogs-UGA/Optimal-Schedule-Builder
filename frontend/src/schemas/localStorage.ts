import * as z from "zod";

/**
 * Every export should be a Zod object whose keys/values are
 * JSON-serializable (i.e., records/arrays of strings, numbers,
 * booleans, and nulls). Further, each must have either a default
 * or be nullable.
 */

export const test = z
  .object({
    foo: z.string(),
  })
  .catch({
    foo: "caught",
  });

export const test2 = z.object({}).nullable();

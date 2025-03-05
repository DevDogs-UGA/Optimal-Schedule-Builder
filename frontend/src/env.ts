import { createEnv } from "@t3-oss/env-nextjs";
// import * as z from 'zod';

/**
 * TODO: when we can actually connect to the course
 * information service, we'll store the URL in an
 * environment variable, and it will be defined here.
 */
export const env = createEnv({
  client: {
    // NEXT_PUBLIC_COURSE_INFORMATION_SERVICE: z.string().url(),
  },
  runtimeEnv: {
    // NEXT_PUBLIC_COURSE_INFORMATION_SERVICE: process.env.NEXT_PUBLIC_COURSE_INFORMATION_SERVICE
  },
});

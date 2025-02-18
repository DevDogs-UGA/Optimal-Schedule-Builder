"use client";

import serverQuerySchema from "@/schemas/serverQueries";
import {
  QueryClient as ReactQueryClient,
  QueryClientProvider as ReactQueryClientProvider,
  type UseQueryOptions,
  useQuery as useReactQuery,
} from "@tanstack/react-query";
import type * as z from "zod";

interface Query {
  route: string;
  params: z.AnyZodObject | z.ZodUnion<[z.AnyZodObject, ...z.AnyZodObject[]]>;
  result: z.ZodTypeAny;
}

function createUseQuery<
  Queries extends string,
  QueryRecord extends Record<Queries, Query>,
>(queries: QueryRecord) {
  /**
   * This hook is a wrapper for react-query which provides typed
   * parameters and responses for the course information backend
   * service. These types are defined in `@schema/serverQueries`.
   *
   * @param queryKey The query to call on the backend.
   * @param params The parameters associated with the provided query.
   * @param options Options passed through to the underlying react-query hook.
   *
   * @see https://tanstack.com/query/latest
   */
  return function useQuery<Q extends keyof QueryRecord>(
    queryKey: Q,
    params: z.input<QueryRecord[Q]["params"]>,
    options: Omit<
      UseQueryOptions<z.output<QueryRecord[Q]["result"]>>,
      "queryKey" | "queryFn"
    > = {},
  ) {
    const query = queries[queryKey];

    return useReactQuery<z.output<QueryRecord[Q]["result"]>>({
      ...options,
      queryKey: ["getCoursesByTerm", params], // TODO: params is an object and queryKey will has its reference, not its value(s).
      queryFn: () =>
        query.params
          .parseAsync(params)
          .then((obj) =>
            fetch(
              new URL(
                `${query.route}?${new URLSearchParams(obj)}`,
                /**
                 * TODO: when we can actually connect to the course
                 * information service, we'll store the URL in an
                 * environment variable, and it will be accessible here.
                 */
                // env.NEXT_PUBLIC_COURSE_INFORMATION_SERVICE
              ),
            ),
          )
          .then((res) => res.json())
          .then((data) => query.result.parseAsync(data)),
    });
  };
}

const useQuery = createUseQuery(serverQuerySchema);
const queryClient = new ReactQueryClient();

export default useQuery;

export function QueryClientProvider({ children }: React.PropsWithChildren) {
  return (
    <ReactQueryClientProvider client={queryClient}>
      {children}
    </ReactQueryClientProvider>
  );
}

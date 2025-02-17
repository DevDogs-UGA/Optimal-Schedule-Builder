import type { RouteMap } from "@/schemas";
import * as serverQuerySchemas from "@/schemas/serverQueries";
import type * as z from "zod";
import { useQuery as useReactQuery } from "@tanstack/react-query";
import { useMemo } from "react";

function createUseQuery<
  Services extends string,
  Queries extends string,
  Map extends Record<Services, RouteMap<Queries>>,
>(map: Map) {
  return function useQuery<S extends Services, Q extends Queries>(
    serviceKey: S,
    queryKey: Q,
    params: z.input<Map[S]["queries"][Q]["params"]>,
    options?: { enabled: boolean },
  ) {
    const { service, query } = useMemo(
      () => ({
        service: map[serviceKey],
        query: map[serviceKey].queries[queryKey],
      }),
      [serviceKey, queryKey],
    );

    return useReactQuery({
      enabled: options?.enabled ?? true,
      queryKey: ["getCoursesByTerm", params],
      queryFn: () =>
        query.params
          .parseAsync(params)
          .then((obj) =>
            fetch(
              new URL(
                `${query.route}?${new URLSearchParams(obj)}`,
                service.endpoint,
              ),
            ),
          )
          .then((res) => res.json())
          .then((data) => query.result.parseAsync(data)),
    });
  };
}

const useQuery = createUseQuery(serverQuerySchemas);
export default useQuery;

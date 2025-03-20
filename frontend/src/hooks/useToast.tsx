"use client";

import * as Toast from "@radix-ui/react-toast";
import { createContext, useCallback, useContext, useState } from "react";
import { AnimatePresence, motion } from "motion/react";

type RenderedToast = [
  id: string,
  duration: number | undefined,
  children: JSX.Element,
];

const context = createContext<((toast: RenderedToast) => void) | null>(null);

// Toast Context Provider and Viewport
export function ToastProvider({ children }: React.PropsWithChildren) {
  const [toasts, setToasts] = useState<RenderedToast[]>([]);

  const appendToast = useCallback(
    (toast: RenderedToast) => {
      setToasts((toasts) => [...toasts, toast]);
    },
    [setToasts],
  );

  const createCleanup = useCallback(
    (target: string) => {
      return (open: boolean) => {
        if (!open) {
          setToasts((toasts) => toasts.filter(([id]) => id != target));
        }
      };
    },
    [setToasts],
  );

  return (
    <Toast.Provider swipeThreshold={150} swipeDirection="right">
      <context.Provider value={appendToast}>{children}</context.Provider>

      <AnimatePresence mode="popLayout">
        {toasts.map(([id, duration, children]) => (
          <Toast.Root
            asChild
            duration={duration}
            forceMount
            key={id}
            onOpenChange={createCleanup(id)}
          >
            <motion.li
              animate={{ x: 0 }}
              drag="x"
              dragConstraints={{ left: 0, right: 0 }}
              dragTransition={{ bounceStiffness: 500, bounceDamping: 15 }}
              dragElastic={{ left: 0, right: 0.5 }}
              exit={{
                opacity: 0,
                zIndex: -1,
                transition: {
                  opacity: {
                    duration: 0.2,
                  },
                },
                x: 24 + 512,
              }}
              initial={{
                x: 24 + 512,
              }}
              layout
              transition={{
                type: "spring",
                mass: 1,
                damping: 30,
                stiffness: 200,
              }}
              whileDrag={{ cursor: "grabbing" }}
            >
              {children}
            </motion.li>
          </Toast.Root>
        ))}
      </AnimatePresence>

      <Toast.Viewport className="fixed bottom-0 right-0 z-[2147483647] m-0 flex w-[32rem] max-w-[100vw] list-none flex-col justify-end gap-2.5 p-6 outline-none" />
    </Toast.Provider>
  );
}

/**
 * Access a handler for publishing toast notification to the screen.
 * @param Component The component to render as a toast notification
 * @returns A function which publishes a toast notification.
 */
export default function useToast<P extends object>(Component: React.FC<P>) {
  const appendToast = useContext(context);

  if (appendToast === null) {
    throw new Error(
      "`useToast(...)` can only be called in a component which is a child of a `<ToastProvider />`.",
    );
  }

  /**
   * Publishes a toast to the screen.
   * @param props Props passed to the toast component
   * @param duration The amount of time to display the toast before automatically disappearing (Optional; defaults to 5000)
   */
  const publish = useCallback(
    (props: P, duration?: number) => {
      const renderedComponent = <Component {...props} />;
      appendToast([crypto.randomUUID(), duration, renderedComponent]);
    },
    [appendToast, Component],
  );

  return publish;
}

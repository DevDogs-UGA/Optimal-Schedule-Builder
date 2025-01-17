"use client";

import * as Accordion from "@radix-ui/react-accordion";
import * as Dialog from "@radix-ui/react-dialog";
import * as Popover from "@radix-ui/react-popover";
import {
  type PropsWithChildren,
  startTransition,
  useCallback,
  useEffect,
  useReducer,
  useState,
} from "react";
import { PiBellBold, PiBellFill, PiTrayDuotone, PiXBold } from "react-icons/pi";

const MobileDisplay = {
  Root: Dialog.Root,
  Trigger: Dialog.Trigger,
  Content: ({ children }: PropsWithChildren) => {
    return (
      <Dialog.Portal>
        <Dialog.Overlay className="data-[state=open]:animate-fadeInOverlay fixed left-0 top-0 z-[999] flex h-[100lvh] w-[100lvw] flex-col justify-end bg-black/40">
          <Dialog.Content
            aria-describedby={undefined}
            className="shadow-x flex h-[60dvh] w-screen flex-col overflow-hidden rounded-t-xl border-2 border-b-0 border-bulldog-red/60 bg-zinc-100 focus:outline-none data-[state=open]:animate-slideUp"
          >
            <Dialog.Title className="sr-only">Notifications</Dialog.Title>
            {children}
          </Dialog.Content>
        </Dialog.Overlay>
      </Dialog.Portal>
    );
  },
};

const DesktopDisplay = {
  Root: Popover.Root,
  Trigger: Popover.Trigger,
  Content: ({ children }: PropsWithChildren) => {
    return (
      <Popover.Portal>
        <Popover.Content
          align="end"
          sideOffset={8}
          alignOffset={-8}
          className="data-[state=open]:animate-slideUpAndFadeIn relative z-[9999] flex h-96 w-80 flex-col overflow-hidden rounded-md border-2 border-bulldog-red/60 bg-zinc-100 shadow-xl"
        >
          {children}
        </Popover.Content>
      </Popover.Portal>
    );
  },
};

interface Notification {
  id: string;
  content: React.ReactNode;
}

interface Optimistic extends Notification {
  closed?: boolean;
}

interface Props {
  initialItems: Readonly<Notification[]>;
  /**
   * React server action for closing a specific notification (to be integrated with backend).
   * @param id ID of the notification.
   * @returns A promise, resolving to "success" flag determining whether the necessary database update was successful.
   */
  closeAction: (id: string) => Promise<{ success: boolean }>;
}

export default function Notifications({ initialItems, closeAction }: Props) {
  const [items, setItems] = useState<Readonly<Optimistic[]>>(initialItems);
  const [{ Root, Trigger, Content }, dispatch] = useReducer(
    (_: unknown, event: MediaQueryList | MediaQueryListEvent) =>
      event.matches ? DesktopDisplay : MobileDisplay,
    MobileDisplay,
  );

  const close = useCallback(
    (id: string) => {
      startTransition(() => {
        // Server Action
        closeAction(id)
          .then((result) => {
            if (result.success) {
              setItems((items) => items.filter((item) => item.id !== id));
            }
          })
          .catch(console.error);
        // Optimistic Update
        setItems((items) =>
          items.map((item) => ({
            ...item,
            closed: item.id === id || item.closed,
          })),
        );
      });
    },
    [closeAction],
  );

  useEffect(() => {
    if (!("window" in globalThis) || !("matchMedia" in window)) {
      return;
    }

    const query = window.matchMedia("(min-width: 640px)");
    query.addEventListener("change", dispatch);
    dispatch(query);

    return () => query.removeEventListener("change", dispatch);
  }, []);

  return (
    <Root>
      <Trigger className="group relative cursor-default rounded-full border-2 border-bulldog-red/30 p-1 text-lg text-black transition-colors hover:border-bulldog-red/60 hover:bg-glory-glory-red/10 data-[state=open]:border-bulldog-red/60 data-[state=open]:bg-glory-glory-red/10 sm:p-1.5 sm:text-xl">
        <PiBellFill className="hidden sm:block" />
        <PiBellBold className="sm:hidden" />
        {items.length > 0 && (
          <span className="absolute -right-px -top-px size-2 rounded-full bg-bulldog-red transition-transform group-data-[state=open]:scale-0 sm:-right-0.5 sm:-top-0.5 sm:size-2.5" />
        )}
      </Trigger>
      <Content>
        {items.length === 0 || items.every((item) => item.closed === true) ? (
          <p className="flex flex-1 flex-col items-center justify-center gap-3 text-mud-gray">
            <PiTrayDuotone className="text-3xl" />
            <span className="text-xs font-semibold">No new notifications</span>
          </p>
        ) : (
          <Accordion.Root
            className="w-full flex-col bg-white text-sm last:shadow-sm"
            value={items.filter((item) => !item.closed).map((item) => item.id)}
            type="multiple"
          >
            {items.map(({ id, content }) => (
              <Accordion.Item key={id} value={id}>
                <Accordion.Content className="data-[state=closed]:animate-collapse -mt-0.5 overflow-hidden border-b-2 border-bulldog-red/30">
                  <div className="flex items-start gap-2 px-4 pb-4 pt-[1.125rem]">
                    <div className="flex-1">{content}</div>
                    <button
                      className="rounded-full p-0.5 text-mud-gray transition-colors hover:bg-dusty-pink hover:text-black"
                      onClick={() => close(id)}
                    >
                      <PiXBold />
                    </button>
                  </div>
                </Accordion.Content>
              </Accordion.Item>
            ))}
          </Accordion.Root>
        )}
      </Content>
    </Root>
  );
}

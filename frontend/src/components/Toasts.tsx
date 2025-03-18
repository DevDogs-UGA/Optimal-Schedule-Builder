import * as Toast from "@radix-ui/react-toast";
import { type IconType } from "react-icons";
import { PiCheckCircle, PiInfo, PiWarning, PiXCircle } from "react-icons/pi";

interface Props {
  icon?: IconType;
  message: string;
}

export function Error({ icon: Icon = PiXCircle, message }: Props) {
  return (
    <div className="flex w-full items-center justify-center gap-5 rounded-lg border-2 border-red-800 bg-red-200 px-6 py-4 text-black shadow-lg">
      <Icon className="text-3xl text-red-800" />
      <Toast.Description className="flex-1 text-lg leading-tight">
        {message}
      </Toast.Description>
    </div>
  );
}

export function Warning({ icon: Icon = PiWarning, message }: Props) {
  return (
    <div className="flex w-full items-center justify-center gap-5 rounded-lg border-2 border-amber-800 bg-amber-200 px-6 py-4 text-black shadow-lg">
      <Icon className="text-3xl text-amber-800" />
      <Toast.Description className="flex-1 text-lg leading-tight">
        {message}
      </Toast.Description>
    </div>
  );
}

export function Info({ icon: Icon = PiInfo, message }: Props) {
  return (
    <div className="flex w-full items-center justify-center gap-5 rounded-lg border-2 border-sky-800 bg-sky-200 px-6 py-4 text-black shadow-lg">
      <Icon className="text-3xl text-sky-800" />
      <Toast.Description className="flex-1 text-lg leading-tight">
        {message}
      </Toast.Description>
    </div>
  );
}

export function Success({ icon: Icon = PiCheckCircle, message }: Props) {
  return (
    <div className="flex w-full items-center justify-center gap-5 rounded-lg border-2 border-emerald-800 bg-emerald-200 px-6 py-4 text-black shadow-lg">
      <Icon className="text-3xl text-emerald-800" />
      <Toast.Description className="flex-1 text-lg leading-tight">
        {message}
      </Toast.Description>
    </div>
  );
}

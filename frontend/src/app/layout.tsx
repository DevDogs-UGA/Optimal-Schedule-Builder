import { Footer } from "@/components/Footer";
import { QueryClientProvider } from "@/hooks/useQuery";
import { ToastProvider } from "@/hooks/useToast";
import "@/styles/globals.css";
import { type Metadata } from "next";
import { Heebo } from "next/font/google";

const heebo = Heebo({ subsets: ["latin"], variable: "--font-sans" });

export const metadata: Metadata = {
  title: "UGA Optimal Schedule Builder",
  description:
    "Optimal Schedule Builder web application for UGA students to generate and design optimal academic schedules",
  icons: [{ rel: "icon", url: "/favicon.ico" }],
};

export default function RootLayout({
  children,
}: Readonly<{ children: React.ReactNode }>) {
  return (
    <html lang="en" className={heebo.variable}>
      <body className="flex min-h-screen flex-col">
        <QueryClientProvider>
          <ToastProvider>
            <main className="relative flex flex-1 flex-col">{children}</main>
            <Footer />
          </ToastProvider>
        </QueryClientProvider>
      </body>
    </html>
  );
}

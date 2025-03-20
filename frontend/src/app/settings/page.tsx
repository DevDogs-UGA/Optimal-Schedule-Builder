import { Navbar } from "@/components/Navbar";

export default function PastCredits() {
  return (
    <div className="min-h-screen">
      <Navbar />
      <div className="flex h-[calc(100vh-5rem)] flex-grow flex-col items-center justify-center text-center">
        <div className="space-y-4">
          <h1 className="text-4xl font-bold md:text-6xl">Settings Page</h1>
        </div>
      </div>
    </div>
  );
}

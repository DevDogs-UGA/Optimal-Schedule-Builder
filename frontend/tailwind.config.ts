import type { Config } from "tailwindcss";

export default {
  content: ["./src/**/*.tsx", "./src/**/*.ts"],
  theme: {
    extend: {
      colors: {
        background: "var(--background)",
        foreground: "var(--foreground)",
        "bulldog-red": "#a02c3c",
        "dusty-pink": "#f0d4dc",
      },
    },
  },
  plugins: [],
} satisfies Config;

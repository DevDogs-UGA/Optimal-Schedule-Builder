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
        "barely-pink": "#fff7f9",
      },
    },
  },
  plugins: [],
} satisfies Config;

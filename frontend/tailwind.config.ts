import type { Config } from "tailwindcss";

export default {
  content: ["./src/**/*.tsx", "./src/**/*.ts"],
  theme: {
    extend: {
      colors: {
        background: "hsl(var(--background))",
        "bulldog-red": "hsl(var(--bulldog-red))",
        "dusty-pink": "hsl(var(--dusty-pink))",
        "barely-pink": "hsl(var(--barely-pink))",
        "dev-dog-blue": "hsl(var(--dev-dog-blue))",
        "nearly-black": "hsl(var(--nearly-black))",
        "midnight-blue": "hsl(var(--midnight-blue))",
        "grout-gray": "hsl(var(--grout-gray))",
        "glory-glory-red": "hsl(var(--glory-glory-red))",
        "mud-gray": "hsl(var(--mud-gray))",
        "pebble-gray": "hsl(var(--pebble-gray))",
        limestone: "hsl(var(--limestone))",
        "baby-blue": "hsl(var(--baby-blue))",
        "lake-herrick": "hsl(var(--lake-herrick))",
        gold: "hsl(var(--gold))",
        peach: "hsl(var(--peach))",
        wave: "hsl(var(--wave))",
      },
    },
  },
  
  plugins: [],
} satisfies Config;

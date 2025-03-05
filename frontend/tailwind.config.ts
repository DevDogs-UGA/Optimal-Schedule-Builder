import type { Config } from "tailwindcss";
import defaultTheme from "tailwindcss/defaultTheme";

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
      keyframes: {
        fadeInOverlay: {
          from: { backgroundColor: "transparent" },
          to: { backgroundColor: "hsla(0 0% 0% / .4)" },
        },
        collapse: {
          from: { height: "var(--radix-accordion-content-height)" },
          to: { height: "0px" },
        },
        slideUpAndFadeIn: {
          from: { opacity: "0", transform: "translateY(2px)" },
          to: { opacity: "1", transform: "translateY(0)" },
        },
        slideUp: {
          from: { opacity: "0", transform: "translateY(100%)" },
          to: { opacity: "1", transform: "translateY(0px)" },
        },
      },
      animation: {
        fadeInOverlay: "fadeInOverlay 300ms cubic-bezier(0.87, 0, 0.13, 1)",
        collapse: "collapse 300ms cubic-bezier(0.87, 0, 0.13, 1)",
        slideUpAndFadeIn:
          "slideUpAndFadeIn 300ms cubic-bezier(0.87, 0, 0.13, 1)",
        slideUp: "slideUp 200ms cubic-bezier(0.87, 0, 0.13, 1)",
      },
      fontFamily: {
        ...defaultTheme.fontFamily,
        sans: ["var(--font-sans)", ...defaultTheme.fontFamily.sans],
      },
    },
  },
  plugins: [],
} satisfies Config;

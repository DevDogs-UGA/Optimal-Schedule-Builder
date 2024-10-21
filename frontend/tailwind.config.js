/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      backgroundImage: {
        "gradient-radial": "radial-gradient(var(--tw-gradient-stops))",
        "gradient-conic":
          "conic-gradient(from 180deg at 50% 50%, var(--tw-gradient-stops))",
      },
      colors: {
        BulldogRed: "#ba0c2f",
        DevDogBlue: "#33334d",
        BarelyPink: "#fff7f9",
        NearlyBlack: "#23221f",
        MidnightBlue: "#222233",
        GloryGloryRed: "#e4002b",
        PureWhite: "#ffffff",
        SolidBlack: "#000000",
        MudGray: "#64575a",
        GroutGray: "#8a7d7d",
        PebbleGray: "#aa999d",
        Limestone: "#d4ccc8",
        DustyPink: "#f2d9df",
        BabyBlue: "#d9ecec",
        LakeHerrick: "#00a3ad",
        Gold: "#f9bd9b",
        Peach: "#e37c7c",
        Wave: "#71ccd2",
      },
    },
  },
  plugins: [],
};

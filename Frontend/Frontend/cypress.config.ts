import { defineConfig } from "cypress";

export default defineConfig({
  e2e: {
    baseUrl: "http://localhost:4200", // Az Angular alkalmazásod alapértelmezett címe
    supportFile: "cypress/support/e2e.ts", // Alapértelmezett támogatói fájl
    specPattern: "cypress/e2e/**/*.cy.ts", // Tesztfájlok elérési útja
    video: false, // Tesztek videófelvételének tiltása
  },

  // Alapértelmezett szélesség
  viewportWidth: 1280,

  // Alapértelmezett magasság
  viewportHeight: 720,

  retries: {
    runMode: 1, // Tesztek újrafuttatásának száma futtatás közben
    openMode: 0, // Tesztek újrafuttatása fejlesztői módban
  },

  component: {
    devServer: {
      framework: "angular",
      bundler: "webpack",
    },
    specPattern: "**/*.cy.ts",
  },
});
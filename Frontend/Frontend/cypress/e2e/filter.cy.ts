describe('FilterComponent Tests', () => {
    beforeEach(() => {
      // Az alkalmazás futtatásának URL-je
      cy.visit('http://localhost:4200'); // Győződj meg róla, hogy a megfelelő URL-t használod
    });
  
    it('should render the form with default values', () => {
      cy.get('form').should('exist'); // Ellenőrzi, hogy az űrlap létezik
      cy.get('input[type="text"]').should('have.value', ''); // Ellenőrzi, hogy a szövegmező üres
      cy.get('input[type="radio"]').should('have.length', 3); // Három rádiógomb van
      cy.get('input[type="radio"]:checked').should('have.value', '1'); // Alapértelmezett kiválasztás
    });
  
    it('should update the selectedForecast on radio button click', () => {
      cy.get('input[type="radio"][value="7"]').check({ force: true }); // Rádiógomb kiválasztása
      cy.get('input[type="radio"]:checked').should('have.value', '7'); // Ellenőrzés
  
      cy.get('input[type="radio"][value="14"]').check({ force: true });
      cy.get('input[type="radio"]:checked').should('have.value', '14');
    });
  
    it('should emit the city and forecast values on form submit', () => {
        // Szimuláljuk egy város beírását
        cy.get('input[type="text"]').type('Budapest');
      
        // Ellenőrizzük, hogy az input mező tartalma frissült
        cy.get('input[type="text"]').should('have.value', 'Budapest');
      
        // Radio kiválasztása
        cy.get('input[type="radio"][value="7"]').check({ force: true });
      
        // Ellenőrizzük, hogy a megfelelő radio van kiválasztva
        cy.get('input[type="radio"]:checked').should('have.value', '7');
      
        // Form elküldése
        cy.get('form').submit();
      
        // Helyettesítsük az Output tesztelést azzal, hogy a DOM-on látható változásokat ellenőrizzük
        // Például ellenőrizzük, hogy a "present" komponens frissül-e a megfelelő város és előrejelzés értékekkel
        cy.contains('Budapest').should('exist'); // Ellenőrizhető, hogy a városnév megjelenik
      });
      
  });
  
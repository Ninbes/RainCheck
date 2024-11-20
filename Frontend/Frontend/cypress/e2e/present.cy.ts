describe('PresentComponent', () => {
    beforeEach(() => {
      // Mockoljuk az API hívást, hogy csak egy napot tartalmazzon a 'days' tömb
      cy.intercept('GET', '/api/forecast', {
        statusCode: 200,
        body: {
          resolvedAddress: 'Budapest',
          days: [{ datetime: new Date().toLocaleDateString() }], // Az aktuális dátumot használjuk
        },
      }).as('getForecast');
  
      // Látogassunk el az oldalra, hogy az alkalmazás betöltődjön
      cy.visit('/');
    });
  
    it('should display the location and the correct forecast date', () => {
      // Ellenőrizzük, hogy csak egy dátumot lássunk
      cy.get('.date').should('have.length', 1); // Csak egy dátumot várunk
  
      const currentDate = new Date().toISOString().split('T')[0]; // 'yyyy-MM-dd' formátumban

      // Ellenőrizzük, hogy a dátum helyesen jelenik meg és megegyezik a mai dátummal
      cy.get('.date').eq(0).should('contain', currentDate);

      // Ellenőrizzük, hogy a helyes város is megjelenik
      cy.get('.location').should('contain', 'Budapest');
    });
  
    it('should show the correct location', () => {
      // Ellenőrizzük, hogy a helyes város neve szerepel a .location elemben
      cy.get('.location').should('contain', 'Budapest');
    });
  });
  
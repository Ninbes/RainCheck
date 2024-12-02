describe('PresentComponent', () => {
    beforeEach(() => {
      cy.intercept('GET', '/api/forecast', {
        statusCode: 200,
        body: {
          resolvedAddress: 'Budapest',
          days: [{ datetime: new Date().toLocaleDateString() }],
        },
      }).as('getForecast');
      cy.visit('/');
    });
    it('should display the location and the correct forecast date', () => {
      cy.get('.date').should('have.length', 1);
      const currentDate = new Date().toISOString().split('T')[0];
      cy.get('.date').eq(0).should('contain', currentDate);
      cy.get('.location').should('contain', 'Budapest');
    });
    it('should show the correct location', () => {
      cy.get('.location').should('contain', 'Budapest');
    });
    it('should display error message if no forecast data is available', () => {
      cy.intercept('GET', '/api/forecast*', {
        statusCode: 400,
      }).as('getNoForecast');
      cy.visit('/');
      cy.get('app-filter input[type="text"]').type('kl');
        cy.get('app-filter input[type="text"]').should('have.value', 'kl');
        cy.get('app-filter form').submit();
      cy.get('.error').should('contain', 'Unknown city.');
    });
  });
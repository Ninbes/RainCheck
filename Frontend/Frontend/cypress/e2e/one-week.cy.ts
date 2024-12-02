describe('OneWeekComponent', () => {
    beforeEach(() => {
      cy.intercept('GET', '/api/one-week*', {
        statusCode: 200,
      }).as('getForecast');
      cy.visit('/');
      cy.get('app-filter input[type="radio"][value="7"]').check({ force: true });
      cy.get('app-filter input[type="radio"]:checked').should('have.value', '7');
      });
  


    it('should display the title', () => {
      cy.get('.title').should('contain', 'One-week forecast:');
    });
  
    it('should display 7 forecast cards', () => {
      cy.get('.card').should('have.length', 7);
    });
  
    it('should display valid time format in card headers', () => {
      cy.get('.card-header').each((header) => {
        const timeFormatRegex = /^[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]$/;
        expect(header.text().trim()).to.match(timeFormatRegex);
      });
    });
    it('should display maximum temperature with °C unit', () => {
      cy.get('.temp').eq(0).invoke('text').then((tempText) => {
        const trimmedText = tempText.trim();
        expect(trimmedText).to.match(/^[+-]?\d+(\.\d+)? °C$/);
      });
    });
    
    it('should display minimum temperature with °C unit', () => {
      cy.get('.temp').eq(1).invoke('text').then((tempText) => {
        const trimmedText = tempText.trim();
        expect(trimmedText).to.match(/^[+-]?\d+(\.\d+)? °C$/);
      });
    });
   it('should display precipitation amount with "cm" unit', () => {
      cy.get('.card').each((card) => {
        cy.wrap(card)
          .find('.card-body')
          .find('.precip')
          .eq(0)
          .invoke('text')
          .should('match', /^[0-9]+(\.[0-9]+)? cm$/);
      });
    });
    it('should display precipitation type or "Not expected" text', () => {
      cy.get('.card').each((card) => {
        cy.wrap(card)
          .find('.card-body')
          .find('.precip')
          .eq(1)
          .should('satisfy', (text: string) => {
            return text === 'Not expected' || text.length > 0;
          });
      });
    });
    it('should toggle details when button is clicked', () => {
      cy.viewport(500, 800);
      cy.get('.details-button').eq(0).click();
      cy.get('.details').eq(0).should('not.have.class', 'hidden');
      cy.get('.details-button').eq(0).click();
      cy.get('.details').eq(0).should('have.class', 'hidden');
    });
    it('should display error message if no forecast data is available', () => {
      cy.intercept('GET', '/api/forecast*', {
        statusCode: 404,
        body: { message: 'No forecast available' },
      }).as('getNoForecast');
      cy.visit('/');
      cy.get('app-filter input[type="text"]').type('kl');
        cy.get('app-filter input[type="text"]').should('have.value', 'kl');
        cy.get('app-filter input[type="radio"][value="7"]').check({ force: true });
        cy.get('app-filter input[type="radio"]:checked').should('have.value', '7');
        cy.get('app-filter form').submit();
      cy.get('.error').should('contain', 'No details available.');
    });
  });

 
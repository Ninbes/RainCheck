describe('TwoWeeksComponent', () => {
    beforeEach(() => {
      cy.intercept('GET', '/api/one-week*', {
        statusCode: 200,
      }).as('getForecast');
      cy.visit('/');
      cy.get('input[type="radio"][value="14"]').check({ force: true }); // Rádiógomb kiválasztása
      cy.get('input[type="radio"]:checked').should('have.value', '14');
      });
    it('should display the title', () => {
      cy.get('.title').should('contain', 'Two-weeks forecast:');
    });
    it('should display three forecast cards for the mocked data', () => {
      cy.get('.card').should('have.length', 14);
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
  });
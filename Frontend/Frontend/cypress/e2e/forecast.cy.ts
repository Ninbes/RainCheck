describe('ForecastComponent', () => {
  beforeEach(() => {
    cy.intercept('GET', '/api/forecast*', {
      statusCode: 200,
      body: {
        days: [
          {
            hours: [
              { datetime: '00:00', temp: 15, precip: 0.1, preciptype: null },
              { datetime: '01:00', temp: 14, precip: 0.2, preciptype: 'rain' },
              { datetime: '02:00', temp: 13, precip: 0, preciptype: null },
            ],
          },
        ],
      },
    }).as('getForecast');
    cy.visit('/');
    });

  it('should display the title', () => {
    cy.get('.title').should('contain', 'One-day forecast:');
  });

  it('should display 24 forecast cards', () => {
    cy.get('.card').should('have.length', 24);
  });

  it('should display valid time format in card headers', () => {
    cy.get('.card-header').each((header) => {
      const timeFormatRegex = /^[0-2][0-9]:[0-5][0-9]$/;
      expect(header.text().trim()).to.match(timeFormatRegex);
    });
  });

  it('should display temperature with °C unit', () => {
    cy.get('.temp').each((tempElement) => {
      const tempText = tempElement.text().trim();
      expect(tempText).to.match(/^[+-]?\d+(\.\d+)? °C$/);
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
      cy.get('app-filter form').submit();
    cy.get('.error').should('contain', 'No details available.');
  });
});


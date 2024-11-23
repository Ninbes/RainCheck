describe('AppComponent E2E Tests', () => {
    beforeEach(() => {
      cy.visit('/');
    });
  
    it('should display the header and video components on load', () => {
      cy.get('app-header').should('exist');
      cy.get('app-video').should('exist');
    });
  
    it('should load the default city and selected value on start', () => {
      cy.get('app-filter').should('exist');
      cy.get('app-present').should('exist');
      cy.get('app-forecast').should('exist');
    });
  
    it('should switch to one-week forecast when selectedValue is "7"', () => {
      cy.get('input[type="radio"][value="7"]').check({ force: true });
      cy.get('app-one-week').should('exist');
      cy.get('app-forecast').should('not.exist');
      cy.get('app-two-weeks').should('not.exist');
    });
  
    it('should switch to two-weeks forecast when selectedValue is "14"', () => {
      cy.get('input[type="radio"][value="14"]').check({ force: true });
      cy.get('app-two-weeks').should('exist');
      cy.get('app-forecast').should('not.exist');
      cy.get('app-one-week').should('not.exist');
    });
  
    it('should hide the scroll arrow after scrolling down', () => {
      cy.scrollTo(0, 100);
      cy.get('.scroll-arrow').should('not.be.visible');
    });

    it('should scroll to target-section when scroll arrow is clicked', () => {
        cy.get('.scroll-arrow').click({ force: true });
        cy.get('#target-section').should('be.visible');
      });
  });
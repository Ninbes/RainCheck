describe('HeaderComponent Tests', () => {
    beforeEach(() => {
      cy.visit('/');
    });
  
    it('Should display the logo and the title', () => {
      cy.get('header').should('exist');
      cy.get('.logo img').should('be.visible');
      cy.get('.logo h2').should('contain.text', 'RainCheck');
    });
  
    it('Should apply "scrolled" class on scroll', () => {
      cy.scrollTo(0, 100);
      cy.get('header').should('have.class', 'scrolled');
    });
  
    it('Should hide the title on smaller screens', () => {
      cy.viewport(749, 800);
      cy.get('.logo h2').should('not.be.visible');
    });
  
    it('Should keep the title visible on larger screens', () => {
      cy.viewport(1024, 800);
      cy.get('.logo h2').should('be.visible');
    });
  });
  
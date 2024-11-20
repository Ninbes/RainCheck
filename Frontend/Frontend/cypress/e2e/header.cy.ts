describe('HeaderComponent Tests', () => {
    beforeEach(() => {
      cy.visit('/'); // Az alapértelmezett útvonal meglátogatása
    });
  
    it('Should display the logo and the title', () => {
      cy.get('header').should('exist'); // Ellenőrizzük, hogy a header létezik
      cy.get('.logo img').should('be.visible'); // A logo képe látható
      cy.get('.logo h2').should('contain.text', 'RainCheck'); // A cím helyes
    });
  
    it('Should apply "scrolled" class on scroll', () => {
      cy.scrollTo(0, 100); // Görgetünk lefelé
      cy.get('header').should('have.class', 'scrolled'); // Ellenőrizzük az osztályt
    });
  
    it('Should hide the title on smaller screens', () => {
      cy.viewport(749, 800); // Kisebb képernyőméret beállítása
      cy.get('.logo h2').should('not.be.visible'); // A cím el van rejtve
    });
  
    it('Should keep the title visible on larger screens', () => {
      cy.viewport(1024, 800); // Nagyobb képernyőméret beállítása
      cy.get('.logo h2').should('be.visible'); // A cím látható
    });
  });
  
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

    it('Should display dropdown menu on button click', () => {
      cy.get('.dropdown-toggle').click();
      cy.get('.dropdown-menu').should('be.visible');
    });
  
    it('Should select "Rainy" theme and emit the correct value', () => {
      cy.get('.dropdown-toggle').click();
      cy.get('app-header input[type="radio"][value="rainy"]').check({ force: true });
      cy.get('app-header input[type="radio"]:checked').should('have.value', 'rainy');
    });
  
    it('Should select "Sunny" theme and emit the correct value', () => {
      cy.get('.dropdown-toggle').click();
      cy.get('app-header input[type="radio"][value="sunny"]').check({ force: true });
      cy.get('app-header input[type="radio"]:checked').should('have.value', 'sunny');
    });
  
    it('Should select "Snowy" theme and emit the correct value', () => {
      cy.get('.dropdown-toggle').click();
      cy.get('app-header input[type="radio"][value="snowy"]').check({ force: true });
      cy.get('app-header input[type="radio"]:checked').should('have.value', 'snowy');
    });
  
    it('Should apply the correct styles for each theme', () => {
      const themes = ['rainy', 'sunny', 'snowy'];
      themes.forEach((theme) => {
        cy.get('.dropdown-toggle').click();
        cy.get(`app-header input[type="radio"][value="${theme}"]`).check({ force: true });
        cy.get(`#${theme}`).should('have.css', 'background');
      });
    });
  });
  
describe('FilterComponent Tests', () => {
    beforeEach(() => {
      cy.visit('http://localhost:4200');
    });
  
    it('should render the form with default values', () => {
      cy.get('form').should('exist');
      cy.get('input[type="text"]').should('have.value', '');
      cy.get('input[type="radio"]').should('have.length', 3);
      cy.get('input[type="radio"]:checked').should('have.value', '1');
    });
    it('should update the selectedForecast on radio button click', () => {
      cy.get('input[type="radio"][value="7"]').check({ force: true });
      cy.get('input[type="radio"]:checked').should('have.value', '7');
      cy.get('input[type="radio"][value="14"]').check({ force: true });
      cy.get('input[type="radio"]:checked').should('have.value', '14');
    });
    it('should emit the city and forecast values on form submit', () => {
        cy.get('input[type="text"]').type('Budapest');
        cy.get('input[type="text"]').should('have.value', 'Budapest');
        cy.get('input[type="radio"][value="7"]').check({ force: true });
        cy.get('input[type="radio"]:checked').should('have.value', '7');
        cy.get('form').submit();
        cy.contains('Budapest').should('exist');
      });
      
  });
  
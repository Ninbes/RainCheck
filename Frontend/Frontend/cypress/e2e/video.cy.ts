describe('Video Component Tests', () => {

    beforeEach(() => {
      cy.visit('/');
    });
  
    it('should display video element', () => {
      cy.get('video.background-video').should('exist');
    });
  
    it('should change opacity on scroll', () => {
      cy.get('video.background-video').should('have.css', 'opacity', '1');
      cy.scrollTo(0, 500);
      cy.get('video.background-video')
        .should('have.css', 'opacity')
        .and('match', /^(?!1$).*$/);
    });
  
    it('should have correct video source and type', () => {
      cy.get('video source')
        .should('have.attr', 'src', 'assets/weatherVid.mp4')
        .and('have.attr', 'type', 'video/mp4');
    });
  });
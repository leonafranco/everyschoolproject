/// <reference types="Cypress" />
class homePage {

  elements = {

  }

  clickVisibleButton = () => {
    cy.wait(1000)
    cy.contains( 'Load more' ).then( $mainContainer => {
        if($mainContainer.prop('disabled')) return;
        cy.contains( 'Load more' ).click();
        this.clickVisibleButton();
    });
  }
}
module.exports = new homePage();

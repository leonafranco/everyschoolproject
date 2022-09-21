/// <reference types="Cypress" />

describe('try to login with valid inputs and log out', () => {
    it('should login a user with valids inputs, enter in homepage and then logout to landing page', () => {
      cy.visit("www.portalestgoh.com")
      cy.wait(1000)
      cy.contains("Entrar").click();
      cy.get(".main > form > :nth-child(1) > .form-input").type("charmeleon@gmail.com");
      cy.get(".main > form > :nth-child(2) > .form-input").type("123456789");
      cy.xpath(`//form/button[1]`).click();
      cy.url().should("contains", "www.portalestgoh.com/home")
      cy.get(".dropdown-toggle").click()
      cy.get(`.dropdown-menu > [role="button"]`).click()
      cy.url().should("contains", "https://www.portalestgoh.com")
     });
  })

  describe('try to login with invalid inputs', () => {
    it('should failed and show an alert', () => {
      cy.visit("https://www.portalestgoh.com")
      cy.wait(1000)
      cy.contains("Entrar").click();
      cy.get(".main > form > :nth-child(1) > .form-input").type("cha@gmail.com");
      cy.get(".main > form > :nth-child(2) > .form-input").type("123456789");
      cy.xpath(`//form/button[1]`).trigger("click");
      cy.wait(100)
      cy.on('window:alert',(t)=>{
        expect(t).to.contains('Password ou Email incorretos');
     });
  })
})

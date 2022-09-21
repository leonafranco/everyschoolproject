/// <reference types="Cypress" />

describe('try to login with valid inputs and log out', () => {
    it('should login a user with valids inputs, enter in homepage and then logout to landing page', () => {
      cy.visit("localhost:3000")
      cy.wait(1000)
      cy.contains("Entrar").click();
      cy.get(".main > form > :nth-child(1) > .form-input").type("charmeleon@gmail.com");
      cy.get(".main > form > :nth-child(2) > .form-input").type("123456789");
      cy.xpath(`//form/button[1]`).click();
      cy.url().should("contains", "localhost:3000/home")
      cy.get('[href="/friendlist"] > .row > #Button').click(),
      cy.wait(500),
      cy.get("#formTitle").type("bulbasauro")
      cy.get(".col-6 .card-title").invoke("text").should("eq", "Bulbasauro") 
    })
})
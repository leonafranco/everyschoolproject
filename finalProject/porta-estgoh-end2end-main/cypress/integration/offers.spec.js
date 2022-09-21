/// <reference types="Cypress" />

describe('try to login with valid inputs and log out', () => {
    it('should login a user with valids inputs, enter in homepage and then logout to landing page', () => {
      cy.visit("www.portalestgoh.com")
      cy.wait(1000)
      cy.contains("Entrar").click();
      cy.get(".main > form > :nth-child(1) > .form-input").type("charmeleon@gmail.com");
      cy.get(".main > form > :nth-child(2) > .form-input").type("123456789");
      cy.xpath(`//form/button[1]`).click();
      cy.url().should("contains", "www.portalestgoh.com/home"),
      cy.get("#Button-Nav").click(),
      cy.xpath(`//label[contains(.,'Oferta')]`).click();
      cy.get("[id$='formTitle']").type("Cypresss title"),
      cy.get("[id$='formCompany']").type("cypress company"),
      cy.get("[id$='formBasicEmail']").type("Oliveira do hospital"),
      cy.get("[id$='exampleForm.ControlTextarea1']").type("This offer is made by cypress testing"),
      cy.get('[type="file"]').attachFile("images.jpeg"),
      cy.xpath("//button[@type='submit']").click(),
      cy.wait(1500),
      cy.get("#Logo").click()
      cy.get('[href="/ofertas"] > .row > #Button').click()
      cy.xpath("//a[contains(text(),'Cypresss title')]").click()
    });
  })
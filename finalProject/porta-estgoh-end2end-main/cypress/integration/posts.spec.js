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
    cy.get("#Button-Nav").click()
    cy.get("[id$='exampleForm.ControlTextarea1']").type("This post is made by cypress testing")
    cy.xpath("//button[@type='submit']").click()
    cy.wait(1500)
    cy.get("#Logo").click()
    cy.xpath("//p[contains(.,'This post is made by cypress testing')]").should('be.visible'),
    cy.get(".directory-pub:nth-child(1) > #Menu-item-card a > #Button-card").click();
    cy.get("[id$='exampleForm.ControlTextarea1']").type("this comment is made from cypress"),
    cy.xpath("//button[@type='submit']").click()
    cy.wait(1500)
    cy.xpath("//p[contains(.,'this comment is made from cypress')]").should('be.visible'),
    cy.get("#Logo").click(),
    cy.get(":nth-child(1) > #Menu-item-card > .card-body > .card-title > .row > #DeleteButton > #Remove-Posts").click(),
    cy.on('window:confirm', () => true);
    cy.wait(1000)
    cy.xpath("//p[contains(.,'This post is made by cypress testing')]").should('not.exist')
   });
})
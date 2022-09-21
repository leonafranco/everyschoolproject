class loginPage {

  elements = {
    cookieWarningAcceptButton: () => cy.get('[id$="cookieWarningAcceptButton"]'),
    usernameInput: () => cy.get('[data-placeholder=Username]'),
    passwordInput: () => cy.get('[data-placeholder=Password]'),
    loginButton: () => cy.get('[id$="loginButton"]')
  }

  typeUsername(username) {
    this.elements.usernameInput().type(username);
  }

  typePassword(password) {
    this.elements.passwordInput().type(password);
  }

}
module.exports = new loginPage();
# Creating a Cypress Project

To set up Cypress E2E testing you need to clone it locally.

  1. Open a terminal and navigate to the root directory of the project.

2. Run ```npm init ``` to create your package.json file.
3. After Cypress get installed run ```npx cypress open```to open Cypress CLI 
4. If any ```firewall``` acess warning pop up, accept it by clicking ```yes```and dismiss it.

# Folder structure 

Folder stucture should look like this:

![Cypress CLI](/images/folderStructure.png)

1. ```Fixtures``` have modified json will be.
2. ```integration``` have the following E2E test specs.
3. ```PagesView```represent single pages on website.
4. ```screenshots```if E2E fail or is necessary to take a screenshot will running a test it will be saved here by default.

Before running any test you should check ```/fixtures/info.json``` and change your variables.

# First View of Cypress CLI

![Cypress CLI](/images/cypresscli.png)

1. When open Cypress CLI you can check all yours integration test. 
2. at the top-right corner you can change your default browser as well as choose the default version.
3. You can run all Integration specs by clicking on ```Run xxx integration specs```right below the browser default or choose 1 spec by clicking on it


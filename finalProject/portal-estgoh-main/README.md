# README

Este README irá documentar os passos que normalmente são necessários para correr a aplicação sem problemas.

## Como devo preparar o meu set up?

Primeiro e antes de tudo precisa de ter a versão do node instalado, recomendo utilizar a mais recente que normalmente existe retrocompatibilidade, caso não consiga faça download do NVM [aqui](https://github.com/nvm-sh/nvm).

Corra `nvm use`, se não tiver esta versão do node instalado deve efectuar `nvm install 16.15.1`e depois tente fazer `nvm use` outra vez.

Agora que tem a versão correta do node deve correr `npm install` para instalar todas as dependencias.

Agora precisa de configurar o ficheiro `.env`, por predifinição pode utilizar o que vem por padrão com o repositório, porém caso queira usar a sua base de dados Firebase necessitará de alterar. [neste link pode encontrar mais informação de como o fazer](https://firebase.google.com/docs/web/setup).

## Como corro a aplicação?

Para correr a aplicação basta escrever `npm run start`, automaticamente o react script irá ser chamado. Agora basta abrir [http://localhost:3000](http://localhost:3000) para ver no seu próprio browser.

## Tenho erros de firebase na consola, o que preciso de fazer?

Verifique na aba de regras do firebase se o mesmo permite escrita e leitura na sua base de dados para a data atual, é um erro bastante normal e facil de acontecer. [Saiba mais aqui](https://firebase.google.com/docs/rules) 


## Links que podem ajudar

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: [https://facebook.github.io/create-react-app/docs/code-splitting](https://facebook.github.io/create-react-app/docs/code-splitting)

### Analyzing the Bundle Size

This section has moved here: [https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size](https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size)

### Making a Progressive Web App

This section has moved here: [https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app](https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app)

### Advanced Configuration

This section has moved here: [https://facebook.github.io/create-react-app/docs/advanced-configuration](https://facebook.github.io/create-react-app/docs/advanced-configuration)

### Deployment

This section has moved here: [https://facebook.github.io/create-react-app/docs/deployment](https://facebook.github.io/create-react-app/docs/deployment)

### `npm run build` fails to minify

This section has moved here: [https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify](https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify)

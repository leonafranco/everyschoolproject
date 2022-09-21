package com.company;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import db.*;
import entities.*;

/**
 * Classe Main, classe principal em que o programa começa a correr
 * @author Leonardo Menezes Franco - 2016053468
 **/
public class Main {

    private static final SimpleDateFormat sdf1 = new SimpleDateFormat(("yyyyMMddHHmmss"));
    private static GereUtilizador gereUtilizador;
    private static GereProduto gereProduto;
    private static GereEncomenda gereEncomenda;
    private static Utilizador utilizador = null;
    private static Funcionario funcionario = null;
    private static Notificacao notificacao = null;
    private static Log log;
    private static long startTime = System.nanoTime();
    private static final long  initProcess = System.currentTimeMillis();

/**
 * Metodo main, primeiro metodo a ser corrido pelo programa, nele é instanciada todas as variaveis para terem acesso a base de dados
 * @param args
 **/
    public static void main(String[] args) {


        Connection conn = DB.getConnection();
        gereUtilizador = new GereUtilizador(conn);
        gereProduto = new GereProduto(conn);
        gereEncomenda = new GereEncomenda(conn);
        notificacao = new Notificacao(conn);
        log = new Log(conn);
        menuInicial();



    }
/**
 * Menu Principal, como o nome indica, primeiro menu a ser chamado apos o programa correr
 * */
    private static void menuInicial() {
        while(true) {
            System.out.println("1- Login\n2- Registo\n3- Sair");
            escolha("menuInicial", leituraInt("Escolha: "));
        }
    }
    /**
     * Menu Gestor, menu chamada apos login de um conta sendo gestora
     * */
    private static void menuGestor() {
        while(true) {
            System.out.println("1- Menu Alterar Dados\n2- Aceitar Encomenda\n3- Criar novo Gestor\n4- Ativar Contas\n5- Pesquisas e Listagens\n6- Notificacoes\n7- Log\n8- Sair");
            escolha("menuGestor", leituraInt("Escolha: "));
        }
    }
    /**
     * Menu armazenista, menu chamado apos login de um conta sendo funcionario armazenista
     * */
    private static void menuArmazenistas() {
        while(true) {
            System.out.println("1- Adicionar Produto\n2- Preparar produto\n3- Alterar Dados Funcionario\n4- Pesquisas e Listagens\n5- Notificacoes\n6- Desativar Conta\n7- Sair ");
            escolha("menuArmazenista", leituraInt("Escolha: "));
        }
    }
    /**
     * Menu Cliente, menu chamado apos login de um conta sendo Cliente
     * */
    private static void menuCliente() {
        while(true) {
            System.out.println("1- Nova Compra\n2- Pesquisas e listagens\n3- Encomenda Entregue\n4- Encomenda entregue\n5- Notificacoes\n6- Desativar Conta\n7- Sair ");
            escolha("menuCliente", leituraInt("Escolha: "));
        }
    }
    /**
     * Menu Alterar Dados, menu chamado gestor decidir que quer alterar os dados de utilizador
     * */
    private static void menuAlterarDados() {
        while(true) {
            System.out.println("1- Altera Dados Gestores\n2- Altera Dados Cliente\n3- Altera Dados Funcionario\n4- remover conta\n 5- pedidos de remocao de conta\n5- Voltar");
            escolha("menuAlteraDados", leituraInt("Escolha: "));
        }
    }
    /**
     * Menu para pesquisar e listager, nele aparece todas as listagens e pesquisas do programa para o Gestor
     * */
    private static void menuPesquisaListagemGestor() {
        while(true) {
            System.out.println("1- Listar todos os utilizadores\n2- Pesquisar utilizador\n3- Listar todas as encomendas\n4- Listar encomendas nao entregues\n5- Pesquisar Encomendas\n6- Pesquisar encomendas por intervalo temporal\n7- Voltar");
            escolha("menuPesquisaListagemGestor", leituraInt("Escolha: "));
        }
    }
    /**
     * Menu para pesquisar e listager, nele aparece todas as listagens e pesquisas do programa para o Funcionario
     * */
    private static void menuPesquisaListagemFuncionario() {
        while(true) {
            System.out.println("1- Listar Encomenda\n2- Pesquisar Encomenda\n3- Listar Produtos\n4- Pesquisar Produtos por stock\n5- Pesquisar Produto por categoria\n6- Voltar");
            escolha("menuPesquisaListagemFuncionario", leituraInt("Escolha: "));
        }
    }
    /**
     * Menu para pesquisar e listager, nele aparece todas as listagens e pesquisas do programa para o Cliente
     * */
    private static void menuPesquisaListagemCliente() {
        while(true) {
            System.out.println("1- Listar Encomenda\n2- Pesquisar Encomenda\n3- Listar Categorias\n4- Pesquisar Produto por categoria\n5- Voltar");
            escolha("menuPesquisaListagemCliente", leituraInt("Escolha: "));
        }
    }
    /**
     * Menu Estafeta, menu chamado apos login de um funcionario estafeta
     * */
    private static void menuEstafeta() {
        while(true) {
            System.out.println("1- Encomenda a caminho\n2- Entregar Encomenda\n3- Alterar Dados Funcionario\n4- Pesquisas e Listagens\n5- Notificacoes\n6- Desativar Conta\n7- Sair ");
            escolha("menuEstafeta", leituraInt("Escolha: "));
        }
    }
    /**
     * metodo com um conjunto de ifs e switchs, nele é realizada todas as mudanças de menus assim como chamadas de metodos posteriores
     * @param escolha
     * @param menu
     * */
    private static void escolha (String menu, int escolha) {
        if(menu.equalsIgnoreCase("menuInicial")) {
            switch (escolha) {
                case 1:
                    login();
                    break;

                case 2 :
                    registo();
                    break;

                case 3:
                    sair();
                    break;

                default:
                    erro();
                    break;
            }
        }
        if (menu.equalsIgnoreCase("menuGestor")) {
            switch(escolha) {
                case 1:
                    menuAlterarDados();
                    break;
                case 2:
                    encomendaGestor();
                    break;
                case 3:
                    novoGestor();
                    break;
                case 4:
                    ativarContas();
                    break;
                case 5:
                    menuPesquisaListagemGestor();
                    break;
                case 6:
                    verNotificacao();
                    break;
                case 7:
                    verLog();
                    break;
                case 8:
                    sair();
                    break;
                default:
                    erro();
                    break;
            }
        }
        if (menu.equalsIgnoreCase("menuArmazenista")) {
            switch(escolha) {
                case 1:
                    novoProduto();
                    break;

                case 2:
                    encomendaArmazenista();
                    break;
                case 3:
                    alterarDadosFuncionario();
                    break;
                case 4:
                    menuPesquisaListagemFuncionario();
                    break;
                case 5:
                    verNotificacao();
                    break;
                case 6:
                    pedidoRemocaoConta();
                    break;
                case 7:
                    sair();
                    break;
                default:
                    erro();
                    break;
            }
        }

        if (menu.equalsIgnoreCase("menuEstafeta")) {
            switch(escolha) {
                case 1:
                    encomendaEstafeta();
                    break;
                case 2:
                    encomendaEntregue();
                    break;
                case 3:
                    alterarDadosFuncionario();
                    break;
                case 4:
                    menuPesquisaListagemFuncionario();
                    break;
                case 5:
                    verNotificacao();
                    break;
                case 6:
                    pedidoRemocaoConta();
                    break;
                case 7:
                    sair();
                    break;
                default:
                    erro();
                    break;
            }
        }

        if (menu.equalsIgnoreCase("menuCliente")) {
            switch(escolha) {
                case 1:
                    novaCompra();
                    break;
                case 2:
                    menuPesquisaListagemCliente();
                    break;
                case 3:
                    alterarDadosCliente();
                    break;
                case 4:
                    //encomendaEntregue();
                    break;
                case 5:
                    verNotificacao();
                    break;
                case 6:
                    pedidoRemocaoConta();
                    break;
                case 7:
                    sair();
                    break;
                default:
                    erro();
                    break;
            }
        }

        if(menu.equalsIgnoreCase("menuAlteraDados")) {
            switch (escolha) {
                case 1:
                    alterarDadosGestor();
                    break;

                case 2 :
                    alterarDadosCliente();
                    break;

                case 3:
                    alterarDadosFuncionario();
                    break;

                case 4:
                    pedidoRemocaoConta();
                    break;

                case 5:
                    verPedidoRemocaoConta();
                    break;
                case 6:
                    menuGestor();
                    break;
                default:
                    erro();
                    break;
            }
        }

        if (menu.equalsIgnoreCase("menuPesquisaListagemGestor")) {
            switch(escolha) {
                case 1:
                    listarUtilizadores();
                    break;
                case 2:
                    pesquisarUtilizadores();
                    break;
                case 3:
                    listarEncomendasUtilizador();
                    break;
                case 4:
                    listarEncomendasNentregues();
                    break;
                case 5:
                    pesquisarEncomendasCliente();
                    break;
                case 6:
                    pesquisarEncomendasTempo();
                    break;
                case 7:
                    menuGestor();
                    break;
                default:
                    erro();
                    break;
            }
        }

        if (menu.equalsIgnoreCase("menuPesquisaListagemFuncionario")) {
            switch(escolha) {
                case 1:
                    funcionarioListarEncomenda();
                    break;
                case 2:
                    funcionarioPesquisarEncomendas();
                    break;
                case 3:
                    listarCategoria();
                    break;
                case 4:
                    pesquisarBaixoStock();;
                    break;
                case 5:
                    pesquisarProduto();
                    break;
                case 6:
                    if(funcionario.getEspecializacao().equalsIgnoreCase("armazenista")) {
                        menuArmazenistas();
                    }else if (funcionario.getEspecializacao().equalsIgnoreCase("estafeta")) {
                        menuEstafeta();
                    }
                    break;
                default:
                    erro();
                    break;
            }
        }

        if (menu.equalsIgnoreCase("menuPesquisaListagemCliente")) {
            switch(escolha) {
                case 1:
                    clienteListarEncomenda();
                    break;
                case 2:
                    clientePesquisarEncomendas();
                    break;
                case 3:
                    listarCategoria();
                    break;
                case 4:
                    pesquisarProduto();
                    break;
                case 5:
                    menuCliente();
                    break;
                default:
                    erro();
                    break;
            }
        }

    }

    /**
     * Metodo de Login, metodo de serve como autenticacao de um Utilizador a sua conta, precisa de introduzir login e password e estes sejam compativeis com
     * informacao na base de dados, posteriormente informa se existem notificacoes assim como chamada de menus principais referidos anteriormente
     * */
    private static void login() {
        System.out.println("Autenticacao");
        String login = leituraString("Login:");
        String password = leituraString("Password");
        utilizador = gereUtilizador.login(login, password);
        if(utilizador == null) {
            System.out.println("Login ou password estão errados");
        }else if (utilizador.getEstado().equalsIgnoreCase("Inativo")) {
            System.out.println("Sua conta ainda não foi ativa");
        }else if(utilizador.getEstado().equalsIgnoreCase("Ativo") && utilizador.getTipo().equalsIgnoreCase("Gestor")) {
            log.inserirLog(utilizador.getLogin(), "Login");
            System.out.println("Bem-vindo [" + utilizador.getNome() +"]");
            if(gereUtilizador.contasInativas("Remover").length() > 0) {
                System.out.println("Existem novos utilizadores por Remover");
            }
            if(gereUtilizador.contasInativas("inativo").length() > 0) {
                System.out.println("Existem novos utilizadores por aceitar");
            }
            if(gereEncomenda.EncomendaEstado("iniciada").length() > 0) {
                System.out.println("Existem novos pedidos de encomenda");
            }
            if(gereEncomenda.EncomendaEstado("terminada").length() > 0) {
                System.out.println("Uma encomenda foi terminada");
            }
            if(gereEncomenda.encomendaExpirada().length() > 0 ) {
                String lista = gereEncomenda.encomendaExpirada();
                System.out.println("Encomendas com o identificador " + lista +" ainda nao foram terminadas");
            }
            menuGestor();
        }else if(utilizador.getEstado().equalsIgnoreCase("Ativo") && utilizador.getTipo().equalsIgnoreCase("cliente")){
            log.inserirLog(utilizador.getLogin(), "Login");
            System.out.println("Bem-vindo [" + utilizador.getNome() +"]");
            String noti = notificacao.lerNotificacao(gereUtilizador.u_id(utilizador.getLogin()));
            if(noti.length() > 0) {
                System.out.println("existem notificacoes por ler");
            }
            menuCliente();
        }
        else if(utilizador.getEstado().equalsIgnoreCase("Ativo") && utilizador.getTipo().equalsIgnoreCase("funcionario")) {
            funcionario = gereUtilizador.loginFunc(utilizador);
            log.inserirLog(utilizador.getLogin(), "Login");
            if (funcionario.getEspecializacao().equalsIgnoreCase("armazenista")) {
                if(gereProduto.baixoStock().length() > 0) {
                    String lista = gereProduto.baixoStock();
                    System.out.println("Os artigos com SKU: " + lista + " tem stock inferior a 10" );
                }
                System.out.println("Bem-vindo [" + funcionario.getNome() + "]");
                String noti = notificacao.lerNotificacao(gereUtilizador.u_id(utilizador.getLogin()));
                if(noti.length() > 0) {
                    System.out.println("existem notificacoes por ler");
                }
                menuArmazenistas();
            } else if (funcionario.getEspecializacao().equalsIgnoreCase("estafeta")) {
                log.inserirLog(utilizador.getLogin(), "Login");
                System.out.println("Bem-vindo [" + funcionario.getNome() + "]");
                String noti = notificacao.lerNotificacao(gereUtilizador.u_id(utilizador.getLogin()));
                if(noti.length() > 0) {
                    System.out.println("existem notificacoes por ler");
                }
                menuEstafeta();
            }
        }else if (utilizador.getEstado().equalsIgnoreCase("Reprovado")) {
            System.out.println("Infelizmente sua conta foi reprovada por um gestor, tente entrar em contacto");
        }



    }

    /**
     * Metodo de registo, nele permite o utilizador a criar uma conta nova no programa, sendo essa conta Gestor cliente ou funcionario
     */

    private static void registo() {

        String login = leituraString("Login: ");
        while(!gereUtilizador.verificaDuplicado(login, "login")) {
            login = leituraString("login existente\n\nIntroduza um login novo : ");
            gereUtilizador.verificaDuplicado(login, "login");
        }
        String nome = leituraString("Nome: ");
        String password = leituraString("Password: ");
        String email = leituraString("email: ");
        while(!gereUtilizador.verificaDuplicado(email, "email") || !verificarEmail(email)) {
            if(!gereUtilizador.verificaDuplicado(email, "email")){
                email = leituraString("Email já existente, tente outro");
                gereUtilizador.verificaDuplicado(email, "email");
            }else if(!verificarEmail(email)){
                email = leituraString("Email invalido\n\nIntroduza um email valido: ");
                verificarEmail(email);
            }
        }
        if(gereUtilizador.verificarUtilizador()) {
            int tipo = leituraInt("Tipo?\n1-Gestor\n2-Cliente\n3-Funcionario");
            if (tipo == 1 ){
                Gestor gestor = new Gestor(nome, login, password, "inativo",  email, "gestor");
                if (gereUtilizador.inserirUtilizador(gestor)) {
                    System.out.println("Gestor Criado com sucesso");
                }else
                    System.out.println("Erro ao criar Gestor");
            }
            if (tipo == 2){

                long numeroContribuinte = leituraLong("Numero De Contribuinte: ");
                while (!verificaNumero(numeroContribuinte, "contribuinte")) {
                    System.out.println("Introduza um valor valido");
                    numeroContribuinte = leituraLong("Numero De Contribuinte: ");
                }

                long contacto = leituraLong("Contacto: ");
                while (!verificaNumero(contacto, "contacto")) {
                    System.out.println("Introduza um contacto valido");
                    contacto = leituraLong("Contacto: ");
                }

                String morada = leituraString("Morada: ");


                Cliente cliente = new Cliente(nome, login, password, "inativo", email, "cliente", numeroContribuinte, contacto, morada);
                if(gereUtilizador.inserirCliente(cliente)) {
                    System.out.println("Cliente Criado com sucesso");
                }else
                    System.out.println("Erro ao criar Cliente");
            }
            if (tipo == 3){

                long numeroContribuinte = leituraLong("Numero De Contribuinte: ");
                while (!verificaNumero(numeroContribuinte, "contribuinte")) {
                    System.out.println("Introduza um valor valido");
                    numeroContribuinte = leituraLong("Numero De Contribuinte: ");
                }

                long contacto = leituraLong("Contacto: ");
                while (!verificaNumero(contacto, "contacto")) {
                    System.out.println("Introduza um valor valido");
                    contacto = leituraLong("Contacto: ");
                }
                String morada = leituraString("Morada: ");

                int especializacao = leituraInt("Especializacao: \n\n1-Armazenistas \n2-Estafetas  ");
                while (especializacao != 1 && especializacao != 2) {
                    System.out.println("Introduza um valor valido");
                    especializacao = leituraInt("Especializacao: ");
                }
                String espec = null;
                if(especializacao == 1) {
                    espec = "armazenista";
                }else if(especializacao == 2) {
                    espec = "estafeta";
                }
                String tempDataDeInicio = leituraString("Data de Inicio de atividade dd/MM/YYYY: ");
                Date dataDeInicio = leituraDate(tempDataDeInicio);
                if (dataDeInicio != null) {
                    Funcionario funcionario = new Funcionario(nome, login, password, "inativo", email, "funcionario", numeroContribuinte, contacto, morada, espec, dataDeInicio);
                    if(gereUtilizador.inserirFuncionario(funcionario)) {
                        System.out.println("Funcionario Criado com sucesso");
                    }
                }
            }
        }else {
            System.out.println("Não existe nenhum conta criada, será criado como Gestor ");
            Gestor gestor = new Gestor(nome, login, password, "ativo", email, "gestor");
            if (gereUtilizador.inserirUtilizador(gestor)) {
                System.out.println("Gestor Criado com sucesso");
            }else
                System.out.println("Erro ao criar Gestor");

        }
    }

    /**
     * Metodo novoGestor, metodo que possibilita a criacao de um novo gestor por parte de gestores
     */

    private static void novoGestor() {
        String login = leituraString("Login: ");
        while (!gereUtilizador.verificaDuplicado(login, "login")) {
            login = leituraString("login existente\n\nIntroduza um login novo : ");
            gereUtilizador.verificaDuplicado(login, "login");
        }
        String nome = leituraString("Nome: ");
        String password = leituraString("Password: ");
        String email = leituraString("email: ");
        while (!gereUtilizador.verificaDuplicado(email, "email") && !verificarEmail(email)) {
            if (!gereUtilizador.verificaDuplicado(email, "email")) {
                email = leituraString("Email já existente, tente outro");
                gereUtilizador.verificaDuplicado(email, "email");
            } else if (!verificarEmail(email)) {
                email = leituraString("Email invalido\n\nIntroduza um email valido: ");
                verificarEmail(email);
            }
        }
        Gestor gestor = new Gestor(nome, login, password, "ativo", email, "gestor");
        if (gereUtilizador.inserirUtilizador(gestor)) {
            log.inserirLog(utilizador.getLogin(), "Criou Novo gestor");
            System.out.println("Gestor Criado com sucesso");
        }else
            System.out.println("Erro ao criar Gestor");
    }

    /**
     * Metodo alterarDadosGestor, metodo que possibilitida Gestores de alterar as suas contas ou de outros Gestores
     */

    private static void alterarDadosGestor() {

        String login = null;
        int op = leituraInt("Deseja alturar a sua conta ou a de outro gestor? \n1-Propria Conta\n2-Outro Gestor");
        if(op == 1) {
            login = utilizador.getLogin();
        }else if(op == 2) {
            login = leituraString("Introduza o login do outro gestor");
        }

        System.out.println("Deseja alterar que tipo de dados?:\n\n1- Nome\n2- Login\n3- Password\n4- email");

        int opcao = leituraInt("Escolha uma opção");

        switch (opcao) {
            case 1:
                String nome = leituraString("Introduza o novo nome");
                if (gereUtilizador.updateUtilizador(utilizador.getTipo(), nome, null, "nome", login)) {
                    System.out.println("Nome alterado com sucesso");
                    utilizador.setNome(nome);
                    log.inserirLog(utilizador.getLogin(), "Alterou Nome");

                } else {
                    System.out.println("Erro ao alterar o nome");
                }
                break;

            case 2:
                String Novologin = leituraString("Introduza o novo login: ");
                while (!gereUtilizador.verificaDuplicado(Novologin, "login")) {
                    login = leituraString("login existente\n\nIntroduza um login novo : ");
                    gereUtilizador.verificaDuplicado(login, "login");
                }
                if (gereUtilizador.updateUtilizador(utilizador.getTipo(), Novologin, null, "login", login)) {
                    System.out.println("Login alterado com sucesso");
                    utilizador.setLogin(login);
                    log.inserirLog(utilizador.getLogin(), "Alterou Login");

                } else {
                    System.out.println("Erro ao alterar o login");
                }
                break;

            case 3:
                String password = leituraString("introduza a nova password: ");
                if (gereUtilizador.updateUtilizador(utilizador.getTipo(), password, null, "password", login)) {
                    System.out.println("password alterado com sucesso");
                    utilizador.setPassword(password);
                    log.inserirLog(utilizador.getLogin(), "Alterou password");

                }
                break;

            case 4:
                String email = leituraString("introduza o novo email: ");
                while(!gereUtilizador.verificaDuplicado(email, "email") || !verificarEmail(email)) {
                    if(!gereUtilizador.verificaDuplicado(email, "email")){
                        email = leituraString("Email já existente, tente outro");
                        gereUtilizador.verificaDuplicado(email, "email");
                    }else if(!verificarEmail(email)){
                        email = leituraString("Email invalido\n\nIntroduza um email valido: ");
                        verificarEmail(email);
                    }
                }
                if (gereUtilizador.updateUtilizador(utilizador.getTipo(), email, null, "email", login)) {
                    System.out.println("Email alterado com sucesso");
                    utilizador.setEmail(email);
                } else {
                    System.out.println("Erro ao alterar Email");
                }
                break;
        }
    }

    /**
     * Metodo verLog, possibilita gestores de ver todos os logs feitos por utilizadores;
     */

    private static void verLog() {
        String lista = "";
        int op = leituraInt("\n\nPretende:\n1-Ver todo log\n2-Procurar log de um utilizador\n3-Voltar no Menu");

        switch (op) {
            case 1:
                lista = log.listaLog();
                System.out.println("LOG:");
                System.out.println("DATA | HORA | UTILIZADOR | ACCAO");
                System.out.println(lista);
                break;

            case 2:
                String login = leituraString("Introduza o login do utilizador que deseja procurar");
                lista = log.listaLog(login);
                System.out.println("LOG");
                System.out.println("DATA | HORA | UTILIZADOR | ACCAO");
                System.out.println(lista);
                break;

            case 3:
                menuGestor();
                break;

            default:
                erro();
                break;
        }
    }

    /**
     * Metodo alterarDadosCliente, metodo que possibilitida Cliente de alterar as suas contas ou de Gestores alterar contas de clientes
     */

    private static void alterarDadosCliente() {

        String login;
        if (utilizador.getTipo().equalsIgnoreCase("Gestor")) {
            login = leituraString("Ensira o login do Cliente que deseja alterar: ");
        }else {
            login = utilizador.getLogin();
        }

        System.out.println("Deseja alterar que tipo de dados?:\n\n1- Nome\n2- Login\n3- Password\n4- email\n5- contribuinte\n6- contacto\n7- Morada\n8- Sair");

        int opcao = leituraInt("Escolha uma opção");


        switch (opcao) {
            case 1:
                String nome = leituraString("Introduza o novo nome");
                if(gereUtilizador.updateUtilizador("cliente", nome, null, "nome", login)) {
                    System.out.println("Nome alterado com sucesso");
                }else {
                    System.out.println("Erro ao alterar o nome");
                }
                break;

            case 2 :
                String Novologin = leituraString("Introduza o novo login: ");
                while(!gereUtilizador.verificaDuplicado(Novologin, "login")) {
                    login = leituraString("login existente\n\nIntroduza um login novo : ");
                    gereUtilizador.verificaDuplicado(Novologin, "login");
                }
                if(gereUtilizador.updateUtilizador("cliente", login, null, "login", login)) {
                    System.out.println("Login alterado com sucesso");
                }else {
                    System.out.println("Erro ao alterar o login");
                }
                break;

            case 3:
                String password = leituraString("introduza a nova password: ");
                if(gereUtilizador.updateUtilizador("cliente", password, null, "password", login)) {
                    System.out.println("password alterado com sucesso");
                }
                break;

            case 4:
                String email = leituraString("introduza o novo email: ");
                while(!gereUtilizador.verificaDuplicado(email, "email")) {
                    email = leituraString("email existente\n\nIntroduza um email novo : ");
                    gereUtilizador.verificaDuplicado(email, "email");
                }
                if(gereUtilizador.updateUtilizador("cliente", email, null, "email", login)) {
                    System.out.println("Email alterado com sucesso");
                }else {
                    System.out.println("Erro ao alterar Email");
                }
                break;

            case 5:
                    long numeroContribuinte = leituraLong("Numero De Contribuinte: ");
                    while (!verificaNumero(numeroContribuinte, "contribuinte")) {
                        System.out.println("Introduza um valor valido");
                        numeroContribuinte = leituraLong("Numero De Contribuinte: ");
                    }
                    if(gereUtilizador.updateUtilizador("cliente", null, numeroContribuinte, "contribuinte", login)) {
                        System.out.println("Contribuinte alterado com sucesso");
                    }else {
                        System.out.println("Erro ao alterar Contribuinte");
                    }
                break;
            case 6:
                    long contacto = leituraLong("Contacto: ");
                    while (!verificaNumero(contacto, "contacto")) {
                        System.out.println("Introduza um valor valido");
                        contacto = leituraLong("Contacto: ");
                    }
                    if(gereUtilizador.updateUtilizador("cliente", null, contacto, "contacto", login)) {
                        System.out.println("Contacto alterado com sucesso");
                    }else {
                        System.out.println("Erro ao alterar Contacto");
                    }
                break;

            case 7:
                    String morada = leituraString("Morada: ");
                    if(gereUtilizador.updateUtilizador("cliente", morada, null, "morada", login)) {
                        System.out.println("Morada Alterada com sucesso");
                    }else {
                        System.out.println("Erro ao alterar morada");
                    }
            case 8:
                if(utilizador.getTipo().equalsIgnoreCase("Gestor")) {
                    menuAlterarDados();
                }else {
                    menuCliente();
                }
            default:
                erro();
                break;
        }
    }

    /**
     * Metodo alterarDadosCliente, metodo que possibilitida Funcionarios de alterar as suas contas ou de Gestores alterar contas de Funcionarios
     */

    private static void alterarDadosFuncionario() {

        String login;
        if (utilizador.getTipo().equalsIgnoreCase("Gestor")) {
            login = leituraString("Ensira o login do Funcionario que deseja alterar: ");
        }else {
            login = utilizador.getLogin();
        }

        System.out.println("Deseja alterar que tipo de dados?:\n\n1- Nome\n2- Login\n3- Password\n4- email\n5- contribuinte\n6- contacto\n7- Morada\n8- especializacao\n9-Data inicio atividade\n10- Sair");

        int opcao = leituraInt("Escolha uma opção");


        switch (opcao) {
            case 1:
                String nome = leituraString("Introduza o novo nome");
                if(gereUtilizador.updateUtilizador("funcionario", nome, null, "nome", login)) {
                    System.out.println("Nome alterado com sucesso");
                }else {
                    System.out.println("Erro ao alterar o nome");
                }
                break;

            case 2 :
                String Novologin = leituraString("Introduza o novo login: ");
                while(!gereUtilizador.verificaDuplicado(Novologin, "login")) {
                    login = leituraString("login existente\n\nIntroduza um login novo : ");
                    gereUtilizador.verificaDuplicado(Novologin, "login");
                }
                if(gereUtilizador.updateUtilizador("funcionario", login, null, "login", login)) {
                    System.out.println("Login alterado com sucesso");
                }else {
                    System.out.println("Erro ao alterar o login");
                }
                break;

            case 3:
                String password = leituraString("introduza a nova password: ");
                if(gereUtilizador.updateUtilizador("funcionario", password, null, "password", login)) {
                    System.out.println("password alterado com sucesso");
                }
                break;

            case 4:
                String email = leituraString("introduza o novo email: ");
                while(!gereUtilizador.verificaDuplicado(email, "email")) {
                    email = leituraString("email existente\n\nIntroduza um email novo : ");
                    gereUtilizador.verificaDuplicado(email, "email");
                }
                if(gereUtilizador.updateUtilizador("funcionario", email, null, "email", login)) {
                    System.out.println("Email alterado com sucesso");
                }else {
                    System.out.println("Erro ao alterar Email");
                }
                break;

            case 5:
                long numeroContribuinte = leituraLong("Numero De Contribuinte: ");
                while (!verificaNumero(numeroContribuinte, "contribuinte")) {
                    System.out.println("Introduza um valor valido");
                    numeroContribuinte = leituraLong("Numero De Contribuinte: ");
                }
                if(gereUtilizador.updateUtilizador("funcionario", null, numeroContribuinte, "contribuinte", login)) {
                    System.out.println("Contribuinte alterado com sucesso");
                }else {
                    System.out.println("Erro ao alterar Contribuinte");
                }
                break;
            case 6:
                long contacto = leituraLong("Contacto: ");
                while (!verificaNumero(contacto, "contacto")) {
                    System.out.println("Introduza um valor valido");
                    contacto = leituraLong("Contacto: ");
                }
                if(gereUtilizador.updateUtilizador("funcionario", null, contacto, "contacto", login)) {
                    System.out.println("Contacto alterado com sucesso");
                }else {
                    System.out.println("Erro ao alterar Contacto");
                }
                break;

            case 7:
                String morada = leituraString("Morada: ");
                if(gereUtilizador.updateUtilizador("funcionario", morada, null, "morada", login)) {
                    System.out.println("Morada Alterada com sucesso");
                }else {
                    System.out.println("Erro ao alterar morada");
                }
            case 8:
                int especializacao = leituraInt("Especializacao: \n\n1-Armazenistas \n2-Estafetas  ");
                while (especializacao != 1 && especializacao != 2) {
                    System.out.println("Introduza um valor valido");
                    especializacao = leituraInt("Especializacao: ");
                }
                String espec = null;
                if(especializacao == 1) {
                    espec = "armazenista";
                }else if(especializacao == 2) {
                    espec = "estafeta";
                }
                if(gereUtilizador.updateUtilizador("funcionario", espec, null, "especializacao", login)){
                    System.out.println("Especializacao alterada com sucesso");
                }else {
                    System.out.println("Erro ao alterar especializacao");
                }

            case 10:
                if(utilizador.getTipo().equalsIgnoreCase("Gestor")) {
                    menuAlterarDados();
                }else if (funcionario.getEspecializacao().equalsIgnoreCase("armazenista")) {
                    menuArmazenistas();
                }else if (funcionario.getEspecializacao().equalsIgnoreCase("estafeta")) {
                    //menuEstafeta();
                }
            default:
                erro();
                break;
        }
    }

    /**
     * Metodo ativarContas, metodo que possibilitida Gestores de ativarem contas, essas contas quando registadas estao inativas
     */

    private static void ativarContas() {

        String lista = gereUtilizador.contasInativas("inativo");
        String login;
        System.out.println("Contas inativas:");
        System.out.println("Nome | Login | Tipo");
        System.out.println(lista);
        int op = leituraInt("\n\n\nPretende 1-ativar, 2-reprovar, 3-inativar alguma conta?\n\n\n4- para voltar ao menu anterior");

        switch (op) {
            case 1:
                login = leituraString("\n\n\nIntroduza o login do utilizador que pretende ativar");
                if(gereUtilizador.updateUtilizador(utilizador.getTipo(), "ativo", null, "estado", login)) {
                    System.out.println(login + " passou a ter a conta ativa!");
                }else {
                    System.out.println("Introduziu um Login invalido");
                }
            break;

            case 2:
                login = leituraString("\n\n\nIntroduza o login do utilizador que pretende reprovar");
                if(gereUtilizador.updateUtilizador(utilizador.getTipo(), "reprovado", null, "estado", login)) {
                    System.out.println(login + " passou a ter a conta reprovada!");
                }else {
                    System.out.println("Introduziu um Login invalido");
                }
                break;

            case 3:
                login = leituraString("\n\n\nIntroduza o login do utilizador que pretende inativar");
                if(gereUtilizador.updateUtilizador(utilizador.getTipo(), "inativo", null, "estado", login)) {
                    System.out.println(login + " passou a ter a conta inativa!");
                }else {
                    System.out.println("Introduziu um Login invalido");
                }
                break;

            case 4:
                menuGestor();
                break;

            default:
                erro();
                break;
        }


    }

    /**
     * Metodo NovoProduto, metodo que possibilitida Armazenista introduzir novo produto no sistema
     */

    private static void novoProduto() {
        System.out.println("Novo Produto");
        String designacao = leituraString("Introduza qual a designacao do produto: ");
        String fabricante = leituraString("Introduza qual o fabricante: ");
        int peso = leituraInt("Introduza qual o peso do produto em gramas");
        float preco = leituraFloat("Introduza qual o preco do produto");
        long SKU = criarSku();
        int lote = leituraInt("Introduza o lote do produto");
        String tempdataDeProd = null;
        Date dataDeProd = null;
        while(dataDeProd == null) {
            tempdataDeProd = leituraString("Introduza data de produção DD/mm/YYYY");
            dataDeProd = leituraDate(tempdataDeProd);
        }
        int op = leituraInt("Este produto tem data de validade? \n1-Sim\n2-Nao");
        Date dataDeValidade = null;
        String tempDataDeValidade = null;
        if(op == 1) {
            while(dataDeValidade == null) {
                tempDataDeValidade = leituraString("Introduza data de validade DD/mm/YYYY");
                dataDeValidade = leituraDate(tempDataDeValidade);
            }
        }
        int stock = leituraInt("Introduza o stock existente!");
        System.out.println("insira agora os dados sobre a categoria!");
        String categoria = leituraString("Introduza qual a deisgnacao da categoria");
        String classificacao = leituraString("Introduza qual a classificacao (AlfaNumerico)");
        Produto produto = new Produto(designacao, fabricante, peso, preco, SKU, lote, dataDeProd, dataDeValidade, stock, categoria, classificacao);
        if(gereProduto.inserirProduto(produto)) {
            System.out.println("Produto Inserido com sucesso");
        }

    }

    /**
     * Metodo NovaCompra, metodo que possibilitida Cliente efectuar uma nova compra
     */

    private static void novaCompra() {
        System.out.println("Nova Compra");
        String identificador = identificadorCompra();
        Date dataCriacao = new Date();
        Encomenda encomenda = new Encomenda(identificador, dataCriacao);
        if(gereEncomenda.inserirEncomenda(encomenda)) {
            int op = 0;
            while(op!=2) {
                String lista = gereProduto.listaProduto();
                System.out.println("SKU | DESIGNACAO | PRECO");
                System.out.println(lista);
                long idEnc = gereEncomenda.ultimoID();
                long SKU = leituraLong("Insira o SKU do produto que deseja comprar");
                long idProd = gereProduto.getIDProduto(SKU);
                long idCliente = gereUtilizador.u_id(utilizador.getLogin());
                if(gereEncomenda.InserirProdEnc(idProd, idEnc) && gereEncomenda.InserirClienteEnc(idCliente, idEnc)) {
                    System.out.println("Compra feita com sucesso!");
                    op = leituraInt("Deseja fazer mais alguma compra? 1-Sim 2-Não");
                }
            }
        }else {
            System.out.println("Erro ao introduzir encomenda!!");
        }


    }

    /**
     * Metodo encomendaGestor, possibilita um gestor de alterar o estado a encomenda de iniciada para aceite, em seguida escolhe um armazenista para
     * preparar a encomenda
     */

    private static void encomendaGestor() {
        String lista = gereEncomenda.EncomendaEstado("iniciada");
        String identificador;
        System.out.println("encomendas iniciada:");
        System.out.println("Identificador | Custo | Estado");
        System.out.println(lista);
        int op = leituraInt("\n\n\nPretende 1-aceitar, 2-rejeitar alguma encomenda??\n\n3- para voltar ao menu anterior");

        switch (op) {
            case 1:
                identificador = leituraString("\n\n\nIntroduza o identificador da encomenda que pretende aceitar");
                if(gereEncomenda.updateEncomenda(0, null, "aceite", "estado", identificador)) {
                    System.out.println(identificador + " passou a ser aceite!");
                    atribuirArmazenista();
                }else {
                    System.out.println("Introduziu um identificador invalido");
                }
                break;

            case 2:
                identificador = leituraString("\n\n\nIntroduza o identificador que pretende rejeitar");
                if(gereEncomenda.updateEncomenda(0, null, "rejeitado", "estado", identificador)) {
                    System.out.println(identificador + " passou a recusada!");
                    notificacao.inserirNotificacao("Sua encomenda foi rejeitada por um gestor!");
                    notificacao.inserirNotificacaoUtilizador(gereEncomenda.u_id_encomenda(identificador), notificacao.ultimoID());
                }else {
                    System.out.println("Introduziu um identificador invalido");
                }
                break;

            case 3:
                menuGestor();
                break;

            default:
                erro();
                break;
        }

    }

    /**
     * Metodo encomendaArmazenista, metodo que possibilitida Armazenista de preparar encomenda alterando o seu estado para "preparada"
     * em seguida atribui a encomenda a um estafeta;
     *
     */

    private static void encomendaArmazenista() {
        String lista = gereEncomenda.EncomendaEstado("aceite");
        String identificador;
        System.out.println("encomendas aceite:");
        System.out.println("Identificador | Custo | Estado");
        System.out.println(lista);
        int op = leituraInt("\n\n\nPretende preparar a encomenda? digite: 1\n2-Sair");

        switch (op) {
            case 1:
                identificador = leituraString("\n\n\nIntroduza o identificador que pretende preparar");
                if(gereEncomenda.updateEncomenda(0, null, "preparada", "estado", identificador)) {
                    System.out.println(identificador + " passou a estar preparada!");
                    atribuirEncomenda(identificador);
                }else {
                    System.out.println("Introduziu um identificador invalido");
                }
                break;

            case 2:
                menuArmazenistas();
                break;

            default:
                erro();
                break;
        }

    }

    /**
     * Metodo que continua do encomendaArmazenista, neste metodo atribui a um estafeta a entrega de uma encomenda
     * @param identificador
     */
    private static void atribuirEncomenda(String identificador) {
        String listaEstafeta = gereUtilizador.procuraEstafeta("estafeta");

        System.out.println("\n\n\nAtribua um estafeta:");
        System.out.println("Nome | Login | Especializacao");
        System.out.println(listaEstafeta);
        int op = leituraInt("\n\n\nPretende atribuir a encomenda? digite: 1\n2-Sair");

        switch (op) {
            case 1:
                String login = leituraString("\n\n\nIntroduza o login do estafeta");
                if(gereEncomenda.updateEncomenda(0, null, "aguarda entrega", "estado", identificador)) {
                    notificacao.inserirNotificacao("Existem encomendas por entregar");
                    notificacao.inserirNotificacaoUtilizador(gereUtilizador.u_id(login), notificacao.ultimoID());
                    System.out.println(identificador + " passou a estar preparada!");
                }else {
                    System.out.println("Introduziu um identificador invalido");
                }
                break;

            case 2:
                menuArmazenistas();
                break;

            default:
                erro();
                break;
        }

    }

    /**
     * Metodo que continua do encomendaGestor, metodo que possibilitida enviar notificacao para um armazenista da necessidade de preparar encomenda
     */

    private static void atribuirArmazenista() {
        String listaEstafeta = gereUtilizador.procuraEstafeta("armazenista");

        System.out.println("\n\n\nAtribua um armazenista:");
        System.out.println("Nome | Login | Especializacao");
        System.out.println(listaEstafeta);
        int op = leituraInt("\n\n\nPretende atribuir a encomenda? digite: 1\n2-Sair");

        switch (op) {
            case 1:
                String login = leituraString("\n\n\nIntroduza o login do armazenista");

                notificacao.inserirNotificacao("Existem encomendas por entregar");
                notificacao.inserirNotificacaoUtilizador(gereUtilizador.u_id(login), notificacao.ultimoID());
                break;


            default:
                erro();
                break;
        }

    }

    /**
     * Metodo encomendaEstafeta, metodo que possibilitida Estafeta de alterar o estado da encomenda de em "aguarda entrega" para "em transporte"
     */

    private static void encomendaEstafeta() {
        String lista = gereEncomenda.EncomendaEstado("aguarda entrega");
        String identificador;
        System.out.println("encomendas iniciada:");
        System.out.println("Identificador | Custo | Estado");
        System.out.println(lista);
        int op = leituraInt("\n\n\nPretende:\n1- Marcar como entregue\n2- Sair");

        switch (op) {
            case 1:
                identificador = leituraString("\n\n\nIntroduza o identificador que pretende marcar como em transporte");
                if(gereEncomenda.updateEncomenda(0, null, "em transporte", "estado", identificador)) {
                    System.out.println(identificador + " passou a ser aceite!");
                }else {
                    System.out.println("Introduziu um identificador invalido");
                }
                break;

            case 2:
                menuEstafeta();
                break;

            default:
                erro();
                break;
        }

    }

    /**
     * Metodo encomendaEstafeta, metodo que possibilitida Estafeta de alterar o estado da encomenda de em "em transporte" para "entregue"
     */

    private static void encomendaEntregue() {
        String lista = gereEncomenda.EncomendaEstado("em transporte");
        String identificador;
        System.out.println("encomendas em transporte:");
        System.out.println("Identificador | Custo | Estado");
        System.out.println(lista);
        int op = leituraInt("\n\n\nPretende 1- Marcar como entregue?\n2- Sair");

        switch (op) {
            case 1:
                identificador = leituraString("\n\n\nIntroduza o identificador que pretende marcar como entregue");
                if(gereEncomenda.updateEncomenda(0, null, "entregue", "estado", identificador)) {
                    System.out.println(identificador + " passou a ser entregue!");
                    notificacao.inserirNotificacao("A sua encomenda já foi entregue");
                    notificacao.inserirNotificacaoUtilizador(gereEncomenda.u_id_encomenda(identificador), notificacao.ultimoID());
                }else {
                    System.out.println("Introduziu um identificador invalido");
                }
                break;

            case 2:
                menuEstafeta();
                break;


            default:
                erro();
                break;
        }
    }

    /**
     * Metodo verNotificacao, metodo que permite utilizadores de ver notificacoes pendentes
     */

    private static void verNotificacao() {
        String lista = "";
        notificacao.lerNotificacao(gereUtilizador.u_id(utilizador.getLogin()));
        System.out.println("Notificações:\n" + lista);
        notificacao.mensagemLida(gereUtilizador.u_id(utilizador.getLogin()));
        log.inserirLog(utilizador.getLogin(), "Viu notificacoes");
    }

    /**
     * Metodo listarUtilizadores, metodo que permite Gestores de listar utilizadores, este de forma descendente ou ascendente
     */

    private static void listarUtilizadores() {
        String lista = "";
        int op = leituraInt("Deseja ver tabela ordenada de forma descendente? \n1-Sim\n2-Nao");
        if(op == 1) {
            lista = gereUtilizador.listarUtilizadores("desc");
        }else {
            lista = gereUtilizador.listarUtilizadores("asc");
        }

        System.out.println("Lista de Utilizadores:");
        System.out.println("Nome | Login | Tipo");
        System.out.println(lista);
    }

    /**
     * metodo pesquisarUtilizadores, metodo que permite gestores de pesquisar um utilizador por Nome, Login ou Tipo
     */

    private static void pesquisarUtilizadores() {
        String lista = "";
        String pesquisa = "";
        int op = leituraInt("Deseja pesquisar por \n1-Nome\n2-Login\n3-Tipo");

        switch (op) {
            case 1:
                pesquisa = leituraString("Qual o nome que deseja pesquisar");
                lista = gereUtilizador.pesquisarUtilizador("nome", pesquisa);
                break;

            case 2:
                pesquisa = leituraString("Qual o Login que deseja pesquisar");
                lista = gereUtilizador.pesquisarUtilizador("login", pesquisa);
                break;

            case 3:
                pesquisa = leituraString("Qual o nome que deseja pesquisar");
                lista = gereUtilizador.pesquisarUtilizador("tipo", pesquisa);
                break;
        }

        System.out.println("Utilizadores encontrados:");
        System.out.println("Nome | Login | Tipo");
        System.out.println(lista);

    }

    /**
     * Metodo listarEncomendasUtilizador, metodo que permite Gestores de listar encomendas por utilizadores ou Data de criacao
     */

    private static void listarEncomendasUtilizador() {
        String lista = "";
        String sentido = "";
        int op = leituraInt("Deseja ver tabela ordenada de forma descendente? \n1-Sim\n2-Nao");
        if(op == 1) {
            sentido = "desc";
        }else {
            sentido = "asc";
        }

        int tipo = leituraInt("Deseja listar por \n1- data de criacao\n2- utilizador");

        switch (tipo) {
            case 1:
                String tempData = leituraString("Data de pesquisa dd/MM/YYYY: ");
                Date data = leituraDate(tempData);
                lista = gereEncomenda.listarEncomendas("data", sentido, data);
                break;

            case 2:
                lista = gereEncomenda.listarEncomendas("utilizador", sentido, null);
                break;
        }

        System.out.println("Lista de Encomenda:");
        System.out.println("Identificador | data criacao | estado");
        System.out.println(lista);
    }

    /**
     * Metodo listarEncomendasUtilizador, metodo que permite Gestores de listar encomendas por utilizadores ou Data de criacao
     */

    private static void listarEncomendasNentregues() {
        String lista = "";
        int op = leituraInt("Deseja ver tabela ordenada de forma descendente? \n1-Sim\n2-Nao");
        if(op == 1) {
            lista = gereEncomenda.listarEncPendentes("desc");
        }else {
            lista = gereEncomenda.listarEncPendentes("asc");
        }

        System.out.println("Lista de Utilizadores:");
        System.out.println("Identificador | custo | estado");
        System.out.println(lista);
    }

    /**
     * Metodo pesquisarEncomendasCliente, metodo que permite a pesquisa de encomendas por Estado Cliente ou Data
     */

    private static void pesquisarEncomendasCliente() {
        String lista = "";
        String pesquisa = "";
        int op = leituraInt("Deseja pesquisar por \n1-Identificador\n2-Estado\n3-Cliente\n4-Data");

        switch (op) {
            case 1:
                pesquisa = leituraString("Qual o Identificador que deseja pesquisar");
                lista = gereEncomenda.pesquisarEncomendaIdentificador("identificador", pesquisa, null);
                break;

            case 2:
                pesquisa = leituraString("Qual o Estado que deseja pesquisar");
                lista = gereEncomenda.pesquisarEncomendaIdentificador("Estado", pesquisa, null);
                break;

            case 3:
                pesquisa = leituraString("Qual o nome do cliente que deseja pesquisar");
                lista = gereEncomenda.pesquisarEncomendaIdentificador("cliente", pesquisa, null);
                break;

            case 4:
                String tempDataDeInicio = leituraString("Data de pesquisa dd/MM/YYYY: ");
                Date dataDeCriacao = leituraDate(tempDataDeInicio);
                if (dataDeCriacao != null) {
                    gereEncomenda.pesquisarEncomendaIdentificador("data", pesquisa, dataDeCriacao);
                }

        }

        System.out.println("Encomendas encontradas:");
        System.out.println("identificador | data de criacao | estado");
        System.out.println(lista);
    }

    /**
     * Metodo pesquisarEncomendasTempo, metodo que permite pesquisar encomendas feitas durante 2 dias diferentes
     */

    private static void pesquisarEncomendasTempo() {
        String lista = "";
        String tempData1 = leituraString("Data de pesquisa dd/MM/YYYY: ");
        Date data1 = leituraDate(tempData1);

        String tempData2 = leituraString("Data de pesquisa dd/MM/YYYY: ");
        Date data2 = leituraDate(tempData2);

        lista = gereEncomenda.pesquisarEncomendaDatas(data1, data2);


        System.out.println("Encomendas encontradas:");
        System.out.println("Identificador | custo | estado");
        System.out.println(lista);
    }

    /**
     * Metodo pedidoRemocaoConta, metodo que permite qualquer utilizador de remover a sua conta, isto enviará um pedido ao gestor para a conta ser removida
     */

    private static void pedidoRemocaoConta() {
        int op = leituraInt("Deseja mesmo remover sua conta? Isso nao significara que dados de compras serao apagados\n\n1- Sim\n2- Nao");

        if(op == 1) {
            if(gereUtilizador.updateUtilizador("gestor", "Remover", null, "estado", utilizador.getLogin())) {
                System.out.println("Aguarde que um gestor aceite o seu pedido");
            }else{
                System.out.println("Erro ao tentar remover a conta");
            }

        }

    }

    /**
     * Metodo verPedidoRemocaoConta, metodo que permite Gestores de aceitar ou nao o pedido de remocao da conta, caso o pedido seja aceite apagará informacoes como
     * nome, email, login
     */

    private static void verPedidoRemocaoConta() {
        String lista = gereUtilizador.contasInativas("Remover");
        System.out.println("Pedidos de Remocao conta:");
        System.out.println("Nome | Login | Tipo");
        System.out.println(lista);
        String login;

        int op = leituraInt("Deseja remover a conta?\n1- Sim\n2- Nao");

        if(op == 1) {
            login = leituraString("Indique o login da conta que pretende remover");
            gereUtilizador.updateUtilizador("gestor", "REMOVIDO", null, "nome", login);
            gereUtilizador.updateUtilizador("gestor", "REMOVIDO", null, "login", login);
            gereUtilizador.updateUtilizador("gestor", "REMOVIDO@REMOVIDO.REMOVIDO", null, "email", login);
            gereUtilizador.updateUtilizador("gestor", "REMOVIDO", null, "estado", login);

        }
    }

    /**
     * Metodo clienteListarEncomenda, metodo que permite clientes de listarem suas encomendas
     */

    private static void clienteListarEncomenda() {
        String lista = "";
        int op = leituraInt("Deseja ver tabela ordenada de forma descendente? \n1-Sim\n2-Nao");
        String sentido;
        if(op == 1) {
            sentido = "desc";
        }else {
            sentido = "asc";
        }
        int tipo = leituraInt("Deseja ordernar por: \n1- Data de criacao \n2- identificador?");

        if(tipo == 1) {
            lista = gereUtilizador.listarEncomendaCliente(utilizador, "data", sentido);
        }else if(tipo == 2) {
            lista = gereUtilizador.listarEncomendaCliente(utilizador, "identificador", sentido);
        }

        System.out.println("Lista das suas Encomendas:");
        System.out.println("Identificador | custo | estado");
        System.out.println(lista);
    }

    /**
     * Metodo clientePesquisarEncomendas, metodo que permite clientes de pesquisarem suas encomendas
     */

    private static void clientePesquisarEncomendas() {
        String lista = "";
        String pesquisa = "";
        int op = leituraInt("Deseja pesquisar por \n1- Identificador\n2- Estado\n3- Data");

        switch (op) {
            case 1:
                pesquisa = leituraString("Qual o Identificador que deseja pesquisar");
                lista = gereUtilizador.pesquisarEncomendaCliente("identificador", pesquisa, utilizador, null);
                break;

            case 2:
                pesquisa = leituraString("Qual o Estado que deseja pesquisar");
                lista = gereUtilizador.pesquisarEncomendaCliente("estado", pesquisa, utilizador, null);
                break;

            case 3:
                String tempData2 = leituraString("Data de pesquisa dd/MM/YYYY: ");
                Date data2 = leituraDate(tempData2);
                lista = gereUtilizador.pesquisarEncomendaCliente("datacriacao", null, utilizador, data2);
                break;

        }

        System.out.println("Encomendas encontradas:");
        System.out.println("identificador | data de criacao | estado");
        System.out.println(lista);
    }

    /**
     * Metodo funcionarioListarEncomenda, metodo que permite Funcionarios de listar suas encomendas
     */

    private static void funcionarioListarEncomenda() {
        String lista = "";
        int op = leituraInt("Deseja ver tabela ordenada de forma descendente? \n1-Sim\n2-Nao");
        String sentido;
        if(op == 1) {
            sentido = "desc";
        }else {
            sentido = "asc";
        }
        int tipo = leituraInt("Deseja ordernar por: \n1- Data de criacao \n2- identificador?");

        if(tipo == 1) {
            lista = gereUtilizador.listarEncomendaFuncionario(utilizador, "data", sentido);
        }else if(tipo == 2) {
            lista = gereUtilizador.listarEncomendaFuncionario(utilizador, "identificador", sentido);
        }

        System.out.println("Lista das suas Encomendas:");
        System.out.println("Identificador | custo | estado");
        System.out.println(lista);
    }

    /**
     * Metodo funcionarioPesquisarEncomendas, metodo que permite Funcionarios de listar suas encomendas
     */

    private static void funcionarioPesquisarEncomendas() {
        String lista = "";
        String pesquisa = "";
        int op = leituraInt("Deseja pesquisar por \n1- Identificador\n2- Estado\n3- Data");

        switch (op) {
            case 1:
                pesquisa = leituraString("Qual o Identificador que deseja pesquisar");
                lista = gereUtilizador.pesquisarEncomendaFuncionario("identificador", pesquisa, utilizador, null);
                break;

            case 2:
                pesquisa = leituraString("Qual o Estado que deseja pesquisar");
                lista = gereUtilizador.pesquisarEncomendaFuncionario("estado", pesquisa, utilizador, null);
                break;

            case 3:
                String tempData2 = leituraString("Data de pesquisa dd/MM/YYYY: ");
                Date data2 = leituraDate(tempData2);
                lista = gereUtilizador.pesquisarEncomendaFuncionario("datacriacao", null, utilizador, data2);
                break;

        }

        System.out.println("Encomendas encontradas:");
        System.out.println("identificador | data de criacao | estado");
        System.out.println(lista);
    }

    /**
     * Metodo pesquisarBaixoStock, metodo que permite Funcionarios pesquisarem produtos com stock inferior a um certo numero
     */

    private static void pesquisarBaixoStock () {
        String lista = "";
        int valor = leituraInt("Deseja pesquisar produtos com stock inferior a ? ");

        lista = gereProduto.pesquisarStock(valor);


        System.out.println("Encomendas encontradas:");
        System.out.println("SKU |      | stock");
        System.out.println(lista);
    }

    /**
     * Metodo listarCategoria, metodo que permite utilizadores de listar produtos por categoria ou designacao
     */

    private static void listarCategoria() {
        String lista = "";
        String sentido = "";
        int op = leituraInt("Deseja ver tabela ordenada de forma descendente? \n1-Sim\n2-Nao");
        if(op == 1) {
            sentido = "desc";
        }else {
            sentido = "asc";
        }

        int tipo = leituraInt("Deseja listar por \n1- designacao\n2- categoria");

        switch (tipo) {
            case 1:
                lista = gereProduto.listarProduto("designacao", sentido);
                break;

            case 2:
                lista = gereProduto.listarProduto("categoria", sentido);
                break;
        }

        System.out.println("Produtos encontrados:");
        System.out.println("SKU    | designacao | Categoria");
        System.out.println(lista);
    }

    /**
     * Metodo pesquisarProduto, metodo que permite utilizadores de pesquisarem um produto por Categoria ou Designacao
     */

    private static void pesquisarProduto() {
        String lista = "";
        String pesquisa = "";
        int op = leituraInt("Deseja pesquisar por \n1- designacao\n2- Categoria\n3- Sair");

        switch (op) {
            case 1:
                pesquisa = leituraString("Qual a Designacao que deseja pesquisar");
                lista = gereProduto.pesquisarProduto("designacao", pesquisa);
                break;

            case 2:
                pesquisa = leituraString("Qual o Estado que deseja pesquisar");
                lista = gereProduto.pesquisarProduto("categoria", pesquisa);
                break;

        }

        System.out.println("Produtos encontrados:");
        System.out.println("SKU    | designacao | Categoria");
        System.out.println(lista);
    }



    /****************************************************************************************************/

    /**
     * Metodo sair, nele informa quanto tempo o programa teve a correr, assim como uma breve mensagem de adeus
     */
    private static void sair() {
        if(utilizador != null) {
            log.inserirLog(utilizador.getLogin(), "Sair");
            System.out.println("Adeus [" + utilizador.getNome() + "]");
        }
        //https://stackoverflow.com/questions/5204051/how-to-calculate-the-running-time-of-my-program/5204075
        long endTime = System.nanoTime();
        long estimatedTime = endTime - startTime;
        SimpleDateFormat finalizacao = new SimpleDateFormat("EEEE; yyyy-MM-dd HH:mm:ss");
        String startTimeFormat = finalizacao.format(initProcess);
        String endTimeFormat = finalizacao.format(System.currentTimeMillis());
        long miliseconds = (estimatedTime / 1000000);
        long seconds = (estimatedTime / 1000000000) % 60;
        long minutes = (estimatedTime / 60000000000l) % 60;
        long hour = (estimatedTime / 3600000000000l) % 60;
        System.out.println("Inicio do processo: " + startTimeFormat);
        System.out.println("Fim do processo: " + endTimeFormat);
        System.out.println("Tempo de execucao: " + miliseconds + " Milissegundos (" + seconds + " Segundos; " + minutes + " Minutos; " + hour + " Horas)");


        DB.closeConnection();
        System.exit(0);
    }

    /**
     * metodo erro(), com mensagem padrão de valor invalido
     */
    private static void erro() {
        System.out.println("inseriu um valor invalido!!");
    }

    /****************************** Verificação e Criação especifica ***********************************************/


    /**
     * Metodo de verificacao de email, copiado de um video no youtube em que explica a utilização de REGEX PATTERN e MATCHER
     * @param email
     * @return boolean verificao
     */

    private static boolean verificarEmail(String email) {

        // fonte https://www.youtube.com/watch?v=OOdO785p3Qo

        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailRegex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(email);
        return matcher.find();
    }

    /**
     * Metodo de verificacao se contacto é correto ou o contribuinte, dependendo do valor do tipo
     * @param verifica
     * @param tipo
     * @return boolean verificacao
     */

    private static boolean verificaNumero(long verifica, String tipo) {
        if(tipo.equalsIgnoreCase("contribuinte")) {
            if(verifica <= 999999999) {
                return true;
            }else {
                return false;
            }
        }

        if (tipo.equalsIgnoreCase("contacto")) {
            if(verifica <= 999999999 && verifica >= 900000000) {
                return true;
            }else if (verifica <= 200000000 && verifica >= 299999999) {
                return true;
            }else if (verifica <= 300000000 && verifica >= 399999999) {
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    /**
     * Metodo
     * @return long sku
     */

    private static long criarSku() {
        long sku = new Random().nextInt(1000000);
        while(!gereProduto.verificarSku(sku)) {
            sku = new Random().nextInt(1000000);
        }
        return sku;
    }

    /**
     * Metodo identificadorCompra que cria um novo identificador de compra
     * @return String data;
     */

    private static String identificadorCompra() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        long numEncomenda = gereEncomenda.ultimoID() + 1;
        String data = sdf1.format(timestamp);
        data = numEncomenda + data;

        return data;
    }

    /****************************** leitura do utilizador *************************************/

    /**
     * Metodo de leitura inteiro disponibilizado pelo professor
     * @param aMsg
     * @return
     */
    private static int leituraInt(String aMsg) {
        System.out.println(aMsg);
        return LeituraDados.leituraInt();
    }

    /**
     * Metodo de leitura String disponibilizado pelo professor
     * @param aMsg
     * @return
     */

    private static String leituraString(String aMsg) {
        System.out.println(aMsg);
        return LeituraDados.leituraString();
    }

    /**
     * Metodo de leitura float disponibilizado pelo professor
     * @param aMsg
     * @return
     */

    private static float leituraFloat(String aMsg) {
        System.out.println(aMsg);
        return LeituraDados.leituraFloat();
    }

    /**
     * Metodo de leitura char disponibilizado pelo professor
     * @param aMsg
     * @return
     */

    private static char leituraChar(String aMsg) {
        System.out.println(aMsg);
        return LeituraDados.leituraChar();
    }


    /**
     * Metodo de leitura long disponibilizado pelo professor
     * @param aMsg
     * @return
     */

    private static long leituraLong(String aMsg) {
        System.out.println(aMsg);
        return LeituraDados.leituraLong();
    }

    /**
     * Metodo criado para a leitura de Data, converte String em tipo Date
     * @param data
     * @return
     */

    private static Date leituraDate(String data)  {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            return date;
        } catch (ParseException e) {
            System.out.println("Introduziu uma data inválida");
        }
        return null;
    }



}
package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import UI.Cliente.MenuClienteUI;
import UI.Cliente.MenuPesquisaListagemClienteUI;
import UI.Cliente.NovaCompraUI;
import UI.Funcionario.*;
import UI.Gestor.*;
import UI.Listagens.*;
import UI.Main.*;
import UI.Pesquisas.*;
import db.*;
import entities.*;

import javax.swing.*;

/**
 * Classe Main, classe principal em que o programa começa a correr, contem toda a troca de informações do software
 *
 * @author Leonardo Menezes Franco - 2016053468
 **/
public class Main extends WindowAdapter implements ActionListener {

    private static Main main = null;
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat(("yyyyMMddHHmmss"));
    private static GereUtilizador gereUtilizador;
    private static GereProduto gereProduto;
    private static GereEncomenda gereEncomenda;
    private static Utilizador utilizador = null;
    private static Funcionario funcionario = null;
    private static Cliente cliente = null;
    private static Notificacao notificacao = null;
    private static Log log;
    private static long startTime = System.nanoTime();
    private static final long initProcess = System.currentTimeMillis();
    private static MainUI mainUI = null;
    private static LoginUI loginUI = null;
    private static RegisterUI registerUI = null;
    private static MenuGestorUI menuGestorUI = null;
    private static MenuAlterarDadosUI menuAlterarDadosUI = null;
    private static MenuArmazenistaUI menuArmazenistaUI = null;
    private static MenuClienteUI menuClienteUI = null;
    private static MenuEstafetaUI menuEstafetaUI = null;
    private static MenuPesquisaListagemClienteUI menuPesquisaListagemClienteUI = null;
    private static MenuPesquisaListagemFuncionarioUI menuPesquisaListagemFuncionarioUI = null;
    private static MenuPesquisaListagemGestorUI menuPesquisaListagemGestorUI = null;
    private static NovoGestorUI novoGestorUI = null;
    private static LogUI logUI = null;
    private static AlterarDadosGestorUI alterarDadosGestorUI = null;
    private static AlterarDadosClienteUI alterarDadosClienteUI = null;
    private static AlterarDadosFuncionarioUI alterarDadosFuncionarioUI = null;
    private static AtivarContaUI ativarContaUI = null;
    private static NovoProdutoUI novoProdutoUI = null;
    private static NovaCompraUI novaCompraUI = null;
    private static AceitarEncomendaUI aceitarEncomendaUI = null;
    private static PrepararNovaEncomendaUI prepararNovaEncomendaUI = null;
    private static EncomendaEstafetaUI encomendaEstafetaUI = null;
    private static EncomendaEntregueUI encomendaEntregueUI = null;
    private static NotificacaoUI notificacaoUI = null;
    private static AtribuirEncomendaUI atribuirEncomendaUI = null;
    private static ListarUtilizadoresUI listarUtilizadoresUI = null;
    private static PesquisarUtilizadoresUI pesquisarUtilizadoresUI = null;
    private static ListarEncomendasUtilizadoresUI listarEncomendasUtilizadoresUI = null;
    private static ListarEncomendasNEntreguesUI listarEncomendasNEntreguesUI = null;
    private static PesquisarEncomendasClienteUI pesquisarEncomendasClienteUI = null;
    private static PesquisarEncomendasTempoUI pesquisarEncomendasTempoUI = null;
    private static PedidoRemocaoContaUI pedidoRemocaoContaUI = null;
    private static VerPedidoRemocaoContaUI verPedidoRemocaoContaUI = null;
    private static ClienteListarEncomendaUI clienteListarEncomendaUI = null;
    private static ClientePesquisarEncomendasUI clientePesquisarEncomendasUI = null;
    private static ListarCategoriaUI listarCategoriaUI = null;
    private static PesquisarProdutoUI pesquisarProdutoUI = null;
    private static PesquisarBaixoStockUI pesquisarBaixoStockUI = null;
    private static PesquisarEncomendaFuncionarioUI pesquisarEncomendaFuncionarioUI = null;
    private static FuncionarioListarEncomendaUI funcionarioListarEncomendaUI = null;

    /**
     * Metodo main, primeiro metodo a correr, nela é chamada o metodo Main()
     *
     * @param args
     **/
    public static void main(String[] args) {
        main = new Main();

    }

    /**
     * public Main, metodo que é a raiz do trabalho, nela é iniciada a base de dados e abre a primeira JFrame mainUI
     */
    public Main() {
        mainUI = new MainUI(this);
        Connection conn = DB.getConnection();
        gereUtilizador = new GereUtilizador(conn);
        gereProduto = new GereProduto(conn);
        gereEncomenda = new GereEncomenda(conn);
        notificacao = new Notificacao(conn);
        log = new Log(conn);
    }

    /**
     * Metodo de Login, metodo de serve como autenticacao de um Utilizador a sua conta, precisa de introduzir login e password e estes sejam compativeis com
     * informacao na base de dados, posteriormente informa se existem notificacoes assim como chamada de menus principais referidos anteriormente
     * @param login String login que contem o Login do utilizador que deseja entrar autenticar no software
     * @param password String password que contem a password do utilizador que deseja entrar no software
     */
    private static void login(String login, String password) {
        utilizador = gereUtilizador.login(login, password);
        if (utilizador == null) {
            errorDialogs("Login e Password errados");
        } else if (utilizador.getEstado().equalsIgnoreCase("Inativo")) {
            errorDialogs("Sua conta ainda não foi ativa");
        } else if (utilizador.getEstado().equalsIgnoreCase("Ativo") && utilizador.getTipo().equalsIgnoreCase("Gestor")) {
            log.inserirLog(utilizador.getLogin(), "Login");
            notificationDialogs("Bem-vindo [" + utilizador.getNome() + "]");
            if (gereUtilizador.contasInativas("Remover").length() > 0) {
                notificationDialogs("Existem novos utilizadores por Remover");
            }
            if (gereUtilizador.contasInativas("inativo").length() > 0) {
                notificationDialogs("Existem novos utilizadores por aceitar");
            }
            if (gereEncomenda.EncomendaEstado("iniciada").length() > 0) {
                notificationDialogs("Existem novos pedidos de encomenda");
            }
            if (gereEncomenda.EncomendaEstado("terminada").length() > 0) {
                notificationDialogs("Uma encomenda foi terminada");
            }
            if (gereEncomenda.encomendaExpirada().length() > 0) {
                String lista = gereEncomenda.encomendaExpirada();
                notificationDialogs("Encomendas com o identificador " + lista + " ainda nao foram terminadas");
            }
            menuGestorUI = new MenuGestorUI(main);
            loginUI.setVisible(false);
        } else if (utilizador.getEstado().equalsIgnoreCase("Ativo") && utilizador.getTipo().equalsIgnoreCase("cliente")) {
            log.inserirLog(utilizador.getLogin(), "Login");
            notificationDialogs("Bem-vindo [" + utilizador.getNome() + "]");
            String noti = notificacao.lerNotificacao(gereUtilizador.u_id(utilizador.getLogin()));
            if (noti.length() > 0) {
                notificationDialogs("existem notificacoes por ler");
            }
            menuClienteUI = new MenuClienteUI(main);
            loginUI.setVisible(false);
        } else if (utilizador.getEstado().equalsIgnoreCase("Ativo") && utilizador.getTipo().equalsIgnoreCase("funcionario")) {
            funcionario = gereUtilizador.loginFunc(utilizador);
            log.inserirLog(utilizador.getLogin(), "Login");
            if (funcionario.getEspecializacao().equalsIgnoreCase("armazenista")) {
                if (gereProduto.baixoStock().length() > 0) {
                    String lista = gereProduto.baixoStock();
                    notificationDialogs("Os artigos com SKU: " + lista + " tem stock inferior a 10");
                }
                notificationDialogs("Bem-vindo [" + funcionario.getNome() + "]");
                String noti = notificacao.lerNotificacao(gereUtilizador.u_id(utilizador.getLogin()));
                if (noti.length() > 0) {
                    notificationDialogs("existem notificacoes por ler");
                }
                menuArmazenistaUI = new MenuArmazenistaUI(main);
                loginUI.setVisible(false);
            } else if (funcionario.getEspecializacao().equalsIgnoreCase("estafeta")) {
                log.inserirLog(utilizador.getLogin(), "Login");
                notificationDialogs("Bem-vindo [" + funcionario.getNome() + "]");
                String noti = notificacao.lerNotificacao(gereUtilizador.u_id(utilizador.getLogin()));
                if (noti.length() > 0) {
                    notificationDialogs("existem notificacoes por ler");
                }
                menuEstafetaUI = new MenuEstafetaUI(main);
                loginUI.setVisible(false);

            }
        } else if (utilizador.getEstado().equalsIgnoreCase("Reprovado")) {
            errorDialogs("Infelizmente sua conta foi reprovada por um gestor, tente entrar em contacto");
        }


    }



    /**
     * Método de registo, permite o utilizador criar uma conta informando todas as variaveis necessarias, essas variaveis estão contidas dentro do array Form
     * @param form Array formado por todas as variaveis de criação de conta, Login, Password, Nome, Email, Tipo, Morada, Especializacao, Data, Numero de contribuinte, Contacto,
     * @return Devolve ao metodo que o chamou um boolean True se falhou False se conseguiu criar conta
     */
    private static boolean registo(String[] form) {

        String login = form[0];
        String password = form[1];
        String nome = form[2];
        String email = form[3];
        String tipo = form[4];
        String morada = form[7];
        String espec = form[8];
        String data = form[9];
        long numeroContribuinte = 0;
        if (form[5] != null) {
            numeroContribuinte = Long.parseLong(form[5]);
        }
        long contacto = 0;
        if (form[6] != null) {
            contacto = Long.parseLong(form[6]);
        }


        if (!gereUtilizador.verificaDuplicado(login, "login")) {
            errorDialogs("Login já existente");
            return true;
        }
        if (!gereUtilizador.verificaDuplicado(email, "email") || !verificarEmail(email)) {
            if (!gereUtilizador.verificaDuplicado(email, "email")) {
                errorDialogs("Email já existente");
                return true;
            } else if (!verificarEmail(email)) {
                errorDialogs("Email não válido");
                return true;
            }
        }
        if (gereUtilizador.verificarUtilizador()) {
            if (tipo.equalsIgnoreCase("Gestor")) {
                Gestor gestor = new Gestor(nome, login, password, "inativo", email, "gestor");
                if (gereUtilizador.inserirUtilizador(gestor)) {
                    notificationDialogs("Gestor criado com sucesso");
                    log.inserirLog(login, "criou conta");
                    return false;
                } else
                    errorDialogs("Error ao criar Gestor");
                return true;
            }
            if (tipo.equalsIgnoreCase("Cliente")) {
                if (!verificaNumero(numeroContribuinte, "contribuinte")) {
                    errorDialogs("Contribuinte não válido");
                    return true;
                }
                if (!verificaNumero(contacto, "contacto")) {
                    errorDialogs("Contacto não válido");
                    return true;
                }
                Cliente cliente = new Cliente(nome, login, password, "inativo", email, "cliente", numeroContribuinte, contacto, morada);
                if (gereUtilizador.inserirCliente(cliente)) {
                    notificationDialogs("Cliente criado com sucesso");
                    log.inserirLog(login, "criou conta");
                    return false;
                } else
                    errorDialogs("Error ao criar Cliente");
                return true;
            }
            if (tipo.equalsIgnoreCase("Funcionario")) {
                if (!verificaNumero(numeroContribuinte, "contribuinte")) {
                    errorDialogs("Contribuinte não válido");
                    return true;
                }
                if (!verificaNumero(contacto, "contacto")) {
                    errorDialogs("Contacto não válido");
                    return true;
                }
                //Tens de corrigir a data
                Date dataDeInicio = leituraDate(data);
                if (dataDeInicio != null) {
                    Funcionario funcionario = new Funcionario(nome, login, password, "inativo", email, "funcionario", numeroContribuinte, contacto, morada, espec, dataDeInicio);
                    if (gereUtilizador.inserirFuncionario(funcionario)) {
                        notificationDialogs("Funcionario criado com sucesso");
                        log.inserirLog(login, "criou conta");
                        return false;
                    }
                } else if (dataDeInicio == null) {
                    errorDialogs("introduziu uma data não valida");
                    return true;
                }
            }
        } else {
            Gestor gestor = new Gestor(nome, login, password, "ativo", email, "gestor");
            if (gereUtilizador.inserirUtilizador(gestor)) {
                notificationDialogs("Gestor criado com sucesso");
                log.inserirLog(login, "criou conta");
                return false;
            } else {
                errorDialogs("Error ao criar Gestor");
                return true;
            }
        }
        return false;
    }


    /**
     * Metodo de criação de um novo gestor.
     * @param form Array form que tem como variaveis Login, Nome, Password, Email do novo Gestor a ser criado
     * @return Devolve ao metodo que o chamou um boolean True se correu bem ou False que houve um erro na criação
     */
    private static boolean novoGestor(String[] form) {

        String login = form[0];
        String password = form[1];
        String nome = form[2];
        String email = form[3];

        if (!gereUtilizador.verificaDuplicado(login, "login")) {
            errorDialogs("Login já existente");
            return true;
        }
        if (!gereUtilizador.verificaDuplicado(email, "email") || !verificarEmail(email)) {
            if (!gereUtilizador.verificaDuplicado(email, "email")) {
                errorDialogs("Email já existente");
                return true;
            } else if (!verificarEmail(email)) {
                errorDialogs("Email não válido");
                return true;
            }
        }
        Gestor gestor = new Gestor(nome, login, password, "ativo", email, "gestor");
        if (gereUtilizador.inserirUtilizador(gestor)) {
            notificationDialogs("Gestor criado com sucesso");
            log.inserirLog(login, "criou conta");
            return false;
        } else {
            errorDialogs("Error ao criar Gestor");
            return true;
        }

    }


    /**
     * Metodo para alteração de dados do Gestor, é enviado um novo formulario que compara os dados anteriores com os novos, caso sejam diferentes atualiza na base de dados os novos dados
     * @param form Array que contem todos as variaveis de utilizador
     * @return Devolve ao metodo que o chamou um boolean True se correu bem ou False que houve um erro na criação
     */
    private static boolean alterarDadosGestor(String[] form) {


        String login = form[0];
        String password = form[1];
        String nome = form[2];
        String email = form[3];

        if (login.equalsIgnoreCase(utilizador.getLogin()) && gereUtilizador.verificaDuplicado(login, "login")) {
            if (gereUtilizador.updateUtilizador(utilizador.getTipo(), login, null, "login", login)) {
                notificationDialogs("Login alterado com sucesso");
                utilizador.setLogin(login);
                log.inserirLog(utilizador.getLogin(), "Alterou Login");
                return true;

            } else {
                errorDialogs("Erro ao alterar o login");
                return false;
            }
        }

        if (!nome.equalsIgnoreCase(utilizador.getNome())) {
            if (gereUtilizador.updateUtilizador(utilizador.getTipo(), nome, null, "nome", login)) {
                notificationDialogs("Nome alterado com sucesso");
                utilizador.setNome(nome);
                log.inserirLog(utilizador.getLogin(), "Alterou Nome");
                return true;

            } else {
                errorDialogs("Erro ao alterar o nome");
                return false;
            }
        }

        if (!password.equalsIgnoreCase(utilizador.getPassword())) {
            if (gereUtilizador.updateUtilizador(utilizador.getTipo(), password, null, "password", login)) {
                notificationDialogs("password alterado com sucesso");
                utilizador.setPassword(password);
                log.inserirLog(utilizador.getLogin(), "Alterou password");
                return true;

            } else {
                errorDialogs("Erro ao alterar o password");
                return false;
            }
        }

        if (!email.equalsIgnoreCase(utilizador.getEmail()) && !verificarEmail(email)) {
            if (!gereUtilizador.verificaDuplicado(email, "email")) {
                errorDialogs("Esse email já foi cadastrado");
                return false;
            } else {
                if (gereUtilizador.updateUtilizador(utilizador.getTipo(), email, null, "email", login)) {
                    notificationDialogs("Email alterado com sucesso");
                    utilizador.setEmail(email);
                    return true;
                } else {
                    errorDialogs("Erro ao alterar o Email");
                    return false;

                }
            }
        } else if (verificarEmail(email)) {
            errorDialogs("Email não está correto");
            return false;
        }

        return true;
    }

    /**
     * Metodo que possibilita os gestores de verificar os Logs da aplicação ou pesquisar por um utilizador especifico
     * @param login Caso necessita pesquisar um utilizador por Login este deve ser apresentado
     * @return retorna a lista de Logs da base de dados
     */
    private static String verLog(String login) {
        if (login == null) {
            return log.listaLog();
        } else {
            return log.listaLog(login);
        }

    }

    /**
     * Metodo para alteração de dados do Cliente, é enviado um novo formulario que compara os dados anteriores com os novos, caso sejam diferentes atualiza na base de dados os novos dados
     * @param form Array que contem todos as variaveis de Cliente
     * @return Devolve ao metodo que o chamou um boolean True se correu bem ou False que houve um erro na criação
     */
    private static boolean alterarDadosCliente(String[] form) {

        String login = form[0];
        String password = form[1];
        String nome = form[2];
        String email = form[3];
        long numeroContribuinte = 0;
        if (form[4] != null) {
            numeroContribuinte = Long.parseLong(form[4]);
        }
        long contacto = 0;
        if (form[5] != null) {
            contacto = Long.parseLong(form[5]);
        }
        String morada = form[6];


        if (login != utilizador.getLogin() && gereUtilizador.verificaDuplicado(login, "login")) {
            if (gereUtilizador.updateUtilizador(utilizador.getTipo(), login, null, "login", login)) {
                notificationDialogs("Login alterado com sucesso");
                utilizador.setLogin(login);
                log.inserirLog(utilizador.getLogin(), "Alterou Login");
                return true;

            } else {
                errorDialogs("Erro ao alterar o login");
                return false;
            }
        }

        if (nome != utilizador.getNome()) {
            if (gereUtilizador.updateUtilizador(utilizador.getTipo(), nome, null, "nome", login)) {
                System.out.println("Nome alterado com sucesso");
                utilizador.setNome(nome);
                log.inserirLog(utilizador.getLogin(), "Alterou Nome");
                return true;

            } else {
                System.out.println("Erro ao alterar o nome");
                return false;
            }
        }

        if (password != utilizador.getPassword()) {
            if (gereUtilizador.updateUtilizador(utilizador.getTipo(), password, null, "password", login)) {
                System.out.println("password alterado com sucesso");
                utilizador.setPassword(password);
                log.inserirLog(utilizador.getLogin(), "Alterou password");
                return true;

            } else {
                System.out.println("Erro ao alterar o nome");
                return false;
            }
        }

        if (email != utilizador.getEmail() && verificarEmail(email)) {
            if (!gereUtilizador.verificaDuplicado(email, "email")) {
                errorDialogs("Esse email já foi cadastrado");
                return false;
            } else {
                if (gereUtilizador.updateUtilizador(utilizador.getTipo(), email, null, "email", login)) {
                    System.out.println("Email alterado com sucesso");
                    utilizador.setEmail(email);
                    return true;
                } else {
                    System.out.println("Erro ao alterar Email");
                    return false;

                }
            }
        } else if (!verificarEmail(email)) {
            errorDialogs("Email não está correto");
            return false;
        }

        if (numeroContribuinte != cliente.getNumeroContribuinte() && verificaNumero(numeroContribuinte, "contribuinte")) {
            if (gereUtilizador.updateUtilizador("cliente", null, numeroContribuinte, "contribuinte", login)) {
                System.out.println("Contribuinte alterado com sucesso");
                return true;
            } else {
                System.out.println("Erro ao alterar Contribuinte");
                return false;
            }
        }

        if (contacto != cliente.getContacto() && verificaNumero(contacto, "contacto")) {
            if (gereUtilizador.updateUtilizador("cliente", null, contacto, "contacto", login)) {
                System.out.println("Contacto alterado com sucesso");
                return true;
            } else {
                System.out.println("Erro ao alterar Contacto");
                return false;
            }
        }

        if (morada != cliente.getMorada()) {
            if (gereUtilizador.updateUtilizador("cliente", morada, null, "morada", login)) {
                System.out.println("Morada Alterada com sucesso");
                return true;
            } else {
                System.out.println("Erro ao alterar morada");
                return false;
            }
        }
        return false;
    }

    /**
     * Metodo para alteração de dados do Funcionario, é enviado um novo formulario que compara os dados anteriores com os novos, caso sejam diferentes atualiza na base de dados os novos dados
     * @param form Array que contem todos as variaveis de Cliente
     * @return Devolve ao metodo que o chamou um boolean True se correu bem ou False que houve um erro na criação
     */
    private static boolean alterarDadosFuncionario(String[] form) {

        String login = form[0];
        String password = form[1];
        String nome = form[2];
        String email = form[3];
        long numeroContribuinte = 0;
        if (form[4] != null) {
            numeroContribuinte = Long.parseLong(form[5]);
        }
        long contacto = 0;
        if (form[5] != null) {
            contacto = Long.parseLong(form[6]);
        }
        String morada = form[6];


        if (login != funcionario.getLogin() && gereUtilizador.verificaDuplicado(login, "login")) {
            if (gereUtilizador.updateUtilizador(funcionario.getTipo(), login, null, "login", login)) {
                notificationDialogs("Login alterado com sucesso");
                utilizador.setLogin(login);
                log.inserirLog(utilizador.getLogin(), "Alterou Login");
                return true;

            } else {
                errorDialogs("Erro ao alterar o login");
                return false;
            }
        }

        if (nome != funcionario.getNome()) {
            if (gereUtilizador.updateUtilizador(funcionario.getTipo(), nome, null, "nome", login)) {
                System.out.println("Nome alterado com sucesso");
                funcionario.setNome(nome);
                log.inserirLog(funcionario.getLogin(), "Alterou Nome");
                return true;

            } else {
                System.out.println("Erro ao alterar o nome");
                return false;
            }
        }

        if (password != funcionario.getPassword()) {
            if (gereUtilizador.updateUtilizador(funcionario.getTipo(), password, null, "password", login)) {
                System.out.println("password alterado com sucesso");
                funcionario.setPassword(password);
                log.inserirLog(funcionario.getLogin(), "Alterou password");
                return true;

            } else {
                System.out.println("Erro ao alterar o nome");
                return false;
            }
        }

        if (email != funcionario.getEmail() && verificarEmail(email)) {
            if (!gereUtilizador.verificaDuplicado(email, "email")) {
                errorDialogs("Esse email já foi cadastrado");
                return false;
            } else {
                if (gereUtilizador.updateUtilizador(funcionario.getTipo(), email, null, "email", login)) {
                    System.out.println("Email alterado com sucesso");
                    funcionario.setEmail(email);
                    return true;
                } else {
                    System.out.println("Erro ao alterar Email");
                    return false;

                }
            }
        } else if (!verificarEmail(email)) {
            errorDialogs("Email não está correto");
            return false;
        }

        if (numeroContribuinte != funcionario.getNumeroContribuinte() && verificaNumero(numeroContribuinte, "contribuinte")) {
            if (gereUtilizador.updateUtilizador("cliente", null, numeroContribuinte, "contribuinte", login)) {
                System.out.println("Contribuinte alterado com sucesso");
                return true;
            } else {
                System.out.println("Erro ao alterar Contribuinte");
                return false;
            }
        }

        if (contacto != cliente.getContacto() && verificaNumero(contacto, "contacto")) {
            if (gereUtilizador.updateUtilizador("cliente", null, contacto, "contacto", login)) {
                System.out.println("Contacto alterado com sucesso");
                return true;
            } else {
                System.out.println("Erro ao alterar Contacto");
                return false;
            }
        }

        if (morada != funcionario.getMorada()) {
            if (gereUtilizador.updateUtilizador("cliente", morada, null, "morada", login)) {
                System.out.println("Morada Alterada com sucesso");
                return true;
            } else {
                System.out.println("Erro ao alterar morada");
                return false;
            }
        }
        return false;
    }

    /**
     * Metodo de ativação de conta, serve para alterar as contas de Inativo para Ativo ou Reprovado
     * @param login Login do utilizador a ser alterada a conta
     * @param tipo Tipo pode ser Ativar, Recusar ou Inativar dependendo do desejo do utilizador
     * @return Devolve ao metodo que o chamou um boolean True se correu bem ou False que houve um erro na criação
     */
    private static boolean ativarContas(String login, String tipo) {

//        int op = leituraInt("\n\n\nPretende 1-ativar, 2-reprovar, 3-inativar alguma conta?\n\n\n4- para voltar ao menu anterior");

        if (tipo.equalsIgnoreCase("ativar")) {
            if (gereUtilizador.updateUtilizador(utilizador.getTipo(), "ativo", null, "estado", login)) {
                notificationDialogs(login + " passou a ter a conta ativa!");
                return true;
            } else {
                errorDialogs("Introduziu um Login invalido");
                return false;
            }
        } else if (tipo.equalsIgnoreCase("recusar")) {
            if (gereUtilizador.updateUtilizador(utilizador.getTipo(), "reprovado", null, "estado", login)) {
                notificationDialogs(login + " passou a ter a conta reprovada!");
                return true;

            } else {
                errorDialogs("Introduziu um Login invalido");
                return false;
            }
        } else if (tipo.equalsIgnoreCase("inativar")) {
            if (gereUtilizador.updateUtilizador(utilizador.getTipo(), "inativo", null, "estado", login)) {
                notificationDialogs(login + " passou a ter a conta inativa!");
                return true;
            } else {
                errorDialogs("Introduziu um Login invalido");
                return false;
            }

        } else {
            errorDialogs("Introduziu um Login invalido");
            return false;
        }
    }


    /**
     * Metodo de criação de um novoProduto, este produto é adicionado na base de dados podendo assim um cliente comprar ou não
     * @param form Array que contem todas as variaveis necessarias para a criação de um novo produto
     * @return Devolve ao metodo que o chamou um boolean True se correu bem ou False que houve um erro na criação
     */
    private static boolean novoProduto(String[] form) {

        String designacao = form[0];
        String fabricante = form[1];
        int peso = 0;
        if (form[2] != null) {
            peso = Integer.parseInt(form[2]);
        }
        float preco = 0;
        if (form[3] != null) {
            preco = Float.parseFloat(form[3]);
        }

        long SKU = criarSku();
        int lote = 0;
        if (form[4] != null) {
            lote = Integer.parseInt(form[4]);
        }
        String tempdataDeProd = form[8];
        Date dataDeProd = null;
        if (tempdataDeProd != null) {
            dataDeProd = leituraDate(tempdataDeProd);
        }
        Date dataDeValidade = null;
        String tempDataDeValidade = form[9];

        if (tempDataDeValidade != null) {
            dataDeValidade = leituraDate(tempDataDeValidade);

        }
        int stock = 0;
        if (form[5] != null) {
            stock = Integer.parseInt(form[5]);
        }

        String categoria = form[6];
        String classificacao = form[7];
        Produto produto = new Produto(designacao, fabricante, peso, preco, SKU, lote, dataDeProd, dataDeValidade, stock, categoria, classificacao);
        if (gereProduto.inserirProduto(produto)) {
            System.out.println("Produto Inserido com sucesso");
            return true;
        }

        return false;
    }


    /**
     * Metodo de criação de uma novaCompra, este produto é adicionado na base de dados podendo assim um Gestor aceitar a compra
     * @param form contem qual o Stock que deseja comrpar e o SKU do artigo
     */
    private static void novaCompra(String[] form) {
        String identificador = identificadorCompra();
        Date dataCriacao = new Date();
        Encomenda encomenda = new Encomenda(identificador, dataCriacao);
        long stock = 0;

        if (form[1] != null) {
            stock = Long.parseLong(form[1]);
        }
        long SKU = 0;
        if (form[0] != null) {
            SKU = Long.parseLong(form[0]);
        }

        if (gereEncomenda.inserirEncomenda(encomenda) && gereProduto.pesquisarSeExisteStock(stock, SKU)) {
            long idEnc = gereEncomenda.ultimoID();

            long idProd = gereProduto.getIDProduto(SKU);
            long idCliente = gereUtilizador.u_id(utilizador.getLogin());
            if (gereEncomenda.InserirProdEnc(idProd, idEnc) && gereEncomenda.InserirClienteEnc(idCliente, idEnc) && gereProduto.retirarStock(stock, SKU)) {
                notificationDialogs("Compra Feita com sucesso");
            }
        } else {
            errorDialogs("Erro ao efectuar Compra");
        }
    }

    /**
     * Metodo encomendaGestor, possibilita um gestor de alterar o estado a encomenda de iniciada para aceite, em seguida escolhe um armazenista para
     * preparar a encomenda
     * @param identificador parametro identificador serve para identificar qual a encomenda que deve ser aceite
     * @param tipo Serve para marcar a nova encomenda como Aceite, Rejeitado
     * @return Devolve ao metodo que o chamou um boolean True se correu bem ou False que houve um erro na criação
     **/

    private static boolean encomendaGestor(String identificador, String tipo) {


        if (tipo.equalsIgnoreCase("aceitar")) {
            if (gereEncomenda.updateEncomenda(0, null, "aceite", "estado", identificador)) {
                notificationDialogs(identificador + " passou a ser aceite!");
                return true;
            } else {
                System.out.println("Introduziu um identificador invalido");
                return false;
            }
        }

        if (tipo.equalsIgnoreCase("Recusar")) {
            if (gereEncomenda.updateEncomenda(0, null, "rejeitado", "estado", identificador)) {
                notificationDialogs(identificador + " passou a recusada!");
                notificacao.inserirNotificacao("Sua encomenda foi rejeitada por um gestor!");
                notificacao.inserirNotificacaoUtilizador(gereEncomenda.u_id_encomenda(identificador), notificacao.ultimoID());
                return false;
            } else {
                errorDialogs("Introduziu um identificador invalido");
                return false;
            }
        }

        return false;
    }

    /**
     * Metodo encomendaArmazenista, metodo que possibilitida Armazenista de preparar encomenda alterando o seu estado para "preparada"
     * em seguida atribui a encomenda a um estafeta;
     * @param identificador parametro identificador serve para identificar qual a encomenda que deve ser aceite
     * @return Devolve ao metodo que o chamou um boolean True se correu bem ou False que houve um erro na criação
     */

    private static boolean encomendaArmazenista(String identificador) {

        if (gereEncomenda.updateEncomenda(0, null, "preparada", "estado", identificador)) {
            notificationDialogs(identificador + " passou a estar preparada!");
            return true;

        } else {
            errorDialogs("Introduziu um identificador invalido");
            return false;
        }
    }

    /**
     * Metodo que continua do encomendaArmazenista, neste metodo atribui a um estafeta a entrega de uma encomenda
     * @param form Array que contem o identificador e o login do utilizador que deve ser notificado
     */
    private static void atribuirEncomenda(String[] form) {


        String identificador = form[0];
        String login = form[1];

        if (gereEncomenda.updateEncomenda(0, null, "aguarda entrega", "estado", identificador)) {
            notificacao.inserirNotificacao("Existem encomendas por entregar");
            notificacao.inserirNotificacaoUtilizador(gereUtilizador.u_id(login), notificacao.ultimoID());
            notificationDialogs(identificador + " passou a estar preparada!");
        } else {
            errorDialogs("Introduziu um identificador invalido");
        }


    }

    /**
     * Metodo que continua do encomendaGestor, metodo que possibilitida enviar notificacao para um armazenista da necessidade de preparar encomenda
     * @param login parametro de login do armazenista que deve ser notificado
     */

    private static void atribuirArmazenista(String login) {
//        String listaEstafeta = gereUtilizador.procuraEstafeta("armazenista");
        notificacao.inserirNotificacao("Existem encomendas por entregar");
        if (notificacao.inserirNotificacaoUtilizador(gereUtilizador.u_id(login), notificacao.ultimoID())) {
            notificationDialogs(login + " ira ser notificado");
        }


    }

    /**
     * Metodo encomendaEstafeta, metodo que possibilitida Estafeta de alterar o estado da encomenda de em "aguarda entrega" para "em transporte"
     */

    private static void encomendaEstafeta(String identificador) {
        String lista = gereEncomenda.EncomendaEstado("aguarda entrega");


        if (gereEncomenda.updateEncomenda(0, null, "em transporte", "estado", identificador)) {
            System.out.println(identificador + " passou a ser aceite!");
        } else {
            System.out.println("Introduziu um identificador invalido");
        }


    }

    /**
     * Metodo encomendaEstafeta, metodo que possibilitida Estafeta de alterar o estado da encomenda de em "em transporte" para "entregue"
     * @param identificador parametro do identificador da encomenda
     */

    private static void encomendaEntregue(String identificador) {
        String lista = gereEncomenda.EncomendaEstado("em transporte");

        if (gereEncomenda.updateEncomenda(0, null, "entregue", "estado", identificador)) {
            System.out.println(identificador + " passou a ser entregue!");
            notificacao.inserirNotificacao("A sua encomenda já foi entregue");
            notificacao.inserirNotificacaoUtilizador(gereEncomenda.u_id_encomenda(identificador), notificacao.ultimoID());
        } else {
            System.out.println("Introduziu um identificador invalido");
        }

    }

    /**
     * Metodo verNotificacao, metodo que permite utilizadores de ver notificacoes pendentes
     */
    private static void verNotificacao() {
        notificacao.mensagemLida(gereUtilizador.u_id(utilizador.getLogin()));
        log.inserirLog(utilizador.getLogin(), "Viu notificacoes");
    }


    /**
     * metodo pesquisarUtilizadores, metodo que permite gestores de pesquisar um utilizador por Nome, Login ou Tipo
     */

    private static String pesquisarUtilizadores(String[] form) {

        String pesquisa = form[1];
        String tipo = form[0];
        String lista = "";

        if(tipo.equalsIgnoreCase("nome")) {
            lista = gereUtilizador.pesquisarUtilizador("nome", pesquisa);
        }
        if(tipo.equalsIgnoreCase("login")) {
            lista = gereUtilizador.pesquisarUtilizador("login", pesquisa);
        }
        if(tipo.equalsIgnoreCase("tipo")) {
            lista = gereUtilizador.pesquisarUtilizador("tipo", pesquisa);
        }

        return lista;


    }

    /**
     * Metodo listarEncomendasUtilizador, metodo que permite Gestores de listar encomendas por utilizadores ou Data de criacao
     */

    private static String listarEncomendasUtilizador(String[] form, String sentido) {
        String lista = "";
        String tipo = form[0];
        String pesquisa = form[1];



        if(tipo.equalsIgnoreCase("data")) {
            Date data = leituraDate(pesquisa);
            lista = gereEncomenda.listarEncomendas("data", sentido, data);
        }
        if(tipo.equalsIgnoreCase("utilizador")) {
            lista = gereEncomenda.listarEncomendas("utilizador", sentido, null);
        }

        return lista;
    }

    /**
     * Metodo listarEncomendasUtilizador, metodo que permite Gestores de listar encomendas por utilizadores ou Data de criacao
     */

    private static String listarEncomendasNentregues(String sentido) {
        String lista = "";

        if (sentido.equalsIgnoreCase("desc")) {
            lista = gereEncomenda.listarEncPendentes("desc");
        } else if(sentido.equalsIgnoreCase("asc")) {
            lista = gereEncomenda.listarEncPendentes("asc");
        }
        return lista;
    }

    /**
     * Metodo pesquisarEncomendasCliente, metodo que permite a pesquisa de encomendas por Estado Cliente ou Data
     */

    private static String pesquisarEncomendasCliente(String[] form) {
        String lista = "";
        String pesquisa = form[1];
        String tipo = form[0];

        if(tipo.equalsIgnoreCase("identificador")) {
            lista = gereEncomenda.pesquisarEncomendaIdentificador("identificador", pesquisa, null);
        }
        if(tipo.equalsIgnoreCase("Estado")) {
            lista = gereEncomenda.pesquisarEncomendaIdentificador("Estado", pesquisa, null);
        }
        if(tipo.equalsIgnoreCase("cliente")) {
            lista = gereEncomenda.pesquisarEncomendaIdentificador("cliente", pesquisa, null);
        }
        if(tipo.equalsIgnoreCase("data")) {
            Date dataDeCriacao = leituraDate(pesquisa);
            if (dataDeCriacao != null) {
                gereEncomenda.pesquisarEncomendaIdentificador("data", pesquisa, dataDeCriacao);
            }
        }
        return lista;

    }

    /**
     * Metodo pesquisarEncomendasTempo, metodo que permite pesquisar encomendas feitas durante 2 dias diferentes
     */

    private static String pesquisarEncomendasTempo(String[] form) {
        String lista = "";
        String tempData1 = form[0];
        Date data1 = leituraDate(tempData1);

        String tempData2 = form[1];
        Date data2 = leituraDate(tempData2);
        if(data1 != null && data2 != null)
            lista = gereEncomenda.pesquisarEncomendaDatas(data1, data2);


        return lista;
    }

    /**
     * Metodo pedidoRemocaoConta, metodo que permite qualquer utilizador de remover a sua conta, isto enviará um pedido ao gestor para a conta ser removida
     */

    private static void pedidoRemocaoConta() {
            if (gereUtilizador.updateUtilizador("gestor", "Remover", null, "estado", utilizador.getLogin())) {
                notificationDialogs("Aguarde que um gestor aceite o seu pedido");
            } else {
                errorDialogs("Erro ao tentar remover a conta");
            }
    }

    /**
     * Metodo verPedidoRemocaoConta, metodo que permite Gestores de aceitar ou nao o pedido de remocao da conta, caso o pedido seja aceite apagará informacoes como
     * nome, email, login
     */

    private static void verPedidoRemocaoConta(String login) {
            gereUtilizador.updateUtilizador("gestor", "REMOVIDO", null, "nome", login);
            gereUtilizador.updateUtilizador("gestor", "REMOVIDO", null, "login", login);
            gereUtilizador.updateUtilizador("gestor", "REMOVIDO@REMOVIDO.REMOVIDO", null, "email", login);
            gereUtilizador.updateUtilizador("gestor", "REMOVIDO", null, "estado", login);
    }

    /**
     * Metodo clienteListarEncomenda, metodo que permite clientes de listarem suas encomendas
     */

    private static String clienteListarEncomenda(String sentido, String tipo) {
        String lista = "";

        if (tipo.equalsIgnoreCase("Data de Criacao")) {
            lista = gereUtilizador.listarEncomendaCliente(utilizador, "data", sentido);
        } else if (tipo.equalsIgnoreCase("identificador")) {
            lista = gereUtilizador.listarEncomendaCliente(utilizador, "identificador", sentido);
        }
        return lista;
    }

    /**
     * Metodo clientePesquisarEncomendas, metodo que permite clientes de pesquisarem suas encomendas
     */

    private static String clientePesquisarEncomendas(String[] form) {
        String lista = "";
        String pesquisa = form[1];
        String tipo = form[0];

        if(tipo.equalsIgnoreCase("identificador")) {
            lista = gereUtilizador.pesquisarEncomendaCliente("identificador", pesquisa, utilizador, null);
        }

        if(tipo.equalsIgnoreCase("estado")) {
            lista = gereUtilizador.pesquisarEncomendaCliente("estado", pesquisa, utilizador, null);
        }

        if(tipo.equalsIgnoreCase("Data criacao")) {
            Date data2 = leituraDate(pesquisa);
            lista = gereUtilizador.pesquisarEncomendaCliente("datacriacao", null, utilizador, data2);
        }

        return lista;
    }

    /**
     * Metodo funcionarioListarEncomenda, metodo que permite Funcionarios de listar suas encomendas
     */

    private static String funcionarioListarEncomenda(String tipo, String sentido) {
        String lista = "";

        if (tipo.equalsIgnoreCase("data de criacao")) {
            lista = gereUtilizador.listarEncomendaFuncionario(utilizador, "data", sentido);
        } else if (tipo.equalsIgnoreCase("identificador")) {
            lista = gereUtilizador.listarEncomendaFuncionario(utilizador, "identificador", sentido);
        }

       return lista;
    }

    /**
     * Metodo funcionarioPesquisarEncomendas, metodo que permite Funcionarios de listar suas encomendas
     */

    private static String funcionarioPesquisarEncomendas(String[] form) {
        String lista = "";
        String pesquisa = form[1];
        String tipo = form[0];

        if(tipo.equalsIgnoreCase("identificador")) {
            lista = gereUtilizador.pesquisarEncomendaFuncionario("identificador", pesquisa, utilizador, null);
        }
        if(tipo.equalsIgnoreCase("estado")) {
            lista = gereUtilizador.pesquisarEncomendaFuncionario("estado", pesquisa, utilizador, null);        }
        if(tipo.equalsIgnoreCase("datacriacao")) {
            Date data2 = leituraDate(pesquisa);
            lista = gereUtilizador.pesquisarEncomendaFuncionario("datacriacao", null, utilizador, data2);
        }


        return lista;
    }

    /**
     * Metodo pesquisarBaixoStock, metodo que permite Funcionarios pesquisarem produtos com stock inferior a um certo numero
     */

    private static String pesquisarBaixoStock(String pesquisa) {
        String lista = "";

        int valor = Integer.parseInt(pesquisa);
        lista = gereProduto.pesquisarStock(valor);

        return lista;


    }

    /**
     * Metodo listarCategoria, metodo que permite utilizadores de listar produtos por categoria ou designacao
     */

    private static String listarCategoria(String tipo, String sentido) {
        String lista = "";

        if(tipo.equalsIgnoreCase("designacao")) {
            lista = gereProduto.listarProduto("designacao", sentido);
        }
        if(tipo.equalsIgnoreCase("categoria")) {
            lista = gereProduto.listarProduto("categoria", sentido);

        }

        return lista;

    }

    /**
     * Metodo pesquisarProduto, metodo que permite utilizadores de pesquisarem um produto por Categoria ou Designacao
     */

    private static String pesquisarProduto(String[] form) {
        String lista = "";
        String pesquisa = form[1];
        String tipo = form[0];

        if(tipo.equalsIgnoreCase("designacao")) {
            lista = gereProduto.pesquisarProduto("designacao", pesquisa);

        }
        if(tipo.equalsIgnoreCase("categoria")) {
            lista = gereProduto.pesquisarProduto("categoria", pesquisa);
        }

        return lista;
    }


    /****************************************************************************************************/

    /**
     * Metodo sair, nele informa quanto tempo o programa teve a correr, assim como uma breve mensagem de adeus
     */
    private static void sair() {
        if (utilizador != null) {
            log.inserirLog(utilizador.getLogin(), "Sair");
            notificationDialogs("Adeus [" + utilizador.getNome() + "]");
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
        notificationDialogs("Inicio do processo: " + startTimeFormat + "\nFim do processo: " + endTimeFormat +
                "\nTempo de execucao: " + miliseconds + " Milissegundos (" + seconds + " Segundos; " + minutes + " Minutos; " + hour + " Horas)");


        DB.closeConnection();
        System.exit(0);
    }


    /****************************** Verificação e Criação especifica ***********************************************/


    /**
     * Metodo de verificacao de email, copiado de um video no youtube em que explica a utilização de REGEX PATTERN e MATCHER
     *
     * @param email recebe o email para ser testado pelo regex
     * @return devolve true ou false caso tenha sucesso a comparar com o regex ou não
     */

    private static boolean verificarEmail(String email) {

        // fonte https://www.youtube.com/watch?v=OOdO785p3Qo

        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(email);
        return matcher.find();
    }

    /**
     * Metodo de verificacao se contacto é correto ou o contribuinte, dependendo do valor do tipo
     *
     * @param verifica recebe um Contacto ou contribuinte do utilizador
     * @param tipo compara com um contribuinte ou contacto
     * @return true ou false caso tenha sucesso com a comparação ou não
     */

    private static boolean verificaNumero(long verifica, String tipo) {
        if (tipo.equalsIgnoreCase("contribuinte")) {
            if (verifica <= 999999999) {
                return true;
            } else {
                return false;
            }
        }

        if (tipo.equalsIgnoreCase("contacto")) {
            if (verifica <= 999999999 && verifica >= 900000000) {
                return true;
            } else if (verifica <= 200000000 && verifica >= 299999999) {
                return true;
            } else if (verifica <= 300000000 && verifica >= 399999999) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Metodo que cria um novo SKU, faz um random dentro de um padrão de numero e verifica se existe mais algum ou é unico
     * @return Numero do Sku
     */

    private static long criarSku() {
        long sku = new Random().nextInt(1000000);
        while (!gereProduto.verificarSku(sku)) {
            sku = new Random().nextInt(1000000);
        }
        return sku;
    }

    /**
     * Metodo identificadorCompra que cria um novo identificador de compra Juntamento com o timestamp numero da encomenda
     * @return devolve o identificador de compra
     */

    private static String identificadorCompra() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        long numEncomenda = gereEncomenda.ultimoID() + 1;
        String data = sdf1.format(timestamp);
        data = numEncomenda + data;

        return data;
    }


    /**
     * Metodo criado para a leitura de Data, converte String em tipo Date
     *
     * @param data
     * @return
     */

    private static Date leituraDate(String data) {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            return date;
        } catch (ParseException e) {
            System.out.println("Introduziu uma data inválida");
        }
        return null;
    }


    /**
     * Metodo que cria uma dialog de erro
     * @param error é passado qual o erro que deve ser mostrado ao utilizador
     */
    private static void errorDialogs(String error) {
        JOptionPane.showMessageDialog(new JFrame(), error);
    }

    /**
     * Metodo que cria uma dialog de notificação
     * @param info é passado qual a informação que deve ser mostrado ao utilizador
     */
    private static void notificationDialogs(String info) {
        JOptionPane.showMessageDialog(new JFrame(), info, "Notificacao", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Metodo crucial, fica a escuta de todos os clicks nos botões da Jframe assim acionando todos os metodos
     * @param e ActioEvent que é acionado assim que houve um click num botão
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("login")) {
            loginUI = new LoginUI(this);
            mainUI.setVisible(false);
        }

        if (e.getActionCommand().equalsIgnoreCase("entrar")) {

            if (loginUI.getLogin().length() > 0 && loginUI.getPassword().length() > 0) {
                login(loginUI.getLogin(), loginUI.getPassword());
            } else if (loginUI.getLogin().length() == 0 && loginUI.getPassword().length() > 0) {
                errorDialogs("Introduza um Login");
            } else if (loginUI.getPassword().length() == 0 && loginUI.getLogin().length() > 0) {
                errorDialogs("Introduza uma Password");
            } else {
                errorDialogs("Introduza um Login e uma Password");
            }
        }

        if (e.getActionCommand().equalsIgnoreCase("CriarNovaConta")) {
            if (!registo(registerUI.getForm())) {
                registerUI.setVisible(false);
                mainUI.setVisible(true);
            }

        }

        if (e.getActionCommand().equalsIgnoreCase("VoltarMenuLogin")) {
            loginUI.setVisible(false);
            mainUI.setVisible(true);
        }

        if (e.getActionCommand().equalsIgnoreCase("VoltarMenuRegister")) {
            registerUI.setVisible(false);
            mainUI.setVisible(true);
        }

        if (e.getActionCommand().equalsIgnoreCase("Registo")) {
            boolean isFirstAccount = gereUtilizador.verificarUtilizador();
            System.out.println(isFirstAccount);
            registerUI = new RegisterUI(this, isFirstAccount);
            mainUI.setVisible(false);
        }

        if (e.getActionCommand().equalsIgnoreCase("criarNovoGestor")) {
            if (!novoGestor(novoGestorUI.getForm())) {
                novoGestorUI.setVisible(false);
                menuGestorUI.setVisible(true);
            }
        }
        if (e.getActionCommand().equalsIgnoreCase("novoGestorUI")) {
            novoGestorUI = new NovoGestorUI(this);
            menuGestorUI.setVisible(false);

        }

        if (e.getActionCommand().equalsIgnoreCase("Sair")) {
            sair();
        }

        if (e.getActionCommand().equalsIgnoreCase("pesquisarLogUtilizador")) {
            if (logUI.getPesquisa() == null) {
                logUI.setVisible(false);
                logUI = new LogUI(this, verLog(null));
            } else {
                logUI.setVisible(false);
                logUI = new LogUI(this, verLog(logUI.getPesquisa()));
            }
        }
        if (e.getActionCommand().equalsIgnoreCase("log")) {
            logUI = new LogUI(this, verLog(null));
            menuGestorUI.setVisible(false);
        }

        if (e.getActionCommand().equalsIgnoreCase("voltarLog")) {
            logUI.setVisible(false);
            menuGestorUI.setVisible(true);
        }

        if (e.getActionCommand().equalsIgnoreCase("menuAlterarDados")) {
            menuAlterarDadosUI = new MenuAlterarDadosUI(this);
            menuGestorUI.setVisible(false);
        }

        if (e.getActionCommand().equalsIgnoreCase("alterarDadosGestor")) {
            alterarDadosGestorUI = new AlterarDadosGestorUI(this, utilizador);
            menuAlterarDadosUI.setVisible(false);
        }

        if (e.getActionCommand().equalsIgnoreCase("alterarDadosCliente")) {
            alterarDadosClienteUI = new AlterarDadosClienteUI(this, null);
            menuAlterarDadosUI.setVisible(false);
        }

        if (e.getActionCommand().equalsIgnoreCase("alterarDadosFuncionario")) {
            alterarDadosFuncionarioUI = new AlterarDadosFuncionarioUI(this, null);
            menuAlterarDadosUI.setVisible(false);
        }

        if (e.getActionCommand().equalsIgnoreCase("updateGestor")) {
            if (alterarDadosGestor(alterarDadosGestorUI.getForm())) {
                menuAlterarDadosUI.setVisible(true);
                alterarDadosGestorUI.setVisible(false);
            }
        }

        if (e.getActionCommand().equalsIgnoreCase("pesquisarGestorAlterarDados")) {
            alterarDadosGestorUI.setVisible(false);
            alterarDadosGestorUI = new AlterarDadosGestorUI(this, gereUtilizador.alterarUtilizadorGestor(alterarDadosGestorUI.getLogin()));
        }

        if (e.getActionCommand().equalsIgnoreCase("pesquisarClienteAlterarDados")) {
            alterarDadosClienteUI.setVisible(false);
            alterarDadosClienteUI = new AlterarDadosClienteUI(this, gereUtilizador.alterarClienteGestor(alterarDadosClienteUI.getLogin()));
        }

        if (e.getActionCommand().equalsIgnoreCase("pesquisarFuncionarioAlterarDados")) {
            alterarDadosFuncionarioUI.setVisible(false);
            alterarDadosFuncionarioUI = new AlterarDadosFuncionarioUI(this, gereUtilizador.alterarFuncGestor(alterarDadosFuncionarioUI.getLogin()));
        }

        if (e.getActionCommand().equalsIgnoreCase("updateFuncionario")) {
            if (alterarDadosFuncionario(alterarDadosFuncionarioUI.getForm())) {
                menuAlterarDadosUI.setVisible(true);
                alterarDadosGestorUI.setVisible(false);
            }
        }

        if (e.getActionCommand().equalsIgnoreCase("updateCliente")) {
            if (!alterarDadosCliente(alterarDadosClienteUI.getForm())) {
                menuAlterarDadosUI.setVisible(true);
                alterarDadosGestorUI.setVisible(false);
            }
        }

        if (e.getActionCommand().equalsIgnoreCase("menuAtivarContas")) {
            menuGestorUI.setVisible(false);
            ativarContaUI = new AtivarContaUI(this, gereUtilizador.contasInativas("inativo"));
        }

        if (e.getActionCommand().equalsIgnoreCase("ativarContaButton")) {
            if (ativarContas(ativarContaUI.getPesquisa(), "ativar")) {
                ativarContaUI.setVisible(false);
                menuGestorUI.setVisible(true);
            }
        }

        if (e.getActionCommand().equalsIgnoreCase("recusarContaButton")) {
            if (ativarContas(ativarContaUI.getPesquisa(), "recusar")) {
                ativarContaUI.setVisible(false);
                menuGestorUI.setVisible(true);
            }
        }

        if (e.getActionCommand().equalsIgnoreCase("inativarContaButton")) {
            if (ativarContas(ativarContaUI.getPesquisa(), "inativar")) {
                ativarContaUI.setVisible(false);
                menuGestorUI.setVisible(true);
            }
        }


        if (e.getActionCommand().equalsIgnoreCase("VoltarmenuAlterarDados")) {
            if (alterarDadosGestorUI.isShowing()) {
                alterarDadosGestorUI.setVisible(false);
                menuAlterarDadosUI.setVisible(true);
            }
            if (alterarDadosClienteUI.isShowing()) {
                alterarDadosClienteUI.setVisible(false);
                menuAlterarDadosUI.setVisible(true);
            }
            if (alterarDadosFuncionarioUI.isShowing()) {
                alterarDadosFuncionarioUI.setVisible(false);
                menuAlterarDadosUI.setVisible(true);
            }
        }

        if (e.getActionCommand().equalsIgnoreCase("adicionaProduto")) {
            menuArmazenistaUI.setVisible(false);
            novoProdutoUI = new NovoProdutoUI(this);
        }

        if (e.getActionCommand().equalsIgnoreCase("adicionarNovoProduto")) {
            if (novoProduto(novoProdutoUI.getForm())) {
                novoProdutoUI.setVisible(false);
                menuArmazenistaUI.setVisible(true);
            }
        }

        if (e.getActionCommand().equalsIgnoreCase("novaCompra")) {
            novaCompraUI = new NovaCompraUI(this, gereProduto.listaProduto());
            menuClienteUI.setVisible(false);
        }

        if (e.getActionCommand().equalsIgnoreCase("AdicionarNovaCompra")) {
            novaCompra(novaCompraUI.getCompra());
        }

        if (e.getActionCommand().equalsIgnoreCase("aceitarEncomenda")) {
            aceitarEncomendaUI = new AceitarEncomendaUI(this, gereEncomenda.EncomendaEstado("iniciada"));
            menuGestorUI.setVisible(false);
        }

        if (e.getActionCommand().equalsIgnoreCase("AceitarNovaEncomenda")) {
            String identificador = aceitarEncomendaUI.getEncomenda();
            if (encomendaGestor(identificador, "Aceitar")) {
                atribuirEncomendaUI = new AtribuirEncomendaUI(this, identificador, gereUtilizador.procuraEstafeta("armazenista"));
                aceitarEncomendaUI.setVisible(false);
            }
        }

        if (e.getActionCommand().equalsIgnoreCase("AtribuirEncomenda")) {
            atribuirArmazenista(atribuirEncomendaUI.getEncomenda());
        }

        if (e.getActionCommand().equalsIgnoreCase("RecusarNovaEncomenda")) {
            encomendaGestor(aceitarEncomendaUI.getEncomenda(), "Recusar");
        }

        if (e.getActionCommand().equalsIgnoreCase("AdicionaFoto")) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showOpenDialog(null);
            File file = jFileChooser.getSelectedFile();
            String filename = file.getAbsolutePath();
            Image getAbsolutePath = null;
            ImageIcon icon = new ImageIcon(filename);
            Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            registerUI.setFoto(filename, icon);
        }

        if (e.getActionCommand().equalsIgnoreCase("prepararProduto")) {
            prepararNovaEncomendaUI = new PrepararNovaEncomendaUI(this, gereEncomenda.EncomendaEstado("aceite"));
        }

        if (e.getActionCommand().equalsIgnoreCase("PrepararNovaEncomenda")) {
            if(encomendaArmazenista(prepararNovaEncomendaUI.getEncomenda())) {
                encomendaEstafetaUI = new EncomendaEstafetaUI(this, prepararNovaEncomendaUI.getEncomenda() , gereEncomenda.EncomendaEstado("aguarda entrega"));
            }
        }

//        if (e.getActionCommand().equalsIgnoreCase("encomendaEstafeta")) {


        if (e.getActionCommand().equalsIgnoreCase("AtribuiNovoEstafeta")) {
            encomendaEstafeta(prepararNovaEncomendaUI.getEncomenda());
        }

        if (e.getActionCommand().equalsIgnoreCase("encomendaEntregue")) {
            encomendaEntregueUI = new EncomendaEntregueUI(this, gereEncomenda.EncomendaEstado("em transporte"));
        }

        if (e.getActionCommand().equalsIgnoreCase("encomendaEntregue")) {
            encomendaEntregueUI = new EncomendaEntregueUI(this, gereEncomenda.EncomendaEstado("em transporte"));
        }

        if (e.getActionCommand().equalsIgnoreCase("encomendaNovaEntregue")) {
            encomendaEntregue(encomendaEntregueUI.getEncomenda());
        }

        if (e.getActionCommand().equalsIgnoreCase("notificacoes")) {
            notificacaoUI = new NotificacaoUI(this, notificacao.lerNotificacao(gereUtilizador.u_id(utilizador.getLogin())));
            verNotificacao();
        }

        if (e.getActionCommand().equalsIgnoreCase("voltarNotificoes")) {
            notificacaoUI.setVisible(false);
            if (utilizador.getTipo().equalsIgnoreCase("Gestor")) {
                menuGestorUI.setVisible(true);
            }
            if (utilizador.getTipo().equalsIgnoreCase("Cliente")) {
                menuClienteUI.setVisible(true);
            }
            if (funcionario.getEspecializacao().equalsIgnoreCase("armazenista")) {
                menuArmazenistaUI.setVisible(true);
            }
            if (funcionario.getEspecializacao().equalsIgnoreCase("estafeta")) {
                menuEstafetaUI.setVisible(true);
            }
        }

        if (e.getActionCommand().equalsIgnoreCase("voltarMenuGestor")) {

            if (menuAlterarDadosUI.isShowing()) {
                menuAlterarDadosUI.setVisible(false);
                menuGestorUI.setVisible(true);
            }
            if (novoGestorUI.isShowing()) {
                novoGestorUI.setVisible(false);
                menuGestorUI.setVisible(true);
            }
            if (ativarContaUI.isShowing()) {
                ativarContaUI.setVisible(false);
                menuAlterarDadosUI.setVisible(true);
            }

        }

        if (e.getActionCommand().equalsIgnoreCase("VoltarmenuAlterarDados")) {
            alterarDadosClienteUI.setVisible(false);
            if (utilizador.getTipo().equalsIgnoreCase("Gestor")) {
                alterarDadosGestorUI.setVisible(true);
            }
            if (utilizador.getTipo().equalsIgnoreCase("Cliente")) {
                menuClienteUI.setVisible(true);
            }
            if (funcionario.getEspecializacao().equalsIgnoreCase("armazenista")) {
                menuArmazenistaUI.setVisible(true);
            }
            if (funcionario.getEspecializacao().equalsIgnoreCase("estafeta")) {
                menuEstafetaUI.setVisible(true);
            }
        }

        if (e.getActionCommand().equalsIgnoreCase("VoltarMenuCliente")) {
            novaCompraUI.setVisible(false);
            menuClienteUI.setVisible(true);
        }

        //listagens Utilizadores

        if(e.getActionCommand().equalsIgnoreCase("pesquisarListagens")) {
            menuGestorUI.setVisible(false);
            menuPesquisaListagemGestorUI = new MenuPesquisaListagemGestorUI(this);
        }

        if(e.getActionCommand().equalsIgnoreCase("listarUtilizadores")) {
            menuPesquisaListagemGestorUI.setVisible(false);
            listarUtilizadoresUI = new ListarUtilizadoresUI(this, gereUtilizador.listarUtilizadores("desc"));
        }
        if(e.getActionCommand().equalsIgnoreCase("ascListaUtilizadores")) {
            listarUtilizadoresUI.setLista(gereUtilizador.listarUtilizadores("asc"));
        }
        if(e.getActionCommand().equalsIgnoreCase("descListaUtilizadores")) {
            listarUtilizadoresUI.setLista(gereUtilizador.listarUtilizadores("desc"));
        }
        if(e.getActionCommand().equalsIgnoreCase("voltarListarUtilizadores")) {
            listarUtilizadoresUI.setVisible(false);
            menuPesquisaListagemGestorUI.setVisible(true);
        }

        //pesquisa utilizadores

        if(e.getActionCommand().equalsIgnoreCase("pesquisarUtilizadores")) {
            pesquisarUtilizadoresUI = new PesquisarUtilizadoresUI(this, "");
            menuPesquisaListagemGestorUI.setVisible(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("pesquisarUtilizadoresUI")) {
            pesquisarUtilizadoresUI.setLista(pesquisarUtilizadores(pesquisarUtilizadoresUI.getPesquisa()));
        }
        if(e.getActionCommand().equalsIgnoreCase("voltarPesquisarUtilizadoresUI")) {
            pesquisarUtilizadoresUI.setVisible(false);
            menuPesquisaListagemGestorUI.setVisible(true);
        }

        //listar encomendas Utilizadores

        if(e.getActionCommand().equalsIgnoreCase("listarEncomendasUtilizador")) {
            listarEncomendasUtilizadoresUI = new ListarEncomendasUtilizadoresUI(this, "");
            menuPesquisaListagemGestorUI.setVisible(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("pesquisarEncomendaUtilizadoresUIAsc")) {
            listarEncomendasUtilizadoresUI.setLista(listarEncomendasUtilizador(listarEncomendasUtilizadoresUI.getPesquisa(), "asc"));
        }
        if(e.getActionCommand().equalsIgnoreCase("pesquisarEncomendaUtilizadoresUIDesc")) {
            listarEncomendasUtilizadoresUI.setLista(listarEncomendasUtilizador(listarEncomendasUtilizadoresUI.getPesquisa(), "asc"));
        }
        if(e.getActionCommand().equalsIgnoreCase("voltarPesquisarEncomendasUtilizadoresUI")) {
            listarEncomendasUtilizadoresUI.setVisible(false);
            menuPesquisaListagemGestorUI.setVisible(true);
        }

        // listar Encomendas Não entregues

        if(e.getActionCommand().equalsIgnoreCase("listarEncomendasNEntregues")) {
            listarEncomendasNEntreguesUI = new ListarEncomendasNEntreguesUI(this, listarEncomendasNentregues("asc"));
            menuPesquisaListagemGestorUI.setVisible(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("listarEncomendasNEntreguesUIAsc")) {
            listarEncomendasNEntreguesUI.setLista(listarEncomendasNentregues("asc"));
        }
        if(e.getActionCommand().equalsIgnoreCase("listarEncomendasNEntreguesUIDesc")) {
            listarEncomendasNEntreguesUI.setLista(listarEncomendasNentregues("desc"));
        }
        if(e.getActionCommand().equalsIgnoreCase("voltarListarEncomendasNEntregues")) {
            listarEncomendasNEntreguesUI.setVisible(false);
            menuPesquisaListagemGestorUI.setVisible(true);
        }

        // pesquisar encomendas Cliente

        if(e.getActionCommand().equalsIgnoreCase("pesquisarEncomendasCLiente")) {
            pesquisarEncomendasClienteUI = new PesquisarEncomendasClienteUI(this, "");
            menuPesquisaListagemGestorUI.setVisible(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("pesquisarEncomendasClienteUIPesquisar")) {
            pesquisarEncomendasClienteUI.setLista(pesquisarEncomendasCliente(pesquisarEncomendasClienteUI.getPesquisa()));
        }
        if(e.getActionCommand().equalsIgnoreCase("voltarpesquisarEncomendasClienteUI")) {
            pesquisarEncomendasClienteUI.setVisible(false);
            menuPesquisaListagemGestorUI.setVisible(true);
        }

        // pesquisar Encomendas Tempo
        if(e.getActionCommand().equalsIgnoreCase("pesquisarEncomendasTempoBTN")) {
            pesquisarEncomendasTempoUI = new PesquisarEncomendasTempoUI(this, "");
            menuPesquisaListagemGestorUI.setVisible(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("pesquisarEncomendasTempoUIPesquisa")) {
            pesquisarEncomendasTempoUI.setLista(pesquisarEncomendasTempo(pesquisarEncomendasTempoUI.getPesquisa()));
        }
        if(e.getActionCommand().equalsIgnoreCase("voltarPesquisarEncomendasTempoUI")){
            pesquisarEncomendasTempoUI.setVisible(false);
            menuPesquisaListagemGestorUI.setVisible(true);
        }

        //remover Conta  VERIFICAR MENU PARA VARIAS CONTAS
        if(e.getActionCommand().equalsIgnoreCase("removerConta")){
            pedidoRemocaoContaUI = new PedidoRemocaoContaUI(this);
            menuAlterarDadosUI.setVisible(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("removerContaBTN")) {
            pedidoRemocaoConta();
            pedidoRemocaoContaUI.setVisible(false);
            if(utilizador.getTipo().equalsIgnoreCase("Gestor"))
                menuAlterarDadosUI.setVisible(true); //VERIFICAR ISTO
            if(utilizador.getTipo().equalsIgnoreCase("Cliente"))
                menuAlterarDadosUI.setVisible(true); //VERIFICAR ISTO
            if(utilizador.getTipo().equalsIgnoreCase("Funcionario"))
                menuAlterarDadosUI.setVisible(true); //VERIFICAR ISTO
        }
        if(e.getActionCommand().equalsIgnoreCase("naoRemoverContaBTN")) {
            pedidoRemocaoContaUI.setVisible(false);
            menuAlterarDadosUI.setVisible(true);
        }

        // Ver pedidos de remocao de conta
        if(e.getActionCommand().equalsIgnoreCase("verPedidoRemocaoConta")) {
            verPedidoRemocaoContaUI = new VerPedidoRemocaoContaUI(this, gereUtilizador.contasInativas("Remover"));
            menuAlterarDadosUI.setVisible(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("removerContaBTN")) {
            verPedidoRemocaoConta(verPedidoRemocaoContaUI.getLogin());
            notificationDialogs("Conta removida com sucesso");
        }
        if(e.getActionCommand().equalsIgnoreCase("voltarVerPedidoRemocaoContaUI")) {
            verPedidoRemocaoContaUI.setVisible(false);
            menuAlterarDadosUI.setVisible(true);
        }

        //Cliente Listar encomenda

        if(e.getActionCommand().equalsIgnoreCase("listarEncomendaCliente")) {
            clienteListarEncomendaUI = new ClienteListarEncomendaUI(this, "");
            menuPesquisaListagemClienteUI.setVisible(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("PesquisaClienteListarEncomendaUIASC")) {
            clienteListarEncomendaUI.setLista(clienteListarEncomenda("asc", clienteListarEncomendaUI.getPesquisa()));
        }
        if(e.getActionCommand().equalsIgnoreCase("PesquisaClienteListarEncomendaUIASC")) {
            clienteListarEncomendaUI.setLista(clienteListarEncomenda("desc", clienteListarEncomendaUI.getPesquisa()));
        }
        if(e.getActionCommand().equalsIgnoreCase("voltarClienteListarEncomendaUI")) {
            clienteListarEncomendaUI.setVisible(false);
            menuPesquisaListagemClienteUI.setVisible(true);
        }

        //Cliente Pesquisar Encomenda

        if(e.getActionCommand().equalsIgnoreCase("pesquisarEncomendaCliente")) {
            clientePesquisarEncomendasUI = new ClientePesquisarEncomendasUI(this, "");
            menuPesquisaListagemClienteUI.setVisible(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("PesquisaClientePesquisarEncomendasUI")) {
            clientePesquisarEncomendasUI.setLista(clientePesquisarEncomendas(clientePesquisarEncomendasUI.getPesquisa()));
        }
        if(e.getActionCommand().equalsIgnoreCase("voltarClientePesquisarEncomendasUI")) {
            clientePesquisarEncomendasUI.setVisible(false);
            menuPesquisaListagemClienteUI.setVisible(true);
        }

        // listar Categoria

        if(e.getActionCommand().equalsIgnoreCase("listarCategorias")) {
            listarCategoriaUI = new ListarCategoriaUI(this, "");
            menuPesquisaListagemClienteUI.setVisible(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("ListarCategoriaUIAsc")) {
            listarCategoriaUI.setLista(listarCategoria(listarCategoriaUI.getPesquisa(), "asc"));
        }
        if(e.getActionCommand().equalsIgnoreCase("ListarCategoriaUIdesc")) {
            listarCategoriaUI.setLista(listarCategoria(listarCategoriaUI.getPesquisa(), "desc"));
        }
        if(e.getActionCommand().equalsIgnoreCase("voltarListarCategoriaUI")){
            listarCategoriaUI.setVisible(false);
            if(utilizador.getTipo().equalsIgnoreCase("cliente"))
                menuPesquisaListagemClienteUI.setVisible(true);
            if(utilizador.getTipo().equalsIgnoreCase("funcionario"))
                menuPesquisaListagemFuncionarioUI.setVisible(true);
        }


        // pesquisar produto

        if(e.getActionCommand().equalsIgnoreCase("pesquisarProduto")) {
            pesquisarProdutoUI = new PesquisarProdutoUI(this, "");
            menuPesquisaListagemClienteUI.setVisible(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("pesquisarProdutoUIPesquisar")) {
            pesquisarProdutoUI.setLista(pesquisarProduto(pesquisarProdutoUI.getPesquisa()));
        }
        if(e.getActionCommand().equalsIgnoreCase("voltarPesquisarProdutoUI")){
            listarCategoriaUI.setVisible(false);
            if(utilizador.getTipo().equalsIgnoreCase("cliente"))
                menuPesquisaListagemClienteUI.setVisible(true);
            if(utilizador.getTipo().equalsIgnoreCase("funcionario"))
                menuPesquisaListagemFuncionarioUI.setVisible(true);
        }

        // pesquisarBaixoStock

        if(e.getActionCommand().equalsIgnoreCase("pesquisarBaixoStock")) {
            pesquisarBaixoStockUI = new PesquisarBaixoStockUI(this, "");
            menuPesquisaListagemFuncionarioUI.setVisible(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("PesquisarBaixoStockUIPesquisar")) {
            pesquisarBaixoStockUI.setLista(pesquisarBaixoStock(pesquisarBaixoStockUI.getPesquisa()));
        }
        if(e.getActionCommand().equalsIgnoreCase("voltarPesquisarBaixoStockUI")) {
            pesquisarBaixoStockUI.setVisible(false);
            menuPesquisaListagemFuncionarioUI.setVisible(true);
        }

        //funcionarioPesquisarEncomendas
        if(e.getActionCommand().equalsIgnoreCase("pesquisarEncomendaFuncionario")) {
            pesquisarEncomendaFuncionarioUI = new PesquisarEncomendaFuncionarioUI(this, "");
            menuPesquisaListagemFuncionarioUI.setVisible(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("voltarPesquisarEncomendaFuncionarioUI")){
            pesquisarEncomendaFuncionarioUI.setVisible(false);
            menuPesquisaListagemFuncionarioUI.setVisible(true);
        }
        if(e.getActionCommand().equalsIgnoreCase("PesquisarEncomendaFuncionarioUIPesquisar")) {
            pesquisarEncomendaFuncionarioUI.setLista(funcionarioPesquisarEncomendas(pesquisarEncomendaFuncionarioUI.getPesquisa()));
        }

        //funcionario Listar Encomenda

        if(e.getActionCommand().equalsIgnoreCase("ListarEncomendaFuncionario")) {
            funcionarioListarEncomendaUI = new FuncionarioListarEncomendaUI(this, "");
            menuPesquisaListagemFuncionarioUI.setVisible(false);
        }
        if(e.getActionCommand().equalsIgnoreCase("voltarFuncionarioListarEncomendaUI")) {
            funcionarioListarEncomendaUI.setVisible(false);
            menuPesquisaListagemFuncionarioUI.setVisible(true);
        }
        if(e.getActionCommand().equalsIgnoreCase("FuncionarioListarEncomendaUIAsc")) {
            funcionarioListarEncomendaUI.setLista(funcionarioListarEncomenda(funcionarioListarEncomendaUI.getPesquisa(), "asc"));
        }
        if(e.getActionCommand().equalsIgnoreCase("FuncionarioListarEncomendaUIDesc")) {
            funcionarioListarEncomendaUI.setLista(funcionarioListarEncomenda(funcionarioListarEncomendaUI.getPesquisa(), "desc"));
        }
    }
}


package pt.ipc.estgoh.loja;

import pt.ipc.estgoh.loja.database.DB;
import pt.ipc.estgoh.loja.database.GereUtilizador;
import pt.ipc.estgoh.loja.entities.Cliente;
import pt.ipc.estgoh.loja.entities.Funcionario;
import pt.ipc.estgoh.loja.entities.Gestor;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class Main {

    private static GereUtilizador gereUtilizador;

    public static void main(String[] args) {

        Connection conn = DB.getConnection();
        gereUtilizador = new GereUtilizador(conn);
        menuInicial();


    }

    private static void menuInicial() {
        while(true) {
            System.out.println("1- Login\n2- Registo\n3- Sair");
            escolha("menuInicial", leituraInt("Escolha: "));
        }
    }

    private static void escolha (String menu, int escolha) {
        if(menu.equalsIgnoreCase("menuInicial")) {
            switch (escolha) {
                case 1:
                    //login();
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

    }

    private static void registo() {


            String login = leituraString("Login: ");
            String nome = leituraString("Nome: ");
            String password = leituraString("Password: ");
            String email = leituraString("email: ");
            while(verificarEmail(email) == false) {
                email = leituraString("Email invalido\n\n introduza um email valido: ");
                verificarEmail(email);
            }
        if(gereUtilizador.verificarUtilizador()) {
            int tipo = leituraInt("Tipo?\n1-Gestor\n2-Cliente\n3-Funcionario");
            if (tipo == 1 ){
                Gestor gestor = new Gestor(nome, login, password, "inativo", email);
                if (gereUtilizador.inserirUtilizador(gestor)) {
                    System.out.println("Gestor Criado com sucesso");
                }else
                    System.out.println("Erro ao criar Gestor");
            }
            if (tipo == 2){
                int numeroContribuinte = leituraInt("Numero De Contribuinte: ");
                int contacto = leituraInt("Numero De Contribuinte: ");
                String morada = leituraString("Password: ");
                char escalao = leituraChar("Escalão: ");
                String setorDeAtividade = leituraString("Setor de Atividade: ");


                Cliente cliente = new Cliente(nome, login, password, "inativo", email, numeroContribuinte, contacto, morada, escalao, setorDeAtividade);
                if(gereUtilizador.inserirCliente(cliente)) {
                    System.out.println("Cliente Criado com sucesso");
                }else
                    System.out.println("Erro ao criar Cliente");
            }
            if (tipo == 3){
                int numeroContribuinte = leituraInt("Numero De Contribuinte: ");
                int contacto = leituraInt("Numero De Contribuinte: ");
                String morada = leituraString("Password: ");
                int especializacao = leituraInt("Especializacao: ");
                String tempDataDeInicio = leituraString("Data de Inicio de atividade dd/MM/YYYY: ");
                Date dataDeInicio = leituraDate(tempDataDeInicio);
                if (dataDeInicio != null) {
                    Funcionario funcionario = new Funcionario(nome, login, password, "inativo", email, numeroContribuinte, contacto, morada, especializacao, dataDeInicio);
                    System.out.println("Funcionario Criado com sucesso");
                }
            }
        }else {
            System.out.println("Não existe nenhum conta criada, será criado como Gestor ");
            Gestor gestor = new Gestor(nome, login, password, "ativo", email);
            if (gereUtilizador.inserirUtilizador(gestor)) {
                System.out.println("Gestor Criado com sucesso");
            }else
                System.out.println("Erro ao criar Gestor");

        }
    }

    private static void sair() {
        DB.closeConnection();
        System.exit(0);
    }


    private static void erro() {
        System.out.println("inseriu um valor invalido!!");
    }


    /****************************** Verificação ***********************************************/

    private static boolean verificarEmail(String email) {

        boolean verifica;

        StringTokenizer emailDivisor = new StringTokenizer(email, "@.");
        
        if (emailDivisor.countTokens() == 3) {
            while (emailDivisor.hasMoreTokens()) {
                if (emailDivisor.nextToken() != null) ;
            }
            return verifica = true;
        }
        return verifica = false;
    }

    /****************************** leitura do utilizador *************************************/

    private static int leituraInt(String aMsg) {
        System.out.println(aMsg);
        return LeituraDados.leituraInt();
    }

    private static String leituraString(String aMsg) {
        System.out.println(aMsg);
        return LeituraDados.leituraString();
    }

    private static float leituraFloat(String aMsg) {
        System.out.println(aMsg);
        return LeituraDados.leituraFloat();
    }

    private static char leituraChar(String aMsg) {
        System.out.println(aMsg);
        return LeituraDados.leituraChar();
    }

    private static Date leituraDate(String data)  {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }



}

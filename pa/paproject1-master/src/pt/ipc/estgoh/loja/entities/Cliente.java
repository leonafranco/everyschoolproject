package pt.ipc.estgoh.loja.entities;

public class Cliente extends Utilizador {

    private int numeroContribuinte;
    private int contacto;
    private String morada;
    private char escalao;
    private String setorDeAtividade;


    public Cliente(String nome, String login, String password, String estado, String email, int numeroContribuinte, int contacto, String morada, char escalao, String setorDeAtividade) {
        super(nome, login, password, estado, email);
        tipo = "Cliente";
        this.numeroContribuinte = numeroContribuinte;
        this.contacto = contacto;
        this.morada = morada;
        this.escalao = escalao;
        this.setorDeAtividade = setorDeAtividade;
    }

    public int getNumeroContribuinte() {
        return numeroContribuinte;
    }

    public int getContacto() {
        return contacto;
    }

    public String getMorada() {
        return morada;
    }

    public char getEscalao() {
        return escalao;
    }

    public String getSetorDeAtividade() {
        return setorDeAtividade;
    }
}

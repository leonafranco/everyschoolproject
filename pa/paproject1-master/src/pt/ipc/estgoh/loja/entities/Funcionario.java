package pt.ipc.estgoh.loja.entities;

import java.util.Date;

public class Funcionario extends Utilizador{

    private int numeroContribuinte;
    private int contacto;
    private String morada;
    private int especializacao;
    private Date dataDeInicio;

    public Funcionario(String nome, String login, String password, String estado, String email, int numeroContribuinte, int contacto, String morada, int especializacao, Date dataDeInicio) {
        super(nome, login, password, estado, email);
        tipo = "Funcionario";
        this.numeroContribuinte = numeroContribuinte;
        this.contacto = contacto;
        this.morada = morada;
        this.especializacao = especializacao;
        this.dataDeInicio = dataDeInicio;
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

    public int getEspecializacao() {
        return especializacao;
    }

    public Date getDataDeInicio() {
        return dataDeInicio;
    }
}

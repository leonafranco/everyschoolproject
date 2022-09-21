package entities;

import java.math.BigInteger;

public class Cliente extends Utilizador {

    private long numeroContribuinte;
    private long contacto;
    private String morada;

    /**
     * Construtor padrao para a criacao de um novo Cliente
     * @param nome
     * @param login
     * @param password
     * @param estado
     * @param email
     * @param tipo
     * @param numeroContribuinte
     * @param contacto
     * @param morada
     */
    public Cliente(String nome, String login, String password, String estado, String email, String tipo, long numeroContribuinte, long contacto, String morada) {
        super(nome, login, password, estado, email, tipo);
        tipo = "Cliente";
        this.numeroContribuinte = numeroContribuinte;
        this.contacto = contacto;
        this.morada = morada;

    }

    /**
     * Metodo Getter para aceder a variavel Contribuinte privada
     * @return numeroContribuinte
     */
    public long getNumeroContribuinte() {
        return numeroContribuinte;
    }

    /**
     * Metodo Getter para aceder a variavel contacto privada
     * @return Contacto
     */
    public long getContacto() {
        return contacto;
    }

    /**
     * Metodo Getter para aceder a variavel morada
     * @return Morada
     */
    public String getMorada() {
        return morada;
    }

}
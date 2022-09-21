package entities;

import java.math.BigInteger;
import java.util.Date;

public class Funcionario extends Utilizador{

    private long numeroContribuinte;
    private long contacto;
    private String morada;
    private String especializacao;
    private Date dataDeInicio;

    /**
     * Construtor padrao para a criacao de um novo Funcionario
     * @param nome
     * @param login
     * @param password
     * @param estado
     * @param email
     * @param tipo
     * @param numeroContribuinte
     * @param contacto
     * @param morada
     * @param especializacao
     * @param dataDeInicio
     */

    public Funcionario(String nome, String login, String password, String estado, String email, String tipo, long numeroContribuinte, long contacto, String morada, String especializacao, Date dataDeInicio) {
        super(nome, login, password, estado, email, tipo);
        this.numeroContribuinte = numeroContribuinte;
        this.contacto = contacto;
        this.morada = morada;
        this.especializacao = especializacao;
        this.dataDeInicio = dataDeInicio;
    }

    /**
     * Metodo Getter para aceder a variavel Contribuinte privada
     * @return Contribuinte
     */
    public long getNumeroContribuinte() {
        return numeroContribuinte;
    }

    /**
     * Metodo Getter para aceder a variavel Contacto privada
     * @return
     */

    public long getContacto() {
        return contacto;
    }

    /**
     * Metodo Getter para aceder a variavel morada privada
     * @return
     */
    public String getMorada() {
        return morada;

    }

    /**
     * Metodo Getter para aceder a variavel Especializacao privada
     * @return
     */

    public String getEspecializacao() {
        return especializacao;
    }

    /**
     * Metodo Getter para aceder a variavel data de inicio privada
     * @return
     */

    public Date getDataDeInicio() {
        return dataDeInicio;
    }
}
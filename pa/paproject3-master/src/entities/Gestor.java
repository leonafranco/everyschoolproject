package entities;


public class Gestor extends Utilizador {

    /**
     * Construtor padrao para a criacao de um novo gestor
     * @param nome
     * @param login
     * @param password
     * @param estado
     * @param email
     * @param tipo
     */
    public Gestor(String nome, String login, String password, String estado, String email, String tipo) {
        super(nome, login, password, estado, email, tipo);
    }
}
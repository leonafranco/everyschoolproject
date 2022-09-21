package entities;

public class Utilizador {

    protected String nome;
    protected String login;
    protected String password;
    protected String estado;
    protected String email;
    protected String tipo;

    /**
     * Construtor padrao para criacao de um novo Utilizador
     * @param nome
     * @param login
     * @param password
     * @param estado
     * @param email
     * @param tipo
     */
    public Utilizador(String nome, String login, String password, String estado, String email, String tipo) {
        this.nome = nome;
        this.login = login;
        this.password = password;
        this.estado = estado;
        this.email = email;
        this.tipo = tipo;
    }

    /**
     * Metodo Getter para aceder a variavel Nome privada
     * @return Nome
     */

    public String getNome() {
        return nome;
    }

    /**
     * Metodo Getter para aceder a variavel Login privada
     * @return Login
     */

    public String getLogin() {
        return login;
    }

    /**
     * Metodo Getter para aceder a variavel password privada
     * @return password
     */

    public String getPassword() {
        return password;
    }

    /**
     * Metodo Getter para aceder a variavel estado privada
     * @return estado
     */

    public String getEstado() {
        return estado;
    }

    /**
     * Metodo Getter para aceder a variavel email privada
     * @return email
     */

    public String getEmail() {
        return email;
    }

    /**
     * Metodo Getter para aceder a variavel Tipo privada
     * @return tipo
     */

    public String getTipo() {
        return tipo;
    }

    /**
     * Metodo Setter para modificar a variavel nome privada
     * @return tipo
     */

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo Setter para modificar a variavel login privada
     * @return boolean
     */


    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Metodo Setter para modificar a variavel password privada
     * @return boolean
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metodo Setter para modificar a variavel estado privada
     * @return boolean
     */

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Metodo Setter para modificar a variavel email privada
     * @return boolean
     */


    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metodo Setter para modificar a variavel tipo privada
     * @return boolean
     */


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * metodo padrao toString do utilizador, usado na maioria para debugging
     * @return
     */
    @Override
    public String toString() {
        return "Utilizador{" +
                "nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", estado='" + estado + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
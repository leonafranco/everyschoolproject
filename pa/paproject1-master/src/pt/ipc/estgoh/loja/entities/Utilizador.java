package pt.ipc.estgoh.loja.entities;

public class Utilizador {

    protected String nome;
    protected String login;
    protected String password;
    protected String estado;
    protected String email;
    protected String tipo;

    public Utilizador(String nome, String login, String password, String estado, String email) {
        this.nome = nome;
        this.login = login;
        this.password = password;
        this.estado = estado;
        this.email = email;
        this.tipo = null;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEstado() {
        return estado;
    }

    public String getEmail() {
        return email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

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

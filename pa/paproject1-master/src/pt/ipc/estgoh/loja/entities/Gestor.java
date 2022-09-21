package pt.ipc.estgoh.loja.entities;

public class Gestor extends Utilizador {
    
    public Gestor(String nome, String login, String password, String estado, String email) {
        super(nome, login, password, estado, email);
        tipo = "Gestor";
    }
}

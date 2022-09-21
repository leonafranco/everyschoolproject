package db;

/**
 * Metodo criado somente para excepcao personalizadas
 * @author Leonardo Menezes Franco - 2016053468
 */

public class DbException extends RuntimeException {

    /**
     * Criacao de uma excepcao personalizada para a eventuais erros ligados a base de dados
     * @param msg
     */
    public DbException(String msg) {
        super(msg);
    }
}
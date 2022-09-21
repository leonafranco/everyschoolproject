package db;


import java.sql.*;

/**
 * Classe Log, Classe feita para Acesso a base de dados da tabela log
 * @author Leonardo Menezes Franco - 2016053468
 */


public class Notificacao {

    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement ps = null;


    /**
     * Construtor padrão que que recebe como atributo a conexao a base de dados
     * @param conn
     */
    public Notificacao(Connection conn) {
        this.conn = conn;
    }

    /**
     * Metodo de insercao de uma Mesangem na tabela Notificacao
     * @param mensagem
     * @return boolean verificador
     */
    public boolean inserirNotificacao(String mensagem) {

        int rowsAffected = 0;

        try {

            ps = conn.prepareStatement(
                    "INSERT INTO notificacoes "
                            + "(n_mensagem, n_visualizada) "
                            + "VALUES "
                            + "(?, ?)");

            ps.setString(1, mensagem);
            ps.setBoolean(2, false);


            rowsAffected = ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(ps);

        }
        if (rowsAffected != 0) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * Insercao na tabela notificao_utilizador, serve para fazer a ligacao entre Utilizadores e Noficacao
     * @param u_id
     * @param n_id
     * @return boolean verificador
     */

    public boolean inserirNotificacaoUtilizador(long u_id, long n_id) {

        boolean verifica = false;
        int rowsAffected = 0;

        try {

            ps = conn.prepareStatement(
                    "INSERT INTO notificacao_utilizador "
                            + "(notificacao_n_id, utilizador_u_id) "
                            + "VALUES "
                            + "(?, ?)");

            ps.setLong(1,n_id);
            ps.setLong(2,u_id);



            rowsAffected = ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(ps);

        }
        if (rowsAffected != 0) {
            return verifica = true;
        }else {
            return verifica;
        }
    }

    /**
     * Metodo de leitura de uma notificacao
     * @param u_id
     * @return
     */

    public String lerNotificacao (long u_id) {
        String lista = "";
        try {
            ps = conn.prepareStatement("Select * from notificacao, notificacao_utilizador where n_visualizada = 'false' AND utilizador_u_id = ? AND n_id = notificacao_n_id");
            ps.setLong(1,u_id);
            rs = ps.executeQuery();


            while (rs.next()) {
                String mensagem = rs.getString("n_mensagem");

                lista +=" | " + mensagem + " |\n" ;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

        return lista;
    }

    /**
     * Metodo de marcacao de uma mensagem como lida depois desta ser lida pelo utilizador
     * @param u_id
     * @return
     */
    public boolean mensagemLida (long u_id) {
        try {

            ps = conn.prepareStatement("update notificacao, notificacao_utilizador SET n_visualizada = 'true' AND utilizador_u_id = ? AND n_id = notificacao_n_id ");

            ps.setLong(1, u_id);

            ps.executeUpdate();

            return true;

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(ps);

        }
        return false;
    }

    /**
     * metodo que retorna o ultimo id introduzido na tabela notificacao
     * @return
     */

    public long ultimoID () {
        long id = 0;

        try {
            st = conn.createStatement();
            rs = st.executeQuery("Select * from notificacao");

            while (rs.next()) {
                id++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return id;
    }
}

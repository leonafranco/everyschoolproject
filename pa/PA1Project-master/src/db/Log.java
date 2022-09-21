package db;



import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Classe Log, Classe feita para Acesso a base de dados da tabela log
 * @author Leonardo Menezes Franco - 2016053468
 */

public class Log {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    /**
     * Construtor padrão que que recebe como atributo a conexao a base de dados
     * @param conn
     */
    public Log (Connection conn) {
        this.conn = conn;
    }

    /**
     * Metodo que insere um Log na tabela dos logs
     * @param login
     * @param acao
     * @return boolean verificador
     */
    public boolean inserirLog(String login, String acao) {
        boolean verifica = false;
        int rowsAffected = 0;

        java.util.Date hoje = new java.util.Date();

        try {

            ps = conn.prepareStatement(
                    "INSERT INTO log "
                            + "(l_data, l_hora, l_utilizador, l_accao) "
                            + "VALUES "
                            + "(?, ?, ?, ?)");

            ps.setDate(1,new java.sql.Date(hoje.getTime()));
            ps.setTime(2,new java.sql.Time(hoje.getTime()));
            ps.setString(3,login);
            ps.setString(4,acao);


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
    } //funciona

    /**
     * Metodo que permite listar todos os logs
     * @return String contendo todos os logs
     */
    public String listaLog() {
        String lista ="";
        try {
            ps = conn.prepareStatement("Select * from log");
            rs = ps.executeQuery();


            while (rs.next()) {
                Date data = rs.getDate("l_data");
                String hora = rs.getString("l_hora");
                String utilizador = rs.getString("l_utilizador");
                String acao = rs.getString("l_accao");


                lista +=" < " + data + " >< " +  hora  +" >< "  + utilizador + " >< " + acao + " > \n" ;
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
     * Metodo que permite listar logs de um determinado utilizador
     * @param login
     * @return String contendo os logs
     */
    public String listaLog(String login) {
        String lista ="";
        try {
            ps = conn.prepareStatement("Select * from log where l_utilizador LIKE ?");
            ps.setString(1, login);
            rs = ps.executeQuery();


            while (rs.next()) {
                Date data = rs.getDate("l_data");
                String hora = rs.getString("l_hora");
                String utilizador = rs.getString("l_utilizador");
                String acao = rs.getString("l_accao");


                lista +=" < " + data + " >< " +  hora  +" >< "  + utilizador + " >< " + acao + " > " ;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return lista;

    }

}

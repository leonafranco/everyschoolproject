package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    /**
     * Classe DB, nela sera escrito todas as linhas de codigo necessarias para chamadas a base de dados
     * @author Leonardo Menezes Franco - 2016053468
     */

    private static Connection conn = null;

    /**
     * Metodo Construtor padrao que acessa ao ficheiro properties para leitura das variaveis necessarias para acessar a base de dados
     * caso seja correto returna a conexao
     * @return conexao a base de dados
     */
    public static Connection getConnection() {
        if(conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    /**
     * Metodo closeConnection, serve para fechar a conexao a base de dados
     */
    public static void closeConnection() {

        if(conn != null) {
            try {
                conn.close();
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    /**
     * Metedodo para fechar o um statement
     * @param st
     */
    public static void closeStatement (Statement st) {
        if (st != null) {
            try{
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    /**
     * metodo para fechar um ResultSet
     * @param rs
     */

    public static void closeResultSet (ResultSet rs) {
        if (rs != null) {
            try{
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    /**
     * Metodo de leitura dos properties
     * @return
     */

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
}
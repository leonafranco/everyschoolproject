package db;

import entities.Encomenda;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe GereEncomenda, Classe feita para Acesso a base de dados da tabela encomenda
 * @author Leonardo Menezes Franco - 2016053468
 */

public class GereEncomenda {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    /**
     * Construtor padrão que que recebe como atributo a conexao a base de dados
     * @param conn
     */

    public GereEncomenda(Connection conn) {
        this.conn = conn;
    }

    /**
     * Metodo inserirEncomenda, insere uma encomenda na tabela de encomendas da base de dados
     * @param encomenda
     * @return boolean verificador
     */
    public boolean inserirEncomenda(Encomenda encomenda) {
        boolean verifica = false;
        int rowsAffected = 0;

        try {

            ps = conn.prepareStatement(
                    "INSERT INTO Encomenda "
                            + "(E_identificador, e_custo, e_datacriacao, e_dataaceitacao, e_estado) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)");

            ps.setString(1,encomenda.getIdentificador());
            ps.setFloat(2,encomenda.getCustoTotal());
            ps.setDate(3,new java.sql.Date(encomenda.getDataCriacao().getTime()));
            ps.setDate(4, null);
            ps.setString(5,encomenda.getEstado());


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
    } //Verificarfunciona

    /**
     * Meotod ultimoId, metodo que percorre todas as encomendas existentes na base de dados e retorna o ultimo resultado do ID
     * @return Long id do ultimo utilizador
     */
    public long ultimoID () {
        long id = 0;

        try {
            st = conn.createStatement();
            rs = st.executeQuery("Select * from encomenda");

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

    /**
     * Metodo que faz a ligacao entre um produto e uma encomenda
     * @param prodID
     * @param encID
     * @return boolean verificador
     */
    public boolean InserirProdEnc(long prodID, long encID) {

        boolean verifica = false;
        int rowsAffected = 0;

        try {

            ps = conn.prepareStatement(
                    "INSERT INTO Produto_Encomenda "
                            + "(produto_p_id, encomenda_e_id) "
                            + "VALUES "
                            + "(?, ?)");

            ps.setLong(1,prodID);
            ps.setLong(2,encID);



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
     * Metodo que faz a ligacao entre uma Encomenda e um Cliente
     * @param clienteEncID
     * @param encID
     * @return boolean verificador
     */
    public boolean InserirClienteEnc(long clienteEncID,long encID) {

        boolean verifica = false;
        int rowsAffected = 0;

        try {

            ps = conn.prepareStatement(
                    "INSERT INTO encomenda_cliente "
                            + "(encomenda_e_id, cliente_utilizador_u_id) "
                            + "VALUES "
                            + "(?, ?)");

            ps.setLong(1,encID);
            ps.setLong(2,clienteEncID);



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
     * Metodo que retorna o estado de uma encomenda
     * @param estado
     * @return String com o estado da encomenda
     */
    public String EncomendaEstado(String estado) {

        String lista = "";
        try {
            ps = conn.prepareStatement("Select * from encomenda where e_estado = ?");
            ps.setString(1,estado);
            rs = ps.executeQuery();


            while (rs.next()) {
                String identificador = rs.getString("e_identificador");
                float custo = rs.getFloat("e_custo");
                String estadoAtual = rs.getString("e_estado");

                lista += identificador + " | " + custo + "€ | " + estadoAtual + "|\n";
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
     * Metodo de verificacao se existe alguma expirada, isto é, depois da aceitacao já passaram dias sem ser confirmada
     * @return String encomendas expiradas
     */
    public String encomendaExpirada() {
        Date dataAtual = new Date();
        String lista ="";

        try {
            ps = conn.prepareStatement("Select * from encomenda where e_estado NOT LIKE 'confirmada'");
            rs = ps.executeQuery();


            while (rs.next()) {
                Date dataCriacao = rs.getDate("e_datacriacao");
                String identificador = rs.getString("e_identificador");

                Long diff = dataCriacao.getTime() - dataAtual.getTime();
                if((diff / (1000 * 60 * 60 * 24)) >= 10) {
                    lista += identificador + " ";
                }



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
     * Metodo para retornar o u_id de uma encomenda informando o identificador da mesma
     * @param identificador
     * @return long u_id
     */
    public long u_id_encomenda(String identificador) {
        long e_id = e_id(identificador);

        try {
            ps = conn.prepareStatement("Select * from encomenda_cliente where encomenda_e_id = ?" );

            ps.setLong(1, e_id);


            rs = ps.executeQuery();
            if (rs.next()) {
                long id = rs.getLong("cliente_utilizador_u_id");

                return id;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return 0;
    }

    /**
     * Metodo para efectuar um update na tabela da encomenda, usa o identificador o mesmo sendo unico e nao mutavel par alterar outros dados da mesma
     * @param custo
     * @param dataAceitacao
     * @param estado
     * @param campo
     * @param identificador
     * @return boolean verificador
     */
    public boolean updateEncomenda (float custo, Date dataAceitacao, String estado, String campo, String identificador) {

        long e_id = e_id(identificador);

        if(e_id == 0) {
            return false;
        }

        String queryUpdate = "update encomenda SET ";


        if (campo.equalsIgnoreCase("custo")) {
            queryUpdate = queryUpdate + "e_custo = ? where e_id = ?";
        }
        if (campo.equalsIgnoreCase("data")) {
            queryUpdate = queryUpdate + "e_dataaceitacao = ? where e_id = ?" ;
        }
        if (campo.equalsIgnoreCase("estado")) {
            queryUpdate = queryUpdate + "e_estado = ? where e_id = ?" ;
        }


        if (custo > 0) {
            try {

                ps = conn.prepareStatement(queryUpdate);

                ps.setFloat(1, custo);
                ps.setLong(2, e_id);

                ps.executeUpdate();

                return true;

            }catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DB.closeStatement(ps);

            }
        }else if (dataAceitacao != null) {
            try {

                ps = conn.prepareStatement(queryUpdate);

                ps.setDate(1,new java.sql.Date(dataAceitacao.getTime()));
                ps.setLong(2, e_id);

                ps.executeUpdate();

                return true;

            }catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DB.closeStatement(ps);

            }
        }else if (estado != null) {
            try {

                ps = conn.prepareStatement(queryUpdate);

                ps.setString(1, estado);
                ps.setLong(2, e_id);

                ps.executeUpdate();

                return true;

            }catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DB.closeStatement(ps);

            }
        }
        return false;
    }

    /**
     * Metodo que retorna o id da encomenda a quando é informado o identificador
     * @param identificador
     * @return long id
     */
    public long e_id(String identificador) {

        try {
            ps = conn.prepareStatement("Select * from encomenda where e_identificador = ?" );

            ps.setString(1, identificador);


            rs = ps.executeQuery();
            if (rs.next()) {
                long id = rs.getLong("e_id");

                return id;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return 0;
    } //funciona

    /**
     * Metodo que serve para listar encomendas, estas podendo ser ordenadas por data ou utilizador
     * @param tipo
     * @param sentido
     * @param data
     * @return
     */
    public String listarEncomendas (String tipo, String sentido, Date data) {
        String query = "select * from encomenda ";

        if(tipo.equalsIgnoreCase("data") && sentido.equalsIgnoreCase("desc")) {
            query = query + "ORDER BY e_datacriacao DESC";
        }else if(tipo.equalsIgnoreCase("data") && sentido.equalsIgnoreCase("asc")) {
            query = query + "ORDER BY e_datacriacao ASC";
        }

        if(tipo.equalsIgnoreCase("utilizador") && sentido.equalsIgnoreCase("asc")) {
            query = "select * from encomenda, encomenda_cliente, utilizador where e_id = encomenda_e_id AND cliente_utilizador_u_id = u_id order by u_nome ASC ";
        }else if(tipo.equalsIgnoreCase("utilizador") && sentido.equalsIgnoreCase("desc")) {
            query = "select * from encomenda, encomenda_cliente, utilizador where e_id = encomenda_e_id AND cliente_utilizador_u_id = u_id ORDER BY u_nome DESC";
        }

        String lista ="";

        if(data == null) {
            try {
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();


                while (rs.next()) {
                    String nome = rs.getString("u_nome");
                    String identificador = rs.getString("e_identificador");
                    Date datacria = rs.getDate("e_datacriacao");
                    String estado = rs.getString("e_estado");

                    lista += identificador + " | " + datacria + " | " + estado + " | \n";
                }


            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DB.closeResultSet(rs);
                DB.closeStatement(st);
            }
            return lista;
        }else {
            try {
                ps = conn.prepareStatement(query);
                ps.setDate(1,  new java.sql.Date(data.getTime()));
                rs = ps.executeQuery();


                while (rs.next()) {

                    String identificador = rs.getString("e_identificador");
                    Date datacria = rs.getDate("e_datacriacao");
                    String estado = rs.getString("e_estado");

                    lista += identificador + " | " + datacria + " | " + estado + " | \n";
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

    /**
     * Metodo que lista encomendas pendentes, podendo estar ordenado Ascedente ou descendente
     * @param sentido
     * @return
     */
    public String listarEncPendentes (String sentido) {

        String query = "Select * from encomenda where e_estado NOT LIKE 'entregue' or 'confirmada' order by e_datacriacao";
        if(sentido.equalsIgnoreCase("desc")) {
            query = query + " desc ";
        }else if(sentido.equalsIgnoreCase("asc")) {
            query = query + " asc ";
        }
        String lista = "";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();


            while (rs.next()) {
                String identificador = rs.getString("e_identificador");
                float custo = rs.getFloat("e_custo");
                String estadoAtual = rs.getString("e_estado");

                lista += identificador + " | " + custo + "€ | " + estadoAtual + "|\n";
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
     * Metodo de pesquisa, pode procurar por uma encomenda informando identificador, estado, cliente ou data
     * @param tipo
     * @param procura
     * @param data
     * @return
     */

    public String pesquisarEncomendaIdentificador(String tipo, String procura, Date data) {
        String query = "select * from encomenda ";

        if(tipo.equalsIgnoreCase("identificador")) {
            query = query + "where e_identificador LIKE ? ";
        }
        if(tipo.equalsIgnoreCase("estado")) {
            query = query + "where e_estado LIKE ? ";
        }
        if(tipo.equalsIgnoreCase("cliente")) {
            query = "select * from encomenda, encomenda_cliente, utilizador where e_id = encomenda_e_id AND cliente_utilizador_u_id = u_id AND u_nome LIKE ? ";
        }

        if(data != null) {
            query = query + "where e_datacriacao LIKE ?";
        }

        String lista ="";

        if(data == null) {
            procura = "%" + procura + "%";
            try {
                ps = conn.prepareStatement(query);
                ps.setString(1, procura);
                rs = ps.executeQuery();


                while (rs.next()) {
                    String identificador = rs.getString("e_identificador");
                    Date datacria = rs.getDate("e_datacriacao");
                    String estado = rs.getString("e_estado");

                    lista += identificador + " | " + datacria + " | " + estado + " | \n";
                }


            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DB.closeResultSet(rs);
                DB.closeStatement(st);
            }
            return lista;
        }else {
            try {
                ps = conn.prepareStatement(query);
                ps.setDate(1, new java.sql.Date(data.getTime()));
                rs = ps.executeQuery();


                while (rs.next()) {
                    String identificador = rs.getString("e_identificador");
                    Date datacria = rs.getDate("e_datacriacao");
                    String estado = rs.getString("e_estado");

                    lista += identificador + " | " + datacria + " | " + estado + " | \n";
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


    /**
     * Metodo de pesquisar por encomenda entre duas 2 datas
     * @param data1
     * @param data2
     * @return
     */
    public String pesquisarEncomendaDatas(Date data1, Date data2) {

        String lista ="";
        try {
            ps = conn.prepareStatement("select * from encomenda where e_datacriacao >= ? AND e_datacriacao <= ?");
            ps.setDate(1, new java.sql.Date(data1.getTime()));
            ps.setDate(2, new java.sql.Date(data2.getTime()));

            rs = ps.executeQuery();


            while (rs.next()) {
                String identificador = rs.getString("e_identificador");
                Date datacria = rs.getDate("e_datacriacao");
                String estado = rs.getString("e_estado");

                lista += identificador + " | " + datacria + " | " + estado + " | \n";
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

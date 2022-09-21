package db;

import entities.Produto;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Classe GereProduto, Classe feita para Acesso a base de dados da tabela produto
 * @author Leonardo Menezes Franco - 2016053468
 */

public class GereProduto {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    /**
     * Construtor padrão que que recebe como atributo a conexao a base de dados
     * @param conn
     */
    public GereProduto (Connection conn) {
        this.conn = conn;
    }

    /**
     * metodo para verificar se um SKU já está em utilizacao
     * @param verifica
     * @return boolean verificador
     */
    public boolean verificarSku(long verifica) {
        try {
                ps = conn.prepareStatement("Select * from produto where p_sku = ?");
                ps.setLong(1, verifica);
                rs = ps.executeQuery();

                if(rs.next()) {
                    return false;
                }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return true;

    } //Verificarfunciona

    /**
     * Metodo de insercao de um produto na base de dados
     * @param produto
     * @return boolean verificador
     */

    public boolean inserirProduto(Produto produto) {
        boolean verifica = false;
        int rowsAffected = 0;

        try {

            ps = conn.prepareStatement(
                    "INSERT INTO Produto "
                            + "(p_Designacao, p_Fabricante, p_peso, p_preco, p_sku, p_lote, p_dataprod, p_datavalidade, p_stock, p_categoria, p_classificacao) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1,produto.getDesignacao());
            ps.setString(2,produto.getFabricante());
            ps.setInt(3,produto.getPeso());
            ps.setFloat(4,produto.getPreco());
            ps.setLong(5,produto.getSKU());
            ps.setLong(6,produto.getLote());
            ps.setDate(7,new java.sql.Date(produto.getDataprod().getTime()));
            ps.setDate(8,new java.sql.Date(produto.getDataValidade().getTime()));
            ps.setLong(9,produto.getStock());
            ps.setString(10,produto.getCategoria());
            ps.setString(11,produto.getClassificacao());

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
            rs = st.executeQuery("Select * from utilizador");

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
     * Metodo de listar produtos da base de dados em que o stock seja superior a
     * @return String contendo o stock
     */

    public String listaProduto() {
        String lista ="";
            try {
                ps = conn.prepareStatement("Select * from produto where p_stock > 1");
                rs = ps.executeQuery();


                while (rs.next()) {
                    long SKU = rs.getLong("p_sku");
                    String designacao = rs.getString("p_designacao");
                    float preco = rs.getFloat("p_preco");

                    lista = SKU + " | " + designacao + " | " + preco + " | " ;
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
     * Metodo de retornar o ID de um produto informando o SKU
      * @param SKU
     * @return long id do produto
     */
    public long getIDProduto (long SKU) {

        try {
            ps = conn.prepareStatement("Select * from produto where p_sku = ?");

            ps.setLong(1, SKU);


            rs = ps.executeQuery();
            if (rs.next()) {
                Long ID = rs.getLong("p_id");


                return ID;
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
     * Metodo que retornar uma String caso existam produtos com Stock inferiores a 10 unidades
     * @return
     */

    public String baixoStock() {
        String lista ="";
        try {
            ps = conn.prepareStatement("Select * from produto where p_stock < 10");
            rs = ps.executeQuery();


            while (rs.next()) {
                long SKU = rs.getLong("p_sku");

                lista += SKU + " ";
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
     * Metodo que permite pesquisar por produtos em que o stock é inferior a um certo valor
     * @param valor
     * @return String lista contendo os produtos com stock inferior
     */

    public String pesquisarStock(int valor) {
        String lista ="";
        try {
            ps = conn.prepareStatement("Select * from produto where p_stock < ?");
            ps.setInt(1, valor);
            rs = ps.executeQuery();


            while (rs.next()) {
                long SKU = rs.getLong("p_sku");
                long stock = rs.getLong("p_stock");
                lista += "SKU : " + SKU + " | " + stock + " Unidades" ;
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
     * metodo listar produto, permite listar produto de ordenado por designacao ou categoria, este mesmo Ascendente ou Descendente
     * @param tipo
     * @param sentido
     * @return String da lista de produtos
     */

    public String listarProduto(String tipo, String sentido) {
        String query = "select * from produto ";

        if(tipo.equalsIgnoreCase("designacao") && sentido.equalsIgnoreCase("asc")) {
            query = query + " order by p_designacao asc";
        }else if(tipo.equalsIgnoreCase("designacao") && sentido.equalsIgnoreCase("DESC")) {
            query = query + " order by p_designacao desc ";
        }
        if(tipo.equalsIgnoreCase("categoria") && sentido.equalsIgnoreCase("asc")) {
            query = query + " order by p_categoria ";
        }else if(tipo.equalsIgnoreCase("categoria") && sentido.equalsIgnoreCase("DESC")) {
            query = query + " order by p_categoria desc ";
        }

        String lista ="";

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();


            while (rs.next()) {
                Long SKU = rs.getLong("p_sku");
                String designacao = rs.getString("p_designacao");
                String categoria = rs.getString("p_categoria");

                lista += SKU + " | " + designacao + " | " + categoria + " | \n";
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
     * Metodo que permite pesquisar produtos por categoria ou designacao
     * @param tipo
     * @param pesquisa
     * @return
     */

    public String pesquisarProduto(String tipo, String pesquisa) {
        String query = "select * from produto ";
        pesquisa = "%" + pesquisa + "%";

        if(tipo.equalsIgnoreCase("designacao")) {
            query = query + " where p_designacao LIKE ? ";
        }

        if(tipo.equalsIgnoreCase("categoria")) {
            query = query + " where p_categoria LIKE ? ";
        }


        String lista ="";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, pesquisa);
            rs = ps.executeQuery();


            while (rs.next()) {
                Long SKU = rs.getLong("p_sku");
                String designacao = rs.getString("p_designacao");
                String categoria = rs.getString("p_categoria");

                lista += SKU + " | " + designacao + " | " + categoria + " | \n";
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

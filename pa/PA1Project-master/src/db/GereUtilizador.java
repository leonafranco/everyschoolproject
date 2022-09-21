package db;


import java.sql.*;
import java.text.SimpleDateFormat;
import entities.Utilizador;
import entities.Funcionario;
import entities.Cliente;

/**
 * Classe GereUtilizador, Classe feita para Acesso a base de dados da tabela utilizador
 * @author Leonardo Menezes Franco - 2016053468
 */
public class GereUtilizador {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    /**
     * Construtor padrão que que recebe como atributo a conexao a base de dados
     * @param conn
     */
    public GereUtilizador (Connection conn) {
        this.conn = conn;
    }


    /**
     * Verifica se existem utilizados inscritos na base de dados;
     * @return
     */
    public boolean verificarUtilizador() {

        boolean verifica = false;

        try {
            st = conn.createStatement();
            rs = st.executeQuery("Select * from utilizador");

            if (rs.next()) {
                verifica = true;
            } else {
                verifica = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return verifica;
    } //funciona

    /**
     * Verifica se existem login ou emails duplicados na base de dados
     * @param verifica
     * @param tipo
     * @return boolean verificador
     */
    public boolean verificaDuplicado(String verifica, String tipo) {


        try {
            if(tipo.equalsIgnoreCase("login")) {
                ps = conn.prepareStatement("Select * from utilizador where U_login = ?");
                ps.setString(1, verifica);
                rs = ps.executeQuery();

                if(rs.next()) {
                    return false;
                }

            }else if (tipo.equalsIgnoreCase("email")) {
                ps = conn.prepareStatement("Select * from utilizador Where U_email = ?");
                ps.setString(1, verifica);
                rs = ps.executeQuery();

                if(rs.next()) {
                    return false;
                }
            }else if(rs == null){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return true;
    } //funciona

    /**
     * Insere um novo utilizador na tabela de utilizadores
     * @param utilizador
     * @return boolean verificador
     */
    public boolean inserirUtilizador(Utilizador utilizador) {
        boolean verifica = false;
        int rowsAffected = 0;

        try {

            ps = conn.prepareStatement(
                    "INSERT INTO UTILIZADOR "
                            + "(U_NOME, U_LOGIN, U_PASSWORD, U_ESTADO, U_EMAIL, U_TIPO) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?, ?)");

            ps.setString(1,utilizador.getNome());
            ps.setString(2,utilizador.getLogin());
            ps.setString(3,utilizador.getPassword());
            ps.setString(4,utilizador.getEstado());
            ps.setString(5,utilizador.getEmail());
            ps.setString(6,utilizador.getTipo());

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
     * insere um utilizador e um funcionario com o id do utilizador
     * @param funcionario
     * @return boolean verificador
     */
    public boolean inserirFuncionario(Funcionario funcionario) {
        int rowsAffected = 0;

        if(inserirUtilizador(funcionario) == false) {
            return false;
        }
        long id = ultimoID();
        try {

            ps = conn.prepareStatement(
                    "INSERT INTO FUNCIONARIO"
                            + "(Utilizador_u_id, F_contribuinte, F_Contacto, F_Morada, F_especializacao, F_DataInicio)"
                            + "VALUES "
                            + "(?, ?, ?, ?, ?,?)");


            ps.setLong(1, id);
            ps.setLong(2,funcionario.getNumeroContribuinte());
            ps.setLong(3,funcionario.getContacto());
            ps.setString(4,funcionario.getMorada());
            ps.setString(5,funcionario.getEspecializacao());
            ps.setDate(6, new java.sql.Date(funcionario.getDataDeInicio().getTime()));


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
    } //funciona

    /**
     * insere um utilizador e um Cliente com o id do utilizador
     * @param cliente
     * @return boolean verificador
     */
    public boolean inserirCliente(Cliente cliente) {
        int rowsAffected = 0;

        if(inserirUtilizador(cliente) == false) {
            return false;
        }
        long id = ultimoID();
        try {


            ps = conn.prepareStatement(
                    "INSERT INTO CLIENTE"
                            + "(Utilizador_u_id, C_contribuinte, C_Contacto, C_Morada)"
                            + "VALUES "
                            + "(?, ?, ?, ?)");

            ps.setLong(1, id);
            ps.setLong(2,cliente.getNumeroContribuinte());
            ps.setLong(3,cliente.getContacto());
            ps.setString(4,cliente.getMorada());



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
    } //funciona

    /**
     * etodo para efectuar um update na tabela da utilizadores, funcionarios ou cliente, usa o Login o mesmo sendo unico e nao mutavel par alterar outros dados da mesma
     * @param tipo
     * @param alteracao
     * @param alteracaoNumero
     * @param campo
     * @param login
     * @return
     */
    public boolean updateUtilizador (String tipo, String alteracao, Long alteracaoNumero, String campo, String login) {

        long u_id = u_id(login);

        if(u_id == 0) {
            return false;
        }

        String queryUpdate = "update utilizador SET ";


        if (campo.equalsIgnoreCase("nome")) {
            queryUpdate = queryUpdate + "u_nome = ? where u_id = ?";
        }
        if (campo.equalsIgnoreCase("login")) {
            queryUpdate = queryUpdate + "u_login = ? where u_id = ?" ;
        }
        if (campo.equalsIgnoreCase("password")) {
            queryUpdate = queryUpdate + "u_password = ? where u_id = ?" ;
        }
        if (campo.equalsIgnoreCase("email")) {
            queryUpdate = queryUpdate + "u_email = ? where u_id = ?";
        }
        if (campo.equalsIgnoreCase("estado")) {
            queryUpdate = queryUpdate + "u_estado = ? where u_id = ?" ;
        }
        if (campo.equalsIgnoreCase("contribuinte") && tipo.equalsIgnoreCase("funcionario")) {
            queryUpdate = "update funcionario SET f_contribuinte = ? where utilizador_u_id = ?";
        }
        if (campo.equalsIgnoreCase("morada") && tipo.equalsIgnoreCase("funcionario")) {
            queryUpdate = "update funcionario SET f_morada = ? where utilizador_u_id = ?";
        }
        if (campo.equalsIgnoreCase("contacto") && tipo.equalsIgnoreCase("funcionario")) {
            queryUpdate = "update funcionario SET f_contacto = ? where utilizador_u_id = ?" ;
        }
        if (campo.equalsIgnoreCase("especializacao")) {
            queryUpdate = "update funcionario SET f_especializacao = ? where utilizador_u_id = ?";
        }
        if (campo.equalsIgnoreCase("datadeinicio")) {
            queryUpdate = "update funcionario SET f_datadeinicio = ? where utilizador_u_id = ?";
        }
        if (campo.equalsIgnoreCase("contribuinte") && tipo.equalsIgnoreCase("cliente")) {
            queryUpdate = "update cliente SET c_contribuinte = ? where utilizador_u_id = ?" ;
        }
        if (campo.equalsIgnoreCase("morada") && tipo.equalsIgnoreCase("cliente")) {
            queryUpdate = "update cliente SET c_morada = ? where utilizador_u_id = ?" ;
        }
        if (campo.equalsIgnoreCase("contacto") && tipo.equalsIgnoreCase("cliente")) {
            queryUpdate = "update cliente SET c_contacto = ? where utilizador_u_id = ?" ;
        }

        if (alteracao == null) {
            try {

                ps = conn.prepareStatement(queryUpdate);

                ps.setLong(1, alteracaoNumero);
                ps.setLong(2, u_id);

                ps.executeUpdate();

                return true;

            }catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DB.closeStatement(ps);

            }
        }else if (alteracaoNumero == null) {
            try {

                ps = conn.prepareStatement(queryUpdate);

                ps.setString(1, alteracao);
                ps.setLong(2, u_id);

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
     * Metodo utilizador para pesquisar um utilizador por nome, login ou tipo
     * @param tipo
     * @param pesquisa
     * @return String utilizadores encontrados
     */
    public String pesquisarUtilizador (String tipo, String pesquisa) {

        pesquisa = "%" + pesquisa + "%";

        String query = "Select * from utilizador ";



        if (tipo.equalsIgnoreCase("nome")) {
            query = query + "where u_nome LIKE ? ";
        }

        if (tipo.equalsIgnoreCase("login") ) {
            query = query + "where u_login LIKE ? ";
        }

        if (tipo.equalsIgnoreCase("tipo")) {
            query = query + "where u_tipo LIKE ? ";
        }

        String lista ="";

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, pesquisa);
            rs = ps.executeQuery();


            while (rs.next()) {
                String nome = rs.getString("u_nome");
                String login = rs.getString("u_login");
                String u_tipo = rs.getString("u_tipo");

                lista += nome + " | " + login + " | " + u_tipo + " | \n";
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
     * Metodo que permite listar todos utlizadores por nome de forma Ascendente ou Descendente
     * @param sentido
     * @return String utilizadores
     */

    public String listarUtilizadores (String sentido) {
        String lista = "";
        String query = "select * from utilizador ";

        if(sentido.equalsIgnoreCase("DESC")){
            query = query + " ORDER BY u_nome DESC";
        }else if(sentido.equalsIgnoreCase("ASC")) {
            query = query + " ORDER BY u_nome ASC";
        }

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();


            while (rs.next()) {
                String nome = rs.getString("u_nome");
                String login = rs.getString("u_login");
                String u_tipo = rs.getString("u_tipo");

                lista += nome + " | " + login + " | " + u_tipo + " | \n";
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
     * Metodo que permite procurar se existem estafesta ou armazenistas na tabela de funcionarios
     * @param Procurar
     * @return String especializacao encontrados
     */
    public String procuraEstafeta (String Procurar) {
        String lista ="";
            try {
                ps = conn.prepareStatement("Select * from funcionario, utilizador where u_id = utilizador_u_id AND f_especizalizacao = ? AND u_estado = 'ativo'");
                ps.setString(1, Procurar);
                rs = ps.executeQuery();


                while (rs.next()) {
                    String nome = rs.getString("u_nome");
                    String login = rs.getString("u_login");
                    String especializacao = rs.getString("f_especializacao");

                    lista = nome + " | " + login + " | " + especializacao + " | ";
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
     * Metodo que permite procurar por contas contendo um certo tipo de estado
     * @param estado
     * @return String contas com o estado
     */

    public String contasInativas (String estado) {
        String lista ="";

            try {
                ps = conn.prepareStatement("Select * from utilizador where u_estado = ?");
                ps.setString(1, estado);
                rs = ps.executeQuery();


                while (rs.next()) {
                    String nome = rs.getString("u_nome");
                    String login = rs.getString("u_login");
                    String tipo = rs.getString("u_tipo");

                    lista += nome + " | " + login + " | " + tipo + "|\n";
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
     * Metodo que permite verificar se o login corresponde ao login da tabela utilizadores e a password com a password da tabela de utilizadores
     * @param login
     * @param password
     * @return retorna um Utilizador
     */
    public Utilizador login (String login, String password) {

        try {
            ps = conn.prepareStatement("Select * from utilizador where u_login = ?" +  " AND u_password = ?");

            ps.setString(1, login);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("u_nome");
                String estado = rs.getString("u_estado");
                String email = rs.getString("u_email");
                String tipo = rs.getString("u_tipo");

                Utilizador utilizador = new Utilizador(nome, login, password, estado, email, tipo);

                return utilizador;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return null;
    }

    /**
     * metodo que permite aceder os eventuais campos necessarios para criar um Funcionario
     * @param utilizador
     * @return Funcionario
     */

    public Funcionario loginFunc (Utilizador utilizador) {

        long id = u_id(utilizador.getLogin());

        try {
            ps = conn.prepareStatement("Select * from funcionario where utilizador_u_id = ?");

            ps.setLong(1, id);


            rs = ps.executeQuery();
            if (rs.next()) {
                long contribuinte = rs.getLong("f_contribuinte");
                long contacto = rs.getLong("f_contacto");
                String morada = rs.getString("f_morada");
                String especializacao = rs.getString("f_especializacao");
                Date datainicio = rs.getDate("f_datainicio");

                Funcionario funcionario = new Funcionario(utilizador.getNome(), utilizador.getLogin(), utilizador.getPassword(), utilizador.getEstado(), utilizador.getEmail(), utilizador.getTipo(), contribuinte, contacto, morada, especializacao, datainicio);

                return funcionario;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return null;
    }

    /**
     * metodo que retorna o ultimo ID inserido na tabela
     * @return long ultido id
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
     * Metodo que permite retornar o id do utilizador informando o seu login
     * @param login
     * @return long id do utilizador
     */

    public long u_id(String login) {

        try {
            ps = conn.prepareStatement("Select * from utilizador where u_login = ?" );

            ps.setString(1, login);


            rs = ps.executeQuery();
            if (rs.next()) {
                long id = rs.getLong("u_id");

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
     * Metodo que permite listar encomenda por data de criacao ou identificador
     * @param utilizador
     * @param tipo
     * @param sentido
     * @return String contendo encomendas
     */

    public String listarEncomendaCliente(Utilizador utilizador, String tipo, String sentido) {

        long id = u_id(utilizador.getLogin());

        String query = "select * from encomenda, encomenda_cliente where encomenda_e_id = e_id and cliente_utilizador_u_id = ?";

        if(tipo.equalsIgnoreCase("data") && sentido.equalsIgnoreCase("asc")) {
            query = query + " order by e_datacriacao ASC";
        }else if(tipo.equalsIgnoreCase("data") && sentido.equalsIgnoreCase("DESC")) {
            query = query + " order by e_datacriacao desc ";
        }

        if(tipo.equalsIgnoreCase("identificador") && sentido.equalsIgnoreCase("asc")) {
            query = query + " order by e_identificador ";
        }else if(tipo.equalsIgnoreCase("identificador") && sentido.equalsIgnoreCase("DESC")) {
            query = query + " order by e_identificador desc ";
        }

        String lista = "";
        try {
            ps = conn.prepareStatement(query);
            ps.setLong(1, id);
            rs = ps.executeQuery();


            while (rs.next()) {
                String identificador = rs.getString("e_identificador");
                Date data = rs.getDate("e_datacriacao");


                lista += identificador + " | " + data + " | \n";
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
     * metodo que permite pesquisar encomendas de um determinado cliente
     * @param tipo
     * @param pesquisa
     * @param utilizador
     * @param data
     * @return
     */

    public String pesquisarEncomendaCliente(String tipo, String pesquisa, Utilizador utilizador, java.util.Date data) { //por termianr


        long id = u_id(utilizador.getLogin());

        pesquisa = "%" + pesquisa + "%";

        String query = "Select * from encomenda, encomenda_cliente ";



        if (tipo.equalsIgnoreCase("identificador")) {
            query = query + "where e_identificador = ? and e_id = encomenda_e_id and cliente_utilizador_u_id = ?";
        }

        if (tipo.equalsIgnoreCase("datacriacao") ) {
            query = query + "where e_datacriacao = ? and e_id = encomenda_e_id and cliente_utilizador_u_id = ?";
        }

        if (tipo.equalsIgnoreCase("estado")) {
            query = query + "where u_tipo = ? and e_id = encomenda_e_id and cliente_utilizador_u_id = ?";
        }

        String lista ="";
        if(data == null) {
            try {
                ps = conn.prepareStatement(query);
                ps.setString(1, pesquisa);
                ps.setLong(2, id);
                rs = ps.executeQuery();


                while (rs.next()) {
                    String identificador = rs.getString("e_identificador");
                    Date datacriacao = rs.getDate("e_datacriacao");
                    String estado = rs.getString("e_estado");

                    lista += identificador + " | " + datacriacao + " | " + estado + " | \n";
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
                ps.setLong(2, id);

                rs = ps.executeQuery();


                while (rs.next()) {
                    String identificador = rs.getString("e_identificador");
                    Date datacriacao = rs.getDate("e_datacriacao");
                    String estado = rs.getString("e_estado");

                    lista += identificador + " | " + datacriacao + " | " + estado + " | \n";
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
     * Metodo que permite listar encomendas de um determinado Funcionario
     * @param utilizador
     * @param tipo
     * @param sentido
     * @return
     */

    public String listarEncomendaFuncionario(Utilizador utilizador, String tipo, String sentido) {

        long id = u_id(utilizador.getLogin());

        String query = "select * from encomenda, encomenda_funcionario where encomenda_e_id = e_id and funcionario_utilizador_u_id = ?";

        if(tipo.equalsIgnoreCase("data") && sentido.equalsIgnoreCase(null)) {
            query = query + " order by e_datacriacao ";
        }else if(tipo.equalsIgnoreCase("data") && sentido.equalsIgnoreCase("DESC")) {
            query = query + " order by e_datacriacao desc ";
        }

        if(tipo.equalsIgnoreCase("identificador") && sentido.equalsIgnoreCase(null)) {
            query = query + " order by e_identificador ";
        }else if(tipo.equalsIgnoreCase("identificador") && sentido.equalsIgnoreCase("DESC")) {
            query = query + " order by e_identificador desc ";
        }

        String lista = "";
        try {
            ps = conn.prepareStatement(query);
            ps.setLong(1, id);
            rs = ps.executeQuery();


            while (rs.next()) {
                String identificador = rs.getString("e_identificador");
                Date data = rs.getDate("e_datacriacao");


                lista += identificador + " | " + data + " | \n";
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
     * metodo que permite pesquisar por encomendas feitas por um Funcionario
     * @param tipo
     * @param pesquisa
     * @param utilizador
     * @param data
     * @return
     */
    public String pesquisarEncomendaFuncionario(String tipo, String pesquisa, Utilizador utilizador, java.util.Date data) { //por termianr


        long id = u_id(utilizador.getLogin());

        pesquisa = "%" + pesquisa + "%";

        String query = "Select * from encomenda, encomenda_funcionario ";



        if (tipo.equalsIgnoreCase("identificador")) {
            query = query + "where e_identificador = ? and e_id = encomenda_e_id and funcionario_utilizador_u_id = ?";
        }

        if (tipo.equalsIgnoreCase("datacriacao") ) {
            query = query + "where e_datacriacao = ? and e_id = encomenda_e_id and funcionario_utilizador_u_id = ?";
        }

        if (tipo.equalsIgnoreCase("estado")) {
            query = query + "where u_tipo = ? and e_id = encomenda_e_id and funcionario_utilizador_u_id = ?";
        }

        String lista ="";
        if(data == null) {
            try {
                ps = conn.prepareStatement(query);
                ps.setString(1, pesquisa);
                ps.setLong(2, id);
                rs = ps.executeQuery();


                while (rs.next()) {
                    String identificador = rs.getString("e_identificador");
                    Date datacriacao = rs.getDate("e_datacriacao");
                    String estado = rs.getString("e_estado");

                    lista += identificador + " | " + datacriacao + " | " + estado + " | \n";
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
                ps.setLong(2, id);

                rs = ps.executeQuery();


                while (rs.next()) {
                    String identificador = rs.getString("e_identificador");
                    Date datacriacao = rs.getDate("e_datacriacao");
                    String estado = rs.getString("e_estado");

                    lista += identificador + " | " + datacriacao + " | " + estado + " | \n";
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


}
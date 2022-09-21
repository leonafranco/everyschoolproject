package pt.ipc.estgoh.loja.database;

import pt.ipc.estgoh.loja.entities.Cliente;
import pt.ipc.estgoh.loja.entities.Funcionario;
import pt.ipc.estgoh.loja.entities.Utilizador;

import java.sql.*;
import java.text.SimpleDateFormat;

public class GereUtilizador {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public GereUtilizador (Connection conn) {
        this.conn = conn;
    }

    public boolean verificarUtilizador() {

        boolean verifica = false;

        try {
            st = conn.createStatement();
            rs = st.executeQuery("Select * from UILIZADORES");  // tens de corrigir isto

            if (rs.first() == true) {
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
    }


    public boolean inserirUtilizador(Utilizador utilizador) {
        boolean verifica = false;
        int rowsAffected = 0;

        try {

            ps = conn.prepareStatement(
                    "INSERT INTO UILIZADORES "   // tens de corrigir isto
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
    }

    public boolean inserirFuncionario(Funcionario funcionario) {
        boolean verifica = false;
        int rowsAffected = 0;

        try {


            ps = conn.prepareStatement(
                    "INSERT INTO FUNCIONARIO"
                            + "VALUES "
                            + "(U_nome, U_login, U_password, U_estado, U_email, U_tipo, F_Numerocontribuinte, F_Contacto, F_Morada, F_especializacao, F_DataDeInicio)"
                            + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");

            ps.setString(1,funcionario.getNome());
            ps.setString(2,funcionario.getLogin());
            ps.setString(3,funcionario.getPassword());
            ps.setString(4,funcionario.getEstado());
            ps.setString(5,funcionario.getEmail());
            ps.setString(6,funcionario.getTipo());
            ps.setInt(7,funcionario.getNumeroContribuinte());
            ps.setInt(8,funcionario.getContacto());
            ps.setString(9,funcionario.getMorada());
            ps.setInt(10,funcionario.getEspecializacao());
            ps.setDate(11, new java.sql.Date(funcionario.getDataDeInicio().getTime()));


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


    public boolean inserirCliente(Cliente cliente) {
        boolean verifica = false;
        int rowsAffected = 0;

        try {


            ps = conn.prepareStatement(
                    "INSERT INTO CLIENTE"
                            +"VALUES "
                            + "(U_nome, U_login, U_password, U_estado, U_email, U_tipo, C_Numerocontribuinte, C_Contacto, C_Morada, C_Escalao, C_SetorDeAtividade)"
                            + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");

            ps.setString(1,cliente.getNome());
            ps.setString(2,cliente.getLogin());
            ps.setString(3,cliente.getPassword());
            ps.setString(4,cliente.getEstado());
            ps.setString(5,cliente.getEmail());
            ps.setString(6,cliente.getTipo());
            ps.setInt(7,cliente.getNumeroContribuinte());
            ps.setInt(8,cliente.getContacto());
            ps.setString(9,cliente.getMorada());
            ps.setString(10,String.valueOf(cliente.getEscalao()));
            ps.setString(11, cliente.getSetorDeAtividade());


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
    
}
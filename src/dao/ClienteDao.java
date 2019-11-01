/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import persistence.ConnectionFactory;
import model.Cliente;
import model.Pedido;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alunos
 */
public class ClienteDao {

    private Connection connection;

    public ClienteDao() {

        this.connection = ConnectionFactory.getConnection();

    }

    public List<Cliente> sqlMake(String sql) {
        List<Cliente> clientes = new ArrayList<Cliente>();
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                Calendar data = Calendar.getInstance();
                cliente.setNome(rs.getString("nome"));
                data.setTime(rs.getDate("dataNascimento"));
                cliente.setDataNascimento(data);
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }

    public Boolean insereContact(Cliente cliente) {
        Boolean inserirComSucesso = false;
        String sql = "insert into clientes"
                + "(nome,dataNascimento,telefone,email)"
                + "values(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setDate(2, new Date(cliente.getDataNascimento().getTimeInMillis()));
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.execute();
            stmt.close();
            inserirComSucesso = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserirComSucesso;
    }

    public List<Cliente> consultaTodosClientes() {
        String sql = "SELECT * FROM clientes";
        return sqlMake(sql);
    }

    public List<Cliente> consultaClientesId(String id) {
        String sql = "SELECT * FROM clientes where id='" + id + "'";
        return sqlMake(sql);
    }

    public List<Cliente> consultaClientesNome(String nome) {
        String sql = "SELECT * FROM clientes WHERE nome LIKE '%" + nome + "%'";
        return sqlMake(sql);
    }

    public List<Cliente> consultaClientesData(String dataS) {
        String sql = "SELECT * FROM clientes WHERE dataNascimento = '" + dataS + "'";
        return sqlMake(sql);
    }

   public Boolean atualizaContato(Cliente cliente) {
        Boolean atualizarComSucesso = Boolean.FALSE;
        String sql = "UPDATE clientes set nome=?, dataNascimento=?, telefone=?,"
                + "email=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setDate(2, new Date(cliente.getDataNascimento().getTimeInMillis()));
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setLong(5, cliente.getId());
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println(linhasAfetadas);
            stmt.close();
            atualizarComSucesso = (linhasAfetadas>0);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return atualizarComSucesso;
    }
}

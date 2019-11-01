/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import persistence.ConnectionFactory;
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
import model.Cliente;

/**
 *
 * @author Alunos
 */
public class PedidoDao {

    private Connection connection;

    public PedidoDao() {
        this.connection = ConnectionFactory.getConnection();
    }

    public List<Pedido> SqlMake(String sql) {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getLong("id"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("dataEntrada"));
                pedido.setDataEntrada(data);
                pedido.setTipoServico(rs.getString("tipoServico"));
                pedido.setValor(rs.getDouble("valor"));
                pedido.setPlaca(rs.getString("placa"));
                pedidos.add(pedido);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedidos;
    }

    public Boolean inserePedido(Pedido pedido) {
        Boolean inserirComSucesso = false;
        String sql = "insert into pedidos"
                + "(dataEntrada,tipoServico,valor,placa)"
                + "values(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, new Date(pedido.getDataEntrada().getTimeInMillis()));
            stmt.setString(2, pedido.getTipoServico());
            stmt.setDouble(3, pedido.getValor());
            stmt.setString(4, pedido.getPlaca());
            stmt.execute();
            stmt.close();
            inserirComSucesso = true;
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserirComSucesso;
    }

    public List<Pedido> consultaPedidosDatas(String data1, String data2) {
        String sql = "SELECT * FROM pedidos WHERE dataEntrada BETWEEN '" + data1 + "' AND '" + data2 + "';";
        return SqlMake(sql);
    }

    public List<Pedido> consultaPedidosID(String id) {
        String sql = "SELECT * FROM pedidos WHERE id='" + id + "'";
        return SqlMake(sql);
    }

    public List<Pedido> consultaPedidosData(String dataS) {
        String sql = "SELECT * FROM pedidos WHERE dataEntrada='" + dataS + "'";
        return SqlMake(sql);
    }

    public List<Pedido> consultaPedidosTipo(String tipo) {
        String sql = "SELECT * FROM pedidos WHERE tipoServico='" + tipo + "'";
        return SqlMake(sql);
    }

    public List<Pedido> consultaPedidosPlaca(String placa) {
        String sql = "SELECT * FROM pedidos WHERE placa='" + placa + "'";
        return SqlMake(sql);
    }
    
     public List<Pedido> consultaTodosPedidos() {
        String sql = "SELECT * FROM pedidos";
        return SqlMake(sql);
    }

      public Boolean atualizaPedido(Pedido pedido) {
        Boolean atualizarComSucesso = Boolean.FALSE;
        String sql = "UPDATE pedidos set dataEntrada=?, tipoServico=?, valor=?,"
                + "placa=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, new Date(pedido.getDataEntrada().getTimeInMillis()));
            stmt.setString(2, pedido.getTipoServico());
            stmt.setDouble(3, pedido.getValor());
            stmt.setString(4, pedido.getPlaca());
            stmt.setLong(5, pedido.getId());
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println(linhasAfetadas);
            stmt.close();
            atualizarComSucesso = (linhasAfetadas>0);
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return atualizarComSucesso;
    }
     
}

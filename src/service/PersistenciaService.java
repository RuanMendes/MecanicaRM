/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import persistence.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alunos
 */
public class PersistenciaService {

    public void reaizaConexao() {
        Connection connection = ConnectionFactory.getConnection();
        try {
            if (connection != null && connection.isClosed()) {
                JOptionPane.showMessageDialog(null, "Conexão Fechada");
            } else {
                JOptionPane.showMessageDialog(null, "Conexão Aberta");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

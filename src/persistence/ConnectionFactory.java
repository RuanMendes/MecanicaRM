/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruan
 * @version 1.0 beta
 * @since 30/04/2019
 */
public class ConnectionFactory {
    //Método que efetua a conexão com o MySQL
    public static Connection getConnection(){
        Connection c = null;
        String url = "jdbc:mysql://localhost/mecanicadb?useTimezone=false&serverTimezone=UTC";
        String user = "root";
        String password = "";
        try {
            c = DriverManager.getConnection(url,user,password);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
}

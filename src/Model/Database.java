/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author misaellopes01
 */
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Database {
    
    public Connection conexao(){
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/parque?user=@misaellopes01&password=Elieser2001";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na Class Database: " + e.getMessage());
        }
        return conn;
    }
 
}

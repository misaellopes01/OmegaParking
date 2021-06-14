/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.UsuarioController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author misaellopes01
 */
public class UsuarioData {
    Connection conn;
    
    public ResultSet userAuth(UsuarioController usuarioOBJ){
        conn = new Database().conexao();
        
        try {
            String sql = "SELECT * FROM `funcionarios` WHERE username = ? and password = ?";
            
            PreparedStatement pst = conn.prepareStatement(sql);
            // O primeiro parâmetro é para indicar a ordem do que estivermos a pegar na database... Pontos de ?
            pst.setString(1, usuarioOBJ.getUsurname());
            pst.setString(2, usuarioOBJ.getPassword());
            
            ResultSet rs = pst.executeQuery();
            return rs;
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class UsuarioData: " + e);
            return null;
        }
        
    }
            
}

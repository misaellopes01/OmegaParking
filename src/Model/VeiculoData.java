/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.ClienteController;
import Controller.VeiculoController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author misaellopes01
 */
public class VeiculoData {
    Connection conn;
    PreparedStatement pst;
    
    public void InsertFunc(VeiculoController veiculoOBJ){
        String sql = "INSERT INTO `veiculos` (matricula, client_id) VALUES (?,?)";
        
        conn = new Database().conexao();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, veiculoOBJ.getMarticula());
            pst.setInt(2, veiculoOBJ.getId_prop());
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Cadastro Efectuado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class VeiculoData -> Função Insert: " + e.getMessage());
        }
    }
    
    public void AlterCar(VeiculoController veiculoOBJ){
        String sql = "UPDATE `veiculos` SET matricula = ? WHERE id = ?;";
        
        conn = new Database().conexao();
        try {
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, veiculoOBJ.getMarticula());
            pst.setInt(2, veiculoOBJ.getId_prop());
            
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Dados Atualizados Com Sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class VeiculoData -> Atualizar: " + e);
        }
    }
}

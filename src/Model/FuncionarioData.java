/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.FuncionarioController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author misaellopes01
 */
public class FuncionarioData {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    ArrayList<FuncionarioController> lista = new ArrayList<>();
    
    public void InsertFunc(FuncionarioController funcionarioOBJ){
        String sql = "INSERT INTO `funcionarios` (username, password, fullname) VALUES (?,?,?)";
        
        conn = new Database().conexao();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, funcionarioOBJ.getFuncUsername());
            pst.setString(2, funcionarioOBJ.getFuncPassword());
            pst.setString(3, funcionarioOBJ.getFuncFullname());
            
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Cadastro Efectuado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class FuncionarioData -> Inserir: " + e);
        }
    }
    
    public ArrayList<FuncionarioController> ListFunc(){
        String sql = "SELECT * FROM `funcionarios`";
        conn = new Database().conexao();
        
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                FuncionarioController funcionarioOBJ = new FuncionarioController();
                funcionarioOBJ.setId(rs.getInt("id"));
                funcionarioOBJ.setFuncUsername(rs.getString("username"));
                funcionarioOBJ.setFuncPassword(rs.getString("password"));
                funcionarioOBJ.setFuncFullname(rs.getString("fullname"));
                
                lista.add(funcionarioOBJ);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class FuncionarioData -> Listar: " + e);
        }
        return lista;
    }
    
    public void AlterFunc(FuncionarioController funcionarioOBJ){
        String sql = "UPDATE `funcionarios` SET username = ?, password = ?, fullname = ? WHERE id = ?;";
        
        conn = new Database().conexao();
        try {
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, funcionarioOBJ.getFuncUsername());
            pst.setString(2, funcionarioOBJ.getFuncPassword());
            pst.setString(3, funcionarioOBJ.getFuncFullname());
            pst.setInt(4, funcionarioOBJ.getId());
            
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Dados Atualizados Com Sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class FuncionarioData -> Atualizar: " + e);
        }
    }
    
    public void DeleteFunc(FuncionarioController funcionarioOBJ){
        String sql = "DELETE FROM `funcionarios` WHERE id = ?;";
        
        conn = new Database().conexao();
        try {
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, funcionarioOBJ.getId());
            
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Dados Eliminados Com Sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class FuncionarioData -> Eliminar: " + e);
        }
    }

  
}

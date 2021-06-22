/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.ClienteController;
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
public class ClientData {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    ArrayList<ClienteController> lista = new ArrayList<>();
    
    
    public void InsertFunc(ClienteController clientOBJ){
        String sql = "INSERT INTO `clientes` (nome, sobrenome, tipo) VALUES (?,?,?);";
        
        conn = new Database().conexao();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, clientOBJ.getFirstname());
            pst.setString(2, clientOBJ.getSurname());
            pst.setString(3, clientOBJ.getType());
            
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Cadastro Efectuado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class ClientData -> Dunção Insert: " + e.getMessage());
        }
    }
    
    public ResultSet propFunc(){
        conn = new Database().conexao();
        String sql = "SELECT id, CONCAT(nome, ' ' , sobrenome) AS Nomes FROM clientes;";
        
        try {
            pst = conn.prepareStatement(sql);
            return pst.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class ClientData -> Função Proprietário: " + e.getMessage());
            return null;
        }
    }
    public ResultSet propFuncData(){
        conn = new Database().conexao();
        String sql = "SELECT clientes.id, CONCAT(clientes.nome, ' ' , clientes.sobrenome) AS nome FROM clientes ORDER BY nome;";
        
        try {
            pst = conn.prepareStatement(sql);
            return pst.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class ClientData -> Função Dados de Proprietário: " + e.getMessage());
            return null;
        }
    }
    
    public void AlterClient(ClienteController clientOBJ){
        String sql = "UPDATE `clientes` SET nome = ?, sobrenome = ?, tipo = ? WHERE id = ?;";
        
        conn = new Database().conexao();
        try {
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, clientOBJ.getFirstname());
            pst.setString(2, clientOBJ.getSurname());
            pst.setString(3, clientOBJ.getType());
            pst.setInt(4, clientOBJ.getID());
            
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Dados Atualizados Com Sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class FuncionarioData -> Atualizar: " + e);
        }
    }
    
    
    
}

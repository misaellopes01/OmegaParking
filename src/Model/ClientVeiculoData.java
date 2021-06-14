/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.ClientVeiculoController;
import Controller.ClienteController;
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
public class ClientVeiculoData {
    
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    ArrayList<ClientVeiculoController> lista = new ArrayList<>();
    
    
    public ArrayList<ClientVeiculoController> ListFunc(){
        String sql = "SELECT clientes.id AS ID, clientes.nome AS Nome, clientes.sobrenome AS Sobrenome, clientes.tipo AS Tipo, veiculos.id AS veiID , veiculos.matricula AS Matricula FROM clientes INNER JOIN veiculos ON clientes.id = veiculos.client_id ORDER BY Nome;";
        conn = new Database().conexao();
        
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                ClientVeiculoController clientOBJ = new ClientVeiculoController();
                clientOBJ.setID(rs.getInt("ID"));
                clientOBJ.setNome(rs.getString("Nome"));
                clientOBJ.setSobrenome(rs.getString("Sobrenome"));
                clientOBJ.setTipo(rs.getString("Tipo"));
                clientOBJ.setMatrícula(rs.getString("Matricula"));
                clientOBJ.setVeiID(rs.getInt("veiID"));
                
                lista.add(clientOBJ);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class FuncionarioData -> Listar Usuários: " + e);
        }
        return lista;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.EstacionamentoController;
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
public class EstacionamentoData {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    ArrayList<EstacionamentoController> lista = new ArrayList<>();
    
    public ResultSet ListarTotal(){
        String sql = "SELECT COUNT(1) AS Total FROM estacionamento;";
        conn = new Database().conexao();
        
        try {
            pst = conn.prepareStatement(sql);
            return pst.executeQuery();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class EstacionamentoData -> Total: " + e);
            return null;
        }
        
    }
    public ResultSet ListarOcupadas(){
        String sql = "SELECT COUNT(estacionamento.id) AS Ocupadas FROM estacionamento, fluxo WHERE fluxo.estacionamento_id = estacionamento.id AND fluxo.data_saida is NULL;";
        conn = new Database().conexao();
        
        try {
            pst = conn.prepareStatement(sql);
            return pst.executeQuery();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class EstacionamentoData -> Ocupadas: " + e);
            return null;
        }
        
    }
    
    public ResultSet listCar(){
        conn = new Database().conexao();
        String sql = "SELECT CONCAT(clientes.nome, ' ', clientes.sobrenome) AS Nome, veiculos.id AS ID_Veiculo, veiculos.matricula AS Matricula FROM clientes, veiculos WHERE clientes.id = veiculos.client_id;";
        
        try {
            pst = conn.prepareStatement(sql);
            return pst.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class EstacionamentoData -> Função listCar: " + e.getMessage());
            return null;
        }
    }
    
    public ResultSet listVaga(){
        conn = new Database().conexao();
        String sql = "SELECT id, CONCAT('Vaga [ ',id,' ]') AS Vagas FROM estacionamento WHERE id NOT IN \n" +
"(SELECT estacionamento_id FROM fluxo) OR id NOT IN\n" +
"(SELECT estacionamento_id FROM fluxo WHERE data_saida IS NULL);";
        
        try {
            pst = conn.prepareStatement(sql);
            return pst.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class EstacionamentoData -> Função listVaga: " + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<EstacionamentoController> ListFlux(){
        String sql = "SELECT fluxo.id AS ID, CONCAT(clientes.nome, ' ' , clientes.sobrenome) AS Proprietario, \n" +
"veiculos.matricula AS Veiculo, CONCAT(EXTRACT(DAY FROM fluxo.data_entrada),'/', EXTRACT(MONTH FROM fluxo.data_entrada),\n" +
"'/',EXTRACT(YEAR FROM fluxo.data_entrada),' ', EXTRACT(HOUR FROM fluxo.data_entrada), 'h:',\n" +
"EXTRACT(MINUTE FROM fluxo.data_entrada)) AS Entrada, CONCAT(EXTRACT(DAY FROM fluxo.data_saida),'/', \n" +
"EXTRACT(MONTH FROM fluxo.data_saida),'/',EXTRACT(YEAR FROM fluxo.data_saida),' ', EXTRACT(HOUR FROM fluxo.data_saida), \n" +
"'h:',EXTRACT(MINUTE FROM fluxo.data_saida)) AS Saida FROM fluxo, clientes, \n" +
"veiculos WHERE fluxo.veiculo_id = veiculos.id AND clientes.id = veiculos.client_id ORDER BY fluxo.data_entrada;";
        conn = new Database().conexao();
        
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                EstacionamentoController estaOBJ = new EstacionamentoController();
                estaOBJ.setID(rs.getInt("ID"));
                estaOBJ.setProprietario(rs.getString("Proprietario"));
                estaOBJ.setVeiculo(rs.getString("Veiculo"));
                estaOBJ.setEntrada(rs.getString("Entrada"));
                estaOBJ.setSaida(rs.getString("Saida"));
                
                lista.add(estaOBJ);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class EstacionamentoData -> ListFlux: " + e);
        }
        return lista;
    }
    
    public void InsertEntrada(EstacionamentoController estacionamentoController){
        String sql = "INSERT INTO `fluxo` (veiculo_id, estacionamento_id) VALUES (?,?)";
        
        conn = new Database().conexao();
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, estacionamentoController.getId_carro());
            pst.setInt(2, estacionamentoController.getId_lugar());
            
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Entrada Registada!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class EstacionamentoData -> InsertEntrada: " + e);
        }
    }
    
    public void RegistSaida(EstacionamentoController estacionamentoController){
        String sql = "UPDATE `fluxo` SET `data_saida`=CURRENT_TIMESTAMP WHERE id=?;";
        
        conn = new Database().conexao();
        try {
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, estacionamentoController.getFluxo_id());
            
            pst.execute();
            pst.close();
            JOptionPane.showMessageDialog(null, "Saída Registada!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class EstacionamentoData -> RegistSaida: " + e);
        }
    }
    
    public ArrayList<EstacionamentoController> ListFluxFull(){
        String sql = "SELECT CONCAT(clientes.nome, ' ' , clientes.sobrenome) AS Proprietario, clientes.tipo AS Tipo, \n" +
"veiculos.matricula AS Veiculo, CONCAT(EXTRACT(DAY FROM fluxo.data_entrada),'/', \n" +
"EXTRACT(MONTH FROM fluxo.data_entrada),'/',EXTRACT(YEAR FROM fluxo.data_entrada),' ', \n" +
"EXTRACT(HOUR FROM fluxo.data_entrada), 'h:',EXTRACT(MINUTE FROM fluxo.data_entrada)) AS Entrada, \n" +
"CONCAT(EXTRACT(DAY FROM fluxo.data_saida),'/', EXTRACT(MONTH FROM fluxo.data_saida),'/',\n" +
"EXTRACT(YEAR FROM fluxo.data_saida),' ', EXTRACT(HOUR FROM fluxo.data_saida), 'h:',\n" +
"EXTRACT(MINUTE FROM fluxo.data_saida)) AS Saida, CONCAT(HOUR(TIMEDIFF(fluxo.data_saida, fluxo.data_entrada)), \n" +
"' h') AS Horas FROM fluxo, clientes, veiculos WHERE fluxo.veiculo_id = veiculos.id \n" +
"AND clientes.id = veiculos.client_id ORDER BY fluxo.data_entrada;";
        conn = new Database().conexao();
        
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                EstacionamentoController estaOBJ = new EstacionamentoController();
                estaOBJ.setProprietario(rs.getString("Proprietario"));
                estaOBJ.setTipo(rs.getString("Tipo"));
                estaOBJ.setVeiculo(rs.getString("Veiculo"));
                estaOBJ.setEntrada(rs.getString("Entrada"));
                estaOBJ.setSaida(rs.getString("Saida"));
                estaOBJ.setTotalHora(rs.getString("Horas"));
                lista.add(estaOBJ);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class EstacionamentoData -> ListFlux: " + e);
        }
        return lista;
    }
    
    public ResultSet getDataFunc(){
        conn = new Database().conexao();
        String sql = "SELECT DISTINCT DATE(data_entrada) AS Datas FROM fluxo;";
        
        try {
            pst = conn.prepareStatement(sql);
            return pst.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class EstacionamentoData -> Função getDataFunc: " + e.getMessage());
            return null;
        }
    }
    
    public ResultSet getDataResultFunc(EstacionamentoController estacionamentoController){
        conn = new Database().conexao();
        String sql = "SELECT COUNT(id), COUNT(data_entrada), COUNT(data_saida)  FROM fluxo WHERE DATE(data_entrada)=?";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, estacionamentoController.getTotalUsoCar());
            
            return pst.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class EstacionamentoData -> Função getDataResultFunc: " + e.getMessage());
            return null;
        }
    }
    
    public ResultSet getDataResultMediaFunc(EstacionamentoController estacionamentoController){
        conn = new Database().conexao();
        String sql = "SELECT CAST(AVG(CONCAT(HOUR(TIMEDIFF(fluxo.data_saida, fluxo.data_entrada)))) AS DECIMAL(10,1)) AS Media\n" +
"FROM fluxo\n" +
"WHERE veiculo_id=?;";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, estacionamentoController.getId_carro());
            
            return pst.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Class EstacionamentoData -> Função getDataResultMediaFunc: " + e.getMessage());
            return null;
        }
    }
    
}

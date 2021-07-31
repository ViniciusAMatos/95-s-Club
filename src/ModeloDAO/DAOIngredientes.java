/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import ModeloBeans.BeansIngredientes;
import ModeloConection.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author MARCOS RICHARD
 */
public class DAOIngredientes {
    private Connection conexao = null;
    
    public DAOIngredientes(){
        try {
            conexao = ConexaoBD.getConexao();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Estabelecer Conexão!\nErro: " + ex);
        }
    }
    
    public void Salvar(BeansIngredientes tmIgredientes) throws SQLException, ClassNotFoundException {

        // Prepara conexão p/ receber o comando SQL
        String sql = "INSERT INTO ingredientes(nome, quant, valor)"
                + "VALUES(?, ?, ?)"; 
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, tmIgredientes.getNome());
            stmt.setInt(2, tmIgredientes.getQuant());
            stmt.setFloat(3, tmIgredientes.getValor());
          
            JOptionPane.showMessageDialog(null, "Dados Inseridos Com Sucesso!");
            stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }
        

    }
    
    public List<BeansIngredientes> getLista(String nome) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "SELECT * FROM ingredientes WHERE nome like ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        List<BeansIngredientes> listaC = null;
        try {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
           
            listaC = new ArrayList<>();
            // Enquanto existir registros, pega os valores do ReultSet e vai adicionando na lista

            while (rs.next()) {
                //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de registros p/ a lista
                BeansIngredientes Ingredientes = new BeansIngredientes();
                // "Cliente" -> Cliente novo - .setNome recebe o campo do banco de String "nome" 
                Ingredientes.setId(Integer.valueOf(rs.getString("id")));
                Ingredientes.setNome(rs.getString("nome"));
                Ingredientes.setQuant(rs.getInt("quant"));
                Ingredientes.setValor(rs.getFloat("valor"));
                
                
                // Adiciona o registro na lista
                listaC.add(Ingredientes);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }
        return listaC;
    }
    
    public void remove(int id) throws SQLException {       
        // Prepara conexão p/ receber o comando SQL
        String sql = "DELETE FROM ingredientes WHERE id=?";
        // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
        try ( // stmt recebe o comando SQL
            PreparedStatement stmt = this.conexao.prepareStatement(sql)) {
            // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
            stmt.setInt(1, id);
            
            // Executa o codigo SQL, e fecha
            stmt.execute();
        }
        
    }
    
    public void altera(BeansIngredientes tmIngredientes) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "UPDATE ingredientes SET nome=?, quant=?, valor=?" + "WHERE id=?";

        // Seta os valores p/ o stmt, substituindo os "?"
        try ( // stmt recebe o comando SQL
                PreparedStatement stmt = this.conexao.prepareStatement(sql)) {
            // Seta os valores p/ o stmt, substituindo os "?"
           
            stmt.setString(1, tmIngredientes.getNome());
            stmt.setFloat(2, tmIngredientes.getQuant());
            stmt.setFloat(3, tmIngredientes.getValor());
            stmt.setInt(4, tmIngredientes.getId());
    

            // O stmt executa o comando SQL no BD, e fecha a conexão
            stmt.execute();
        }
    }
    
}

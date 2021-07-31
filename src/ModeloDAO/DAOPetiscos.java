/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import ModeloBeans.BeansBebidas;
import ModeloBeans.BeansPetiscos;
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
public class DAOPetiscos {
    private Connection conexao = null;
    
    public DAOPetiscos(){
        try {
            conexao = ConexaoBD.getConexao();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Estabelecer Conexão!\nErro: " + ex);
        }
    }
    
    public void Salvar(BeansPetiscos tmPetiscos) throws SQLException, ClassNotFoundException {

        // Prepara conexão p/ receber o comando SQL
        String sql = "INSERT INTO petiscos(nome, descricao, valor)"
                + "VALUES(?, ?, ?)"; 
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, tmPetiscos.getNome());
            stmt.setString(2, tmPetiscos.getDescricao());
            stmt.setFloat(3, tmPetiscos.getValor());
          
            JOptionPane.showMessageDialog(null, "Dados Inseridos Com Sucesso!");
            stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }
        

    }
    
    public List<BeansPetiscos> getLista(String nome) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "SELECT * FROM petiscos WHERE nome like ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        List<BeansPetiscos> listaC = null;
        try {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
           
            listaC = new ArrayList<>();
            // Enquanto existir registros, pega os valores do ReultSet e vai adicionando na lista

            while (rs.next()) {
                //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de registros p/ a lista
                BeansPetiscos Petiscos = new BeansPetiscos();
                // "Cliente" -> Cliente novo - .setNome recebe o campo do banco de String "nome" 
                Petiscos.setId(Integer.valueOf(rs.getString("id")));
                Petiscos.setNome(rs.getString("nome"));
                Petiscos.setDescricao(rs.getString("descricao"));
                Petiscos.setValor(rs.getFloat("valor"));



                // Adiciona o registro na lista
                listaC.add(Petiscos);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }
        return listaC;
    }
    
    public void remove(int id) throws SQLException {       
        // Prepara conexão p/ receber o comando SQL
        String sql = "DELETE FROM petiscos WHERE id=?";
        // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
        try ( // stmt recebe o comando SQL
            PreparedStatement stmt = this.conexao.prepareStatement(sql)) {
            // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
            stmt.setInt(1, id);
            
            // Executa o codigo SQL, e fecha
            stmt.execute();
        }
        
    }
    
    public void altera(BeansPetiscos tmPetiscos) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "UPDATE petiscos SET nome=?, descricao=?, valor=?" + "WHERE id=?";

        // Seta os valores p/ o stmt, substituindo os "?"
        try ( // stmt recebe o comando SQL
                PreparedStatement stmt = this.conexao.prepareStatement(sql)) {
            // Seta os valores p/ o stmt, substituindo os "?"
           
            stmt.setString(1, tmPetiscos.getNome());
            stmt.setString(2, tmPetiscos.getDescricao());
            stmt.setFloat(3, tmPetiscos.getValor());
            stmt.setInt(4, tmPetiscos.getId());


            // O stmt executa o comando SQL no BD, e fecha a conexão
            stmt.execute();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import ModeloBeans.BeansIngressos;
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
 * @author vinic
 */
public class DAOIngressos {
     private Connection conexao = null;
    
    public DAOIngressos(){
        try {
            conexao = ConexaoBD.getConexao();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Estabelecer Conexão!\nErro: " + ex);
        }
    }
    
    public void Salvar(BeansIngressos tmIngressos) throws SQLException, ClassNotFoundException {

        // Prepara conexão p/ receber o comando SQL
        String sql = "INSERT INTO ingressos(nome, valor)"
                + "VALUES(?, ?)"; 
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, tmIngressos.getNome());
            stmt.setFloat(2, tmIngressos.getValor());
          
            JOptionPane.showMessageDialog(null, "Dados Inseridos Com Sucesso!");
            stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }
        

    }
    
    public List<BeansIngressos> getLista(String nome) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "SELECT * FROM ingressos WHERE nome like ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        List<BeansIngressos> listaC = null;
        try {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
           
            listaC = new ArrayList<>();
            // Enquanto existir registros, pega os valores do ReultSet e vai adicionando na lista

            while (rs.next()) {
                //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de registros p/ a lista
                BeansIngressos Ingressos = new BeansIngressos();
                // "Cliente" -> Cliente novo - .setNome recebe o campo do banco de String "nome" 
                Ingressos.setId(Integer.valueOf(rs.getString("id")));
                Ingressos.setNome(rs.getString("nome"));
                Ingressos.setValor(rs.getFloat("valor"));



                // Adiciona o registro na lista
                listaC.add(Ingressos);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }
        return listaC;
    }
    
    public void remove(int id) throws SQLException {       
        // Prepara conexão p/ receber o comando SQL
        String sql = "DELETE FROM ingressos WHERE id=?";
        // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
        try ( // stmt recebe o comando SQL
            PreparedStatement stmt = this.conexao.prepareStatement(sql)) {
            // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
            stmt.setInt(1, id);
            
            // Executa o codigo SQL, e fecha
            stmt.execute();
        }
        
    }
    
    public void altera(BeansIngressos tmIngressos) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "UPDATE ingressos SET nome=?, valor=?" + "WHERE id=?";

        // Seta os valores p/ o stmt, substituindo os "?"
        try ( // stmt recebe o comando SQL
                PreparedStatement stmt = this.conexao.prepareStatement(sql)) {
            // Seta os valores p/ o stmt, substituindo os "?"
           
            stmt.setString(1, tmIngressos.getNome());
            stmt.setFloat(2, tmIngressos.getValor());
            stmt.setInt(3, tmIngressos.getId());
    

            // O stmt executa o comando SQL no BD, e fecha a conexão
            stmt.execute();
        }
    }

    
}


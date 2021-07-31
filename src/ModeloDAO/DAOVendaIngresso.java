/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import ModeloBeans.BeansVendaIngresso;
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
public class DAOVendaIngresso {
    private Connection conexao = null;

    // Estabelece uma conexão
    public DAOVendaIngresso() {
        try {
            conexao = ConexaoBD.getConexao();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Estabelecer Conexão!\nErro: " + ex);
        }
    }

    /* <-CONEXÃO COM O BD---- */
 /* ----CLIENTE-> */

    // CREATE - Adiciona um registro
    public void Salvar(BeansVendaIngresso tmVendaIngresso) throws SQLException, ClassNotFoundException {

        // Prepara conexão p/ receber o comando SQL
        String sql = "INSERT INTO VendaIngresso(nome, cpf, nomeI, valor,dataCompra)"
                + "VALUES(?, ?, ?, ?,?)"; 
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, tmVendaIngresso.getNome());
            stmt.setString(2, tmVendaIngresso.getCpf());
            stmt.setString(3, tmVendaIngresso.getNomeIng());
            stmt.setFloat(4, tmVendaIngresso.getValor());
            stmt.setString(5, tmVendaIngresso.getDataCompra());
          
            JOptionPane.showMessageDialog(null,"Venda Realizada Com Sucesso!!");
            stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }
        

    }

    public List<BeansVendaIngresso> getListaIngressos(String nome) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "SELECT * FROM VendaIngresso WHERE nome like ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        List<BeansVendaIngresso> listaC = null;
        try {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
           
            listaC = new ArrayList<>();
            // Enquanto existir registros, pega os valores do ReultSet e vai adicionando na lista

            while (rs.next()) {
                //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de registros p/ a lista
                BeansVendaIngresso VendaIngresso = new BeansVendaIngresso();
                // "Cliente" -> Cliente novo - .setNome recebe o campo do banco de String "nome" 
                VendaIngresso.setId(rs.getInt("id"));
                VendaIngresso.setNome(rs.getString("nome"));
                VendaIngresso.setCpf(rs.getString("cpf"));
                VendaIngresso.setNomeIng(rs.getString("nomeI"));
                VendaIngresso.setValor(rs.getFloat("valor"));
                VendaIngresso.setDataCompra(rs.getString("dataCompra"));



                // Adiciona o registro na lista
                listaC.add(VendaIngresso);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }
        return listaC;
    }
    public void remove(int id) throws SQLException {       
        // Prepara conexão p/ receber o comando SQL
        String sql = "DELETE FROM VendaIngresso WHERE id=?";
        // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
        try ( // stmt recebe o comando SQL
            PreparedStatement stmt = this.conexao.prepareStatement(sql)) {
            // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
            stmt.setInt(1, id);
            
            // Executa o codigo SQL, e fecha
            stmt.execute();
        }
        
    }
}
    
    
    
    
  
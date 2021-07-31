/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import ModeloBeans.BeansLogin;
import ModeloBeans.BeansVendaIngresso;
import ModeloBeans.BeansVendaPetiscos;
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
public class DAOVendaPetiscos {
    private Connection conexao = null;
    public DAOVendaPetiscos() {
        try {
            conexao = ConexaoBD.getConexao();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Estabelecer Conexão!\nErro: " + ex);
        }
    }
    
    
    
        public void Salvar(BeansVendaPetiscos tmVendaPetiscos) throws SQLException, ClassNotFoundException {

        // Prepara conexão p/ receber o comando SQL
        String sql = "INSERT INTO VendaPetiscos(nome, cpf,nomeI,descricao,quantidade,valor,dataCompra)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?)"; 
        PreparedStatement stmt = null;
        BeansLogin s = new BeansLogin();
        try {
            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, tmVendaPetiscos.getNome());
            stmt.setString(2, tmVendaPetiscos.getCpf());
            stmt.setString(3, tmVendaPetiscos.getNomeIng());
            stmt.setString(4, tmVendaPetiscos.getDescricao());
            stmt.setInt(5, tmVendaPetiscos.getQuantidade());
            stmt.setFloat(6, tmVendaPetiscos.getValor());
            stmt.setString(7, tmVendaPetiscos.getDataCompra());
            atualizaSaldoSQL(s.getCredito(), s.getLogin());

          
            JOptionPane.showMessageDialog(null,"Venda Realizada Com Sucesso!!");
            stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }
        

    }

    public List<BeansVendaPetiscos> getListaPetiscos(String nome) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "SELECT * FROM VendaPetiscos WHERE nome like ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        List<BeansVendaPetiscos> listaC = null;
        try {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
           
            listaC = new ArrayList<>();
            // Enquanto existir registros, pega os valores do ReultSet e vai adicionando na lista

            while (rs.next()) {
                //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de registros p/ a lista
                BeansVendaPetiscos VendaPetiscos = new BeansVendaPetiscos();
                // "Cliente" -> Cliente novo - .setNome recebe o campo do banco de String "nome" 
                VendaPetiscos.setId(rs.getInt("id"));
                VendaPetiscos.setNome(rs.getString("nome"));
                VendaPetiscos.setCpf(rs.getString("cpf"));
                VendaPetiscos.setNomeIng(rs.getString("nomeI"));
                VendaPetiscos.setDescricao(rs.getString("descricao"));
                VendaPetiscos.setQuantidade(rs.getInt("quantidade"));
                VendaPetiscos.setValor(rs.getFloat("valor"));
                VendaPetiscos.setDataCompra(rs.getString("dataCompra"));



                // Adiciona o registro na lista
                listaC.add(VendaPetiscos);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }
        return listaC;
    }
    
    private void atualizaSaldoSQL(float saldo, String login) throws SQLException{
        String sql = "UPDATE cliente SET credito=? where login=?";
        BeansLogin log = new BeansLogin();
        try(PreparedStatement stmt = this.conexao.prepareStatement(sql)){
            stmt.setFloat(1, saldo);
            stmt.setString(2, login);
            stmt.execute();
        }
        
    }
    
    private void atualizaEstoqueSQL(int estoque, String nome) throws SQLException{
        String sql = "UPDATE bebidas SET quant=? where nome=?";
        try(PreparedStatement stmt = this.conexao.prepareStatement(sql)){
            stmt.setFloat(1, estoque);
            stmt.setString(2, nome);
            stmt.execute();
        }
        
    }
}

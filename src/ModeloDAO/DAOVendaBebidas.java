/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import ModeloBeans.BeansBebidas;
import ModeloBeans.BeansLogin;
import ModeloBeans.BeansVendaBebidas;
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
public class DAOVendaBebidas {
    private Connection conexao = null;
    public DAOVendaBebidas() {
        try {
            conexao = ConexaoBD.getConexao();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Estabelecer Conexão!\nErro: " + ex);
        }
    }
    
    
    
        public void Salvar(BeansVendaBebidas tmVendaBebidas, BeansBebidas tmBebidas) throws SQLException, ClassNotFoundException {

        // Prepara conexão p/ receber o comando SQL
        String sql = "INSERT INTO VendaBebidas(nome, cpf,nomeI,quantidade,valor,dataCompra)"
                + "VALUES(?, ?, ?, ?, ?, ?)"; 
        PreparedStatement stmt = null;
        BeansLogin s = new BeansLogin();
        try {
            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, tmVendaBebidas.getNome());
            stmt.setString(2, tmVendaBebidas.getCpf());
            stmt.setString(3, tmVendaBebidas.getNomeIng());
            stmt.setInt(4, tmVendaBebidas.getQuantidade());
            stmt.setFloat(5, tmVendaBebidas.getValor());
            stmt.setString(6, tmVendaBebidas.getDataCompra());
            atualizaSaldoSQL(s.getCredito(), s.getLogin());
            atualizaEstoqueSQL(tmBebidas.getQuant(), tmVendaBebidas.getNomeIng());
          
            JOptionPane.showMessageDialog(null,"Venda Realizada Com Sucesso!!");
            stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }
        

    }

    public List<BeansVendaBebidas> getListaBebidas(String nome) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "SELECT * FROM VendaBebidas WHERE nome like ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        List<BeansVendaBebidas> listaC = null;
        try {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
           
            listaC = new ArrayList<>();
            // Enquanto existir registros, pega os valores do ReultSet e vai adicionando na lista

            while (rs.next()) {
                //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de registros p/ a lista
                BeansVendaBebidas VendaBebidas = new BeansVendaBebidas();
                // "Cliente" -> Cliente novo - .setNome recebe o campo do banco de String "nome" 
                VendaBebidas.setId(rs.getInt("id"));
                VendaBebidas.setNome(rs.getString("nome"));
                VendaBebidas.setCpf(rs.getString("cpf"));
                VendaBebidas.setNomeIng(rs.getString("nomeI"));
                VendaBebidas.setQuantidade(rs.getInt("quantidade"));
                VendaBebidas.setValor(rs.getFloat("valor"));
                VendaBebidas.setDataCompra(rs.getString("dataCompra"));



                // Adiciona o registro na lista
                listaC.add(VendaBebidas);
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

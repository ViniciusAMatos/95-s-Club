/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import ModeloBeans.BeansBebidas;
import ModeloBeans.BeansLogin;
import ModeloBeans.BeansRecargaCredito;
import ModeloBeans.BeansVendaBebidas;
import ModeloConection.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author vinic
 */
public class DAORecargaCredito {
    private Connection conexao = null;
     public DAORecargaCredito() {
        try {
            conexao = ConexaoBD.getConexao();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Estabelecer Conexão!\nErro: " + ex);
        }
    }
     
     public void Salvar(BeansRecargaCredito tmRecargaCredito) throws SQLException, ClassNotFoundException {

        // Prepara conexão p/ receber o comando SQL
        String sql = "INSERT INTO RecargaCredito(id, nomeFunc, login, nomecliente, logincliente, valor, dataRecarga)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?)"; 
        PreparedStatement stmt = null;
        BeansLogin s = new BeansLogin();
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, tmRecargaCredito.getIdF());
            stmt.setString(2, tmRecargaCredito.getNomefuncionario());
            stmt.setString(3, tmRecargaCredito.getLoginfuncionario());
            stmt.setString(4, tmRecargaCredito.getNomecliente());
            stmt.setString(5, tmRecargaCredito.getLogincliente());
            stmt.setFloat(6, tmRecargaCredito.getValor());
            stmt.setString(7, tmRecargaCredito.getData());          
            stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }
        

    }
     
     
     
     
     
     
     
     
     
     
}

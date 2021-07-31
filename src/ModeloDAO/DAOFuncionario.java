/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import ModeloBeans.BeansFuncionario;
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
public class DAOFuncionario {
    private Connection conexao = null;
    
    public DAOFuncionario(){
        try {
            conexao = ConexaoBD.getConexao();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Estabelecer Conexão!\nErro: " + ex);
        }
    }
    
    public void Salvar(BeansFuncionario tmFuncionario) throws SQLException, ClassNotFoundException {

        // Prepara conexão p/ receber o comando SQL
        String sql = "INSERT INTO funcionario(nome, funcao, cpf, endereco, nascimento, agencia, conta, banco, salario)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, tmFuncionario.getNome());
            stmt.setString(2, tmFuncionario.getFuncao());
            stmt.setString(3, tmFuncionario.getCpf());
            stmt.setString(4, tmFuncionario.getEndereco());
            stmt.setString(5, tmFuncionario.getData());
            stmt.setString(6, tmFuncionario.getAgencia());
            stmt.setString(7, tmFuncionario.getConta());
            stmt.setString(8, tmFuncionario.getBanco());
            stmt.setFloat(9, tmFuncionario.getSalario());
          
            JOptionPane.showMessageDialog(null, "Dados Inseridos Com Sucesso!");
            stmt.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }
        

    }
    
    public List<BeansFuncionario> getLista(String nome) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "SELECT * FROM funcionario WHERE nome like ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        List<BeansFuncionario> listaC = null;
        try {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
           
            listaC = new ArrayList<>();
            // Enquanto existir registros, pega os valores do ReultSet e vai adicionando na lista

            while (rs.next()) {
                //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de registros p/ a lista
                BeansFuncionario Funcionario = new BeansFuncionario();
                // "Cliente" -> Cliente novo - .setNome recebe o campo do banco de String "nome" 
                Funcionario.setCodigo(Integer.valueOf(rs.getString("id")));
                Funcionario.setNome(rs.getString("nome"));
                Funcionario.setFuncao(rs.getString("funcao"));
                Funcionario.setCpf(rs.getString("cpf"));
                Funcionario.setEndereco(rs.getString("endereco"));
                Funcionario.setData(rs.getString("nascimento"));
                Funcionario.setAgencia(rs.getString("agencia"));
                Funcionario.setConta(rs.getString("conta"));
                Funcionario.setBanco(rs.getString("banco"));
                Funcionario.setSalario(rs.getFloat("salario"));



                // Adiciona o registro na lista
                listaC.add(Funcionario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }
        return listaC;
    }
    
    public void remove(int id) throws SQLException {       
        // Prepara conexão p/ receber o comando SQL
        String sql = "DELETE FROM funcionario WHERE id=?";
        // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
        try ( // stmt recebe o comando SQL
            PreparedStatement stmt = this.conexao.prepareStatement(sql)) {
            // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
            stmt.setInt(1, id);
            
            // Executa o codigo SQL, e fecha
            stmt.execute();
        }
        
    }
    
    public void altera(BeansFuncionario tmFuncionario) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "UPDATE funcionario SET nome=?, funcao=?, cpf=?, endereco=?, nascimento=?, agencia=?, conta=?, banco=?, salario=?"
                + "WHERE id=?";

        // Seta os valores p/ o stmt, substituindo os "?"
        try ( // stmt recebe o comando SQL
                PreparedStatement stmt = this.conexao.prepareStatement(sql)) {
            // Seta os valores p/ o stmt, substituindo os "?"
            stmt.setInt(10, tmFuncionario.getCodigo());
            stmt.setString(1, tmFuncionario.getNome());
            stmt.setString(2, tmFuncionario.getFuncao());
            stmt.setString(3, tmFuncionario.getCpf());
            stmt.setString(4, tmFuncionario.getEndereco());
            stmt.setString(5, tmFuncionario.getData());
            stmt.setString(6, tmFuncionario.getAgencia());
            stmt.setString(7, tmFuncionario.getConta());
            stmt.setString(8, tmFuncionario.getBanco());
            stmt.setFloat(9, tmFuncionario.getSalario());
    

            // O stmt executa o comando SQL no BD, e fecha a conexão
            stmt.execute();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;

import ModeloBeans.BeansUsuarioFuncionario;
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
public class DAOUsuarios {
     private Connection conexao = null;
     
public DAOUsuarios(){
        try {
            conexao = ConexaoBD.getConexao();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Estabelecer Conexão!\nErro: " + ex);
        }
}

 public void Salvar(BeansUsuarioFuncionario tmUserFunc) throws SQLException, ClassNotFoundException {

        // Prepara conexão p/ receber o comando SQL
        String sql = "INSERT INTO usuario(nome,login, senha, funcao,cpf)"
                + "VALUES(?, ?, ?, ?, ?)"; 
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement(sql);

           // stmt.setInt(1, tmUserFunc.getId());
            stmt.setString(1, tmUserFunc.getNome());            
            stmt.setString(2, tmUserFunc.getLogin());            
            stmt.setString(3, tmUserFunc.getSenha());
            stmt.setString(4, tmUserFunc.getFuncao());
            stmt.setString(5, tmUserFunc.getCpf());
          
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Dados Inseridos Com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }

    }
    
    
    public List<BeansUsuarioFuncionario> getListaUser(String nome) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql = "SELECT id,nome,login,senha,funcao,cpf FROM usuario WHERE nome like ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        List<BeansUsuarioFuncionario> UserLista = null;
        try {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
           
            UserLista = new ArrayList<>();
            // Enquanto existir registros, pega os valores do ReultSet e vai adicionando na UserLista

            while (rs.next()) {
                //  A cada loop, é instanciado um novo objeto, p/ servir de ponte no envio de registros p/ a UserLista
                BeansUsuarioFuncionario UserFunc = new BeansUsuarioFuncionario();
                // "Func" -> Cliente novo - .setNome recebe o campo do banco de String "nome" 
                UserFunc.setId(Integer.valueOf(rs.getString("id")));
                UserFunc.setNome(rs.getString("nome"));
                UserFunc.setLogin(rs.getString("login"));
                UserFunc.setSenha(rs.getString("senha"));
                UserFunc.setFuncao(rs.getString("funcao"));
                UserFunc.setCpf(rs.getString("cpf"));


                // Adiciona o registro na UserLista
                UserLista.add(UserFunc);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO Ao Inserir Dados!\nErro: " + ex);
        }
        return UserLista;
    }
    
    public void remove(int id) throws SQLException {       
        // Prepara conexão p/ receber o comando SQL
        String sql = "DELETE FROM usuario WHERE id=?";
        // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
        try ( // stmt recebe o comando SQL
            PreparedStatement stmt = this.conexao.prepareStatement(sql)) {
            // Seta o valor do ID p/ a condição de verificação SQL, dentro do stmt
            stmt.setInt(1, id);
            
            // Executa o codigo SQL, e fecha
            stmt.execute();
        }
        
    }
    
    public void altera(BeansUsuarioFuncionario tmUserFunc) throws SQLException {
        // Prepara conexão p/ receber o comando SQL
        String sql;
         sql = "UPDATE usuario SET nome=?, login=?, senha=?, funcao=?, cpf=? WHERE id=?";

        // Seta os valores p/ o stmt, substituindo os "?"
        try ( // stmt recebe o comand   o SQL
                PreparedStatement stmt = this.conexao.prepareStatement(sql)) {
            // Seta os valores p/ o stmt, substituindo os "?"
            
            stmt.setInt(6, tmUserFunc.getId());
            stmt.setString(1, tmUserFunc.getNome());
            stmt.setString(5, tmUserFunc.getCpf());
            stmt.setString(2, tmUserFunc.getLogin());
            stmt.setString(3, tmUserFunc.getSenha());
            stmt.setString(4, tmUserFunc.getFuncao());

            
            // O stmt executa o comando SQL no BD, e fecha a conexão
            stmt.execute();
            
        }
    }



}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloConection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author vinic
 */
public class ConexaoBD {
     private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/boate";
    private static final String USER = "root";
    private static final String PASS = "";
    public Connection conexao;
    public ResultSet rs;
    public Statement stm;
    
     public static Connection getConexao() throws SQLException {
        
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o BD!\n"+ex.getMessage());
        }   
        return null;
        
    }
     
     public static void closeConexao(Connection con){
         if (con != null){
             try {
                 con.close();
             } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Erro ao fechar o BD!\n"+ex.getMessage());
             }
         }
     }
     
     public static void closeConexao(Connection con,PreparedStatement stmt){
         if (stmt != null){
             try {
                 stmt.close();
             } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Erro ao fechar o BD!\n"+ex.getMessage());
             }
         }
         closeConexao(con);
     }
 
     public static void closeConexao(Connection con,PreparedStatement stmt, ResultSet rs){
         if (rs != null){
             try {
                 rs.close();
             } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Erro ao fechar o BD!\n"+ex.getMessage());
             }
         }
         closeConexao(con, stmt);
     }
     
     public void executaSQL(String sql){
         
             try {
                 stm = conexao.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
                 rs = stm.executeQuery(sql);
             } catch (SQLException ex) {
                 //Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
             }
           
     }

    /*public PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}

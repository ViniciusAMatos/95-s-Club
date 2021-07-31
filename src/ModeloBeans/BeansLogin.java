/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloBeans;

/**
 *
 * @author vinic
 */
public class BeansLogin {
    
private static String login;
private static String nome;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        BeansLogin.id = id;
    }
private static int id;

    public float getCredito() {
        return credito;
    }

    public void setCredito(float credito) {
        BeansLogin.credito = credito;
    }

    
private static float credito;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        BeansLogin.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        BeansLogin.nome = nome;
    }

}
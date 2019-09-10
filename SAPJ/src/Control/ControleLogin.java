/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import View.Login;

/**
 *
 * @author Gustavo Santana
 */
public class ControleLogin {

    Login view;

    void iniciarLogin() {
        view = new Login(this);
        view.setVisible(true);
    }

    public void login(String login, String senha) {
        System.out.println(login);
        System.out.println(senha);
        
        new ControleCadastroAdvogado().iniciarCadastroAdvogados();
        view.dispose();
    }

}

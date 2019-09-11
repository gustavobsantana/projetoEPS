/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Admin;
import Model.Advogado;
import View.Login;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
        Admin admin = new Admin();
        if (admin.getLogin().equals(login) && admin.getSenha().equals(senha)) {
            new ControleCadastroAdvogado().iniciarCadastroAdvogados();
            view.dispose();
        } else {
            Boolean permisao = false;
            ArrayList<Advogado> advogados = new BancoAdvogados().listarAdvogados();
            for (Advogado advogado : advogados) {
                if (login.equals(advogado.getLogin()) && senha.equals(advogado.getSenha())) {
                    permisao = true;
                }
            }
            if (permisao == true) {
                JOptionPane.showMessageDialog(null, "OK");
            } else {
                JOptionPane.showMessageDialog(null, "ERRO AO ACESSAR SISTEMA! \n Confira USUARIO e SENHA");
            }
        }
    }
}

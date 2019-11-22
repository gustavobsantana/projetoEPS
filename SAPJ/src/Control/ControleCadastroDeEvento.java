/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import View.CadastroDeEvento;

/**
 *
 * @author Gustavo Santana
 */
public class ControleCadastroDeEvento {
    CadastroDeEvento view;
    public void iniciarCadastroDeEvento(){
        this.view = new CadastroDeEvento(this);
        this.view.setVisible(true);
    }
}

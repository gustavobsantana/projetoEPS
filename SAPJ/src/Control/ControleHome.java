/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Advogado;
import View.Home;

/**
 *
 * @author Gustavo Santana
 */
public class ControleHome {

    Home view;
    Advogado advogadoLogado;

    //inicia a view
    public void iniciarHome(Advogado advogado) {

        this.view = new Home(this);
        
        
        this.view.setVisible(true);
    }
   
    public void clientes(){
        new ControleListaDeClientes().iniciarCliente();
    }
}

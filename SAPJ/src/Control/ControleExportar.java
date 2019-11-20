/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import View.Exportar;

/**
 *
 * @author Gustavo Santana
 */
public class ControleExportar {
    
    Exportar view;
    
    
    public void iniciarExportar(){
       view = new Exportar(this); 
       view.setVisible(true);
    }
}

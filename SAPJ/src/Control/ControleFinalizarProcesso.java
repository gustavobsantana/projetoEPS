/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Processo;
import View.FinalizarProcesso;

/**
 *
 * @author Gustavo Santana
 */
public class ControleFinalizarProcesso {

    Processo processo;
    FinalizarProcesso view;

    public void iniciarFinalizarProcesso(Processo processo) {
        this.processo = processo;
        this.view = new FinalizarProcesso();
        
        view.setVisible(true);
    }
}

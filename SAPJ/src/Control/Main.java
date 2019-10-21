/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Advogado;
import Model.Processo;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Santana
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Advogado> advogados = new BancoAdvogados().listarAdvogados();

        Processo processo = new Processo();
        processo.setNumero(10);
        processo.setAdvogado(advogados.get(1));

        new BancoProcessos().adicionarProcesso(processo);

        new BancoProcessos().listarProcessos().forEach(_processo -> {
            System.out.println(_processo.getAdvogado().getNome());
        });

        new ControleLogin().iniciarLogin();
        // TODO code application logic here
    }

}

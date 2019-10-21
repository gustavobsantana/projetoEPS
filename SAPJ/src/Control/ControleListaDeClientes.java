/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import View.ListaDeCliente;
import javax.swing.JTable;

/**
 *
 * @author Gustavo Santana
 */
public class ControleListaDeClientes {

    ListaDeCliente view;
    JTable tabelaClientes;

    //inicia a view
    public void iniciarCliente() {
        this.view = new ListaDeCliente(this);

        view.setVisible(true);
    }

    //popula a lista de Clientes
    public void iniciarListaClientes() {

    }

    //abre a tela para cadstro de cliente
    public void adicionarCliente() {
        new ControleCadastroDeClientes().iniciaCadastroClientes();

    }

    //desabilita um cliente
    public void desabilitarCliente() {

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Cliente;
import View.ListaDeCliente;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gustavo Santana
 */
public class ControleListaDeClientes {

    ListaDeCliente view;
    JTable tabelaClientes;
    int rowSelect = -1;

    //inicia a view
    public void iniciarCliente() {
        this.view = new ListaDeCliente(this);

        tabelaClientes = view.getTabelClientes();
        iniciarListaClientes();
        tabelaClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                rowSelect = tabelaClientes.rowAtPoint(e.getPoint());
            }
        });
        view.setVisible(true);
    }

    //popula a lista de Clientes
    public void iniciarListaClientes() {
        DefaultTableModel model = (DefaultTableModel) tabelaClientes.getModel();
        new BancoClientes().listarClientes().forEach(cliente -> {
            model.addRow(new Object[]{
                cliente.getNome(),
                cliente.getCpfCnpj(),
                cliente.getEmail(),
                cliente.getEndereco(),
                cliente.getTelefone(),
                cliente.isDesabilitado(),});
        });

    }

    //abre a tela para cadstro de cliente
    public void adicionarCliente() {
        new ControleCadastroDeClientes().iniciaCadastroClientes();

    }

    //desabilita um cliente
    public void desabilitarCliente() {
        if (rowSelect > -1) {
            String cpf = (String) tabelaClientes.getModel().getValueAt(rowSelect, 1);
            ArrayList<Cliente> clientes = new BancoClientes().listarClientes();
            clientes.forEach(_cliente -> {
                if (_cliente.getCpfCnpj().equals(cpf)) {
                    _cliente.setDesabilitado(!_cliente.isDesabilitado());
                }
            });
            new BancoClientes().editarCliente(clientes);
            JOptionPane.showMessageDialog(null, "Cliente Atualizado");
            view.dispose();
            iniciarCliente();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um cliente");

        }
    }

    public void voltar() {
        view.dispose();
    }

}

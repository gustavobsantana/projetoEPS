/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Enums.TipoProcesso;
import Model.Advogado;
import Model.Cliente;
import Model.Processo;
import View.CadastroDeProcesso;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo Santana
 */
public class ControleCadastroDeProcessos {

    CadastroDeProcesso view;
    Advogado advogado;
    JComboBox<TipoProcesso> comboTipo;
    JComboBox<Cliente> comboCliente;
    boolean cadastrar;

    public void iniciaCadastroDeProcesso(Advogado advogado) {
        this.view = new CadastroDeProcesso(this);
        this.advogado = advogado;
        this.view.setTxtAdvogado(advogado.getNome());

        this.comboTipo = this.view.getComboTipo();
        this.comboTipo.addItem(TipoProcesso.CIVIL);
        this.comboTipo.addItem(TipoProcesso.TRABALHISTA);
        this.comboTipo.addItem(TipoProcesso.PENAL);
        this.comboTipo.addItem(TipoProcesso.ADMINISTRATIVO);
        this.comboTipo.addItem(TipoProcesso.PREVIDENCIARIO);
        this.comboTipo.addItem(TipoProcesso.TRIBUTARIO);

        this.comboCliente = this.view.getComboCliente();
        new BancoClientes().listarClientes().forEach(cliente -> {
            this.comboCliente.addItem(cliente);
        });

        this.view.setVisible(true);
    }

    //Adiviona um processo
    public void adicionar(
            String codigo,
            TipoProcesso tipo,
            String vara,
            Cliente cliente,
            int clienteEhReu) {
        cadastrar = true;
        if (codigo.equals("") || !codigo.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
        } else if (vara.equals("")) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
        } else if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos \n"
                    + "cadastre um cliente");
        } else {
            new BancoProcessos().listarProcessos().forEach(_processo -> {
                if (codigo.equals(_processo.getNumero() + "")) {
                    JOptionPane.showMessageDialog(null, "Codigo já cadastrado");
                    cadastrar = false;
                }
            });

            if (cadastrar) {
                Processo processo = new Processo();
                processo.setNumero(Integer.parseInt(codigo));
                processo.setCliente(cliente);
                processo.setAdvogado(advogado);
                processo.setTipo(tipo);
                processo.setVara(vara);
                processo.setClienteEhReu(clienteEhReu == 0);
                new BancoProcessos().adicionarProcesso(processo);
                this.view.dispose();
                JOptionPane.showMessageDialog(null, "Processo cadastrado com sucesso");
            }
        }
    }

    //limpa os campos
    public void cancelar() {

    }

    //volta a tela
    public void voltar() {
        this.view.dispose();
    }

}

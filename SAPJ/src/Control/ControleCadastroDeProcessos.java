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
import java.util.ArrayList;
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
    Processo processo;

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

    public void iniciaCadastroDeProcessoEditar(Processo processo) {
        this.processo = processo;
        this.view = new CadastroDeProcesso(this);
        this.advogado = processo.getAdvogado();
        this.view.setTxtAdvogado(advogado.getNome());
        this.comboTipo = this.view.getComboTipo();
        this.comboTipo.addItem(TipoProcesso.CIVIL);
        this.comboTipo.addItem(TipoProcesso.TRABALHISTA);
        this.comboTipo.addItem(TipoProcesso.PENAL);
        this.comboTipo.addItem(TipoProcesso.ADMINISTRATIVO);
        this.comboTipo.addItem(TipoProcesso.PREVIDENCIARIO);
        this.comboTipo.addItem(TipoProcesso.TRIBUTARIO);

        this.comboCliente = this.view.getComboCliente();
        int i = 0;
        int indexDoClienteDoProcesso = 0;

        ArrayList<Cliente> clientesDoBanco = new BancoClientes().listarClientes();
        for (Cliente _cliente : clientesDoBanco) {
            if (_cliente.getCpfCnpj().equals(processo.getCliente().getCpfCnpj())) {
                indexDoClienteDoProcesso = i;
            }
            i++;
            this.comboCliente.addItem(_cliente);
        }

        this.view.getBtnAdicionar().setText("Editar");
        this.comboCliente.setSelectedIndex(indexDoClienteDoProcesso);
        this.view.getComboBoxTipo().setSelectedItem(processo.getTipo());
        this.view.getComboBoxClienteEhReu().setSelectedIndex(processo.isClienteEhReu() ? 0 : 1);
        this.view.getTxtCodigo().setText(processo.getNumero() + "");
        this.view.getTxtVara().setText(processo.getVara());

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
            JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios devem ser preenchidos");
        } else if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Todos os campos obrigatórios devem ser preenchidos");
        } else {
            if (processo != null) {
                editar(codigo, tipo, vara, cliente, clienteEhReu);
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
    }

    public void editar(String codigo,
            TipoProcesso tipo,
            String vara,
            Cliente cliente,
            int clienteEhReu) {

        ArrayList<Processo> processos = new BancoProcessos().listarProcessos();
        for (Processo _processo : processos) {
            if (_processo.getNumero() == processo.getNumero()) {
                _processo.setNumero(Integer.parseInt(codigo));
                _processo.setCliente(cliente);
                _processo.setAdvogado(advogado);
                _processo.setTipo(tipo);
                _processo.setVara(vara);
                _processo.setClienteEhReu(clienteEhReu == 0);
            }
        }
        new BancoProcessos().editarProcesso(processos);
        this.view.dispose();
        JOptionPane.showMessageDialog(null, "Processo cadastrado com sucesso");

    }

    //limpa os campos
    public void cancelar() {

    }

    //volta a tela
    public void voltar() {
        this.view.dispose();
    }

}

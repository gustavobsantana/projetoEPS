/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Cliente;
import View.CadastroDeCliente;
import View.ListaDeCliente;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author Gustavo Santana
 */
public class ControleCadastroDeClientes {

    CadastroDeCliente view;
    boolean cadastrar;

    public void iniciaCadastroClientes() {

        this.view = new CadastroDeCliente(this);

        PromptSupport.setPrompt("Nome do Cliente", this.view.getTxtNome());
        PromptSupport.setPrompt("CPF ou CNPJ somente numeros", this.view.getTxtCpfOuCNPJ());
        PromptSupport.setPrompt("Email do Cliente", this.view.getTxtEmail());
        PromptSupport.setPrompt("Endereço do Cliente", this.view.getTxtEndereco());
        PromptSupport.setPrompt("Telefone do Cliente", this.view.getTxtTelefone());

        view.setVisible(true);

    }

    //cadastra o cliente
    public void cadastrar(String nome,
            String cpfOuCNPJ, String email,
            String endereco, String telefone) {

        cadastrar = true;
        if (nome.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório");
        } else if (cpfOuCNPJ.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório");
        } else if (email.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório");
        } else if (endereco.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório");
        } else if (telefone.equals("") || !telefone.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Campo obrigatório");
        } else {
            ArrayList<Cliente> clientes = new BancoClientes().listarClientes();
            clientes.forEach(cliente -> {
                if (cliente.getCpfCnpj().equals(cpfOuCNPJ)) {
                    JOptionPane.showMessageDialog(null, "CPF ou CNPJ Já cadastrado");
                    cadastrar = false;
                }
            });
            if (cadastrar) {
                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setCpfCnpj(cpfOuCNPJ);
                cliente.setEmail(email);
                cliente.setEndereco(endereco);
                cliente.setTelefone(Integer.parseInt(telefone));
                new BancoClientes().adicionarCliente(cliente);
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
                this.voltar();
            }
        }
    }

    //limpa os campos
    public void cancelar() {

    }

    //fecha a tela e volta pra lista de clientes
    public void voltar() {
        view.dispose();
    }
}

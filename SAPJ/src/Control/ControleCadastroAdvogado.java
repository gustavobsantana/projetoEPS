/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Advogado;
import View.CadastroAdvogados;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Gustavo Santana
 */
public class ControleCadastroAdvogado {

    CadastroAdvogados view;

    public void iniciarCadastroAdvogados() {
        this.view = new CadastroAdvogados(this);
        this.view.setVisible(true);
        listarAdvogados();
    }

    public void cadastrarAdvogado(String nome, String oab, String cpf, String email, String login, String senha) {
        Advogado advogado = new Advogado();
        advogado.setNome(nome);
        advogado.setNumeroOAB(Integer.parseInt(oab));
        advogado.setCpf(cpf);
        advogado.setEmail(email);
        advogado.setLogin(login);
        advogado.setSenha(senha);

        new BancoAdvogados().adicionarAdvogado(advogado);
        listarAdvogados();

    }

    public void listarAdvogados() {
        JList lista = view.getLista();
        DefaultListModel model = new DefaultListModel();
        lista.setModel(model);
        model.clear();

        ArrayList<Advogado> advogados = new BancoAdvogados().listarAdvogados();

        advogados.forEach((Advogado adv) -> {
            model.addElement(adv.getNome());
        });

        lista.addListSelectionListener((e) -> {
            int i = lista.getSelectedIndex();
            Advogado adv = advogados.get(i);
            view.getTxtNome().setText(adv.getNome());
            view.getTxtOAB().setText(adv.getNumeroOAB() + "");
            view.getTxtCPF().setText(adv.getCpf());
            view.getTxtEmail().setText(adv.getEmail());
            view.getTxtLogin().setText(adv.getLogin());
        });

    }

}

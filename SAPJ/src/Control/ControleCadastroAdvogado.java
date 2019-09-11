/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Advogado;
import View.CadastroAdvogados;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author Gustavo Santana
 */
public class ControleCadastroAdvogado {

    CadastroAdvogados view;
    JList listaAtivos;
    JList listaDesativados;
    DefaultListModel modelAtivos = new DefaultListModel();
    DefaultListModel modelDesativados = new DefaultListModel();
    ArrayList<Advogado> advogados = new ArrayList();
    ArrayList<Advogado> advogadosAtivos;
    boolean cadastrar;

    public void iniciarCadastroAdvogados() {
        this.view = new CadastroAdvogados(this);
        this.listaAtivos = view.getLista();
        this.listaAtivos.setModel(modelAtivos);
        this.listaDesativados = view.getListaDesativados();
        this.listaDesativados.setModel(modelDesativados);

        PromptSupport.setPrompt("Digite o nome do Advogado", view.getTxtNome());
        PromptSupport.setPrompt("Digite o numero da OAB", view.getTxtOAB());
        PromptSupport.setPrompt("Digite o cpf somente números, ex.: 12345678911", view.getTxtCPF());
        PromptSupport.setPrompt("Digite o email, ex.: email@gmail.com", view.getTxtEmail());
        PromptSupport.setPrompt("Digite o login do Usuario", view.getTxtLogin());
        PromptSupport.setPrompt("Digite a senha do Usuario", view.getTxtSenha());

        this.view.setVisible(true);
        listarAdvogados();
    }

    public void cadastrarAdvogado(String nome, String oab, String cpf, String email, String login, String senha) {
        cadastrar = true;

        if (!cpf.equals("") && !login.equals("") && !senha.equals("") && !nome.equals("") && !oab.equals("")) {
            Advogado advogado = new Advogado();
            advogado.setNome(nome);
            advogado.setNumeroOAB(Integer.parseInt(oab));
            advogado.setCpf(cpf);
            advogado.setEmail(email);
            advogado.setLogin(login);
            advogado.setSenha(senha);
            advogados.forEach(adv -> {
                if (adv.getCpf().equals(cpf)) {
                    cadastrar = false;
                    JOptionPane.showMessageDialog(null, "CPF já cadastrado");
                } else if (adv.getLogin().equals(login)) {
                    cadastrar = false;
                    JOptionPane.showMessageDialog(null, "Login já cadastrado");
                }
            });

            if (cadastrar) {
                new BancoAdvogados().adicionarAdvogado(advogado);
                listarAdvogados();
            }
        } else {
            JOptionPane.showMessageDialog(null, "NOME, OAB, CPF, LOGIN e SENHA campos obrigatórios");

        }

    }

    public void listarAdvogados() {
        listaDesativados.clearSelection();
        modelDesativados.clear();
        listaAtivos.clearSelection();
        modelAtivos.clear();

        advogados = new BancoAdvogados().listarAdvogados();
        advogados.forEach((Advogado adv) -> {
            if (!adv.isDesativado()) {
                modelAtivos.addElement(adv.getCpf() + " - " + adv.getNome());
            } else {
                modelDesativados.addElement(adv.getCpf() + " - " + adv.getNome());
            }
        });

        MouseListener mouseListenerAtivos = new MouseAdapter() {
            Advogado adv = new Advogado();

            public void mouseClicked(MouseEvent mouseEvent) {
                JList theList = (JList) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 1) {
                    int index = theList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        String cpf = modelAtivos.get(index).toString().split(" -")[0];
                        advogados.forEach((advogado) -> {
                            if (advogado.getCpf().equals(cpf)) {
                                this.adv = advogado;
                                view.getTxtNome().setText(adv.getNome());
                                view.getTxtOAB().setText(adv.getNumeroOAB() + "");
                                view.getTxtCPF().setText(adv.getCpf());
                                view.getTxtEmail().setText(adv.getEmail());
                                view.getTxtLogin().setText(adv.getLogin());

                            }
                        });
                    }
                }
            }
        };

        MouseListener mouseListenerDesativados = new MouseAdapter() {
            Advogado adv = new Advogado();

            public void mouseClicked(MouseEvent mouseEvent) {
                JList theList = (JList) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 1) {
                    int index = theList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        String cpf = modelDesativados.get(index).toString().split(" -")[0];
                        advogados.forEach((advogado) -> {
                            if (advogado.getCpf().equals(cpf)) {
                                this.adv = advogado;
                                view.getTxtNome().setText(adv.getNome());
                                view.getTxtOAB().setText(adv.getNumeroOAB() + "");
                                view.getTxtCPF().setText(adv.getCpf());
                                view.getTxtEmail().setText(adv.getEmail());
                                view.getTxtLogin().setText(adv.getLogin());
                            }
                        });
                    }
                }
            }
        };

        listaDesativados.addMouseListener(mouseListenerDesativados);
        listaAtivos.addMouseListener(mouseListenerAtivos);
    }

    public void atualizarAdvogado(String nome, String oab, String cpf, String email, String login, String senha) {
        advogados.forEach((adv) -> {
            if (cpf.equals(adv.getCpf())) {
                adv.setNome(nome);
                adv.setNumeroOAB(Integer.parseInt(oab));
                adv.setCpf(cpf);
                adv.setEmail(email);
                adv.setLogin(login);
                if (!"".equals(senha)) {
                    adv.setSenha(senha);
                }
            }
        });

        new BancoAdvogados().editarAdvogado(advogados);

        listarAdvogados();
    }

    public void desativarAdvogado(String cpf) {
        System.out.println(cpf);
        advogados.forEach(adv -> {
            System.out.println(adv.isDesativado());

            if (adv.getCpf().equals(cpf)) {
                adv.setDesativado(!adv.isDesativado());
            }
        });

        new BancoAdvogados().editarAdvogado(advogados);

        listarAdvogados();
    }
}

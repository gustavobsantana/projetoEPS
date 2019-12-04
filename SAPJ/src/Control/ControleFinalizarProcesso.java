/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Processo;
import View.FinalizarProcesso;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo Santana
 */
public class ControleFinalizarProcesso {

    Processo processo;
    FinalizarProcesso view;

    public void iniciarFinalizarProcesso(Processo processo) {
        this.processo = processo;
        this.view = new FinalizarProcesso(this);
        this.view.getButtonGroup().add(view.getRadioConcluso());
        this.view.getButtonGroup().add(view.getRadioCancelado());

        view.setVisible(true);
    }

    public void finalizar(String informacao) {
        if (this.view.getButtonGroup().getSelection() != null) {
            if (this.view.getRadioConcluso().isSelected() && informacao.length() > 5) {
                ArrayList<Processo> processos = new BancoProcessos().listarProcessos();
                processos.forEach(_processo -> {
                    if (processo.getNumero() == _processo.getNumero()) {
                        _processo.setConcluido(true);
                        _processo.setCancelado(false);
                        _processo.setComentarioEncerramento(informacao);
                    }
                });
                new BancoProcessos().editarProcesso(processos);
                this.view.dispose();
                JOptionPane.showMessageDialog(null, "Processo finalizado com sucesso");
                System.out.println("CONCLUIDO");
            } else if (this.view.getRadioConcluso().isSelected() && informacao.length() < 5) {
                JOptionPane.showMessageDialog(null, "Advogado deve adicionar veredito");
            } else if (this.view.getRadioCancelado().isSelected()) {
                ArrayList<Processo> processos = new BancoProcessos().listarProcessos();
                processos.forEach(_processo -> {
                    if (processo.getNumero() == _processo.getNumero()) {
                        _processo.setCancelado(true);
                        _processo.setConcluido(false);
                        _processo.setComentarioEncerramento(informacao);
                    }
                });
                new BancoProcessos().editarProcesso(processos);
                this.view.dispose();
                JOptionPane.showMessageDialog(null, "Processo cancelado com sucesso");
                System.out.println("CANCELADO");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione CONCLUSO ou CANCELADO");
        }
    }

    public void voltar() {
        this.view.dispose();
    }
}

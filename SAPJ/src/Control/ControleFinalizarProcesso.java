/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Enums.StatusProcesso;
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

    public void concluir(String informacao) {
        if (this.view.getButtonGroup().getSelection() != null && !informacao.equals("")) {
            if (this.view.getRadioConcluso().isSelected()) {
                ArrayList<Processo> processos = new BancoProcessos().listarProcessos();
                processos.forEach(_processo -> {
                    if (processo.getNumero() == _processo.getNumero()) {
                        _processo.setStatus(StatusProcesso.CONCLUSO);
                        _processo.setComentarioEncerramento(informacao);
                    }
                });
                new BancoProcessos().editarProcesso(processos);
            } else {
                ArrayList<Processo> processos = new BancoProcessos().listarProcessos();
                processos.forEach(_processo -> {
                    if (processo.getNumero() == _processo.getNumero()) {
                        _processo.setStatus(StatusProcesso.CANCELADO);
                        _processo.setComentarioEncerramento(informacao);
                    }
                });
                new BancoProcessos().editarProcesso(processos);
            }
            
            this.view.dispose();
            JOptionPane.showMessageDialog(null, "Status alterado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "As informações para o encerramento são obrigatórias");
        }
    }
    
    public void voltar(){
        this.view.dispose();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Processo;
import View.ListaDeProcessos;
import View.VisualizarInformacoesDoProcesso;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Santana
 */
public class ControleVisualizarInformacoesDoProcesso {

    VisualizarInformacoesDoProcesso view;
    Processo processo;

    public void iniciarVisualizarMovimentacao(Processo processo) {
        this.view = new VisualizarInformacoesDoProcesso(this);
        this.processo = processo;
        this.prencherCampos();
        this.view.setVisible(true);

    }

    public void iniciarVisualizarMovimentacaoADM(Processo processo) {
        this.view = new VisualizarInformacoesDoProcesso(this);
        this.view.getBtnAddEvento().setVisible(false);
        this.view.getBtnAddMovimentacao().setVisible(false);
        this.processo = processo;
        this.prencherCampos();
        this.view.setVisible(true);
    }

    public void voltar() {
        this.view.dispose();
    }

    //preenche os campos com os valores do processo
    public void prencherCampos() {
        this.view.getTxtCodigo().setText(processo.getNumero() + "");
        this.view.getTxtAdvogado().setText(processo.getAdvogado().getNome());
        this.view.getTxtTipo().setText(processo.getTipo() + "");
        this.view.getTxtVara().setText(processo.getVara());
        this.view.getTxtCliente().setText(processo.getCliente().getNome());
    }

    public void adicionarEvento() {
        new ControleCadastroDeEvento().iniciarCadastroDeEvento(this.processo, this);
    }

    public void teste() {
        new BancoEventos().listarEventos().forEach(evento -> {
            System.out.println(evento.getDescricao());
        });
    }

    public void atualizar() {
        ArrayList<Processo> processos = new BancoProcessos().listarProcessos();
        processos.forEach(_processo -> {
            if (_processo.getNumero() == this.processo.getNumero()) {
                this.processo = _processo;
            }
        });
        this.prencherCampos();
        this.processo.getEventos().forEach(evento -> {
            System.out.println(evento.getDescricao());
            System.out.println(evento.getData());
            System.out.println(evento.getHora());
            System.out.println("--------------------");
        });
    }
}

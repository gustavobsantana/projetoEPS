/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Evento;
import Model.Processo;
import View.CadastroDeEvento;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Santana
 */
public class ControleCadastroDeEvento {

    CadastroDeEvento view;
    Processo processo;
    ControleVisualizarInformacoesDoProcesso controleVisualizarInformacoesDoProcesso;

    public void iniciarCadastroDeEvento(Processo processo, ControleVisualizarInformacoesDoProcesso controleVisualizarInformacoesDoProcesso) {
        this.view = new CadastroDeEvento(this);
        this.controleVisualizarInformacoesDoProcesso = controleVisualizarInformacoesDoProcesso;
        this.processo = processo;
        this.view.setVisible(true);
    }

    public void adicionar(String descricao, String data, String hora) {
        Evento evento = new Evento();
        evento.setDescricao(descricao);
        evento.setData(data);
        evento.setHora(hora);
        new BancoEventos().adicionarEvento(evento);
        ArrayList<Evento> eventos = new ArrayList<>();
        if (processo.getEventos() != null) {
            eventos.addAll(processo.getEventos());
        }
        eventos.add(evento);
        processo.setEventos(eventos);
        ArrayList<Processo> processos = new BancoProcessos().listarProcessos();
        processos.forEach(_processo -> {
            if (_processo.getNumero() == this.processo.getNumero()) {
                _processo.setEventos(eventos);
            }
        });
        new BancoProcessos().editarProcesso(processos);
        controleVisualizarInformacoesDoProcesso.atualizar();
    }
    
    public void cancelar(){
        this.view.dispose();
    }
}

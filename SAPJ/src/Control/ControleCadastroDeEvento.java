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
import java.util.Calendar;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.prompt.PromptSupport;

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
        PromptSupport.setPrompt("Digite a descrição do evento", this.view.getTxtDescricao());
        PromptSupport.setPrompt("20/12/2019", this.view.getTxtData());
        PromptSupport.setPrompt("18:15", this.view.getTxtHora());
        this.controleVisualizarInformacoesDoProcesso = controleVisualizarInformacoesDoProcesso;
        this.processo = processo;
        this.view.setVisible(true);
    }

    public Calendar stringParaCalendar(String data) {
        Calendar calendar = Calendar.getInstance();
        String[] parts = data.split("/");
        calendar.set(
                Integer.parseInt(parts[2]),
                mesDoCalendar(Integer.parseInt(parts[1])),
                Integer.parseInt(parts[0])
        );
        return calendar;
    }

    public int mesDoCalendar(int mes) {

        switch (mes) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 6;
            case 8:
                return 7;
            case 9:
                return 8;
            case 10:
                return 9;
            case 11:
                return 10;
            case 12:
                return 11;
            default:
                return mes;
        }
    }

    public void adicionar(String descricao, String data, String hora) {
        if (!descricao.equals("") && !data.equals("") && !hora.equals("")) {
            Evento evento = new Evento();
            evento.setDescricao(descricao);
            evento.setData(stringParaCalendar(data));
            evento.setHora(hora);
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
            JOptionPane.showMessageDialog(null, "Evento cadastrado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
        }
    }

    public void cancelar() {
        this.view.dispose();
    }
}

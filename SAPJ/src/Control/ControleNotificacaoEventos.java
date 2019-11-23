/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Evento;
import Model.Processo;
import View.NotificacaoEventos;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Gustavo Santana
 */
public class ControleNotificacaoEventos {

    NotificacaoEventos view;
    ArrayList<Processo> processos = new ArrayList<>();
    JList lista;
    DefaultListModel model = new DefaultListModel();

    public void iniciarNotificacaoEventos(ArrayList<Processo> processos) {
        this.view = new NotificacaoEventos(this);
        this.processos = processos;
        this.lista = this.view.getList();
        this.lista.setModel(model);
        pupularListaEventos();
        this.view.setVisible(true);
    }

    public void pupularListaEventos() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        processos.forEach(_processo -> {
            ArrayList<Evento> eventos = new ArrayList<>();
            if (_processo.getEventos() != null) {
                eventos = _processo.getEventos();
                eventos.forEach(_evento -> {
                    if (dateFormat.format(Calendar.getInstance().getTime()).equals(dateFormat.format(_evento.getData().getTime()))) {
                        model.addElement(_evento.getHora() + " - " + _evento.getDescricao());
                        model.addElement("Processo: " + _processo.getNumero());
                        model.addElement("Cliente(s): " + _processo.getCliente());
                        model.addElement(" ");
                    }
                });
            }
        });
    }

}

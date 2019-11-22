/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Cliente;
import Model.Evento;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Santana
 */
public class BancoEventos {

    private String NOMEARQUIVO = "dadosEventos.dat";

    ArrayList<Object> eventos = new ArrayList<Object>();

    public void adicionarEvento(Evento evento) {
        eventos = Empacotamento.lerArquivoBinario(NOMEARQUIVO);
        eventos.add(evento);
        Empacotamento.gravarArquivoBinario(eventos, NOMEARQUIVO);
    }

    public ArrayList<Evento> listarEventos() {
        ArrayList<Object> listaObject = Empacotamento.lerArquivoBinario(NOMEARQUIVO);
        ArrayList<Evento> listaEventos = new ArrayList<Evento>();
        for (Object lista : listaObject) {
            Evento evento = new Evento();
            evento.setData(((Evento) lista).getData());
            evento.setDescricao(((Evento) lista).getDescricao());
            evento.setHora(((Evento) lista).getHora());
            listaEventos.add(evento);
        }
        return listaEventos;
    }

    public void editarEvento(ArrayList<Evento> _eventos) {
        eventos = Empacotamento.lerArquivoBinario(NOMEARQUIVO);
        eventos.clear();
        eventos.addAll(_eventos);
        Empacotamento.gravarArquivoBinario(eventos, NOMEARQUIVO);
    }
}

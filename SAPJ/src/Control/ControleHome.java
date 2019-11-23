/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Advogado;
import Model.Evento;
import Model.Processo;
import View.Home;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Gustavo Santana
 */
public class ControleHome {
    
    Home view;
    Advogado advogadoLogado;

    //inicia a view
    public void iniciarHome(Advogado advogado) {
        if(advogado!=null){
            this.view = new Home(this);
            this.advogadoLogado = advogado;
            this.view.setVisible(true);
            buscarEventosDoAdvogado();
        }
    }
    
    public void buscarEventosDoAdvogado() {
        ArrayList<Processo> processosDoAdvogado = buscarProcessosDoAdvogado();
        ArrayList<Processo> processosComEventoDoDia = new ArrayList<>();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        
        processosDoAdvogado.forEach(_processo -> {
            _processo.getEventos().forEach(evento -> {
                if (dateFormat.format(Calendar.getInstance().getTime()).equals(dateFormat.format(evento.getData().getTime()))) {
                    if (!processosComEventoDoDia.contains(_processo)) {
                        processosComEventoDoDia.add(_processo);
                    }
                }
            });
        });
        if (!processosComEventoDoDia.isEmpty()) {
            new ControleNotificacaoEventos().iniciarNotificacaoEventos(processosComEventoDoDia);
        }
    }
    
    public ArrayList<Processo> buscarProcessosDoAdvogado() {
        ArrayList<Processo> processos = new BancoProcessos().listarProcessos();
        ArrayList<Processo> processosDoAdvogadoLogado = new ArrayList<>();
        
        for (Processo _processo : processos) {  
            if (_processo.getAdvogado().getNumeroOAB() == advogadoLogado.getNumeroOAB()) {
                processosDoAdvogadoLogado.add(_processo);
            }
        } 
        
        return processosDoAdvogadoLogado;
    }
    
    public void clientes() {
        new ControleListaDeClientes().iniciarCliente();
    }
    
    public void processos() {
        new ControleListaDeProcessos().iniciaListaDeProcessos(advogadoLogado);
    }
}

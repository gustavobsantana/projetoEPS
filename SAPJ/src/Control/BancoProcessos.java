/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Processo;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Santana
 */
public class BancoProcessos {
    private String NOMEARQUIVO = "dadosProcessos.dat";

    ArrayList<Object> processos = new ArrayList<Object>();

    public void adicionarProcesso(Processo processo) {
        processos = Empacotamento.lerArquivoBinario(NOMEARQUIVO);
        processos.add(processo);
        Empacotamento.gravarArquivoBinario(processos, NOMEARQUIVO);
    }

    public ArrayList<Processo> listarProcessos() {
        ArrayList<Object> listaObject = Empacotamento.lerArquivoBinario(NOMEARQUIVO);
        ArrayList<Processo> listaProcessos = new ArrayList<Processo>();

        for (Object lista : listaObject) {
            Processo processo = new Processo();
            processo.setNumero(((Processo)lista).getNumero());
            processo.setCliente(((Processo)lista).getCliente());
            processo.setAdvogado(((Processo)lista).getAdvogado());
            processo.setDescricao(((Processo)lista).getDescricao());
            processo.setTipo(((Processo)lista).getTipo());
            processo.setEventos(((Processo)lista).getEventos());
            processo.setCliente(((Processo)lista).getCliente());
            processo.setNomeDaParte(((Processo)lista).getNomeDaParte());
            processo.setVara(((Processo)lista).getVara());
            processo.setMovimentacoes(((Processo)lista).getMovimentacoes());
            listaProcessos.add(processo);
        }

        return listaProcessos;
    }
    
    public void editarAdvogado(ArrayList<Processo> _processo){
        processos = Empacotamento.lerArquivoBinario(NOMEARQUIVO);
        processos.clear();
        processos.addAll(_processo);
        Empacotamento.gravarArquivoBinario(processos, NOMEARQUIVO);
    }
    
    
}

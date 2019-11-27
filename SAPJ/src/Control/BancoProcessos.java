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
            Processo processo = new Processo(((Processo) lista).getNumero(), ((Processo) lista).getCliente(), ((Processo) lista).getAdvogado(), ((Processo) lista).getDescricao(), ((Processo) lista).getTipo(), ((Processo) lista).getNomeDaParte(), ((Processo) lista).getVara(), ((Processo) lista).getMovimentacoes(), ((Processo) lista).getComentarioEncerramento(), ((Processo) lista).getEventos(), ((Processo) lista).isConcluido(), ((Processo) lista).isCancelado());
            listaProcessos.add(processo);
        }

        return listaProcessos;
    }

    public void editarProcesso(ArrayList<Processo> _processo) {
        processos = Empacotamento.lerArquivoBinario(NOMEARQUIVO);
        processos.clear();
        processos.addAll(_processo);
        Empacotamento.gravarArquivoBinario(processos, NOMEARQUIVO);
    }

}

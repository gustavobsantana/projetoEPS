/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Advogado;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Santana
 */
public class BancoAdvogados {
    private String NOMEARQUIVO = "dadosAdvogados.dat";

    ArrayList<Object> advogados = new ArrayList<Object>();

    public void adicionarAdvogado(Advogado advogado) {
        advogados = Empacotamento.lerArquivoBinario(NOMEARQUIVO);
        advogados.add(advogado);
        Empacotamento.gravarArquivoBinario(advogados, NOMEARQUIVO);
    }

    public ArrayList<Advogado> listarAdvogados() {
        ArrayList<Object> listaObject = Empacotamento.lerArquivoBinario(NOMEARQUIVO);
        ArrayList<Advogado> listaAdvogados = new ArrayList<Advogado>();

        for (Object lista : listaObject) {
            Advogado advogado = new Advogado();
            advogado.setNome(((Advogado)lista).getNome());
            advogado.setCpf(((Advogado)lista).getCpf());
            advogado.setEmail(((Advogado)lista).getEmail());
            advogado.setNumeroOAB(((Advogado)lista).getNumeroOAB());
            advogado.setLogin(((Advogado)lista).getLogin());
            advogado.setSenha(((Advogado)lista).getSenha());
            listaAdvogados.add(advogado);
        }

        return listaAdvogados;
    }
    
    public void editarAdvogado(ArrayList<Advogado> _advogados){
        advogados = Empacotamento.lerArquivoBinario(NOMEARQUIVO);
        advogados.clear();
        advogados.addAll(_advogados);
        Empacotamento.gravarArquivoBinario(advogados, NOMEARQUIVO);
    }
}

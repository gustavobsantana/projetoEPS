/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Advogado;
import Model.Processo;
import View.ListaDeProcessosADM;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gustavo Santana
 */
public class ControleListaDeProcessosADM {

    ListaDeProcessosADM view;
    JTable tabelProcessos;
    Map<String, Integer> mapAdvogados = new HashMap<String, Integer>();

    //inicia a view
    public void iniciarListaDeProcessos() {
        this.view = new ListaDeProcessosADM(this);

        tabelProcessos = view.getTabelProcessos();
        listarQuantidadeDeProcessos();

        this.view.setVisible(true);
    }

    //volta para o menu de administrador
    public void voltar() {
        view.dispose();
    }

    //insere os elementos na lista
    public void listarQuantidadeDeProcessos() {
        DefaultTableModel model = (DefaultTableModel) tabelProcessos.getModel();
        ArrayList<Advogado> advogados = new BancoAdvogados().listarAdvogados();
        ArrayList<Processo> processos = new BancoProcessos().listarProcessos();

        advogados.forEach(adv -> {
            mapAdvogados.put(adv.getCpf(), 0);
        });

        processos.forEach(proce -> {
            mapAdvogados.put(
                    proce.getAdvogado().getCpf(),
                    mapAdvogados.get(proce.getAdvogado().getCpf()) + 1
            );
        });

        mapAdvogados.forEach((cpf, quantidade) -> {
            advogados.forEach(adv -> {
                if (adv.getCpf().equals(cpf)) {
                    model.addRow(new Object[]{adv.getNome(), quantidade});
                }
            });
        });

    }
}

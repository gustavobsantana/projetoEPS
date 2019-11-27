/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Advogado;
import Model.Processo;
import View.ListaDeProcessos;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gustavo Santana
 */
public class ControleListaDeProcessos {

    ListaDeProcessos view;
    JTable tabelaProcessos;
    Advogado advogado;
    int rowSelect = -1;

    //inicia a view
    public void iniciaListaDeProcessos(Advogado _advogado) {
        this.advogado = _advogado;
        this.view = new ListaDeProcessos(this);
        this.tabelaProcessos = this.view.getTableProcessos();
        DefaultTableModel model = (DefaultTableModel) tabelaProcessos.getModel();
        new BancoProcessos().listarProcessos().forEach(_processo -> {
            model.addRow(new Object[]{
                _processo.getNumero(),
                _processo.getCliente(),
                _processo.getVara(),
                _processo.getAdvogado().getNome(), 
                statusDoProcesso(_processo)
            });
        });

        tabelaProcessos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                rowSelect = tabelaProcessos.rowAtPoint(e.getPoint());
            }
        });

        view.setVisible(true);

    }

    public void iniciaListaDeProcessosADM() {
        this.view = new ListaDeProcessos(this);
        this.view.getBtnAdicionar().setVisible(false);
        this.view.getBtnFinalizar().setVisible(false);
        this.tabelaProcessos = this.view.getTableProcessos();
        DefaultTableModel model = (DefaultTableModel) tabelaProcessos.getModel();
        new BancoProcessos().listarProcessos().forEach(_processo -> {
            model.addRow(new Object[]{
                _processo.getNumero(),
                _processo.getCliente(),
                _processo.getVara(),
                _processo.getAdvogado().getNome(),
                statusDoProcesso(_processo)
            });
        });

        tabelaProcessos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                rowSelect = tabelaProcessos.rowAtPoint(e.getPoint());
            }
        });

        view.setVisible(true);
    }

    
     public String statusDoProcesso(Processo processo) {
        if(processo.isConcluido()) return "Concluso";
        if(processo.isCancelado()) return "Cancelado";
        return processo.getVara().equals("") ? "Aguardando Vara" : "Aberto";
    }
    
    //tela de adicionar
    public void adicionar() {
        new ControleCadastroDeProcessos().iniciaCadastroDeProcesso(advogado);
    }

    //tela de visualizar
    public void visualizar() {
        if (rowSelect > -1) {
            int numero = (int) tabelaProcessos.getModel().getValueAt(rowSelect, 0);
            new BancoProcessos().listarProcessos().forEach(_processo -> {
                if (numero == _processo.getNumero()) {
                    if (this.advogado == null) {
                        new ControleVisualizarInformacoesDoProcesso().iniciarVisualizarMovimentacaoADM(_processo);
                    } else {
                        new ControleVisualizarInformacoesDoProcesso().iniciarVisualizarMovimentacao(_processo);
                    }
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um processo");
        }
    }

    //tela de finalizar
    public void finalizar() {
        if (rowSelect > -1) {
            int numero = (int) tabelaProcessos.getModel().getValueAt(rowSelect, 0);
            new BancoProcessos().listarProcessos().forEach(_processo -> {
                if (numero == _processo.getNumero()) {
                    new ControleFinalizarProcesso().iniciarFinalizarProcesso(_processo);
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um processo");
        }

    }

    //tela de adicionar movimentação
    public void adicionarMovimentacao() {

    }

    //tela de adicionar evento
    public void adicionarEvento() {

    }

    //voltar
    public void voltar() {
        this.view.dispose();
    }
}

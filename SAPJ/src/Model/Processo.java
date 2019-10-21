/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Enums.StatusProcesso;
import Enums.TipoProcesso;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Santana
 */
public class Processo implements Serializable{
    
    private int numero;
    private Cliente cliente;
    private Advogado advogado;
    private String descricao; //-----
    private TipoProcesso tipo;
    private ArrayList<Evento> eventos; //----
    private boolean clienteEhReu; 
    private String nomeDaParte; //-----
    private String vara;
    private ArrayList<Movimentacao> movimentacoes; //----
    private StatusProcesso status = StatusProcesso.ABERTO;
    private String comentarioEncerramento;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Advogado getAdvogado() {
        return advogado;
    }

    public void setAdvogado(Advogado advogado) {
        this.advogado = advogado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoProcesso getTipo() {
        return tipo;
    }

    public void setTipo(TipoProcesso tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    public boolean isClienteEhReu() {
        return clienteEhReu;
    }

    public void setClienteEhReu(boolean clienteEhReu) {
        this.clienteEhReu = clienteEhReu;
    }

    public String getNomeDaParte() {
        return nomeDaParte;
    }

    public void setNomeDaParte(String nomeDaParte) {
        this.nomeDaParte = nomeDaParte;
    }

    public String getVara() {
        return vara;
    }

    public void setVara(String vara) {
        this.vara = vara;
    }

    public ArrayList<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(ArrayList<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public StatusProcesso getStatus() {
        return status;
    }

    public void setStatus(StatusProcesso status) {
        this.status = status;
    }

    public String getComentarioEncerramento() {
        return comentarioEncerramento;
    }

    public void setComentarioEncerramento(String comentarioEncerramento) {
        this.comentarioEncerramento = comentarioEncerramento;
    }
    
    
    
    
    
}

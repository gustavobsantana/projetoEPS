/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Gustavo Santana
 */
public class Evento implements Serializable {
    
    private Date data;
    private String descricao;
    private boolean ehPrazo;

    public boolean isEhPrazo() {
        return ehPrazo;
    }

    public void setEhPrazo(boolean ehPrazo) {
        this.ehPrazo = ehPrazo;
    }
    

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}

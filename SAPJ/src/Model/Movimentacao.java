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
public class Movimentacao implements Serializable {
     
    private Date data;
    private String descricao;
    private Advogado advogadoResponsavel;
}

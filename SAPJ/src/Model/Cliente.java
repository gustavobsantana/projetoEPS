/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Gustavo Santana
 */
public class Cliente  implements Serializable {
    
    private String nome;
    private String email;
    private String cpfCnpj;
    private int telefone;
    private String endereco;
    private boolean desabilitado = false;

    public Cliente() {
    }

    public Cliente(String nome, String email, String cpfCnpj, int telefone, String endereco, boolean desabilitado) {
        this.nome = nome;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
        this.telefone = telefone;
        this.endereco = endereco;
        this.desabilitado = desabilitado;
    }
    
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean isDesabilitado() {
        return desabilitado;
    }

    public void setDesabilitado(boolean desabilitado) {
        this.desabilitado = desabilitado;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
    
    
    
}

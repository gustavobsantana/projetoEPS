/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import View.CadastroDeCliente;
import View.ListaDeCliente;

/**
 *
 * @author Gustavo Santana
 */
public class ControleCadastroDeClientes {
    
    CadastroDeCliente view;
    
    
    
    public void iniciaCadastroClientes(){
        
        this.view = new CadastroDeCliente(this);
        
        view.setVisible(true);
        
    }
    

    
    //cadastra o cliente
    public void cadastrar(){
    }
    
    //limpa os campos
    public void cancelar(){
        
    }
    
    //fecha a tela e volta pra lista de clientes
    public void voltar(){
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Gustavo Santana
 */
public class Admin extends Usuario{

    @Override
    public String getLogin() {
        return "admin";
    }
    
    @Override
    public String getSenha() {
        return "admin";
    }       
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.rest.entidades;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class BuscarRecetas {
    
    private String token;
    private List<String> ingredientes;
    
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public List<String> getIngredientes() {
        return ingredientes;
    }
    
    public void setIngredientes(List<String> ingredientes) {
	this.ingredientes = ingredientes;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.servicios;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Nando
 */
@Path("/recetas")
public class Recetas {
    
    
    @GET
    @Produces("text/json")
    public String getRecetas() {
        
        return "Recetas";
    }
    
}

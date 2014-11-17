/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.servicios;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Nando
 */
@Path("/recetas")
public class Recetas {
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getReceta() {
        
        return "getReceta";
    }
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String busquedaRecetas() {
        
        return "busquedaRecetas";
    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String publicarReceta() {
        
        return "publicarReceta";
    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String valorarReceta() {
        
        return "valorarReceta";
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTopBusquedas() {
        
        return "getTopBusquedas";
    }
    
}

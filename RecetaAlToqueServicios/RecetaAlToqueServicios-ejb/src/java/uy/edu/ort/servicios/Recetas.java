/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.servicios;


import com.google.gson.Gson;

import javax.ejb.EJB;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import uy.edu.ort.dominio.Receta;

import uy.edu.ort.entidades.RecetaEntity;

import uy.edu.ort.persistencia.RecetaSBLocal;

/**
 *
 * @author Nando
 */
@Path("/recetas")
public class Recetas {
    
    
    @EJB
    private RecetaSBLocal recetaEJB;
    
    private final Gson gson = new Gson();
    
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getReceta(@PathParam("id") long id) {
        RecetaEntity receta = recetaEJB.obtenerPorNombre("Nombre");
        return gson.toJson(recetaEJB.obtenerDTO(receta));
    }
    
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
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

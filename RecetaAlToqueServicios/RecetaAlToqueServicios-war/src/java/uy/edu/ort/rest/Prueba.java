/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ASUS
 */
@Path("/prueba")
public class Prueba {
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String prueba1() {
        return "prueba1";
        //return gson.toJson("getTopBusquedas");
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.rest;

import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import uy.edu.ort.dominio.Usuario;
import uy.edu.ort.negocio.gestion.UsuarioSBNegocio;
/**
 *
 * @author Nando
 */
@Stateless
@Path("/usuarios")
public class Usuarios {   
    
    @EJB
    private UsuarioSBNegocio usuarioEJB;
    //private final UsuarioDummy usuarioEJB = new UsuarioDummy();
    
    private final Gson gson = new Gson();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("registro")
    public String registro (Usuario usuario) {
	usuario = usuarioEJB.alta(usuario);
        System.out.println("paso");
        return gson.toJson(usuario);
    }    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    public String login (Usuario usuario) {
	String token = usuarioEJB.login(usuario);
	return gson.toJson(token);
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("logout")
    public String logout (String token) {
        //usuarioEJB.limpiarToken(token);
        return gson.toJson("Token borrado");
    }
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("topbusquedas")
    public String getTopBusquedas() {
        
        return gson.toJson("getTopBusquedas");
    }
    
}

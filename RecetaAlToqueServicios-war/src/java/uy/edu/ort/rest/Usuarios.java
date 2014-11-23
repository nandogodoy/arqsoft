/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.rest;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import uy.edu.ort.dominio.Usuario;
import uy.edu.ort.negocio.gestion.TokenInvalidoException;
import uy.edu.ort.negocio.gestion.UsuarioSBNegocio;
import uy.edu.ort.rest.entidades.Token;
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
    public String logout (Token token) {
        Usuario usuario;
	try {
	    usuario = usuarioEJB.obtenerPorToken(token.getToken());
	    usuarioEJB.logout(usuario);
	    return gson.toJson(usuario);
	} catch (TokenInvalidoException ex) {
	    //Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
	    return gson.toJson(ex.getMessage());
	}
    }
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("validar/{token}")
    public String validar(@PathParam("token") String token) {
        Usuario usuario;
	try {
	    usuario = usuarioEJB.obtenerPorToken(token);
	    return gson.toJson(usuario);
	} catch (TokenInvalidoException ex) {
	    Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
	    return gson.toJson(ex.getMessage());
	}
        
    }
    
}

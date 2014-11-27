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
    
    private final Gson gson = new Gson();
    
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("registro")
    public String registro (Usuario usuario) {
	try {
	    this.validarAltaUsuario(usuario);
	    usuario = usuarioEJB.alta(usuario);
            if(usuario!=null)
                return gson.toJson(usuario);
            else{
                return gson.toJson("Nombre de usuario o email ya existente");
            }
	} catch (DatosInvalidosException ex) {
	    return gson.toJson(ex.getMessage());
	}
	
    }    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    public String login (Usuario usuario) {
	try {
	    this.validarLogin(usuario);
	    String token = usuarioEJB.login(usuario);
	    return gson.toJson(token);
	} catch (DatosInvalidosException ex) {
	    return gson.toJson(ex.getMessage());
	}
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("logout")
    public String logout (Token token) {
        Usuario usuario;
	try {
	    this.validarToken(token);
	    usuario = usuarioEJB.obtenerPorToken(token.getToken());
	    usuarioEJB.logout(usuario);
	    return gson.toJson(usuario);
	} catch (DatosInvalidosException ex) {
	    return gson.toJson(ex.getMessage());
	} catch (TokenInvalidoException ex) {
	    return gson.toJson(ex.getMessage());
	}
    }
    
    
    

    
    
    private void validarAltaUsuario (Usuario usuario) throws DatosInvalidosException {
	if (usuario.getEmail() == null || usuario.getEmail().equals("")) {
	    throw new DatosInvalidosException("Debe completar el campo email");
	}
	if (usuario.getNombre() == null || usuario.getNombre().equals("")) {
	    throw new DatosInvalidosException("Debe completar el campo nombre");
	}
	if (usuario.getPassword() == null || usuario.getPassword().equals("")) {
	    throw new DatosInvalidosException("Debe completar el campo password");
	}
    }
    
    
    private void validarLogin (Usuario usuario) throws DatosInvalidosException {
	if (usuario.getEmail() == null || usuario.getEmail().equals("")) {
	    throw new DatosInvalidosException("Debe completar el campo email");
	}
	if (usuario.getPassword() == null || usuario.getPassword().equals("")) {
	    throw new DatosInvalidosException("Debe completar el campo password");
	}
    }
    
    
    private void validarToken (Token token) throws DatosInvalidosException {
	if (token.getToken() == null || token.getToken().equals("")) {
	    throw new DatosInvalidosException("Faltan datos para validar usuario(token)");
	}
    }
    
}

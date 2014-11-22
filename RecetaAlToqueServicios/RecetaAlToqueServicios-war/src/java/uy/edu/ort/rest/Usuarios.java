/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.rest;

import com.google.gson.Gson;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import uy.edu.ort.dominio.Usuario;
import uy.edu.ort.entidades.UsuarioEntity;
import uy.edu.ort.persistencia.UsuarioSBLocal;
import uy.edu.ort.rest.dummy.UsuarioDummy;


/**
 *
 * @author Nando
 */
@Path("/usuarios")
public class Usuarios {
    
    
    
    @EJB
    private UsuarioSBLocal usuarioEJB;
    //private final UsuarioDummy usuarioEJB = new UsuarioDummy();
    
    private final Gson gson = new Gson();
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("registro")
    public String registro (Usuario usuario) {
	this.encriptarPassword(usuario);
	this.generarToken(usuario);
	usuarioEJB.alta(usuario);
        return gson.toJson(usuario);
    }
    
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("login")
    public String login (Usuario usuario) {
	String email	= usuario.getEmail();
	String password	= usuario.getPassword();
	//UsuarioEntity usuarioEntity = usuarioEJB.obtenerPorEmailYContraenia(email, password);
	//return gson.toJson(usuarioEJB.generarToken(usuarioEJB.obtenerDTO(usuarioEntity)));
	return gson.toJson(usuario);
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
    
    
    
    private void generarToken(Usuario usuario) {
        String token = "";
	long now = (new Date()).getTime();
        String generador = "Tu r3c3ta" + usuario.getEmail() + now;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            token = md.digest(generador.getBytes()).toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioDummy.class.getName()).log(Level.SEVERE, null, ex);
        }
        usuario.setToken(token);
	usuario.setExpira(new Date(now + 5 * 60 * 1000));
    }
    
    
    private void encriptarPassword(Usuario usuario) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            usuario.setPassword(md.digest(usuario.getPassword().getBytes()).toString());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioDummy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

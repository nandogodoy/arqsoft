/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.servicios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

/**
 *
 * @author Nando
 */
@Path("/usuarios")
public class Usuarios {
    
    
    @EJB
    private UsuarioSBLocal usuarioEJB;
    
    private final Gson gson = new Gson();
    
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String registro (Usuario usuario) {
	usuarioEJB.alta(usuario);
        Gson transformer = new GsonBuilder().create();
        return transformer.toJson(usuario);
        //return gson.toJson(usuario);
    }   
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login (Usuario usuario) {
	UsuarioEntity usuarioEntity = usuarioEJB.obtenerPorEmailYContraenia(usuario.getEmail(), usuario.getPassword());
	return gson.toJson(usuarioEJB.generarToken(usuarioEJB.obtenerDTO(usuarioEntity)));
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String logout (String token) {
        usuarioEJB.limpiarToken(token);
        return gson.toJson("Token borrado");
    }
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTopBusquedas() {
        
        return "getTopBusquedas";
    }
    
}

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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import uy.edu.ort.dominio.Usuario;
import uy.edu.ort.dummy.UsuarioDummy;
import uy.edu.ort.entidades.UsuarioEntity;
import uy.edu.ort.persistencia.RecetaSBLocal;
import uy.edu.ort.persistencia.UsuarioSBLocal;

/**
 *
 * @author Nando
 */
@Path("/usuarios")
public class Usuarios {
    
    
    @EJB
    private UsuarioSBLocal usuarioEJB;
    private final UsuarioDummy usuarioDummy = new UsuarioDummy();
    
    private final Gson gson = new Gson();
    
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String registro (String postData) {
        // TODO ver como obtener los datos por POST
        Object data = gson.fromJson(postData, Object.class);
        Usuario usuario = new Usuario();
        //usuarioEJB.alta(usuario);
        usuarioDummy.alta(usuario);
        return gson.toJson(usuario);
    }
    
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login (String postData) {
        // TODO ver como obtener los datos por POST
        Object data = gson.fromJson(postData, Object.class);
        UsuarioEntity usuario = usuarioDummy.obtenerPorEmailYContraenia(postData, postData);
        return gson.toJson(usuarioDummy.generarToken(usuarioEJB.obtenerDTO(usuario)));
    }
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String logout (String postData) {
        // TODO ver como obtener los datos por POST
        Object data = gson.fromJson(postData, Object.class);
        usuarioDummy.limpiarToken(postData);
        return gson.toJson("Token borrado");
    }
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTopBusquedas() {
        
        return "getTopBusquedas";
    }
    
}

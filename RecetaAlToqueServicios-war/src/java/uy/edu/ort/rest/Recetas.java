/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.rest;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import uy.edu.ort.dominio.Ingrediente;

import uy.edu.ort.jms.RecetasJMS;

import uy.edu.ort.dominio.Usuario;
import uy.edu.ort.dominio.Receta;

import uy.edu.ort.negocio.core.RecetaSBNegocio;
import uy.edu.ort.negocio.gestion.IngredienteInvalidoException;
import uy.edu.ort.negocio.gestion.IngredienteSBNegocio;
import uy.edu.ort.negocio.gestion.TokenInvalidoException;
import uy.edu.ort.negocio.gestion.UsuarioSBNegocio;
import uy.edu.ort.rest.entidades.AltaReceta;
import uy.edu.ort.rest.entidades.BuscarRecetas;
import uy.edu.ort.rest.entidades.Token;
import uy.edu.ort.rest.entidades.ValorarReceta;

/**
 *
 * @author ASUS
 */
@Path("/recetas")
public class Recetas {
    
    
    @EJB
    private RecetaSBNegocio recetaEJB;
    @EJB
    private UsuarioSBNegocio usuarioEJB;
    @EJB
    private IngredienteSBNegocio ingredienteEJB;
    
    private final Gson gson = new Gson();
    
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getReceta(@PathParam("id") long id) {
	/*
        Receta receta = recetaEJB.obtenerPorNombre("Nombre");
        return gson.toJson(recetaEJB.obtenerDTO(receta));
	*/
	return "getreceta";
    }
    
    
    
    @POST
    @Path("busqueda")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String busquedaRecetas(BuscarRecetas busarRecetas) {
        try {
	    Usuario usuario = usuarioEJB.obtenerPorToken(busarRecetas.getToken());
	    List<Receta> recetas = recetaEJB.buscar(busarRecetas.getIngredientes(), usuario);
	    return gson.toJson(recetas);
	} catch (TokenInvalidoException ex) {
	    Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
	    return gson.toJson("Acceso no autorizado (token invalido)");
	} catch (IngredienteInvalidoException ex) {
	    Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
	    return gson.toJson(ex.getMessage());
	}
    }
    
    
    @POST
    @Path("publicar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String publicarReceta(AltaReceta altaReceta) {
	try {
	    Usuario usuario = usuarioEJB.obtenerPorToken(altaReceta.getToken());
	    Receta receta = new Receta();
	    receta.setNombre(altaReceta.getNombre());
	    receta.setProcedimiento(altaReceta.getProcedimiento());
	    
	    Ingrediente ingr = null;
	    List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
	    for (String ingrediente: altaReceta.getIngredientes()) {
		ingr = ingredienteEJB.obtenerPorNombre(ingrediente);
		ingredientes.add(ingr);
	    }
	    receta.setIngredientes(ingredientes);
	    
	    RecetasJMS recetaJMS = new RecetasJMS();
	    recetaJMS.altaReceta(usuario, receta);
	    return gson.toJson("Receta "+receta.getNombre()+" creada exitosamente");
	} catch (TokenInvalidoException ex) {
	    Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
	    return gson.toJson("Acceso no autorizado (token invalido)");
	} catch (IngredienteInvalidoException ex) {
	    Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
	    return gson.toJson(ex.getMessage());
	}
    }
    
    
    @POST
    @Path("valorar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String valorarReceta(ValorarReceta valorarReceta) {
        try {
	    usuarioEJB.obtenerPorToken(valorarReceta.getToken());
	    Receta receta = new Receta();
	    receta.setNombre(valorarReceta.getNombre());
	    
	    RecetasJMS recetaJMS = new RecetasJMS();
	    recetaJMS.valorarReceta(receta, valorarReceta.getValoracion());
	    return gson.toJson("Receta "+receta.getNombre()+" valorada con un puntaje de "+valorarReceta.getValoracion());
	} catch (TokenInvalidoException ex) {
	    Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
	    return gson.toJson("Acceso no autorizado (token invalido)");
	}
    }
    
    
    @POST
    @Path("topbusquedas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getTopBusquedas(Token token) {
        try {
	    usuarioEJB.obtenerPorToken(token.getToken());
	    return gson.toJson(ingredienteEJB.obtenerTopBusqueda());
	} catch (TokenInvalidoException ex) {
	    Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
	    return gson.toJson("Acceso no autorizado (token invalido)");
	}
    }
}

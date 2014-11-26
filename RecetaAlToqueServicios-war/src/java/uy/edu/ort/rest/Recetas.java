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
import uy.edu.ort.rest.excepciones.DatosInvalidosException;

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
    
    
    
    @POST
    @Path("busqueda")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String busquedaRecetas(BuscarRecetas busarRecetas) {
        try {
	    this.validarBusquedaRecetas(busarRecetas);
	    Usuario usuario = usuarioEJB.obtenerPorToken(busarRecetas.getToken());
	    List<Receta> recetas = recetaEJB.buscar(busarRecetas.getIngredientes(), usuario);
	    return gson.toJson(recetas);
	} catch (DatosInvalidosException ex) {
	    return gson.toJson(ex.getMessage());
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
	    this.validarPublicarReceta(altaReceta);
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
	} catch (DatosInvalidosException ex) {
	    return gson.toJson(ex.getMessage());
	} catch (TokenInvalidoException ex) {
	    return gson.toJson("Acceso no autorizado (token invalido)");
	} catch (IngredienteInvalidoException ex) {
	    return gson.toJson(ex.getMessage());
	}
    }
    
    
    @POST
    @Path("valorar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String valorarReceta(ValorarReceta valorarReceta) {
        try {
	    this.validarValorarReceta(valorarReceta);
	    usuarioEJB.obtenerPorToken(valorarReceta.getToken());
	    Receta receta = new Receta();
	    receta.setNombre(valorarReceta.getNombre());
	    
	    RecetasJMS recetaJMS = new RecetasJMS();
	    recetaJMS.valorarReceta(receta, valorarReceta.getValoracion());
	    return gson.toJson("Receta "+receta.getNombre()+" valorada con un puntaje de "+valorarReceta.getValoracion());
	} catch (DatosInvalidosException ex) {
	    return gson.toJson(ex.getMessage());
	} catch (TokenInvalidoException ex) {
	    return gson.toJson("Acceso no autorizado (token invalido)");
	}
    }
    
    
    @POST
    @Path("topbusquedas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getTopBusquedas(Token token) {
        try {
	    this.validarToken(token);
	    usuarioEJB.obtenerPorToken(token.getToken());
	    return gson.toJson(ingredienteEJB.obtenerTopBusqueda());
	} catch (DatosInvalidosException ex) {
	    return gson.toJson(ex.getMessage());
	} catch (TokenInvalidoException ex) {
	    return gson.toJson("Acceso no autorizado (token invalido)");
	}
    }
    
    
    
    /////////////////////////////////////////////////
    //////// FUNCIONES DE VALIDACION ////////////////
    /////////////////////////////////////////////////
    
    private void validarPublicarReceta (AltaReceta altaReceta) throws DatosInvalidosException {
	if (altaReceta.getToken() == null || altaReceta.getToken().equals("")) {
	    throw new DatosInvalidosException("Faltan datos para validar usuario(token)");
	}
	if (altaReceta.getNombre() == null || altaReceta.getNombre().equals("")) {
	    throw new DatosInvalidosException("Debe completar el campo nombre");
	}
	if (altaReceta.getProcedimiento() == null || altaReceta.getProcedimiento().equals("")) {
	    throw new DatosInvalidosException("Debe completar el campo procedimiento");
	}
	if (altaReceta.getIngredientes().isEmpty()) {
	    throw new DatosInvalidosException("La receta debe contener al menos un ingrediente");
	}
    }
    
    
    private void validarBusquedaRecetas (BuscarRecetas buscarRecetas) throws DatosInvalidosException {
	if (buscarRecetas.getToken() == null || buscarRecetas.getToken().equals("")) {
	    throw new DatosInvalidosException("Faltan datos para validar usuario(token)");
	}
	if (buscarRecetas.getIngredientes().isEmpty()) {
	    throw new DatosInvalidosException("Debe ingresar al menos un ingrediente");
	}
    }
    
    
    private void validarValorarReceta(ValorarReceta valorarReceta) throws DatosInvalidosException {
	if (valorarReceta.getToken() == null || valorarReceta.getToken().equals("")) {
	    throw new DatosInvalidosException("Faltan datos para validar usuario(token)");
	}
	if (valorarReceta.getNombre() == null || valorarReceta.getNombre().equals("")) {
	    throw new DatosInvalidosException("Debe ingresar el nombre de la receta a valorar");
	}
	if (valorarReceta.getValoracion() == 0) {
	    throw new DatosInvalidosException("La valoracion debe ser distinta de cero");
	}
    }
    
    
    private void validarToken (Token token) throws DatosInvalidosException {
	if (token.getToken() == null || token.getToken().equals("")) {
	    throw new DatosInvalidosException("Faltan datos para validar usuario(token)");
	}
    }
    
}

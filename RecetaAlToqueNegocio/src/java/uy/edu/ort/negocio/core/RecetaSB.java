/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.negocio.core;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import uy.edu.ort.dominio.Ingrediente;
import uy.edu.ort.dominio.Receta;
import uy.edu.ort.dominio.Usuario;
import uy.edu.ort.negocio.gestion.IngredienteInvalidoException;
import uy.edu.ort.negocio.gestion.IngredienteSBNegocio;
import uy.edu.ort.negocio.gestion.UsuarioSBNegocio;
import uy.edu.ort.persistencia.RecetaSBLocal;
import uy.edu.ort.persistencia.UniqueConstraintException;
/**
 *
 * @author Richard
 */
@Stateless
public class RecetaSB implements RecetaSBNegocio {
    
    @EJB
    RecetaSBLocal persistencia;
    @EJB 
    BusquedaSBNegocio busqueda;
    @EJB 
    IngredienteSBNegocio ingredienteEJB;
    @EJB
    UsuarioSBNegocio usuarioEJB;
    
    @Override
    public void valorar(Receta receta,float valoracion){
        persistencia.valorar(receta,valoracion);        
    }
    @Override
    public void alta(Receta receta,Usuario usuario){
        try {
            persistencia.alta(receta, usuario);
        } catch (UniqueConstraintException ex) {
            Logger.getLogger(RecetaSB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Receta> buscar (List<String> ingredientes, Usuario usuario) throws IngredienteInvalidoException {
	List<Ingrediente> listaIngredientes = new ArrayList<>();
	for (String ing: ingredientes) {
	    Ingrediente ingrediente = ingredienteEJB.obtenerPorNombre(ing);
	    listaIngredientes.add(ingrediente);
	}
        List<Receta> resultado = persistencia.obtenerLista(listaIngredientes);
        busqueda.alta(listaIngredientes, usuario);
        return resultado;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.negocio.core;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import uy.edu.ort.dominio.Ingrediente;
import uy.edu.ort.dominio.Receta;
import uy.edu.ort.dominio.Usuario;
import uy.edu.ort.negocio.gestion.IngredienteInvalidoException;
import uy.edu.ort.negocio.gestion.IngredienteSBNegocio;
import uy.edu.ort.persistencia.RecetaSBLocal;
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
    
    @Override
    public void valorar(Receta receta,float valoracion){
        persistencia.valorar(receta,valoracion);        
    }
    @Override
    public void alta(Receta receta,Usuario usuario){
        persistencia.alta(receta, usuario);
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

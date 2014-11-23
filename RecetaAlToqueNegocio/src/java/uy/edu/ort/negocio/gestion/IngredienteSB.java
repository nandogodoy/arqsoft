/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.negocio.gestion;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import uy.edu.ort.dominio.Ingrediente;
import uy.edu.ort.persistencia.IngredienteSBLocal;

/**
 *
 * @author Richard
 */
@Stateless
public class IngredienteSB implements IngredienteSBNegocio {

    @EJB
    private IngredienteSBLocal ingredienteEJB;
    
    @Override
    public Ingrediente obtenerPorNombre(String nombre) throws IngredienteInvalidoException {
	Ingrediente ingrediente = ingredienteEJB.obtenerPorNombre(nombre);
	if (ingrediente == null) {
	    throw new IngredienteInvalidoException("No existe un ingrediente llamaado: " + ingrediente);
	}
	return ingrediente;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

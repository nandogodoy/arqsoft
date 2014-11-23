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
    IngredienteSBLocal persistencia;
    
    @Override
    public void alta(Ingrediente ingrediente) {
        persistencia.alta(ingrediente);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

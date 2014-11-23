/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.negocio.gestion;

import javax.ejb.Local;
import uy.edu.ort.dominio.Ingrediente;

/**
 *
 * @author Richard
 */
@Local
public interface IngredienteSBNegocio {
    public void alta(Ingrediente ingrediente);
}

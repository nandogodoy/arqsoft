/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import javax.ejb.Local;
import uy.edu.ort.entidades.IngredienteEntity;

/**
 *
 * @author Richard
 */
@Local
public interface IngredienteSBLocal {
    public void alta(IngredienteEntity ingrediente);
    public void modificar(IngredienteEntity ingrediente);
    public void eliminar(IngredienteEntity ingrediente);
}

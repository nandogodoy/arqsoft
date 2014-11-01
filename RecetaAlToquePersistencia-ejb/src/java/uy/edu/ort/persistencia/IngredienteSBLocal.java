/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import javax.ejb.Local;
import uy.edu.ort.dominio.Ingrediente;
import uy.edu.ort.entidades.IngredienteEntity;

/**
 *
 * @author Richard
 */
@Local
public interface IngredienteSBLocal {
    public void alta(Ingrediente ingrediente);
    public void modificar(Ingrediente ingrediente);
    public void eliminar(Ingrediente ingrediente);
    
    public IngredienteEntity obtenerPorNombre(String nombre);
}

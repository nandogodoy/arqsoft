/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.negocio.gestion;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import uy.edu.ort.dominio.Ingrediente;

/**
 *
 * @author Richard
 */
@Local
public interface IngredienteSBNegocio {
    
    public void alta(Ingrediente ingrediente);
    public Ingrediente obtenerPorNombre(String nombre) throws IngredienteInvalidoException;
    public Map<Long,Ingrediente> obtenerTopBusqueda();
    
}

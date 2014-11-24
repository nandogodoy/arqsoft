/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import java.util.List;
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
    public Ingrediente obtenerPorNombreDTO(String nombre);
    
    public List<IngredienteEntity> obtenerLista(List<Ingrediente> ingredientes);
    public List<Ingrediente> obtenerListaDTO(List<IngredienteEntity> ingredientes);
    
    public List<Ingrediente> obtenerTopBusqueda();

}

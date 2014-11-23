/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.negocio.core;

import java.util.List;
import javax.ejb.Local;
import uy.edu.ort.dominio.*;
/**
 *
 * @author Richard
 */
@Local
public interface RecetaSBNegocio {
    
    void valorar(Receta receta,float valoracion);
    void alta(Receta receta,Usuario usuario);
    List<Receta> buscar(List<Ingrediente> ingredientes);
    
    
}

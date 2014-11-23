/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.negocio.core;

import java.util.List;
import javax.ejb.Local;
import uy.edu.ort.dominio.Busqueda;
import uy.edu.ort.dominio.Ingrediente;
import uy.edu.ort.dominio.Usuario;

/**
 *
 * @author Richard
 */
@Local
public interface BusquedaSBNegocio {
    public void alta(List<Ingrediente> lista, Usuario ususario);
    public List<Busqueda> top10Busquedas();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import java.util.List;
import javax.ejb.Local;
import uy.edu.ort.dominio.Busqueda;
import uy.edu.ort.entidades.BusquedaEntity;

/**
 *
 * @author Richard
 */
@Local
public interface BusquedaSBLocal {

    public void alta(Busqueda busqueda);

    public List<Busqueda> obtenerPorUsuario(String usuario);

    public List<Busqueda> obtenerTop10();
    
}

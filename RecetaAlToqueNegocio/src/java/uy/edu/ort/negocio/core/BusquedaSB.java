/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.negocio.core;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import uy.edu.ort.dominio.Busqueda;
import uy.edu.ort.dominio.Ingrediente;
import uy.edu.ort.dominio.Usuario;
import uy.edu.ort.persistencia.BusquedaSBLocal;

/**
 *
 * @author Richard
 */
@Stateless
public class BusquedaSB implements BusquedaSBNegocio {

    @EJB
    BusquedaSBLocal persistencia;
    
    @Override
    public void alta(List<Ingrediente> lista, Usuario ususario) {
        Busqueda busqueda= new Busqueda(ususario, lista, new Date());
        persistencia.alta(busqueda);
    }

    @Override
    public List<Busqueda> top10Busquedas() {
        List<Busqueda> lista = persistencia.obtenerTop10();
        return lista;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}

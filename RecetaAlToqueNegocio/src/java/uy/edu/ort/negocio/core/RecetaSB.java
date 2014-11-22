/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.negocio.core;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import uy.edu.ort.dominio.Receta;
import uy.edu.ort.dominio.Usuario;
/**
 *
 * @author Richard
 */
@Stateless
public class RecetaSB implements uy.edu.ort.negocio.core.RecetaSBLocal {
    
    @EJB
    uy.edu.ort.persistencia.RecetaSBLocal persistencia;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void valorar(Receta receta,float valoracion){
        persistencia.valorar(receta,valoracion);        
    }
    @Override
    public void alta(Receta receta,Usuario usuario){
        persistencia.alta(receta, usuario);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import uy.edu.ort.entidades.UsuarioEntity;

/**
 *
 * @author Richard
 */
@Stateless
public class UsuarioSB implements UsuarioSBLocal {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public void alta(UsuarioEntity usuario) {
        if(!em.contains(usuario)){
            em.persist(usuario);        
        }
    }

    @Override
    public void eliminar(UsuarioEntity usuario) {
        if(em.contains(usuario)){
            em.remove(usuario);
        }
    
    }

    @Override
    public void modificar(UsuarioEntity usuario) {
        if(em.contains(usuario)){
            em.merge(usuario);
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}

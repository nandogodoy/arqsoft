/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import uy.edu.ort.entidades.RecetaEntity;

/**
 *
 * @author Richard
 */
@Stateless
public class RecetaSB implements RecetaSBLocal {

    @PersistenceContext
    EntityManager em;
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void alta(RecetaEntity receta) {
        if(!em.contains(receta)){
        em.persist(receta);
        }
    }

    @Override
    public void modificar(RecetaEntity receta) {
        if(em.contains(receta)){
            em.merge(receta);
        }
    }

    @Override
    public void eliminar(RecetaEntity receta) {
        if(em.contains(receta)){
            em.remove(receta);
        }
    }
}

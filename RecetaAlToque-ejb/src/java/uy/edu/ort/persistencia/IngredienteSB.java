/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import uy.edu.ort.entidades.IngredienteEntity;

/**
 *
 * @author Richard
 */
@Stateless
public class IngredienteSB implements IngredienteSBLocal {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public void alta(IngredienteEntity ingrediente) {
        if(!em.contains(ingrediente)){
        em.persist(ingrediente);
        }
    }

    @Override
    public void modificar(IngredienteEntity ingrediente) {
        if(em.contains(ingrediente)){
            em.merge(ingrediente);
        }
    }

    @Override
    public void eliminar(IngredienteEntity ingrediente) {
        if(em.contains(ingrediente)){
            em.remove(ingrediente);
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

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
import uy.edu.ort.dominio.Receta;

/**
 *
 * @author Richard
 */
@Stateless
public class RecetaSB implements RecetaSBLocal {

    @PersistenceContext
    EntityManager em;
    UsuarioSBLocal usuarioSB;
    IngredienteSBLocal ingredienteSB;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void alta(Receta receta) {
        RecetaEntity entity= new RecetaEntity();
        entity.setNombre(receta.getNombre());
        entity.setUsuario(usuarioSB.obtenerPorNombre(receta.getUsuario().getNombre()));
        entity.setValoracion(receta.getValoracion());
        entity.setProcedimiento(receta.getProcedimiento());
        entity.setPrincipal(ingredienteSB.obtenerPorNombre(receta.getPrincipal().getNombre()));
        entity.setSegundo(ingredienteSB.obtenerPorNombre(receta.getSegundo().getNombre()));
        entity.setTercero(ingredienteSB.obtenerPorNombre(receta.getTercero().getNombre()));
        entity.setCuarto(ingredienteSB.obtenerPorNombre(receta.getCuarto().getNombre()));
        
        if(!em.contains(entity)){
            em.persist(entity);
        }
    }

    @Override
    public void modificar(Receta receta) {
        RecetaEntity original=this.obtenerPorNombre(receta.getNombre());
        original.setUsuario(usuarioSB.obtenerPorNombre(receta.getUsuario().getNombre()));
        original.setValoracion(receta.getValoracion());
        original.setProcedimiento(receta.getProcedimiento());
        original.setPrincipal(ingredienteSB.obtenerPorNombre(receta.getPrincipal().getNombre()));
        original.setSegundo(ingredienteSB.obtenerPorNombre(receta.getSegundo().getNombre()));
        original.setTercero(ingredienteSB.obtenerPorNombre(receta.getTercero().getNombre()));
        original.setCuarto(ingredienteSB.obtenerPorNombre(receta.getCuarto().getNombre()));
        
        if(em.contains(original)){
            em.merge(original);
        }
    }

    @Override
    public void eliminar(Receta receta) {
        RecetaEntity borrar=this.obtenerPorNombre(receta.getNombre());
        if(em.contains(borrar)){
            em.remove(borrar);
        }
    }
    
    @Override
    public RecetaEntity obtenerPorNombre(String nombre){
        return null;
    }
}

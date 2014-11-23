/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import uy.edu.ort.entidades.IngredienteEntity;
import uy.edu.ort.dominio.Ingrediente;
import uy.edu.ort.dominio.Usuario;
import uy.edu.ort.entidades.UsuarioEntity;

/**
 *
 * @author Richard
 */
@Stateless
public class IngredienteSB implements IngredienteSBLocal {

    @PersistenceContext
    EntityManager em;
    
    @Override
    public void alta(Ingrediente ingrediente) {
        IngredienteEntity entity= new IngredienteEntity();
        entity.setNombre(ingrediente.getNombre());
        if(!em.contains(entity)){
            em.persist(entity);
        }
    }

    @Override
    public void modificar(Ingrediente ingrediente) {
        IngredienteEntity entity= new IngredienteEntity();
        entity.setNombre(ingrediente.getNombre());
        
        if(em.contains(entity)){
            em.merge(entity);
        }
    }

    @Override
    public void eliminar(Ingrediente ingrediente) {
        IngredienteEntity entity= new IngredienteEntity();
        entity.setNombre(ingrediente.getNombre());
        if(em.contains(entity)){
            em.remove(entity);
        }
    }
    @Override
    public Ingrediente obtenerPorNombre(String nombre){
        TypedQuery<IngredienteEntity> query= em.createNamedQuery("IngredienteEntity.findByNombre", IngredienteEntity.class);
        query.setParameter("nombre", nombre);
        try{
            IngredienteEntity ingrediente= query.getSingleResult();
            return this.obtenerDTO(ingrediente);
        }
        catch(NoResultException e){}
        return null;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Ingrediente> obtenerLista(List<IngredienteEntity> ingredientes) {
        List<Ingrediente> lista = new ArrayList();
        for (IngredienteEntity entidad : ingredientes) {
            Ingrediente ing= new Ingrediente(entidad.getNombre());
            lista.add(ing);
        }
        return lista;
    }
    
    
    private Ingrediente obtenerDTO(IngredienteEntity i) {
        Ingrediente ingrediente = new Ingrediente(i.getNombre());
        return ingrediente;
    }
}

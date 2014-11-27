/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import uy.edu.ort.entidades.IngredienteEntity;
import uy.edu.ort.dominio.Ingrediente;


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
    public IngredienteEntity obtenerPorNombre(String nombre){
        TypedQuery<IngredienteEntity> query= em.createNamedQuery("IngredienteEntity.findByNombre", IngredienteEntity.class);
        query.setParameter("nombre", nombre);
        try{
            IngredienteEntity ingrediente= query.getSingleResult();
            return ingrediente;
        }
        catch(NoResultException e){return null;}
        
    }
    
    @Override
    public Ingrediente obtenerPorNombreDTO(String nombre){
        TypedQuery<IngredienteEntity> query= em.createNamedQuery("IngredienteEntity.findByNombre", IngredienteEntity.class);
        query.setParameter("nombre", nombre);
        try{
            IngredienteEntity ingrediente= query.getSingleResult();
            return this.obtenerDTO(ingrediente);
        }
        catch(NoResultException e){return null;}
        
    }
    

    @Override
    public List<Ingrediente> obtenerListaDTO(List<IngredienteEntity> ingredientes) {
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

    @Override
    public List<IngredienteEntity> obtenerLista(List<Ingrediente> ingredientes) {
        List<IngredienteEntity> lista = new ArrayList();
        for (Ingrediente ingrediente : ingredientes) {
            IngredienteEntity ing= obtenerPorNombre(ingrediente.getNombre());
            lista.add(ing);
        }
        return lista;
    }
    
    
    @Override
    public Map<String, Long> obtenerTopBusqueda() {
        
        Query query = em.createNativeQuery("select count(1) as cantidad,i.* from ingredienteentity i, busquedas_ingredientes b where i.ID=b.ingrediente_id group by ingrediente_id order by count(1) desc");
        try{
            List<Object[]> resultList = query.setMaxResults(10).getResultList();
            Map<String, Long> resultMap = new HashMap<String, Long>(resultList.size());
            for (Object[] result : resultList){
                //Ingrediente ing= new Ingrediente((String)result[2]);
		//Ingrediente ing= new Ingrediente((String)result[2]);
                resultMap.put((String)result[2], (Long)result[0]);
            }
            return resultMap;
        }
        catch(NoResultException e){return null;}
        
    }
}

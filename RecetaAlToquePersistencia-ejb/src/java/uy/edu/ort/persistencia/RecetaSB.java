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
import uy.edu.ort.entidades.RecetaEntity;
import uy.edu.ort.dominio.Receta;
import uy.edu.ort.dominio.Usuario;

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
    public void alta(Receta receta,Usuario usuario) {
        RecetaEntity entity= new RecetaEntity();
        entity.setNombre(receta.getNombre());
        entity.setUsuario(usuarioSB.obtenerPorNombre(usuario.getNombre()));
        entity.setValoracion(receta.getValoracion());
        entity.setProcedimiento(receta.getProcedimiento());
        //entity.setIngredientes(ingredienteSB.obtenerLista(receta.getIngredientes()));
        
        if(!em.contains(entity)){
            em.persist(entity);
        }
    }

    @Override
    public void modificar(Receta receta) {
        RecetaEntity original=this.obtenerPorNombre(receta.getNombre());
        original.setValoracion(receta.getValoracion());
        original.setProcedimiento(receta.getProcedimiento());
//        original.setIngredientes(ingredienteSB.obtenerLista(receta.getIngredientes()));
        
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
    public RecetaEntity obtenerPorNombre(String nombre)
    {
        try{
            return em.createNamedQuery("RecetaEntity.findByNombre",RecetaEntity.class).setParameter("nombre", nombre).getSingleResult();
        }
        catch(NoResultException nores){}
        return null;
    }
    @Override
    public Receta obtenerDTO(RecetaEntity entidad)
    {
        Receta receta= new Receta();
        receta.setNombre(entidad.getNombre());
        receta.setProcedimiento(entidad.getProcedimiento());
        receta.setIngredientes(ingredienteSB.obtenerLista(entidad.getIngredientes()));
        receta.setValoracion(entidad.getValoracion());
        return receta;
        
    }

    @Override
    public List<Receta> obtenerListaDTO(List<RecetaEntity> recetas) {
        List<Receta> recs = new ArrayList();
        Iterator it = recetas.iterator();
        while(it.hasNext())
        {
            Receta r= obtenerDTO((RecetaEntity)it.next());
            recs.add(r);
        }
        return recs;
    }
}

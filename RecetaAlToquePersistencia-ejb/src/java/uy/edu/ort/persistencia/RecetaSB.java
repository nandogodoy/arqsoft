/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import uy.edu.ort.dominio.Ingrediente;
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
    
    @EJB
    UsuarioSBLocal usuarioSB;
    @EJB
    IngredienteSBLocal ingredienteSB;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void alta(Receta receta,Usuario usuario) {
        RecetaEntity entity= new RecetaEntity();
        entity.setNombre(receta.getNombre());
        entity.setUsuario(usuarioSB.obtenerPorNombre(usuario.getNombre()));
        entity.setValoracion(receta.getValoracion());
        entity.setCantValoraciones(receta.getCantValoraciones());
        entity.setProcedimiento(receta.getProcedimiento());
        entity.setIngredientes(ingredienteSB.obtenerLista(receta.getIngredientes()));
        
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
            TypedQuery<RecetaEntity> query=em.createNamedQuery("RecetaEntity.findByNombre",RecetaEntity.class);
            query.setParameter("nombre", nombre);
            RecetaEntity entidad=query.getSingleResult();
            return entidad;
        }
        catch(NoResultException nores)
        {
            System.out.println(nores.getMessage());
            System.out.println(nores.getCause());
        }
        return null;
    }
    @Override
    public Receta obtenerDTO(RecetaEntity entidad)
    {
        Receta receta= new Receta();
        receta.setNombre(entidad.getNombre());
        receta.setProcedimiento(entidad.getProcedimiento());
        receta.setIngredientes(ingredienteSB.obtenerListaDTO(entidad.getIngredientes()));
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

    @Override
    public void valorar(Receta receta, float valoracion) {
        RecetaEntity entidad = obtenerPorNombre(receta.getNombre());
        int cantidad = entidad.getCantValoraciones();
        float valorPrevio = entidad.getValoracion();
        float valorNuevo= (valorPrevio*cantidad + valoracion )/( cantidad + 1 );
        entidad.setValoracion(valorNuevo);
        entidad.setCantValoraciones(cantidad+1);
        em.merge(entidad);
    }
    
    @Override
    public List<Receta> obtenerLista (List<Ingrediente> ingredientes) {
        List<RecetaEntity> lista = em.createNamedQuery("RecetaEntity.findAll",RecetaEntity.class).getResultList();
        List<Receta> retorno = new ArrayList<Receta>();
	for (RecetaEntity entidad : lista) {
	    if(entidad.getIngredientes().containsAll(ingredienteSB.obtenerLista(ingredientes)))
		retorno.add(obtenerDTO(entidad));
	}
        return retorno;
    }
}

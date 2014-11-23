/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import uy.edu.ort.dominio.Busqueda;
import uy.edu.ort.dominio.Ingrediente;
import uy.edu.ort.dominio.Receta;
import uy.edu.ort.entidades.BusquedaEntity;
import uy.edu.ort.entidades.IngredienteEntity;
import uy.edu.ort.entidades.RecetaEntity;

/**
 *
 * @author Richard
 */
@Stateless
public class BusquedaSB implements BusquedaSBLocal {

    @PersistenceContext
    EntityManager em;
    @EJB
    UsuarioSBLocal usuarioSB;
    @EJB
    IngredienteSBLocal ingredienteSB;
    @EJB
    RecetaSBLocal recetaSB;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void alta(Busqueda busqueda)
    {
        BusquedaEntity entity= new BusquedaEntity();
        entity.setUsuario(usuarioSB.obtenerPorNombre(busqueda.getUsuario().getNombre()));
        entity.setFecha(new Date());
        List<IngredienteEntity> ings= new ArrayList();
        Iterator<Ingrediente> it= busqueda.getIngredientes().iterator();
        while (it.hasNext())
        {
            //ings.add(ingredienteSB.obtenerPorNombre(it.next().getNombre()));
        }
        entity.setIngredientes(ings);
        List<RecetaEntity> recs = new ArrayList();
        if(!em.contains(entity)){
            em.persist(entity);
        }
    }
    
    @Override
    public List<Busqueda> obtenerPorUsuario(String usuario)
    {
        try{
            List<Busqueda> lista= new ArrayList();
            List<BusquedaEntity> entidades=em.createNamedQuery("BusquedaEntity.findByUsuario",BusquedaEntity.class).setParameter("usuario", usuario).getResultList();
            Iterator<BusquedaEntity> it= entidades.iterator();
            while(it.hasNext())
            {
                BusquedaEntity entity= it.next();
                List<Ingrediente> ings= new ArrayList();
                Iterator <IngredienteEntity> itIngrediente = entity.getIngredientes().iterator();
                while(itIngrediente.hasNext())
                {
                    IngredienteEntity ing = itIngrediente.next();
                    Ingrediente ingrediente= new Ingrediente(ing.getNombre());
                    ings.add(ingrediente);
                }

            }
            return lista;
        }
        catch (NoResultException nores){}
        return null;
    }
}

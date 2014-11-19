/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import uy.edu.ort.dominio.Receta;
import uy.edu.ort.dominio.Usuario;
import uy.edu.ort.entidades.UsuarioEntity;

/**
 *
 * @author Richard
 */
@Stateless
public class UsuarioSB implements UsuarioSBLocal {

    @PersistenceContext
    EntityManager em;
    RecetaSBLocal recetaSB;
    
    @Override
    public void alta(Usuario usuario) {
        UsuarioEntity entity= new UsuarioEntity();
        entity.setNombre(usuario.getNombre());
        entity.setEmail(usuario.getEmail());
        entity.setPassword(usuario.getPassword());
        entity.setToken(usuario.getToken());
        entity.setExpira(usuario.getExpira());
        if(!em.contains(entity)){
            em.persist(entity);        
        }
    }

    @Override
    public void eliminar(Usuario usuario) {
        UsuarioEntity borrar= this.obtenerPorNombre(usuario.getNombre());
        if(em.contains(borrar)){
            em.remove(borrar);
        }
    
    }

    @Override
    public void modificar(Usuario usuario) {
        UsuarioEntity original= this.obtenerPorNombre(usuario.getNombre());
        original.setEmail(usuario.getEmail());
        original.setPassword(usuario.getPassword());
        original.setValoracion(usuario.getValoracion());
        original.setToken(usuario.getToken());
        original.setExpira(usuario.getExpira());
        if(em.contains(original)){
            em.merge(original);
        }
    }
    @Override
    public UsuarioEntity obtenerPorNombre(String nombre){
        TypedQuery<UsuarioEntity> query= em.createNamedQuery("UsuarioEntity.findByNombre", UsuarioEntity.class);
        query.setParameter("nombre", nombre);
        try{
            UsuarioEntity usuario= query.getSingleResult();
            return usuario;
        }
        catch(Exception e){}
        return null;
    }
    
    @Override
    public Usuario obtenerDTO(UsuarioEntity u)
    {
        Usuario usuario= new Usuario();
        usuario.setNombre(u.getNombre());
        usuario.setEmail(u.getEmail());
        usuario.setPassword(u.getPassword());
        usuario.setValoracion(u.getValoracion());
        usuario.setToken(u.getToken());
        usuario.setExpira(u.getExpira());
        List<Receta> recetas= recetaSB.obtenerListaDTO(u.getRecetas());
        usuario.setRecetas(recetas);
        return usuario;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void expirarToken(Usuario usuario) {
        UsuarioEntity original= this.obtenerPorNombre(usuario.getNombre());
        original.setExpira(usuario.getExpira());
        if(em.contains(original)){
            em.merge(original);
        }
    }
    
}

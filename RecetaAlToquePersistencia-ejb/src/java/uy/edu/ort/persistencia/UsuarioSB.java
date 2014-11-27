/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    @EJB
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
            UsuarioEntity usuario = query.getSingleResult();
            return usuario;
        }
        catch(Exception e){}
        return null;
    }
    
    
    @Override
    public Usuario obtenerPorNombreDTO (String nombre) {        
        return this.obtenerDTO(this.obtenerPorNombre(nombre));
    }
    
    @Override
    public Usuario obtenerPorEmailDTO (String email) {
	TypedQuery<UsuarioEntity> query= em.createNamedQuery("UsuarioEntity.findByEmail", UsuarioEntity.class);
        query.setParameter("email", email);
        try{
            UsuarioEntity usuario = query.getSingleResult();
            return this.obtenerDTO(usuario);
        }
        catch(Exception e){
	    return null;
	}
        
    }
    
    @Override
    public Usuario obtenerDTO (UsuarioEntity u) {
	if (u != null) {
	    Usuario usuario= new Usuario();
	    usuario.setNombre(u.getNombre());
	    usuario.setEmail(u.getEmail());
	    usuario.setPassword(u.getPassword());
	    usuario.setValoracion(u.getValoracion());
	    usuario.setToken(u.getToken());
	    usuario.setExpira(u.getExpira());
	    return usuario;
	}
	return null;
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
    
    @Override
    public Usuario obtenerPorEmailYContraenia(String email, String contrasenia) {
        TypedQuery<UsuarioEntity> query= em.createNamedQuery("UsuarioEntity.findByMailPasswd", UsuarioEntity.class);
        query.setParameter("mail", email);
        query.setParameter("passwd", contrasenia);
        try{
            UsuarioEntity usuario = query.getSingleResult();
            return this.obtenerDTO(usuario);
        }
        catch(Exception e){return null;}
    }
    
    @Override
    public Usuario obtenerPorToken(String token) {
	Usuario usuario = null;
        TypedQuery<UsuarioEntity> query= em.createNamedQuery("UsuarioEntity.findByToken", UsuarioEntity.class);
        query.setParameter("token", token);
        try{
            //UsuarioEntity usuario = query.getSingleResult();
	    List results = query.getResultList();
	    UsuarioEntity usuarioE = null;
	    if(!results.isEmpty()){
		// ignores multiple results
		usuarioE = (UsuarioEntity)results.get(0);
		usuario = this.obtenerDTO(usuarioE);
	    }
            return usuario;
        } catch(Exception e){
	    System.out.println("No se encontro el usuario por token: "+token);
	    System.out.println("e.getMessage(): "+e.getMessage());
	    Usuario usu = new Usuario();
	    usu.setEmail("joseperez@gmail.com");
	    usu.setToken("tokendeusuario");
	    usu.setNombre("josesito");
	    usu.setPassword("password");
	    return usu;
	}
    }
    
    
    @Override
    public String generarToken(Usuario usuario) {
        String token = "";
        String generador = "Tu r3c3ta" + usuario.getEmail() + (new Date()).getTime();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            token = md.digest(generador.getBytes()).toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioSB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return token;
    }

    
    @Override
    public void limpiarToken(String email) {
	// Borro el token del usuario
    }

    
    @Override
    public List<Usuario> top10Valorados() {
	ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	//Query query = em.createNativeQuery("SELECT u FROM usuarioentity i WHERE 1 ORDER BY u.valoracion DESC LIMIT 10");
	TypedQuery<UsuarioEntity> query= em.createNamedQuery("UsuarioEntity.topValorados", UsuarioEntity.class);
        try{
            List<UsuarioEntity> resultList = query.setMaxResults(10).getResultList();
            for (UsuarioEntity usuarioEntity : resultList){
                listaUsuarios.add(this.obtenerDTO(usuarioEntity));
            }
            return listaUsuarios;
        }
        catch(NoResultException e){}
	return new ArrayList<Usuario>();
    }
}

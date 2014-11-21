/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.rest.dummy;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uy.edu.ort.dominio.Receta;
import uy.edu.ort.dominio.Usuario;
import uy.edu.ort.entidades.RecetaEntity;
import uy.edu.ort.entidades.UsuarioEntity;

/**
 *
 * @author ASUS
 */
public class UsuarioDummy {
    
    
    public UsuarioEntity obtenerPorEmailYContraenia(String email, String contrasenia) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(new Long("1"));
        usuario.setEmail(email);
	usuario.setNombre("Nombre de usuario");
        return usuario;
    }
    
    
    public UsuarioEntity obtenerPorToken(String token) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(new Long("1"));
        usuario.setEmail("email@ort.edu.uy");
	usuario.setNombre("Nombre de usuario");
        return usuario;
    }

    
    public boolean alta(Usuario usuario) {
        return true;
    }
    
    public String generarToken(Usuario usuario) {
        String token = "";
        String generador = "Tu r3c3ta" + usuario.getEmail() + (new Date()).getTime();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            token = md.digest(generador.getBytes()).toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioDummy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return token;
    }

    public void limpiarToken(String email) {
	// Borro el token del usuario
    }
    
    
    
    
    public Usuario obtenerDTO(UsuarioEntity u) {
        Usuario usuario= new Usuario();
        usuario.setNombre(u.getNombre());
        usuario.setEmail(u.getEmail());
        usuario.setPassword(u.getPassword());
        usuario.setValoracion(u.getValoracion());
        List<Receta> recetas= new ArrayList();
        Iterator<RecetaEntity> it = u.getRecetas().iterator();
        while(it.hasNext()) {
            RecetaEntity entidad= it.next();
            Receta receta = new Receta();
            receta.setNombre(entidad.getNombre());
            receta.setProcedimiento(entidad.getProcedimiento());
	    /*
            receta.setPrincipal(new Ingrediente(entidad.getPrincipal().getNombre()));
            receta.setSegundo(new Ingrediente(entidad.getSegundo().getNombre()));
            receta.setTercero(new Ingrediente(entidad.getTercero().getNombre()));
            receta.setCuarto(new Ingrediente(entidad.getCuarto().getNombre()));
	    */
            receta.setValoracion(entidad.getValoracion());
            receta.setUsuario(usuario);
            recetas.add(receta);
        }
        usuario.setRecetas(recetas);
        return usuario;
    }
    
    
}

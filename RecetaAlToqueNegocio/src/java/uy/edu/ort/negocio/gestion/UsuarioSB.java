/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.negocio.gestion;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import uy.edu.ort.dominio.Busqueda;
import uy.edu.ort.dominio.Receta;
import uy.edu.ort.dominio.Usuario;
import uy.edu.ort.persistencia.UniqueConstraintException;
import uy.edu.ort.persistencia.UsuarioSBLocal;

/**
 *
 * @author Richard
 */
@Stateless
public class UsuarioSB implements UsuarioSBNegocio {

    
    @EJB
    private UsuarioSBLocal usuarioEJB;
    
    
    @Override
    public Usuario alta(Usuario usuario) throws DatosDuplicadosException{
	this.encriptarPassword(usuario);
	this.generarToken(usuario);
	Usuario usuarioNombre = usuarioEJB.obtenerPorNombreDTO(usuario.getNombre());
	if (usuarioNombre != null) {
	    throw new DatosDuplicadosException("Ya existe un usuario con ese nombre");
	}
	Usuario usuarioEmail = usuarioEJB.obtenerPorEmailDTO(usuario.getEmail());
	if (usuarioEmail != null) {
	    throw new DatosDuplicadosException("Ya existe un usuario con ese email");
	}
        try {
            usuarioEJB.alta(usuario);
        } catch (UniqueConstraintException ex) {
            Logger.getLogger(UsuarioSB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

	this.actualizarExpira(usuario);
	usuarioEJB.modificar(usuario);
	return usuario;
    }

    @Override
    public void eliminar(Usuario usuario) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Usuario usuario) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String login(Usuario usuario) throws DatosInvalidosException {
	this.encriptarPassword(usuario);
	usuario = usuarioEJB.obtenerPorEmailYContraenia(usuario.getEmail(), usuario.getPassword());
	if (usuario == null) {
	    throw new DatosInvalidosException("No existe usuario para esa combinacion de email y contraseña");
	}
	this.generarToken(usuario);
	this.actualizarExpira(usuario);
	usuarioEJB.modificar(usuario);
	return usuario.getToken();
    }

     
    @Override
    public Usuario obtenerPorToken(String token) throws TokenInvalidoException {
	Date now = new Date();
	Usuario usuario = usuarioEJB.obtenerPorToken(token);
	if (usuario == null || usuario.getExpira().before(now)) {
	    throw new TokenInvalidoException("El token expiro");
	}
	return usuario;
    }

    @Override
    public void logout(Usuario usuario) {
	usuario.setExpira(new Date());
	usuarioEJB.modificar(usuario);
    }

    
    @Override
    public List<Usuario> top10Valorados() {
        List<Usuario> lista = usuarioEJB.top10Valorados();
        return lista;
    }
    
    
    private void generarToken(Usuario usuario) {
	long now = (new Date()).getTime();
        String generador = "Tu r3c3ta" + usuario.getEmail() + now;
        usuario.setToken(this.md5Java(generador));
	usuario.setExpira(new Date(now + 5 * 60 * 1000));
    }
    
    
    private void encriptarPassword(Usuario usuario) {
	usuario.setPassword(this.md5Java(usuario.getPassword()));        
    }
    
    private void actualizarExpira (Usuario usuario) {
	long now = (new Date()).getTime();
	usuario.setExpira(new Date(now + 5 * 60 * 1000));
    }
	    
    
    
    private String md5Java (String message){
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
           
            //converting byte array to Hexadecimal String
           StringBuilder sb = new StringBuilder(2*hash.length);
           for(byte b : hash){
               sb.append(String.format("%02x", b&0xff));
           }
          
           digest = sb.toString();
          
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioSB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return digest;
    }


    
}

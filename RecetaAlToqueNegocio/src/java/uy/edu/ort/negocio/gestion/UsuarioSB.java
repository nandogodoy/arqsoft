/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.negocio.gestion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import uy.edu.ort.dominio.Usuario;
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
    public Usuario alta(Usuario usuario) {
	this.encriptarPassword(usuario);
	this.generarToken(usuario);
	usuarioEJB.alta(usuario);
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
    public String login(Usuario usuario) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario obtenerPorToken(String token) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void expirarToken(Usuario usuario) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void generarToken(Usuario usuario) {
        String token = "";
	long now = (new Date()).getTime();
        String generador = "Tu r3c3ta" + usuario.getEmail() + now;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            token = md.digest(generador.getBytes()).toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioSB.class.getName()).log(Level.SEVERE, null, ex);
        }
        usuario.setToken(token);
	usuario.setExpira(new Date(now + 5 * 60 * 1000));
    }
    
    
    private void encriptarPassword(Usuario usuario) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            usuario.setPassword(md.digest(usuario.getPassword().getBytes()).toString());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioSB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

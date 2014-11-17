/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.dummy;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import uy.edu.ort.dominio.Usuario;
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
    
    
}

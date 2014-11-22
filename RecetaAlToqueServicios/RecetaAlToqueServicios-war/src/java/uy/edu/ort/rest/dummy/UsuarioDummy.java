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


/**
 *
 * @author ASUS
 */
public class UsuarioDummy {
    
    
    public Usuario obtenerPorEmailYContraenia(String email, String contrasenia) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
	usuario.setNombre("Nombre de usuario");
        return usuario;
    }
    
    
    public Usuario obtenerPorToken(String token) {
        Usuario usuario = new Usuario();
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

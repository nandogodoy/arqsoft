/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.ws;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Richard
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UsuarioSB port= new UsuarioSB_Service().getUsuarioSBPort();
        Usuario usuario= new Usuario();
        usuario.setNombre("Maru");
        usuario.setEmail("maru@elgourmet.com");
        usuario.setPassword("m4ru");
        port.alta(usuario);
        System.out.println(usuario.getNombre());
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

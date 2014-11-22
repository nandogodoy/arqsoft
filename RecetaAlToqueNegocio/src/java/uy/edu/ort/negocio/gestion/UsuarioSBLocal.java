/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.negocio.gestion;

import javax.ejb.Local;
import uy.edu.ort.dominio.Usuario;

/**
 *
 * @author Richard
 */
@Local
public interface UsuarioSBLocal {
    
    
    public Usuario alta(Usuario usuario);
    public void eliminar(Usuario usuario);
    public void modificar(Usuario usuario);

    
    public String login(Usuario usuario);
    public Usuario obtenerPorToken(String token);
    public void expirarToken(Usuario usuario);
    
}

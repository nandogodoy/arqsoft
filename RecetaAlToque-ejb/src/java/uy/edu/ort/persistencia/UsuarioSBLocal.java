/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import javax.ejb.Local;
import uy.edu.ort.dominio.Usuario;
/**
 *
 * @author Richard
 */
import uy.edu.ort.entidades.UsuarioEntity;
@Local
public interface UsuarioSBLocal {
    public void alta(Usuario usuario);
    public void eliminar(Usuario usuario);
    public void modificar(Usuario usuario);
    
    public UsuarioEntity obtenerPorNombre(String nombre);
}

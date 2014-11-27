/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import java.util.List;
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

    public Usuario obtenerDTO(UsuarioEntity u);
    public void expirarToken(Usuario usuario);
    public Usuario obtenerPorEmailYContraenia(String email, String contrasenia);
    public String generarToken(Usuario usuario);
    public void limpiarToken(String email);
    public Usuario obtenerPorToken(String token);
    
    public List<Usuario> top10Valorados();
    
}

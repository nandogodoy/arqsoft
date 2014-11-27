/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.negocio.gestion;

import java.util.List;
import javax.ejb.Local;
import uy.edu.ort.dominio.Busqueda;
import uy.edu.ort.dominio.Receta;
import uy.edu.ort.dominio.Usuario;

/**
 *
 * @author Richard
 */
@Local
public interface UsuarioSBNegocio {
    
    
    public Usuario alta(Usuario usuario) throws DatosDuplicadosException;
    public void eliminar(Usuario usuario);
    public void modificar(Usuario usuario);

    
    public String login(Usuario usuario) throws DatosInvalidosException;
    public void logout(Usuario usuario);
    public Usuario obtenerPorToken(String token) throws TokenInvalidoException;
    
    public List<Usuario> top10Valorados();
    
}

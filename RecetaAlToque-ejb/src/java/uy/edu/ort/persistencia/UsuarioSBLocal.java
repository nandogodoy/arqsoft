/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import javax.ejb.Local;
/**
 *
 * @author Richard
 */
import uy.edu.ort.entidades.UsuarioEntity;
@Local
public interface UsuarioSBLocal {
    public void alta(UsuarioEntity usuario);
    public void eliminar(UsuarioEntity usuario);
    public void modificar(UsuarioEntity usuario);
}

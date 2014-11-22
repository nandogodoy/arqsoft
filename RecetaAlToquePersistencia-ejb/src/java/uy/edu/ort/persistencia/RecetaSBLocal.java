/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.persistencia;

import java.util.List;
import javax.ejb.Local;
import uy.edu.ort.dominio.Receta;
import uy.edu.ort.dominio.Usuario;
import uy.edu.ort.entidades.RecetaEntity;
/**
 *
 * @author Richard
 */
@Local
public interface RecetaSBLocal {
    public void alta(Receta receta, Usuario usuario);

    public void modificar(Receta receta);
    public void eliminar(Receta receta);
    public RecetaEntity obtenerPorNombre(String nombre);

    public Receta obtenerDTO(RecetaEntity entidad);

    public List<Receta> obtenerListaDTO(List<RecetaEntity> recetas);

    public void valorar(Receta receta, float valoracion);
}

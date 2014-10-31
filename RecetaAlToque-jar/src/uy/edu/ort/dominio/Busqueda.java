/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Richard
 */
public class Busqueda {
    private Usuario usuario;
    private List<Ingrediente> ingredientes;
    private List<Receta> recetas;
    private Date fecha;

    public Busqueda(Usuario usuario, List<Ingrediente> ingredientes, Date fecha) {
        this.usuario = usuario;
        this.ingredientes = ingredientes;
        this.fecha = fecha;
        this.recetas = new ArrayList<Receta>();
    }

    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.dominio;

/**
 *
 * @author Richard
 */
public class Receta {
    private String nombre;
    private float valoracion;
    private String procedimiento;
    private Usuario usuario;
    private Ingrediente principal;
    private Ingrediente segundo;
    private Ingrediente tercero;
    private Ingrediente cuarto;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ingrediente getPrincipal() {
        return principal;
    }

    public void setPrincipal(Ingrediente principal) {
        this.principal = principal;
    }

    public Ingrediente getSegundo() {
        return segundo;
    }

    public void setSegundo(Ingrediente segundo) {
        this.segundo = segundo;
    }

    public Ingrediente getTercero() {
        return tercero;
    }

    public void setTercero(Ingrediente tercero) {
        this.tercero = tercero;
    }

    public Ingrediente getCuarto() {
        return cuarto;
    }

    public void setCuarto(Ingrediente cuarto) {
        this.cuarto = cuarto;
    }
    
    
    
}

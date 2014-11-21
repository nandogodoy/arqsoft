/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.dominio;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Richard
 */
public class Usuario {
    private String nombre;
    private String email;
    private String password;
    private float valoracion;
    private String token;
    private Date expira;
    
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpira() {
        return expira;
    }

    public void setExpira(Date expira) {
        this.expira = expira;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uy.edu.ort.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Richard
 */
@Entity
public class RecetaEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private UsuarioEntity usuario;
    
    @Column (name="nombre")
    private String nombre;
    
    @Column(name="valoracion")
    private float valoracion;
    
    @Column (name="procedimiento")
    private String procedimiento;
    
    @Column (name="principal")
    @OneToOne (mappedBy="IngredienteEntity")
    private IngredienteEntity principal;
    
    @Column (name="segundo")
    @OneToOne(mappedBy="IngredienteEntity")
    private IngredienteEntity segundo;
    
    @Column (name="tercero")
    @OneToOne(mappedBy="IngredienteEntity")
    private IngredienteEntity tercero;
    
    @Column (name="cuarto")
    @OneToOne(mappedBy="IngredienteEntity")
    private IngredienteEntity cuarto;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

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

    public IngredienteEntity getPrincipal() {
        return principal;
    }

    public void setPrincipal(IngredienteEntity principal) {
        this.principal = principal;
    }

    public IngredienteEntity getSegundo() {
        return segundo;
    }

    public void setSegundo(IngredienteEntity segundo) {
        this.segundo = segundo;
    }

    public IngredienteEntity getTercero() {
        return tercero;
    }

    public void setTercero(IngredienteEntity tercero) {
        this.tercero = tercero;
    }

    public IngredienteEntity getCuarto() {
        return cuarto;
    }

    public void setCuarto(IngredienteEntity cuarto) {
        this.cuarto = cuarto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecetaEntity)) {
            return false;
        }
        RecetaEntity other = (RecetaEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uy.edu.ort.dominio.Receta[ id=" + id + " ]";
    }
    
}
